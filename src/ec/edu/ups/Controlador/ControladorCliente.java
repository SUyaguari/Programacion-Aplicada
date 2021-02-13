/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.Controlador;

import ec.edu.ups.modelo.Asunto;
import ec.edu.ups.modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estudiantes
 */
public class ControladorCliente {
    
    public boolean insertarCliente(Cliente cliente){
        
        Connection connection = ControladorBD.conectar();
        try{
            String sql = "INSERT INTO CLIENTE(codigo, cedula, nombre, apellido, direccion) "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, cliente.getCodigo());
            ps.setString(2, cliente.getCedula());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getApellido());
            ps.setString(5, cliente.getDireccion());
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            return false;
        }finally{
            ControladorBD.desconectar(connection);
        }
    }
    
    public List<Cliente> listar(){
        List<Cliente> lista = new ArrayList<>();
        
        Connection con = ControladorBD.conectar();
        
        try{
            String sql = "SELECT * FROM PROCURADORES";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()){
                Cliente p = new Cliente(resultado.getLong(1), resultado.getString(2), 
                        resultado.getString(3), resultado.getString(4), resultado.getString(5));
                lista.add(p);
            }
        }catch(Exception e){
            
        }
        return lista ;
    }
}
