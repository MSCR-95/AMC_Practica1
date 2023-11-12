package acm_practica1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Maria S
 */
public class VisualParte1B extends javax.swing.JFrame {

    private List<Punto> listaCiudades;
    private String nombreTSP;
    private int talla;
    private final GeneraPuntos GP = new GeneraPuntos();
    private final Algoritmos1B TSP = new Algoritmos1B();
    //private final Style defautStyle;
    //private final StyledDocument styledDocument;

    /**
     * Creates new form JFramePruebitas
     */
    public VisualParte1B() {
        initComponents();

        GP.rellenarPuntos(1, false);
        this.listaCiudades = GP.getListaPuntos();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //styledDocument = texto.getStyledDocument();
        //defautStyle = texto.getStyle("default");
        PrintStream printStream = new PrintStream(new CustomOutputStream(texto));
        System.setOut(printStream);
        System.setErr(printStream);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarPuntos(g, TSP.TSPUnidireccional(listaCiudades,TSP.getRand()), this.getWidth(), this.getHeight());
            }
        };
        jPanel2 = new javax.swing.JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarPuntos(g, TSP.TSPBidireccional(listaCiudades,TSP.getRand()), this.getWidth(), this.getHeight());
            }

        };
        text_Unidireccional = new javax.swing.JLabel();
        text_Bidireccional = new javax.swing.JLabel();
        text_CargarTSP = new javax.swing.JLabel();
        botonCargarTSP = new javax.swing.JButton();
        introducir_nombre_TSP = new javax.swing.JTextField();
        text_generar_aleatorio = new javax.swing.JLabel();
        introducir_talla = new javax.swing.JTextField();
        botonGenerar = new javax.swing.JButton();
        text_Estudio_empirico = new javax.swing.JLabel();
        botonComprobar = new javax.swing.JButton();
        textoScroll = new javax.swing.JScrollPane(texto);
        texto = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(900, 600));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 281, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(260, 215));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 321, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        text_Unidireccional.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        text_Unidireccional.setText("Unidireccional");

        text_Bidireccional.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        text_Bidireccional.setText("Bidireccional");

        text_CargarTSP.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        text_CargarTSP.setText("Cargar .tsp");

        botonCargarTSP.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        botonCargarTSP.setText("Cargar");
        botonCargarTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCargarTSPActionPerformed(evt);
            }
        });

        introducir_nombre_TSP.setText("Nombre archivo");
        introducir_nombre_TSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                introducir_nombre_TSPActionPerformed(evt);
            }
        });

        text_generar_aleatorio.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        text_generar_aleatorio.setText("Generar aleatorio");

        introducir_talla.setText("Talla");
        introducir_talla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                introducir_tallaActionPerformed(evt);
            }
        });

        botonGenerar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        botonGenerar.setText("Generar");
        botonGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGenerarActionPerformed(evt);
            }
        });

        text_Estudio_empirico.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        text_Estudio_empirico.setText("Estudio empirico");

        botonComprobar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        botonComprobar.setText("Comprobar");
        botonComprobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonComprobarActionPerformed(evt);
            }
        });

        texto.setEditable(false);
        textoScroll.setViewportView(texto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_Unidireccional, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(introducir_nombre_TSP)
                                        .addComponent(text_CargarTSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(text_generar_aleatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(introducir_talla, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(botonCargarTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(botonComprobar, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(botonGenerar, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(text_Estudio_empirico, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(text_Bidireccional, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(textoScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 847, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_Unidireccional)
                    .addComponent(text_Bidireccional))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(text_CargarTSP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(introducir_nombre_TSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonCargarTSP)
                        .addGap(29, 29, 29)
                        .addComponent(text_generar_aleatorio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(introducir_talla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonGenerar)
                        .addGap(18, 18, 18)
                        .addComponent(text_Estudio_empirico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonComprobar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(textoScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //nombre del archivo tsp
    private void introducir_nombre_TSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_introducir_nombre_TSPActionPerformed
        //nombreTSP = introducir_nombre_TSP.getText();
    }//GEN-LAST:event_introducir_nombre_TSPActionPerformed

    private void introducir_tallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_introducir_tallaActionPerformed
        //pasamos un String a Int
        //talla = Integer.parseInt(introducir_talla.getText());
    }//GEN-LAST:event_introducir_tallaActionPerformed

    private void botonComprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonComprobarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonComprobarActionPerformed
    //Boton de cargar .tsp
    private void botonCargarTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCargarTSPActionPerformed

        listaCiudades.clear();  //vaciamos la lista antes de usarla
        nombreTSP = introducir_nombre_TSP.getText();
        GP.LeePuntos("src/data/" + nombreTSP + "/" + nombreTSP);
        this.listaCiudades = GP.getListaPuntos();
        TSP.calcularRutas(listaCiudades);
        jPanel1.repaint();
        jPanel2.repaint();
    }//GEN-LAST:event_botonCargarTSPActionPerformed

    private void botonGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGenerarActionPerformed

        listaCiudades.clear();  //vaciamos la lista antes de usarla
        talla = Integer.parseInt(introducir_talla.getText());
        GP.rellenarPuntos(talla, false);
        this.listaCiudades = GP.getListaPuntos();

        TSP.calcularRutas(listaCiudades);
        jPanel1.repaint();
        jPanel2.repaint();
        //ELIMINAR EL TEXTO ANTES DE VOLVER A PONERLO

    }//GEN-LAST:event_botonGenerarActionPerformed

    // Clase para redirigir la salida estándar al JTextPane
    private static class CustomOutputStream extends OutputStream {

        private JTextPane textPane;

        public CustomOutputStream(JTextPane textPane) {
            this.textPane = textPane;
        }

        @Override
        public void write(int b) {
            StyledDocument doc = textPane.getStyledDocument();
            Style style = textPane.getStyle(StyleContext.DEFAULT_STYLE);
            try {
                doc.insertString(doc.getLength(), String.valueOf((char) b), style);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

    private static void dibujarPuntos(Graphics g, List<Punto> ruta, int panelAncho, int panelAlto) {

        double maxX = ruta.stream().mapToDouble(Punto::getX).max().orElse(0);
        double maxY = ruta.stream().mapToDouble(Punto::getY).max().orElse(0);

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
            //linea entre el primero y el ultimo
            int primerX = (int) (ruta.get(0).getX() * factorEscala);
            int primerY = (int) (ruta.get(0).getY() * factorEscala);
            int ultimoX = (int) (ruta.get(ruta.size() - 1).getX() * factorEscala);
            int ultimoY = (int) (ruta.get(ruta.size() - 1).getY() * factorEscala);

            g.drawLine(primerX, primerY, ultimoX, ultimoY);
        }

        for (int i = 0; i < ruta.size(); i++) {
            Punto punto = ruta.get(i);
            int x = (int) (punto.getX() * factorEscala);
            int y = (int) (punto.getY() * factorEscala);

            if (i == 0) {
                g.setColor(Color.BLUE);     // Cambiar el color del primer punto a azul
            } else if (i == ruta.size() - 1) {
                g.setColor(Color.YELLOW);   // Cambiar el color del ultimo punto a amarillo
            } else {
                g.setColor(Color.BLACK);    // Restablecer el color a negro para los demás puntos
            }
            g.fillOval(x, y, 5, 5);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCargarTSP;
    private javax.swing.JButton botonComprobar;
    private javax.swing.JButton botonGenerar;
    private javax.swing.JTextField introducir_nombre_TSP;
    private javax.swing.JTextField introducir_talla;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel text_Bidireccional;
    private javax.swing.JLabel text_CargarTSP;
    private javax.swing.JLabel text_Estudio_empirico;
    private javax.swing.JLabel text_Unidireccional;
    private javax.swing.JLabel text_generar_aleatorio;
    private javax.swing.JTextPane texto;
    private javax.swing.JScrollPane textoScroll;
    // End of variables declaration//GEN-END:variables
}
