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
        Punto P1 = new Punto();
        Punto P2 = new Punto();
        P1.setX(5);
        P1.setY(10);
        P2.setX(2);
        P2.setY(4);
       ParDePuntos PdP = new ParDePuntos(P1, P2);
        System.out.println("Distancia "+ PdP.distancia());
    }
    
}
