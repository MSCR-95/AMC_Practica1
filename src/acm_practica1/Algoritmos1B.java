package acm_practica1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maria S
 */
public class Algoritmos1B {

    public void calcularRutas(List<Punto> ciudades) {

        //Calculo de los costos de los caminos
        System.out.println("\nRuta Unidireccional");
        List<Punto> rutaUnidireccional = TSPUnidireccional(ciudades);
        System.out.println("SOLUTION: " + calcularCostoTotal(rutaUnidireccional));
        imprimirRuta(rutaUnidireccional);

        System.out.println("\nRuta Bidireccional");
        List<Punto> rutaBidireccional = TSPBidireccional(ciudades);
        System.out.println("SOLUTION: " + calcularCostoTotal(rutaBidireccional));
        imprimirRuta(rutaBidireccional);

    }

    public void comprobacionEmpirica() {
        GeneraPuntos GP = new GeneraPuntos();
        List<Punto> ciudades = new ArrayList<>();
        
        int mejorUni = 0;
        int mejorBi = 0;
        //talla 500
        for (int i = 0; i < 5; i++) {
            GP.rellenarPuntos(500, false);
            ciudades = GP.getListaPuntos();
            //Si el costo de unidireccional es mayo, el bidireccional es mejor
            if(calcularCostoTotal(TSPUnidireccional(ciudades)) > calcularCostoTotal(TSPBidireccional(ciudades))){
                mejorBi++;
            }else
                mejorUni++;
        }
        System.out.println("Talla 500, mejores, unidireccional: "+ mejorUni + " Bidirecional: "+mejorBi);

    }

    public List<Punto> TSPUnidireccional(List<Punto> ciudades) {

        List<Punto> ruta = new ArrayList<>();
        Punto ciudadActual = ciudades.get(0);
        ruta.add(ciudadActual);

        while (ruta.size() < ciudades.size()) {
            Punto ciudadMasCercana = null;
            int distanciaMinima = (int) Double.MAX_VALUE;

            //Comprobar el bucle
            for (Punto ciudad : ciudades) {
                //Si esa ciudad no ha sido visitada antes
                if (!ruta.contains(ciudad)) {
                    //Calculamos la distancia entre la ciudad actual y las demas(por el bucle)
                    int distancia = (int) Punto.distancia(ciudadActual, ciudad);
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

    public List<Punto> TSPBidireccional(List<Punto> ciudades) {

        List<Punto> ruta = new ArrayList<>();
        Punto ciudadActual = ciudades.get(0);
        ruta.add(ciudadActual);

        while (ruta.size() < ciudades.size()) {
            Punto ciudadMasCercanaDesdeInicio = null; //Extremo inicial del camino
            Punto ciudadMasCercanaDesdeFinal = null;  //Extremo final del camino
            int distanciaMinimaInicio = (int) Double.MAX_VALUE;
            int distanciaMinimaFinal = (int) Double.MAX_VALUE;

            for (Punto ciudad : ciudades) {
                if (!ruta.contains(ciudad)) {

                    int distanciaInicio = (int) Punto.distancia(ciudadActual, ciudad);
                    int distanciaFinal = (int) Punto.distancia(ciudad, ruta.get(0));
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

    public static void imprimirRuta(List<Punto> ruta) {
        for (Punto ciudad : ruta) {
            System.out.print(ciudad.getIndice() + ",");
        }

        System.out.println(ruta.get(0).getIndice()); // Regresa al inicio
        /*
        int pesoArista;
        for (int i = 0; i < ruta.size() - 1; i++) {
            Punto ciudadActual = ruta.get(i);
            Punto ciudadSiguiente = ruta.get(i + 1);
            pesoArista = (int) Punto.distancia(ciudadActual, ciudadSiguiente);
            System.out.println(pesoArista + " - " + ciudadActual.getIndice() + "," + ciudadSiguiente.getIndice());
        }
        //Mostra peso regreso a la ciudad de inicio
        pesoArista = (int) Punto.distancia(ruta.get(ruta.size() - 1), ruta.get(0));
        System.out.println(pesoArista + " - " + ruta.get(ruta.size() - 1).getIndice() + "," + ruta.get(0).getIndice());
         */
    }

    //AÑADIR A ALGORITMO 1B
    public static int calcularCostoTotal(List<Punto> ruta) {
        int costoTotal = 0;
        int numCiudades = ruta.size();

        for (int i = 0; i < numCiudades - 1; i++) {
            Punto ciudadActual = ruta.get(i);
            Punto ciudadSiguiente = ruta.get(i + 1);
            costoTotal += Punto.distancia(ciudadActual, ciudadSiguiente);
        }
        // Agregar el costo de regreso a la ciudad de inicio
        costoTotal += Punto.distancia(ruta.get(numCiudades - 1), ruta.get(0));

        return costoTotal;
    }
}
