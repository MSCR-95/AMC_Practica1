package acm_practica1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneraPuntos {

    public List<Punto> puntos = new ArrayList<>();
    
    //Añadir cargarPuntos() para cargar los puntos desde un fichero con extension: .tsp
    
    public void crearPuntosAleatorios(int cantidadPuntos, int minimo, int maximo) {

        Random rand = new Random();
        int ramdomX, ramdomY;

        for (int i = 0; i < cantidadPuntos; i++) {

            ramdomX = minimo + rand.nextInt((maximo - minimo) + 1);
            ramdomY = minimo + rand.nextInt((maximo - minimo) + 1);

            Punto p1 = new Punto(ramdomX, ramdomY);

            puntos.add(p1);
            //System.out.println(" " + p1);
        }
    }

    public void verPuntos() {
        
        System.out.println("------------MOSTRAR LOS PUNTOS-----------");
        for (int i = 0; i < puntos.size(); i++) {
            System.out.println(" " + puntos.get(i));
        }
    }

    public List<Punto> getListaPuntos() {
        return puntos;
    }
    
}
