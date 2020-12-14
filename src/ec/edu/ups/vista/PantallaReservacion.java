package ec.edu.ups.vista;

import ec.edu.ups.controlador.ControladorParquadero;
import ec.edu.ups.controlador.ControladorVehiculo;
import ec.edu.ups.modelo.Parqueadero;
import ec.edu.ups.modelo.Vehiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class PantallaReservacion implements Initializable {

    ControladorParquadero controladorParquadero;
    ControladorVehiculo controladorVehiculo;
    Vehiculo vehiculo;

    public PantallaReservacion() {
        controladorVehiculo = controladorVehiculo.getInstance();
        controladorParquadero  = controladorParquadero.getInstance();
    }

    @FXML
    private TextField txtBuscarPlaca;

    @FXML
    private Button btnBuscar;

    @FXML
    private TextField txtFechaIngreso;

    @FXML
    private Text lblIngreso;

    @FXML
    private Text lblSalida;

    @FXML
    private TextField txtCedula;

    @FXML
    private DatePicker fechaSalida;

    @FXML
    private ComboBox<String> cbxTipo;

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private DatePicker fechaSalida1;

    @FXML
    private Button btnRegistrar;

    @FXML
    private TextField txtPuesto;

    @FXML
    private Button btnParqueadero;


    @FXML
    void eventAceptar(ActionEvent event) {

        String tipo = cbxTipo.getSelectionModel().getSelectedItem();
        String puesto = txtPuesto.getText();
        switch (tipo){
            case "Arrentamiento":
                String fecaIngreso = txtFechaIngreso.getText();
                String fechaSalid =  fechaSalida.getValue()+"";
                if(fecaIngreso.isEmpty() || fechaSalid.isEmpty() || puesto.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Llene los campos");
                }else{
                   if(Integer.parseInt(puesto)>=1 && Integer.parseInt(puesto)<=50){
                       if(controladorParquadero.comprobarPuestos(vehiculo, Integer.parseInt(puesto))){
                           DateFormat fechaHora =  new SimpleDateFormat("EEE MMM dd HH:mm:ss 'COT' yyyy", Locale.ENGLISH);
                           DateFormat fechaHora2 =  new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
                           Date f = null;
                           Date f2 = null;
                           try {
                               f = fechaHora.parse(fecaIngreso);
                               f2 = fechaHora2.parse(fechaSalid);
                               System.out.println(f);
                               System.out.println(f2);
                           } catch (ParseException e) {
                               System.out.println("Error obtener la fecha");;
                           }
                           Parqueadero comprobar = controladorParquadero.read(new Parqueadero(0, 0, "", vehiculo, null, null, 0));
                           if(comprobar==null){
                               boolean cent = controladorParquadero.create(new Parqueadero(controladorParquadero.generarId(),Integer.parseInt(puesto), tipo, vehiculo, f, f2, 0));
                               if(cent){
                                   JOptionPane.showMessageDialog(null, "Vehiculo ingresado correctamente");
                                   Stage stage = (Stage) this.btnAceptar.getScene().getWindow();
                                   stage.close();
                               }else{
                                   JOptionPane.showMessageDialog(null, "No se ha podido ingresar correctamente");
                               }
                           }else{
                               boolean cent = controladorParquadero.update(comprobar, new Parqueadero(controladorParquadero.generarId(),Integer.parseInt(puesto), tipo, vehiculo, f, f2, 0));
                               if(cent){
                                   JOptionPane.showMessageDialog(null, "Vehiculo ingresado correctamente");
                                   Stage stage = (Stage) this.btnAceptar.getScene().getWindow();
                                   stage.close();
                               }else{
                                   JOptionPane.showMessageDialog(null, "No se ha podido ingresar correctamente");
                               }
                           }
                       }else{
                           JOptionPane.showMessageDialog(null, "Puesto actualmente ocupado o vehiculo parqueado");
                       }
                   }else{
                       JOptionPane.showMessageDialog(null, "Puesto fuera de rango \n(Selecione del 1 al 50)");
                   }
                }
                break;
            case "Reservado":

                String fechaEntrada = fechaSalida1.getValue()+"";
                String fechaSali =  fechaSalida.getValue()+"";
                if(fechaEntrada.isEmpty() || fechaSali.isEmpty() || puesto.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Llene los campos");
                }else{
                    if(Integer.parseInt(puesto)>=1 && Integer.parseInt(puesto)<=50){
                        if(controladorParquadero.comprobarPuestos(vehiculo, Integer.parseInt(puesto))){
                            DateFormat fechaHora =  new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
                            Date f = null;
                            Date f2 = null;
                            try {
                                f = fechaHora.parse(fechaEntrada);
                                f2 = fechaHora.parse(fechaSali);
                            } catch (ParseException e) {
                                System.out.println("Error obtener la fecha");;
                            }
                            Parqueadero comprobar = controladorParquadero.read(new Parqueadero(0, 0, "", vehiculo, null, null, 0));
                            if(comprobar==null){
                                boolean cent = controladorParquadero.create(new Parqueadero(controladorParquadero.generarId(),Integer.parseInt(puesto), tipo, vehiculo, f, f2, 0));
                                if(cent){
                                    JOptionPane.showMessageDialog(null, "Vehiculo ingresado correctamente");
                                    try {
                                        comprebante(vehiculo);
                                    } catch (IOException e) {

                                    }
                                    Stage stage = (Stage) this.btnAceptar.getScene().getWindow();
                                    stage.close();
                                }else{
                                    JOptionPane.showMessageDialog(null, "No se ha podido ingresar correctamente");
                                }

                            }else{
                                boolean cent = controladorParquadero.update(comprobar,new Parqueadero(controladorParquadero.generarId(),Integer.parseInt(puesto), tipo, vehiculo, f, f2, 0));
                                if(cent){
                                    JOptionPane.showMessageDialog(null, "Vehiculo ingresado correctamente");
                                    try {
                                        comprebante(vehiculo);
                                    } catch (IOException e) {

                                    }
                                    Stage stage = (Stage) this.btnAceptar.getScene().getWindow();
                                    stage.close();
                                }else{
                                    JOptionPane.showMessageDialog(null, "No se ha podido ingresar correctamente");
                                }
                            }

                        }else{
                            JOptionPane.showMessageDialog(null, "Puesto actualmente ocupado o vehiculo parqueado");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Puesto fuera de rango \n(Selecione del 1 al 50)");
                    }
                }
                break;
        }
    }

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
                Date date = new Date();
                txtFechaIngreso.setText(String.valueOf(date));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> lista = FXCollections.observableArrayList("Arrentamiento", "Reservado");
        cbxTipo.setItems(lista);
        lblIngreso.setVisible(false);
        lblSalida.setVisible(false);
        txtFechaIngreso.setVisible(false);
        fechaSalida.setVisible(false);
        fechaSalida1.setVisible(false);
    }

    @FXML
    void eventCbxMostrar(ActionEvent event) {
        if(cbxTipo.getSelectionModel().getSelectedItem().equals("Arrentamiento")){
            lblIngreso.setVisible(true);
            lblSalida.setVisible(true);
            txtFechaIngreso.setVisible(true);
            fechaSalida.setVisible(true);
            fechaSalida1.setVisible(false);
        }
        if(cbxTipo.getSelectionModel().getSelectedItem().equals("Reservado")){
            lblIngreso.setVisible(true);
            lblSalida.setVisible(true);
            fechaSalida1.setVisible(true);
            fechaSalida.setVisible(true);
            txtFechaIngreso.setVisible(false);
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
    void eventMostrarParqueadero(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PantallaParqueadero.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
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