/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acm_practica1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Maria S
 */
public class Pruebas {

    public static void main(String[] args) {

        //PRUEBAS CON FRAME
        VisualParte1A frame = new VisualParte1A();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
        /*
        GeneraPuntos GP = new GeneraPuntos();
        //GP.LeePuntos("src/data/berlin52.tsp/berlin52.tsp");
        int talla = 500;
        GP.rellenarPuntos(talla, false);
        //String nombre = "dataSet"+talla;
        //GP.CreaTSP(nombre);
        //GP.EscribeTSP(nombre);

        List<Punto> listaCiudades = GP.getListaPuntos();
        
        //List<Punto> listaCiudades = new ArrayList<>();
        Algoritmos algoritmos = new Algoritmos(listaCiudades);
        //algoritmos.comprobarEstrategias(listaCiudades);
       
        
        
        algoritmos.comparar2Estrategias();

        
        JFrame frame = new JFrame("Lista de Puntos");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //no necesitamos la lista original porque las coordenas de los puntos son la misma que en la ruta
                //dibujarPuntos(g, listaCiudades, TSP.TSPBidireccional(listaCiudades), this.getWidth(), this.getHeight());
                //dibujarPuntos(g, listaCiudades, ParDePuntos pdMini, this.getWidth(), this.getHeight());
                dibujarPuntos(g, listaCiudades, this.getWidth(), this.getHeight());
            }
        };

        frame.add(panel);
        frame.setVisible(true);
        */

    }

    //private static void dibujarPuntos(Graphics g, List<Punto> puntos, List<Punto> ruta, int panelAncho, int panelAlto) {
    private static void dibujarPuntos(Graphics g, List<Punto> ruta, int panelAncho, int panelAlto) {

        Algoritmos algoritmos = new Algoritmos(ruta);
        //Podemos usar cualquier algoritmo, ya que la lista de puntos es la misma, y los puntos con la distancia minima tambien
        //Usamos el con poda porque suele ser muy rapido
        Solucion s = algoritmos.busquedaConPoda(ruta);

        //Sacamos los dos puntos mas cercanos de la solucion
        Punto p1 = algoritmos.getPuntoPorIndice(s.indiceP1);
        Punto p2 = algoritmos.getPuntoPorIndice(s.indiceP2);

        double maxX = ruta.stream().mapToDouble(Punto::getX).max().orElse(0);
        double maxY = ruta.stream().mapToDouble(Punto::getY).max().orElse(0);

        double factorEscala = Math.min(panelAncho / maxX, panelAlto / maxY);

        //Dibuja los puntos
        for (int i = 0; i < ruta.size(); i++) {
            Punto punto = ruta.get(i);
            int x = (int) (punto.getX() * factorEscala);
            int y = (int) (punto.getY() * factorEscala);

            g.setColor(Color.BLACK);    // Restablecer el color a negro para los demás puntos

            g.fillOval(x, y, 5, 5);
        }
        
        
        //linea entre los dos puntos
        g.setColor(Color.RED);
        int primerX = (int) (p1.getX() * factorEscala);
        int primerY = (int) (p1.getY() * factorEscala);
        int ultimoX = (int) (p2.getX() * factorEscala);
        int ultimoY = (int) (p2.getY() * factorEscala);

        g.drawLine(primerX, primerY, ultimoX, ultimoY);
        //Pintamos de rojo los dos puntos mas cercanos
        g.fillOval(primerX, primerY, 5, 5);
        g.fillOval(ultimoX, ultimoY, 5, 5);

    }

    //----------------- IMPRIMIR LISTA ------------------------//
    private static void imprimirLista(List<Punto> puntos) {
        for (Punto punto : puntos) {
            System.out.println(punto);
        }
    }

    //-----------------------QUICKSORT X, Y------------------//
    private static void ordenarPuntosPorYQuickSort(List<Punto> puntos) {
        quicksort(puntos, 0, puntos.size() - 1, Comparator.comparingDouble(Punto::getY));
    }

    private static void ordenarPuntosPorXQuickSort(List<Punto> puntos) {
        quicksort(puntos, 0, puntos.size() - 1, Comparator.comparingDouble(Punto::getX));
    }

    private static void quicksort(List<Punto> puntos, int izq, int der, Comparator<Punto> comparador) {
        if (izq < der) {
            int particion = particion(puntos, izq, der, comparador);
            quicksort(puntos, izq, particion - 1, comparador);
            quicksort(puntos, particion + 1, der, comparador);
        }
    }

    private static int particion(List<Punto> puntos, int izq, int der, Comparator<Punto> comparador) {
        Punto pivote = puntos.get(izq);
        int i = izq;
        int j = der;

        while (i < j) {
            while (comparador.compare(puntos.get(i), pivote) <= 0 && i < j) {
                i++;
            }
            while (comparador.compare(puntos.get(j), pivote) > 0) {
                j--;
            }
            if (i < j) {
                intercambiar(puntos, i, j);
            }
        }

        intercambiar(puntos, izq, j);
        return j;
    }

    private static void intercambiar(List<Punto> puntos, int i, int j) {
        Punto temp = puntos.get(i);
        puntos.set(i, puntos.get(j));
        puntos.set(j, temp);
    }

}
