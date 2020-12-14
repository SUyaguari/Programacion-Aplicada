package ec.edu.ups.vista;

import ec.edu.ups.modelo.Parqueadero;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PantallaConprobante implements Initializable {

    Parqueadero parqueadero;

    public void llegarDato(Parqueadero parqueadero){
        this.parqueadero = parqueadero;
        txtfIngreso.setText(parqueadero.getIngreso()+"");
        txtfSalida.setText(parqueadero.getSalida()+"");
        txtValor.setText(parqueadero.getTotal()+"");
    }

    @FXML
    private Button btnAceptar;

    @FXML
    private TextField txtfIngreso;

    @FXML
    private TextField txtfSalida;

    @FXML
    private TextField txtValor;

    @FXML
    void eventCerrar(ActionEvent event) {
        Stage stage = (Stage) this.btnAceptar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
