package ec.edu.ups.vista;

import ec.edu.ups.modelo.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PantallaGestion {

    Persona persona;

    public PantallaGestion() {

    }

    public void recibirPersona(Persona persona){
        this.persona = persona;
        txtNombre.setText(persona.getNombre().trim()+" "+persona.getApellido().trim());
        txtRol.setText(persona.getRol().trim());
        if(!persona.getRol().equals("Administrador")){
            btnRegistrarUsuarios.setVisible(false);
        }
    }

    public void cargarDatos(){

    }

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnRetirarVehiculo;

    @FXML
    private Button btnReservaciones;

    @FXML
    private Button btnParqueadero;

    @FXML
    private Button btnIngresarVehiculos;

    @FXML
    private Button btnRegistrarUsuarios;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtRol;

    @FXML
    void eventCerrarSesion(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader ventana = new FXMLLoader(getClass().getResource("PantallaLogin.fxml"));
        Parent root = ventana.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) this.btnCerrarSesion.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void eventIngresarVehiculos(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader ventana = new FXMLLoader(getClass().getResource("PantallaIngresarVehiculo.fxml"));
        Parent root = ventana.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void eventParqueadero(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PantallaParqueadero.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void eventRegistrarUsuarios(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader ventana = new FXMLLoader(getClass().getResource("PantallaRegistro.fxml"));
        Parent root = ventana.load();
        PantallaRegistro pantallaRegistro = ventana.getController();
        pantallaRegistro.recibirRol("Usuario");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void eventReservaciones(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PantallaReservacion.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void eventRetirarVehiculo(ActionEvent event) throws IOException {
        FXMLLoader ventana = new FXMLLoader(getClass().getResource("PantallaRetirarVehiculo.fxml"));
        Parent root = ventana.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}