package acm_practica1;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Maria S
 */
public class ACM_Practica1b {

    /**
     * @param args the command line arguments
     */
    private static List<Punto> listaCiudades;  // Campo de instancia
    private static final int VENTANA_TAMANO = 600;
    
    public static void main(String[] args) {

       
        //List<Punto> listaCiudades = new ArrayList<>();
        
        GeneraPuntos GP = new GeneraPuntos();
        //GP.rellenarPuntos(1000, false);
        //GP.CreaTSP("Hola");
        //GP.EscribeTSP("Hola");

        /*
        List<Punto> listaCiudades = new ArrayList<>();
        GeneraPuntos GP = new GeneraPuntos();
        GP.LeePuntos("src/data/berlin52.tsp/berlin52.tsp");
        listaCiudades = GP.getListaPuntos();
        */
        //GP.LeePuntos("src/data/berlin52.tsp/berlin52.tsp");
        GP.LeePuntos("src/data/ch130.tsp/ch130.tsp");
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
        /*
        for (Punto ciudad : listaCiudades) {
            System.out.println(ciudad.getIndice());
        }
        */
        Algoritmos1B TSP = new Algoritmos1B();
        // Resuelve el TSP utilizando la estrategia unidireccional
        
        //TSP.calcularRutas(listaCiudades);
        //con talla 5 ya tarda en ejecutarse, es inviable
        //TSP.comprobacionEmpirica();
        

        //--------------------
        //--------------------
        /*
        // Calcular el desplazamiento y ajustar las coordenadas
        double offsetX = calcularDesplazamientoX();
        double offsetY = calcularDesplazamientoY();

        // Ajustar las coordenadas de los puntos
        ajustarCoordenadas(offsetX, offsetY);
        
        // Escalar las coordenadas para ajustarse al tamaño de la ventana
        escalarCoordenadas();
        
        // Crear un JFrame
        JFrame frame = new JFrame("Lista de Puntos");
        frame.setSize(VENTANA_TAMANO, VENTANA_TAMANO);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un JPanel personalizado para dibujar los puntos
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarPuntos(g, listaCiudades);
            }
        };
        // Agregar el panel al JFrame
        frame.add(panel);

        // Hacer visible el JFrame
        frame.setVisible(true);
        */
        //---------------NUEVOOO
        
        JFrame frame = new JFrame("Lista de Puntos");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarPuntos(g, listaCiudades, TSP.TSPBidireccional(listaCiudades),this.getWidth(), this.getHeight());
            }
        };

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void dibujarPuntos(Graphics g, List<Punto> puntos,List<Punto> ruta, int panelAncho, int panelAlto) {
        double maxX = puntos.stream().mapToDouble(Punto::getX).max().orElse(0);
        double maxY = puntos.stream().mapToDouble(Punto::getY).max().orElse(0);

        double factorEscala = Math.min(panelAncho / maxX, panelAlto / maxY);

       // Dibujar líneas rojas entre los puntos de la ruta
        if (ruta != null && !ruta.isEmpty()) {
            g.setColor(Color.RED);
            for (int i = 0; i < ruta.size() - 1; i++) {
                Punto punto1 = ruta.get(i);
                Punto punto2 = ruta.get(i + 1);

                int x1 = (int) (punto1.getX() * factorEscala);
                int y1 = (int) (punto1.getY() * factorEscala);
                int x2 = (int) (punto2.getX() * factorEscala);
                int y2 = (int) (punto2.getY() * factorEscala);

                g.drawLine(x1, y1, x2, y2);
            }
        }

        // Dibujar puntos
        g.setColor(Color.BLACK);
        for (Punto punto : puntos) {
            int x = (int) (punto.getX() * factorEscala);
            int y = (int) (punto.getY() * factorEscala);
            g.fillOval(x, y, 5, 5);
        }
    }
    
    /*
    
    // Método para dibujar los puntos en el panel
    public static void dibujarPuntos(Graphics g, List<Punto> puntos) {
        for (Punto punto : puntos) {
            g.fillOval((int)punto.getX(), (int)punto.getY(), 5, 5); // Dibuja un círculo pequeño en cada punto
        }
    }
    
    // Método para calcular el desplazamiento mínimo en el eje X
    private static double calcularDesplazamientoX() {
        return listaCiudades.stream().mapToDouble(Punto::getX).min().orElse(0);
    }

    // Método para calcular el desplazamiento mínimo en el eje Y
    private static double calcularDesplazamientoY() {
        return listaCiudades.stream().mapToDouble(Punto::getY).min().orElse(0);
    }

    // Método para ajustar las coordenadas de los puntos
    private static void ajustarCoordenadas(double offsetX, double offsetY) {
        for (Punto punto : listaCiudades) {
            punto.setX(punto.getX() - offsetX);
            punto.setY(punto.getY() - offsetY);
        }
    }

    // Método para escalar las coordenadas para ajustarse al tamaño de la ventana
    private static void escalarCoordenadas() {
        double maxX = listaCiudades.stream().mapToDouble(Punto::getX).max().orElse(0);
        double maxY = listaCiudades.stream().mapToDouble(Punto::getY).max().orElse(0);

        double factorX = (VENTANA_TAMANO - 1) / maxX;
        double factorY = (VENTANA_TAMANO - 1) / maxY;

        for (Punto punto : listaCiudades) {
            punto.setX(punto.getX() * factorX);
            punto.setY(punto.getY() * factorY);
        }
    }
    */
}