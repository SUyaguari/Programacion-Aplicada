/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Estudiantes
 */
@Entity
public class VehiculoBD implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String placa;
    
    @Column
    private String modelo;
    
    @ManyToOne
    @JoinColumn(name = "fk_persona")
    private PersonaBD persona;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private List<ParqueaderoBD> parqueadero;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public PersonaBD getPersona() {
        return persona;
    }

    public void setPersona(PersonaBD persona) {
        this.persona = persona;
    }

    public List<ParqueaderoBD> getParqueadero() {
        return parqueadero;
    }

    public void setParqueadero(List<ParqueaderoBD> parqueadero) {
        this.parqueadero = parqueadero;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VehiculoBD)) {
            return false;
        }
        VehiculoBD other = (VehiculoBD) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ups.modelo.VehiculoBD[ id=" + id + " ]";
    }
    
}
