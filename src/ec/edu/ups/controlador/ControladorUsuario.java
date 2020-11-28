package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Persona;
import ec.edu.ups.modelo.Usuario;
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
public class ControladorUsuario extends AbstractControlador<Usuario>{

    private static ControladorUsuario instance = new ControladorUsuario();
    
    private static int REGISTRO;
    private RandomAccessFile archivo;
    
    /**
     * Estructura del archivo.
     * codigo: 4 bytes
     * usuario: 30 bytes
     * contraseña 15 bytes
     * total: 53 bytes
     */
    public ControladorUsuario() {
        try {
            REGISTRO=53;
            archivo = new RandomAccessFile("datos/usuarios.txt", "rw");
            listar();
        } catch (FileNotFoundException ex) {
            System.out.println("Error lectura/escritura |Cotrolador Usuario|");
        }
    }
    
    public static ControladorUsuario getInstance(){
        return instance;
    }
    
    @Override
    public boolean createArchivo(Usuario usuario) {
        try {
            archivo.seek(archivo.length());
            archivo.writeInt(usuario.getCodigo());
            archivo.writeUTF(usuario.getUsuario());
            archivo.writeUTF(usuario.getContraseña());
            return true;
        } catch (IOException ex) {
            System.out.println("Error lectura/escritura controlador Usuario create");
        }
        return false;
    }

    @Override
    public boolean updateArchivo(Usuario usuario) {
        int salto = 0;
        try {
            while (salto < archivo.length()) {
                archivo.seek(salto);
                int codigo = archivo.readInt();
                if (codigo == usuario.getCodigo()) {
                    archivo.seek(salto);
                    archivo.writeInt(usuario.getCodigo());
                    archivo.writeUTF(usuario.getUsuario());
                    archivo.writeUTF(usuario.getContraseña());
                    return true;
                }
                salto += REGISTRO;
            }
        } catch (IOException ex) {
            System.out.println("Error lectura/escritura controlador Usuario update");
        }
        return false;
    }

    @Override
    public boolean deliteArchivo(Usuario usuario) {
        String cadena = "";
        int salto = 0;
        try {
            while (salto < archivo.length()) {
                archivo.seek(salto);
                int codigo = archivo.readInt();
                if (codigo == usuario.getCodigo()) {
                    archivo.seek(salto);
                    archivo.writeInt(-100);
                    archivo.writeUTF(String.format("%-" + 30 + "s", cadena));
                    archivo.writeUTF(String.format("%-" + 15 + "s", cadena));
                    return true;
                }
                salto += REGISTRO;
            }
        } catch (IOException ex) {
            System.out.println("Error lectura/escritura controlador Usuario delite");
        }
        return false;
    }

    @Override
    public int generarCodigo() {
         List<Usuario> lista = getLista();
        int codigo = 0;
        if (lista.size()>0){
            for (Usuario usuario : lista) {
                int aux = usuario.getCodigo();
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
    public boolean validar(Usuario objecto) {
        return true;
    }
    
    public boolean iniciarSesion(String usuario, String contraseña){
        List<Usuario> lista = getLista();
        
            for (Usuario usuario1 : lista) { 
            
               if(usuario1.getUsuario().trim().equals(usuario) && usuario1.getContraseña().trim().equals(contraseña)){
                    return true;
                }
            }
        
            return false;
    }

    @Override
    public void listar() {
        List<Usuario> lista = new ArrayList<Usuario>();
        int salto = 0;
        try {
            
            while (salto < archivo.length()) {
                
                archivo.seek(salto);
                
                int codigo = archivo.readInt();
                if(codigo>=0){
                    String usuario = archivo.readUTF();
                    String contraseña = archivo.readUTF();

                    Usuario u = new Usuario(codigo, usuario, contraseña);
                    lista.add(u);
                }

                salto += REGISTRO;
            }
            setLista(lista);
        } catch (IOException ex) {
            System.out.println("Error lectrura/escritura controlador Usuario)");
            ex.printStackTrace();
        }
    }
    
}
