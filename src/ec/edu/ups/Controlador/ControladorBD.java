/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Estudiantes
 */
public class ControladorBD {
    
    public static Connection conectar(){
       Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbPracticaClase", "postgres","pandicornios");
            
        } catch (Exception ex) {
            System.out.println("Error Conexion base");
            ex.printStackTrace();
        }
        return con;
        
    }
    
    public static void desconectar(Connection con){
        
        try {
            con.close();
        } catch (SQLException ex) {
           
        }
    }
}
