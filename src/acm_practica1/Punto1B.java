/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acm_practica1;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 *
 * @author Maria S
 */
public class Punto1B implements Cloneable {

    int id;
    private double x;
    private double y;

    public Punto1B(double x, double y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    @Override
    public Punto1B clone() {

        return this;
    }

    public Punto1B(int id) {
        this.x = 0;
        this.y = 0;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getx() {
        return this.x;
    }

    public double gety() {
        return this.y;
    }

    public void setx(double x) {
        this.x = x;
    }

    public void sety(double y) {
        this.y = y;
    }

    public static double distancia(Punto1B p, Punto1B q) {
        double distancia;
        distancia = sqrt((pow(p.getx() - q.getx(), 2)) + (pow(p.gety() - q.gety(), 2)));
        return distancia;
    }
    /*
    public static int calcularPeso(Punto1B i, Punto1B j) {
        return ((((int) (distancia(i, j) * 100)) % 100) + 1);
    }
    */
    @Override
    public String toString() {
        String texto = Integer.toString(id);
        //String texto = "\t X:" + df.format(this.getx()) + "\t Y: " + df.format(this.gety());
        return texto;

    }
}
