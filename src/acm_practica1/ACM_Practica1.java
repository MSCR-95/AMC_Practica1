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
        
        /*
        //Declaramos los puntos
        Punto p1 = new Punto(1, 1);
        Punto p2 = new Punto(5, 5);
        Punto p3 = new Punto(10, 10);
        
        //Declaramos los pares de puntos
        ParDePuntos p1p2 = new ParDePuntos(p1, p2);
        ParDePuntos p1p3 = new ParDePuntos(p1,p3);
        
        System.out.println(p1 + ", " + p2);
        
        //Calculo de la distancia
        double distancia1 = p1p2.distancia();
        double distancia2 = p1p3.distancia();
        
        //Comprobamos que distancia es mayor
        System.out.println(distancia1);
        System.out.println(distancia2);
        */
        
        GeneraPuntos GP = new GeneraPuntos();
        
        GP.crearPuntosAleatorios(10, 5, 20);
        GP.verPuntos();
        
    }
    
}
