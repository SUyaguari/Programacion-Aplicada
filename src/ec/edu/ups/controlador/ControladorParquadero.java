package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Parqueadero;
import ec.edu.ups.modelo.Vehiculo;

import java.util.List;

public class ControladorParquadero extends AbstractControlador<Parqueadero> {

    private static ControladorParquadero instance;

    public ControladorParquadero() {
        setRuta("datos/parqueadero.txt");
        leerDatosArchivo();
    }

    public static ControladorParquadero getInstance(){
        if(instance == null){
            instance = new ControladorParquadero();
        }
        return instance;
    }

    public int generarId(){
        List<Parqueadero> lista = getLista();
        int mayor = -100;
        if(!lista.isEmpty()){
            for (Parqueadero parqueadero: lista){
                if(parqueadero.getCodigo()>mayor){
                    mayor = parqueadero.getCodigo();
                }
            }
            return mayor+1;
        }
        return 1;
    }

    public boolean comprobarPuestos(Vehiculo vehiculo, int puesto){
        List<Parqueadero> lista = getLista();
        for(Parqueadero obheto :lista){
            if((obheto.getPuesto()==puesto && (!obheto.getTipo().equals("Retirado") )) ||
                obheto.getVehiculo().equals(vehiculo) && !obheto.getTipo().equals("Retirado")){
                return false;
            }
        }
        return true;
    }
}
