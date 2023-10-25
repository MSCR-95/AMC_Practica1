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
                        //System.out.println("Punto " + i + ": " + punto.get(i) + "Punto " + j + ": " + punto.get(j) + "Distancia: " + distanciaMin + "\n");;
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
    
    private void ordenarPuntosPorXQuickSort(List<Punto> punto) {

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
        Punto x = null;
        Punto y = null;
        int nComparaciones = 0;
        //Ordenamos la lista por la x
        ordenarPuntosPorXQuickSort(punto);

        for (int i = 0; i < punto.size(); i++) {
            for (int j = i + 1; j < punto.size(); j++) {
                //Controlamos que un punto no calcule la distancia con el mismo
                    ParDePuntos dosPuntos = new ParDePuntos(punto.get(i), punto.get(j));
                    distancia = dosPuntos.distancia();
                    //Descartamos los puntos lejanos
                    nComparaciones++;
                    while (distancia < distanciaMin) {
                        distanciaMin = distancia;
                        x = punto.get(i);
                        y = punto.get(j);
                        //System.out.println("Punto " + i + ": " + punto.get(i) + "Punto " + j + ": " + punto.get(j) + "Distancia: " + distanciaMin + "\n");;
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
}
/*
CHAT GP
public static double encontrarDistanciaMinima(List<Punto> puntos) {
        // Ordenar los puntos por la coordenada x
        puntos.sort(Comparator.comparingDouble(punto -> punto.x));

        int n = puntos.size();
        double distanciaMinima = Double.MAX_VALUE;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                double distanciaX = puntos.get(j).x - puntos.get(i).x;
                if (distanciaX >= distanciaMinima) {
                    break; // Poda: no es necesario seguir con puntos más lejos en la coordenada x.
                }
                double distancia = calcularDistancia(puntos.get(i), puntos.get(j));
                if (distancia < distanciaMinima) {
                    distanciaMinima = distancia;
                }
            }
        }

        return distanciaMinima;
    }
*/
