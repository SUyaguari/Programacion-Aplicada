package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Persona;

import java.util.List;

public class ControladorPersona extends AbstractControlador<Persona> {

    private static ControladorPersona instance;

    public ControladorPersona() {
        setRuta("datos/persona.txt");
        leerDatosArchivo();
    }


    public static ControladorPersona getInstance(){
        if(instance == null){
            instance = new ControladorPersona();
        }
        return instance;
    }



    public Persona iniciarSesion(Persona persona){

        List<Persona> lista = getLista();

        for (Persona persona1 : lista) {
            if(persona1.equals(persona)){
                return persona1;
            }
        }
        return null;
    }
}
