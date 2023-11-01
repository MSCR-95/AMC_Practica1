/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    private int nComparacionesDyV, nComparaciones = 0;
    private int indiceP1, indiceP2 = -1;

    public int getIndiceP1() {
        return indiceP1;
    }

    public int getIndiceP2() {
        return indiceP2;
    }

    public int getNComparaciones() {
        return nComparaciones;
    }

    public int getNComparacionesDyV() {
        return nComparacionesDyV;
    }

    //AÑADIDO IZQUIERDA Y DERECHA PARA USAR ESTE ALGORITMO EN EL DIVIDE Y VENCERAS
    public double busquedaExaustiva(List<Punto> punto, int izquierda, int derecha) {

        double distancia = -1;
        double distanciaMin = Double.POSITIVE_INFINITY;

        for (int i = izquierda; i < derecha; i++) {
            for (int j = i + 1; j < derecha; j++) {
                //Controlamos que un punto no calcule la distancia con el mismo
                ParDePuntos dosPuntos = new ParDePuntos(punto.get(i), punto.get(j));
                distancia = dosPuntos.distancia();
                nComparaciones++;
                nComparacionesDyV++;
                //System.out.println("Entra en exahustivo");
                //FILTRAMOS
                if (distancia < distanciaMin) {
                    distanciaMin = distancia;

                    //indiceP1 = i;
                    //indiceP2 = j;

                    indiceP1 = dosPuntos.getP1().getIndice()-1;
                    indiceP2 = dosPuntos.getP2().getIndice()-1;
                    //indiceP1 = punto.get(i).getIndice();
                    //indiceP1 = punto.get(j).getIndice();
                    //System.out.println("x: " + indiceP1 + " P1: " + dosPuntos.getP1() + "y: " + indiceP2 + " P2: " + dosPuntos.getP2());
                }
                //AQUI NOS MUESTRA TODOS LOS PUNTOS Y TODAS SUS DISTANCIAS
                //System.out.println("Punto " + i + ": " + punto.get(i) + "Punto " + j + ": " + punto.get(j) + "Distancia: " + distancia + "\n");
            }
        }
        //Mostramos los puntos, la distancia minima entre ellos y el numero de comparaciones realizadas
        //System.out.println("X: " + x + "Y: " + y + "Distancia: " + df.format(distanciaMin));
        //System.out.println("Numero de comparaciones: " + nComparaciones);
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
        double distancia = -1;
        double distanciaMin = Double.POSITIVE_INFINITY;
        double distanciaX = 0;
        Punto x = null;
        Punto y = null;
        int nComparaciones = 0;
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
                    nComparaciones++;
                    ParDePuntos dosPuntos = new ParDePuntos(punto.get(i), punto.get(j));
                    distancia = dosPuntos.distancia();
                    if (distancia < distanciaMin) {
                        x = punto.get(i);
                        y = punto.get(j);
                        distanciaMin = distancia;
                    }
                }
                //System.out.println("Punto " + i + ": " + punto.get(i) + "Punto " + j + ": " + punto.get(j) + "Distancia: " + distancia + "\n");
                j++;
            }
        }
        System.out.println("entra en la poda");
        //Mostramos los puntos, la distancia minima entre ellos y el numero de comparaciones realizadas
        System.out.println("X: " + x + "Y: " + y + "Distancia: " + df.format(distanciaMin));
        System.out.println("Numero de comparaciones: " + nComparaciones);
        return distanciaMin;
    }

    /*
    public double busquedaConDyV(List<Punto> punto) {
        double distancia = -1;
        double distanciaMin = Double.POSITIVE_INFINITY;
        Punto x = null;
        Punto y = null;
        int nComparaciones = 0;
        int mitad = punto.size() / 2;
        int j = 0;
        List<Punto> PuntosMitad1= new ArrayList<>();
        List<Punto> PuntosMitad2= new ArrayList<>();

        ordenarPuntosPorXQuickSort(punto);
        
        for (int i = 0; i < punto.size(); i++) {
            if(i<mitad){
                PuntosMitad1.add(punto.get(i));
                System.out.println("mitad 1:");
                    System.out.println("Punto " + (i+1) + ": " + String.format("%n") + "X: " + PuntosMitad1.get(i).getX() + " Y: " + PuntosMitad1.get(i).getY());
            }
            else{
                PuntosMitad2.add(punto.get(i));
                System.out.println("mitad 2:");
                    System.out.println("Punto " + (i+1) + ": " + String.format("%n") + "X: " + PuntosMitad2.get(j).getX() + " Y: " + PuntosMitad2.get(j).getY());
                    j++;
            }
        }
       
        return distanciaMin;
    }
     */
    /**
     * *******************************************
     */
    //CALCULA BIEN LA DISTANCIA
    //HAY QUE COMPROBRAR SI ESTAMOS SUMANDO BIEN EL NUMERO DE COMPARACIONES
    //ENCONTRAR LA MANERA DE QUE MUESTRE LOS PUNTOS *Estaba accediendo al indx de punto, cuando despues la lista punto cambia sun indx al ordenarla
    public double busquedaDivideYVenceras(List<Punto> punto) {
        //La copiamos para tener la lista original sin ordenar
        puntos = punto;
        //System.out.println(punto);
        //Primero, ordena la lista de puntos por la coordenada x.
        ordenarPuntosPorXQuickSort(punto);
        //System.out.println(punto);

        nComparacionesDyV = 0;
        indiceP1 = -1;
        indiceP2 = -1;

        return buscarPuntosMasCercanos(punto, 0, punto.size() - 1);
    }

    private double buscarPuntosMasCercanos(List<Punto> punto, int izquierda, int derecha) {
        Punto x = null;
        Punto y = null;

        if (derecha - izquierda <= 2) {

            nComparacionesDyV++;
            //Cuando hay pocos puntos, realiza una búsqueda exhaustiva.
            return busquedaExaustiva(punto, izquierda, derecha);
        }
        //Calculamos la mitad
        int mitad = (izquierda + derecha) / 2;
        Punto puntoMitad = punto.get(mitad);

        //Calcula la distancia minima por la izquierda
        double distanciaIzquierda = buscarPuntosMasCercanos(punto, izquierda, mitad);
        System.out.println("distancia Izquierda: "+ distanciaIzquierda);
        //calcula la distancia minima por la derecha
        double distanciaDerecha = buscarPuntosMasCercanos(punto, mitad + 1, derecha);
        System.out.println("distancia Derecha: "+ distanciaDerecha);
        //Nos quedamos con la distancia mas pequeña de las dos
        double distanciaMinima = Math.min(distanciaIzquierda, distanciaDerecha);

        // Encuentra puntos dentro de la franja central.
        List<Punto> franja = new ArrayList<>();
        for (int i = izquierda; i <= derecha; i++) {

            if (Math.abs(punto.get(i).getX() - puntoMitad.getX()) < distanciaMinima) {

                nComparacionesDyV++;
                franja.add(punto.get(i));
            }
        }

        // Ordena los puntos de la franja por coordenada y.
        ordenarPuntosPorYQuickSort(franja);
        // Comprueba si hay puntos más cercanos en la franja.
        for (int i = 0; i < franja.size(); i++) {
            for (int j = i + 1; j < franja.size() && franja.get(j).getY() - franja.get(i).getY() < distanciaMinima; j++) {
                ParDePuntos dosPuntos = new ParDePuntos(franja.get(i), franja.get(j));
                double distancia = dosPuntos.distancia();

                if (distancia < distanciaMinima) {
                    distanciaMinima = distancia;
                    nComparacionesDyV++;
                    //Px = punto.get(i);
                    //Py = punto.get(j);

                    System.out.println("x: " + franja.get(i) + "y: " + franja.get(j));
                    //indiceP1 = punto.indexOf(franja.get(i));
                    //indiceP2 = punto.indexOf(franja.get(j));
                    x = punto.get(i);
                    y = punto.get(j);

                    indiceP1 = franja.get(i).getIndice();
                    indiceP1 = franja.get(j).getIndice();
                    //System.out.println("" + dosPuntos.getP1() + dosPuntos.getP2());

                    //System.out.println("indicePX: " +indicePx+" -> " + punto.get(indicePx));
                    //System.out.println("indicePY: " +indicePy+" -> " + punto.get(indicePy));
                    //System.out.println("x: " + indicePx + " y: " + indicePy);
                }
            }
        }
        //System.out.println("Indice p1:" + indiceP1 + " indice p2: " + indiceP2);
        //System.out.println("X: " + x + "Y: " + y + "Distancia: " + df.format(distanciaMinima));
        //System.out.println("Numero de comparaciones: " + nComparaciones);
        return distanciaMinima;
    }

    /*
    public double buscarPuntosMasCercanos(List<Punto> punto) {
        ordenarPuntosPorXQuickSort(punto);
        indicePx = -1;
        indicePy = -1;
        //private int indicePx, indicePy = -1;
        double distanciaMinima = dyv(punto, 0, punto.size() - 1, indicePx, indicePy);
        return distanciaMinima;
    }

    private double dyv(List<Punto> punto, int izquierda, int derecha, int indicePx, int indicePy) {

        if (derecha - izquierda + 1 >= 4) {
            int mitad = (izquierda + derecha) / 2;
            int puntoXIzquierda = -1;
            int puntoYIzquierda = -1;
            int puntoXDerecha = -1;
            int puntoYDerecha = -1;

            double distanciaIzquierda = dyv(punto, izquierda, mitad, puntoXIzquierda, puntoYIzquierda);
            double distanciaDerecha = dyv(punto, mitad + 1, derecha, puntoXDerecha, puntoYDerecha);

            double distanciaMinima = Math.min(distanciaIzquierda, distanciaDerecha);

            if (distanciaMinima == distanciaIzquierda) {
                indicePx = puntoXIzquierda;
                indicePy = puntoYIzquierda;
            } else {
                indicePx = puntoXDerecha;
                indicePy = puntoYDerecha;
            }

            int a1 = mitad;
            boolean fin = false;
            while (a1 >= izquierda && !fin) {
                if ((punto.get(mitad + 1).getX() - punto.get(a1).getX()) > distanciaMinima) {
                    fin = true;
                } else {
                    a1--;
                }
            }

            int a2 = mitad + 1;
            fin = false;
            while (a2 <= derecha && !fin) {
                if ((punto.get(a2).getX() - punto.get(mitad).getX()) > distanciaMinima) {
                    fin = true;
                } else {
                    a2++;
                }
            }

            for (int a3 = a1 + 1; a3 <= mitad; a3++) {
                for (int a4 = mitad + 1; a4 <= a2 - 1; a4++) {
                    ParDePuntos dosPuntos = new ParDePuntos(punto.get(a3), punto.get(a4));
                    double distancia = dosPuntos.distancia();

                    if (distancia < distanciaMinima) {
                        distanciaMinima = distancia;
                        indicePx = a3;
                        indicePy = a4;
                    }
                }
            }
            System.out.println("Punto x:" + indicePx + " Punto y: " + indicePy);
            return distanciaMinima;
        } else {
            indicePx = izquierda;
            indicePy = izquierda + 1;

            System.out.println("Punto X: " + indicePx + " Punto Y: " + indicePy);
            return busquedaExaustiva(punto, izquierda, derecha);
        }
    }
     */
    public Punto getPuntoPorIndice(int ind, List<Punto> punto) {
        Punto puntoIndice = null;
        for (int i = 0; i < punto.size(); i++) {
            if (punto.get(i).getIndice() == ind) {
                puntoIndice = punto.get(i);
            }
        }
        return puntoIndice;
    }
}
