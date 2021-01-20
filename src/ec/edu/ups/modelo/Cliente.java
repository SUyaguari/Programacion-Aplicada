package ec.edu.ups.modelo;

import javax.swing.JLabel;

/**
 *
 * @author Sebastian Uyaguari
 */
public class Cliente {
    
    private int id;
    private int cuenta;
    private int contador;
    private JLabel label;

    public Cliente(int id, int cuenta) {
        this.id = id;
        this.cuenta = cuenta;
        this.contador = 1;
        this.label = null;
    }

    public Cliente(int id, int cuenta, int contador) {
        this.id = id;
        this.cuenta = cuenta;
        this.contador = contador;
        this.label = null;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
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
        final Cliente other = (Cliente) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", cuenta=" + cuenta + ", contador=" + contador + '}';
    }
    
    
}
