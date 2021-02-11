package ec.edu.ups.modelo;

import java.awt.Image;
import javax.swing.ImageIcon;



public class VehiculosParqueadero {

    private static ImageIcon image;
    private int puesto;
    private String tipo;

    public VehiculosParqueadero() {

    }

    public void asignarImagen(ImageIcon image){
        this.image= image;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void asignarPuesto(int puesto){
        this.puesto = puesto;
    }

    public int getPuesto() {
        return puesto;
    }

    public void asignarTipo(String tipo){
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "VehiculosParqueadero{" +
                "image=" + image +
                ", puesto=" + puesto +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
