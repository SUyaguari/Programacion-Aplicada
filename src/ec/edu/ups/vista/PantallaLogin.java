package ec.edu.ups.vista;

import ec.edu.ups.controlador.ControladorPersona;
import ec.edu.ups.modelo.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class PantallaLogin {

    ControladorPersona controladorPersona;

    public PantallaLogin(){
        controladorPersona = controladorPersona.getInstance();
    }

    @FXML
    private PasswordField txtContraseña;

    @FXML
    private TextField txtCorreo;

    @FXML
    private Button btnEntrar;

    @FXML
    private Button btnRegistro;

    @FXML
    void EventInicioSesion(ActionEvent event) throws IOException {
        String correo = txtCorreo.getText();
        String contraseña = txtContraseña.getText();

        if(correo.isEmpty() || contraseña.isEmpty()){
            JOptionPane.showMessageDialog(null, "Llene todos los campos");
        }else{
            Persona p = controladorPersona.iniciarSesion(new Persona("", "", "", "", correo, contraseña));
            if(p!=null && (p.getRol().equals("Administrador") || p.getRol().trim().equals("Usuario"))){
                Stage stage = new Stage();
                FXMLLoader ventana = new FXMLLoader(getClass().getResource("PantallaGestion.fxml"));
                Parent root = ventana.load();
                PantallaGestion pantallaGestion = ventana.getController();
                pantallaGestion.recibirPersona(p);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) this.btnEntrar.getScene().getWindow();
                stage1.close();
            }else{
                JOptionPane.showMessageDialog(null, "Datos incorrectos o no registrado");
            }
        }
    }

    @FXML
    void EventRegistro(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader ventana = new FXMLLoader(getClass().getResource("PantallaRegistro.fxml"));
            Parent root = ventana.load();
            PantallaRegistro pantallaRegistro = ventana.getController();
            pantallaRegistro.recibirRol("Administrador");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception) {
            System.out.println();
        }

    }


}
