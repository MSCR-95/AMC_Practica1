/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acm_practica1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Maria S
 */
public class Algoritmos {

    public List<Punto> puntos = new ArrayList<>();
    private DecimalFormat df = new DecimalFormat("#.########");

    private int nComparacionesDyV = 0;
    private Punto Px = null;
    private Punto Py = null;

    //AÑADIDO IZQUIERDA Y DERECHA PARA USAR ESTE ALGORITMO EN EL DIVIDE Y VENCERAS
    public double busquedaExaustiva(List<Punto> punto, int izquierda, int derecha) {

        double distancia = -1;
        double distanciaMin = Double.POSITIVE_INFINITY;
        Punto x = null;
        Punto y = null;
        int nComparaciones = 0;

        for (int i = izquierda; i < derecha; i++) {
            for (int j = i + 1; j < derecha; j++) {
                //Controlamos que un punto no calcule la distancia con el mismo
                ParDePuntos dosPuntos = new ParDePuntos(punto.get(i), punto.get(j));
                distancia = dosPuntos.distancia();
                nComparaciones++;
                //FILTRAMOS
                if (distancia < distanciaMin) {
                    distanciaMin = distancia;
                    x = punto.get(i);
                    y = punto.get(j);
                }
                //AQUI NOS MUESTRA TODOS LOS PUNTOS Y TODAS SUS DISTANCIAS
                //System.out.println("Punto " + i + ": " + punto.get(i) + "Punto " + j + ": " + punto.get(j) + "Distancia: " + distancia + "\n");
            }
        }
        //Mostramos los puntos, la distancia minima entre ellos y el numero de comparaciones realizadas
        //System.out.println("X: " + x + "Y: " + y + "Distancia: " + df.format(distanciaMin));
        //System.out.println("Numero de comparaciones: " + nComparaciones);
        return distanciaMin;
    }

    //No sirve, tenemos que implementar quickSort
    private void ordenarPuntosPorXQuickSort(List<Punto> punto) {
        //Ordenar ArrayList de punto por X en orden ascendente usando un comparador personalizado
        Collections.sort(punto, new Comparator<Punto>() {
            @Override
            public int compare(Punto p1, Punto p2) {
                return Double.compare(p1.getX(), p2.getX());
            }
        });
    }

    //No sirve, tenemos que implementar quickSort
    private void ordenarPuntosPorYQuickSort(List<Punto> punto) {
        //Ordenar ArrayList de punto por X en orden ascendente usando un comparador personalizado
        Collections.sort(punto, new Comparator<Punto>() {
            @Override
            public int compare(Punto p1, Punto p2) {
                return Double.compare(p1.getY(), p2.getY());
            }
        });
    }

    public double busquedaConPoda(List<Punto> punto) {
        double distancia = -1;
        double distanciaMin = Double.POSITIVE_INFINITY;
        double distanciaX = 0;
        Punto x = null;
        Punto y = null;
        int nComparaciones = 0;
        //Ordenamos la lista por la x
        ordenarPuntosPorXQuickSort(punto);

        for (int i = 0; i < punto.size(); i++) {
            int j = i + 1;
            Boolean alarma = false;
            while (j < punto.size() && !alarma) {
                distanciaX = Math.abs(punto.get(j).getX() - punto.get(i).getX());
                // Poda: no es necesario seguir con puntos más lejos en la coordenada x.
                if (distanciaX >= distanciaMin) {
                    alarma = true;
                }
                if (!alarma) {
                    nComparaciones++;
                    ParDePuntos dosPuntos = new ParDePuntos(punto.get(i), punto.get(j));
                    distancia = dosPuntos.distancia();
                    if (distancia < distanciaMin) {
                        x = punto.get(i);
                        y = punto.get(j);
                        distanciaMin = distancia;
                    }
                }
                //System.out.println("Punto " + i + ": " + punto.get(i) + "Punto " + j + ": " + punto.get(j) + "Distancia: " + distancia + "\n");
                j++;
            }
        }
        System.out.println("entra en la poda");
        //Mostramos los puntos, la distancia minima entre ellos y el numero de comparaciones realizadas
        System.out.println("X: " + x + "Y: " + y + "Distancia: " + df.format(distanciaMin));
        System.out.println("Numero de comparaciones: " + nComparaciones);
        return distanciaMin;
    }

