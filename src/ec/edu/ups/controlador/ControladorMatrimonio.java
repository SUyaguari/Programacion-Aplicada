package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Matrimonio;
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
public class ControladorMatrimonio extends AbstractControlador<Matrimonio>{
    
    private static ControladorMatrimonio instance = new ControladorMatrimonio();
    
    ControladorContrayente controladorContrayente;
    ControladorJuez controladorJuez;
    ControladorTestigo controladorTestigo;

    private RandomAccessFile archivo;
    private static int REGISTRO;
    /**
     * Estructura del archivo.
     * codigo : 4 byts
     * lugar: 100 byts
     * fecha: 28 byts
     * contrayente1: 4 byts
     * contrayente2: 4 byts
     * testigo1: 4 byts
     * testigo2: 4 byts
     * autoridad: 4 byts
     * total 156 bytes
     */
    public ControladorMatrimonio() {
        try {
            REGISTRO = 156;
            archivo = new RandomAccessFile("/datos/matrimonio.txt", "rw");
        } catch (FileNotFoundException ex) {
            System.out.println("Error lectura/escritura |Cotrolador matrimonio|");
        }
    }
    
    public static ControladorMatrimonio getInstance(){
        return instance;
    }

    @Override
    public boolean createArchivo(Matrimonio matrimonio) {
        try {
            archivo.seek(archivo.length());
            archivo.writeInt(matrimonio.getCodigo());
            archivo.writeUTF(matrimonio.getLugar());
            archivo.writeUTF(matrimonio.getFecha()+"");
            archivo.writeInt(matrimonio.getContrayente1().getCodigo());
            archivo.writeInt(matrimonio.getContrayente2().getCodigo());
            archivo.writeInt(matrimonio.getTestigo1().getCodigo());
            archivo.writeInt(matrimonio.getTestigo2().getCodigo());
            archivo.writeInt(matrimonio.getAutoridad().getCodigo());
            return true;
        } catch (IOException ex) {
            System.out.println("Error lectura/escritura controlador Matrimonio create");
        }
        return false;
    }

    @Override
    public boolean updateArchivo(Matrimonio matrimonio) {
        int salto = 0;
        try {
            while (salto < archivo.length()) {
                archivo.seek(salto);
                int codigo = archivo.readInt();
                if (codigo == matrimonio.getCodigo()) {
                    archivo.seek(salto);
                    archivo.writeInt(matrimonio.getCodigo());
                    archivo.writeUTF(matrimonio.getLugar());
                    archivo.writeUTF(matrimonio.getFecha()+"");
                    archivo.writeInt(matrimonio.getContrayente1().getCodigo());
                    archivo.writeInt(matrimonio.getContrayente2().getCodigo());
                    archivo.writeInt(matrimonio.getTestigo1().getCodigo());
                    archivo.writeInt(matrimonio.getTestigo2().getCodigo());
                    archivo.writeInt(matrimonio.getAutoridad().getCodigo());
                    return true;
                }
                salto += REGISTRO;
            }
        } catch (IOException ex) {
            System.out.println("Error lectura/escritura controlador Matrimonio update");
        }
        return false;
    }

    @Override
    public boolean deliteArchivo(Matrimonio matrimonio) {
        String cadena = "";
        int salto = 0;
        try {
            while (salto < archivo.length()) {
                archivo.seek(salto);
                int codigo = archivo.readInt();
                if (codigo == matrimonio.getCodigo()) {
                    archivo.seek(salto);
                    archivo.writeInt(-100);
                    archivo.writeUTF(String.format("%-" + 100 + "s", cadena));
                    archivo.writeUTF(String.format("%-" + 28 + "s", cadena));
                    archivo.writeInt(-100);
                    archivo.writeInt(-100);
                    archivo.writeInt(-100);
                    archivo.writeInt(-100);
                    archivo.writeInt(-100);
                    
                    return true;
                }
                salto += REGISTRO;
            }
        } catch (IOException ex) {
            System.out.println("Error lectura/escritura controlador Matrimonio delite");

        }
        return false;
    }

    @Override
    public int generarCodigo() {
        List<Matrimonio> lista = getLista();
        int codigo = 0;
        if (lista.size()>0){
            for (Matrimonio matrimonio : lista) {
                int aux = matrimonio.getCodigo();
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
    public boolean validar(Matrimonio matrimonio) {
        return true;
    }   
    
    @Override
    public void listar() {
        List<Matrimonio> lista = new ArrayList<Matrimonio>();
        int salto = 0;
        try {
            while (salto < archivo.length()) {
                
                archivo.seek(salto);
                int codigo = archivo.readInt();
                if(codigo>=0){
                    
                    String lugar = archivo.readUTF();
                    String fecha = archivo.readUTF();
                    int contra1 = archivo.readInt();
                    int contra2= archivo.readInt();
                    int tes1= archivo.readInt();
                    int tes2= archivo.readInt();
                    int autor= archivo.readInt();



                   int dia = Integer.parseInt(fecha.substring(7,10));
                   int año = Integer.parseInt(fecha.substring(24,28));
                   int mes = recuperarMes(fecha.substring(3,7));
                   Date di = new Date(año-1900, mes, dia);

                   Matrimonio m = new Matrimonio(codigo, lugar, di, buscarContrayente(contra1), buscarContrayente(contra2),
                           buscarTestigos(tes1), buscarTestigos(tes2), 
                           buscarAutoridad(autor));

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
    
    public Persona buscarContrayente(int contribuyente){
        controladorContrayente = ControladorContrayente.getInstance();
        return controladorContrayente.read(new Persona(contribuyente, "", "", "", "", "", null));
    }
    
    public Persona buscarTestigos(int testigo){
        controladorTestigo = ControladorTestigo.getInstane();
        return controladorContrayente.read(new Persona(testigo, "", "", "", "", "", null));
    }
    
    public Persona buscarAutoridad(int autoridad){
        controladorJuez = ControladorJuez.getInstance();
        return controladorContrayente.read(new Persona(autoridad, "", "", "", "", "", null));
    }
}
