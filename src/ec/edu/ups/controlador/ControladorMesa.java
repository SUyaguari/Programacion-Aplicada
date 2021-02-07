package ec.edu.ups.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Estudiantes
 */
public class ControladorMesa extends Thread{

    HashMap<Integer, ControladorJuegoNumeroConcreto> lista;
    HashMap<Integer, ControladorParImpar> listaPar;
    HashMap<Integer, ControladorMartingala> listaMartingala;
    JTextField numeroLabel;
    Thread thread;
    private Random random;
    
    private boolean cent;
    private int a;
    
    public ControladorMesa(HashMap lista, JTextField numero, int a) {
        
        this.numeroLabel= numero;
        
        this.a = a;
        
        if(a==1){
            
            this.lista = lista;
        }
        if(a==2){
            this.listaPar= lista;
        }
        if(a==3){
            this.listaMartingala= lista;
        }
        random  = new Random();
        
        thread = new Thread(this);
        
        thread.start();
        
    }

    @Override
    public void run() {
        cent = true;
        System.out.println("a ="+a);
        while(cent){
            
            if(a==1){
                try {
                    int numero = random.nextInt(37);
                    numeroLabel.setText(numero+"");
                    for (Map.Entry<Integer, ControladorJuegoNumeroConcreto> entry : lista.entrySet()) {
                        ControladorJuegoNumeroConcreto value = entry.getValue();
                            
                        
                        value.setNumeroApuesta(numero);

                        Thread t = new Thread(value);

                        t.start();
                    }
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {

                }  
            }
            
            if(a==2){
                try {
                    int numero = random.nextInt(37);
                    numeroLabel.setText(numero+"");
                    for (Map.Entry<Integer, ControladorParImpar> entry : listaPar.entrySet()) {
                        ControladorParImpar value = entry.getValue();
                            
                        value.setNumeroApuesta(numero);

                        Thread t = new Thread(value);

                        t.start();
                    }
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {

                }
            }
            
            if(a==3){
                try {
                    int numero = random.nextInt(37);
                    numeroLabel.setText(numero+"");
                    for (Map.Entry<Integer, ControladorMartingala> entry : listaMartingala.entrySet()) {
                        ControladorMartingala value = entry.getValue();
                            
                        value.setNumeroApuesta(numero);

                        Thread t = new Thread(value);

                        t.start();
                    }
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {

                }
            }
        }
    
    }

    public boolean isCent() {
        return cent;
    }

    public void setCent(boolean cent) {
        this.cent = cent;
    }
}
