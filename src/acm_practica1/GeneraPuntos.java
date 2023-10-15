package acm_practica1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class GeneraPuntos {

    public List<Punto> puntos = new ArrayList<>();

    //Añadir cargarPuntos() para cargar los puntos desde un fichero con extension: .tsp
    public void crearPuntosAleatorios(int cantidadPuntos, int minimo, int maximo) {

        Random rand = new Random();
        int ramdomX, ramdomY;

        for (int i = 0; i < cantidadPuntos; i++) {

            ramdomX = minimo + rand.nextInt((maximo - minimo) + 1);
            ramdomY = minimo + rand.nextInt((maximo - minimo) + 1);

            Punto p1 = new Punto(ramdomX, ramdomY);

            puntos.add(p1);
            //System.out.println(" " + p1);
        }
    }

    public void verPuntos() {

        System.out.println("------------MOSTRAR LOS PUNTOS-----------");
        for (int i = 0; i < puntos.size(); i++) {
            System.out.println(" " + puntos.get(i));
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
           
}
