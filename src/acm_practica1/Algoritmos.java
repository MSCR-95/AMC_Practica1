package acm_practica1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.sound.midi.SoundbankResource;

/**
 *
 * @author Maria S
 */
public class Algoritmos {

    public List<Punto> puntos = new ArrayList<>();
    private DecimalFormat df = new DecimalFormat("#.########");

    private int nComparacionesExhaustiva, nComparacionesDyV, nComparaciones, nComparacionesPoda, nComparacionesDyVMejorado, nComparacionesTest, intents = 0;
    //private int indiceP1, indiceP2 = -1;

    private double tiempoBusquedaExhaustiva = 0.0;
    private double tiempoBusquedaConPoda = 0.0;
    private double tiempoDivideYVenceras = 0.0;
    private double tiempoEncontrarPuntosMasCercanos = 0.0;
    private double tiempoEncontrarPuntosMasCercanosTest = 0.0;
    private double tiempoDivideYVencerasMejorado = 0.0;
    private double tiempoDyV = 0.0;
    //private double dMinAnterior = 10000.0;
    private double dMinAnterior = Double.POSITIVE_INFINITY;
    private double dMinAnteriorTest = 10000.0;
    private double dMinfSol = 10000.0;
    private Solucion dMinfSolu = new Solucion(10000, 0, 0, 0, 0);

    //Guardamos una copia de la lista original
    public Algoritmos(List<Punto> Listapuntos) {
        this.puntos = Listapuntos;
    }

    //CONSTRUCTOR VACIO PARA PRUEBAS
    public Algoritmos() {
    }

    //AÑADIDO IZQUIERDA Y DERECHA PARA USAR ESTE ALGORITMO EN EL DIVIDE Y VENCERAS
    public Solucion busquedaExhaustiva(List<Punto> punto, int izquierda, int derecha, int nComparaciones) {

        double startTime = System.nanoTime();

        Solucion S = new Solucion(0, 0, 0, 0, 0);

        double distancia = -1;
        S.dMin = Double.POSITIVE_INFINITY;
        nComparacionesExhaustiva = 0;
        //this.nComparaciones = 0;

        for (int i = izquierda; i < derecha; i++) {
            for (int j = i + 1; j < derecha; j++) {
                //Controlamos que un punto no calcule la distancia con el mismo
                ParDePuntos dosPuntos = new ParDePuntos(punto.get(i), punto.get(j));
                distancia = dosPuntos.distancia();
                S.nComparaciones++;
                nComparacionesExhaustiva++;
                this.nComparaciones++;
                nComparaciones++;
                //FILTRAMOS
                if (distancia < S.dMin) {
                    S.dMin = distancia;
                    S.indiceP1 = i;
                    S.indiceP2 = j;
                }
                //AQUI NOS MUESTRA TODOS LOS PUNTOS Y TODAS SUS DISTANCIAS
            }
        }
        //Mostramos los puntos, la distancia minima entre ellos y el numero de comparaciones realizadas
        double endTime = System.nanoTime();
        S.time = (endTime - startTime) / 1e6; //Pasamos a mseg
        tiempoBusquedaExhaustiva = S.time;
        tiempoDivideYVenceras = tiempoDivideYVenceras + S.time;
        return S;
    }

    /*
     *public Solucion busquedaExhaustivaSucia(List<Punto> punto, int izquierda, int derecha) {
        double startTime = System.nanoTime();


        double distancia = -1;

        Solucion S = new Solucion(distanciaMin, -1, -1);


        for (int i = izquierda; i < derecha; i++) {
            for (int j = i + 1; j < derecha; j++) {
                ParDePuntos dosPuntos = new ParDePuntos(punto.get(i), punto.get(j));
                distancia = dosPuntos.distancia();
                nComparaciones++;
                nComparacionesDyV++;
                if (distancia < S.dMin) {
                    S.dMin = distancia;
                    S.indiceP1 = i;
                    S.indiceP2 = j;
                }
            }
        }
     */
    //No sirve, tenemos que implementar quickSort
    /*
    private void ordenarPuntosPorXQuickSort(List<Punto> punto) {
        //Ordenar ArrayList de punto por X en orden ascendente usando un comparador personalizado
        Collections.sort(punto, new Comparator<Punto>() {
            @Override
            public int compare(Punto p1, Punto p2) {
                return Double.compare(p1.getX(), p2.getX());
            }
        });
    }

    //No sirve, tenemos que implementar quickSort
    private void ordenarPuntosPorYQuickSort(List<Punto> punto) {
        //Ordenar ArrayList de punto por Y en orden ascendente usando un comparador personalizado
        Collections.sort(punto, new Comparator<Punto>() {
            @Override
            public int compare(Punto p1, Punto p2) {
                return Double.compare(p1.getY(), p2.getY());
            }
        });
    }
    */
    public Solucion busquedaConPoda(List<Punto> punto) {
        double startTime = System.nanoTime();

        double distancia = -1;
        double distanciaX = 0;

        nComparacionesPoda = 0;
        nComparaciones = 0;

        Solucion S = new Solucion(0, 0, 0, 0, 0);
        S.dMin = Double.POSITIVE_INFINITY;
        //int nComparaciones = 0;
        //Ordenamos la lista por la x
        ordenarPuntosPorXQuickSort(punto);

        for (int i = 0; i < punto.size(); i++) {
            int j = i + 1;
            Boolean alarma = false;
            while (j < punto.size() && !alarma) {
                distanciaX = Math.abs(punto.get(j).getX() - punto.get(i).getX());
                // Poda: no es necesario seguir con puntos más lejos en la coordenada x.
                if (distanciaX >= S.dMin) {
                    alarma = true;
                }
                if (!alarma) {
                    S.nComparaciones++;
                    nComparacionesPoda++;
                    ParDePuntos dosPuntos = new ParDePuntos(punto.get(i), punto.get(j));
                    distancia = dosPuntos.distancia();
                    if (distancia < S.dMin) {
                        S.dMin = distancia;
                        S.indiceP1 = dosPuntos.getP1().getIndice();
                        S.indiceP2 = dosPuntos.getP2().getIndice();
                    }
                }
                j++;
            }
        }

        double endTime = System.nanoTime();
        S.time = (endTime - startTime) / 1e6;
        tiempoBusquedaConPoda = S.time;
        return S;
    }

