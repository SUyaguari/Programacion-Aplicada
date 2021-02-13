package ec.edu.ups.modelo;

import java.util.Date;

/**
 *
 * @author Estudiantes
 */
public class Asunto {
    
    private long codigo;
    private Date inicio;
    private Date fin;
    private String estado;
    
    private Cliente cliente_fk;

    public Asunto() {
    }

    public Asunto(long codigo, Date inicio, Date fin, String estado, Cliente cliente) {
        this.codigo = codigo;
        this.inicio = inicio;
        this.fin = fin;
        this.estado = estado;
        this.cliente_fk = cliente;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente_fk;
    }

    public void setCliente(Cliente cliente) {
        this.cliente_fk = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (int) (this.codigo ^ (this.codigo >>> 32));
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
        final Asunto other = (Asunto) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Asunto{" + "codigo=" + codigo + '}';
    }
    
    
}
