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

        double distancia = 0;

        //ParDePuntos dosPuntos = new ParDePuntos();
        for (int i = 0; i < punto.size(); i++) {
            for (int j = 0; j < punto.size(); j++) {
                if (punto.get(i) != punto.get(j)) { //Controlamos que un punto no calcule la distancia con el mismo
                    ParDePuntos dosPuntos = new ParDePuntos(punto.get(i), punto.get(j));
                    //dosPuntos(punto.get(i), punto.get(j));
                    distancia = dosPuntos.distancia();
                    System.out.println("Punto 1: " + punto.get(i) + " Punto 2: " + punto.get(j) + " " + distancia);
                }

            }
        }
        return distancia;
    }

}
