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
        //GP.rellenarPuntos(50, false);
        GP.LeePuntos("src/data/berlin52.tsp/berlin52.tsp");

        GP.CreaTSP("Hola");
        GP.EscribeTSP("Hola");
       
        GP.verPuntos();
        
        Algoritmos algoritmos = new Algoritmos();
        
        
        algoritmos.busquedaExaustiva(GP.getListaPuntos());
        algoritmos.busquedaConPoda(GP.getListaPuntos());
    }

}
