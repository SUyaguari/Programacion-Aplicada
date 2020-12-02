package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Persona;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian Uyaguari
 */
public class ControladorPersona extends AbstractControlador<Persona>{

    private static ControladorPersona instance = new ControladorPersona();
    private static int REGISTRO = 133;
    private RandomAccessFile archivo;
    
    /**
     * Estructura del archivo
     * 
     * codigo: 4 bytes
     * rol: 7 bytes + 2 bytes
     * cedula: 10 bytes + 2 bytes
     * nombre: 25 bytes + 2 bytes
     * apellido: 25 bytes + 2 bytes
     * correo: 30 bytes + 2 bytes
     * contraseña: 20 bytes +2
     * 
     * total: 133 bytes
     */
    public ControladorPersona() {
        try {
            archivo = new RandomAccessFile("datos/persona.txt", "rw");
        } catch (FileNotFoundException ex) {
            System.out.println("Error COntrolador persona: create");
        }
    }

    public static ControladorPersona getInstance() {
        return instance;
    }
    
    @Override
    public boolean create(Persona objeto) {
        try {
            
            archivo.seek(archivo.length());
            archivo.writeInt(objeto.getCodigo());
            archivo.writeUTF(objeto.getRol());
            archivo.writeUTF(objeto.getCedula());
            archivo.writeUTF(objeto.getNombre());
            archivo.writeUTF(objeto.getApellido());
            archivo.writeUTF(objeto.getCorreo());
            archivo.writeUTF(objeto.getContraseña());
            return true;
            
        } catch (IOException ex) {
            System.out.println("Error COntrolador persona: create");
        }
        return false;
    }

    @Override
    public Persona read(Persona objeto) {
        int salto = 0;
        try {
            while (salto < archivo.length()) {                
                archivo.seek(salto+13);
                String cedula = archivo.readUTF();
                if(cedula.equals(objeto.getCedula())){
                    archivo.seek(salto);
                    return new Persona(archivo.readInt(), archivo.readUTF(), 
                            archivo.readUTF(), archivo.readUTF(), archivo.readUTF(),
                            archivo.readUTF(), archivo.readUTF());
                   
                }
                salto+=REGISTRO;
            }  
        } catch (IOException ex) {
            System.out.println("Erro rcontrolador persona: read");
        }
        return null;
    }

    @Override
    public boolean update(Persona objeto) {
        int salto = 0;
        try {
            
            while (salto < archivo.length()) {                
                archivo.seek(salto);
                int codigo = archivo.readInt();
                if(codigo==objeto.getCodigo()){
                    archivo.seek(salto);
                    archivo.writeInt(objeto.getCodigo());
                    archivo.writeUTF(objeto.getRol());
                    archivo.writeUTF(objeto.getCedula());
                    archivo.writeUTF(objeto.getNombre());
                    archivo.writeUTF(objeto.getApellido());
                    archivo.writeUTF(objeto.getCorreo());
                    archivo.writeUTF(objeto.getContraseña());
                    return true;
                }
                salto+=REGISTRO; 
           }
            
        } catch (IOException ex) {
            System.out.println("Error controlador Persona: update");
        }
        return false;
    }

    @Override
    public boolean delite(Persona objeto) {
        int salto = 0;
        try {
            while(salto<archivo.length()){
                archivo.seek(salto);
                int codigo = archivo.readInt();
                if(codigo==objeto.getCodigo()){
                    archivo.seek(salto);
                    archivo.writeInt(-100);
                    archivo.writeUTF(String.format("%-" + 7 + "s", ""));
                    archivo.writeUTF(String.format("%-" + 10 + "s", ""));
                    archivo.writeUTF(String.format("%-" + 25 + "s", ""));
                    archivo.writeUTF(String.format("%-" + 25 + "s", ""));
                    archivo.writeUTF(String.format("%-" + 30 + "s", ""));
                    archivo.writeUTF(String.format("%-" + 20 + "s", ""));
                    return true;
                }
                salto+=REGISTRO;
            }
        } catch (IOException ex) {
            System.out.println("Error controlador Persona: delite");
        }
        return false;
    }

    @Override
    public List<Persona> findAll() {
        List<Persona> lista = new ArrayList<>();
        int salto = 0;
        
        try {
            while (salto< archivo.length()) {
                archivo.seek(salto);
                int codigo = archivo.readInt();
                if(codigo>0){
                    lista.add(new Persona(codigo, archivo.readUTF(), 
                            archivo.readUTF(), archivo.readUTF(), archivo.readUTF(),
                            archivo.readUTF(), archivo.readUTF()));
                    
                }
                salto+=REGISTRO;
            }
        } catch (IOException ex) {
            System.out.println("Error controlador Persona findAll");
        }
        return lista;
    }
    
    public Persona iniciarSesion(Persona persona){
        List<Persona> lista = findAll();
        
        for (Persona persona1 : lista) {
            if(persona1.equals(persona)){
                return persona1;
            }
        }
        return null;
    }
}
