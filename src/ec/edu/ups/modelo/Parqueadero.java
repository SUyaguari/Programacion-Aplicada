package ec.edu.ups.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Parqueadero implements Serializable {

    private int codigo;
    private int puesto;
    private String tipo;
    private Vehiculo vehiculo;
    private Date ingreso;
    private Date salida;
    private double total;

    public Parqueadero(int codigo, int puesto, String tipo, Vehiculo vehiculo, Date ingreso, Date salida, double total) {
        this.codigo = codigo;
        this.puesto = puesto;
        this.tipo = tipo;
        this.vehiculo = vehiculo;
        this.ingreso = ingreso;
        this.salida = salida;
        this.total = total;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getPuesto() {
        return puesto;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Date getIngreso() {
        return ingreso;
    }

    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
    }

    public Date getSalida() {
        return salida;
    }

    public void setSalida(Date salida) {
        this.salida = salida;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parqueadero that = (Parqueadero) o;
        return Objects.equals(vehiculo, that.vehiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehiculo);
    }

    @Override
    public String toString() {
        return "Parqueadero{" +
                "codigo=" + codigo +
                ", puesto=" + puesto +
                ", tipo='" + tipo + '\'' +
                ", vehiculo=" + vehiculo +
                ", ingreso=" + ingreso +
                ", salida=" + salida +
                ", total=" + total +
                '}';
    }
}
