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
        
        GP.rellenarPuntos(10, false);
        GP.verPuntos();
        
        Algoritmos algoritmos = new Algoritmos();
        
        //algoritmos.busquedaExaustiva(GP.getListaPuntos());
        //algoritmos.busquedaConPoda(GP.getListaPuntos());
        /*
        //PARA COMPROBAR QUE LA LISTA SE ORDENA POR LA X;
        algoritmos.ordenarPuntosPorX(GP.getListaPuntos());
        GP.verPuntos();
        */
        algoritmos.ordenarPuntosPorXQuickSort(GP.getListaPuntos());
        /*
        GeneraPuntos GP = new GeneraPuntos();
        GP.LeePuntos("src/data/berlin52.tsp/berlin52.tsp");
        GP.CreaTSP("Try1");
        GP.EscribeTSP("Try1");
        
        GeneraPuntos GP = new GeneraPuntos();
        GP.LeePuntos("src/data/berlin52.tsp/berlin52.tsp");
        GP.verPuntos();
        */
        /*
        //rellenar puntos aleatorios
        GP.rellenarPuntos(10, false);
        GP.verPuntos();
        
        Algoritmos algoritmos = new Algoritmos();
        
        algoritmos.busquedaExaustiva(GP.getListaPuntos());
        */
        /*
        GeneraPuntos GP = new GeneraPuntos();
        GP.rellenarPuntos(10, false);
        GP.verPuntos();
        //GP.LeePuntos("src/data/berlin52.tsp/berlin52.tsp");
        
        GP.CreaTSP("Hola");
        GP.EscribeTSP("Hola");
    */
    }

}
