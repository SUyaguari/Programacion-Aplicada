package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Cajero;
import ec.edu.ups.modelo.Cliente;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Sebastian Uyaguaari
 */
public class ControladorCajero implements Runnable {

    private Thread thread;
    private Cajero cajero;

    private JTextArea txtArea;
    private JLabel persona;
    private JPanel contenedor;
    

    private boolean centinela;

    private Random randomico = new Random();

    private ControladorCola cola = ControladorCola.getInstance();
    private ControladroRandomProcesos controladroRandomProcesos = new ControladroRandomProcesos();

    boolean cent=true;
    
    public ControladorCajero(Cajero cajero, JTextArea txtArea, JLabel persona, JPanel contenedor) {

        centinela = true;

        this.cajero = cajero;

        this.txtArea = txtArea;
        this.persona = persona;
        this.contenedor = contenedor;
        
        thread = new Thread(this);

        thread.start();

    }

    @Override
    public void run() {

        while (centinela) {

            while(cent){
                System.out.println(cola.colaLlena());
                if(cola.colaLlena()==false){
                    cent=false;
                    break;
                    
                }
            }
            cobrar();

        }
    }

    
    public void cobrar() {

        try {
            if (!cola.colaVacia()) {
                
                Cliente cliente = cola.desencolar();

                txtArea.append("El cliente " + cliente.getId() + " Ingreso a Caja \n");
                contenedor.remove(cliente.getLabel());
                contenedor.updateUI();
                persona.setLocation(106, 336);
                persona.setIcon(new ImageIcon(getClass().getResource("/ec/edu/ups/imagenes/persona.png")));
                persona.setVisible(true);

                int x = 0;
                switch(cajero.getId()){
                    case 0:
                        x=324;
                        for (int i = 336; i < 417; i+=10) {
                            persona.setLocation(106, i);
                            Thread.sleep(30);
                        }
                        for (int i = 106; i < 325; i+=10) {
                            persona.setLocation(i, 416);
                            Thread.sleep(30);
                        }
                        break;
                    case 1:
                        x=392;
                        for (int i = 106; i < 393; i+=10) {
                            persona.setLocation(i, 336);
                            Thread.sleep(30);
                        }
                        for (int i = 336; i < 417; i+=10) {
                            persona.setLocation(392, i);
                            Thread.sleep(30);
                        }
                        break;
                    case 2:
                        x=460;
                        for (int i = 106; i < 461; i+=10) {
                            persona.setLocation(i, 336);
                            Thread.sleep(30);
                        }
                        for (int i = 336; i < 417; i+=10) {
                            persona.setLocation(460, i);
                            Thread.sleep(30);
                        }
                        break;
                }

                cajero.getCajero().setText("  Ocupado");

                int valor = cliente.getCuenta();
                int aux = valor;

                boolean cent = false;
                while (cent == false) {
                    if(cajero.getId()==0){
                        persona.setLocation(324, 416);
                    }
                    if(cajero.getId()==1){
                        persona.setLocation(392, 416);
                    }
                    if(cajero.getId()==2){
                        persona.setLocation(460, 416);
                    }
                    String accion = controladroRandomProcesos.generarAccion();

                    int v = controladroRandomProcesos.generarValor();
                    if (accion.equals("Ingresar")) {
                        aux = aux + v;
                    } else {
                        aux = aux - v;
                    }

                    if (aux >= 0) {
                        cent = true;
                        try {
                            txtArea.append("El cliente " + cliente.getId() + " " + accion + " el valor de: " + v+"\n");

                        } catch (NullPointerException e) {
                        }
                        int h = cliente.getContador();
                        cliente.setContador(h);
                    } else {
                        aux = valor;
                    }
                }

                cliente.setCuenta(aux);

                cola.actualizarLista(cliente);
                
                txtArea.append("La cuenta del "+cliente.getId()+" tiene el valor total de: "+cliente.getCuenta()+"\n");

                int hola = randomico.nextInt(35);
               
                try {
                    Thread.sleep((hola+16)*1000);
                    

                } catch (InterruptedException ex) {

                }
                cajero.getCajero().setText("   Libre");
                txtArea.append("El cliente " + cliente.getId() + " Salio a Caja \n");

                
                
                for (int i = x; i > 10; i-=10) {
                    persona.setLocation(i, 416);
                    Thread.sleep(30);
                }
                for (int i = 416; i >56 ; i-=10) {
                    persona.setLocation(10, i);
                    Thread.sleep(30);
                }
                persona.setVisible(false);
            } else {
                try {
                    int d = randomico.nextInt(3);
                    Thread.sleep((d + 2) * 1000);
                } catch (InterruptedException ex) {

                }
            }
        } catch (Exception e) {

        }

    }

}
