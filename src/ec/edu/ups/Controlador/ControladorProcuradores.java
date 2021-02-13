/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.Controlador;

import ec.edu.ups.modelo.Asunto;
import ec.edu.ups.modelo.Procuradores;
import java.sql.Array;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estudiantes
 */
public class ControladorProcuradores {
    
    public void ingresarProcuradores(Procuradores procuradores){
        
        Connection connection = ControladorBD.conectar();
        try{
            String sql = "INSERT INTO PROCURADORES(codigo, cedula, nombre, apellido, direccion, asuntos_fk) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, procuradores.getCodigo());
            ps.setString(2, procuradores.getCedula());
            ps.setString(3, procuradores.getNombre());
            ps.setString(4, procuradores.getApellido());
            ps.setString(5, procuradores.getDireccion());
            ps.setLong(6, procuradores.getAsuntos().getCodigo());
            ps.executeUpdate();
            
        }catch(Exception e){
            
        }finally{
            ControladorBD.desconectar(connection);
        }
    }
    
    public List<Procuradores> listar(){
        List<Procuradores> lista = new ArrayList<>();
        
        Connection con = ControladorBD.conectar();
        
        try{
            String sql = "SELECT * FROM PROCURADORES";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()){
                Asunto a = new Asunto();
                a.setCodigo(resultado.getLong(6));
                Procuradores p = new Procuradores(resultado.getLong(1), resultado.getString(2), 
                        resultado.getString(3), resultado.getString(4), resultado.getString(5),a);
                lista.add(p);
            }
        }catch(Exception e){
            
        }
        return lista ;
    }
}
