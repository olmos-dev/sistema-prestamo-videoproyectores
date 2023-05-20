/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spv;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author UV
 */
public class Reporte extends javax.swing.JFrame {
    Conectar cc = new Conectar();  //Métodos para trabajar con la conexión de la base de datos.
    Connection cn = cc.conexion();
    ResultSet rs = null;
    PreparedStatement pst = null;
    private static int valor1;
    private static int valor2;
    private static int valor3;
    private static int valor4;
    private static int valor5;
    private static int valor6;
    private static int valor7;
    private static int valor8;
    private static int valor9;
    private static int valor10;
    private static int valor11;
    

    /**
     * Creates new form Reporte
     */
    public Reporte() {
        initComponents();
        this.getContentPane().setBackground(new java.awt.Color(92,172,238));
        this.setLocationRelativeTo(null);
    }
    

    
    private void reporteArticulos(String f1, String f2){
        try{
            String proyector = "select count(inventario) inventario from prestamoVista where inventario != 'No solicito' and fecha between '"+f1+"' and '"+f2+"'";
            pst = cn.prepareStatement(proyector);
            rs = pst.executeQuery();
            while(rs.next()){
                valor1 = rs.getInt("inventario");
            }
        }catch(SQLException e){
            e.getMessage();
        }
         
        try{   
            String vga = "select count(vga) vga from prestamoVista where vga = 'si' and fecha between '"+f1+"' and '"+f2+"'";
            pst = cn.prepareCall(vga);
            rs = pst.executeQuery();
            while(rs.next()){
                valor2 = rs.getInt("vga");
            }
        }catch(SQLException e){
            e.getMessage();
        }
        
        try{
            String hdmi = "select count(HDMI) HDMI from prestamoVista where HDMI = 'si' and fecha between '"+f1+"' and '"+f2+"';";
            pst = cn.prepareCall(hdmi);
            rs = pst.executeQuery();
            while(rs.next()){
                valor3 = rs.getInt("HDMI");
                
            }
        }catch(SQLException e){
            e.getMessage();
        }
            
        try{
            String ext = "select count(extension) extension from prestamoVista where extension = 'si' and fecha between '"+f1+"' and '"+f2+"'";
            pst = cn.prepareCall(ext);
            rs = pst.executeQuery();
            while(rs.next()){
                valor4 = rs.getInt("extension");
            }    
        }catch(SQLException e){
            e.getMessage();
        }
                
        
        try{
            String bocinas = "select count(bocinas) bocinas from prestamoVista where bocinas = 'si' and fecha between '"+f1+"' and '"+f2+"'";
            pst = cn.prepareCall(bocinas);
            rs = pst.executeQuery();
            while(rs.next()){
                valor5 = rs.getInt("bocinas");
            }
        }catch(SQLException e)
        {
            e.getMessage();
        }
            
            
        try{        
            String vga2 = "select count(vga) vga from agotado where VGA = 1 and fecha between '"+f1+"' and '"+f2+"';";
            pst = cn.prepareCall(vga2);
            rs = pst.executeQuery();
            while(rs.next()){
                valor6 = rs.getInt("vga");
            }
            
        }catch(SQLException e){
            e.getMessage();
        }
                
        try{
            String HDMI2 = "select count(HDMI) HDMI from agotado where HDMI = 1 and fecha between '"+f1+"' and '"+f2+"'";
            pst = cn.prepareCall(HDMI2);
            rs = pst.executeQuery();
            while(rs.next()){
                valor7 = rs.getInt("HDMI");
            }    
        }catch(SQLException e){
            e.getMessage();         
        }
                
        try{
            String ext2 = "select count(ext) ext from agotado where ext = 1 and fecha between '"+f1+"' and '"+f2+"'";
            pst = cn.prepareCall(ext2);
            rs = pst.executeQuery();
            while(rs.next()){
                valor8 = rs.getInt("ext");
            }
        }catch(SQLException e){
            e.getMessage();
        }
              
        try{
            String bocinas2 = "select count(bocinas) bocinas from agotado where bocinas = 1 and fecha between '"+f1+"' and '"+f2+"'";
            pst = cn.prepareCall(bocinas2);
            rs = pst.executeQuery();
            while(rs.next()){
                valor9 = rs.getInt("bocinas");
            }    
        }catch(SQLException e){
            e.getMessage();
        }
        
         try{
            String proyector2 = "select count(proyector) proyector from agotado where proyector = 1";
            pst = cn.prepareCall(proyector2);
            rs = pst.executeQuery();
            while(rs.next()){
                valor10 = rs.getInt("proyector");
            }    
        }catch(SQLException e){
            e.getMessage();
        }
    }
    
