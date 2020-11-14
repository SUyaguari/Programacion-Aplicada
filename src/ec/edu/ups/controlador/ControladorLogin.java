/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estudiantes
 */
public class ControladorLogin {
    
    private static ControladorLogin instance = new ControladorLogin();
    
    private List lista = new ArrayList();

    private ControladorLogin() {
        
    }
    
    public static ControladorLogin getInstance(){
        return instance;
    }
    
    public boolean agregarUsuario(Usuario object){
        if(object!=null)
            return lista.add(object);
        return false;
    }
    
    public Usuario validarLogin(Usuario object){
        if(lista.size()>0){
            for (Object object1 : lista) {
                if(object1.equals(object)){
                    return object;
                }
            }
        }
        return null;
    }
    
}
