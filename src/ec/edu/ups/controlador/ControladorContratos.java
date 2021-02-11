/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.ContratoBD;
import java.util.List;


/**
 *
 * @author Estudiantes
 */
public class ControladorContratos extends AbstractControlador<ContratoBD>{
    
    private static ControladorContratos instance = new ControladorContratos();

    public static ControladorContratos getInstance() {
        return instance;
    }
    
    
    public ContratoBD buscarCedula(String persona){  
        
        List<ContratoBD> lista = findAll();
        
        for (ContratoBD contratoBD : lista) {
            if(contratoBD.getPer().getCedula().equals(persona)){
                return contratoBD;
            }
        }
        return null;
        /*
        EntityManager em = getEntityManager();
        ContratoBD usuario=null;
        try {
            String jpql = "Select p from ContratoBD p where p.per='"+persona+"'";
            System.out.println(jpql);
            usuario = (ContratoBD) em.createQuery(jpql).getSingleResult();
            if(usuario!=null){
                return usuario;
            }
        } catch (NoResultException e) {
            System.out.println("Error Iniciar Sesion");
        }
        JOptionPane.showMessageDialog(null, "Persona no registrada");
        return null;*/
    }
}
