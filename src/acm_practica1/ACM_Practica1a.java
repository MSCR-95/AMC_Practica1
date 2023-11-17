/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package acm_practica1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Maria S
 */
public class ACM_Practica1a {

    //Formatear a 8 decimales
    public static double decimales8(double numero) {
        //long decimales = 10000000000;
        numero = Math.round(numero * 100000000) / 100000000d;
        return numero;
    }

    private static DecimalFormat df = new DecimalFormat("#.########");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        GeneraPuntos GP = new GeneraPuntos();
        int talla = 5000;
        GP.rellenarPuntos(talla, true);
        //GP.CreaTSP("Hola1");
        //GP.EscribeTSP("Hola1", Algoritmos);

        //GP.LeePuntos("src/data/berlin52.tsp/berlin52.tsp");
        //GP.LeePuntos("src/data/ch130.tsp/ch130.tsp");
        //GP.LeePuntos("src/data/ch150.tsp/ch150.tsp");
        //GP.LeePuntos("src/data/d493.tsp/d493.tsp");
        //GP.LeePuntos("src/data/d657.tsp/d657.tsp");
        //GP.LeePuntos("src/data/intentos/Hola.tps");
        //GP.verPuntos();
        List<Punto> p1 = GP.getListaPuntos();
        List<Punto> p2 = p1;
        List<Punto> p3 = p1;
        List<Punto> p4 = p1;
        //Solucion S;
        Solucion s2;
        Algoritmos algoritmos = new Algoritmos();
        //algoritmos.getPuntoPorIndice(0, GP.getListaPuntos());
        //System.out.println(algoritmos.getPuntoPorIndice(0, GP.getListaPuntos()));
         */

        Algoritmos algoritmos = new Algoritmos();
        int numIteraciones = 5;
        int tallaInicial = 1000;
        Algoritmos algoritmo = new Algoritmos();
        GeneraPuntos GP = new GeneraPuntos();
        //Object[] columnNames2 = {"Talla", "tiempo(mseg)", "tiempo(mseg)", "tiempo(mseg)", "tiempo(mseg)"};
        //tableModel.addRow(columnNames2);
        Solucion s2;
        for (int i = 0; i < numIteraciones; i++) {
            int talla = tallaInicial + i * 1000;
            //GP = new GeneraPuntos();
            GP.rellenarPuntos(talla, true);
            List<Punto> puntos = GP.getListaPuntos();

            //*****************************************************************
            System.out.println("EXHAUSTIVO ANTIGUO");
            System.out.println("talla: " + talla);
            s2 = algoritmos.busquedaExhaustiva(puntos);
            System.out.println("Puntos: ");
            System.out.println(s2.p1 + "  " + s2.p2);
            System.out.println("Distancia: " + df.format(s2.dMin));
            System.out.println("Comparaciones: " + s2.nComparaciones);
            System.out.println("Tiempo: " + s2.time);
            //*****************************************************************
            System.out.println("\nEXHAUSTIVO CON PODA");
            System.out.println("talla: " + talla);
            s2 = algoritmos.busquedaConPoda(puntos);
            System.out.println("Puntos: ");
            System.out.println(s2.p1 + "  " + s2.p2);
            System.out.println("Distancia: " + df.format(s2.dMin));
            System.out.println("Comparaciones: " + s2.nComparaciones);
            System.out.println("Tiempo: " + s2.time);

            //*****************************************************************
            System.out.println("\nDIVIDE Y NUEVO");
            System.out.println("talla: " + talla);
            s2 = algoritmos.DyV(puntos);
            System.out.println("Puntos: ");
            System.out.println(s2.p1 + "  " + s2.p2);
            System.out.println("Distancia: " + df.format(s2.dMin));
            System.out.println("Comparaciones: " + s2.nComparaciones);
            System.out.println("Tiempo: " + s2.time);

            //*****************************************************************
            System.out.println("\nDIVIDE Y MEJORADO");
            System.out.println("talla: " + talla);
            s2 = algoritmos.DyVMejorado(puntos);
            System.out.println("Puntos: ");
            System.out.println(s2.p1 + "  " + s2.p2);
            System.out.println("Distancia: " + df.format(s2.dMin));
            System.out.println("Comparaciones: " + s2.nComparaciones);
            System.out.println("Tiempo: " + s2.time);
        }
    }

}
