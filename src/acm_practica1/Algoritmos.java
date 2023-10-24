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

    public double busquedaExaustiva(List<Punto> punto) {

        double distancia = -1;
        double distanciaMin = Double.POSITIVE_INFINITY;
        Punto x = null;
        Punto y = null;
        int nComparaciones = 0;

        for (int i = 0; i < punto.size(); i++) {
            for (int j = 0; j < punto.size(); j++) {
                //Controlamos que un punto no calcule la distancia con el mismo
                if (punto.get(i) != punto.get(j)) {
                    ParDePuntos dosPuntos = new ParDePuntos(punto.get(i), punto.get(j));
                    distancia = dosPuntos.distancia();
                    //FILTRAMOS
                    if (distancia < distanciaMin) {
                        nComparaciones++;
                        distanciaMin = distancia;
                        x = punto.get(i);
                        y = punto.get(j);
                        //System.out.println("Punto " + i + ": " + punto.get(i) + "Punto " + j + ": " + punto.get(j) + "Distancia: " + distanciaMin + "\n");;
                    }
                    //AQUI NOS MUESTRA TODOS LOS PUNTOS Y TODAS SUS DISTANCIAS
                    //System.out.println("Punto " + i + ": " + punto.get(i) + "Punto " + j + ": " + punto.get(j) + "Distancia: " + distancia + "\n");
                }
            }
        }
        //Mostramos los puntos, la distancia minima entre ellos y el numero de comparaciones realizadas
        System.out.println("X: " + x + "Y: " + y + "Distancia: " + df.format(distanciaMin));
        System.out.println("Numero de comparaciones: " + nComparaciones);
        return distanciaMin;
    }

    /* NO SIRVE PORQUE ES UN N^2
    public void ordenarPuntosPorX(List<Punto> punto) {
        //punto.sort(c);
        for (int j = 1; j < punto.size(); j++) {
            Punto x = punto.get(j);
            int i = j - 1;
            while ((i > -1) && (punto.get(i).getX() > x.getX())) {
                punto.set(i + 1, punto.get(i));
                i--;
            }
            punto.set(i + 1, x);
        }
    }
     */
    private void ordenarPuntosPorXQuickSort(List<Punto> punto) {

        //Comprobamos la lista antes de ordenar
        /*
        System.out.println("LISTA SIN ORDENAR");
        for (int i = 0; i < punto.size(); i++) {
            System.out.println(i + 1 + " " + punto.get(i));
        }
         */
        //Ordenar ArrayList de punto por X en orden ascendente usando un comparador personalizado
        Collections.sort(punto, new Comparator<Punto>() {
            @Override
            public int compare(Punto p1, Punto p2) {
                return Double.compare(p1.getX(), p2.getX());
            }
        });
        //Mostramos la lista despues de ordenar
        /*
        System.out.println("LISTA ORDENADA POR X");
        for (int i = 0; i < punto.size(); i++) {
            System.out.println(i + 1 + " " + punto.get(i));
        }
         */
    }

    public double busquedaConPoda(List<Punto> punto) {

        double distancia = -1;
        double distanciaMin = Double.POSITIVE_INFINITY;
        Punto x = null;
        Punto y = null;
        int nComparaciones = 0;
        //Ordenamos la lista por la x
        ordenarPuntosPorXQuickSort(punto);

        for (int i = 0; i < punto.size(); i++) {
            for (int j = 0; j < punto.size(); j++) {
                //Controlamos que un punto no calcule la distancia con el mismo
                if (punto.get(i) != punto.get(j)) { 
                    ParDePuntos dosPuntos = new ParDePuntos(punto.get(i), punto.get(j));
                    distancia = dosPuntos.distancia();
                    //Descartamos los puntos lejanos
                    while (distancia < distanciaMin) {
                        nComparaciones++;
                        distanciaMin = distancia;
                        x = punto.get(i);
                        y = punto.get(j);
                        //System.out.println("Punto " + i + ": " + punto.get(i) + "Punto " + j + ": " + punto.get(j) + "Distancia: " + distanciaMin + "\n");;
                    }
                    //AQUI NOS MUESTRA TODOS LOS PUNTOS Y TODAS SUS DISTANCIAS
                    //System.out.println("Punto " + i + ": " + punto.get(i) + "Punto " + j + ": " + punto.get(j) + "Distancia: " + distancia + "\n");
                }
            }
        }
        //Mostramos los puntos, la distancia minima entre ellos y el numero de comparaciones realizadas
        System.out.println("X: " + x + "Y: " + y + "Distancia: " + df.format(distanciaMin));
        System.out.println("Numero de comparaciones: " + nComparaciones);
        return distanciaMin;
    }
}
