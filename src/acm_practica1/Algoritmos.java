package acm_practica1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Maria S
 */
public class Algoritmos {

    public List<Punto> puntos = new ArrayList<>();
    private DecimalFormat df = new DecimalFormat("#.########");

    private int nComparacionesDyV, nComparaciones, nComparacionesPoda = 0;
    private int indiceP1, indiceP2 = -1;

    private double tiempoBusquedaExhaustiva = 0.0;
    private double tiempoBusquedaConPoda = 0.0;
    private double tiempoDivideYVenceras = 0.0;
    private double tiempoEncontrarPuntosMasCercanos = 0.0;
    private double tiempoDivideYVencerasMejorado = 0.0;
    private double dMinAnterior = 10000.0;

    
    //Guardamos una copia de la lista original
    public Algoritmos(List<Punto> Listapuntos) {
        this.puntos = Listapuntos;
    }

    public int getIndiceP1() {
        return indiceP1;
    }

    public int getIndiceP2() {
        return indiceP2;
    }

    public int getNComparaciones() {
        return nComparaciones;
    }
    
    public int getNComparacionesPoda() {
        return nComparacionesPoda;
    }

    public int getNComparacionesDyV() {
        return nComparacionesDyV;
    }

    public double getTiempoBusquedaExhaustiva() {
        return tiempoBusquedaExhaustiva;
    }

    public double getTiempoBusquedaConPoda() {
        return tiempoBusquedaConPoda;
    }

    public double getTiempoDivideYVenceras() {
        return tiempoDivideYVenceras;
    }

    public double getTiempoDivideYVencerasMejorado() {
        return tiempoDivideYVencerasMejorado;
    }

    public double getTiempoEncontrarPuntosMasCercanos() {
        return tiempoEncontrarPuntosMasCercanos;
    }

    //AÑADIDO IZQUIERDA Y DERECHA PARA USAR ESTE ALGORITMO EN EL DIVIDE Y VENCERAS
    public double busquedaExaustiva(List<Punto> punto, int izquierda, int derecha) {
        double startTime = System.nanoTime();

        double distancia = -1;
        double distanciaMin = Double.POSITIVE_INFINITY;

        for (int i = izquierda; i < derecha; i++) {
            for (int j = i + 1; j < derecha; j++) {
                //Controlamos que un punto no calcule la distancia con el mismo
                ParDePuntos dosPuntos = new ParDePuntos(punto.get(i), punto.get(j));
                distancia = dosPuntos.distancia();
                nComparaciones++;
                nComparacionesDyV++;
                //FILTRAMOS
                if (distancia < distanciaMin) {
                    distanciaMin = distancia;
                    indiceP1 = dosPuntos.getP1().getIndice() - 1;
                    indiceP2 = dosPuntos.getP2().getIndice() - 1;
                }
                //AQUI NOS MUESTRA TODOS LOS PUNTOS Y TODAS SUS DISTANCIAS
            }
        }
        //Mostramos los puntos, la distancia minima entre ellos y el numero de comparaciones realizadas
        double endTime = System.nanoTime();
        tiempoBusquedaExhaustiva = (endTime - startTime) / 1e6; //Pasamos a mseg
        return distanciaMin;
    }



