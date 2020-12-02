package ec.edu.ups.controlador;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian Uyaguari
 */
public abstract class AbstractControlador<T>{
    
    public abstract boolean create(T objeto);
    
    public abstract T read(T objeto);
    
    public abstract boolean update(T objeto);
    
    public abstract boolean delite(T objeto);
    
    public abstract List<T> findAll();
    
    public int generarCodigo(List<T> lista){
        int minimo=-100;
        if(!lista.isEmpty()){
            for (T t : lista) {
                
                Method[] metodos = t.getClass().getMethods();
                for (Method metodo : metodos) {
                    
                    if(metodo.getName().equals("getCodigo")){
                        try {
                            String i = metodo.invoke(t, null)+"";
                            if(Integer.parseInt(i) > minimo){
                                minimo = Integer.parseInt(i);
                            }
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(AbstractControlador.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalArgumentException ex) {
                            Logger.getLogger(AbstractControlador.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InvocationTargetException ex) {
                            Logger.getLogger(AbstractControlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            return minimo+1;
        }
        return 1;
    }
}
