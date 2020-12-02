/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import java.util.Objects;

/**
 *
 * @author Estudiantes
 */
public class Estudiantes {
    
    private int codigo;
    private String nombre;
    private String apellido;
    private Curso curso;
    private Persona docente;

    public Estudiantes() {
    }

    public Estudiantes(Curso curso, Persona docente) {
        this.curso = curso;
        this.docente = docente;
    }

    public Estudiantes(int codigo, String nombre, String apellido, Curso curso, Persona docente) {
        this.codigo = codigo;
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.curso = curso;
        this.docente = docente;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.curso);
        hash = 59 * hash + Objects.hashCode(this.docente);
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
        final Estudiantes other = (Estudiantes) obj;
        if (!Objects.equals(this.curso, other.curso)) {
            return false;
        }
        if (!Objects.equals(this.docente, other.docente)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Estudiantes{" + "codigo=" + codigo + ", nombre=" + nombre + ", apellido=" + apellido + ", curso=" + curso + ", docente=" + docente + '}';
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
