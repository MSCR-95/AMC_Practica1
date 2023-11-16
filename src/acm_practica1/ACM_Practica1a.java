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
public class ACM_Practica1a {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        GeneraPuntos GP = new GeneraPuntos();
        GP.rellenarPuntos(5000, false);
        GP.CreaTSP("Hola");
        GP.EscribeTSP("Hola");

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
        Solucion S;
        Solucion2 s2;
        Algoritmos algoritmos = new Algoritmos(GP.getListaPuntos());
        //algoritmos.getPuntoPorIndice(0, GP.getListaPuntos());
        //System.out.println(algoritmos.getPuntoPorIndice(0, GP.getListaPuntos()));

        //*****************************************************************
        System.out.println("EXHAUSTIVO ANTIGUO");

        S = algoritmos.busquedaExhaustiva(p1, 0, GP.getListaPuntos().size(), 0);
        System.out.println("Puntos: ");
        System.out.println(GP.puntos.get(S.indiceP1) + "" + GP.puntos.get(S.indiceP2));
        System.out.println("Distancia: " + S.dMin);
        System.out.println("Comparaciones: " + S.nComparaciones);
        System.out.println("Tiempo: " + S.time);

        System.out.println("EXHAUSTIVO NUEVO");

        s2 = algoritmos.Exhaustivo(p1);
        System.out.println("Puntos: ");
        System.out.println(s2.p1 + "  " + s2.p2);
        System.out.println("Distancia: " + s2.dMin);
        System.out.println("Comparaciones: " + s2.nComparaciones);
        System.out.println("Tiempo: " + s2.time);

        //*****************************************************************
        System.out.println("\nEXHAUSTIVO CON PODA");
        S = algoritmos.busquedaConPoda(p2);
        System.out.println("Puntos: ");
        System.out.println(algoritmos.getPuntoPorIndice(S.indiceP1) + "" + algoritmos.getPuntoPorIndice(S.indiceP2));
        System.out.println("Distancia: " + S.dMin);
        System.out.println("Comparaciones: " + S.nComparaciones);
        System.out.println("Tiempo: " + S.time);

        //*****************************************************************
/*
        System.out.println("\nDIVIDE Y VENCERAS");
        //algoritmos.busquedaConDyV(GP.getListaPuntos());

        S = algoritmos.busquedaDivideYVenceras(GP.getListaPuntos());
        System.out.println("Puntos: ");
        System.out.println(algoritmos.getPuntoPorIndice(S.indiceP1) +""+ algoritmos.getPuntoPorIndice(S.indiceP2));
        System.out.println("Distancia: " + S.dMin);
        System.out.println("Comparaciones: " + S.nComparaciones);

        System.out.println("Tiempo: "+ S.time);
         */
        System.out.println("\nDIVIDE Y NUEVO");
        //algoritmos.busquedaConDyV(GP.getListaPuntos());

        s2 = algoritmos.DyV(p3);
        System.out.println("Puntos: ");
        System.out.println(s2.p1 + "" + s2.p2);
        System.out.println("Distancia: " + s2.dMin);
        System.out.println("Comparaciones: " + s2.nComparaciones);

        System.out.println("Tiempo: " + s2.time);

        //*****************************************************************
        /*
        System.out.println("\nDIVIDE Y VENCERAS MEJORADO");
        Solucion2 Pp =  algoritmos.DyVMejorado(GP.getListaPuntos());//algoritmos.busquedaDivideYVencerasRec(GP.getListaPuntos());
        System.out.println("Puntos: ");
        System.out.println(algoritmos.getPuntoPorIndice(Pp.getP1().getIndice()) +""+ algoritmos.getPuntoPorIndice(Pp.getP2().getIndice()));
        System.out.println("Distancia: " + Pp.distancia());
        System.out.println("Comparaciones: " + S.nComparaciones);

        System.out.println("Tiempo: "+ S.time);
         */
        System.out.println("\nDIVIDE Y MEJORADO");
        //algoritmos.busquedaConDyV(GP.getListaPuntos());

        s2 = algoritmos.DyVMejorado(p4);
        System.out.println("Puntos: ");
        System.out.println(s2.p1 + "" + s2.p2);
        System.out.println("Distancia: " + s2.dMin);
        System.out.println("Comparaciones: " + s2.nComparaciones);

        System.out.println("Tiempo: " + s2.time);
        
    }
}
