package ec.edu.ups.controlador;

import ec.edu.ups.modelo.PersonaBD;
import static ec.edu.ups.utils.UtilsJPA.getEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;

/**
 *
 * @author Estudiantes
 */
public class ControladorPersona extends AbstractControlador<PersonaBD>{

   
    
    public PersonaBD iniciarSesion(String correo, String contrasñea){  
        
        EntityManager em = getEntityManager();
        PersonaBD usuario=null;
        try {
            System.out.println("holi "+correo+contrasñea);
            String jpql = "Select p from PersonaBD p where p.correo='"+correo+"'";
            System.out.println(jpql);
            usuario = (PersonaBD) em.createQuery(jpql).getSingleResult();
            if(usuario.getContraseña().equals(contrasñea)){
                return usuario;
            }else{
                JOptionPane.showMessageDialog(null, "Contraseña Incorrecta");
                return null;
            }
        } catch (NoResultException e) {
            System.out.println("Error Iniciar Sesion");
        }
            System.out.println(usuario+"");
        
        JOptionPane.showMessageDialog(null, "Usuario no registrado");
        return null;
    }
    
    public PersonaBD buscarCedula(String cedula){  
        
        EntityManager em = getEntityManager();
        PersonaBD usuario=null;
        try {
            String jpql = "Select p from PersonaBD p where p.cedula='"+cedula+"'";
            System.out.println(jpql);
            usuario = (PersonaBD) em.createQuery(jpql).getSingleResult();
            if(usuario!=null){
                return usuario;
            }
        } catch (NoResultException e) {
            System.out.println("Error Iniciar Sesion");
        }
        JOptionPane.showMessageDialog(null, "Persona no registrada");
        return null;
    }

    
}
