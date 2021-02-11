package ec.edu.ups.imagen;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public class ControladorImagenes {

    public Icon asignarResrado(){
        ImageIcon i =   new ImageIcon(getClass().getResource("reservad.png"));
        Icon icino = new ImageIcon(i.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
        return icino;
    }

    public Icon asignarArrendamiento(){
        
        ImageIcon i =   new ImageIcon(getClass().getResource("arrendamient.png"));
        Icon icino = new ImageIcon(i.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
        return icino;
    }

    public Icon asignarVacio(){
                ImageIcon i =   new ImageIcon(getClass().getResource("rectangulo.png"));
        Icon icino = new ImageIcon(i.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
        return icino;
    }

    public Icon asignarIngreso(){
        ImageIcon i =   new ImageIcon(getClass().getResource("cochecopia.png"));
        Icon icino = new ImageIcon(i.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
        return icino;
    }
}
