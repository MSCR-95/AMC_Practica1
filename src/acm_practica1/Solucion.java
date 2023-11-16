package acm_practica1;

public class Solucion {
    public double dMin;
    public int indiceP1;
    public int indiceP2;
    public double time;
    public int nComparaciones;

    public Solucion() {
    }

    public Solucion(double dMin, int indiceP1, int indiceP2, double time, int nComparaciones) {
        this.dMin = dMin;
        this.indiceP1 = indiceP1;
        this.indiceP2 = indiceP2;
        this.time = time;
        this.nComparaciones = nComparaciones;
    }
}
