package ec.edu.ups.modelo;

import java.util.Objects;

/**
 *
 * @author Sebastian Uyaguari
 */
public class Actividad {
    
    private int codigo;
    private String nombreActividad;
    private Curso curso;
    private Persona docente;
    private String url;
    private String nombre;

    public Actividad() {
    }

    public Actividad(int codigo, String nombreActividad, Curso curso, Persona docente, String url, String nombre) {
        this.setNombreActividad(nombreActividad);
        this.codigo = codigo;
        this.curso = curso;
        this.docente = docente;
        this.setUrl(url);
        this.setNombre(nombre);
    }

    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = validarEspacios(nombreActividad, 30);
    }

    
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Persona getDocente() {
        return docente;
    }

    public void setDocente(Persona docente) {
        this.docente = docente;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = validarEspacios(url, 50);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = validarEspacios(nombre, 30);
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
        final Actividad other = (Actividad) obj;
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Actividad{" + "codigo=" + codigo + ", curso=" + curso + ", docente=" + docente + ", url=" + url + ", nombre=" + nombre + '}';
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
