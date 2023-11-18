package acm_practica1;

/**
 *
 * @author Maria S
 */
public class Solucion {

    public double dMin = 0.0;
    public Punto p1;
    public Punto p2;
    public double time = 0.0;
    public int nComparaciones;

    public Solucion() {
        this.dMin = 0;
        this.time = 0;
        this.nComparaciones = 0;
    }

    public Solucion(double dMin, Punto p1, Punto p2, double time, int nComparaciones) {
        this.dMin = dMin;
        this.p1 = p1;
        this.p2 = p2;
        this.time = time;
        this.nComparaciones = nComparaciones;
    }
}
