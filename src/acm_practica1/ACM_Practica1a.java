/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package acm_practica1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import acm_practica1.Algoritmos.Solucion;

/**
 *
 * @author Maria S
 */
public class ACM_Practica1a {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        GeneraPuntos GP = new GeneraPuntos();
        //GP.rellenarPuntos(1000, false);
        //GP.CreaTSP("Hola");
        //GP.EscribeTSP("Hola");

        //GP.LeePuntos("src/data/berlin52.tsp/berlin52.tsp");
        //GP.LeePuntos("src/data/ch130.tsp/ch130.tsp");
        //GP.LeePuntos("src/data/ch150.tsp/ch150.tsp");
        //GP.LeePuntos("src/data/d493.tsp/d493.tsp");
        GP.LeePuntos("src/data/d657.tsp/d657.tsp");

        //GP.verPuntos();
        double distancia = 0;
        Algoritmos algoritmos = new Algoritmos(GP.getListaPuntos());
        //algoritmos.getPuntoPorIndice(0, GP.getListaPuntos());
        //System.out.println(algoritmos.getPuntoPorIndice(0, GP.getListaPuntos()));


        //*****************************************************************
      
        System.out.println("EXHAUSTIVO");

        distancia = algoritmos.busquedaExaustiva(GP.getListaPuntos(), 0, GP.getListaPuntos().size());
        System.out.println("Puntos: ");
        System.out.println(GP.puntos.get(algoritmos.getIndiceP1()) + "" + GP.puntos.get(algoritmos.getIndiceP2()));
        System.out.println("Distancia: " + distancia);
        System.out.println("Comparaciones: " + algoritmos.getNComparaciones());
        System.out.println("Tiempo: "+ algoritmos.getTiempoBusquedaExhaustiva());

        
        //*****************************************************************
        
        System.out.println("\nEXHAUSTIVO CON PODA");
        distancia = algoritmos.busquedaConPoda(GP.getListaPuntos());
        System.out.println("Puntos: ");
        System.out.println(algoritmos.getPuntoPorIndice(algoritmos.getIndiceP1()) +""+ algoritmos.getPuntoPorIndice(algoritmos.getIndiceP2()));
        System.out.println("Distancia: " + distancia);
        System.out.println("Comparaciones: " + algoritmos.getNComparacionesPoda());
        System.out.println("Tiempo: "+algoritmos.getTiempoBusquedaConPoda());
        
        //*****************************************************************

        System.out.println("\nDIVIDE Y VENCERAS");
        //algoritmos.busquedaConDyV(GP.getListaPuntos());

        distancia = algoritmos.busquedaDivideYVenceras(GP.getListaPuntos());
        System.out.println("Puntos: ");
        System.out.println(algoritmos.getPuntoPorIndice(algoritmos.getIndiceP1()) +""+ algoritmos.getPuntoPorIndice(algoritmos.getIndiceP2()));
        System.out.println("Distancia: " + distancia);
        System.out.println("Comparaciones: " + algoritmos.getNComparacionesDyV());

        System.out.println("Tiempo: "+ algoritmos.getTiempoEncontrarPuntosMasCercanos());

        System.out.println("\nDIVIDE Y VENCERAS MEJORADO");
        distancia = algoritmos.busquedaDivideYVencerasMejorado(GP.getListaPuntos());
        System.out.println("Puntos: ");
        System.out.println(algoritmos.getPuntoPorIndice(algoritmos.getIndiceP1()) +""+ algoritmos.getPuntoPorIndice(algoritmos.getIndiceP2()));
        System.out.println("Distancia: " + distancia);
        System.out.println("Comparaciones: " + algoritmos.getNComparacionesDivideYVencerasMejorado());

        System.out.println("Tiempo: "+ algoritmos.getTiempoEncontrarPuntosMasCercanosTest());
       
    }  
}