    public Solucion busquedaDivideYVenceras(List<Punto> punto) {
        //Primero, ordena la lista de puntos por la coordenada x.
        double startTime = System.nanoTime();
        ordenarPuntosPorXQuickSort(punto);

        nComparacionesDyV = 0;
        nComparaciones = 0;
        tiempoDivideYVenceras = 0.0;

        Solucion tal = buscarPuntosMasCercanos(punto, 0, punto.size() - 1, nComparacionesDyV);
        double endTime = System.nanoTime();
        double time = (endTime - startTime) / 1e6;
        tal.time = time;
        return tal;
    }

    private Solucion buscarPuntosMasCercanos(List<Punto> punto, int izquierda, int derecha, int nComparaciones) {
        //double startTime = System.nanoTime();

        if (derecha - izquierda <= 2) {
            //Cuando hay pocos puntos, realiza una búsqueda exhaustiva.
            Solucion loq = busquedaExhaustiva(punto, izquierda, derecha, nComparacionesDyV);
            return loq;
        }
        //Calculamos la mitad
        int mitad = (izquierda + derecha) / 2;
        Punto puntoMitad = punto.get(mitad);

        //Calcula la distancia minima por la izquierda
        Solucion distanciaIzquierda = buscarPuntosMasCercanos(punto, izquierda, mitad, nComparacionesDyV);
        //calcula la distancia minima por la derecha
        Solucion distanciaDerecha = buscarPuntosMasCercanos(punto, mitad + 1, derecha, nComparacionesDyV);
        //Nos quedamos con la distancia mas pequeña de las dos
        double distanciaMinima = Math.min(distanciaIzquierda.dMin, distanciaDerecha.dMin);

        Punto puntoMitadIzq = puntoMitad;
        Punto puntoMitadDer = punto.get(mitad + 1);
        double MediaPuntos = (puntoMitadIzq.getX() + puntoMitadDer.getX()) / 2;
        double franjaIzq = MediaPuntos - distanciaMinima;
        double franjaDer = MediaPuntos + distanciaMinima;
        List<Punto> franja = new ArrayList<>();

        int aux1 = -1;
        int aux2 = -1;
        for (int i = izquierda; i < derecha + 1; i++) {
            if (punto.get(i).getX() >= franjaIzq && punto.get(i).getX() <= franjaDer) {
                franja.add(punto.get(i));
                if (aux1 == -1) {
                    aux1 = i;
                }
                aux2 = i;
            }
        }

        Solucion franjaSol = busquedaExhaustiva(punto, aux1, aux2, nComparacionesDyV);
        Solucion Legit;
        if (distanciaIzquierda.dMin < distanciaDerecha.dMin && distanciaIzquierda.dMin < franjaSol.dMin) {
            Legit = distanciaIzquierda;
        } else if (distanciaDerecha.dMin < distanciaIzquierda.dMin && distanciaDerecha.dMin < franjaSol.dMin) {
            Legit = distanciaDerecha;
        } else if (franjaSol.dMin < distanciaIzquierda.dMin && franjaSol.dMin < distanciaDerecha.dMin) {
            Legit = franjaSol;
        } else {
            Legit = distanciaIzquierda;
        }

        if (Legit.indiceP1 != -1 && Legit.indiceP2 != -1) {
            ParDePuntos Pp = new ParDePuntos(punto.get(Legit.indiceP1), punto.get(Legit.indiceP2));
            if (Legit.dMin < dMinAnterior) {
                dMinAnterior = Legit.dMin;
                Legit.indiceP1 = Pp.getP1().getIndice();
                Legit.indiceP2 = Pp.getP2().getIndice();
            }
        }

        //double endTime = System.nanoTime();
        //System.out.println("Tiempo DyV despues de bPMC: " + (endTime - startTime) / 1e6);
        //Legit.time = (endTime - startTime) / 1e6;
        Legit.time = tiempoDivideYVenceras;
        Legit.nComparaciones = this.nComparaciones;
        return Legit;
    }

    public Solucion busquedaDivideYVencerasMejorado(List<Punto> punto) {
        //Primero, ordena la lista de puntos por la coordenada x.
        nComparacionesDyVMejorado = 0;
        nComparaciones = 0;
        tiempoDivideYVencerasMejorado = 0;
        ordenarPuntosPorXQuickSort(punto);

        Solucion tal = buscarPuntosMasCercanos(punto, 0, punto.size() - 1, nComparacionesDyVMejorado);
        return tal;
    }

    public Solucion busquedaExhaustivaSuciaTest(List<Punto> punto, int izquierda, int derecha, double time, int nComparaciones) {
        double startTime = System.nanoTime();

        Solucion S = new Solucion(0, 0, 0, 0, 0);

        double distancia = -1;
        S.dMin = Double.POSITIVE_INFINITY;
        nComparacionesExhaustiva = 0;
        //this.nComparaciones = 0;
        for (int i = izquierda; i < derecha || intents < 12; i++) {
            for (int j = i + 1; j < derecha; j++) {
                //Controlamos que un punto no calcule la distancia con el mismo
                ParDePuntos dosPuntos = new ParDePuntos(punto.get(i), punto.get(j));
                distancia = dosPuntos.distancia();
                S.nComparaciones++;
                nComparacionesExhaustiva++;
                this.nComparaciones++;
                nComparaciones++;
                //FILTRAMOS
                if (distancia < S.dMin) {
                    S.dMin = distancia;
                    S.indiceP1 = i;
                    S.indiceP2 = j;
                    intents++;
                }
                //AQUI NOS MUESTRA TODOS LOS PUNTOS Y TODAS SUS DISTANCIAS
            }
        }

        //Mostramos los puntos, la distancia minima entre ellos y el numero de comparaciones realizadas
        double endTime = System.nanoTime();
        S.time = (endTime - startTime) / 1e6; //Pasamos a mseg
        tiempoBusquedaExhaustiva = S.time;
        time = time + S.time;
        intents++;
        return S;
    }

