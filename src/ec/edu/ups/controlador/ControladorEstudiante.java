package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Estudiantes;
import ec.edu.ups.modelo.Persona;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sebastian Uyaguari
 */
public class ControladorEstudiante extends AbstractControlador<Estudiantes>{
    
    private static ControladorEstudiante instance = new ControladorEstudiante();
    
    ControladorCurso controladorCurso;
    ControladorPersona controladorPersona;

    private RandomAccessFile archivo;
    private static int REGISTRO=87;
    
    /**
     * Estructura del archivo.
     * 
     * codigo: 4 bytes
     * nombre: 25 bytes + 2 bytes
     * apellido: 25 bytes + 2 bytes
     * curso: 15 bytes + 2 bytes
     * docente: 10 bytes + 2 bytes
     * 
     * total: 87 bytes
     */
    public ControladorEstudiante() {
        try {
            archivo = new RandomAccessFile("datos/estudiante.txt", "rw");
            controladorCurso = ControladorCurso.getInstance();
            controladorPersona = ControladorPersona.getInstance();
        } catch (FileNotFoundException ex) {
            System.out.println("Error COntrolador persona: create");
        }
    }

    public static ControladorEstudiante getInstance() {
        return instance;
    }

    @Override
    public boolean create(Estudiantes objeto) {
         try {
            
            archivo.seek(archivo.length());
            archivo.writeInt(objeto.getCodigo());
            archivo.writeUTF(objeto.getNombre());
            archivo.writeUTF(objeto.getApellido());
            archivo.writeUTF(objeto.getCurso().getCurso());
            archivo.writeUTF(objeto.getDocente().getCedula());
            return true;
            
        } catch (IOException ex) {
            System.out.println("Error COntrolador Estudiante: create");
        }
        return false;
    }

    @Override
    public Estudiantes read(Estudiantes objeto) {
        int salto = 0;
        try {
            while (salto < archivo.length()) {                
                archivo.seek(salto);
                int codigo = archivo.readInt();
                if(codigo == objeto.getCodigo()){
                    archivo.seek(salto);
                    return new Estudiantes(archivo.readInt(), archivo.readUTF(), archivo.readUTF(),
                        controladorCurso.buscarPorCurso(archivo.readUTF()),
                            controladorPersona.read(new Persona(0, "", archivo.readUTF(),"", "", "", "")));
                   
                }
                salto+=REGISTRO;
            }  
        } catch (IOException ex) {
            System.out.println("Erro rcontrolador estudiante: read");
        }
        return null;
    }

    @Override
    public boolean update(Estudiantes objeto) {
        int salto = 0;
        try {
            
            while (salto < archivo.length()) {                
                archivo.seek(salto);
                int codigo = archivo.readInt();
                if(codigo==objeto.getCodigo()){
                    archivo.seek(salto);
                    archivo.writeInt(objeto.getCodigo());
                    archivo.writeUTF(objeto.getNombre());
                    archivo.writeUTF(objeto.getApellido());
                    archivo.writeUTF(objeto.getCurso().getCurso());
                    archivo.writeUTF(objeto.getDocente().getCedula());
                    return true;
                }
                salto+=REGISTRO; 
           }
            
        } catch (IOException ex) {
            System.out.println("Error controlador Estudiante: update");
        }
        return false;
    }

    @Override
    public boolean delite(Estudiantes objeto) {
        int salto = 0;
        try {
            while(salto<archivo.length()){
                archivo.seek(salto);
                int codigo = archivo.readInt();
                if(codigo==objeto.getCodigo()){
                    archivo.seek(salto);
                    archivo.writeInt(-100);
                    archivo.writeUTF(String.format("%-" + 25 + "s", ""));
                    archivo.writeUTF(String.format("%-" + 25 + "s", ""));
                    archivo.writeUTF(String.format("%-" + 15 + "s", ""));
                    archivo.writeUTF(String.format("%-" + 10 + "s", ""));
                    return true;
                }
                salto+=REGISTRO;
            }
        } catch (IOException ex) {
            System.out.println("Error controlador Estudiante: delite");
        }
        return false;
    }

    @Override
    public List<Estudiantes> findAll() {
        List<Estudiantes> lista = new ArrayList<>();
        int salto = 0;
        
        try {
            while (salto< archivo.length()) {
                archivo.seek(salto);
                int codigo = archivo.readInt();
               if(codigo>0){
                    lista.add(new Estudiantes(codigo, archivo.readUTF(), archivo.readUTF(),
                        controladorCurso.buscarPorCurso(archivo.readUTF().trim()),
                            controladorPersona.read(new Persona(0, "", archivo.readUTF(),"", "", "", ""))));
                }
                salto+=REGISTRO;
            }
        } catch (IOException ex) {
            System.out.println("Error controlador Estudiante findAll " );
        }
        return lista;
    }
}
