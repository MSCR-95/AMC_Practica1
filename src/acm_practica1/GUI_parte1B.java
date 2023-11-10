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
import javax.swing.SwingUtilities;

/**
 *
 * @author Maria S
 */
public class GUI_parte1B extends javax.swing.JFrame {

    /**
     * Creates new form parte1bGUI
     */
    public GUI_parte1B() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(800, 600));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(350, 350));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 363, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 350));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 363, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(266, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    /*
    private static final int VENTANA_TAMANO = 600;

    private List<Punto> listaCiudades;

    public GUI_parte1B(List<Punto> listaCiudades) {
        
        //this.listaCiudades = listaCiudades;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(VENTANA_TAMANO, VENTANA_TAMANO);

        Algoritmos1B TSP = new Algoritmos1B();
        
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarPuntos(g, listaCiudades, TSP.TSPBidireccional(listaCiudades),this.getWidth(), this.getHeight());
            }
        };

        add(panel);
    }

private static void dibujarPuntos(Graphics g, List<Punto> puntos,List<Punto> ruta, int panelAncho, int panelAlto) {
        double maxX = puntos.stream().mapToDouble(Punto::getX).max().orElse(0);
        double maxY = puntos.stream().mapToDouble(Punto::getY).max().orElse(0);

        double factorEscala = Math.min(panelAncho / maxX, panelAlto / maxY);

       // Dibujar líneas rojas entre los puntos de la ruta
        if (ruta != null && !ruta.isEmpty()) {
            g.setColor(Color.RED);
            for (int i = 0; i < ruta.size() - 1; i++) {
                Punto punto1 = ruta.get(i);
                Punto punto2 = ruta.get(i + 1);

                int x1 = (int) (punto1.getX() * factorEscala);
                int y1 = (int) (punto1.getY() * factorEscala);
                int x2 = (int) (punto2.getX() * factorEscala);
                int y2 = (int) (punto2.getY() * factorEscala);

                g.drawLine(x1, y1, x2, y2);
            }
        }

        // Dibujar puntos
        g.setColor(Color.BLACK);
        for (Punto punto : puntos) {
            int x = (int) (punto.getX() * factorEscala);
            int y = (int) (punto.getY() * factorEscala);
            g.fillOval(x, y, 5, 5);
        }
    }

    public static void main(String args[]) {
        GeneraPuntos GP = new GeneraPuntos();
        GP.LeePuntos("src/data/berlin52.tsp/berlin52.tsp");
        List<Punto> listaCiudades = GP.getListaPuntos();

        SwingUtilities.invokeLater(() -> {
            GUI_parte1B frame = new GUI_parte1B(listaCiudades);
            frame.setVisible(true);
        });
    }
    

   */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
