/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acm_practica1;

/**
 *
 * @author Maria S
 */
public class Solucion2 {

    public double dMin;
    //public int indiceP1;
    //public int indiceP2;
    public Punto p1;
    public Punto p2;
    public double time;
    public int nComparaciones;

    public Solucion2() {
    }

    public Solucion2(double dMin, Punto p1, Punto p2, double time, int nComparaciones) {
        this.dMin = dMin;
        this.p1 = p1;
        this.p2 = p2;
        //this.indiceP1 = indiceP1;
        //this.indiceP2 = indiceP2;
        this.time = time;
        this.nComparaciones = nComparaciones;
    }
}
