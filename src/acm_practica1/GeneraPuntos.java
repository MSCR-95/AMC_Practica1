package acm_practica1;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class GeneraPuntos {
    
    private DecimalFormat df = new DecimalFormat("#.##########");
    public List<Punto> puntos = new ArrayList<>();
    
    public void rellenarPuntos(int n, boolean mismaX) { 
        int num, den;
        double x, y, aux1;
        Random rand = new Random();
        
        if (mismaX) { // PEOR CASO - TRUE
            for (int i = 0; i < n; i++) {
                aux1 = rand.nextInt(1000) + 7; // 7 y 1007
                y = aux1 / ((double) (i + 1) + i * 0.100); // aux2; //+(i/3.0);
                num = rand.nextInt(3);
                y += ((i % 500) - num * (rand.nextInt(100)));
                x = 1;
                Punto p = new Punto( x, y);
                puntos.add(p);
            }
        } else { // CASO MEDIO - FALSE
            for (int i = 0; i < n; i++) {
                num = rand.nextInt(4000) + 1;   // genera un número aleatorio entre 1 y 4000
                den = rand.nextInt(11) + 7;     // genera un aleatorio entre 7 y 17
                x = num / ((double) den + 0.37);    // división con decimales
                y = (rand.nextInt(4000) + 1) / ((double) (rand.nextInt(11) + 7) + 0.37);
                Punto p = new Punto( x, y);
                puntos.add(p);
            }
        }
    }

    public void verPuntos() {

        System.out.println("------------MOSTRAR LOS PUNTOS-----------\n");
        for (int i = 0; i < puntos.size(); i++) {
            System.out.println( i+1 +" " + puntos.get(i));
        }
    }

    public List<Punto> getListaPuntos() {
        return puntos;
    }
    
    public void LeePuntos(String path) { 
        int DIM;
        int ENT;
        double D1;
        double D2;
           try { 
               List<String> allLines = Files.readAllLines(Paths.get(path));
               StringTokenizer Token = new StringTokenizer(allLines.get(3), "DIMENSION: ", false);
                DIM = Integer.parseInt(Token.nextToken());
                for (int i = 6; i < DIM+6; i++) {
                    Token = new StringTokenizer(allLines.get(i)," ", false);
                    ENT = Integer.parseInt(Token.nextToken());
                    D1 = Double.parseDouble(Token.nextToken());
                    D2 = Double.parseDouble(Token.nextToken());
                    puntos.add(new Punto(D1, D2));
                    }           
               }
           catch (IOException e) { 
                   e.printStackTrace(); 
                } 
           }
    
    public void CreaTSP(String NombreFile){
        try {
            File myObj = new File("src/data/intentos/" + NombreFile + ".tsp");
            if (myObj.createNewFile()) {
                System.out.println("Archivo creado: " + NombreFile + ".tsp");
            } 
            else {
                System.out.println("Ya existe un archivo con ese nombre.");
            }
        } 
        catch (IOException e) {
            System.out.println("Error creando.");
            e.printStackTrace();
            }
    }  

    public void EscribeTSP(String NombreFile){
         try {
            FileWriter myWriter = new FileWriter("src/data/intentos/" + NombreFile + ".tsp");
            myWriter.write("Puntos de " + NombreFile + String.format("%n"));
            
            for (int i = 0; i < puntos.size(); i++) {
                myWriter.write("\n" + (i+1) + " "+ df.format(puntos.get(i).getX()) + " " + df.format(puntos.get(i).getY()));
            }
            
            myWriter.close();
            System.out.println("Archivo escrito con exito.");
        } catch (IOException e) {
            System.out.println("Error escribiendo.");
            e.printStackTrace();
        }
    }   
}
