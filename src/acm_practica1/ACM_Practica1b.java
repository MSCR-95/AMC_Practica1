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
        
        GeneraPuntos GP = new GeneraPuntos();

        GP.LeePuntos("src/data/berlin52.tsp/berlin52.tsp");   // UNI > BI
        //GP.LeePuntos("src/data/ch130.tsp/ch130.tsp");         // UNI > BI
        //GP.LeePuntos("src/data/ch150.tsp/ch150.tsp");         // UNI < BI
        //GP.LeePuntos("src/data/d493.tsp/d493.tsp");           // UNI == BI
        //GP.LeePuntos("src/data/d657.tsp/d657.tsp");           // UNI == BI
        
        //GP.CreaTSP("Hola1B");
        //GP.EscribeTSP("Hola");
        //Agrega ciudades a la lista
        listaCiudades = GP.getListaPuntos();
        
        Algoritmos1B TSP = new Algoritmos1B();
        //TSP.EscribeTSP_1B("Hola1B", listaCiudades);
        // Resuelve el TSP utilizando la estrategia unidireccional

        //TSP.calcularRutas(listaCiudades);
        
        //con talla 5 ya tarda en ejecutarse, es inviable
        TSP.comprobacionEmpirica();
        //--------------------
        //--------------------
        /*
        JFrame frame = new JFrame("Lista de Puntos");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //no necesitamos la lista original porque las coordenas de los puntos son la misma que en la ruta
                //dibujarPuntos(g, listaCiudades, TSP.TSPBidireccional(listaCiudades), this.getWidth(), this.getHeight());
                dibujarPuntos(g, TSP.TSPBidireccional(listaCiudades,TSP.getRand()), this.getWidth(), this.getHeight());
            }
        };

        frame.add(panel);
        frame.setVisible(true);
        */
    }

    //private static void dibujarPuntos(Graphics g, List<Punto> puntos, List<Punto> ruta, int panelAncho, int panelAlto) {
    private static void dibujarPuntos(Graphics g, List<Punto> ruta, int panelAncho, int panelAlto) {
        
        //double maxX = puntos.stream().mapToDouble(Punto::getX).max().orElse(0);
        //double maxY = puntos.stream().mapToDouble(Punto::getY).max().orElse(0);
        
        double maxX = ruta.stream().mapToDouble(Punto::getX).max().orElse(0);
        double maxY = ruta.stream().mapToDouble(Punto::getY).max().orElse(0);

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
            //linea entre el primero y el ultimo
            int primerX = (int) (ruta.get(0).getX() * factorEscala);
            int primerY = (int) (ruta.get(0).getY() * factorEscala);
            int ultimoX = (int) (ruta.get(ruta.size() - 1).getX() * factorEscala);
            int ultimoY = (int) (ruta.get(ruta.size() - 1).getY() * factorEscala);

            g.drawLine(primerX, primerY, ultimoX, ultimoY);  
        }
        
        for (int i = 0; i < ruta.size(); i++) {
            Punto punto = ruta.get(i);
            int x = (int) (punto.getX() * factorEscala);
            int y = (int) (punto.getY() * factorEscala);

            if (i == 0) {
                g.setColor(Color.BLUE);     // Cambiar el color del primer punto a azul
            } else if (i == ruta.size() -1 ) {
                g.setColor(Color.YELLOW);   // Cambiar el color del ultimo punto a amarillo
            } else {
                g.setColor(Color.BLACK);    // Restablecer el color a negro para los demás puntos
            }
            g.fillOval(x, y, 5, 5);
        }
    }

}
