/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acm_practica1;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
                    
                    nComparaciones++;
                    ParDePuntos dosPuntos = new ParDePuntos(punto.get(i), punto.get(j));
                    distancia = dosPuntos.distancia();

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
        }
        //Mostramos los puntos y la distancia minima entre ellos
        System.out.println("X: " + x + "Y: " + y + "Distancia: " + df.format(distanciaMin));
        System.out.println("Numero de comparaciones: " + nComparaciones);
        return distanciaMin;
    }

}
