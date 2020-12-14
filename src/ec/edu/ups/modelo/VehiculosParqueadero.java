package ec.edu.ups.modelo;

import javafx.scene.image.Image;

public class VehiculosParqueadero {

    private static Image image;
    private int puesto;
    private String tipo;

    public VehiculosParqueadero() {

    }

    public void asignarImagen(Image image){
        this.image= image;
    }

    public Image getImage() {
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
