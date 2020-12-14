package ec.edu.ups.vista;

import ec.edu.ups.controlador.ControladorParquadero;
import ec.edu.ups.controlador.FabricaDeVehiculos;
import ec.edu.ups.modelo.Parqueadero;
import ec.edu.ups.modelo.VehiculosParqueadero;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.net.URL;

import java.util.List;

import java.util.ResourceBundle;

public class PantallaParqueadero implements Initializable {

    ControladorParquadero controladorParquadero;

    public PantallaParqueadero() {
        this.controladorParquadero = controladorParquadero.getInstance();
    }

    @FXML
    private GridPane gridParqueadero;

    @FXML
    private Pane panleGrid;

    @FXML
    private Button btnRegresar;

    @FXML
    void eventRegresar(ActionEvent event) {
        Stage stage = (Stage) this.btnRegresar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //crearObjetosParqueadero();

        //crearParqueadero();
        noseQUeHagoConMiVida();
    }


    public void noseQUeHagoConMiVida(){
        List<Parqueadero> lista = controladorParquadero.getLista();
        gridParqueadero = new GridPane();

        Label label;
        int cont = 1;

        for(int j = 0; j<5; j++){
            for (int i = 0; i<10; i++){
                VehiculosParqueadero holi = null;
                boolean cent = false;
                for (Parqueadero objeto :lista) {
                    //System.out.println("No hay no existe");
                    if(objeto.getPuesto()==cont){
                        holi =FabricaDeVehiculos.crearVehiculos(objeto.getPuesto(), objeto.getTipo());
                        //System.out.println(holi);
                        cent = true;
                    }
                }
                if(cent==false){
                    //System.out.println("Cree");
                    holi = FabricaDeVehiculos.crearVehiculos(cont, "Vacio");
                    //System.out.println(holi);
                }
                label = new Label();
                label.setText(""+cont+"");
                label.setGraphic(new ImageView(holi.getImage()));
                label.setTextAlignment(TextAlignment.LEFT);
                label.setPrefSize(60,50.6);
                label.setFont(new Font("Arial Rounded MT Bold", 12));
                GridPane.setConstraints(label, i, j);
                gridParqueadero.getChildren().add(label);
                cont++;
            }
        }
        gridParqueadero.setGridLinesVisible(true);
        panleGrid.getChildren().addAll(gridParqueadero);
    }

}
