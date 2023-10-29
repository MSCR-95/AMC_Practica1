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
            for (int j = i + 1; j < punto.size(); j++) {
                //Controlamos que un punto no calcule la distancia con el mismo
                ParDePuntos dosPuntos = new ParDePuntos(punto.get(i), punto.get(j));
                distancia = dosPuntos.distancia();
                nComparaciones++;
                //FILTRAMOS
                if (distancia < distanciaMin) {
                    distanciaMin = distancia;
                    x = punto.get(i);
                    y = punto.get(j);
                }
                //AQUI NOS MUESTRA TODOS LOS PUNTOS Y TODAS SUS DISTANCIAS
                //System.out.println("Punto " + i + ": " + punto.get(i) + "Punto " + j + ": " + punto.get(j) + "Distancia: " + distancia + "\n");
            }
        }
        //Mostramos los puntos, la distancia minima entre ellos y el numero de comparaciones realizadas
        System.out.println("X: " + x + "Y: " + y + "Distancia: " + df.format(distanciaMin));
        System.out.println("Numero de comparaciones: " + nComparaciones);
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

    private void ordenarPuntosPorXVarQuickSort(List<Punto> punto) {
        //Ordenar ArrayList de punto por X en orden ascendente usando un comparador personalizado
        Collections.sort(punto, new Comparator<Punto>() {
            @Override
            public int compare(Punto p1, Punto p2) {
                return Double.compare(p1.getX(), p2.getX());
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
            for (int j = i + 1; j < punto.size(); j++) {

                distanciaX = Math.abs(punto.get(j).getX() - punto.get(i).getX());
                //System.out.println("PuntoJ.X: " + punto.get(j).getX() + " PuntoI.X: " + punto.get(i).getX() + " DistanciaX: " + distanciaX);
                // Poda: no es necesario seguir con puntos más lejos en la coordenada x.
                if (distanciaX >= distanciaMin) {
                    System.out.println("entra en la poda");
                    //Mostramos los puntos, la distancia minima entre ellos y el numero de comparaciones realizadas
                    System.out.println("X: " + x + "Y: " + y + "Distancia: " + df.format(distanciaMin));
                    System.out.println("Numero de comparaciones: " + nComparaciones);
                    return distanciaMin;  
                }
                nComparaciones++;
                ParDePuntos dosPuntos = new ParDePuntos(punto.get(i), punto.get(j));
                distancia = dosPuntos.distancia();
                if (distancia < distanciaMin) {
                    x = punto.get(i);
                    y = punto.get(j);
                    distanciaMin = distancia;
                }
                //AQUI NOS MUESTRA TODOS LOS PUNTOS Y TODAS SUS DISTANCIAS
                //System.out.println("Punto " + i + ": " + punto.get(i) + "Punto " + j + ": " + punto.get(j) + "Distancia: " + distancia + "\n");
            }
        }
        return distanciaMin;
    }


    public double busquedaConPodaWhile(List<Punto> punto) {
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
                //System.out.println("PuntoJ.X: " + punto.get(j).getX() + " PuntoI.X: " + punto.get(i).getX() + " DistanciaX: " + distanciaX);
                // Poda: no es necesario seguir con puntos más lejos en la coordenada x.
                if (distanciaX >= distanciaMin) {
                    alarma = true;
                }
                if (!alarma){
                    nComparaciones++;
                    ParDePuntos dosPuntos = new ParDePuntos(punto.get(i), punto.get(j));
                    distancia = dosPuntos.distancia();
                    if (distancia < distanciaMin) {
                        x = punto.get(i);
                        y = punto.get(j);
                        distanciaMin = distancia;
                  }
                }

                //AQUI NOS MUESTRA TODOS LOS PUNTOS Y TODAS SUS DISTANCIAS
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
}
