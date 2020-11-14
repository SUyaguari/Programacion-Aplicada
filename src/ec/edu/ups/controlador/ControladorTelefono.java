/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Persona;
import ec.edu.ups.modelo.Telefono;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estudiantes
 */
public class ControladorTelefono extends AbstractControlador<Telefono>{

    @Override
    public boolean validar(Telefono objeto) {
        return true;
    }

    @Override
    public void ordenarLista() {
        List<Telefono> lista = getLista();
        for (int i = 0; i < lista.size()-1; i++) {
            for (int j = i+1; j < lista.size(); j++) {
                if(lista.get(i).getNumero().compareTo(lista.get(j).getNumero())>0){
                    Telefono aux = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, aux);
                }
            }
        }
        setLista(lista);
    }

    @Override
    public int generarId() {
        List<Telefono> lista = getLista();
        int codigo = 0;
        if (lista.size()>0){
            for (Telefono telefono : lista) {
                int aux = telefono.getCodigo();
                if(aux>codigo){
                    codigo=aux;
                }
            }
            return codigo+1;
        }else{
            return 1;
            
        }
    }
    
    public List buscarTelefonoPersona(int id){
        List<Telefono> lista = new ArrayList<>();
        List<Telefono> listaTelefonos = getLista();
        for (Telefono listaTelefono : listaTelefonos) {
            Persona persona = listaTelefono.getPersona();
            if(persona.getId()==id){
                lista.add(listaTelefono);
            }
        }
        return lista;
    }
    
}
