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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Estudiantes
 */
public class ControladorAsunto {
    
    public void ingresarAsunto(Asunto asunto){
        Connection con = ControladorBD.conectar();
        String sql = "INSERT INTO ASUNTO(codigo, inicio, fin, estado, cliente_fk) "
                    + "VALUES (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, asunto.getCodigo());
            ps.setDate(2, new java.sql.Date(asunto.getInicio().getTime()));
            ps.setDate(3, new java.sql.Date(asunto.getInicio().getTime()));
            ps.setString(4, asunto.getEstado());
            ps.setLong(5, asunto.getCliente().getCodigo());
        } catch (SQLException ex) {
            System.out.println("Error");
        }
    }
    
    public List<Asunto> listar(){
        List<Asunto> lista = new ArrayList<>();
        
        Connection con = ControladorBD.conectar();
        
        try{
            String sql = "SELECT * FROM PROCURADORES";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()){
                Asunto p = new Asunto();
                p.setInicio(new Date(resultado.getDate(2).getTime()));
                p.setFin(new Date(resultado.getDate(3).getTime()));
                p.setEstado(resultado.getString(4));
                p.setCliente((Cliente) resultado.getObject(4));
                lista.add(p);
            }
        }catch(Exception e){
            
        }
        return lista ;
    }
}