    /*
    public double busquedaConDyV(List<Punto> punto) {
        double distancia = -1;
        double distanciaMin = Double.POSITIVE_INFINITY;
        Punto x = null;
        Punto y = null;
        int nComparaciones = 0;
        int mitad = punto.size() / 2;
        int j = 0;
        List<Punto> PuntosMitad1= new ArrayList<>();
        List<Punto> PuntosMitad2= new ArrayList<>();

        ordenarPuntosPorXQuickSort(punto);
        
        for (int i = 0; i < punto.size(); i++) {
            if(i<mitad){
                PuntosMitad1.add(punto.get(i));
                System.out.println("mitad 1:");
                    System.out.println("Punto " + (i+1) + ": " + String.format("%n") + "X: " + PuntosMitad1.get(i).getX() + " Y: " + PuntosMitad1.get(i).getY());
            }
            else{
                PuntosMitad2.add(punto.get(i));
                System.out.println("mitad 2:");
                    System.out.println("Punto " + (i+1) + ": " + String.format("%n") + "X: " + PuntosMitad2.get(j).getX() + " Y: " + PuntosMitad2.get(j).getY());
                    j++;
            }
        }
       
        return distanciaMin;
    }
     */
    /**
     * *******************************************
     */
    //CALCULA BIEN LA DISTANCIA
    //HAY QUE COMPROBRAR SI ESTAMOS SUMANDO BIEN EL NUMERO DE COMPARACIONES
    //ENCONTRAR LA MANERA DE QUE MUESTRE LOS PUNTOS
    public double busquedaDivideYVenceras(List<Punto> punto) {
        //Primero, ordena la lista de puntos por la coordenada x.
        ordenarPuntosPorXQuickSort(punto);
        //nComparacionesDyV++;
        return buscarPuntosMasCercanos(punto, 0, punto.size() - 1);
    }

    private double buscarPuntosMasCercanos(List<Punto> punto, int izquierda, int derecha) {
        int x = 0;
        int y = 0;
        if (derecha - izquierda <= 2) {
            //Cuando hay pocos puntos, realiza una búsqueda exhaustiva.
            //nComparacionesDyV++;
            return busquedaExaustiva(punto, izquierda, derecha);
        }

        int mitad = (izquierda + derecha) / 2;
        Punto puntoMitad = punto.get(mitad);

        double distanciaIzquierda = buscarPuntosMasCercanos(punto, izquierda, mitad);
        double distanciaDerecha = buscarPuntosMasCercanos(punto, mitad + 1, derecha);
        double distanciaMinima = Math.min(distanciaIzquierda, distanciaDerecha);

        // Encuentra puntos dentro de la franja central.
        List<Punto> franja = new ArrayList<>();
        for (int i = izquierda; i <= derecha; i++) {
            
            if (Math.abs(punto.get(i).getX() - puntoMitad.getX()) < distanciaMinima) {
                nComparacionesDyV++;
                franja.add(punto.get(i));
            }
        }

        // Ordena los puntos de la franja por coordenada y.
        ordenarPuntosPorYQuickSort(franja);

        // Comprueba si hay puntos más cercanos en la franja.
        for (int i = 0; i < franja.size(); i++) {
            for (int j = i + 1; j < franja.size() && franja.get(j).getY() - franja.get(i).getY() < distanciaMinima; j++) {
                ParDePuntos dosPuntos = new ParDePuntos(franja.get(i), franja.get(j));
                double distancia = dosPuntos.distancia();
                 nComparacionesDyV++;
                if (distancia < distanciaMinima) {
                   

                    distanciaMinima = distancia;
                    //Px = punto.get(i);
                    //Py = punto.get(j);
                    x = i;
                    y = j;
                }
            }
        }
        System.out.println(punto.get(x) + " " + punto.get(y) + "Distancia: " + distanciaMinima);
        System.out.println("Numero de comparaciones: " + nComparacionesDyV);
        return distanciaMinima;
    }

    /*
    private double busquedaExaustiva(List<Punto> punto, int izquierda, int derecha) {
        // Implementa la búsqueda exhaustiva en un subconjunto de puntos.
        // Puedes utilizar el mismo código que tenías para la búsqueda exhaustiva.
        // Asegúrate de ajustar los índices izquierda y derecha.
    }

    private void ordenarPuntosPorXQuickSort(List<Punto> punto) {
        // Ordena ArrayList de punto por X en orden ascendente usando un comparador personalizado
        // Asegúrate de ajustar los índices en el método Collections.sort.
    }

    private void ordenarPuntosPorYQuickSort(List<Punto> punto) {
        // Ordena ArrayList de punto por Y en orden ascendente usando un comparador personalizado
        // Asegúrate de ajustar los índices en el método Collections.sort.
    }
     */
}
