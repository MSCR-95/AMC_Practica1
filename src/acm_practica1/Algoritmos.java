package acm_practica1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javax.sound.midi.SoundbankResource;

/**
 *
 * @author Maria S
 */
public class Algoritmos {

    public List<Punto> puntos = new ArrayList<>();
    private DecimalFormat df = new DecimalFormat("#.########");

    /*
    public Algoritmos(List<Punto> Listapuntos) {
        this.puntos = Listapuntos;
    }
     */
    public Algoritmos() {
    }

    public void setLista(List<Punto> Listapuntos) {
        this.puntos = Listapuntos;
    }

    public List<Punto> getListaPuntos() {
        return puntos;
    }

    public Solucion busquedaExhaustiva(List<Punto> punto) {

        double startTime = System.nanoTime();
        Solucion S = new Solucion(0.0, null, null, 0, 0);

        double distancia = -1;
        S.dMin = Double.POSITIVE_INFINITY;
        //nComparacionesExhaustiva = 0;
        for (int i = 0; i < punto.size(); i++) {
            for (int j = i + 1; j < punto.size(); j++) {
                //Controlamos que un punto no calcule la distancia con el mismo
                ParDePuntos dosPuntos = new ParDePuntos(punto.get(i), punto.get(j));
                distancia = dosPuntos.distancia();
                S.nComparaciones++;
                //FILTRAMOS
                if (distancia < S.dMin) {
                    S.dMin = distancia;
                    S.p1 = dosPuntos.getP1();
                    S.p2 = dosPuntos.getP2();
                }
            }
        }
        double endTime = System.nanoTime();
        double time = (endTime - startTime) / 1e6; //Pasamos a mseg
        S.time = time;
        setLista(punto);
        return S;
    }

    public Solucion busquedaConPoda(List<Punto> punto) {
        double startTime = System.nanoTime();

        double distancia = -1;
        double distanciaX = 0;

        //nComparacionesPoda = 0;
        //nComparaciones = 0;
        Solucion S = new Solucion();
        S.dMin = Double.POSITIVE_INFINITY;
        //int nComparaciones = 0;
        //Ordenamos la lista por la x
        List<Punto> puntoOrdenado = ordenarPuntosPorXQuickSort(punto);

        for (int i = 0; i < puntoOrdenado.size(); i++) {
            int j = i + 1;
            Boolean alarma = false;
            while (j < puntoOrdenado.size() && !alarma) {
                distanciaX = Math.abs(puntoOrdenado.get(j).getX() - puntoOrdenado.get(i).getX());
                // Poda: no es necesario seguir con puntos más lejos en la coordenada x.
                if (distanciaX >= S.dMin) {
                    alarma = true;
                }
                if (!alarma) {
                    S.nComparaciones++;
                    //nComparacionesPoda++;
                    ParDePuntos dosPuntos = new ParDePuntos(puntoOrdenado.get(i), puntoOrdenado.get(j));
                    distancia = dosPuntos.distancia();
                    if (distancia < S.dMin) {
                        S.dMin = distancia;
                        S.p1 = dosPuntos.getP1();
                        S.p2 = dosPuntos.getP2();
                        //S.indiceP1 = dosPuntos.getP1().getIndice();
                        //S.indiceP2 = dosPuntos.getP2().getIndice();
                    }
                }
                j++;
            }
        }

        double endTime = System.nanoTime();
        S.time = (endTime - startTime) / 1e6;
        //tiempoBusquedaConPoda = S.time;
        setLista(puntoOrdenado);
        return S;
    }