    private Solucion buscarPuntosMasCercanosTest(List<Punto> punto, int izquierda, int derecha, double time, int nComparaciones) {
        //double startTime = System.nanoTime();

        if (derecha - izquierda <= 2) {
            //Cuando hay pocos puntos, realiza una búsqueda exhaustiva.
            Solucion loq = busquedaExhaustiva(punto, izquierda, derecha, nComparacionesDyVMejorado);
            //double endTime = System.nanoTime();
            //loq.time = loq.time + (endTime - startTime) / 1e6;
            return loq;
        }
        //Calculamos la mitad
        int mitad = (izquierda + derecha) / 2;
        Punto puntoMitad = punto.get(mitad);

        //Calcula la distancia minima por la izquierda
        Solucion distanciaIzquierda = buscarPuntosMasCercanosTest(punto, izquierda, mitad, tiempoDivideYVencerasMejorado, nComparacionesDyVMejorado);
        //calcula la distancia minima por la derecha
        Solucion distanciaDerecha = buscarPuntosMasCercanosTest(punto, mitad + 1, derecha, tiempoDivideYVencerasMejorado, nComparacionesDyVMejorado);
        //Nos quedamos con la distancia mas pequeña de las dos
        double distanciaMinima = Math.min(distanciaIzquierda.dMin, distanciaDerecha.dMin);

        Punto puntoMitadIzq = puntoMitad;
        Punto puntoMitadDer = punto.get(mitad + 1);
        double MediaPuntos = (puntoMitadIzq.getX() + puntoMitadDer.getX()) / 2;
        double franjaIzq = MediaPuntos - distanciaMinima;
        double franjaDer = MediaPuntos + distanciaMinima;
        List<Punto> franja = new ArrayList<>();

        int aux1 = -1;
        int aux2 = -1;
        for (int i = izquierda; i < derecha + 1; i++) {
            if (punto.get(i).getX() >= franjaIzq && punto.get(i).getX() <= franjaDer) {
                franja.add(punto.get(i));
                if (aux1 == -1) {
                    aux1 = i;
                }
                aux2 = i;
            }
        }

        Solucion franjaSol = busquedaExhaustiva(punto, aux1, aux2, nComparacionesDyVMejorado);
        Solucion Legit;
        if (distanciaIzquierda.dMin < distanciaDerecha.dMin && distanciaIzquierda.dMin < franjaSol.dMin) {
            Legit = distanciaIzquierda;
        } else if (distanciaDerecha.dMin < distanciaIzquierda.dMin && distanciaDerecha.dMin < franjaSol.dMin) {
            Legit = distanciaDerecha;
        } else {
            Legit = distanciaIzquierda;
        }
        ordenarPuntosPorYQuickSort(franja);
        ParDePuntos Pp = new ParDePuntos(punto.get(Legit.indiceP1), punto.get(Legit.indiceP2));
        for (int i = 0; i < franja.size(); i++) {
            for (int j = i + 1; j < franja.size() && i < 12; j++) {
                ParDePuntos Ppp = new ParDePuntos(franja.get(i), franja.get(j));
                double dist = Ppp.distancia();
                if (dist < Pp.distancia()) {
                    Legit.dMin = dist;
                    Legit.indiceP1 = Ppp.getP1().getIndice();
                    Legit.indiceP2 = Ppp.getP2().getIndice();
                }
            }
        }
        /*
        if (franjaSol.dMin < distanciaIzquierda.dMin && franjaSol.dMin < distanciaDerecha.dMin){
            LegitT = franjaSol;
            Legit = LegitT;
        }


        if(Legit.indiceP1 != -1 && Legit.indiceP2 != -1){
             ParDePuntos Ppu = new ParDePuntos(punto.get(Legit.indiceP1), punto.get(Legit.indiceP2));
                if (Legit.dMin < dMinAnterior){
                    dMinAnterior = Legit.dMin;
                    Legit.indiceP1 = Ppu.getP1().getIndice();
                    Legit.indiceP2 = Ppu.getP2().getIndice();
            }
        }
         */

        double endTime = System.nanoTime();
        //System.out.println("Tiempo DyV despues de bPMC: " + (endTime - startTime) / 1e6);
        //Legit.time = (endTime - startTime) / 1e6;
        Legit.time = tiempoDivideYVencerasMejorado;
        Legit.nComparaciones = this.nComparaciones;
        return Legit;
    }

