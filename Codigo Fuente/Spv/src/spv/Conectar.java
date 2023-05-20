/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Alberto Olmos
 */
public class Conectar {
    Connection conectar = null;
    public Connection conexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conectar=DriverManager.getConnection("jdbc:mysql://localhost/videoproyectores","root","");
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Sin conexión a BD");
            System.exit(0);
        }
        return conectar;
    }
}

