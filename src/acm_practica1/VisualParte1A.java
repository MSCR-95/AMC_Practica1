/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package acm_practica1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Maria S
 */
class MyTableModel extends DefaultTableModel {

    public MyTableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Hacer que las celdas no sean editables
    }
}

public class VisualParte1A extends javax.swing.JFrame {

    private List<Punto> listaCiudades;
    private String nombreTSP;
    private int talla;
    private final GeneraPuntos GP = new GeneraPuntos();
    private boolean casoPeor = false;
    private MyTableModel tableModel;

    /**
     * Creates new form VisualParte1A
     */
    public VisualParte1A() {
        GP.rellenarPuntos(1, false);
        this.listaCiudades = GP.getListaPuntos();
        initComponents();

    }

    // Inicializar la tabla
    private void initTableModel() {
        Object[] columnNames = {"Talla", "Tiempo S1", "Tiempo S2", "Distancia S1", "Distancia S2"};
        tableModel = new MyTableModel(columnNames, 0); // 0 indica que inicialmente no hay filas
        table.setModel(tableModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 =  new javax.swing.JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarPuntos(g, listaCiudades, this.getWidth(), this.getHeight());
            }
        };
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane(table);
        table = new javax.swing.JTable();
        introducir_nombre_TSP = new javax.swing.JTextField();
        cargarTSPbutton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        introducir_talla = new javax.swing.JTextField();
        botonGenerar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        comparar2Button = new javax.swing.JButton();
        casoPeorButton = new javax.swing.JRadioButton();
        Algoritmo1ComboBox = new javax.swing.JComboBox<>();
        dibujarPuntosButton = new javax.swing.JButton();
        Algoritmo2ComboBox = new javax.swing.JComboBox<>();
        CompararTodasButton = new javax.swing.JButton();
        ComprobarTodasButton = new javax.swing.JButton();
        puntosMasGrandeButton = new javax.swing.JButton();
        guardarTSPButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 356, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );

        jLabel1.setText("Nombre .tsp");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table);

        introducir_nombre_TSP.setText("Nombre...");

        cargarTSPbutton.setText("Cargar");
        cargarTSPbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarTSPbuttonActionPerformed(evt);
            }
        });

        jLabel2.setText("Aleatorio");

        introducir_talla.setText("Talla");
        introducir_talla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                introducir_tallaActionPerformed(evt);
            }
        });

        botonGenerar.setText("Generar");
        botonGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGenerarActionPerformed(evt);
            }
        });

        jLabel3.setText("Estrategias:");

        comparar2Button.setText("Comparar 2");
        comparar2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comparar2ButtonActionPerformed(evt);
            }
        });

        casoPeorButton.setText("Caso peor");
        casoPeorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                casoPeorButtonActionPerformed(evt);
            }
        });

        Algoritmo1ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Exhaustivo", "Exhaustivo con poda", "Divide y venceras", "Divide y venceras mejorado" }));
        Algoritmo1ComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Algoritmo1ComboBoxActionPerformed(evt);
            }
        });

        dibujarPuntosButton.setText("Dibujar puntos");
        dibujarPuntosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dibujarPuntosButtonActionPerformed(evt);
            }
        });

        Algoritmo2ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Exhaustivo", "Exhaustivo con poda", "Divide y venceras", "Divide y venceras mejorado" }));

        CompararTodasButton.setText("Comparar todas");
        CompararTodasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompararTodasButtonActionPerformed(evt);
            }
        });

        ComprobarTodasButton.setText("Comprobar todas");
        ComprobarTodasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComprobarTodasButtonActionPerformed(evt);
            }
        });

        puntosMasGrandeButton.setText("+");
        puntosMasGrandeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puntosMasGrandeButtonActionPerformed(evt);
            }
        });

        guardarTSPButton.setText("Guardar tsp");
        guardarTSPButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarTSPButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(17, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 851, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(guardarTSPButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(introducir_nombre_TSP, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(introducir_talla, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(42, 42, 42))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(ComprobarTodasButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(66, 66, 66)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(comparar2Button)
                                            .addComponent(Algoritmo1ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(66, 66, 66)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Algoritmo2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cargarTSPbutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(botonGenerar, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                            .addComponent(dibujarPuntosButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(casoPeorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(puntosMasGrandeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(CompararTodasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(introducir_nombre_TSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cargarTSPbutton))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(introducir_talla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(botonGenerar)
                                .addComponent(casoPeorButton))
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(guardarTSPButton)
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dibujarPuntosButton)
                            .addComponent(ComprobarTodasButton)
                            .addComponent(puntosMasGrandeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Algoritmo1ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Algoritmo2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comparar2Button)
                            .addComponent(CompararTodasButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void introducir_tallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_introducir_tallaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_introducir_tallaActionPerformed

    private void comparar2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comparar2ButtonActionPerformed
        comparar2Estrategias();
    }//GEN-LAST:event_comparar2ButtonActionPerformed

    private void casoPeorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_casoPeorButtonActionPerformed
        if (casoPeorButton.isSelected()) {
            casoPeor = true;
        } else {
            casoPeor = false;
        }


    }//GEN-LAST:event_casoPeorButtonActionPerformed

    private void dibujarPuntosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dibujarPuntosButtonActionPerformed
        //jPanel1.add(this);
        jPanel1.repaint();
        //dibujarPuntos(g, listaCiudades, this.getWidth(), this.getHeight());
    }//GEN-LAST:event_dibujarPuntosButtonActionPerformed

    private void Algoritmo1ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Algoritmo1ComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Algoritmo1ComboBoxActionPerformed
    //Cargamos el TSP en la lista
    private void cargarTSPbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarTSPbuttonActionPerformed
        try {
            listaCiudades.clear();  //vaciamos la lista antes de usarla
            nombreTSP = introducir_nombre_TSP.getText();
            GP.LeePuntos("src/data/" + nombreTSP + "/" + nombreTSP);
            this.listaCiudades = GP.getListaPuntos();
            //imprimirLista(listaCiudades);
        } catch (Exception e) {
            System.out.println("Error: el archivo tsp no existe"); 
        }

    }//GEN-LAST:event_cargarTSPbuttonActionPerformed

    private void botonGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGenerarActionPerformed
        try {
            listaCiudades.clear();  //vaciamos la lista antes de usarla
            talla = Integer.parseInt(introducir_talla.getText());
            GP.rellenarPuntos(talla, casoPeor);
            this.listaCiudades = GP.getListaPuntos();
            //imprimirLista(listaCiudades);
        } catch (Exception e) {
            System.out.println("Error: talla incorrecta");
        }

    }//GEN-LAST:event_botonGenerarActionPerformed

    private void CompararTodasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompararTodasButtonActionPerformed
        compararTodasEstrategias();
    }//GEN-LAST:event_CompararTodasButtonActionPerformed

    private void ComprobarTodasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComprobarTodasButtonActionPerformed
        // TODO add your handling code here:
        comprobarEstrategias(listaCiudades);
    }//GEN-LAST:event_ComprobarTodasButtonActionPerformed

    private void puntosMasGrandeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puntosMasGrandeButtonActionPerformed
        puntosMasGrandes();
    }//GEN-LAST:event_puntosMasGrandeButtonActionPerformed

    private void guardarTSPButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarTSPButtonActionPerformed
        talla = Integer.parseInt(introducir_talla.getText());
        String nombre = "dataSet"+talla;
        GP.CreaTSP(nombre);
        GP.EscribeTSP(nombre);
    }//GEN-LAST:event_guardarTSPButtonActionPerformed
    
    //Formatear a 8 decimales
    public double decimales8(double numero) {
        //long decimales = 10000000000;
        numero = Math.round(numero * 100000000) / 100000000d;
        return numero;
    }
    
    //Para pruebas
    private static void imprimirLista(List<Punto> puntos) {
        for (Punto punto : puntos) {
            System.out.println(punto);
        }
    }

    public Punto getPuntoPorIndice(List<Punto> puntos, int ind) {
        Punto puntoIndice = null;
        for (int i = 0; i < puntos.size(); i++) {
            if (puntos.get(i).getIndice() == ind) {
                puntoIndice = puntos.get(i);
            }
        }
        return puntoIndice;
    }

    //--COMPROBAR TODOS LOS ALGORITMOS CON UN DATASET--//
    public void comprobarEstrategias(List<Punto> puntos) {

        Algoritmos algoritmo = new Algoritmos();
        Solucion sExha = new Solucion();
        Solucion sExhaPoda = new Solucion();
        Solucion sDyV = new Solucion();
        Solucion sDyVMejorado = new Solucion();

        //Nos aseguramos de que cada algoritmo trabaje con una copia de la lista
        List<Punto> puntosExha = puntos;
        List<Punto> puntosExhaPoda = puntos;
        List<Punto> puntosDyV = puntos;
        List<Punto> puntosDyVMej = puntos;

        //Inicializamos la tabla
        Object[] columnNames = {"Estrategia", "Punto 1", "Punto 2", "Distancia", "Calculadas", "Tiempo(mseg)"};
        tableModel = new MyTableModel(columnNames, 0); // 0 indica que inicialmente no hay filas
        table.setModel(tableModel);

        //resultado = busquedaExhaustiva(puntos, 0, puntos.size() - 1);
        sExha = algoritmo.busquedaExhaustiva(puntosExha, 0, puntos.size() - 1, 0);
        sExhaPoda = algoritmo.busquedaConPoda(puntosExhaPoda);
        sDyV = algoritmo.busquedaDivideYVenceras(puntosDyV);
        sDyVMejorado = algoritmo.busquedaDivideYVencerasMejorado(puntosDyVMej);
        
        System.out.println("Indice de Exhaustivo");
        System.out.println(sExha.indiceP1);

        ParDePuntos ppExha = new ParDePuntos(getPuntoPorIndice(puntos, sExha.indiceP1 + 1), getPuntoPorIndice(puntos, sExha.indiceP2 + 1));
        ParDePuntos ppExhaPoda = new ParDePuntos(getPuntoPorIndice(puntos, sExhaPoda.indiceP1), getPuntoPorIndice(puntos, sExhaPoda.indiceP2));
        ParDePuntos ppDyV = new ParDePuntos(getPuntoPorIndice(puntos, sDyV.indiceP1), getPuntoPorIndice(puntos, sDyV.indiceP2));
        ParDePuntos ppDyVMejor = new ParDePuntos(getPuntoPorIndice(puntos, sDyVMejorado.indiceP1), getPuntoPorIndice(puntos, sDyVMejorado.indiceP2));

        tableModel.addRow(new Object[]{"Exhaustiva", ppExha.getP1(), ppExha.getP2(), decimales8(sExha.dMin), sExha.nComparaciones, sExha.time});
        tableModel.addRow(new Object[]{"Exhaustiva con poda", ppExhaPoda.getP1(), ppExhaPoda.getP2(), decimales8(sExhaPoda.dMin), sExhaPoda.nComparaciones, sExhaPoda.time});
        tableModel.addRow(new Object[]{"Divide y venceras", ppDyV.getP1(), ppDyV.getP2(), decimales8(sDyV.dMin), sDyV.nComparaciones, sDyV.time});
        tableModel.addRow(new Object[]{"Divi y venceras mejorado", ppDyVMejor.getP1(), ppDyVMejor.getP2(), decimales8(sDyVMejorado.dMin), sDyVMejorado.nComparaciones, sDyVMejorado.time});
        //System.out.println(pdExha.distancia());
        System.out.println("Estrategia       Punto 1                    Punto 2                      distancias           Calculadas        tiempo ");
        System.out.println("Exhaustiva" + "       " + ppExha.getP1() + "        " + ppExha.getP2() + "        " + sExha.dMin + "       " + sExha.nComparaciones + "       " + sExha.time);
        System.out.println("Exhaustiva" + "       " + ppExhaPoda.getP1() + "        " + ppExhaPoda.getP2() + "        " + sExhaPoda.dMin + "       " + sExhaPoda.nComparaciones + "       " + sExhaPoda.time);
        System.out.println("Exhaustiva" + "       " + ppDyV.getP1() + "        " + ppDyV.getP2() + "        " + sDyV.dMin + "       " + sDyV.nComparaciones + "       " + sDyV.time);
        System.out.println("Exhaustiva" + "       " + ppDyVMejor.getP1() + "        " + ppDyVMejor.getP2() + "        " + sDyVMejorado.dMin + "       " + sDyVMejorado.nComparaciones + "       " + sDyVMejorado.time);

    }

    //--COMPARAR DOS ALGORITMOS--//
    public void comparar2Estrategias() {
        //initTableModel();
        String nombreAlg1 = Algoritmo1ComboBox.getSelectedItem().toString();
        String nombreAlg2 = Algoritmo2ComboBox.getSelectedItem().toString();
        Solucion s1 = new Solucion();
        Solucion s2 = new Solucion();

        String nAlg1 = "";  //Para pruebas
        String nAlg2 = "";  //Para pruebas

        //Inicializamos la tabla
        Object[] columnNames = {"", nombreAlg1, nombreAlg2, nombreAlg1, nombreAlg2};
        tableModel = new MyTableModel(columnNames, 0); // 0 indica que inicialmente no hay filas
        table.setModel(tableModel);
        Object[] columnNames2 = {"Talla", "Tiempo", "Tiempo", "Distancias", "Distancias"};
        tableModel.addRow(columnNames2);

        int numIteraciones = 5;
        int tallaInicial = 1000;
        Algoritmos algoritmo = new Algoritmos();
        GeneraPuntos GP;

        for (int i = 0; i < numIteraciones; i++) {
            int talla = tallaInicial + i * 1000;
            GP = new GeneraPuntos();
            GP.rellenarPuntos(talla, casoPeor);
            List<Punto> puntos = GP.getListaPuntos();
            //Nos aseguramos de que cada algoritmo trabaje con una copia de la lista
            List<Punto> puntosAlg1 = puntos;
            List<Punto> puntosAlg2 = puntos;

            //Elegimos los algoritmos segun la seleccion de los ComboBoxs
            switch (nombreAlg1) {
                case "Exhaustivo":
                    s1 = algoritmo.busquedaExhaustiva(puntosAlg1, 0, puntosAlg1.size() - 1, 0);
                    nAlg1 = "Exhaus";
                    break;
                case "Exhaustivo con poda":
                    s1 = algoritmo.busquedaConPoda(puntosAlg1);
                    nAlg1 = "Exhaust Poda";
                    break;
                case "Divide y venceras":
                    s1 = algoritmo.busquedaDivideYVenceras(puntosAlg1);
                    nAlg1 = "DyV";
                    break;
                case "Divide y venceras mejorado":
                    s1 = algoritmo.busquedaDivideYVencerasMejorado(puntosAlg1);
                    nAlg1 = "DyVMejora";
                    break;
            }
            switch (nombreAlg2) {
                case "Exhaustivo":
                    s2 = algoritmo.busquedaExhaustiva(puntosAlg2, 0, puntosAlg2.size() - 1, 0);
                    nAlg2 = "Exhaus";
                    break;
                case "Exhaustivo con poda":
                    s2 = algoritmo.busquedaConPoda(puntosAlg2);
                    nAlg2 = "Exhaus Poda";
                    break;
                case "Divide y venceras":
                    s2 = algoritmo.busquedaDivideYVenceras(puntosAlg2);
                    nAlg2 = "DyV";
                    break;
                case "Divide y venceras mejorado":
                    s2 = algoritmo.busquedaDivideYVencerasMejorado(puntosAlg2);
                    nAlg2 = "DyVMejora";
                    break;
            }
            // Agrega los datos a la tabla
            tableModel.addRow(new Object[]{talla, s1.time, s2.time, s1.nComparaciones, s2.nComparaciones});

            //  PRUEBAS
            System.out.println("Nombre Algoritmo 1: " + nAlg1 + " Nombre algoritmo 2: " + nAlg2);
            System.out.println("talla      tiempo(mseg) S1         tiempo(mseg)S2        Distancia_S1         Distancia_S2");
            System.out.println(talla + "       " + s1.time + "                    " + s2.time + "             " + s1.nComparaciones + "                " + s2.nComparaciones);
            System.out.println("**********************************************");
        }
    }

    //--COMPARAR TODOS LOS ALGORITMOS--//
    public void compararTodasEstrategias() {
        //initTableModel();

        Solucion sExha = new Solucion();
        Solucion sExhaPoda = new Solucion();
        Solucion sDyV = new Solucion();
        Solucion sDyVMejorado = new Solucion();

        //Inicializamos la tabla
        Object[] columnNames = {"", "Exhaustivo", "Exhaustivo con poda", "Divide y venceras", "Divide y venceras mejorado"};
        tableModel = new MyTableModel(columnNames, 0); // 0 indica que inicialmente no hay filas
        table.setModel(tableModel);

        int numIteraciones = 5;
        int tallaInicial = 1000;
        Algoritmos algoritmo = new Algoritmos();
        GeneraPuntos GP;
        Object[] columnNames2 = {"Talla", "tiempo(mseg)", "tiempo(mseg)", "tiempo(mseg)", "tiempo(mseg)"};
        tableModel.addRow(columnNames2);

        for (int i = 0; i < numIteraciones; i++) {
            int talla = tallaInicial + i * 1000;
            GP = new GeneraPuntos();
            GP.rellenarPuntos(talla, casoPeor);
            List<Punto> puntos = GP.getListaPuntos();

            //Nos aseguramos de que cada algoritmo trabaje con una copia de la lista
            List<Punto> puntosExha = puntos;
            List<Punto> puntosExhaPoda = puntos;
            List<Punto> puntosDyV = puntos;
            List<Punto> puntosDyVMej = puntos;

            sExha = algoritmo.busquedaExhaustiva(puntosExha, 0, puntosExha.size() - 1, 0);
            sExhaPoda = algoritmo.busquedaConPoda(puntosExhaPoda);
            sDyV = algoritmo.busquedaDivideYVenceras(puntosDyV);
            sDyVMejorado = algoritmo.busquedaDivideYVencerasMejorado(puntosDyVMej);

            // Agrega los datos a la tabla
            tableModel.addRow(new Object[]{talla, sExha.time, sExhaPoda.time, sDyV.time, sDyVMejorado.time});

            // PRUEBAS
            System.out.println("Exhaustivo, Exahustivo con poda, Divide y Venceras, Divide y venceras mejorado");
            System.out.println("talla      tiempo(mseg) S1         tiempo(mseg)S2        Distancia_S1         Distancia_S2");
            System.out.println(talla + "       " + sExha.time + "          " + sExhaPoda.time + "             " + sDyV.time + "              " + sDyVMejorado.time);
            System.out.println("**********************************************");
        }
    }

    private void puntosMasGrandes() {

        JFrame frame = new JFrame("Lista de Puntos");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //no necesitamos la lista original porque las coordenas de los puntos son la misma que en la ruta
                //dibujarPuntos(g, listaCiudades, TSP.TSPBidireccional(listaCiudades), this.getWidth(), this.getHeight());
                //dibujarPuntos(g, listaCiudades, ParDePuntos pdMini, this.getWidth(), this.getHeight());
                dibujarPuntos(g, listaCiudades, this.getWidth(), this.getHeight());
            }
        };

        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private static void dibujarPuntos(Graphics g, List<Punto> ruta, int panelAncho, int panelAlto) {

        Algoritmos algoritmos = new Algoritmos(ruta);
        //Podemos usar cualquier algoritmo, ya que la lista de puntos es la misma, y los puntos con la distancia minima tambien
        //Usamos el con poda porque suele ser muy rapido
        Solucion s = algoritmos.busquedaConPoda(ruta);

        try {
            //Sacamos los dos puntos mas cercanos de la solucion
            Punto p1 = algoritmos.getPuntoPorIndice(s.indiceP1);
            Punto p2 = algoritmos.getPuntoPorIndice(s.indiceP2);

            double maxX = ruta.stream().mapToDouble(Punto::getX).max().orElse(0);
            double maxY = ruta.stream().mapToDouble(Punto::getY).max().orElse(0);

            double factorEscala = Math.min(panelAncho / maxX, panelAlto / maxY);

            //Dibuja los puntos
            for (int i = 0; i < ruta.size(); i++) {
                Punto punto = ruta.get(i);
                int x = (int) (punto.getX() * factorEscala);
                int y = (int) (punto.getY() * factorEscala);

                g.setColor(Color.BLACK);
                g.fillOval(x, y, 5, 5);
            }

            //linea entre los dos puntos
            g.setColor(Color.RED);
            int primerX = (int) (p1.getX() * factorEscala);
            int primerY = (int) (p1.getY() * factorEscala);
            int ultimoX = (int) (p2.getX() * factorEscala);
            int ultimoY = (int) (p2.getY() * factorEscala);

            g.drawLine(primerX, primerY, ultimoX, ultimoY);
            //Pintamos de rojo los dos puntos mas cercanos
            g.fillOval(primerX, primerY, 5, 5);
            g.fillOval(ultimoX, ultimoY, 5, 5);

        } catch (Exception e) {
            System.out.println("Aun no se puede dibujar");
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Algoritmo1ComboBox;
    private javax.swing.JComboBox<String> Algoritmo2ComboBox;
    private javax.swing.JButton CompararTodasButton;
    private javax.swing.JButton ComprobarTodasButton;
    private javax.swing.JButton botonGenerar;
    private javax.swing.JButton cargarTSPbutton;
    private javax.swing.JRadioButton casoPeorButton;
    private javax.swing.JButton comparar2Button;
    private javax.swing.JButton dibujarPuntosButton;
    private javax.swing.JButton guardarTSPButton;
    private javax.swing.JTextField introducir_nombre_TSP;
    private javax.swing.JTextField introducir_talla;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton puntosMasGrandeButton;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