    private Solucion buscarPuntosMasCercanosTest2(List<Punto> punto, int izquierda, int derecha, double time, int nComparaciones) {
        //double startTime = System.nanoTime();

        if (derecha - izquierda <= 2) {
            //Cuando hay pocos puntos, realiza una búsqueda exhaustiva.
            Solucion loq = busquedaExhaustiva(punto, izquierda, derecha, nComparacionesDyV);
            //double endTime = System.nanoTime();
            //loq.time = loq.time + (endTime - startTime) / 1e6;
            return loq;
        }
        //Calculamos la mitad
        int mitad = (izquierda + derecha) / 2;
        Punto puntoMitad = punto.get(mitad);

        //Calcula la distancia minima por la izquierda
        Solucion distanciaIzquierda = buscarPuntosMasCercanosTest2(punto, izquierda, mitad, tiempoDivideYVencerasMejorado, nComparacionesDyVMejorado);
        //calcula la distancia minima por la derecha
        Solucion distanciaDerecha = buscarPuntosMasCercanosTest2(punto, mitad + 1, derecha, tiempoDivideYVencerasMejorado, nComparacionesDyVMejorado);
        //Nos quedamos con la distancia mas pequeña de las dos
        double distanciaMinima = Math.min(distanciaIzquierda.dMin, distanciaDerecha.dMin);

        Punto puntoMitadIzq = puntoMitad;
        Punto puntoMitadDer = punto.get(mitad + 1);
        double MediaPuntos = (puntoMitadIzq.getX() + puntoMitadDer.getX()) / 2;
        double franjaIzq = MediaPuntos - distanciaMinima;
        double franjaDer = MediaPuntos + distanciaMinima;
        List<Punto> franja = new ArrayList<>();

        int aux1 = -1;
        int aux2 = -1;
        for (int i = izquierda; i < derecha + 1; i++) {
            if (punto.get(i).getX() >= franjaIzq && punto.get(i).getX() <= franjaDer) {
                franja.add(punto.get(i));
                if (aux1 == -1) {
                    aux1 = i;
                }
                aux2 = i;
            }
        }

        Solucion franjaSol = busquedaExhaustiva(punto, aux1, aux2, nComparacionesDyV);
        Solucion Legit;
        if (distanciaIzquierda.dMin < distanciaDerecha.dMin && distanciaIzquierda.dMin < franjaSol.dMin) {
            Legit = distanciaIzquierda;
        } else if (distanciaDerecha.dMin < distanciaIzquierda.dMin && distanciaDerecha.dMin < franjaSol.dMin) {
            Legit = distanciaDerecha;
        } else if (franjaSol.dMin < distanciaIzquierda.dMin && franjaSol.dMin < distanciaDerecha.dMin) {
            Legit = franjaSol;
        } else {
            Legit = distanciaIzquierda;
        }

        if (Legit.indiceP1 != -1 && Legit.indiceP2 != -1) {
            ParDePuntos Pp = new ParDePuntos(punto.get(Legit.indiceP1), punto.get(Legit.indiceP2));
            if (Legit.dMin < dMinAnteriorTest) {
                dMinAnteriorTest = Legit.dMin;
                Legit.indiceP1 = Pp.getP1().getIndice();
                Legit.indiceP2 = Pp.getP2().getIndice();
            }
        }

        double endTime = System.nanoTime();
        Legit.time = tiempoDivideYVencerasMejorado;
        Legit.nComparaciones = this.nComparaciones;
        return Legit;
    }

    private Solucion buscarPuntosMasCercanosX(List<Punto> punto, int izquierda, int derecha, double time, int nComparaciones) {
        System.out.println("**X**");
        ordenarPuntosPorXQuickSort(punto);
        double startTime = System.nanoTime();
        //int arriba = 0;
        //int abajo = 0;

        if (derecha - izquierda <= 2) {
            //Cuando hay pocos puntos, realiza una búsqueda exhaustiva.
            Solucion loq = busquedaExhaustiva(punto, izquierda, derecha, nComparacionesDyVMejorado);
            return loq;
        }
        //Calculamos la mitad
        int mitad = (izquierda + derecha) / 2;
        //int media = (arriba + abajo) / 2;
        Punto puntoMitad = punto.get(mitad);
        //Punto puntoMedia = punto.get(media);

        //Calcula la distancia minima por la izquierda
        Solucion distanciaIzquierda = buscarPuntosMasCercanosTest(punto, izquierda, mitad, time, nComparacionesDyVMejorado);
        //calcula la distancia minima por la derecha
        Solucion distanciaDerecha = buscarPuntosMasCercanosTest(punto, mitad + 1, derecha, time, nComparacionesDyVMejorado);
        //Nos quedamos con la distancia mas pequeña de las dos
        double distanciaMinima = Math.min(distanciaIzquierda.dMin, distanciaDerecha.dMin);

        Punto puntoMitadIzq = puntoMitad;
        Punto puntoMitadDer = punto.get(mitad + 1);
        double MediaPuntos = (puntoMitadIzq.getX() + puntoMitadDer.getX()) / 2;
        double franjaIzq = MediaPuntos - distanciaMinima;
        double franjaDer = MediaPuntos + distanciaMinima;
        List<Punto> franja = new ArrayList<>();

        int aux1 = -1;
        int aux2 = -1;
        for (int i = izquierda; i < derecha + 1; i++) {
            if (punto.get(i).getX() >= franjaIzq && punto.get(i).getX() <= franjaDer) {
                franja.add(punto.get(i));
                if (aux1 == -1) {
                    aux1 = i;
                }
                aux2 = i;
            }
        }
        /*int i = 0;
        int j = 0;
        while (i<12) {
            if (punto.get(j).getY() >= franjaIzq && punto.get(j).getY() <= franjaDer){
                franja.add(punto.get(j));
                i++;
                if(aux1 == -1){
                    aux1 = i;
                }
            }
            j++;
        }*/

        ordenarPuntosPorYQuickSort(franja);

        Solucion franjaSol = busquedaExhaustiva(punto, aux1, aux2, nComparacionesDyVMejorado);

        Solucion Legit;
        if (distanciaIzquierda.dMin < distanciaDerecha.dMin && distanciaIzquierda.dMin < franjaSol.dMin) {
            Legit = distanciaIzquierda;
        } else if (distanciaDerecha.dMin < distanciaIzquierda.dMin && distanciaDerecha.dMin < franjaSol.dMin) {
            Legit = distanciaDerecha;
        } else if (franjaSol.dMin < distanciaIzquierda.dMin && franjaSol.dMin < distanciaDerecha.dMin) {
            Legit = franjaSol;
        } else {
            Legit = distanciaIzquierda;
        }

        if (Legit.indiceP1 != -1 && Legit.indiceP2 != -1) {
            ParDePuntos Pp = new ParDePuntos(punto.get(Legit.indiceP1), punto.get(Legit.indiceP2));
            if (Legit.dMin < dMinAnteriorTest) {
                dMinAnteriorTest = Legit.dMin;
                //System.out.println("Distancia dMinAnteriorTest: " + dMinAnteriorTest);
                //System.out.println("Pp1: "+ Pp.getP1().getIndice() + " " + Pp.getP1().getX() + " " + Pp.getP1().getY()+ " Pp2:"+ Pp.getP2().getIndice() + " "  + Pp.getP2().getX() + " " + Pp.getP2().getY());
                //System.out.println("PpDistancia: " + Pp.distancia());
                Legit.indiceP1 = Pp.getP1().getIndice();
                Legit.indiceP2 = Pp.getP2().getIndice();
            }
        }

        double endTime = System.nanoTime();
        Legit.time = (endTime - startTime) / 1e6;
        time = time + Legit.time;
        Legit.nComparaciones = this.nComparaciones;
        return Legit;
    }

