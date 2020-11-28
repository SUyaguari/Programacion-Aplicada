package ec.edu.ups.controlador;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sebastian Uyaguari
 */
public abstract class AbstractControlador<T> {
    
    private List<T> lista;

    public AbstractControlador() {
        lista = new ArrayList<T>();
    }
    
    public boolean create(T objeto){
        var v = validar(objeto);
        if(v==true){
            var v2 = createArchivo(objeto);
            if(v2 == true){
                
                return lista.add(objeto);
            }
        }
        return false;
    }
    
    public abstract boolean createArchivo(T objeto);
    
    public T read(T objeto){
        try{
            return lista.stream().filter(t -> t.equals(objeto)).findFirst().get();
        }catch(NullPointerException ex){
            System.out.println("Error lista vacia");
        }
        return null;
    }
    
    public boolean update(T objeto){
        int posicion = buscarPosicion(objeto);
        if(posicion >=0){
            if(updateArchivo(objeto)){
                lista.set(posicion, objeto);
                return true;
            }
        }        
        return false;   
    }
    
    public abstract boolean updateArchivo(T objeto);
    
    public boolean delite(T objeto){
        if(lista.contains(objeto)){
            if(deliteArchivo(objeto))
                return lista.remove(objeto);
        }
        return false;
    }
    
    public abstract boolean deliteArchivo(T objeto);
    
    public int buscarPosicion(T buscar){
        for (int i = 0; i < lista.size(); i++) {
            Object objeto = lista.get(i);
            if(objeto.equals(buscar))
                return i;
        }
        return -1;
    }
    
    public abstract int generarCodigo();

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }
    
    public abstract boolean validar(T objecto);
    
    public abstract void listar();
    
}
