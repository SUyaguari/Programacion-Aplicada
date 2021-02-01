package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Usuario;
import static ec.edu.ups.utils.UtilsJPA.getEntityManager;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author Estudiantes
 */
public class ControladroUsuario extends AbstractControlador<Usuario>{
    
    private static ControladroUsuario instance = new ControladroUsuario();

    public static ControladroUsuario getInstance() {
        return instance;
    }
    
    public Object iniciarSesion(String correo, String contrasñea){  
        
        EntityManager em = getEntityManager();
        
        try {
            String jpql = "Select u from Usuario u where u.correo='"+correo+"'";
            Usuario usuario = (Usuario) em.createQuery(jpql).getSingleResult();
            if(usuario.getContraseña().equals(contrasñea)){
                return usuario;
            }
            JOptionPane.showMessageDialog(null, "Contraseña Incorrecta");
            return null;
        } catch (NoResultException e) {
            System.out.println("Error Iniciar Sesion");
        }
        JOptionPane.showMessageDialog(null, "Usuario no registrado");
        return null;
    }
}