    private Solucion buscarPuntosMasCercanosY(List<Punto> punto, int izquierda, int derecha, double time, int nComparaciones) {
        double startTime = System.nanoTime();

        if (derecha - izquierda <= 2) {
            // Cuando hay pocos puntos, realiza una búsqueda exhaustiva.
            Solucion loq = busquedaExhaustiva(punto, izquierda, derecha, nComparacionesDyVMejorado);
            return loq;
        }

        // Calculamos la mitad
        int mitad = (izquierda + derecha) / 2;
        Punto puntoMitad = punto.get(mitad);

        // Calcula la distancia minima por la izquierda
        Solucion distanciaIzquierda = buscarPuntosMasCercanosTest(punto, izquierda, mitad, time, nComparacionesDyVMejorado);
        // Calcula la distancia minima por la derecha
        Solucion distanciaDerecha = buscarPuntosMasCercanosTest(punto, mitad + 1, derecha, time, nComparacionesDyVMejorado);
        // Nos quedamos con la distancia más pequeña de las dos
        double distanciaMinima = Math.min(distanciaIzquierda.dMin, distanciaDerecha.dMin);

        Punto puntoMitadIzq = puntoMitad;
        Punto puntoMitadDer = punto.get(mitad + 1);
        double MediaPuntos = (puntoMitadIzq.getX() + puntoMitadDer.getX()) / 2;
        double franjaIzq = MediaPuntos - distanciaMinima;
        double franjaDer = MediaPuntos + distanciaMinima;
        List<Punto> franja = new ArrayList<>();

        int aux1 = -1;
        int aux2 = -1;
        for (int i = izquierda; i < derecha + 1; i++) {
            if (punto.get(i).getX() >= franjaIzq && punto.get(i).getX() <= franjaDer) {
                franja.add(punto.get(i));
                if (aux1 == -1) {
                    aux1 = i;
                }
                aux2 = i;
            }
        }

        // Ordena la franja por la coordenada y utilizando quicksort
        ordenarPuntosPorYQuickSort(franja);
        Solucion S = busquedaExhaustivaSuciaTest(franja, aux1, aux2, 0, 0);

        // Actualiza el tiempo y el número de comparaciones
        double endTime = System.nanoTime();
        S.time = (endTime - startTime) / 1e6;
        time = time + S.time;
        S.nComparaciones = this.nComparaciones;

        return S;
    }

    /*
    public Solucion busquedaDivideYVencerasRec(List<Punto> p) {
        // Ordena la lista de puntos por la coordenada x.
        ordenarPuntosPorXQuickSort(p);

        nComparacionesDyV = 0;
        nComparaciones = 0;
        tiempoDivideYVenceras = 0.0;

        // Llama a la versión adaptada del algoritmo DyVRec
        Solucion resultado = DyVRec(p, 0, p.size() - 1);

        // Crea y devuelve la solución
        return resultado;
    }

    private Solucion DyVRec(List<Punto> p, int izq, int der) {
        Solucion pDistMin;
        if (der - izq <= 3) {
            // Caso Base: Realiza una búsqueda exhaustiva.
            Solucion Sol = busquedaExhaustiva(p, izq, der, tiempoDivideYVencerasMejorado, nComparacionesDyVMejorado);
            return Sol;
        } else {
            int medio = (izq + der) / 2;

            // Llamadas recursivas
            //System.out.println("p: " + p + "izq: " + izq + "medio: " + medio);
            Solucion pIzq = DyVRec(p, izq, medio);
            //System.out.println("P1: " + pIzq.indiceP1 + "P2: " + pIzq.indiceP2);
            Solucion pDer = DyVRec(p, medio + 1, der);
            //System.out.println("P1: " + pDer.indiceP1 + "P2: " + pDer.indiceP2);
            double distI = pIzq.dMin;
            double distD = pDer.dMin;

            // Resolución recursiva
            if (distI <= distD) {
                pDistMin = pIzq;
            } else {
                pDistMin = pDer;
            }

            // Comprobación de los puntos en el medio

            for (int a = medio + 1; a <= der; a++) {
                if (p.get(a).getX() - p.get(medio).getX() > pDistMin.dMin) {
                    break;
                }

                for (int b = medio; b >= izq; b--) {
                    if (p.get(medio + 1).getX() - p.get(b).getX() > pDistMin.dMin) {
                        break;
                    }

                    for (int c = b + 1; c <= medio; c++) {
                        for (int d = medio + 1; d <= a - 1; d++) {
                            ParDePuntos pauxPP = new ParDePuntos(p.get(c), p.get(d));
                            Solucion paux1 = new Solucion(pauxPP.distancia(), pauxPP.getP1().getIndice(), pauxPP.getP2().getIndice(), 0, 0);
                            double distPaux = paux1.dMin;
                            double distMin = pDistMin.dMin;

                            if (distPaux < distMin) {
                                pDistMin = paux1;
                            }
                        }
                    }

                    for (int c = medio + 1; c <= a - 1; c++) {
                        for (int d = b + 1; d <= medio; d++) {
                            ParDePuntos pauxPP = new ParDePuntos(p.get(c), p.get(d));
                            Solucion paux2 = new Solucion(pauxPP.distancia(), pauxPP.getP1().getIndice(), pauxPP.getP2().getIndice(), 0, 0);
                            double distPaux = paux2.dMin;
                            double distMin = pDistMin.dMin;

                            if (distPaux < distMin) {
                                pDistMin = paux2;
                            }
                        }
                    }
                }
            }
        }
        if(pDistMin.indiceP1 != -1 && pDistMin.indiceP2 != -1){
            ParDePuntos Pp = new ParDePuntos(p.get(pDistMin.indiceP1), p.get(pDistMin.indiceP2));
            if (pDistMin.dMin < dMinAnteriorTest){
                dMinAnterior = pDistMin.dMin;
                pDistMin.indiceP1 = Pp.getP1().getIndice();
                pDistMin.indiceP2 = Pp.getP2().getIndice();
                dMinfSolu = pDistMin;
            }
            else{
                pDistMin = dMinfSolu;
            }
        }
        if(pDistMin.dMin == 17.96051224213805){
            System.out.println("Ind1: " + pDistMin.indiceP1 + " Ind2: " + pDistMin.indiceP2);
        }
        return pDistMin;
    }
     */
    public Solucion2 Exhaustivo(List<Punto> p) {
        double startTime = System.nanoTime();
        nComparacionesExhaustiva = 0;

        double distanciaMinima = Double.MAX_VALUE;
        double aux = 0;
        //ParDePuntos PuntosMasCercanos;
        Punto p1 = new Punto();
        Punto p2 = new Punto();
        for (int i = 0; i < p.size(); i++) {
            for (int j = i + 1; j < p.size(); j++) {
                nComparacionesExhaustiva++;
                ParDePuntos paux = new ParDePuntos(p.get(i), p.get(j));
                aux = paux.distancia();
                if (aux < distanciaMinima) {
                    distanciaMinima = aux;
                    p1 = p.get(i);
                    p2 = p.get(j);
                }
            }
        }
        double endTime = System.nanoTime();
        double time;
        time = (endTime - startTime) / 1e6; //Pasamos a mseg

        Solucion2 s = new Solucion2(distanciaMinima, p1, p2, time, nComparacionesExhaustiva);
        tiempoBusquedaExhaustiva = time;
        tiempoDivideYVenceras = tiempoDivideYVenceras + time;
        //Tiempo busuedaExhaustiva mas DyV
        return s;
    }

