/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acm_practica1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maria S
 */
public class Algoritmos {

    public List<Punto> puntos = new ArrayList<>();

    public double busquedaExaustiva(List<Punto> punto) {

        double distancia = -1;
        double distanciaMin = Double.POSITIVE_INFINITY;

        //ParDePuntos dosPuntos = new ParDePuntos();
        for (int i = 0; i < punto.size(); i++) {
            for (int j = 0; j < punto.size(); j++) {
                //Controlamos que un punto no calcule la distancia con el mismo
                if (punto.get(i) != punto.get(j)) {
                    ParDePuntos dosPuntos = new ParDePuntos(punto.get(i), punto.get(j));

                    distancia = dosPuntos.distancia();
                    
                    //FILTRAMOS Y MOSTRAR¡MOS SOLO LA MENOR DISTANCIA DE UN PUNTO
                    if (distancia < distanciaMin) {

                        distanciaMin = distancia;
                        System.out.println("Punto " + i + ": " + punto.get(i) + "Punto " + j + ": " + punto.get(j) + "Distancia: " + distanciaMin + "\n");;
                    }
                    //AQUI NOS MUESTRA TODOS LOS PUNTOS Y TODAS SUS DISTANCIAS
                    //System.out.println("Punto " + i + ": " + punto.get(i) + "Punto " + j + ": " + punto.get(j) + "Distancia: " + distancia + "\n");
                }
            }
        }
        System.out.println("" );
        System.out.println("Distancia Minima: " + distanciaMin);
        return distanciaMin;
    }

}
