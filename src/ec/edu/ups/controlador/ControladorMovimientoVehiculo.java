/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.imagen.ControladorImagenes;
import ec.edu.ups.modelo.ContratoBD;
import ec.edu.ups.modelo.ParqueaderoBD;
import ec.edu.ups.modelo.VehiculosParqueadero;
import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Estudiantes
 */
public class ControladorMovimientoVehiculo extends Thread{
    
    JPanel panel;
    String accion;
    int puesto;
    Thread thread;
    JLabel a;
    ControladorImagenes controladorImagenes; 
    ControladorParqueadero controladorParqueadero;
    ControladorContratos contratos;
    int x, y;

    public ControladorMovimientoVehiculo(JPanel panel, String accion, int puesto) {
        this.panel = panel;
        this.accion = accion;
        this.puesto = puesto;
        this.thread = thread;
        
        controladorImagenes = new ControladorImagenes();
        controladorParqueadero = new ControladorParqueadero();
        contratos = ControladorContratos.getInstance();
        
        
        
        thread = new Thread(this);
        thread.start();
        }

    @Override
    public void run() {
        calcularPuesto();
        if(accion.equals("Ingresar")){
            Ingresar();
            actualizarPanel();
        }
        else{
            actualizarPanel();
            salida();
        }
    }
    
    
    public void calcularPuesto(){
        int cont = 1;
        x=64;
        y=0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if(puesto==cont){
                    return;
                }
                x+=64;
                cont++;
            }
            x=64;
            y+=128;
        }
        
    }
    
    public void Ingresar(){
        
        a = new JLabel();
        a.setIcon(controladorImagenes.asignarIngreso());
        a.setSize(64, 64);
        a.setLocation(0, 576);
        a.setVisible(true);
        panel.add(a);
        panel.updateUI();
        
        for (int i = 576; i > y+64; i-=10) {
            a.setLocation(0, i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControladorMovimientoVehiculo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (int i = 0; i < x; i+=10) {
            a.setLocation(i, y+64);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControladorMovimientoVehiculo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for (int i = y+64; i > y; i-=10) {
            a.setLocation(x, i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControladorMovimientoVehiculo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            Logger.getLogger(ControladorMovimientoVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        a.setVisible(false);
    }
    
    public void salida(){
        a = new JLabel();
        a.setIcon(controladorImagenes.asignarIngreso());
        a.setSize(64, 64);
         a.setLocation(x, y);
       a.setVisible(true);
        panel.add(a);
        panel.updateUI();
        
        for (int i = y; i < y+64; i+=10) {
            a.setLocation(x, i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControladorMovimientoVehiculo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for (int i = x; i < 704; i+=10) {
            a.setLocation(i, y+64);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControladorMovimientoVehiculo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for (int i = y+64; i < 576; i+=10) {
            a.setLocation(704, i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControladorMovimientoVehiculo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(ControladorMovimientoVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        a.setVisible(false);
    
    }
    
    public void actualizarPanel(){
        panel.removeAll();
        panel.updateUI();
         List<ParqueaderoBD> parqueadero = controladorParqueadero.findAll();
         List<ContratoBD> contratos = this.contratos.findAll();
         
        int cont = 1;
        int x = 64;
        int y = 0;
        
        JLabel lb;
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                VehiculosParqueadero v = null;
                boolean cent = false;
                
                for (ContratoBD contrato : contratos) {
                    //Date f = new Date();
                    //int fechaIni = f.compareTo(contrato.getInicio());
                    //int fechaInf = f.compareTo(contrato.getSalida());
                    //&& (fechaIni>=0 && fechaInf<=0)
                    if(contrato.getPuesto()==cont ){
                    v = FabricaDeVehiculos.crearVehiculos(contrato.getPuesto(), "Reservado");
                        cent = true;
                    }
                }
                
                for (ParqueaderoBD parque : parqueadero) {
                    if(parque.getPuesto()==cont && !parque.getTipo().equals("Retirado")){
                        v = FabricaDeVehiculos.crearVehiculos(parque.getPuesto(), parque.getTipo());
                        cent= true;
                    }
                }

                lb = new JLabel();
                
                if(cent==true){
                    //v = FabricaDeVehiculos.crearVehiculos(cont, "Vacio");
                    lb.setIcon(v.getImage());
                }
                
                lb.setBounds(x, y, 64, 64);
                lb.setVisible(true);
                lb.setBorder(new LineBorder(Color.BLACK));
                lb.setText(cont+"");
                panel.add(lb);
                x+=64;
                cont++;
            }
            x=64;
            y+=128;
        }
        panel.updateUI();
    }
}

