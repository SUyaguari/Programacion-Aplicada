package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Casa;
import static ec.edu.ups.utils.UtilsJPA.getEntityManager;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author Estudiantes
 */
public class ControladorCasa extends AbstractControlador<Casa>{

    private static ControladorCasa instance = new ControladorCasa();
    
    public static ControladorCasa getInstance(){
        return instance;
    }
    
    public Casa buscarDireccion(String direccion){
     
        EntityManager em = getEntityManager();
        
        try {
            String jpql = "Select c from Casa c where c.direccion='"+direccion+"'";
            Casa casa = (Casa) em.createQuery(jpql).getSingleResult();
            return casa;
        } catch (NoResultException e) {
            System.out.println("Error Buscar cedula");
        }
        return null;
    }
    
}