    public Solucion2 ExhaustivoDyV(List<Punto> p, int izq, int der) {
        double startTime = System.nanoTime();
        //nComparacionesExhaustiva = 0;
        
        double distanciaMinima = Double.MAX_VALUE;
        double aux = 0;
        //ParDePuntos PuntosMasCercanos;
        Punto p1 = new Punto();
        Punto p2 = new Punto();
        for (int i = izq; i < der; i++) {
            for (int j = i + 1; j < der; j++) {
                //nComparacionesExhaustiva++;
                nCompDyV++;
                //System.out.println(nCompDyV);
                ParDePuntos paux = new ParDePuntos(p.get(i), p.get(j));
                aux = paux.distancia();
                if (aux < distanciaMinima) {
                    distanciaMinima = aux;
                    //System.out.println("p.get(i)" + p.get(i));
                    //System.out.println("p.get(j)" + p.get(j));
                    p1 = p.get(i);
                    p2 = p.get(j);
                }
            }
        }

        double endTime = System.nanoTime();
        double time;
        time = (endTime - startTime) / 1e6; //Pasamos a mseg
        Solucion2 s = new Solucion2(distanciaMinima, p1, p2, time, nComparacionesExhaustiva);
        return s;
    }

    public Solucion2 DyV(List<Punto> p) {
        double startTime = System.nanoTime();
        ordenarPuntosPorXQuickSort(p);
        Solucion2 s2 = DyVRec(p, 0, p.size() - 1);
        double endTime = System.nanoTime();
        s2.time = (endTime - startTime) / 1e6;
        s2.nComparaciones = nCompDyV;
        return s2;
    }

    //public double dMinDyV = 0.0;
    public int nCompDyV = 0;
    public Solucion2 DyVRec(List<Punto> p, int izq, int der) {

        Solucion2 pDistMin = new Solucion2(Double.MAX_VALUE, null, null, 0, 0);

        if (der - izq <= 2) {
            // Caso base
            //pDistMin.nComparaciones++;
            nCompDyV++;
            Solucion2 s = ExhaustivoDyV(p, izq, der);
            return s;
        } else {
            int medio = (izq + der) / 2;

            Solucion2 pIzq, pDer;
            pIzq = DyVRec(p, izq, medio);
            pDer = DyVRec(p, medio + 1, der);

            double distI = pIzq.dMin;
            double distD = pDer.dMin;
            //Puntos por la izquierda
            if (distI <= distD) {
                //pDistMin.nComparaciones += pIzq.nComparaciones;
                pDistMin.dMin = distI;
                pDistMin.p1 = pIzq.p1;
                pDistMin.p2 = pIzq.p2;
                //pDistMin.nComparaciones++;
                nCompDyV++;
                //Puntos por la derecha
            } else {
                //pDistMin.nComparaciones += pDer.nComparaciones;
                pDistMin.dMin = distD;
                pDistMin.p1 = pDer.p1;
                pDistMin.p2 = pDer.p2;
                //pDistMin.nComparaciones++;
                nCompDyV++;
            }

            // Comprobación de puntos en el medio
            List<Punto> strip = new ArrayList<>();
            ParDePuntos pp = new ParDePuntos(null, null);
            //double distancia = 0.0;
            for (int i = izq; i <= der; i++) {
                pp.setP1(p.get(i));
                pp.setP2(p.get(medio));
                
                nCompDyV++;
                
                //if (Math.abs(p.get(i).getX() - p.get(medio).getX()) < pDistMin.dMin) {
                if (pp.distancia() < pDistMin.dMin) {
                    strip.add(p.get(i));
                }
            }
            ordenarPuntosPorYQuickSort(strip);
            double distancia;
            for (int i = 0; i < strip.size(); i++) {
                for (int j = i + 1; j < strip.size() && (strip.get(j).getY() - strip.get(i).getY()) < pDistMin.dMin; j++) {
                    pp.setP1(strip.get(i));
                    pp.setP2(strip.get(j));

                    //ParDePuntos paux = new ParDePuntos(strip.get(i), strip.get(j));
                    //distancia = paux.distancia();
                    distancia = pp.distancia();
                    //pDistMin.nComparaciones++;
                    nCompDyV++;
                    if (distancia < pDistMin.dMin) {
                        pDistMin.dMin = distancia;
                        //pDistMin.p1 = paux.getP1();
                        //pDistMin.p2 = paux.getP2();
                        pDistMin.p1 = pp.getP1();
                        pDistMin.p2 = pp.getP2();
                    }
                    
                }
            }

            return pDistMin;
        }
    }

