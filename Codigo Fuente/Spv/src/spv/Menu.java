/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spv;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
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
public class Menu extends javax.swing.JFrame {
    public static int filas;  //se crearon estas variables para pasar valores para ser utilizadas en otras clases.
    public static String id;   
    public static int f;      
    Conectar cc = new Conectar();  //Métodos para trabajar con la conexión de la base de datos.
    Connection cn = cc.conexion();
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    
    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        cargarDatos();  //Para cargar los datos en la tabla
        this.getContentPane().setBackground(new java.awt.Color(92,172,238));
        filas = jTable1.getRowCount();  
        this.setSize(new Dimension(1350,650)); 
        this.setLocationRelativeTo(null);  
        this.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        jTextFieldBuscar.requestFocusInWindow();

    }
    
    public void cargarDatos(){  //Este metodo se utiliza para llevar la tabla desde la BD con una vista
        DefaultTableModel modelo = new DefaultTableModel();
           
        modelo.addColumn("FOLIO");
        modelo.addColumn("INVENTARIO");
        modelo.addColumn("FECHA");
        modelo.addColumn("HORA");
        modelo.addColumn("MATRICULA");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("MATERIA");
        modelo.addColumn("HORARIO");
        modelo.addColumn("MAESTRO");
        modelo.addColumn("AULA");
        modelo.addColumn("VGA");
        modelo.addColumn("HDMI");
        modelo.addColumn("EXT.");
        modelo.addColumn("BOCINAS");
        modelo.addColumn("ESTADO");
        
        
        
        jTable1.setModel(modelo);
        Object []columnas = new Object[15];
        
        try{
            String cargar = ("select * from prestamoVista");
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(cargar);
            while(rs.next()){
                columnas[0]=rs.getInt(1);
                columnas[1]=rs.getString(2);
                columnas[2]=rs.getString(3);
                columnas[3]=rs.getString(4);
                columnas[4]=rs.getString(5);
                columnas[5]=rs.getString(6);
                columnas[6]=rs.getString(7);
                columnas[7]=rs.getString(8);
                columnas[8]=rs.getString(9);
                columnas[9]=rs.getString(10);
                columnas[10]=rs.getString(11);
                columnas[11]=rs.getString(12);
                columnas[12]=rs.getString(13);
                columnas[13]=rs.getString(14);
                columnas[14]=rs.getString(15);
                modelo.addRow(columnas);
            }
            jTable1.setModel(modelo);
        }catch(SQLException e){
            
        }
        
        TableColumnModel tcm = jTable1.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(30);
        tcm.getColumn(1).setPreferredWidth(60);
        tcm.getColumn(2).setPreferredWidth(50);
        tcm.getColumn(3).setPreferredWidth(40);
        tcm.getColumn(4).setPreferredWidth(60);
        tcm.getColumn(5).setPreferredWidth(200);
        tcm.getColumn(6).setPreferredWidth(100);
        tcm.getColumn(7).setPreferredWidth(60);
        tcm.getColumn(8).setPreferredWidth(80);
        tcm.getColumn(9).setPreferredWidth(50);
        tcm.getColumn(10).setPreferredWidth(40);
        tcm.getColumn(11).setPreferredWidth(40);
        tcm.getColumn(12).setPreferredWidth(40);
        tcm.getColumn(13).setPreferredWidth(40);
        tcm.getColumn(14).setPreferredWidth(50);
    }
    
    public void tamColumna(){
        TableColumnModel tcm = jTable1.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(30);
        tcm.getColumn(1).setPreferredWidth(60);
        tcm.getColumn(2).setPreferredWidth(50);
        tcm.getColumn(3).setPreferredWidth(40);
        tcm.getColumn(4).setPreferredWidth(60);
        tcm.getColumn(5).setPreferredWidth(200);
        tcm.getColumn(6).setPreferredWidth(100);
        tcm.getColumn(7).setPreferredWidth(60);
        tcm.getColumn(8).setPreferredWidth(80);
        tcm.getColumn(9).setPreferredWidth(50);
        tcm.getColumn(10).setPreferredWidth(40);
        tcm.getColumn(11).setPreferredWidth(40);
        tcm.getColumn(12).setPreferredWidth(40);
        tcm.getColumn(13).setPreferredWidth(40);
        tcm.getColumn(14).setPreferredWidth(50);
        
    }
    
    public void buscarAula(){  //Este metodo es para buscar por aula
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("FOLIO");
        modelo.addColumn("INVENTARIO");
        modelo.addColumn("FECHA");
        modelo.addColumn("HORA");
        modelo.addColumn("MATRICULA");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("MATERIA");
        modelo.addColumn("HORARIO");
        modelo.addColumn("MAESTRO");
        modelo.addColumn("AULA");
        modelo.addColumn("VGA");
        modelo.addColumn("HDMI");
        modelo.addColumn("EXT.");
        modelo.addColumn("BOCINAS");
        modelo.addColumn("ESTADO");
        
        jTable1.setModel(modelo);
        Object []columnas = new Object[15];
        
        try{
            String aula = "select * from prestamo where aula='"+jTextFieldBuscar.getText()+"'order by folio desc";
            PreparedStatement pst = cn.prepareStatement(aula);
            ResultSet rs = pst.executeQuery(aula);
            while(rs.next()){
                 columnas[0]=rs.getInt(1);
                 columnas[1]=rs.getString(2);
                 columnas[2]=rs.getString(3);
                 columnas[3]=rs.getString(4);
                 columnas[4]=rs.getString(5);
                 columnas[5]=rs.getString(6);
                 columnas[6]=rs.getString(7);
                 columnas[7]=rs.getString(8);
                 columnas[8]=rs.getString(9);
                 columnas[9]=rs.getString(10);
                 columnas[10]=rs.getString(11);
                 columnas[11]=rs.getString(12);
                 columnas[12]=rs.getString(13);
                 columnas[13]=rs.getString(14);
                 columnas[14]=rs.getString(15);
                 modelo.addRow(columnas); 
            }
            jTable1.setModel(modelo);
            int r;
            r = this.jTable1.getRowCount();
            tamColumna();
            if(r == 0){
                JOptionPane.showMessageDialog(null, "Sin resultados");
            }
        }catch(SQLException e){
        }
        
        
    
    }
    
    public void buscarEstado(){ //Este metodo es para buscar por estado
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("FOLIO");
        modelo.addColumn("INVENTARIO");
        modelo.addColumn("FECHA");
        modelo.addColumn("HORA");
        modelo.addColumn("MATRICULA");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("MATERIA");
        modelo.addColumn("HORARIO");
        modelo.addColumn("MAESTRO");
        modelo.addColumn("AULA");
        modelo.addColumn("VGA");
        modelo.addColumn("HDMI");
        modelo.addColumn("EXT.");
        modelo.addColumn("BOCINAS");
        modelo.addColumn("ESTADO");
        
        jTable1.setModel(modelo);
        Object []columnas = new Object[15];
        
        try{
            String estado = "select * from prestamo where estado like '%"+jTextFieldBuscar.getText()+"%'order by folio desc";
            PreparedStatement pst = cn.prepareStatement(estado);
            ResultSet rs = pst.executeQuery(estado);
            while(rs.next()){
                 columnas[0]=rs.getInt(1);
                 columnas[1]=rs.getString(2);
                 columnas[2]=rs.getString(3);
                 columnas[3]=rs.getString(4);
                 columnas[4]=rs.getString(5);
                 columnas[5]=rs.getString(6);
                 columnas[6]=rs.getString(7);
                 columnas[7]=rs.getString(8);
                 columnas[8]=rs.getString(9);
                 columnas[9]=rs.getString(10);
                 columnas[10]=rs.getString(11);
                 columnas[11]=rs.getString(12);
                 columnas[12]=rs.getString(13);
                 columnas[13]=rs.getString(14);
                 columnas[14]=rs.getString(15);
                 modelo.addRow(columnas); 
            }
            jTable1.setModel(modelo);
            tamColumna();
            resultado();
        }catch(SQLException e){
        }
        
    }
    
    public void buscarFecha(){  //Este metodo es para buscar por fecha
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("FOLIO");
        modelo.addColumn("INVENTARIO");
        modelo.addColumn("FECHA");
        modelo.addColumn("HORA");
        modelo.addColumn("MATRICULA");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("MATERIA");
        modelo.addColumn("HORARIO");
        modelo.addColumn("MAESTRO");
        modelo.addColumn("AULA");
        modelo.addColumn("VGA");
        modelo.addColumn("HDMI");
        modelo.addColumn("EXT.");
        modelo.addColumn("BOCINAS");
        modelo.addColumn("ESTADO");
        
        jTable1.setModel(modelo);
        Object []columnas = new Object[15];
        
        try{
            String fecha = "select * from prestamo where fecha like'%"+jTextFieldBuscar.getText()+"%'order by folio desc";
            PreparedStatement pst = cn.prepareStatement(fecha);
            ResultSet rs = pst.executeQuery(fecha);
            while(rs.next()){
                 columnas[0]=rs.getInt(1);
                 columnas[1]=rs.getString(2);
                 columnas[2]=rs.getString(3);
                 columnas[3]=rs.getString(4);
                 columnas[4]=rs.getString(5);
                 columnas[5]=rs.getString(6);
                 columnas[6]=rs.getString(7);
                 columnas[7]=rs.getString(8);
                 columnas[8]=rs.getString(9);
                 columnas[9]=rs.getString(10);
                 columnas[10]=rs.getString(11);
                 columnas[11]=rs.getString(12);
                 columnas[12]=rs.getString(13);
                 columnas[13]=rs.getString(14);
                 columnas[14]=rs.getString(15);
                 modelo.addRow(columnas); 
            }
            jTable1.setModel(modelo);
            tamColumna();
            resultado();
        }catch(SQLException e){
        }
        
    }
    
    public void buscarMatricula(){  //Este metodo es para buscar por matricula
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("FOLIO");
        modelo.addColumn("INVENTARIO");
        modelo.addColumn("FECHA");
        modelo.addColumn("HORA");
        modelo.addColumn("MATRICULA");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("MATERIA");
        modelo.addColumn("HORARIO");
        modelo.addColumn("MAESTRO");
        modelo.addColumn("AULA");
        modelo.addColumn("VGA");
        modelo.addColumn("HDMI");
        modelo.addColumn("EXT.");
        modelo.addColumn("BOCINAS");
        modelo.addColumn("ESTADO");
        
        jTable1.setModel(modelo);
        Object []columnas = new Object[15];
        
        try{
            String matricula = "select * from prestamo where matricula ='"+jTextFieldBuscar.getText()+"'order by folio desc";
            PreparedStatement pst = cn.prepareStatement(matricula);
            ResultSet rs = pst.executeQuery(matricula);
            while(rs.next()){
                 columnas[0]=rs.getInt(1);
                 columnas[1]=rs.getString(2);
                 columnas[2]=rs.getString(3);
                 columnas[3]=rs.getString(4);
                 columnas[4]=rs.getString(5);
                 columnas[5]=rs.getString(6);
                 columnas[6]=rs.getString(7);
                 columnas[7]=rs.getString(8);
                 columnas[8]=rs.getString(9);
                 columnas[9]=rs.getString(10);
                 columnas[10]=rs.getString(11);
                 columnas[11]=rs.getString(12);
                 columnas[12]=rs.getString(13);
                 columnas[13]=rs.getString(14);
                 columnas[14]=rs.getString(15);
                 modelo.addRow(columnas); 
            }
            jTable1.setModel(modelo); 
            int r;
            tamColumna();
            r = this.jTable1.getRowCount();
            if(r == 0){
                JOptionPane.showMessageDialog(null, "Sin resultados");
            }
        }catch(SQLException e){
        }
        
        
    }
    
    public void buscarInventario(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("FOLIO");
        modelo.addColumn("INVENTARIO");
        modelo.addColumn("FECHA");
        modelo.addColumn("HORA");
        modelo.addColumn("MATRICULA");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("MATERIA");
        modelo.addColumn("HORARIO");
        modelo.addColumn("MAESTRO");
        modelo.addColumn("AULA");
        modelo.addColumn("VGA");
        modelo.addColumn("HDMI");
        modelo.addColumn("EXT.");
        modelo.addColumn("BOCINAS");
        modelo.addColumn("ESTADO");
        
        jTable1.setModel(modelo);
        Object []columnas = new Object[15];
        
        try{
            String matricula = "select * from prestamo where inventario ='"+jTextFieldBuscar.getText()+"'order by folio desc";
            PreparedStatement pst = cn.prepareStatement(matricula);
            ResultSet rs = pst.executeQuery(matricula);
            while(rs.next()){
                 columnas[0]=rs.getInt(1);
                 columnas[1]=rs.getString(2);
                 columnas[2]=rs.getString(3);
                 columnas[3]=rs.getString(4);
                 columnas[4]=rs.getString(5);
                 columnas[5]=rs.getString(6);
                 columnas[6]=rs.getString(7);
                 columnas[7]=rs.getString(8);
                 columnas[8]=rs.getString(9);
                 columnas[9]=rs.getString(10);
                 columnas[10]=rs.getString(11);
                 columnas[11]=rs.getString(12);
                 columnas[12]=rs.getString(13);
                 columnas[13]=rs.getString(14);
                 columnas[14]=rs.getString(15);
                 modelo.addRow(columnas); 
            }
            jTable1.setModel(modelo); 
            int r;
            tamColumna();
            r = this.jTable1.getRowCount();
            if(r == 0){
                JOptionPane.showMessageDialog(null, "Sin resultados");
            }
        }catch(SQLException e){
        }
        
    }
    
    public void buscarNombre(){  //Este metodo es para buscar por nombre
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("FOLIO");
        modelo.addColumn("INVENTARIO");
        modelo.addColumn("FECHA");
        modelo.addColumn("HORA");
        modelo.addColumn("MATRICULA");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("MATERIA");
        modelo.addColumn("HORARIO");
        modelo.addColumn("MAESTRO");
        modelo.addColumn("AULA");
        modelo.addColumn("VGA");
        modelo.addColumn("HDMI");
        modelo.addColumn("EXT.");
        modelo.addColumn("BOCINAS");
        modelo.addColumn("ESTADO");
        
        jTable1.setModel(modelo);
        Object []columnas = new Object[15];
        
        try{
            String nombre = "select * from prestamo where nombre like '%"+jTextFieldBuscar.getText()+"%'order by folio desc";
            PreparedStatement pst = cn.prepareStatement(nombre);
            ResultSet rs = pst.executeQuery(nombre);
            while(rs.next()){
                 columnas[0]=rs.getInt(1);
                 columnas[1]=rs.getString(2);
                 columnas[2]=rs.getString(3);
                 columnas[3]=rs.getString(4);
                 columnas[4]=rs.getString(5);
                 columnas[5]=rs.getString(6);
                 columnas[6]=rs.getString(7);
                 columnas[7]=rs.getString(8);
                 columnas[8]=rs.getString(9);
                 columnas[9]=rs.getString(10);
                 columnas[10]=rs.getString(11);
                 columnas[11]=rs.getString(12);
                 columnas[12]=rs.getString(13);
                 columnas[13]=rs.getString(14);
                 columnas[14]=rs.getString(15);
                 modelo.addRow(columnas); 
            }
            jTable1.setModel(modelo);
            tamColumna();
            int r;
            r = this.jTable1.getRowCount();
            if(r == 0){
                JOptionPane.showMessageDialog(null, "Sin resultados");
            }
        }catch(SQLException e){
        }
        
    }
    
        public void buscarNotas(){  //Este metodo es para buscar por notas
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("FOLIO");
        modelo.addColumn("INVENTARIO");
        modelo.addColumn("FECHA");
        modelo.addColumn("HORA");
        modelo.addColumn("MATRICULA");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("MATERIA");
        modelo.addColumn("HORARIO");
        modelo.addColumn("MAESTRO");
        modelo.addColumn("AULA");
        modelo.addColumn("VGA");
        modelo.addColumn("HDMI");
        modelo.addColumn("EXT.");
        modelo.addColumn("BOCINAS");
        modelo.addColumn("ESTADO");
        
        jTable1.setModel(modelo);
        Object []columnas = new Object[15];
        
        try{
            String nombre = "select*from prestamo where notas like '%"+jTextFieldBuscar.getText()+"%'order by folio desc";
            PreparedStatement pst = cn.prepareStatement(nombre);
            ResultSet rs = pst.executeQuery(nombre);
            while(rs.next()){
                 columnas[0]=rs.getInt(1);
                 columnas[1]=rs.getString(2);
                 columnas[2]=rs.getString(3);
                 columnas[3]=rs.getString(4);
                 columnas[4]=rs.getString(5);
                 columnas[5]=rs.getString(6);
                 columnas[6]=rs.getString(7);
                 columnas[7]=rs.getString(8);
                 columnas[8]=rs.getString(9);
                 columnas[9]=rs.getString(10);
                 columnas[10]=rs.getString(11);
                 columnas[11]=rs.getString(12);
                 columnas[12]=rs.getString(13);
                 columnas[13]=rs.getString(14);
                 columnas[14]=rs.getString(15);
                 modelo.addRow(columnas); 
            }
            jTable1.setModel(modelo);
            tamColumna();
            int r;
            r = this.jTable1.getRowCount();
            if(r == 0){
                JOptionPane.showMessageDialog(null, "Sin resultados");
            }
        }catch(SQLException e){
        }
        
    }
    
    
    public void resultado(){  //Este metodo depues de una busqueda, aloja el numero de resultados o no.
        int r;
            r = this.jTable1.getRowCount();
            if(r == 0){
                JOptionPane.showMessageDialog(null, "Sin resultados");
            }else{
                if(r>1){
                    JOptionPane.showMessageDialog(null,"Número de resultados encontrados: "+r);
                    
                }
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldBuscar = new javax.swing.JTextField();
        jButtonBuscar = new javax.swing.JButton();
        jComboBoxFiltro = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jButtonRegistrar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonVer = new javax.swing.JButton();
        jButtonProyector = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();
        jButtonInforme = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Préstamo de videoproyectores");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(92, 172, 238));

        jPanel2.setBackground(new java.awt.Color(92, 172, 238));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jTextFieldBuscar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldBuscar.setToolTipText("Ingrese una palabra clave");
        jTextFieldBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarActionPerformed(evt);
            }
        });
        jTextFieldBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldBuscarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBuscarKeyTyped(evt);
            }
        });

        jButtonBuscar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButtonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search.png"))); // NOI18N
        jButtonBuscar.setToolTipText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jComboBoxFiltro.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Aula", "Estado", "Fecha", "Inventario", "Matricula", "Nombre", "Notas" }));
        jComboBoxFiltro.setToolTipText("Filtro de búsqueda");
        jComboBoxFiltro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxFiltroItemStateChanged(evt);
            }
        });
        jComboBoxFiltro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxFiltroMouseClicked(evt);
            }
        });
        jComboBoxFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFiltroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBoxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonBuscar))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jComboBoxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(92, 172, 238));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ménu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jButtonRegistrar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButtonRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add.png"))); // NOI18N
        jButtonRegistrar.setToolTipText("Registrar un préstamo");
        jButtonRegistrar.setBorderPainted(false);
        jButtonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarActionPerformed(evt);
            }
        });

        jButtonEliminar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/delete.png"))); // NOI18N
        jButtonEliminar.setToolTipText("Eliminar un préstamo");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonVer.setBackground(new java.awt.Color(0, 0, 0));
        jButtonVer.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButtonVer.setForeground(new java.awt.Color(153, 153, 153));
        jButtonVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eye.png"))); // NOI18N
        jButtonVer.setToolTipText("Ver información de un préstamo");
        jButtonVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerActionPerformed(evt);
            }
        });

        jButtonProyector.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButtonProyector.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/screen-proyector.png"))); // NOI18N
        jButtonProyector.setToolTipText("Administración de videoproyectores");
        jButtonProyector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProyectorActionPerformed(evt);
            }
        });

        jButtonSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/exit.png"))); // NOI18N
        jButtonSalir.setToolTipText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });

        jButtonInforme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/signing.png"))); // NOI18N
        jButtonInforme.setToolTipText("Generar Reportes");
        jButtonInforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInformeActionPerformed(evt);
            }
        });

        jButtonUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/update-arrows.png"))); // NOI18N
        jButtonUpdate.setToolTipText("Actualizar");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonRegistrar)
                .addGap(18, 18, 18)
                .addComponent(jButtonVer)
                .addGap(18, 18, 18)
                .addComponent(jButtonEliminar)
                .addGap(18, 18, 18)
                .addComponent(jButtonProyector)
                .addGap(18, 18, 18)
                .addComponent(jButtonInforme)
                .addGap(18, 18, 18)
                .addComponent(jButtonUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSalir)
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonVer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonProyector, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jButtonInforme))
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        jTable1 = new javax.swing.JTable(){
            public boolean isCellEditable (int rowIndex, int colIndex){
                return false;
            }
        };
        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(92, 172, 238));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Facultad de Medicina");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Aula de Medios");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel3.setText("Sistema de préstamo de videoproyectores");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel2))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Logo-UV2.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.setContentAreaFilled(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel4.setText("Universidad Veracruzana");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel4)))
                        .addGap(65, 65, 65)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarActionPerformed
        this.dispose();
        Registrar r = new Registrar();
        r.setVisible(true);
    }//GEN-LAST:event_jButtonRegistrarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        // TODO add your handling code here:
        int fila = jTable1.getSelectedRow();  //En esta parate es la funcion del boton eliminar
        if(fila == -1){
            JOptionPane.showMessageDialog(null,"Debe seleccionar una fila", "Eliminar", JOptionPane.WARNING_MESSAGE);
        }else{
            int r = JOptionPane.showConfirmDialog(null, "Toda la información relacionada con el proyector se eliminara \n"+"¿Esta seguro que desea hacerlo?","Eliminar la información del registro", JOptionPane.YES_NO_OPTION);
            if(r == JOptionPane.YES_OPTION){
                try{
                    id = jTable1.getValueAt(fila, 0).toString();   
                    PreparedStatement pst = cn.prepareStatement("delete from prestamo where folio = '"+id+"'");
                    pst.executeUpdate();
                    cargarDatos();
            }catch(SQLException e){
                e.getMessage();
            }
            }
        }
        jButtonEliminar.setFocusable(false);
        jTextFieldBuscar.requestFocusInWindow();    
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerActionPerformed
        // TODO add your handling code here:
        int fila = jTable1.getSelectedRow();  //En esta parte es la funcion del boton ver
        if(fila==-1){
            JOptionPane.showMessageDialog(null,"Debe seleccionar una fila", "Ver", JOptionPane.WARNING_MESSAGE);
        }else{
            id = jTable1.getValueAt(fila, 0).toString();       
            this.dispose();
            Ver v = new Ver();
            v.setVisible(true);
        }
        jTextFieldBuscar.requestFocusInWindow();   
        
    }//GEN-LAST:event_jButtonVerActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        // TODO add your handling code here:
        if(jTextFieldBuscar.getText().isEmpty()){  //En esta parte es todo lo del boton buscar. 
            JOptionPane.showMessageDialog(null, "Ingresa una palabra clave");
            jTextFieldBuscar.requestFocusInWindow();
        }else{
            if(jComboBoxFiltro.getSelectedIndex() == 0){
                JOptionPane.showMessageDialog(null, "Seleccione un filtro");
                 jComboBoxFiltro.requestFocusInWindow();
            }else{
                if(jComboBoxFiltro.getSelectedIndex() == 1){
                    buscarAula();
                }else{
                    if(jComboBoxFiltro.getSelectedIndex() == 2){
                        buscarEstado();
                    }else{
                        if(jComboBoxFiltro.getSelectedIndex() == 3){
                            buscarFecha();
                        }else{
                            if(jComboBoxFiltro.getSelectedIndex() == 4){
                                buscarInventario();
                            }else{
                                if(jComboBoxFiltro.getSelectedIndex() == 5){
                                    buscarMatricula();
                                }else{
                                    if(jComboBoxFiltro.getSelectedIndex() == 6){
                                        buscarNombre();
                                    }else{
                                        if(jComboBoxFiltro.getSelectedIndex() == 7){
                                            buscarNotas();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        jTextFieldBuscar.requestFocusInWindow();
       
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jComboBoxFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxFiltroActionPerformed

    private void jTextFieldBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarActionPerformed
        // TODO add your handling code here:        
    }//GEN-LAST:event_jTextFieldBuscarActionPerformed

    private void jTextFieldBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscarKeyPressed
        // TODO add your handling code here:
        if(jTextFieldBuscar.getText().isEmpty()){  //Mientras el usurario borre toda su busqueda se volvera cargar los datos que tenia en el estado anterior
            cargarDatos();
        }
        
    }//GEN-LAST:event_jTextFieldBuscarKeyPressed

    private void jTextFieldBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscarKeyTyped
        // TODO add your handling code here:
        String lim = jTextFieldBuscar.getText(); //En esta parte son la validaciones del sistema.
        if(lim.length()>=50){
            evt.consume();
            Toolkit t = Toolkit.getDefaultToolkit();
            t.beep();
        } 
    }//GEN-LAST:event_jTextFieldBuscarKeyTyped

    private void jButtonProyectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProyectorActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Proyector p = new Proyector();
        p.setVisible(true);
        
    }//GEN-LAST:event_jButtonProyectorActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        try {
            // TODO add your handling code here:
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        //En esta parte es para dar doble clic en la tabla y que cargue los datos.
        if(evt.getClickCount() == 2){
            int fila = jTable1.getSelectedRow();
            id = jTable1.getValueAt(fila, 0).toString();
            Actualizar act = new Actualizar();
            this.dispose();
            act.setVisible(true);   
        }
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTable1MousePressed

    private void jButtonInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInformeActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Reporte r = new Reporte();
        r.setVisible(true);
        
    }//GEN-LAST:event_jButtonInformeActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        // TODO add your handling code here:
        jTextFieldBuscar.setEditable(true);
        jTextFieldBuscar.setText(null);
        jTextFieldBuscar.requestFocusInWindow();
        jComboBoxFiltro.setSelectedIndex(0);
        cargarDatos();
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jComboBoxFiltroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxFiltroItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxFiltroItemStateChanged

    private void jComboBoxFiltroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxFiltroMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxFiltroMouseClicked

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonInforme;
    private javax.swing.JButton jButtonProyector;
    private javax.swing.JButton jButtonRegistrar;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JButton jButtonVer;
    private javax.swing.JComboBox<String> jComboBoxFiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldBuscar;
    // End of variables declaration//GEN-END:variables
}
