package acm_practica1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GeneraPuntos {

    private static final JFileChooser filechooser = new JFileChooser();

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

        System.out.println("------------MOSTRAR LOS PUNTOS-----------");
        for (int i = 0; i < puntos.size(); i++) {
            System.out.println(" " + puntos.get(i));
        }
    }

    public List<Punto> getListaPuntos() {
        return puntos;
    }



}
