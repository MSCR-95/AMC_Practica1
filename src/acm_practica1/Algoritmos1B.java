package acm_practica1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JTextArea;

/**
 *
 * @author Maria S
 */
public class Algoritmos1B {

    private int rand;
    private double tiempoUnidirec = 0.0;
    private double tiempoBidirec = 0.0;

    public double decimales4(double numero) {
        numero = Math.round(numero * 10000) / 10000d;
        return numero;
    }

    public int getRand() {
        return this.rand;
    }

    public void calcularRutas(List<Punto> ciudades) {

        GeneraPuntos GP = new GeneraPuntos();
        // Llamada a la función para generar un número aleatorio entre 1 y 100
        this.rand = generarNumeroAleatorio(ciudades.size() - 1);
        //this.rand = 0;

        //Calculo de los costos de los caminos
        System.out.println("Ruta Unidireccional");
        List<Punto> rutaUnidireccional = TSPUnidireccional(ciudades, rand);
        System.out.println("SOLUTION: " + calcularCostoTotal(rutaUnidireccional));
        imprimirRuta(rutaUnidireccional);

        GP.CreaTSP("Unidireccional");
        EscribeTSP_1B("Unidireccional", rutaUnidireccional);

        System.out.println("\nRuta Bidireccional");
        List<Punto> rutaBidireccional = TSPBidireccional(ciudades, rand);
        System.out.println("SOLUTION: " + calcularCostoTotal(rutaBidireccional));
        imprimirRuta(rutaBidireccional);

        GP.CreaTSP("Bidireccional");
        EscribeTSP_1B("Bidireccional", rutaUnidireccional);
        System.out.println("---------------------------------");

    }

    public void comprobacionEmpirica() {

        GeneraPuntos GP = new GeneraPuntos();
        List<Punto> ciudades = new ArrayList<>();
        int mejorUni = 0;
        int mejorBi = 0;
        int iguales = 0;
        int talla = 0;
        //Para talla 5000 -> i=10 
        for (int i = 0; i < 2; i++) {
            talla += 500;
            GP.rellenarPuntos(talla, false);
            // j es el numero de ejecuciones diferentes
            for (int j = 0; j < 100; j++) {
                //numero aleatorio para la ciudad inicial
                this.rand = generarNumeroAleatorio(GP.puntos.size() - 1);
                ciudades = GP.getListaPuntos();
                //Si el costo de unidireccional es mayo, el bidireccional es mejor
                if (calcularCostoTotal(TSPUnidireccional(ciudades, rand)) > calcularCostoTotal(TSPBidireccional(ciudades, rand))) {
                    mejorBi++;
                } else if (calcularCostoTotal(TSPUnidireccional(ciudades, rand)) < calcularCostoTotal(TSPBidireccional(ciudades, rand))) {
                    mejorUni++;
                } else {
                    iguales++;
                }
            }
            System.out.println("Talla: " + talla + ", Mejor [Uni:(" + mejorUni + "), Bi:(" + mejorBi + "), Iguales:(" + iguales + ")]");
            System.out.println("Tiempo medio ejecucion [Uni: " + decimales4(tiempoUnidirec / 100) + ", Bi: " + decimales4(tiempoBidirec / 100) + "]\n");
            mejorBi = mejorUni = iguales = 0;
            tiempoBidirec = tiempoUnidirec = 0.0;
        }
    }

    public List<Punto> TSPUnidireccional(List<Punto> ciudades, int primeraCiudad) {

        double startTime = System.nanoTime();

        List<Punto> ruta = new ArrayList<>();

        Punto ciudadActual = ciudades.get(primeraCiudad);
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
        double endTime = System.nanoTime();
        tiempoUnidirec = (endTime - startTime) / 1e6;
        return ruta;
    }

    public List<Punto> TSPBidireccional(List<Punto> ciudades, int primeraCiudad) {
        double startTime = System.nanoTime();

        List<Punto> ruta = new ArrayList<>();
        //Punto ciudadActual = ciudades.get(0);
        Punto ciudadActual = ciudades.get(primeraCiudad);
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
        double endTime = System.nanoTime();
        tiempoBidirec = (endTime - startTime) / 1e6;
        return ruta;
    }

