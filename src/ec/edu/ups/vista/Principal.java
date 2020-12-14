package ec.edu.ups.vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("PantallaLogin.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("PantallaRetirarVehiculo.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("PantallaReservacion.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
