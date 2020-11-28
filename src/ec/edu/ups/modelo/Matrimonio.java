package ec.edu.ups.modelo;

import java.util.Date;

/**
 *
 * @author Sebastian Uyaguari
 */
public class Matrimonio {
 
    private int codigo;
    private String lugar;
    private Date fecha;
    private Persona contrayente1;
    private Persona contrayente2;
    private Persona testigo1;
    private Persona testigo2;
    private Persona autoridad;

    public Matrimonio() {
    }

    public Matrimonio(int codigo, String lugar, Date fecha, Persona contrayente1, Persona contrayente2, Persona testigo1, Persona testigo2, Persona autoridad) {
        this.codigo = codigo;
        this.setLugar(lugar);
        this.fecha = fecha;
        this.contrayente1 = contrayente1;
        this.contrayente2 = contrayente2;
        this.testigo1 = testigo1;
        this.testigo2 = testigo2;
        this.autoridad = autoridad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = validarEspacios(lugar, 100);
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Persona getContrayente1() {
        return contrayente1;
    }

    public void setContrayente1(Persona contrayente1) {
        this.contrayente1 = contrayente1;
    }

    public Persona getContrayente2() {
        return contrayente2;
    }

    public void setContrayente2(Persona contrayente2) {
        this.contrayente2 = contrayente2;
    }

    public Persona getTestigo1() {
        return testigo1;
    }

    public void setTestigo1(Persona testigo1) {
        this.testigo1 = testigo1;
    }

    public Persona getTestigo2() {
        return testigo2;
    }

    public void setTestigo2(Persona testigo2) {
        this.testigo2 = testigo2;
    }

    public Persona getAutoridad() {
        return autoridad;
    }

    public void setAutoridad(Persona autoridad) {
        this.autoridad = autoridad;
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
        int hash = 3;
        hash = 47 * hash + this.codigo;
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
        final Matrimonio other = (Matrimonio) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Matrimonio{" + "codigo=" + codigo + ", lugar=" + lugar + ", fecha=" + fecha + ", contrayente1=" + contrayente1 + ", contrayente2=" + contrayente2 + ", testigo1=" + testigo1 + ", testigo2=" + testigo2 + ", autoridad=" + autoridad + '}';
    }
}
