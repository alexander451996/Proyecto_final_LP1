package controlador;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionBD {
     private String cadenaConexion, usuario, contrasenia, ip, bd;
    private Statement instruccion;
    private ResultSet tablaResultado;
    private Connection conexion;
    private static ConexionBD objBD = new ConexionBD();
    
    private ConexionBD(){
        this.ip ="127.0.0.1";
        this.usuario = "root"; 
        this.contrasenia = ""; 
        this.bd = "baseproyecto";
    }
    
    public static ConexionBD getInstance(){
        return objBD;
    }
    
    public boolean conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver"); //Lo modifique para mi version de Mysql , este es el anterior: com.mysql.jdbc.Driver
            cadenaConexion = "jdbc:mysql://";
            cadenaConexion += ip;
            cadenaConexion += "/"+bd;
            cadenaConexion += "?userTimezone=true&serverTimezone=UTC";
            conexion = DriverManager.getConnection(cadenaConexion,usuario,contrasenia);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al conectar");
            return false;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean cerrar(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error cerrar la conexion");
            return false;
        }
        return true;
    }
    
    public Connection getConexion(){
        return conexion;
    }
}
