/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spv;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static spv.Menu.id;

/**
 *
 * @author Alberto Olmos
 */
public class Actualizar extends javax.swing.JFrame {
    Conectar cc = new Conectar();  //Metodos para conectar la BD y para actualizar los datos.
    Connection cn = cc.conexion();
    ResultSet rs = null;
    PreparedStatement pst = null;
    

    /**
     * Creates new form Actualizar
     */
    public Actualizar() {
        initComponents();
        this.getContentPane().setBackground(new java.awt.Color(92,172,238));
        //imagen();
        this.setLocationRelativeTo(null);
        this.setTitle("Actualizar información del préstamo del videoproyector");
        llenarInventario();
        mostrarDatos();
        
    }
    
    
    public void mostrarDatos(){  //Este metodo recibe desde la pantalla principal el folio, una vez obtenido el folio es enviado a la BD para que empieze a cargar los datos que ya fueron almacenados
        try{
            String sql= "select * from prestamo where folio = '"+id+"'";
            pst=cn.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String folio = rs.getString("folio");
                jTextFieldFolio.setText(folio);
                
                String inventario = rs.getString("inventario");
                jComboBoxInventario.setSelectedItem(inventario);
                
                String nombre = rs.getString("nombre");
                jTextFieldNombre.setText(nombre);
                
                String matricula = rs.getString("matricula");
                jTextFieldMatricula.setText(matricula);
                
                
                String materia = rs.getString("materia");
                jComboBoxMateria.addItem(materia);
                jComboBoxMateria.setSelectedItem(materia);
                
                String horario = rs.getString("horario");
                jTextFieldHorario.setText(horario);
                
                String maestro = rs.getString("maestro");
                jTextFieldMaestro.setText(maestro);
                
                String aula = rs.getString("aula");
                jComboBoxAula.setSelectedItem(aula);
                
                String vga = rs.getString("VGA");
                jComboBoxVGA.setSelectedItem(vga);
                
                String hdmi = rs.getString("HDMI");
                jComboBoxHDMI.setSelectedItem(hdmi);
                
                String ext = rs.getString("extension");
                jComboBoxExt.setSelectedItem(ext);
                
                String boc = rs.getString("bocinas");
                jComboBoxBocinas.setSelectedItem(boc);
                
                String est = rs.getString("estado");
                jComboBoxEstado.setSelectedItem(est);
                
                String n = rs.getString("notas");
                jTextAreaNotas.setText(n);
            }
        }catch(Exception e){   
        }
    }
    
    public void llenarInventario(){  //Somplemente llena el invetario desde la BD pero solo con los proyectores que funcionan
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
    
    public void actualizar(){  //En esta parte es donde se insertan los datos para su actualización
        try{
            String sql = "update prestamo set inventario = '"+jComboBoxInventario.getSelectedItem().toString()+"', matricula= '"+jTextFieldMatricula.getText()+"',nombre = '"+jTextFieldNombre.getText()+"' , materia = '"+jComboBoxMateria.getSelectedItem().toString()+"' , horario='"+jTextFieldHorario.getText()+"', maestro = '"+jTextFieldMaestro.getText()+"', aula = '"+jComboBoxAula.getSelectedItem().toString()+"', VGA='"+jComboBoxVGA.getSelectedItem().toString()+"', HDMI='"+jComboBoxHDMI.getSelectedItem().toString()+"', extension = '"+jComboBoxExt.getSelectedItem().toString()+"', bocinas = '"+jComboBoxBocinas.getSelectedItem().toString()+"', estado = '"+jComboBoxEstado.getSelectedItem().toString()+"', notas = '"+jTextAreaNotas.getText()+"' where folio = '"+jTextFieldFolio.getText()+"'                                                        ";
            PreparedStatement pss = cn.prepareStatement(sql);
            int opc = pss.executeUpdate();
            if(opc>0){
                JOptionPane.showMessageDialog(null,"Actualización Correcta");
            }else{
                JOptionPane.showMessageDialog(this, "No puedo actualizarse correctamente","Error",JOptionPane.WARNING_MESSAGE);
            }
            cn.close();
        }catch(SQLException e){
            
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

        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldMatricula = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxMateria = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldMaestro = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxAula = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldFolio = new javax.swing.JTextField();
        jTextFieldHorario = new javax.swing.JTextField();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Actualizar Información del Videoproyector");
        setResizable(false);

        jPanel5.setBackground(new java.awt.Color(92, 172, 238));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información del solicitante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 14))); // NOI18N

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
        jComboBoxAula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAulaActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Folio");

        jTextFieldFolio.setEditable(false);
        jTextFieldFolio.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jTextFieldHorario.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldHorario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldHorarioKeyTyped(evt);
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
                            .addComponent(jLabel18)
                            .addComponent(jLabel1))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jTextFieldMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxAula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxMateria, 0, 1, Short.MAX_VALUE)
                            .addComponent(jTextFieldHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxAula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(92, 172, 238));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información de los articulos préstados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 14))); // NOI18N

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
        jComboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Prestado", "Entregado" }));

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
        jButtonAceptar.setToolTipText("Actualizar");
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
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jComboBoxMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMateriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMateriaActionPerformed

    private void jComboBoxInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxInventarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxInventarioActionPerformed

    private void jTextFieldNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNombreKeyTyped
        // TODO add your handling code here:
        // Validaciones
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

    private void jTextFieldHorarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldHorarioKeyTyped
        // TODO add your handling code here:
        //Validaciones
        String lim = jTextFieldHorario.getText();
        if(lim.length()>=11){
            evt.consume();
            Toolkit t = Toolkit.getDefaultToolkit();
            t.beep();
        }
    }//GEN-LAST:event_jTextFieldHorarioKeyTyped

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
        // TODO add your handling code here:
        //Una vez que se haya llenado los datos con la nueva informacion se podra almacenar correctarmete.
        if(jTextFieldNombre.getText().isEmpty() || jTextFieldMatricula.getText().isEmpty() || jTextFieldHorario.getText().isEmpty() || jTextFieldMaestro.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Campos vacíos");
        }else{
            if(jComboBoxMateria.getSelectedIndex() == 0 || jComboBoxAula.getSelectedIndex() == 0 || jComboBoxInventario.getSelectedIndex() == 0 || jComboBoxEstado.getSelectedIndex() == 0){
                JOptionPane.showMessageDialog(null, "Falta seleccionar elemento(s)");
            }
            else{
                actualizar();
                this.dispose();
                Menu m = new Menu();
                m.setVisible(true);
            }
        }
    }//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        // TODO add your handling code here:
        //Se hace una validacion en caso que el usuario quiera volver y no se guarde la informacion o bien se guarde.
        if(!jTextFieldNombre.getText().isEmpty() || !jTextFieldMatricula.getText().isEmpty() || jComboBoxMateria.getSelectedIndex() !=0 || !jTextFieldMaestro.getText().isEmpty() || !jTextFieldHorario.getText().isEmpty() || jComboBoxAula.getSelectedIndex() !=0 || jComboBoxInventario.getSelectedIndex() !=0 || jComboBoxEstado.getSelectedIndex() !=0 || !jTextAreaNotas.getText().isEmpty()){
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
    }//GEN-LAST:event_jButtonVolverActionPerformed

    private void jComboBoxAulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxAulaActionPerformed

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
            java.util.logging.Logger.getLogger(Actualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Actualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Actualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Actualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Actualizar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JComboBox<String> jComboBoxAula;
    private javax.swing.JComboBox<String> jComboBoxBocinas;
    private javax.swing.JComboBox<String> jComboBoxEstado;
    private javax.swing.JComboBox<String> jComboBoxExt;
    private javax.swing.JComboBox<String> jComboBoxHDMI;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaNotas;
    private javax.swing.JTextField jTextFieldFolio;
    private javax.swing.JTextField jTextFieldHorario;
    private javax.swing.JTextField jTextFieldMaestro;
    private javax.swing.JTextField jTextFieldMatricula;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
}
