/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Persona;
import ec.edu.ups.modelo.Registro;
import java.util.List;
import java.util.Random;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Estudiantes
 */
public class ControladorMartingala implements Runnable{

    ControladorBanco controladorBanco;
    ControladorPersona controladorPersona;
    
    int numeroApuesta;
    int montoApostar;
    
    Persona persona;
    JTextField numero;
    JTextArea txtArea;
    
    Random random;
    
    public ControladorMartingala(Persona persona, JTextField numero, JTextArea txtArea) {
        
        controladorBanco = ControladorBanco.getInstance();
        controladorPersona = ControladorPersona.getInstance();
        
        random = new Random();
        
        this.persona = persona;
        this.numero = numero;
        this.txtArea = txtArea;
        
        montoApostar= 10;
        
    }

    
    @Override
    public void run() {
        if(dineroCuenta()){
            int apuesta = numeroApostar();
            if(apuesta==getNumeroApuesta()){
                cobrarDinero();
                montoApostar=10;
            }else{
                montoApostar = montoApostar*2;
                txtArea.append("El jugador "+persona.getNombre()+" no gano la apuesta \n");
            }
        }else{
            txtArea.append("El jugador "+persona.getNombre()+" ya no puede seguir apostando \n");
        }
    }

    private boolean dineroCuenta() {
        if(persona.getCuenta() > montoApostar){
            return true;
        }else{
            return false;
        }
    }

    private int numeroApostar() {
        int numeroA = (random.nextInt(36))+1;
        controladorBanco.incrementarApuesta(montoApostar);
        retirarDinero(numeroA);
        txtArea.append("El jugador "+persona.getNombre()+" apuesta al numero "+numeroA +
                "con el valor de "+montoApostar+"\n");
        numero.setText(numeroA+"");
        return numeroA;
    }
    
    public void retirarDinero(int numeroApu){
        List<Registro> registros =  persona.getRegistro();
        registros.add( new Registro( numeroApu, montoApostar, "Retirar", "Apuesta Martingala", persona));
        persona.setRegistro(registros);
        double dinero = persona.getCuenta()-montoApostar;
        persona.setCuenta(dinero);
        try{
            System.out.println(persona);
            controladorPersona.update(persona);
            
        }catch(NullPointerException e){
            
        }
    }

    public void cobrarDinero(){
        int valor = controladorBanco.getGananciaMartingala(montoApostar);
        List<Registro> registros =  persona.getRegistro();
        registros.add(new Registro(numeroApuesta, valor, "ingreso", "Gano Martingala", persona));
        persona.setRegistro(registros);
        double dinero = persona.getCuenta()+valor;
        persona.setCuenta(dinero);
        controladorPersona.update(persona);
        txtArea.append("El jugador "+persona.getNombre()+" gana la apuesta \n");

    }
    
    public int getNumeroApuesta() {
        return numeroApuesta;
    }

    public void setNumeroApuesta(int numeroApuesta) {
        this.numeroApuesta = numeroApuesta;
    }
    
    
    
}
