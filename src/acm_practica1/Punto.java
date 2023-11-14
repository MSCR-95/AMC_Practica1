package acm_practica1;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.text.DecimalFormat;

/**
 *
 * @author Maria S
 */
public class Punto {

    private double x;
    private double y;
    private int indice;

    private DecimalFormat df = new DecimalFormat("#.###");

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
    //Para la parte 1B
    public static double distancia(Punto p, Punto q) {
        double distancia;
        distancia = sqrt((pow(p.getX()- q.getX(), 2)) + (pow(p.getY() - q.getY(), 2)));
        return distancia;
    }

    @Override
    public String toString() {
        //return df.format(x) + " " + df.format(y) + String.format("%n");
        return indice + " (" + df.format(x) + " " + df.format(y) + ")";
    }
}