    /*
    public Solucion2 DyVMejorado(List<Punto> p) {
        long ini = System.nanoTime();
        ordenarPuntosPorXQuickSort(p);
        long fin = System.nanoTime();
        return DyVMejoradoRec(p, 0, p.size() - 1);

    }

    public Solucion2 DyVMejoradoRec(List<Punto> p, int izq, int der) {

        //*ParDePuntos pDistMin;
        Solucion2 pDistMin;
        if (der - izq <= 3) {
            //Caso Base
            return Exhaustivo(p);
        } else {
            int medio = (izq + der) / 2;
            //Divide en dos mitades

            //*ParDePuntos pIzq, pDer;
            Solucion2 pIzq, pDer;
            //llamadas recursivas
            pIzq = DyVRec(p, izq, medio);
            pDer = DyVRec(p, medio + 1, der);

            double distI, distD;
            ParDePuntos PdistI = new ParDePuntos(pIzq.p1, pIzq.p2);
            ParDePuntos PdistD = new ParDePuntos(pDer.p1, pDer.p2);
            distI = PdistI.distancia();
            distD = PdistD.distancia();

            //resolucion recursiva
            if (distI <= distD) {
                pDistMin = pIzq;

            } else {
                pDistMin = pDer;
            }

            //CONTROL FRANJA CENTRAL
            // Ordenamos punto franja media
            List<Punto> franjaMedia = new ArrayList<>();
            int a, b;
            ParDePuntos paux = null;
            for (a = medio + 1; a <= der; a++) {
                ParDePuntos paux2 = new ParDePuntos(pDistMin.p1, pDistMin.p2);
                if (p.get(a).getX() - p.get(medio).getX() > paux2.distancia()) {
                    break;
                } else {
                    franjaMedia.add(p.get(a));
                }
            }

            for (b = medio; b >= izq; b--) {
                ParDePuntos paux2 = new ParDePuntos(pDistMin.p1, pDistMin.p2);
                if (p.get(medio + 1).getX() - p.get(b).getX() > paux2.distancia()) {
                    break;
                } else {
                    franjaMedia.add(p.get(b));
                }
            }

            //ordenamos por eje Y
            ordenarPuntosPorYQuickSort(franjaMedia);

            for (int i = 0; i < franjaMedia.size(); i++) {
                for (int j = i + 1; j < franjaMedia.size() && j < i + 12; j++) {
                    ParDePuntos paux2 = new ParDePuntos(pDistMin.p1, pDistMin.p2);
                    ParDePuntos paux3 = new ParDePuntos(franjaMedia.get(i), franjaMedia.get(j));
                    double dist = paux3.distancia();
                    if (dist < paux2.distancia()) {
                        pDistMin.p1 = franjaMedia.get(i);
                        pDistMin.p2 = franjaMedia.get(j);
                    }
                }
            }

        }

        return pDistMin;

    }
     */
    public Solucion2 DyVMejorado(List<Punto> p) {
        double startTime = System.nanoTime();
        ordenarPuntosPorXQuickSort(p);
        Solucion2 s2 = DyVMejoradoRec(p, 0, p.size() - 1);
        double endTime = System.nanoTime();
        s2.time = (endTime - startTime) / 1e6;
        return s2;
    }

    private Solucion2 DyVMejoradoRec(List<Punto> p, int izq, int der) {

        Solucion2 pDistMin = new Solucion2(Double.MAX_VALUE, null, null, 0, 0);
        pDistMin.time = System.nanoTime();

        if (der - izq <= 3) {
            // Caso base
            Solucion2 s = ExhaustivoDyV(p, izq, der);
            return s;
        } else {
            int medio = (izq + der) / 2;

            Solucion2 pIzq, pDer;
            pIzq = DyVRec(p, izq, medio);
            pDer = DyVRec(p, medio + 1, der);

            double distI = pIzq.dMin;
            double distD = pDer.dMin;

            //Puntos por la izquierda
            if (distI <= distD) {
                pDistMin.nComparaciones += pIzq.nComparaciones;
                pDistMin.dMin = distI;
                pDistMin.p1 = pIzq.p1;
                pDistMin.p2 = pIzq.p2;
                //Puntos por la derecha
            } else {
                pDistMin.nComparaciones += pDer.nComparaciones;
                pDistMin.dMin = distD;
                pDistMin.p1 = pDer.p1;
                pDistMin.p2 = pDer.p2;
            }

            //Comprobamos los puntos en el medio
            List<Punto> franjaMedia = new ArrayList<>();

            for (int i = izq; i <= der; i++) {
                if (Math.abs(p.get(i).getX() - p.get(medio).getX()) < pDistMin.dMin) {
                    franjaMedia.add(p.get(i));
                }
            }
            ordenarPuntosPorYQuickSort(franjaMedia);

            for (int i = 0; i < franjaMedia.size(); i++) {
                for (int j = i + 1; j < franjaMedia.size() && j - i <= 12; j++) {
                    ParDePuntos paux = new ParDePuntos(franjaMedia.get(i), franjaMedia.get(j));
                    double distancia = paux.distancia();

                    if (distancia < pDistMin.dMin) {
                        pDistMin.dMin = distancia;
                        pDistMin.p1 = paux.getP1();
                        pDistMin.p2 = paux.getP2();
                    }
                    pDistMin.nComparaciones++;
                }
            }
            
            return pDistMin;
        }
        
    }

