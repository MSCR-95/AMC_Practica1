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
    
    private DecimalFormat df = new DecimalFormat("#.##########");

    public Punto() {
        super();
    }

    public Punto( double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
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
       return x + " " + y  + String.format("%n");
    }
}
