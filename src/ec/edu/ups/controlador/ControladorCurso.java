package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Curso;
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
public class ControladorCurso extends AbstractControlador<Curso>{
    
    private static ControladorCurso instance = new ControladorCurso();
    private static int REGISTRO=37;
    
    ControladorPersona controladorPersona;
    
    private RandomAccessFile archivo; 
    
    /**
     * Estructura del archivo.
     * 
     * codigo: 4 bytes
     * curso: 15 bytes + 2 bytes
     * alumnos: 4 bytes
     * Persona: 10 bytes + 2 bytes
     * 
     * total: 37 bytes 
     */
    public ControladorCurso() {
        try {
            archivo = new RandomAccessFile("datos/curso.txt", "rw");
            controladorPersona = ControladorPersona.getInstance();
        } catch (FileNotFoundException ex) {
            System.out.println("Error controlador curso");
        }
    }

    public static ControladorCurso getInstance() {
        return instance;
    }

    @Override
    public boolean create(Curso objeto) {
        try {
            archivo.seek(archivo.length());
            archivo.writeInt(objeto.getCodigo());
            archivo.writeUTF(objeto.getCurso());
            archivo.writeInt(objeto.getAlumnos());
            archivo.writeUTF(objeto.getDocente().getCedula());
            return true;
            
        } catch (IOException ex) {
            System.out.println("Error COntrolador Curso: create");
        }
        return false;
    }

    @Override
    public Curso read(Curso objeto) {
        int salto = 0;
        try {
            while (salto < archivo.length()) {                
                archivo.seek(salto);
                int codigo = archivo.readInt();
                if(codigo == objeto.getCodigo()){
                    archivo.seek(salto);
                    return  new Curso(archivo.readInt(), archivo.readUTF(), archivo.readInt(),
                            controladorPersona.read(new Persona(0, "", archivo.readUTF(), "", "", "", "")));
                }
                salto+=REGISTRO;
            }  
        } catch (IOException ex) {
            System.out.println("Erro rcontrolador Curso: read");
        }
        return null;
    }

    @Override
    public boolean update(Curso objeto) {
        int salto = 0;
        try {
            
            while (salto < archivo.length()) {                
                archivo.seek(salto);
                int codigo = archivo.readInt();
                if(codigo==objeto.getCodigo()){
                    archivo.seek(salto);
                    archivo.writeInt(objeto.getCodigo());
                    archivo.writeUTF(objeto.getCurso());
                    archivo.writeInt(objeto.getAlumnos());
                    archivo.writeUTF(objeto.getDocente().getCedula());
                    
                    return true;
                }
                salto+=REGISTRO; 
           }
            
        } catch (IOException ex) {
            System.out.println("Error controlador Curso: update");
        }
        return false;
    }

    @Override
    public boolean delite(Curso objeto) {
        int salto = 0;
        try {
            while(salto<archivo.length()){
                archivo.seek(salto);
                int codigo = archivo.readInt();
                if(codigo==objeto.getCodigo()){
                    archivo.seek(salto);
                    archivo.writeInt(-100);
                    archivo.writeUTF(String.format("%-" + 15 + "s", ""));
                    archivo.writeInt(-100);
                    archivo.writeUTF(String.format("%-" + 10 + "s", ""));
                    return true;
                }
                salto+=REGISTRO;
            }
        } catch (IOException ex) {
            System.out.println("Error controlador Curso: delite");
        }
        return false;
    }

    @Override
    public List<Curso> findAll() {
        List<Curso> lista = new ArrayList<>();
        int salto = 0;
        
        try {
            while (salto< archivo.length()) {
                
                archivo.seek(salto);
                int codigo = archivo.readInt();
                if(codigo>0){
                    lista.add(new Curso(codigo, archivo.readUTF(), archivo.readInt(),
                            controladorPersona.read(new Persona(0, "", archivo.readUTF(), "", "", "", ""))));
                }
                salto+=REGISTRO;
            }
        } catch (IOException ex) {
            System.out.println("Error controlador Persona findAll");
        }
        return lista;
    }
    
    public Curso buscarPorCurso(String curso){
        List<Curso> lista = findAll();
        for (Curso curso1 : lista) {
            System.out.println(curso1.getCurso());
            if(curso1.getCurso().trim().equals(curso)){
                return curso1;
            }
        }
        return null;
    }
}
