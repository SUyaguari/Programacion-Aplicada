package ec.edu.ups.controlador;

import java.util.Random;

/**
 *
 * @author Sebastian Uyaguari
 */
public class ControladroRandomProcesos {
    
    private String[] accion ={"Ingresar", "Retirar"};
    private int[] valor = {100, 50, 20};
    private Random randomico = new Random();
    
    public String generarAccion(){
        
        int i = randomico.nextInt(accion.length);
        return accion[i];
    }
    
    public int generarValor(){
        
        int i = randomico.nextInt(valor.length);
        return valor[i];
    }
    
    
}
