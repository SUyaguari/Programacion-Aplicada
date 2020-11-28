package ec.edu.ups.modelo;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Sebastian Uyaguari
 */
public class Persona {
    
    private int codigo;
    private String cedula;
    private String genero;
    private String nombre;
    private String apellido;
    private String direccion;
    private Date fechaNacimiento;

    public Persona() {
    }

    public Persona(int codigo, String cedula, String genero, String nombre, String apellido, String direccion, Date fechaNacimiento) {
        this.codigo = codigo;
        this.setCedula(cedula);
        this.setGenero(genero);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setDireccion(direccion);
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = validarEspacios(cedula, 10);
    }
    
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = validarEspacios(genero, 10);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = validarEspacios(nombre, 50);
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = validarEspacios(apellido, 50);
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = validarEspacios(direccion, 75);
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String validarEspacios(String cadena, int numero){
        if(cadena.length()==numero){
            return cadena;
        }else{
            if(cadena.length()>numero){
                cadena = cadena.substring(0,numero);
                return cadena;
            }else{
                for (int i = cadena.length(); i < numero; i++) {
                    cadena+=" ";
                }
                return cadena;
            }
        }
    }
     
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persona{" + "codigo=" + codigo + ", cedula=" + cedula + ", genero=" + genero + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion + ", fechaNacimiento=" + fechaNacimiento + '}';
    }
    
}