    /*
    public Solucion Exhaustivo(List<Punto> p) {
        double startTime = System.nanoTime();
        //nComparacionesExhaustiva = 0;

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

        Solucion s = new Solucion(distanciaMinima, p1, p2, time, nComparacionesExhaustiva);
        return s;
    }
     */
    public Solucion ExhaustivoDyV(List<Punto> p, int izq, int der) {

        double startTime = System.nanoTime();
        int nComparacionesExhaustiva = 0;
        double distanciaMinima = Double.MAX_VALUE;
        double aux = 0;
        Punto p1 = new Punto();
        Punto p2 = new Punto();
        for (int i = izq; i < der; i++) {
            for (int j = i + 1; j < der; j++) {
                nComparacionesExhaustiva++;
                nCompDyV++;
                nCompDyVMejor++;
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
        Solucion s = new Solucion(distanciaMinima, p1, p2, time, nComparacionesExhaustiva);
        return s;
    }

    public Solucion DyV(List<Punto> p) {
        double startTime = System.nanoTime();
        List<Punto> pOrdnados = ordenarPuntosPorYQuickSort(p);
        Solucion s2 = DyVMejoradoRec(pOrdnados, 0, p.size() - 1);
        double endTime = System.nanoTime();
        s2.time = (endTime - startTime) / 1e6;
        s2.nComparaciones = nCompDyV;
        nCompDyV = 0;
        setLista(pOrdnados);
        return s2;
    }

    private int nCompDyV = 0;

    public Solucion DyVRec(List<Punto> p, int izq, int der) {

        Solucion pDistMin = new Solucion(Double.MAX_VALUE, null, null, 0, 0);
        pDistMin.time = System.nanoTime();

        if (der - izq <= 3) {
            // Caso base
            Solucion s = ExhaustivoDyV(p, izq, der);
            return s;
        } else {
            int medio = (izq + der) / 2;

            Solucion pIzq, pDer;
            pIzq = DyVRec(p, izq, medio);
            pDer = DyVRec(p, medio + 1, der);

            double distI = pIzq.dMin;
            double distD = pDer.dMin;
            //resolucion recursiva
            if (distI <= distD) {
                pDistMin = pIzq;

                //Si las distancias son iguales, tambien consideramos pDer
                if (distI == distD) {
                    Solucion pDistMinDer = new Solucion(distD, pDer.p1, pDer.p2, 0, 0);
                    pDistMin = pDistMinDer;
                }
                nCompDyV++;
            } else {
                pDistMin = pDer;
                // Si las distancias son iguales, tambien consideramos pIzq
                if (distI == distD) {
                    Solucion pDistMinIzq = new Solucion(distI, pIzq.p1, pIzq.p2, 0, 0);
                    pDistMin = pDistMinIzq;
                }
                nCompDyV++;
            }

            //Busqueda exhaustiva en la franja cercana a la línea vertical divisoria
            List<Punto> strip = new ArrayList<>();
            for (int i = izq; i <= der; i++) {
                if (Math.abs(p.get(i).getX() - p.get(medio).getX()) < pDistMin.dMin) {
                    strip.add(p.get(i));
                }
            }

            for (int i = 0; i < strip.size(); i++) {
                for (int j = i + 1; j < strip.size() && (strip.get(j).getY() - strip.get(i).getY()) < pDistMin.dMin; j++) {
                    ParDePuntos paux = new ParDePuntos(strip.get(i), strip.get(j));
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

    public Solucion DyVMejorado(List<Punto> p) {
        double startTime = System.nanoTime();
        //ordenarPuntosPorXQuickSort(p);
        List<Punto> pOrdenados = ordenarPuntosPorYQuickSort(p);
        Solucion s2 = DyVRec(pOrdenados, 0, p.size() - 1);
        double endTime = System.nanoTime();
        s2.time = (endTime - startTime) / 1e6;
        s2.nComparaciones = nCompDyVMejor;
        nCompDyVMejor = 0;
        setLista(pOrdenados);
        return s2;
    }

    private int nCompDyVMejor = 0;

    public Solucion DyVMejoradoRec(List<Punto> p, int izq, int der) {
        Solucion pDistMin = new Solucion(0, null, null, 0, 0);
        pDistMin.time = System.nanoTime();

        if (der - izq <= 5) {
            Solucion s = ExhaustivoDyV(p, izq, der);
            return s;
        } else {
            int medio = (izq + der) / 2;

            Solucion pIzq = DyVMejoradoRec(p, izq, medio);
            Solucion pDer = DyVMejoradoRec(p, medio + 1, der);

            double distI = new ParDePuntos(pIzq.p1, pIzq.p2).distancia();
            double distD = new ParDePuntos(pDer.p1, pDer.p2).distancia();

            //resolucion recursiva
            if (distI <= distD) {
                pDistMin = pIzq;

                //Si las distancias son iguales, tambien consideramos pDer
                if (distI == distD) {
                    Solucion pDistMinDer = new Solucion(distD, pDer.p1, pDer.p2, 0, 0);
                    pDistMin = pDistMinDer;
                }
                nCompDyV++;
            } else {
                pDistMin = pDer;
                // Si las distancias son iguales, tambien consideramos pIzq
                if (distI == distD) {
                    Solucion pDistMinIzq = new Solucion(distI, pIzq.p1, pIzq.p2, 0, 0);
                    pDistMin = pDistMinIzq;
                }
                nCompDyV++;
            }

            List<Punto> strip = new ArrayList<>();
            for (int i = izq; i <= der; i++) {
                if (Math.abs(p.get(i).getX() - p.get(medio).getX()) < pDistMin.dMin) {
                    strip.add(p.get(i));
                }
            }

            List<Punto> stripOrdenado = ordenarPuntosPorYQuickSort(strip);
            for (int i = 0; i < stripOrdenado.size(); i++) {
                for (int j = i + 1; j < stripOrdenado.size() && j - i <= 12; j++) {
                    ParDePuntos paux = new ParDePuntos(stripOrdenado.get(i), stripOrdenado.get(j));
                    double distancia = paux.distancia();

                    if (distancia < pDistMin.dMin) {
                        pDistMin.dMin = distancia;
                        pDistMin.p1 = paux.getP1();
                        pDistMin.p2 = paux.getP2();
                    }
                    pDistMin.nComparaciones++;
                }
            }
        }

        return pDistMin;

    }

    //Creada para buscar el punto por su indice en la tabla original sin ordenar
    //Para pruebas
    public Punto getPuntoPorIndice(int ind) {
        Punto puntoIndice = null;
        for (int i = 0; i < puntos.size(); i++) {
            if (puntos.get(i).getIndice() == ind) {
                puntoIndice = puntos.get(i);
            }
        }
        return puntoIndice;
    }

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

    private static List<Punto> ordenarPuntosPorYQuickSort(List<Punto> puntos) {
        quicksort(puntos, 0, puntos.size() - 1, Comparator.comparingDouble(Punto::getY));
        return puntos;
    }

    private static List<Punto> ordenarPuntosPorXQuickSort(List<Punto> puntos) {
        quicksort(puntos, 0, puntos.size() - 1, Comparator.comparingDouble(Punto::getX));
        return puntos;

    }

    private static List<Punto> quicksort(List<Punto> puntos, int izq, int der, Comparator<Punto> comparador) {
        if (izq < der) {
            int particion = particion(puntos, izq, der, comparador);
            quicksort(puntos, izq, particion - 1, comparador);
            quicksort(puntos, particion + 1, der, comparador);
        }
        return puntos;
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
