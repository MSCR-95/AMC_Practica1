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

        //ParDePuntos dosPuntos = new ParDePuntos();
        for (int i = 0; i < punto.size(); i++) {
            for (int j = 0; j < punto.size(); j++) {
                //Controlamos que un punto no calcule la distancia con el mismo
                if (punto.get(i) != punto.get(j)) {
                    ParDePuntos dosPuntos = new ParDePuntos(punto.get(i), punto.get(j));
                    
                    distancia = dosPuntos.distancia();
                    
                    //FALTA FILTRAR Y MOSTRAR SOLO LA MENOR DISTANCIA DE UN PUNTO
                    /*
                    if (distancia == -1 || distancia > dosPuntos.distancia()) {
                        distancia = dosPuntos.distancia();
                        
                    }
                    */
                    //AQUI NOS MUESTRA TODOS LOS PUNTOS Y TODAS SUS DISTANCIAS
                    System.out.println("Punto 1: " + punto.get(i) + " Punto 2: " + punto.get(j) + " " + distancia);
                }
            }
        }
        return distancia;
    }

}
