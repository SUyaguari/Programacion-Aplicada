/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import java.awt.Color;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Sebastian Uyaguari
 */
public class Filosofos extends Thread{
    
    private Thread thread;
    private JButton filosofo;
    private int id;
    private int res;
    private JButton derecho;
    private JButton izquierdo;
    private JTextField resultado;
    private JTextArea txtArea;
    
    private Random randomico = new Random();

    public Filosofos(int id, JButton filosofo, JButton derecho, JButton izquierdo, JTextField resultado, JTextArea txtArea) {
        this.id = id;
        this.filosofo = filosofo;
        this.derecho = derecho;
        this.izquierdo = izquierdo;
        this.resultado = resultado;
        this.txtArea = txtArea;
        
        thread = new Thread(this);
        
        thread.start();
    }
    
    @Override
    public void run(){
        for (int i = 0; i < 100; i++) {
            synchronized(this.izquierdo){
                synchronized(this.derecho){
                    comer();
                }
            }
            pensar();
        }
    }
    
    
    public void comer(){
        String frase;
        
        derecho.setText("0cupado");
        derecho.setBackground(Color.RED);
        
        izquierdo.setText("0cupado");
        izquierdo.setBackground(Color.RED);
        
        filosofo.setText("Filosofo"+(id+1)+"");
        filosofo.setBackground(Color.YELLOW);
        
        res = Integer.parseInt(resultado.getText());
        res++;
        resultado.setText(res+"");
        
        frase = "El filosofo " + (id + 1) + " esta comiendo \n";
        
        txtArea.append(frase);
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            
        }
        
        filosofo.setText("Filosofo"+(id+1)+"");
        filosofo.setBackground(Color.CYAN);
        frase = "El filosofo " + (id + 1) + " libero sus tenedores\n";
        txtArea.append(frase);
    }
    
    public void pensar(){
        String frase;
        
        derecho.setText("Libre");
        derecho.setBackground(Color.WHITE);
        
        izquierdo.setText("Libre");
        izquierdo.setBackground(Color.WHITE);
        
        filosofo.setText("Filosofo"+(id+1)+"");
        filosofo.setBackground(Color.CYAN);
        
        frase = "El filosofo " + (id + 1) + " esta pensando\n";
        txtArea.append(frase);
        
        try {
            
            Thread.sleep(randomico.nextInt(4000)+1000);
            
        } catch (InterruptedException ex) {
            
        }
        
    }
    
}
