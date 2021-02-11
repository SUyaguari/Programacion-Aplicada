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
import javax.persistence.OneToMany;

/**
 *
 * @author Estudiantes
 */
@Entity
public class PersonaBD implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String cedula;
    
    @Column
    private String rol;
    
    @Column
    private String nombre;
    
    @Column
    private String apellido;
    
    @Column
    private String correo;
    
    @Column
    private String contraseña;
    
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<VehiculoBD> vehiculos;
    
    @OneToMany(mappedBy = "per", cascade = CascadeType.ALL)
    private List<ContratoBD> contratos;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public List<VehiculoBD> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<VehiculoBD> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public List<ContratoBD> getContratos() {
        return contratos;
    }

    public void setContratos(List<ContratoBD> contratos) {
        this.contratos = contratos;
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
        if (!(object instanceof PersonaBD)) {
            return false;
        }
        PersonaBD other = (PersonaBD) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ups.modelo.PersonaBD[ id=" + id + " ]";
    }
    
}
