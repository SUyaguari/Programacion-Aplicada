package ec.edu.ups.vista;

import ec.edu.ups.controlador.ControladorParquadero;
import ec.edu.ups.controlador.ControladorVehiculo;
import ec.edu.ups.modelo.Parqueadero;
import ec.edu.ups.modelo.Vehiculo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class PantallaRetirarVehiculo implements Initializable {

    ControladorVehiculo controladorVehiculo;
    ControladorParquadero controladorParquadero;
    Parqueadero antiguo;


    public PantallaRetirarVehiculo() {
        controladorVehiculo = controladorVehiculo.getInstance();
        controladorParquadero = controladorParquadero.getInstance();

    }

    @FXML
    private TextField txtBuscarPlaca;

    @FXML
    private Button btnBuscar;

    @FXML
    private TextField txtFechaIngreso;

    @FXML
    private TextField txtHoraSalida;

    @FXML
    private TextField txtCedula;

    @FXML
    private DatePicker fechaSalida;

    @FXML
    private TextField txtValor;

    @FXML
    private Button btnCalcular;

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtFechaSalida;

    @FXML
    void eventBuscar(ActionEvent event) {
        String placa = txtBuscarPlaca.getText();
        if (placa.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Llene el campo de busqueda");
        } else {
            Vehiculo vehiculo = controladorVehiculo.read(new Vehiculo(placa, "", null));
            Parqueadero parqueadero = controladorParquadero.read(new Parqueadero(0, 0, "", vehiculo, null, null, 0));
            if (parqueadero != null) {
                antiguo = parqueadero;


                System.out.println(antiguo.getTipo());


                txtFechaIngreso.setText(parqueadero.getIngreso() + "");
                txtCedula.setText(parqueadero.getVehiculo().getPersona().getCedula());
                txtHoraSalida.setEditable(true);
                fechaSalida.setEditable(true);
                if(parqueadero.getSalida()!=null){


                    Date fecha = parqueadero.getSalida();

                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(fecha);

                    fechaSalida.setValue(LocalDate.of(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH)));
                    txtHoraSalida.setText(calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND));

                }

            } else {
                JOptionPane.showMessageDialog(null, "Vehiculo no ingresado");
            }
        }
    }

    @FXML
    void eventCalcularValor(ActionEvent event) {
        String fIngreso = txtFechaIngreso.getText();
        String fSalida = fechaSalida.getValue()+" "+txtHoraSalida.getText().trim();

        if(fIngreso.isEmpty() || fSalida.isEmpty()){
            JOptionPane.showMessageDialog(null, "Llene el campo de fecha de salida");
        }else{
            DateFormat fechaHora =  new SimpleDateFormat("EEE MMM dd HH:mm:ss 'COT' yyyy", Locale.ENGLISH);
            DateFormat fechaHora2 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            Date f = null;
            Date f2 = null;
            try {
                f = fechaHora.parse(fIngreso);
                f2 = fechaHora2.parse(fSalida);
            } catch (ParseException e) {
                System.out.println("Error obtener la fecha");;
            }
            System.out.println(antiguo.getTipo());
            if(antiguo.getTipo().equals("Parqueado")){
                int añoIngreso = f.getYear()*525600;
                int mesIngreso = f.getMonth()*43800;
                int diaIngreso = f.getDay()*1440;
                int horaIngreso = f.getHours()*60;
                int minutoIngreso = f.getMinutes();

                int añoSalida = f2.getYear()*525600;
                int mesSalida = f2.getMonth()*43800;
                int diaSalida = f2.getDay()*1440;
                int horaSalida = f2.getHours()*60;
                int minutoSalida = f2.getMinutes();

                int totalIngreso= añoIngreso+mesIngreso+diaIngreso+horaIngreso+minutoIngreso;
                int totalSalida= añoSalida+mesSalida+diaSalida+horaSalida+minutoSalida;

                int totalTotal = totalSalida-totalIngreso;

                int fracciones = (int) totalTotal/10;

                fracciones++;

                double total = fracciones*0.25;

                txtValor.setText(total+"");
            }else if(antiguo.getTipo().equals("Reservado")){


                int añoIngreso = f.getYear()*365;
                int mesIngreso = f.getMonth()*30;
                int diaIngreso = f.getDay();

                int añoSalida = antiguo.getSalida().getYear()*365;
                int mesSalida = antiguo.getSalida().getMonth()*30;
                int diaSalida = antiguo.getSalida().getDay();

                int totalIngreso= añoIngreso+mesIngreso+diaIngreso;
                int totalSalida= añoSalida+mesSalida+diaSalida;

                int totalTotal = totalSalida-totalIngreso;

                int fracciones = (int) totalTotal;

                if(fracciones==0){

                    fracciones++;

                }


                double total = fracciones*3.15;

                int comparar = f2.compareTo(antiguo.getSalida());
                System.out.println(comparar);
                if(comparar >0){
                    double respañdp = total;
                    double descuento = total*(0.10);
                    total = total+descuento;
                    JOptionPane.showMessageDialog(null, "Usted a exedido la fecha de salida \n Se le cobrara una multa del 10% \n total: "+respañdp +"\n monto acumulado: "+total);
                }

                txtValor.setText(total+"");

            }else if (antiguo.getTipo().equals("Arrentamiento")){

                int año = antiguo.getSalida().getYear()-f.getYear();
                int mesIngreso = antiguo.getSalida().getMonth()-f.getMonth();
                int diaIngreso = antiguo.getSalida().getDay()-f.getDay();




                int totalTotal = mesIngreso*4 +año*52;

                int fracciones = (int) totalTotal/7;


                if(fracciones==0){

                    fracciones++;
                }


                double total = -1*fracciones*5.75;

                int comparar = f2.compareTo(antiguo.getSalida());
                System.out.println(comparar);
                if(comparar >0){
                    double respañdp = total;
                    double descuento = total*(0.10);
                    total = total+descuento;
                    JOptionPane.showMessageDialog(null, "Usted a exedido la fecha de salida \n Se le cobrara una multa del 10% \n total: "+respañdp +"\n monto acumulado: "+total);
                }

                txtValor.setText(total+"");

            }
        }
    }

    @FXML
    void eventCancelar(ActionEvent event) {
        Stage stage = (Stage) this.btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void eventRetirar(ActionEvent event) {
        String fSalida = fechaSalida.getValue()+" "+txtHoraSalida.getText().trim();
        String valor = txtValor.getText();
        if(fSalida.isEmpty() || valor.isEmpty()){
            JOptionPane.showMessageDialog(null, "Llene todos los campos");
        }else {
            DateFormat fechaHora =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            Date f = null;
            try {
                f = fechaHora.parse(fSalida);
            } catch (ParseException e) {
                System.out.println("Error obtener la fecha");;
            }
            boolean cent = controladorParquadero.update(antiguo, new Parqueadero(antiguo.getCodigo(), antiguo.getPuesto(), "Retirado", antiguo.getVehiculo(), antiguo.getIngreso(), f, Double.parseDouble(valor)));
            if(cent == true){
                JOptionPane.showMessageDialog(null, "Vehiculo retirado exitosamente");
                try {
                    comprebante(new Parqueadero(antiguo.getCodigo(), antiguo.getPuesto(), "Retirado", antiguo.getVehiculo(), antiguo.getIngreso(), f, Double.parseDouble(valor)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = (Stage) this.btnCancelar.getScene().getWindow();
                stage.close();
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtFechaSalida.setVisible(false);
    }

    public void comprebante(Parqueadero parqueadero) throws IOException {
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
