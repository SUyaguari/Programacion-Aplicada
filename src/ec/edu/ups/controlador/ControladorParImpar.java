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
public class ControladorParImpar implements Runnable{
    
    ControladorBanco controladorBanco;
    ControladorPersona controladorPersona;
    
    int numeroApuesta;
    
    Persona persona;
    JTextField numero;
    JTextArea txtArea;
    
    private Random random;

    public ControladorParImpar(Persona persona, JTextField numero, JTextArea txtArea) {
    
        controladorBanco = ControladorBanco.getInstance();
        controladorPersona = ControladorPersona.getInstance();
        
        random = new Random();
        
        this.persona = persona;
        this.numero = numero;
        this.txtArea = txtArea;
        
        
    }

    @Override
    public void run() {
        if(dineroCuenta()){
            if((numeroApuesta%2==0 && apostar()==0) || (numeroApuesta%2==1 && apostar()==1)){
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
        int apuesta = random.nextInt(2);
        controladorBanco.incrementarApuesta(10);
        retirarDinero(10);
        if(apuesta==0){
            txtArea.append("El jugador "+persona.getNombre()+" apuesta al numero Par \n");
            numero.setText("Par");
        }else{
            txtArea.append("El jugador "+persona.getNombre()+" apuesta al numero Impar \n");
            numero.setText("Impar");
        }
        return apuesta;
    }
    
    public void retirarDinero(int numeroApu){
        List<Registro> registros =  persona.getRegistro();
        registros.add( new Registro( numeroApu, 10, "Retirar", "Apuesta Par Impar", persona));
        persona.setRegistro(registros);
        double dinero = persona.getCuenta()-10;
        persona.setCuenta(dinero);
        try{
            System.out.println(persona);
            controladorPersona.update(persona);
            
        }catch(NullPointerException e){
            
        }
    }
    
    public void cobrarDinero(){
        int valor = controladorBanco.getGananciaPar();
        List<Registro> registros =  persona.getRegistro();
        registros.add(new Registro(numeroApuesta, valor, "ingreso", "Gano Par Impar", persona));
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
