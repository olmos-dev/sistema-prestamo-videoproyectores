/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spv;

import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Alberto Olmos
 */
public class Proyector extends javax.swing.JFrame {
    Conectar cc = new Conectar(); //Metodos para la conexion de la BD y para que se puedan actualizar, agregar, eliminar datos desde BD,
    Connection cn = cc.conexion();
    ResultSet rs = null;
    PreparedStatement pst = null;
    public String inv;

    /**
     * Creates new form Proyector
     */
    public Proyector() {
        initComponents();
        this.getContentPane().setBackground(new java.awt.Color(92,172,238));
        this.setLocationRelativeTo(null);
        this.setTitle("Administración de videoproyectores");
        cargarDatos();
        jButtonModificar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jTextFieldInventario.requestFocusInWindow();

    }

    public void limpiar(){  //Solo para limpiar los campos cuando el usuario haya realizado captura de datos
        jTextFieldInventario.setText(null);
        jComboBoxNo.setSelectedIndex(0);
        jComboBoxEstado.setSelectedIndex(0);
        jButtonAgregar.setFocusable(false);
        jButtonModificar.setFocusable(false);
        jButtonEliminar.setFocusable(false);
        jTextArea1.setText(null);
    }
    
    public void cargarDatos() {  //Carga la informacion de los videoproyectores desde la BD
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("INVENTARIO");
        modelo.addColumn("NO.");
        modelo.addColumn("ESTADO");
        modelo.addColumn("NOTAS");

        jTable1.setModel(modelo);
        Object[] columnas = new Object[15];

        try {
            String cargar = ("select * from proyectorVista");
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(cargar);
            while (rs.next()) {
                columnas[0] = rs.getString(1);
                columnas[1] = rs.getString(2);
                columnas[2] = rs.getString(3);
                columnas[3] = rs.getString(4);
                modelo.addRow(columnas);
            }
            jTable1.setModel(modelo);
        } catch (SQLException e) {

        }
    }

    public void agregar() {  //Es para agregar un videoproyector al sistema
        String inv = jTextFieldInventario.getText();
        String no = (String) jComboBoxNo.getSelectedItem();
        String estado = (String) jComboBoxEstado.getSelectedItem();
        String notas = jTextArea1.getText();

        try {
            String insertar = "insert into proyector (inventario, NO, estado, notas)values(?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(insertar);
            pst.setString(1, inv);
            pst.setString(2, no);
            pst.setString(3, estado);
            if(notas.isEmpty()){
                String nota = "Ninguno";
                pst.setString(4, nota);
            }else{
                 pst.setString(4, notas);
                
            }
            pst.execute();
        } catch (SQLException e) {

        }
    }
   
