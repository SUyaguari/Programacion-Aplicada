/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ups.edu.ec.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;



/**
 *
 * @author Estudiantes
 */
public class ControladorAgenda <P>{
    
    private List<P> lista;

    public ControladorAgenda() {
        lista = new ArrayList<>();
    }

    public void create(P objeto){
        lista.add(objeto);
    }
    
    public Optional<P> read(Predicate<P> p){
        return lista.stream().filter(p).findFirst();
    }
    
    
    public boolean update(P objeto, P objetoNuevo){
        int posicion = lista.indexOf(objeto);
        lista.set(posicion, objetoNuevo);
        return false;
    }
    
    public boolean delite(P objeto){
        if(lista.contains(objeto)){
            lista.remove(objeto);
            return true;
        }
        return false;
    }
    
    public List<P> findAll(){
        return lista;
    }
}
