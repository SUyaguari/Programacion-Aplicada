package ec.edu.ups.controlador;

import ec.edu.ups.modelo.ParqueaderoBD;
import ec.edu.ups.modelo.VehiculoBD;
import java.util.List;

/**
 *
 * @author Estudiantes
 */
public class ControladorParqueadero extends AbstractControlador<ParqueaderoBD>{
    
    /*public boolean comprobarPuestos(VehiculoBD vehiculo, int puesto){
        List<ParqueaderoBD> lista = getLista();
        for(ParqueaderoBD obheto :lista){
            if((obheto.getPuesto()==puesto && (!obheto.getTipo().equals("Retirado") )) ||
                obheto.getVehiculo().equals(vehiculo) && !obheto.getTipo().equals("Retirado")){
                return false;
            }
        }
        return true;
    }*/
}