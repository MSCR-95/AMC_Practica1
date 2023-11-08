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

/**
 *
 * @author Maria S
 */
public class ACM_Practica1b {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        List<Punto> listaCiudades = new ArrayList<>();
        
        GeneraPuntos GP = new GeneraPuntos();
        //GP.rellenarPuntos(1000, false);
        //GP.CreaTSP("Hola");
        //GP.EscribeTSP("Hola");

        GP.LeePuntos("src/data/berlin52.tsp/berlin52.tsp");
        //GP.LeePuntos("src/data/ch130.tsp/ch130.tsp");
        //GP.LeePuntos("src/data/ch150.tsp/ch150.tsp");
        //GP.LeePuntos("src/data/d493.tsp/d493.tsp");
        //GP.LeePuntos("src/data/d657.tsp/d657.tsp");

        //Agrega ciudades a la lista
        listaCiudades = GP.getListaPuntos();
        //listaCiudades = leePuntos("src/data/berlin52.tsp/berlin52.tsp");  // UNI > BI
        //listaCiudades = leePuntos("src/data/ch130.tsp/ch130.tsp");        // UNI > BI
        //listaCiudades = leePuntos("src/data/ch150.tsp/ch150.tsp");        // UNI < BI
        //listaCiudades = leePuntos("src/data/d493.tsp/d493.tsp");          // UNI == BI
        //listaCiudades = leePuntos("src/data/d657.tsp/d657.tsp");          // UNI == BI
        // Imprime la lista de ciudades
        
        for (Punto ciudad : listaCiudades) {
            System.out.println(ciudad.getIndice());
        }
        Algoritmos1B TSP = new Algoritmos1B();
        // Resuelve el TSP utilizando la estrategia unidireccional
        List<Punto> rutaUnidireccional = TSP.TSPUnidireccional(listaCiudades);

        // Resuelve el TSP utilizando la estrategia bidireccional
        List<Punto> rutaBidireccional = TSP.TSPBidireccional(listaCiudades);

        System.out.println("\nRuta Unidireccional");
        System.out.println("SOLUTION: " + TSP.calcularCostoTotal(rutaUnidireccional));
        TSP.imprimirRuta(rutaUnidireccional);
        
        
        System.out.println("\nRuta Bidireccional");
        System.out.println("SOLUTION: " + TSP.calcularCostoTotal(rutaBidireccional));
        TSP.imprimirRuta(rutaBidireccional);
        
    }  

}