package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Persona;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian Uyaguari
 */
public class ControladorTestigo extends AbstractControlador<Persona>{

    private static ControladorTestigo instance = new ControladorTestigo();
    
    private static int REGISTRO;
    private RandomAccessFile archivo;
    
    /**
     * Estructura del Archivo.
     * Codigo: 4 bytes
     * Cedula: 10 bytes
     * Genero: 10 bytes
     * Nombre: 50 bytes
     * Apellido: 50 bytes
     * Direccion: 75 bytes
     * FechaNacimiento:30 bytes
     * Total: 239 bytes
    **/
    public ControladorTestigo() {
        try {
            REGISTRO=239;
            archivo = new RandomAccessFile("/datos/testigos.txt", "rw");
            listar();
        } catch (FileNotFoundException ex) {
            System.out.println("Error lectura/escritura |Cotrolador jueces|");
        }
    }

    public static ControladorTestigo getInstane(){
        return instance;
    }
    
    @Override
    public boolean createArchivo(Persona persona) {
        try {
            archivo.seek(archivo.length());
            archivo.writeInt(persona.getCodigo());
            archivo.writeUTF(persona.getCedula());
            archivo.writeUTF(persona.getGenero());
            archivo.writeUTF(persona.getNombre());
            archivo.writeUTF(persona.getApellido());
            archivo.writeUTF(persona.getDireccion());
            archivo.writeUTF(persona.getFechaNacimiento()+"");
            return true;
        } catch (IOException ex) {
            System.out.println("Error lectura/escritura controlador Contrayente create");
        }
        return false;
    }

    @Override
    public boolean updateArchivo(Persona persona) {
        int salto = 0;
        try {
            while (salto < archivo.length()) {
                archivo.seek(salto);
                int codigo = archivo.readInt();
                if (codigo == persona.getCodigo()) {
                    archivo.seek(salto);
                    archivo.writeInt(persona.getCodigo());
                    archivo.writeUTF(persona.getCedula());
                    archivo.writeUTF(persona.getGenero());
                    archivo.writeUTF(persona.getNombre());
                    archivo.writeUTF(persona.getApellido());
                    archivo.writeUTF(persona.getDireccion());
                    archivo.writeUTF(persona.getFechaNacimiento()+"");
                    return true;
                }
                salto += REGISTRO;
            }
        } catch (IOException ex) {
            System.out.println("Error lectura/escritura controlador Testigo update");
        }
        return false;
    }

    @Override
    public boolean deliteArchivo(Persona persona) {
        String cadena = "";
        int salto = 0;
        try {
            while (salto < archivo.length()) {
                archivo.seek(salto);
                int codigo = archivo.readInt();
                if (codigo == persona.getCodigo()) {
                    archivo.seek(salto);
                    archivo.writeInt(-100);
                    archivo.writeUTF(String.format("%-" + 10 + "s", cadena));
                    archivo.writeUTF(String.format("%-" + 10 + "s", cadena));
                    archivo.writeUTF(String.format("%-" + 50 + "s", cadena));
                    archivo.writeUTF(String.format("%-" + 50 + "s", cadena));
                    archivo.writeUTF(String.format("%-" + 75 + "s", cadena));
                    archivo.writeUTF(String.format("%-" + 28 + "s", cadena));
                    return true;
                }
                salto += REGISTRO;
            }
        } catch (IOException ex) {
            System.out.println("Error lectura/escritura controlador Testigo delite");
        }
        return false;
    }

    @Override
    public int generarCodigo() {
        List<Persona> lista = getLista();
        int codigo = 0;
        if (lista.size()>0){
            for (Persona persona : lista) {
                int aux = persona.getCodigo();
                if(aux>codigo){
                    codigo=aux;
                }
            }
            return codigo+1;
        }else{
            return 1;
        }
    }

    @Override
    public boolean validar(Persona objecto) {
        String generos[] = {"hombre", "mujer", "masculino", "femenino"};
        for (int i = 0; i < generos.length; i++) {
            if(objecto.getGenero().equalsIgnoreCase(generos[i])){
                return true;
            }
        }
        return false;
    }

    @Override
    public void listar() {
        List<Persona> lista = new ArrayList<Persona>();
        int salto = 0;
        try {
            while (salto < archivo.length()) {
                
                archivo.seek(salto);
                int codigo = archivo.readInt();
                if(codigo>=0){
                     String cedula = archivo.readUTF();
                    String genero = archivo.readUTF();
                    String nombre= archivo.readUTF();
                    String apellido= archivo.readUTF();
                    String direccion= archivo.readUTF();
                    String fecha= archivo.readUTF();



                   int dia = Integer.parseInt(fecha.substring(7,10));
                   int año = Integer.parseInt(fecha.substring(24,28));
                   int mes = recuperarMes(fecha.substring(3,7));
                   Date di = new Date(año-1900, mes, dia);
                   Persona p = new Persona(codigo, cedula, genero, nombre, apellido, direccion, di);
                   lista.add(p);
                }

                salto += REGISTRO;
            }
            setLista(lista);
        } catch (IOException ex) {
            System.out.println("Error lectrura/escritura controlador Usuario)");
            ex.printStackTrace();
        }
    }
    
    public int recuperarMes(String  mes){
        String meses[] = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        for (int i = 0; i < 12; i++) {
            if(meses[i].equals(mes)){
                return i;
            }
        }
        return 0;
    }
    
}

        
        