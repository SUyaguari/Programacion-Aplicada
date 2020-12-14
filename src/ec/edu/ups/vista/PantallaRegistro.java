package ec.edu.ups.vista;

import ec.edu.ups.controlador.ControladorPersona;
import ec.edu.ups.modelo.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class PantallaRegistro {

    ControladorPersona controladorPersona;
    String rol;

    public PantallaRegistro(){
        controladorPersona = controladorPersona.getInstance();
    }

    public void recibirRol(String rol){
        this.rol = rol;
        System.out.println(rol);
    }

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtNoombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtContraseña;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnCancelar;

    @FXML
    void EventCancelar(ActionEvent event) {
        Stage stage = (Stage) this.btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void EventRegistro(ActionEvent event) {
        String cedula = txtCedula.getText();
        String nombre = txtNoombre.getText();
        String apellido = txtApellido.getText();
        String correo = txtCorreo.getText();
        String contraseña = txtContraseña.getText();

        if(cedula.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || contraseña.isEmpty() || correo.isEmpty()){
            JOptionPane.showMessageDialog(null, "Llene todos los campos");
        }else{
            boolean cent = controladorPersona.create(new Persona(cedula, rol, nombre, apellido, correo, contraseña));
            if(cent){
                JOptionPane.showMessageDialog(null, rol+" registrado con exito");
            }else{
                JOptionPane.showMessageDialog(null, "No se a podido registrar");
            }
            Stage stage = (Stage) this.btnRegistrar.getScene().getWindow();
            stage.close();
        }
    }

}