    //No sirve, tenemos que implementar quickSort
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
        //Ordenar ArrayList de punto por X en orden ascendente usando un comparador personalizado
        Collections.sort(punto, new Comparator<Punto>() {
            @Override
            public int compare(Punto p1, Punto p2) {
                return Double.compare(p1.getY(), p2.getY());
            }
        });
    }

    public double busquedaConPoda(List<Punto> punto) {
        double startTime = System.nanoTime();

        double distancia = -1;
        double distanciaMin = Double.POSITIVE_INFINITY;
        double distanciaX = 0;
        Punto x = null;
        Punto y = null;
        //int nComparaciones = 0;
        //Ordenamos la lista por la x
        ordenarPuntosPorXQuickSort(punto);

        for (int i = 0; i < punto.size(); i++) {
            int j = i + 1;
            Boolean alarma = false;
            while (j < punto.size() && !alarma) {
                distanciaX = Math.abs(punto.get(j).getX() - punto.get(i).getX());
                // Poda: no es necesario seguir con puntos más lejos en la coordenada x.
                if (distanciaX >= distanciaMin) {
                    alarma = true;
                }
                if (!alarma) {
                    nComparacionesPoda++;
                    ParDePuntos dosPuntos = new ParDePuntos(punto.get(i), punto.get(j));
                    distancia = dosPuntos.distancia();
                    if (distancia < distanciaMin) {
                        distanciaMin = distancia;
                        //x = i;
                        //y = j;
                        indiceP1 = dosPuntos.getP1().getIndice();
                        indiceP2 = dosPuntos.getP2().getIndice();
                    }
                }
                j++;
            }
        }

        double endTime = System.nanoTime();
        tiempoBusquedaConPoda = (endTime - startTime) / 1e6;
        return distanciaMin;
    }

    public class Solucion {
        public double dMin;
        public int indiceP1; 
        public int indiceP2;

        public Solucion (double dMin, int indiceP1, int indiceP2){
            this.dMin = dMin;
            this.indiceP1 = indiceP1;
            this.indiceP2 = indiceP2;
        }
    }

    public Solucion busquedaExhaustivaSucia(List<Punto> punto, int izquierda, int derecha) {
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

    public double busquedaDivideYVenceras(List<Punto> punto) {

        double startTime = System.nanoTime();

        //Primero, ordena la lista de puntos por la coordenada x.
        ordenarPuntosPorXQuickSort(punto);

        nComparacionesDyV = 0;
        indiceP1 = -1;
        indiceP2 = -1;

        double endTime = System.nanoTime();
        tiempoDivideYVenceras = tiempoEncontrarPuntosMasCercanos;

        Solucion tal = buscarPuntosMasCercanos(punto, 0, punto.size() - 1);
        indiceP1 = tal.indiceP1;
        indiceP2 = tal.indiceP2;
        return tal.dMin;
    }

    private Solucion buscarPuntosMasCercanos(List<Punto> punto, int izquierda, int derecha) {
        double startTime = System.nanoTime();

        if (derecha - izquierda <= 2) {            
            //Cuando hay pocos puntos, realiza una búsqueda exhaustiva.
            Solucion loq = busquedaExhaustivaSucia(punto, izquierda, derecha);
            return loq;
        }
        //Calculamos la mitad
        int mitad = (izquierda + derecha) / 2;
        Punto puntoMitad = punto.get(mitad);

        //Calcula la distancia minima por la izquierda
        Solucion distanciaIzquierda = buscarPuntosMasCercanos(punto, izquierda, mitad);
        //calcula la distancia minima por la derecha
        Solucion distanciaDerecha = buscarPuntosMasCercanos(punto, mitad + 1, derecha);
        //Nos quedamos con la distancia mas pequeña de las dos
        double distanciaMinima = Math.min(distanciaIzquierda.dMin, distanciaDerecha.dMin);

        Punto puntoMitadIzq = puntoMitad;
        Punto puntoMitadDer = punto.get(mitad+1);
        double MediaPuntos = (puntoMitadIzq.getX() + puntoMitadDer.getX())/2;
        double franjaIzq = MediaPuntos - distanciaMinima;
        double franjaDer = MediaPuntos + distanciaMinima;
        List<Punto> franja = new ArrayList<>();
        
        int aux1 = -1;
        int aux2 = -1;
        for (int i = izquierda; i < derecha+1; i++) {
            if (punto.get(i).getX() >= franjaIzq && punto.get(i).getX() <= franjaDer){
                franja.add(punto.get(i));
                if(aux1 == -1){
                    aux1 = i;
                }
                aux2 = i;
            }
        }

        Solucion franjaSol = busquedaExhaustivaSucia (punto, aux1, aux2);
        Solucion Legit;
        if(distanciaIzquierda.dMin < distanciaDerecha.dMin && distanciaIzquierda.dMin < franjaSol.dMin){
            Legit = distanciaIzquierda;
        }
        else if (distanciaDerecha.dMin < distanciaIzquierda.dMin && distanciaDerecha.dMin < franjaSol.dMin){
            Legit = distanciaDerecha;
        }
        else if (franjaSol.dMin < distanciaIzquierda.dMin && franjaSol.dMin < distanciaDerecha.dMin){
            Legit = franjaSol;
        }
        else{
            Legit = distanciaIzquierda;
        }

        if(Legit.indiceP1 != -1 && Legit.indiceP2 != -1){
             ParDePuntos Pp = new ParDePuntos(punto.get(Legit.indiceP1), punto.get(Legit.indiceP2));
                if (Legit.dMin < dMinAnterior){
                    dMinAnterior = Legit.dMin;
                    Legit.indiceP1 = Pp.getP1().getIndice();
                    Legit.indiceP2 = Pp.getP2().getIndice();
            }
        }
        
        double endTime = System.nanoTime();
        tiempoEncontrarPuntosMasCercanos = (endTime - startTime) / 1e6;
        return Legit;
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
}
