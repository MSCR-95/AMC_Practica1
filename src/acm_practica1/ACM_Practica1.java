/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package acm_practica1;

/**
 *
 * @author Maria S
 */
public class ACM_Practica1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        GeneraPuntos GP = new GeneraPuntos();
        //GP.rellenarPuntos(10, false);
        //GP.CreaTSP("Hola");
        //GP.EscribeTSP("Hola");

        GP.LeePuntos("src/data/berlin52.tsp/berlin52.tsp");
        //GP.LeePuntos("src/data/ch130.tsp/ch130.tsp");
        //GP.LeePuntos("src/data/ch150.tsp/ch150.tsp");
        //GP.LeePuntos("src/data/d493.tsp/d493.tsp");
        //GP.LeePuntos("src/data/d657.tsp/d657.tsp");

        GP.verPuntos();
        double distancia = 0;
        Algoritmos algoritmos = new Algoritmos();
        //algoritmos.getPuntoPorIndice(0, GP.getListaPuntos());
        //System.out.println(algoritmos.getPuntoPorIndice(0, GP.getListaPuntos()));

        /**
         * *****************************************************************
         */
        System.out.println("EXHAUSTIVO");

        distancia = algoritmos.busquedaExaustiva(GP.getListaPuntos(), 0, GP.getListaPuntos().size());
        System.out.println("Puntos: ");
        System.out.println(GP.puntos.get(algoritmos.getIndiceP1()) + "" + GP.puntos.get(algoritmos.getIndiceP2()));
        System.out.println("Distancia: " + distancia);
        System.out.println("Comparaciones: " + algoritmos.getNComparaciones());

        /**
         * *****************************************************************
         */
        System.out.println("\nEXHAUSTIVO CON PODA");
        algoritmos.busquedaConPoda(GP.getListaPuntos());

        /**
         * *****************************************************************
         */
        System.out.println("\nDIVIDE Y VENCERAS");
        //algoritmos.busquedaConDyV(GP.getListaPuntos());

        distancia = algoritmos.busquedaDivideYVenceras(GP.getListaPuntos());
        System.out.println("Puntos: ");
        System.out.println(GP.puntos.get(algoritmos.getIndiceP1()) + "" + GP.puntos.get(algoritmos.getIndiceP2()));
        System.out.println("Distancia: " + distancia);
        System.out.println("Comparaciones: " + algoritmos.getNComparacionesDyV());
        
        //distancia = algoritmos.buscarPuntosMasCercanos(GP.getListaPuntos());
        /*
        System.out.println("Comparaciones: " + algoritmos.getNComparacionesDyV());
        System.out.println(distancia);

        Punto p1 = algoritmos.getPuntoPorIndice(algoritmos.getIndiceP1(), GP.getListaPuntos());
        Punto p2 = algoritmos.getPuntoPorIndice(algoritmos.getIndiceP2(), GP.getListaPuntos());
        System.out.println("Puntos: " + p1 + p2);
         */

        //System.out.println("("+algoritmos.getIndicePx() + ") " + GP.puntos.get(algoritmos.getIndicePx()));
        //System.out.println("("+algoritmos.getIndicePy() + ") " + GP.puntos.get(algoritmos.getIndicePy()));
        //System.out.println("(" + (algoritmos.getIndicePx() + 1) + ") " + GP.puntos.get(algoritmos.getIndicePx()) + "(" + (algoritmos.getIndicePy() + 1) + ") " + GP.puntos.get(algoritmos.getIndicePy()));
        //System.out.println(""+GP.getListaPuntos());
        //System.out.println("Distancia: " + distancia);
        //System.out.println("Comparaciones: " + algoritmos.getNComparacionesDyV());
    }

}
