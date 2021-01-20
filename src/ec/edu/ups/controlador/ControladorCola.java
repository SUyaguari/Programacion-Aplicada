package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Cliente;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Sebastian Uyaguari
 */
public class ControladorCola {
    
    private static ControladorCola instance = new ControladorCola();
    
    private int MAXIMO = 10;
    private List<Cliente> lista;  
    LinkedList<Cliente> cola;
    
    public static ControladorCola getInstance(){
        
        return instance;
    
    }
    
    public ControladorCola() {
        
        lista = new ArrayList<>();
        cola = new LinkedList<>();
    
    }
    
    public  boolean encolar(Cliente cliente){
        if(colaLlena()){
                Cliente c = clienteRemplazado(cliente);
                cola.addLast(c);
                return true;
        }
        return false;
    }
    
    public Cliente desencolar(){
        if(!colaVacia()){
            return cola.removeFirst();
        }
        return null;
    }
    
    public boolean colaLlena(){
        
        if(cola.size() <= MAXIMO-1){
            return true;
        }
        return false;
    }
    
    public boolean colaVacia(){
        
        if(cola.size()==0){
            return true;
        }
        return false;
    }
    
    public boolean comprobarCola(Cliente cliente){
        if(cola.contains(cliente)){
            
            return true;
        }
        return false;
    }

    public List<Cliente> getLista() {
        return lista;
    }

    public void setLista(List<Cliente> lista) {
        this.lista = lista;
    }
    
    public Cliente clienteRemplazado(Cliente cliente){
        
        for (Cliente cliente1 : lista) {
            if(cliente1.getId()==cliente.getId()){
                return cliente1;
            }
        }
        
        return null;
    }
 
    public void actualizarLista(Cliente cliente){
        
        for (Cliente cliente1 : lista) {
            if(cliente1.getId()==cliente.getId()){
                int pos = lista.indexOf(cliente1);
                lista.set(pos, cliente);
            }
        }
    }
}
