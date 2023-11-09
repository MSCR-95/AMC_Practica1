package acm_practica1;

/**
 *
 * @author Maria S
 */
public class ParDePuntos {

    private Punto p1;
    private Punto p2;

    ParDePuntos(Punto p1, Punto p2) {
        super();
        this.p1 = p1;
        this.p2 = p2;
    }

    public Punto getP1() {
        return p1;
    }

    public void setP1(Punto p1) {
        this.p1 = p1;
    }

    public Punto getP2() {
        return p2;
    }

    public void setP2(Punto p2) {
        this.p2 = p2;
    }
    
    public double distancia() {
        double diferenciaX = this.getP1().getX() - this.getP2().getX();
        double diferenciaY = this.getP1().getY() - this.getP2().getY();
        return Math.sqrt(Math.pow(diferenciaX, 2) + Math.pow(diferenciaY, 2));
    }
    
    /*
    public double distancia(Punto p1, Punto p2) {
        double diferenciaX = p2.getX() - p1.getX();
        double diferenciaY = p2.getY() - p1.getY();
        return Math.sqrt(Math.pow(diferenciaX, 2) + Math.pow(diferenciaY, 2));
    }
    */
}
