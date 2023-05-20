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
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import static spv.Menu.filas;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
/**
 *
 * @author Alberto Olmos
 */
public class Registrar extends javax.swing.JFrame {
    Conectar cc = new Conectar();  //Metodos para la conexion de la BD y para insertar datos. 
    Connection cn = cc.conexion();
    ResultSet rs = null;
    PreparedStatement pst = null;
    private static int valor;
    
    

    /**
     * Creates new form Registrar
     */
    public Registrar() {
        initComponents();
        llenarInventario();
        /*
        filas = filas + 1;
        String folio = String.valueOf(valor);
        */
        num();
        String folio = Integer.toString(valor+1);
        jTextFieldFolio.setText(folio);
        this.setLocationRelativeTo(null);
        jTextFieldNombre.requestFocusInWindow();
        this.setTitle("Registrar préstamo de videoproyector");
        this.getContentPane().setBackground(new java.awt.Color(92,172,238));
        
    }
    
        public void num(){
        try{
            String sql= "select * from prestamo";
            pst=cn.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                valor = rs.getInt("folio");
            }
            System.out.println(valor);
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void llenarInventario(){//Se llena desde la base de datos los inventarios de los proyectores que solo funcionan
        try{
            String llenar = "select * from llenarInventario";
            pst=cn.prepareStatement(llenar);
            rs=pst.executeQuery();
            while(rs.next()){
                String inventario = rs.getString("inventario");
                jComboBoxInventario.addItem(inventario);
            }
        }catch(SQLException e){
            
        }
    }
    
    public void articulosAgotados(){
        try{
            Date fec = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
            String fecha = formato.format(fec);
            
            String insertar = "INSERT INTO agotado (id, fecha, VGA, HDMI, ext, bocinas) VALUES (?, ?, ? , ?, ?,?);";
            PreparedStatement pst = cn.prepareCall(insertar);
            pst.setInt(1, 0);
            pst.setString(2, fecha);
            if(jRadioButtonVGA.isSelected()){
                pst.setInt(3, 1);
            }else{
                pst.setInt(3, 0);
            }
            if(jRadioButtonHDMI.isSelected()){
                pst.setInt(4, 1);
            }else{
                pst.setInt(4, 0);
            }
            if(jRadioButtonExt.isSelected()){
                pst.setInt(5, 1);
            }else{
                pst.setInt(5, 0);
            }
            if(jRadioButtonBocinas.isSelected()){
                pst.setInt(6, 1);
            }else{
                pst.setInt(6, 0);
            }
            pst.execute();
            mensajeExitoso();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al guardar los datos");
        }
    }
    
    public void insertarDatos(){  //En esta parte se insertar los datos para la BD
        int folio = valor+1;
        String inventario = (String) jComboBoxInventario.getSelectedItem();
        Date fec = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
        String fecha = formato.format(fec);
        
        Date tiempo = new Date();
        String h ="HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(h);
        String hora = format.format(tiempo);
 
        String matricula = jTextFieldMatricula.getText();
        String nombre = jTextFieldNombre.getText();
        String materia = (String) jComboBoxMateria.getSelectedItem();
        String h1 = (String) jComboBoxHorario1.getSelectedItem();
        String h2 = (String) jComboBoxHorario2.getSelectedItem();
        String horario = (h1+"-"+h2);
        String maestro = jTextFieldMaestro.getText();
        String aula = (String) jComboBoxAula.getSelectedItem().toString();
        String vga = (String) jComboBoxVGA.getSelectedItem();
        String hdmi = (String) jComboBoxHDMI.getSelectedItem();
        String ext = (String) jComboBoxExt.getSelectedItem();
        String bocinas = (String) jComboBoxBocinas.getSelectedItem();
        String estado = (String) jComboBoxEstado.getSelectedItem();
        String notas = jTextAreaNotas.getText(); 
    
        try{
            String insertar = "insert into prestamo (folio, inventario, fecha, hora, matricula, nombre, materia, horario, maestro, aula, VGA, HDMI, extension, bocinas, estado, notas) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(insertar);
            pst.setInt(1, folio);
            pst.setString(2, inventario);
            pst.setString(3, fecha);
            pst.setString(4, hora);
            if(matricula.isEmpty()){
                matricula = "Sin datos";
                pst.setString(5, matricula);
                
            }else{
                 pst.setString(5, matricula);
                
            }
            if(maestro.isEmpty()){
                maestro = "Sin datos";
                pst.setString(6, nombre);
                
                
            }else{
                 pst.setString(6, nombre);
            }
            pst.setString(7, materia);
            pst.setString(8, horario);
            pst.setString(9, maestro);
            pst.setString(10, aula);
            pst.setString(11, vga);
            pst.setString(12, hdmi);
            pst.setString(13, ext);
            pst.setString(14, bocinas);
            pst.setString(15, estado);
            if(notas.isEmpty()){
                notas = "Ninguno.";
                 pst.setString(16, notas);
                
            }else
            {
                pst.setString(16, notas);
            }
            pst.execute();
            mensajeExitoso();
            cn.close();
        }catch(SQLException e){
            
            JOptionPane.showMessageDialog(null,"Error al guardar los datos");
        }
    }
    
     public void insertarDatosText(){  //En esta parte se insertar los datos para la BD
        int folio = valor+1;
        String inventario = (String) jComboBoxInventario.getSelectedItem();
        Date fec = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
        String fecha = formato.format(fec);
        
        Date tiempo = new Date();
        String h ="HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(h);
        String hora = format.format(tiempo);
 
        String matricula = jTextFieldMatricula.getText();
        String nombre = jTextFieldNombre.getText();
        String materia = jTextFieldMateria.getText();
        String h1 = (String) jComboBoxHorario1.getSelectedItem();
        String h2 = (String) jComboBoxHorario2.getSelectedItem();
        String horario = (h1+"-"+h2);
        String maestro = jTextFieldMaestro.getText();
        String aula = (String) jComboBoxAula.getSelectedItem().toString();
        String vga = (String) jComboBoxVGA.getSelectedItem();
        String hdmi = (String) jComboBoxHDMI.getSelectedItem();
        String ext = (String) jComboBoxExt.getSelectedItem();
        String bocinas = (String) jComboBoxBocinas.getSelectedItem();
        String estado = (String) jComboBoxEstado.getSelectedItem();
        String notas = jTextAreaNotas.getText(); 
    
        try{
            String insertar = "insert into prestamo (folio, inventario, fecha, hora, matricula, nombre, materia, horario, maestro, aula, VGA, HDMI, extension, bocinas, estado, notas) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(insertar);
            pst.setInt(1, folio);
            pst.setString(2, inventario);
            pst.setString(3, fecha);
            pst.setString(4, hora);
            if(matricula.isEmpty()){
                matricula = "Sin datos";
                pst.setString(5, matricula);
                
            }else{
                 pst.setString(5, matricula);
                
            }
            if(maestro.isEmpty()){
                maestro = "Sin datos";
                pst.setString(6, nombre);
                
                
            }else{
                 pst.setString(6, nombre);
            }
            pst.setString(7, materia);
            pst.setString(8, horario);
            pst.setString(9, maestro);
            pst.setString(10, aula);
            pst.setString(11, vga);
            pst.setString(12, hdmi);
            pst.setString(13, ext);
            pst.setString(14, bocinas);
            pst.setString(15, estado);
            if(notas.isEmpty()){
                notas = "Ninguno.";
                 pst.setString(16, notas);
                
            }else
            {
                pst.setString(16, notas);
            }
            pst.execute();
            mensajeExitoso();
            cn.close();
        }catch(SQLException e){
            
            JOptionPane.showMessageDialog(null,"Error al guardar los datos");
        }
    }
    

    
    
    public void mensajeExitoso()  //un metodo para que muestre un registro exitoso
    {
        JOptionPane.showMessageDialog(null, "Se ha registrado el préstamo");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldMatricula = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxMateria = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxHorario1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxHorario2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldMaestro = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxAula = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldFolio = new javax.swing.JTextField();
        jTextFieldMateria = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxInventario = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxEstado = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaNotas = new javax.swing.JTextArea();
        jButtonVolver = new javax.swing.JButton();
        jButtonAceptar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jComboBoxVGA = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jComboBoxHDMI = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jComboBoxExt = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jComboBoxBocinas = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jRadioButtonVGA = new javax.swing.JRadioButton();
        jRadioButtonHDMI = new javax.swing.JRadioButton();
        jRadioButtonExt = new javax.swing.JRadioButton();
        jRadioButtonBocinas = new javax.swing.JRadioButton();
        jButtonAgostado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Préstamo de Videoproyector");
        setBackground(new java.awt.Color(153, 178, 255));
        setResizable(false);

        jPanel5.setBackground(new java.awt.Color(92, 172, 238));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingresar datos del solicitante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Nombre");

        jTextFieldNombre.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreActionPerformed(evt);
            }
        });
        jTextFieldNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNombreKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Matricula");

        jTextFieldMatricula.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMatriculaActionPerformed(evt);
            }
        });
        jTextFieldMatricula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldMatriculaKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Materia");

        jComboBoxMateria.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxMateria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Ninguna", "Anatomía Humana I", "Anatomía Humana II", "Anatomía Radiológica", "Anatomía y radiología dental", "Anatomofisiologia", "Bioestadística", "Bioética", "Bioquímica Básica", "Bioquímica Clínica", "Cardiología", "Control de enfermedades crónico degenerativas", "Control de enfermedades transmisibles", "Demografía", "Educación Quirúrgica", "Embriología", "Endocrinología", "Epidemiologia y ecología", "Equipo radiográfico I", "Equipo Radiográfico II", "Estrategias educativas en salud", "Estudios radiográficos especiales", "Experiencia Recepcional", "Farmacología", "Física Medica", "Fisiología General", "Fisiología Humana", "Fisiología sistemática", "Fisiopatología Sindromatica", "Fisiopatología Sistematica", "Gastroenterología", "Genética", "Geriatría", "Ginecología", "Hematología", "Hemodinámica", "Higiene ", "Histología", "Imagenlogia", "Inmunología", "Internado de pregrado", "Medicina Nuclear", "Metodología de la investigación", "Microbiología", "Nefrología", "Neumología", "Obstétrica", "Oftalmología", "Oncología", "Optativas", "Organización y administración profesional", "Otorrinolaringología", "Parasitología", "Patología especial", "Patología general", "Patología quirúrgica", "Pediatría del niño enfermo", "Pediatría del niño sano", "Propedéutica clínica", "Psicología", "Psicología medica", "Psiquiatría", "Radiología", "Radiología Medica I", "Radiología Medica II", "Radiología Medica III", "Radioterapia", "Reumatología", "Salud materno infantil", "Salud reproductiva y educación sexual", "Seminario de ecografía y ultrasonido", "Semiología Clínica", "Servicio Social", "Socioantropología", "Taller de Mecánica", "Técnicas de enfermería para radiografía de diagnostico", "Terapéutica", "Traumatología y ortopedia", "Urología", "Otro" }));
        jComboBoxMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMateriaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Horario");

        jComboBoxHorario1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxHorario1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", " 7:00 ", " 8:00", " 9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00" }));
        jComboBoxHorario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxHorario1ActionPerformed(evt);
            }
        });

        jLabel5.setText("a");

        jComboBoxHorario2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxHorario2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", " 7:00", " 8:00", " 9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00" }));
        jComboBoxHorario2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxHorario2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Maestro");

        jTextFieldMaestro.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldMaestro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldMaestroKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Aula");

        jComboBoxAula.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxAula.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "1 ", "2", "3", "4", "6", "9", "10", "11", "12", "15", "16", "17", "18", "19", "20", "21", "23", "24", "25", "26", "Laboratorio", "Quirófano", "Anfiteatro", "Aula Magna", "Audiovisual 1", "Audiovisual 2", "Simuladores", "Otro" }));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Folio");

        jTextFieldFolio.setEditable(false);
        jTextFieldFolio.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jTextFieldMateria.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMateriaActionPerformed(evt);
            }
        });
        jTextFieldMateria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldMateriaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel1))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jComboBoxHorario1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxHorario2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextFieldMatricula)
                                    .addComponent(jComboBoxMateria, 0, 1, Short.MAX_VALUE)
                                    .addComponent(jTextFieldMateria))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(jComboBoxAula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(110, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(26, 26, 26)
                        .addComponent(jTextFieldMaestro)
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextFieldFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTextFieldMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxHorario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxHorario2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxAula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(92, 172, 238));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingresar articulos préstados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 14))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Inventario");

        jComboBoxInventario.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxInventario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        jComboBoxInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxInventarioActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Estado");

        jComboBoxEstado.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Prestado" }));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Notas");

        jTextAreaNotas.setColumns(20);
        jTextAreaNotas.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextAreaNotas.setLineWrap(true);
        jTextAreaNotas.setRows(5);
        jTextAreaNotas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextAreaNotasKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTextAreaNotas);

        jButtonVolver.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButtonVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/back.png"))); // NOI18N
        jButtonVolver.setToolTipText("Volver");
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });

        jButtonAceptar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButtonAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/checked.png"))); // NOI18N
        jButtonAceptar.setToolTipText("Registrar");
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("VGA");

        jComboBoxVGA.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxVGA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Si" }));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("HDMI");

        jComboBoxHDMI.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxHDMI.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Si" }));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Extension");

        jComboBoxExt.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxExt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Si" }));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Bocinas");

        jComboBoxBocinas.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxBocinas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Si" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButtonVolver)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAceptar))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxVGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxHDMI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxExt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxBocinas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(39, 39, 39)
                                .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBoxInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jComboBoxVGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jComboBoxHDMI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jComboBoxExt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jComboBoxBocinas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonVolver)
                    .addComponent(jButtonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(92, 172, 238));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Articulos agotados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 14))); // NOI18N

        jRadioButtonVGA.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jRadioButtonVGA.setText("VGA");

        jRadioButtonHDMI.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jRadioButtonHDMI.setText("HDMI");

        jRadioButtonExt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jRadioButtonExt.setText("Extensión");
        jRadioButtonExt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonExtActionPerformed(evt);
            }
        });

        jRadioButtonBocinas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jRadioButtonBocinas.setText("Bocinas");

        jButtonAgostado.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButtonAgostado.setText("Enviar");
        jButtonAgostado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgostadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jRadioButtonVGA)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonHDMI)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonExt)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonBocinas)
                .addGap(0, 59, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonAgostado)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonVGA)
                    .addComponent(jRadioButtonHDMI)
                    .addComponent(jRadioButtonExt)
                    .addComponent(jRadioButtonBocinas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jButtonAgostado)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombreActionPerformed

    private void jTextFieldMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMatriculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMatriculaActionPerformed

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        // TODO add your handling code here:
        //Sirve si el usuario desea volver y no queiere continuar con el registro
        if(!jTextFieldNombre.getText().isEmpty() || !jTextFieldMatricula.getText().isEmpty() || jComboBoxMateria.getSelectedIndex() !=0 || !jTextFieldMaestro.getText().isEmpty() || jComboBoxHorario1.getSelectedIndex() !=0 || jComboBoxHorario2.getSelectedIndex() !=0 || jComboBoxAula.getSelectedIndex() !=0 || jComboBoxInventario.getSelectedIndex() !=0 || jComboBoxEstado.getSelectedIndex() !=0 || !jTextAreaNotas.getText().isEmpty()){
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
            try {
                this.cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Registrar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonVolverActionPerformed

    private void jComboBoxHorario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxHorario1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxHorario1ActionPerformed

    private void jComboBoxHorario2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxHorario2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxHorario2ActionPerformed

    private void jComboBoxInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxInventarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxInventarioActionPerformed

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
        // TODO add your handling code here:
        if(jTextFieldNombre.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Por favor, ingresar nombre");
        }else{
              if((jComboBoxMateria.getSelectedIndex() == 0 && jTextFieldMateria.getText().isEmpty()) || jComboBoxHorario1.getSelectedIndex() == 0 || jComboBoxHorario2.getSelectedIndex() == 0 || jComboBoxAula.getSelectedIndex() == 0 || jComboBoxInventario.getSelectedIndex() == 0 || jComboBoxEstado.getSelectedIndex() == 0){
                  JOptionPane.showMessageDialog(null, "Falta seleccionar elemento(s)");  
              }else{
                  if(jComboBoxMateria.getSelectedIndex() == 0 && !jTextFieldMateria.getText().isEmpty()){
                      insertarDatosText(); 
                      this.dispose();
                      Menu m = new Menu();
                      m.setVisible(true);
                  }else{
                      if(jComboBoxMateria.getSelectedIndex() > 0 && jTextFieldMateria.getText().isEmpty()){
                          insertarDatos();
                          this.dispose();
                          Menu m = new Menu();
                          m.setVisible(true);
                      }
                  }
              }
        }
    }//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jComboBoxMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMateriaActionPerformed
        // TODO add your handling code here:
        if(jComboBoxMateria.getSelectedIndex()==0){
            jTextFieldMateria.setEnabled(true);
        }else{
            jTextFieldMateria.setEnabled(false);
        }
        jTextFieldMateria.setText(null);
    }//GEN-LAST:event_jComboBoxMateriaActionPerformed

    private void jTextFieldNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNombreKeyTyped
        // TODO add your handling code here:
        //Validaciones
        String lim = jTextFieldNombre.getText();
        if(lim.length()>=50){
            evt.consume();
            Toolkit t = Toolkit.getDefaultToolkit();
            t.beep();
        }
        
        char c = evt.getKeyChar();
        if(Character.isDigit(c)){
            getToolkit().beep();
            evt.consume();
        }   
    }//GEN-LAST:event_jTextFieldNombreKeyTyped

    private void jTextFieldMatriculaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldMatriculaKeyTyped
        // TODO add your handling code here:
        //Validaciones
        String lim = jTextFieldMatricula.getText();
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
    }//GEN-LAST:event_jTextFieldMatriculaKeyTyped

    private void jTextFieldMaestroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldMaestroKeyTyped
        // TODO add your handling code here:
        //Validaciones
        String lim = jTextFieldMaestro.getText();
        if(lim.length()>=50){
            evt.consume();
            Toolkit t = Toolkit.getDefaultToolkit();
            t.beep();
        }
        
        char c = evt.getKeyChar();
        if(Character.isDigit(c)){
            getToolkit().beep();
            evt.consume();
        }   
    }//GEN-LAST:event_jTextFieldMaestroKeyTyped

    private void jTextAreaNotasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaNotasKeyTyped
        // TODO add your handling code here:
        //Validaciones
        String lim = jTextAreaNotas.getText();
        if(lim.length()>=256){
            evt.consume();
            Toolkit t = Toolkit.getDefaultToolkit();
            t.beep();
        }
    }//GEN-LAST:event_jTextAreaNotasKeyTyped

    private void jRadioButtonExtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonExtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonExtActionPerformed

    private void jButtonAgostadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgostadoActionPerformed
        // TODO add your handling code here:
        
        if(!jRadioButtonVGA.isSelected() && !jRadioButtonHDMI.isSelected() && !jRadioButtonExt.isSelected() && !jRadioButtonBocinas.isSelected()){
            JOptionPane.showMessageDialog(null, "Falta seleccionar articulo(s)");
        }else{
            articulosAgotados();
            this.dispose();
            Menu m = new Menu();
            m.setVisible(true);
        }
        
    }//GEN-LAST:event_jButtonAgostadoActionPerformed

    private void jTextFieldMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMateriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMateriaActionPerformed

    private void jTextFieldMateriaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldMateriaKeyTyped
        // TODO add your handling code here:
         String lim = jTextFieldMateria.getText();
        if(lim.length()>=60){
            evt.consume();
            Toolkit t = Toolkit.getDefaultToolkit();
            t.beep();
        }    
    }//GEN-LAST:event_jTextFieldMateriaKeyTyped

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
            java.util.logging.Logger.getLogger(Registrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonAgostado;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JComboBox<String> jComboBoxAula;
    private javax.swing.JComboBox<String> jComboBoxBocinas;
    private javax.swing.JComboBox<String> jComboBoxEstado;
    private javax.swing.JComboBox<String> jComboBoxExt;
    private javax.swing.JComboBox<String> jComboBoxHDMI;
    private javax.swing.JComboBox<String> jComboBoxHorario1;
    private javax.swing.JComboBox<String> jComboBoxHorario2;
    private javax.swing.JComboBox<String> jComboBoxInventario;
    private javax.swing.JComboBox<String> jComboBoxMateria;
    private javax.swing.JComboBox<String> jComboBoxVGA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButtonBocinas;
    private javax.swing.JRadioButton jRadioButtonExt;
    private javax.swing.JRadioButton jRadioButtonHDMI;
    private javax.swing.JRadioButton jRadioButtonVGA;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaNotas;
    private javax.swing.JTextField jTextFieldFolio;
    private javax.swing.JTextField jTextFieldMaestro;
    private javax.swing.JTextField jTextFieldMateria;
    private javax.swing.JTextField jTextFieldMatricula;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables

}