    public void modificar() {  //Es para modificar la informacion de un videoproyector pero no se podra modicar en dato caso que se haya relizado el resgristo de un prestamo. No se pude por reglas de integridad de BD relaciones.
        try {
            if(jTextArea1.getText().isEmpty()){
                String nota = "Ninguno";
                 String sql = "update proyector set inventario='" + jTextFieldInventario.getText() + "',No='" + jComboBoxNo.getSelectedItem().toString() + "',estado='" + jComboBoxEstado.getSelectedItem().toString()+ "',notas='"+nota+"' where inventario = '" + inv + "'";
            PreparedStatement pst = cn.prepareStatement(sql);
            int res = pst.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Se modifico correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo modificar");
            }
                
                
            }else{
                 String sql = "update proyector set inventario='" + jTextFieldInventario.getText() + "',No='" + jComboBoxNo.getSelectedItem().toString() + "',estado='" + jComboBoxEstado.getSelectedItem().toString()+ "',notas='"+jTextArea1.getText()+"' where inventario = '" + inv + "'";
            PreparedStatement pst = cn.prepareStatement(sql);
            int res = pst.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Se modifico correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo modificar");
            }
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar porque existe información que se relaciona con los prestamos");
        }
    }
    
    public void eliminar() {  //Este metodo sirve para eliminar (Pasa lo mismo con el metodo de modificar) por reglas de integridad no se puede elimianar si existe un registro del un prestamo. 
        try {
            PreparedStatement pst = cn.prepareStatement("delete from proyector where inventario = '" + inv + "'");
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se elimino correctamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar porque existe información que se relaciona con los prestamos");
        };

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
        jLabel1 = new javax.swing.JLabel();
        jTextFieldInventario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxNo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxEstado = new javax.swing.JComboBox<>();
        jButtonAgregar = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButtonEliminar = new javax.swing.JButton();
        jButtonReporte = new javax.swing.JButton();
        jButtonVolver = new javax.swing.JButton();
        jButtonLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(92, 172, 238));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información de los videoproyectores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Inventario");

        jTextFieldInventario.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldInventarioActionPerformed(evt);
            }
        });
        jTextFieldInventario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldInventarioKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("No");

        jComboBoxNo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxNo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "1 ", "2", "3", "4", "6", "9", "10", "11", "12", "15", "16", "17", "18", "19", "20", "21", "23", "24", "25", "26", "Laboratorio", "Quirófano", "Anfiteatro", "Aula Magna", "Audiovisual 1", "Audiovisual 2", "Simuladores", "Sin Asignar" }));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Estado");

        jComboBoxEstado.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Funciona", "No Funciona", "De Baja" }));

        jButtonAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add.png"))); // NOI18N
        jButtonAgregar.setToolTipText("Agregar un videoproyector");
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });

        jButtonModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit.png"))); // NOI18N
        jButtonModificar.setToolTipText("Modificar datos de un videoproyector");
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Nota");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextArea1KeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(jTextArea1);

        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/delete.png"))); // NOI18N
        jButtonEliminar.setToolTipText("Eliminar videoproyector");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/signing.png"))); // NOI18N
        jButtonReporte.setToolTipText("Generar reporte");
        jButtonReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReporteActionPerformed(evt);
            }
        });

        jButtonVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/back.png"))); // NOI18N
        jButtonVolver.setToolTipText("Volver a ménu principal");
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });

        jButtonLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/update-arrows.png"))); // NOI18N
        jButtonLimpiar.setToolTipText("Limpiar campos");
        jButtonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonAgregar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonModificar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonReporte)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonVolver))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(45, 45, 45)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonAgregar)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButtonReporte)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jButtonVolver))
                    .addComponent(jButtonLimpiar)))
        );

        jTable1 = new javax.swing.JTable(){
            public boolean isCellEditable (int rowIndex, int colIndex){
                return false;
            }
        };
        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        // TODO add your handling code here:
        if(!jTextFieldInventario.getText().isEmpty() || jComboBoxNo.getSelectedIndex()!=0 || jComboBoxEstado.getSelectedIndex()!=0  || !jTextArea1.getText().isEmpty()){
            int r = JOptionPane.showConfirmDialog(null, "Toda la información ingresada no se guardara \n"+"¿Esta seguro que desea hacerlo?","Advertencia", JOptionPane.YES_NO_OPTION);
            if(r == JOptionPane.YES_OPTION){
                this.dispose();
                Menu m = new Menu();
                m.setVisible(true);
            }
        }else{ 
            this.dispose();
            Menu m = new Menu();
            m.setVisible(true);
        }
        
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Proyector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonVolverActionPerformed

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
        // TODO add your handling code here:
        //Validaciones para guardar la informacion correctamente
        if (jTextFieldInventario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el inventario");
        } else {
            if (jComboBoxNo.getSelectedIndex() == 0 || jComboBoxEstado.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Debe seleecionar elemento(s)");
            } else {
                agregar();
                JOptionPane.showMessageDialog(null, "Se agrego correctamente.");
                cargarDatos();
                limpiar();
                jTextFieldInventario.requestFocusInWindow();
                
            }
        }
        jTextFieldInventario.requestFocusInWindow();
        
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        // TODO add your handling code here:
        //Validaciones para que el resgistro se lleve correctamente al momento de actualizar la informacion.
        if (jTextFieldInventario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione la fila donde esta el videoproyector");
        } else {
            if (jComboBoxNo.getSelectedIndex() == 0 || jComboBoxEstado.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Debe seleecionar elemento(s)");
            } else {
                modificar();
                cargarDatos();
            }
        }
        limpiar();
        jButtonAgregar.setEnabled(true);
        jButtonModificar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jTextFieldInventario.requestFocusInWindow();
    }//GEN-LAST:event_jButtonModificarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        //Cargara los datos desde la tabla para que puedan ser mostrados para el usuario
        int fila = jTable1.getSelectedRow();
        if (fila >= 0) {
            jTextFieldInventario.setText(jTable1.getValueAt(fila, 0).toString());
            inv = jTable1.getValueAt(fila, 0).toString();
            jComboBoxNo.setSelectedItem(jTable1.getValueAt(fila, 1).toString());
            jComboBoxEstado.setSelectedItem(jTable1.getValueAt(fila, 2).toString());
            jTextArea1.setText(jTable1.getValueAt(fila, 3).toString());

        } else {
        }
        jButtonAgregar.setEnabled(false);
        jButtonModificar.setEnabled(true);
        jButtonEliminar.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextFieldInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldInventarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldInventarioActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        // TODO add your handling code here:
        //Validacion para que el usuario pueda eliminar la informacion correctamente
        if (jTextFieldInventario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione la fila donde esta el videoproyector");
        } else {
            if (jComboBoxNo.getSelectedIndex() == 0 || jComboBoxEstado.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Debe seleecionar elemento(s)");
            } else {
                eliminar();
                cargarDatos(); 
            }
        }
        limpiar();
        jButtonAgregar.setEnabled(true);
        jButtonModificar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jTextFieldInventario.requestFocusInWindow();
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jTextArea1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyTyped
        // TODO add your handling code here:
        //Validacion del tamaño de texto
        String lim = jTextArea1.getText();
        if(lim.length()>=101){
            evt.consume();
            Toolkit t = Toolkit.getDefaultToolkit();
            t.beep();
        }
    }//GEN-LAST:event_jTextArea1KeyTyped

    private void jButtonReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReporteActionPerformed
        // TODO add your handling code here:
        //En esta parte se crea el metodo para generar el reporte
         try{
            JasperReport r = JasperCompileManager.compileReport("reporte1.jrxml");
            JasperPrint print = JasperFillManager.fillReport(r, null, this.cn);
            JasperViewer view = new JasperViewer(print,false);
            view.setTitle("Reporte - Información de los videoproyectores");
            view.setVisible(true);
        }catch(JRException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        jTextFieldInventario.requestFocusInWindow();
        
    }//GEN-LAST:event_jButtonReporteActionPerformed

    private void jTextFieldInventarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInventarioKeyTyped
        // TODO add your handling code here:
        String lim = jTextFieldInventario.getText();
        if(lim.length()>=9){
            evt.consume();
            Toolkit t = Toolkit.getDefaultToolkit();
            t.beep();
        } 
        
        char c = evt.getKeyChar();
        if(Character.isWhitespace(c)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldInventarioKeyTyped

    private void jTextArea1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea1KeyPressed

    private void jButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarActionPerformed
        // TODO add your handling code here:
        jTextFieldInventario.setText(null);  //Este metodo se utiliza para limpiar campos
        jComboBoxNo.setSelectedIndex(0);
        jComboBoxEstado.setSelectedIndex(0);
        jTextArea1.setText(null);
        jTable1.clearSelection();
        jTextFieldInventario.requestFocusInWindow();
        jButtonAgregar.setEnabled(true);
        jButtonEliminar.setEnabled(false);
        jButtonModificar.setEnabled(false);
    }//GEN-LAST:event_jButtonLimpiarActionPerformed

  
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Proyector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Proyector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Proyector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Proyector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Proyector().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JButton jButtonReporte;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JComboBox<String> jComboBoxEstado;
    private javax.swing.JComboBox<String> jComboBoxNo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFieldInventario;
    // End of variables declaration//GEN-END:variables
}