    public static void imprimirRuta(List<Punto> ruta) {
        for (Punto ciudad : ruta) {
            System.out.print(ciudad.getIndice() + ",");
        }

        System.out.println(ruta.get(0).getIndice()); // Regresa al inicio
        /*
        //Muestra todos los vertices, con los pesos de las aristas
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

    //Guardar en un TSP
    public void EscribeTSP_1B(String NombreFile, List<Punto> ruta) {
        try {
            FileWriter myWriter = new FileWriter("src/data/intentos/" + NombreFile + ".tsp");
            myWriter.write("NAME: " + NombreFile + ".tsp\n");
            myWriter.write("TYPE: TSP\n");
            myWriter.write("DIMENSION: " + ruta.size() + "\n");
            myWriter.write("SOLUTION: " + calcularCostoTotal(ruta) + "\n");
            myWriter.write("TOUR_SECTION\n");

            for (Punto ciudad : ruta) {
                myWriter.write(ciudad.getIndice() + ",");
            }

            myWriter.write(ruta.get(0).getIndice() + "\n");

            int pesoArista;
            for (int i = 0; i < ruta.size() - 1; i++) {
                Punto ciudadActual = ruta.get(i);
                Punto ciudadSiguiente = ruta.get(i + 1);
                pesoArista = (int) Punto.distancia(ciudadActual, ciudadSiguiente);
                myWriter.write(pesoArista + " - " + ciudadActual.getIndice() + "," + ciudadSiguiente.getIndice() + "\n");
            }
            //Mostra peso regreso a la ciudad de inicio
            pesoArista = (int) Punto.distancia(ruta.get(ruta.size() - 1), ruta.get(0));
            myWriter.write(pesoArista + " - " + ruta.get(ruta.size() - 1).getIndice() + "," + ruta.get(0).getIndice() + "\n");

            myWriter.write("EOF");

            myWriter.close();
            System.out.println("Archivo escrito con exito.");
        } catch (IOException e) {
            System.out.println("Error escribiendo.");
            e.printStackTrace();
        }
    }

    // Función para generar un número aleatorio entre 1 y el número pasado por parámetro
    private static int generarNumeroAleatorio(int maximo) {
        Random rand = new Random();
        return rand.nextInt(maximo) + 1; // La expresión rand.nextInt(maximo) genera un número entre 0 y maximo - 1
    }

    //-----------OPCIONAL------------//
    public void calcularSolucionOptima(List<Punto> ciudades) {

        if (ciudades.size() > 12) {
            System.out.println("Para calcular la ruta optima, la lista no puede tener mas de 12 puntos.");
            return;
        }

        double startTime = System.nanoTime();
        List<Punto> mejorRuta = null;
        int mejorCosto = Integer.MAX_VALUE;

        List<List<Punto>> permutaciones = generarPermutaciones(ciudades);

        for (List<Punto> ruta : permutaciones) {
            int costoActual = calcularCostoTotal(ruta);
            if (costoActual < mejorCosto) {
                mejorCosto = costoActual;
                mejorRuta = new ArrayList<>(ruta);
            }
        }

        double endTime = System.nanoTime();
        double tiempoFuerzaBruta = (endTime - startTime) / 1e6;

        System.out.println("Ruta Optima (Fuerza Bruta)");
        imprimirRuta(mejorRuta);
        System.out.println("SOLUTION: " + mejorCosto);
        System.out.println("Tiempo de ejecucion (Fuerza Bruta): " + decimales4(tiempoFuerzaBruta) + " ms");
    }

    private List<List<Punto>> generarPermutaciones(List<Punto> ciudades) {
        List<List<Punto>> permutaciones = new ArrayList<>();
        generarPermutacionesRec(ciudades, 0, permutaciones);
        return permutaciones;
    }

    private void generarPermutacionesRec(List<Punto> ciudades, int indice, List<List<Punto>> permutaciones) {
        if (indice == ciudades.size()) {
            permutaciones.add(new ArrayList<>(ciudades));
            return;
        }

        for (int i = indice; i < ciudades.size(); i++) {
            intercambiar(ciudades, indice, i);
            generarPermutacionesRec(ciudades, indice + 1, permutaciones);
            intercambiar(ciudades, indice, i); // Deshacer el intercambio para volver al estado original
        }
    }

    private void intercambiar(List<Punto> ciudades, int i, int j) {
        Punto temp = ciudades.get(i);
        ciudades.set(i, ciudades.get(j));
        ciudades.set(j, temp);
    }
}
