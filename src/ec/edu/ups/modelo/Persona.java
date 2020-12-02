package ec.edu.ups.modelo;

import java.util.Objects;

/**
 *
 * @author Sebastian Uyaguari
 */
public class Persona {
    
    private int codigo;
    private String rol;
    private String cedula;
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;

    public Persona() {
    
    }

    public Persona(int codigo, String rol, String cedula, String nombre, String apellido, String correo, String contraseña) {
        this.setCodigo(codigo);
        this.setRol(rol);
        this.setCedula(cedula);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setCorreo(correo);
        this.setContraseña(contraseña);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = validarEspacios(rol, 7);
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = validarEspacios(cedula, 10);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = validarEspacios(nombre, 25);
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = validarEspacios(apellido, 25);
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = validarEspacios(correo, 30);
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = validarEspacios(contraseña, 20);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.correo);
        hash = 19 * hash + Objects.hashCode(this.contraseña);
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
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        if (!Objects.equals(this.contraseña, other.contraseña)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Persona{" + "codigo=" + codigo + ", rol=" + rol + ", cedula=" 
                + cedula + ", nombre=" + nombre + ", apellido=" + apellido 
                + ", correo=" + correo + ", contrase\u00f1a=" + contraseña + '}';
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
    
}
