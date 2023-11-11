package acm_practica1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maria S
 */
public class Algoritmos1B {
    
    public List<Punto1B> TSPUnidireccional(List<Punto1B> ciudades) {

        List<Punto1B> ruta = new ArrayList<>();
        Punto1B ciudadActual = ciudades.get(0);
        ruta.add(ciudadActual);

        while (ruta.size() < ciudades.size()) {
            Punto1B ciudadMasCercana = null;
            int distanciaMinima = (int) Double.MAX_VALUE;
            
            //Comprobar el bucle
            for (Punto1B ciudad : ciudades) {
                //Si esa ciudad no ha sido visitada antes
                if (!ruta.contains(ciudad)) {
                    //Calculamos la distancia entre la ciudad actual y las demas(por el bucle)
                    int distancia = (int) Punto1B.distancia(ciudadActual, ciudad);
                    if (distancia < distanciaMinima) {
                        distanciaMinima = distancia;
                        ciudadMasCercana = ciudad;
                    }
                }
            }
            ruta.add(ciudadMasCercana);
            ciudadActual = ciudadMasCercana;
        }
        return ruta;
    }

    public List<Punto1B> TSPBidireccional(List<Punto1B> ciudades) {

        List<Punto1B> ruta = new ArrayList<>();
        Punto1B ciudadActual = ciudades.get(0);
        ruta.add(ciudadActual);

        while (ruta.size() < ciudades.size()) {
            Punto1B ciudadMasCercanaDesdeInicio = null; //Extremo inicial del camino
            Punto1B ciudadMasCercanaDesdeFinal = null;  //Extremo final del camino
            int distanciaMinimaInicio = (int) Double.MAX_VALUE;
            int distanciaMinimaFinal = (int) Double.MAX_VALUE;

            for (Punto1B ciudad : ciudades) {
                if (!ruta.contains(ciudad)) {
                    
                    int distanciaInicio = (int) Punto1B.distancia(ciudadActual, ciudad);
                    int distanciaFinal = (int) Punto1B.distancia(ciudad, ruta.get(0));
                    //Calculamos la menor distancia entre el extremo del Inicio
                    if (distanciaInicio < distanciaMinimaInicio) {
                        distanciaMinimaInicio = distanciaInicio;
                        ciudadMasCercanaDesdeInicio = ciudad;
                    }
                    //Calculamos la menor distancia entre el extremo final
                    if (distanciaFinal < distanciaMinimaFinal) {
                        distanciaMinimaFinal = distanciaFinal;
                        ciudadMasCercanaDesdeFinal = ciudad;
                    }
                }
            }
            // Elegir la ciudad más cercana entre las dos opciones
            if (distanciaMinimaInicio < distanciaMinimaFinal) {
                ruta.add(ciudadMasCercanaDesdeInicio);
                ciudadActual = ciudadMasCercanaDesdeInicio;
            } else {
                ruta.add(0, ciudadMasCercanaDesdeFinal);
            }
        }

        return ruta;
    }
}
