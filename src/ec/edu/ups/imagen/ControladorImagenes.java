package ec.edu.ups.imagen;

import javafx.scene.image.Image;

public class ControladorImagenes {

    public Image asignarResrado(){
        return  new Image(getClass().getResourceAsStream("reservad.png"), 50, 40, true, true);
    }

    public Image asignarArrendamiento(){
        return new Image(getClass().getResourceAsStream("arrendamient.png"), 50, 40, true, true) ;
    }

    public Image asignarVacio(){
        return  new Image(getClass().getResourceAsStream("proteccio.png"), 50, 40, true, true) ;
    }

    public Image asignarIngreso(){
        return new Image(getClass().getResourceAsStream("cochecopia.png"), 50, 40, true, true) ;
    }
}
