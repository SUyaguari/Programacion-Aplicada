package ec.edu.ups.controlador;

import ec.edu.ups.modelo.VehiculoBD;
import static ec.edu.ups.utils.UtilsJPA.getEntityManager;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;

/**
 *
 * @author Estudiantes
 */
public class ControladorVehiculo extends AbstractControlador<VehiculoBD>{

 
    public VehiculoBD buscarPlaca(String placa){  
        
        EntityManager em = getEntityManager();
        VehiculoBD usuario=null;
        try {
            String jpql = "Select p from VehiculoBD p where p.placa='"+placa+"'";
            System.out.println(jpql);
            usuario = (VehiculoBD) em.createQuery(jpql).getSingleResult();
            if(usuario!=null){
                return usuario;
            }
        } catch (NoResultException e) {
            System.out.println("Error ");
        }
        JOptionPane.showMessageDialog(null, "Vehiculo no registrada");
        return null;
    }
}
