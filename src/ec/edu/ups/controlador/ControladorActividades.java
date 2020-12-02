package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Actividad;
import ec.edu.ups.modelo.Persona;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Estudiantes
 */
public class ControladorActividades extends AbstractControlador<Actividad>{
    
    private static ControladorActividades intance = new ControladorActividades();
    private static int REGISTRO = 149;
    private RandomAccessFile archivo;
    
    ControladorCurso controladorCurso;
    ControladorPersona controladorPersona;
    /**
     * Estructura del archivo
     * 
     * codigo: 4
     * nombreA: 30 + 2
     * curso: 15 + 2 
     * docente: 10 + 2
     * url: 50 + 2
     * nombre: 30 + 2
     * 
     * total: 117
     */
    public ControladorActividades() {
        try {
            archivo = new RandomAccessFile("datos/actividad.txt", "rw");
            controladorCurso = ControladorCurso.getInstance();
            controladorPersona = ControladorPersona.getInstance();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControladorActividades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ControladorActividades getIntance() {
        return intance;
    }

    @Override
    public boolean create(Actividad objeto) {
        try {
            archivo.seek(archivo.length());
            archivo.writeInt(objeto.getCodigo());
            archivo.writeUTF(objeto.getNombreActividad());
            archivo.writeUTF(objeto.getCurso().getCurso());
            archivo.writeUTF(objeto.getDocente().getCedula());
            archivo.writeUTF(objeto.getUrl());
            archivo.writeUTF(objeto.getNombre());
            return true;
            
        } catch (IOException ex) {
            System.out.println("Error COntrolador Actividad: create");
        }
        return false;
    }

    @Override
    public Actividad read(Actividad objeto) {
        int salto = 0;
        try {
            while (salto < archivo.length()) {                
                archivo.seek(salto);
                int codigo = archivo.readInt();
                if(codigo == objeto.getCodigo()){
                    archivo.seek(salto);
                    return  new Actividad(codigo, archivo.readUTF(), controladorCurso.buscarPorCurso(archivo.readUTF().trim()),
                            controladorPersona.read(new Persona(0, "", archivo.readUTF(), "", "", "", "")), archivo.readUTF(), archivo.readUTF());
                }
                salto+=REGISTRO;
            }  
        } catch (IOException ex) {
            System.out.println("Erro rcontrolador Actividad: read");
        }
        return null;
    }

    @Override
    public boolean update(Actividad objeto) {
        int salto = 0;
        try {
            
            while (salto < archivo.length()) {                
                archivo.seek(salto);
                int codigo = archivo.readInt();
                if(codigo==objeto.getCodigo()){
                    archivo.seek(salto);
                    archivo.writeInt(objeto.getCodigo());
                    archivo.writeUTF(objeto.getNombreActividad());
                    archivo.writeUTF(objeto.getCurso().getCurso());
                    archivo.writeUTF(objeto.getDocente().getCedula());
                    archivo.writeUTF(objeto.getUrl());
                    archivo.writeUTF(objeto.getNombre());
                    return true;
                }
                salto+=REGISTRO; 
           }
            
        } catch (IOException ex) {
            System.out.println("Error controlador Actividad: update");
        }
        return false;
    }

    @Override
    public boolean delite(Actividad objeto) {
        int salto = 0;
        try {
            while(salto<archivo.length()){
                archivo.seek(salto);
                int codigo = archivo.readInt();
                if(codigo==objeto.getCodigo()){
                    archivo.seek(salto);
                    archivo.writeInt(-100);
                    archivo.writeUTF(String.format("%-" + 30 + "s", ""));
                    archivo.writeUTF(String.format("%-" + 15 + "s", ""));
                    archivo.writeUTF(String.format("%-" + 10 + "s", ""));
                    archivo.writeUTF(String.format("%-" + 50 + "s", ""));
                    archivo.writeUTF(String.format("%-" + 30 + "s", ""));
                    return true;
                }
                salto+=REGISTRO;
            }
        } catch (IOException ex) {
            System.out.println("Error controlador Actividad: delite");
        }
        return false;
    }

    @Override
    public List<Actividad> findAll() {
        List<Actividad> lista = new ArrayList<>();
        int salto = 0;
        
        try {
            while (salto< archivo.length()) {
                
                archivo.seek(salto);
                int codigo = archivo.readInt();
                if(codigo>0){
                    lista.add(new Actividad(codigo, archivo.readUTF(), controladorCurso.buscarPorCurso(archivo.readUTF().trim()),
                            controladorPersona.read(new Persona(0, "", archivo.readUTF(), "", "", "", "")), archivo.readUTF(), archivo.readUTF()));
                }
                salto+=REGISTRO;
            }
        } catch (IOException ex) {
            System.out.println("Error controlador Actividad findAll");
        }
        return lista;
    }
    
    public Map<String, String> url(String variable) throws MalformedURLException, IOException{
        Map<String, String> mapa = new HashMap<>();
        String s = "https://play.google.com/store/search?q=" + variable + "=apps&hl=es&gl=US";
        URL url = new URL(s);

        URLConnection conexion = url.openConnection();
        InputStream is = conexion.getInputStream();
        BufferedReader bufer = new BufferedReader(new InputStreamReader(is));
        String contenedor = "";
        String linea = bufer.readLine();
        while (null != linea) {
            contenedor += linea;
            linea = bufer.readLine();
            
        }
        
        Pattern titulo = Pattern.compile("((title=)(\")([0-9a-zA-Z]*\\s?)(\"))");
        Matcher matcher2 = titulo.matcher(contenedor);
        Pattern urll = Pattern.compile("((href=)\"(\\/store\\/apps\\/details)(.)(id=)(.\\S*)\")");
        Matcher matcher = urll.matcher(contenedor);
        
        while (matcher.find()){
            System.out.println(matcher.group());
        }
        while (matcher2.find() && matcher.find()) {
            mapa.put(matcher2.group(), matcher.group());
        }
        return mapa;
    }
    
}
