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

    /**
     * public void LeePuntos() { try { List<String> allLines =
     * Files.readAllLines(Paths.get("data\berlin52.tsp\berlin52.tsp"));
     *
     * for (String line : allLines) { System.out.println(line); } } catch
     * (IOException e) { e.printStackTrace(); } }/*
     */
    public List<Punto> leerFicheros() {

        puntos.clear();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("TSP", "tsp");
        filechooser.setFileFilter(filter);

        filechooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int result = filechooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File fichero = filechooser.getSelectedFile();
            String[] str = fichero.getName().split("\\.");

            if (str[1].equals("tsp")) {
                try {
                    try (FileReader fr = new FileReader(fichero); BufferedReader br = new BufferedReader(fr)) {
                        String lineaActual;
                        int lineasLeidas = 0;

                        while ((lineaActual = br.readLine()) != null) {
                            lineasLeidas++;
                            String[] linea = lineaActual.split(" ");
                            if (lineasLeidas > 6 && !(lineaActual.equals("") || lineaActual.equals("EOF"))) {
                                if (!linea[0].equals("EOF")) {
                                    //añadido el id
                                    puntos.add(new Punto(Double.parseDouble(linea[1]), Double.parseDouble(linea[2])));
                                }
                            }
                        }
                    }
                    /*
                    if (UIView != null) {
                        UIView.Fichero.setText(fichero.getName());
                        UIView.Fichero.setForeground(Color.white);
                        UIView.BotonBuscar.setEnabled(true);
                        pintarCanvas();
                    }
                     */
                    //System.out.println("Fichero " + fichero.getName() + " leido correctamente");
                } catch (IOException | NumberFormatException e) {
                    //vMensaje.Message("error", e.getMessage());
                    System.out.println(e.getMessage());
                }

            }

        } else if (result == JFileChooser.CANCEL_OPTION) {
            /*
            if (UIView != null) {
                UIView.BotonBuscar.setEnabled(false);
                vMensaje.Message("error", "No se ha seleccionado ningun fichero");
                UIView.Fichero.setText("No se ha seleccionado ningun fichero");
                UIView.Fichero.setForeground(Color.red);
            }
             */
        }

        return puntos;
    }

}
