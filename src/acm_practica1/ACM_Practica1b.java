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

        List<Punto1B> listaCiudades = new ArrayList<>();

        //Agrega ciudades a la lista

        listaCiudades = leePuntos("src/data/berlin52.tsp/berlin52.tsp");  // UNI > BI
        //listaCiudades = leePuntos("src/data/ch130.tsp/ch130.tsp");        // UNI > BI
        //listaCiudades = leePuntos("src/data/ch150.tsp/ch150.tsp");        // UNI < BI
        //listaCiudades = leePuntos("src/data/d493.tsp/d493.tsp");          // UNI == BI
        //listaCiudades = leePuntos("src/data/d657.tsp/d657.tsp");          // UNI == BI
        // Imprime la lista de ciudades
        
        for (Punto1B ciudad : listaCiudades) {
            System.out.println(ciudad);
        }
        Algoritmos1B TSP = new Algoritmos1B();
        // Resuelve el TSP utilizando la estrategia unidireccional
        List<Punto1B> rutaUnidireccional = TSP.TSPUnidireccional(listaCiudades);

        // Resuelve el TSP utilizando la estrategia bidireccional
        List<Punto1B> rutaBidireccional = TSP.TSPBidireccional(listaCiudades);

        System.out.println("\nRuta Unidireccional");
        System.out.println("SOLUTION: " + calcularCostoTotal(rutaUnidireccional));
        imprimirRuta(rutaUnidireccional);
        
        
        System.out.println("\nRuta Bidireccional");
        System.out.println("SOLUTION: " + calcularCostoTotal(rutaBidireccional));
        imprimirRuta(rutaBidireccional);
        
    }  

    public static List<Punto1B> leePuntos(String path) {
        List<Punto1B> puntos = new ArrayList<>();
        int DIM;
        int ENT;
        double D1 = 0.0;
        double D2 = 0.0;
        try {
            List<String> allLines = Files.readAllLines(Paths.get(path));
            StringTokenizer Token = new StringTokenizer(allLines.get(3), "DIMENSION: ", false);
            DIM = Integer.parseInt(Token.nextToken());
            for (int i = 6; i < DIM + 6; i++) {
                Token = new StringTokenizer(allLines.get(i), " ", false);
                ENT = Integer.parseInt(Token.nextToken());
                D1 = Double.parseDouble(Token.nextToken());
                D2 = Double.parseDouble(Token.nextToken());
                puntos.add(new Punto1B(D1, D2, ENT));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return puntos;
    }
    
    public static void imprimirRuta(List<Punto1B> ruta) {
        for (Punto1B ciudad : ruta) {
            System.out.print(ciudad.getId() + ",");
        }
        System.out.println(ruta.get(0).getId()); // Regresa al inicio
        int pesoArista;
        for (int i = 0; i < ruta.size() - 1; i++) {
            Punto1B ciudadActual = ruta.get(i);
            Punto1B ciudadSiguiente = ruta.get(i + 1);
            pesoArista = (int) Punto1B.distancia(ciudadActual, ciudadSiguiente);
            System.out.println(pesoArista + " - " + ciudadActual.getId() + "," + ciudadSiguiente.getId());
        }
        //Mostra peso regreso a la ciudad de inicio
        pesoArista = (int) Punto1B.distancia(ruta.get(ruta.size() - 1), ruta.get(0));
        System.out.println(pesoArista + " - " + ruta.get(ruta.size() - 1) + "," + ruta.get(0));
    }

    //AÑADIR A ALGORITMO 1B
    public static int calcularCostoTotal(List<Punto1B> ruta) {
        int costoTotal = 0;
        int numCiudades = ruta.size();

        for (int i = 0; i < numCiudades - 1; i++) {
            Punto1B ciudadActual = ruta.get(i);
            Punto1B ciudadSiguiente = ruta.get(i + 1);
            costoTotal += Punto1B.distancia(ciudadActual, ciudadSiguiente);
        }
        // Agregar el costo de regreso a la ciudad de inicio
        costoTotal += Punto1B.distancia(ruta.get(numCiudades - 1), ruta.get(0));

        return costoTotal;
    }  
}