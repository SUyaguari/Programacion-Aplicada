package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Cliente;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Sebastian Uyaguari
 */
public class ControladorCliente implements Runnable{
    
    ControladorCola cola = ControladorCola.getInstance();
    Cliente cliente;
    
    Random randomico = new Random();
    
    Thread thread;
    
    JPanel contenedor;
    JLabel persona;
    JTextArea txtArea;
    
    int cont;
    
    public ControladorCliente(Cliente cliente, JPanel contenedor, JLabel persona, JTextArea txtArea, int c) {
       
        this.cliente = cliente;
        this.contenedor = contenedor;
        this.persona = persona;
        this.txtArea = txtArea;
        this.cont = c;
        
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while(true){
            
            synchronized(cola){
                
                if(cliente.getContador()!=1){
                    System.out.println("Cuenta llena");
                }else{
                    if(!cola.comprobarCola(cliente)){
                            esperar();
                        try {
                            llegarBanco();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }
    
    
    
    public void esperar(){
        
            try {
                int holi =  randomico.nextInt(((5)+1));
                holi= (holi)*1000;
                
                System.out.println(cliente +" || "+  holi);
                Thread.sleep(holi);
                //Thread.sleep((randomico.nextInt(10)+1)*1000);
            } catch (InterruptedException ex) {

            }
       
    }
    
    
    public synchronized void llegarBanco() throws InterruptedException{
        
        persona.setLocation(712, 56);
        persona.setIcon(new ImageIcon(getClass().getResource("/ec/edu/ups/imagenes/persona.png")));
        persona.setVisible(true);
        
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(getClass().getResource("/ec/edu/ups/imagenes/persona.png")));
        
        cliente.setLabel(label);
        
                for (int i = 56; i < 152; i+=10) {
                    persona.setLocation(712, i);
                    Thread.sleep(30);
                }
                for (int i = 712; i >372 ; i-=10) {
                    persona.setLocation(i, 152);
                    Thread.sleep(30);
                }
        if(!cola.comprobarCola(cliente)){
            boolean a = cola.encolar(cliente);
            if(a){
                contenedor.add(label);
                contenedor.updateUI();
                txtArea.append("El cliente "+cliente.getId()+" entra a cola \n");
                persona.setVisible(false);
                
            
            }else{
               
                for (int i = 371; i >38 ; i-=10) {
                    persona.setLocation(i, 152);
                    Thread.sleep(30);
                }
                
                for (int i = 152; i >56 ; i-=10) {
                    persona.setLocation(38, i);
                    Thread.sleep(30);
                }
                persona.setVisible(false);
                txtArea.append("La cola esta llena el cliente "+cliente.getId()+" se va \n");
                
   
            }
        
        }else{
            txtArea.append("El cliente "+cliente.getId()+" ya esta en cola");
        } 
        
    }
}
