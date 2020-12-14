package ec.edu.ups.controlador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractControlador <T>{

    private FileInputStream archivoLectura;
    private FileOutputStream archivoEscritura;
    private ObjectInputStream objetoLectura;
    private ObjectOutputStream objetoEscritura;
    private String ruta;
    private List<T> lista;

    public AbstractControlador(){
        lista = new ArrayList<>();
    }

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public void leerDatosArchivo(){
        try {
            archivoLectura = new FileInputStream(ruta);
            try {
                objetoLectura = new ObjectInputStream(archivoLectura);
            }catch (EOFException e){
                System.out.println("Error llegue al final al comenzar");
            }
            try {
                lista = (List<T>) objetoLectura.readObject();;
                System.out.println(lista);
            } catch (NullPointerException e){
                System.out.println("Error archivo nulo");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error al abrir el archivo ");
        }catch (IOException e) {
            System.out.println("Erro lectura escritura");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Error");
        }
    }

    public boolean escribirDatosArchivo(List<T> lista){
        try {
            archivoEscritura = new FileOutputStream(ruta);
            objetoEscritura = new ObjectOutputStream(archivoEscritura);

            objetoEscritura.writeObject(lista);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean create(T objeto){
        if(lista.add(objeto))
            return escribirDatosArchivo(lista);

        return false;

    }

    public T read(T objeto){
        for (T objetoo: lista) {
            if(objeto.equals(objetoo)){
                return objetoo;
            }
        }
        return null;
    }

    public boolean update(T objeto, T nuevo){
        if(lista.contains(objeto)){
            int i = lista.indexOf(objeto);
            lista.set(i, nuevo);
            return escribirDatosArchivo(lista);
        }
        return false;
    }

    public boolean delite(T objeto){
        if(lista.contains(objeto)){
            if(lista.remove(objeto))
                return escribirDatosArchivo(lista);
        }
        return false;
    }


}
