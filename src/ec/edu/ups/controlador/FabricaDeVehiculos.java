package ec.edu.ups.controlador;

import ec.edu.ups.imagen.ControladorImagenes;
import ec.edu.ups.modelo.VehiculosParqueadero;

import java.util.HashMap;

public class FabricaDeVehiculos {

    private static HashMap<Integer, VehiculosParqueadero> lista  = new HashMap<>();;
    private static ControladorImagenes controladorImagenes = new ControladorImagenes();

    public static VehiculosParqueadero crearVehiculos(int posicion, String tipo){


        VehiculosParqueadero p = null;


        switch (tipo){
            case "Reservado":
                p = new VehiculosParqueadero();
                p.asignarImagen(controladorImagenes.asignarResrado());
                p.asignarTipo(tipo);
                p.asignarPuesto(posicion);
                break;
            case "Arrentamiento":
                p = new VehiculosParqueadero();
                p.asignarImagen(controladorImagenes.asignarArrendamiento());
                p.asignarTipo(tipo);
                p.asignarPuesto(posicion);
                break;
            case "Vacio":
                p = new VehiculosParqueadero();
                p.asignarImagen(controladorImagenes.asignarVacio());
                p.asignarTipo(tipo);
                p.asignarPuesto(posicion);
                break;
            case "Parqueado":
                p = new VehiculosParqueadero();
                p.asignarImagen(controladorImagenes.asignarIngreso());
                p.asignarTipo(tipo);
                p.asignarPuesto(posicion);
                break;
            case "Retirado":
                p = new VehiculosParqueadero();
                p.asignarImagen(controladorImagenes.asignarVacio());
                p.asignarTipo(tipo);
                p.asignarPuesto(posicion);
                break;
        }

        lista.put(posicion, p);
        return p;
    }

}
