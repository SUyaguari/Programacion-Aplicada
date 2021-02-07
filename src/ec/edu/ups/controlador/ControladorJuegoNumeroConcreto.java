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
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Estudiantes
 */
public class ControladorJuegoNumeroConcreto implements Runnable{

    Persona persona;
    Random random;
    int numeroApuesta;
    
    ControladorBanco controladorBanco;
    ControladorPersona controladorPersona;
    
    JTextField etiquetaNumero;
    JTextArea txtArea;
    
    public ControladorJuegoNumeroConcreto(Persona persona, JTextField n, JTextArea txtArea) {
       
       controladorPersona = ControladorPersona.getInstance();
       controladorBanco = ControladorBanco.getInstance();
       this.persona = persona;
       
       random = new Random();
       
       etiquetaNumero = n;
       this.txtArea = txtArea;
    
    }

    @Override
    public void run() {
        if(dineroCuenta()){
            int numeroApusta = apostar();
            if(getNumeroApuesta()== numeroApusta){
                cobrarDinero();
            }else{
                txtArea.append("El jugador "+persona.getNombre()+" no gano la apuesta \n");
            }
        }else{
            txtArea.append("El jugador "+persona.getNombre()+" ya no puede seguir apostando \n");
        }
    }

    private boolean dineroCuenta() {
        if(persona.getCuenta() > 10){
            return true;
        }else{
            return false;
        }
    }
    
    public int apostar(){
        int numeroA = (random.nextInt(36))+1;
        controladorBanco.incrementarApuesta(10);
        retirarDinero(numeroA);
        txtArea.append("El jugador "+persona.getNombre()+" apuesta al numero "+numeroA +"\n");
        etiquetaNumero.setText(numeroA+"");
        return numeroA;
    }

    public void retirarDinero(int numeroApu){
        List<Registro> registros =  persona.getRegistro();
        registros.add( new Registro( numeroApu, 10, "Retirar", "Apuesta Concreto", persona));
        persona.setRegistro(registros);
        double dinero = persona.getCuenta()-10;
        persona.setCuenta(dinero);
        try{
            System.out.println(persona);
            controladorPersona.update(persona);
            
        }catch(NullPointerException e){
            
        }
    }
    
    public int getNumeroApuesta() {
        return numeroApuesta;
    }

    public void setNumeroApuesta(int numeroApuesta) {
        this.numeroApuesta = numeroApuesta;
    }
    
    public void cobrarDinero(){
        int valor = controladorBanco.getGananciaConcreto();
        List<Registro> registros =  persona.getRegistro();
        registros.add(new Registro(numeroApuesta, valor, "ingreso", "Gano Concreto", persona));
        persona.setRegistro(registros);
        double dinero = persona.getCuenta()+valor;
        persona.setCuenta(dinero);
        controladorPersona.update(persona);
        txtArea.append("El jugador "+persona.getNombre()+" gana la apuesta \n");

    }
    
}