    /*
    public double divideYVencerasMejorado(List<Punto> puntos) {
        double startTime = System.nanoTime();
        // Si hay pocos puntos, utiliza la búsqueda exhaustiva.
        if (puntos.size() <= 3) {
            return busquedaExaustiva(puntos, 0, puntos.size());
        }

        // Divide los puntos en dos mitades.
        int mitad = puntos.size() / 2;
        List<Punto> izquierda = puntos.subList(0, mitad);
        List<Punto> derecha = puntos.subList(mitad, puntos.size());

        // Calcula las distancias mínimas en ambas mitades de manera recursiva.
        double distanciaMinIzquierda = divideYVencerasMejorado(izquierda);
        double distanciaMinDerecha = divideYVencerasMejorado(derecha);

        // Encuentra la distancia mínima en la franja intermedia.
        List<Punto> franja = new ArrayList<>();
        for (Punto punto : puntos) {
            if (Math.abs(punto.getX() - puntos.get(mitad).getX()) < distanciaMinIzquierda) {
                franja.add(punto);
            }
        }
        Collections.sort(franja, Comparator.comparing(Punto::getY));

        double distanciaMinFranja = Double.POSITIVE_INFINITY;
        for (int i = 0; i < franja.size(); i++) {
            for (int j = i + 1; j < franja.size() && j - i <= 11; j++) {
                ParDePuntos dosPuntos = new ParDePuntos(franja.get(i), franja.get(j));
                double distancia = dosPuntos.distancia();
                distanciaMinFranja = Math.min(distanciaMinFranja, distancia);
            }
        }

        // Encuentra la distancia mínima final.
        double distanciaMin = Math.min(distanciaMinIzquierda, distanciaMinDerecha);
        distanciaMin = Math.min(distanciaMin, distanciaMinFranja);

        double endTime = System.nanoTime();
        tiempoEncontrarPuntosMasCercanos = (endTime - startTime) / 1e6;
        return distanciaMin;
    }
     */
 /*
 *     public Solucion busquedaExhaustivaSucia(List<Punto> punto, int izquierda, int derecha) {
        double startTime = System.nanoTime();

        double distancia = -1;
        double distanciaMin = Double.POSITIVE_INFINITY;

        Solucion S = new Solucion(distanciaMin, -1, -1);

        for (int i = izquierda; i < derecha; i++) {
            for (int j = i + 1; j < derecha; j++) {
                ParDePuntos dosPuntos = new ParDePuntos(punto.get(i), punto.get(j));
                distancia = dosPuntos.distancia();
                nComparaciones++;
                nComparacionesDyV++;
                if (distancia < S.dMin) {
                    S.dMin = distancia;
                    S.indiceP1 = i;
                    S.indiceP2 = j;
                }
            }
        }
        double endTime = System.nanoTime();
        tiempoBusquedaExhaustiva = (endTime - startTime) / 1e6; //Pasamos a mseg
        return S;
    }
     */
//Creada para buscar el punto por su indice en la tabla original sin ordenar
    public Punto getPuntoPorIndice(int ind) {
        Punto puntoIndice = null;
        for (int i = 0; i < puntos.size(); i++) {
            if (puntos.get(i).getIndice() == ind) {
                puntoIndice = puntos.get(i);
            }
        }
        return puntoIndice;
    }
    //-----------------------Controles para el Frame ---------------------//

    //Formatear a 8 decimales
    public double decimales8(double numero) {
        //long decimales = 10000000000;
        numero = Math.round(numero * 100000000) / 100000000d;
        return numero;
    }

    //Formatear a 4 decimales
    public double decimales4(double numero) {
        //long decimales = 10000000000;
        numero = Math.round(numero * 10000) / 10000d;
        return numero;
    }
private static void ordenarPuntosPorYQuickSort(List<Punto> puntos) {
        quicksort(puntos, 0, puntos.size() - 1, Comparator.comparingDouble(Punto::getY));
    }

    private static void ordenarPuntosPorXQuickSort(List<Punto> puntos) {
        quicksort(puntos, 0, puntos.size() - 1, Comparator.comparingDouble(Punto::getX));
    }

    private static void quicksort(List<Punto> puntos, int izq, int der, Comparator<Punto> comparador) {
        if (izq < der) {
            int particion = particion(puntos, izq, der, comparador);
            quicksort(puntos, izq, particion - 1, comparador);
            quicksort(puntos, particion + 1, der, comparador);
        }
    }

    private static int particion(List<Punto> puntos, int izq, int der, Comparator<Punto> comparador) {
        Punto pivote = puntos.get(izq);
        int i = izq;
        int j = der;

        while (i < j) {
            while (comparador.compare(puntos.get(i), pivote) <= 0 && i < j) {
                i++;
            }
            while (comparador.compare(puntos.get(j), pivote) > 0) {
                j--;
            }
            if (i < j) {
                intercambiar(puntos, i, j);
            }
        }
        intercambiar(puntos, izq, j);
        return j;
    }

    private static void intercambiar(List<Punto> puntos, int i, int j) {
        Punto temp = puntos.get(i);
        puntos.set(i, puntos.get(j));
        puntos.set(j, temp);
    }

}
