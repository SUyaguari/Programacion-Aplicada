package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Vehiculo;

public class ControladorVehiculo extends AbstractControlador<Vehiculo> {

    private static ControladorVehiculo instance;

    public ControladorVehiculo(){
        setRuta("datos/vehiculo.txt");
        leerDatosArchivo();
    }

    public static ControladorVehiculo getInstance(){
        if(instance == null){
            instance = new ControladorVehiculo();
        }
        return instance;
    }
}
