package ec.edu.ups.modelo;

import java.util.Objects;

/**
 *
 * @author Sebastian Uyaguari
 */
public class Usuario {
    
    private int codigo;
    private String usuario;
    private String contraseña;

    public Usuario() {
    }

    public Usuario(int codigo, String usuario, String contraseña) {
        this.codigo = codigo;
        this.setUsuario(usuario);
        this.setContraseña(contraseña);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = validarEspacios(usuario, 30);
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = validarEspacios(contraseña, 15);
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
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.usuario);
        hash = 67 * hash + Objects.hashCode(this.contraseña);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.contraseña, other.contraseña)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codigo=" + codigo + ", usuario=" + usuario + ", contrase\u00f1a=" + contraseña + '}';
    }
}
