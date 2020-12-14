package ec.edu.ups.vista;

import ec.edu.ups.controlador.ControladorParquadero;
import ec.edu.ups.controlador.ControladorVehiculo;
import ec.edu.ups.modelo.Parqueadero;
import ec.edu.ups.modelo.Vehiculo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PantallaIngresarVehiculo {

    ControladorVehiculo controladorVehiculo;
    ControladorParquadero controladorParquadero;

    Vehiculo vehiculo;

    public PantallaIngresarVehiculo() {
        controladorVehiculo = controladorVehiculo.getInstance();
        controladorParquadero = controladorParquadero.getInstance();
    }

    @FXML
    private TextField txtBuscarPlaca;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnRegistrar;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtPlaca;

    @FXML
    private TextField txtFecha;

    @FXML
    private Button btnIngresar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtPuestos;

    @FXML
    private Button btnPuestos;



    @FXML
    void eventBuscar(ActionEvent event) {
        String buscar = txtBuscarPlaca.getText();

        if(buscar.isEmpty()){
            JOptionPane.showMessageDialog(null, "Llene el campo de busqueda");
        }else{
            Vehiculo vehiculo = controladorVehiculo.read(new Vehiculo(buscar,"", null ));
            if(vehiculo !=null){
                this.vehiculo = vehiculo;
                txtCedula.setText(vehiculo.getPersona().getCedula());
                txtPlaca.setText(vehiculo.getPlaca());
                Date date = new Date();
                txtFecha.setText(String.valueOf(date));
            }else{
                JOptionPane.showMessageDialog(null, "Vehiculo no registrado");
            }
        }
    }

    @FXML
    void eventCancelar(ActionEvent event) {
        Stage stage = (Stage) this.btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void eventIngresar(ActionEvent event) {
        String fecha =  txtFecha.getText();
        String puesto = txtPuestos.getText();
        DateFormat fechaHora =  new SimpleDateFormat("EEE MMM dd HH:mm:ss 'COT' yyyy", Locale.ENGLISH);
        Date f = null;
        try {
            f = fechaHora.parse(fecha);
        } catch (ParseException e) {
            System.out.println("Error obtener la fecha");;
        }
        if(puesto.isEmpty()){
            JOptionPane.showMessageDialog(null, "Llene los campos");
        }else{
            if(Integer.parseInt(puesto)>=1 && Integer.parseInt(puesto)<=50){
                if(controladorParquadero.comprobarPuestos(vehiculo, Integer.parseInt(puesto))){
                    Parqueadero comprobar = controladorParquadero.read(new Parqueadero(0, 0, "", vehiculo, null, null, 0));
                    if(comprobar==null){
                        boolean cent = controladorParquadero.create(new Parqueadero(controladorParquadero.generarId(),Integer.parseInt(puesto), "Parqueado", vehiculo, f, null, 0));
                        if(cent){
                            JOptionPane.showMessageDialog(null, "Vehiculo ingresado correctamente");
                            try {
                                comprebante(vehiculo);
                            } catch (IOException e) {

                            }
                            limpiar();
                        }else{
                            JOptionPane.showMessageDialog(null, "No se ha podido ingresar correctamente");
                        }
                    }else{
                        boolean cent = controladorParquadero.update(comprobar, new Parqueadero(controladorParquadero.generarId(),Integer.parseInt(puesto), "Parqueado", vehiculo, f, null, 0));
                        if(cent){
                            JOptionPane.showMessageDialog(null, "Vehiculo ingresado correctamente");
                            try {
                                comprebante(vehiculo);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            limpiar();
                        }else{
                            JOptionPane.showMessageDialog(null, "No se ha podido ingresar correctamente");
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Puesto actualmente ocupado o vehiculo parqueado");
                }
            }else {
                JOptionPane.showMessageDialog(null, "Puesto fuera de rango \n(Selecione del 1 al 50)");
            }
        }
    }

    @FXML
    void eventRegistrar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader ventana = new FXMLLoader(getClass().getResource("PantallaRegistroVehiculos.fxml"));
        Parent root = ventana.load();
        PantallaRegistroVehiculos pantallaRegistro = ventana.getController();
        pantallaRegistro.recibirRol("Cliente");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void eventVerPuestos(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PantallaParqueadero.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void limpiar(){
        txtFecha.setText("");
        txtPlaca.setText("");
        txtPuestos.setText("");
        txtCedula.setText("");
        txtBuscarPlaca.setText("");
    }

    public void comprebante(Vehiculo vehiculo) throws IOException {
        Parqueadero parqueadero = controladorParquadero.read(new Parqueadero(0, 0, "", vehiculo, null, null, 0));
        Stage stage = new Stage();
        FXMLLoader ventana = new FXMLLoader(getClass().getResource("PantallaConprobante.fxml"));
        Parent root = ventana.load();
        PantallaConprobante pantallaRegistro = ventana.getController();
        pantallaRegistro.llegarDato(parqueadero);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}