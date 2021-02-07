/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Persona;
import static ec.edu.ups.utils.UtilsJPA.getEntityManager;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author Estudiantes
 */
public class ControladorPersona extends AbstractControlador<Persona>{
 
    private static ControladorPersona instance = new ControladorPersona();

    public static ControladorPersona getInstance() {
        return instance;
    }
    
    
    public Persona buscarCedula(String cedula){
        
        EntityManager em = getEntityManager();
        try {
            String jpql = "Select p from Persona p where p.cedula='"+cedula+"'";
            Persona persona = (Persona) em.createQuery(jpql).getSingleResult();
            return persona;
        } catch (NoResultException e) {
            System.out.println("Error Buscar cedula");
        }
        return null;
    }
    
    
}
