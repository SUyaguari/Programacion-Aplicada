package ec.edu.ups.modelo;

import javax.swing.JLabel;

/**
 *
 * @author Sebastian Uyaguri
 */
public class Cajero {
    
    private int id;
    private JLabel cajero;

    public Cajero(int id, JLabel cajero) {
        this.id = id;
        this.cajero = cajero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JLabel getCajero() {
        return cajero;
    }

    public void setCajero(JLabel cajero) {
        this.cajero = cajero;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
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
        final Cajero other = (Cajero) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cajero{" + "id=" + id + ", cajero=" + cajero + '}';
    }
    
    
    
}
