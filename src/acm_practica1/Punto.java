/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acm_practica1;

import java.text.DecimalFormat;

/**
 *
 * @author Maria S
 */
public class Punto {

    private double x;
    private double y;
    private int indice;
    
    private DecimalFormat df = new DecimalFormat("#.##########");

    public Punto() {
        super();
    }

    public Punto(int indice, double x, double y) {
        super();
        this.indice = indice;
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getIndice() {
        return indice;
    }
    

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
       //return df.format(x) + " " + df.format(y) + String.format("%n");
       return indice +" ("+ x + " " + y  + String.format(")%n");
    }
}