        private void reporteVacio(String f1, String f2){
        try{
            String prestamo ="select count(fecha)fecha from prestamo where fecha between '"+f1+"' and '"+f2+"'";
            pst = cn.prepareCall(prestamo);
            rs = pst.executeQuery();
            while(rs.next()){
                valor11=rs.getInt("fecha");
            }
            
        }catch(SQLException e){
            e.getMessage();
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jRadioButtonReporte1 = new javax.swing.JRadioButton();
        jRadioButtonReporte2 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jDateChooserFin = new com.toedter.calendar.JDateChooser();
        jDateChooserInicio = new com.toedter.calendar.JDateChooser();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Generar Reporte");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(92, 172, 238));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccionar tipo de reporte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        buttonGroup1.add(jRadioButtonReporte1);
        jRadioButtonReporte1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jRadioButtonReporte1.setText("Registro de préstamos de videoproyectores");
        jRadioButtonReporte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonReporte1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonReporte2);
        jRadioButtonReporte2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jRadioButtonReporte2.setText("Articulos solicitados");
        jRadioButtonReporte2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonReporte2ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/signing.png"))); // NOI18N
        jButton1.setToolTipText("Clic para generar reporte");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Fecha de inicio");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Fecha de termino");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/back.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jRadioButtonReporte1)
                            .addComponent(jRadioButtonReporte2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooserFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jDateChooserInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 83, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(24, 24, 24))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButtonReporte1)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonReporte2)
                        .addGap(20, 20, 20)
                        .addComponent(jDateChooserInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooserFin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(!jRadioButtonReporte1.isSelected() && !jRadioButtonReporte2.isSelected()){
            JOptionPane.showMessageDialog(null, "Debe seleccionar un reporte");
        }else{
            if(jRadioButtonReporte1.isSelected()){
                try{
                    if(jDateChooserInicio.getDate() == null || jDateChooserFin.getDate() == null){
                        JOptionPane.showMessageDialog(null, "Falta seleccionar fecha","Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        
                    }else{
                        SimpleDateFormat f1 = new SimpleDateFormat("YYYY-MM-dd");
                        String fecha1 = f1.format(jDateChooserInicio.getDate());
                        
                        SimpleDateFormat f2 = new SimpleDateFormat("YYYY-MM-dd");
                        String fecha2 = f2.format(jDateChooserFin.getDate());
                        
                        reporteVacio(fecha1,fecha2);
                        
                        if(valor11==0){
                            JOptionPane.showMessageDialog(null,"No se puede generar reporte\n"+"(No existe información, con este intervalo de tiempo)", "Error", JOptionPane.ERROR_MESSAGE);
                            jDateChooserInicio.setCalendar(null);
                            jDateChooserFin.setCalendar(null);
                            jDateChooserInicio.requestFocusInWindow();
                            
                        }else{
                             Map p = new HashMap();
                             p.put("parameterF1", fecha1);
                             p.put("parameterF2", fecha2);
                    
                             JasperReport r = JasperCompileManager.compileReport("reporte2.jrxml");
                             JasperPrint print = JasperFillManager.fillReport(r,p,cn);
                             JasperViewer view = new JasperViewer(print,false);
                             view.setTitle("Reporte - Préstamo de videoproyectores");
                             view.setVisible(true);
                             jDateChooserInicio.setCalendar(null);
                             jDateChooserFin.setCalendar(null);
                             jDateChooserInicio.requestFocusInWindow();
                        }
                    
                    }
            }catch(JRException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
                
        }else{
                if(jDateChooserInicio.getDate() == null || jDateChooserFin.getDate() == null){
                    JOptionPane.showMessageDialog(null, "Falta seleccionar fecha","Mensaje", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    if(jRadioButtonReporte2.isSelected()){
                        try{
                            SimpleDateFormat f1 = new SimpleDateFormat("YYYY-MM-dd");
                            String fecha1 = f1.format(jDateChooserInicio.getDate());
                        
                    
                            SimpleDateFormat f2 = new SimpleDateFormat("YYYY-MM-dd");
                            String fecha2 = f2.format(jDateChooserFin.getDate());
                         
                            reporteArticulos(fecha1,fecha2);
                           
                            if(valor1 == 0 && valor2 == 0 && valor3 == 0 && valor4 == 0 && valor5 == 0 && valor6 == 0 && valor7 == 0 && valor8==0 && valor9==0){
                                 JOptionPane.showMessageDialog(null,"No se puede generar reporte\n"+"(No existe información, con este intervalo de tiempo)", "Error", JOptionPane.ERROR_MESSAGE);
                                 jDateChooserInicio.setCalendar(null);
                                 jDateChooserFin.setCalendar(null);
                                 jDateChooserInicio.requestFocusInWindow();
                                 
                            }else{
                                Map p = new HashMap();
                                p.put("parameterProyector", valor1);
                                p.put("parameterVGA", valor2);
                                p.put("parameterHDMI", valor3);
                                p.put("parameterExt", valor4);
                                p.put("parameterBocinas", valor5);
                                p.put("parameterVGA2", valor6);
                                p.put("parameterHDMI2", valor7);
                                p.put("parameterExt2", valor8);
                                p.put("parameterBocinas2", valor9);
                                p.put("parameterProyector2",valor10);
                                p.put("parameterF1", fecha1);
                                p.put("parameterF2", fecha2);
                                          
                                JasperReport reporte = JasperCompileManager.compileReport("reporte3.jrxml");
                                JasperPrint print = JasperFillManager.fillReport(reporte, p, cn);
                                JasperViewer view = new JasperViewer(print,false);
                                view.setTitle("Reporte - Articulos Solicitados");
                                view.setVisible(true);
                                jDateChooserInicio.setCalendar(null);
                                jDateChooserFin.setCalendar(null);
                                jDateChooserInicio.requestFocusInWindow();
                            }
                        }catch(JRException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButtonReporte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonReporte1ActionPerformed
        // TODO add your handling code here:
        /*
        jDateChooserInicio.setEnabled(false);
        jDateChooserFin.setEnabled(false);
        jDateChooserInicio.setCalendar(null);
        jDateChooserFin.setCalendar(null);
        */
        
        
    }//GEN-LAST:event_jRadioButtonReporte1ActionPerformed

    private void jRadioButtonReporte2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonReporte2ActionPerformed
        // TODO add your handling code here:
         //jDateChooserInicio.setEnabled(true);
         //jDateChooserFin.setEnabled(true);
    }//GEN-LAST:event_jRadioButtonReporte2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Menu mu = new Menu();
        mu.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Reporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reporte().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooserFin;
    private com.toedter.calendar.JDateChooser jDateChooserInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButtonReporte1;
    private javax.swing.JRadioButton jRadioButtonReporte2;
    // End of variables declaration//GEN-END:variables
}
