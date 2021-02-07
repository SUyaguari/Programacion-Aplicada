/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Persona;
import ec.edu.ups.modelo.Registro;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Estudiantes
 */
public class ControladorBanco {
    
    private Persona persona;
    private double dineroBanco;
    ControladorPersona controladorPersona = ControladorPersona.getInstance();
    
    private static ControladorBanco instance = new ControladorBanco();
    
    public ControladorBanco() {
        
        Persona persona = controladorPersona.buscarCedula("0000000000");
        dineroBanco = persona.getCuenta();
        this.persona = persona;
        
    }

    public static ControladorBanco getInstance() {
        return instance;
    }
    
    public synchronized int getGananciaConcreto(){
        
        if(dineroBanco>360){
            retirarApuestos(360);
            return 360;
        }else{
            return 0;
        }
    }
    
    public synchronized int getGananciaPar(){
        
        if(dineroBanco > 20){
            retirarApuestos(20);
            return 20;
            
        }else{
            return 0;
        }
    }
    
    public synchronized int getGananciaMartingala(int apuesta){
        if(dineroBanco > (apuesta*36)){
            retirarApuestos((int) (apuesta*36));
            return apuesta*36;
        }else{
            return 0;
        }
    }

    public double getDineroBanco() {
        return dineroBanco;
    }

    public void setDineroBanco(double dineroBanco) {
        this.dineroBanco = dineroBanco;
    }
    
    public synchronized void incrementarApuesta(int valor){
        List<Registro> registros =  persona.getRegistro();
        registros.add(new Registro(0, valor, "Ingreso", "Apuesta", persona));
        persona.setRegistro(registros);
        dineroBanco +=valor;
        persona.setCuenta(dineroBanco);
        controladorPersona.update(persona);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            
        }
    }
    
    public synchronized void retirarApuestos(int valor){
        List<Registro> registros =  persona.getRegistro();
        registros.add(new Registro(0, valor, "Retirada", "Perdida", persona));
        persona.setRegistro(registros);
        dineroBanco -=valor;
        persona.setCuenta(dineroBanco);
        controladorPersona.update(persona);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {

        }
    } 
}
