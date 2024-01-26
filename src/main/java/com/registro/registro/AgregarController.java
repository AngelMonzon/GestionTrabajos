package com.registro.registro;

import com.registro.registro.BaseDeDatos.OperacionesCRUD;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;



import org.controlsfx.control.SearchableComboBox;

public class AgregarController implements Initializable {

    //Variables de formulario Agregar Cliente
    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNumTel;

    @FXML
    private TextField txtDireccion;

    @FXML
    private Label advertenciaLblClientes;

    @FXML
    private Label advertenciaLblTrabajos;


    //Variables de formulario Agregar Trabajo

    private ArrayList<String> clientesLista = new ArrayList<String>();

    @FXML
    private SearchableComboBox<String> trbCliente;

    @FXML
    private TextField trbNombre_trabajo;

    @FXML
    private DatePicker trbFecha_instalacion;

    @FXML
    private DatePicker trbFecha_mantenimiento;

    @FXML
    private TextField trbCosto;

    @FXML
    private TextArea trbComentarios;

    @FXML
    private Label labelSuccess;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OperacionesCRUD.mostrarNombresClientes(clientesLista);
        trbCliente.getItems().addAll(clientesLista);

        //Establecer fechas por defecto
        trbFecha_instalacion.setValue(LocalDate.now());
        trbFecha_mantenimiento.setValue(LocalDate.now().plusMonths(6));

        //No mostrar las labels de advertencia al inicio
        advertenciaLblTrabajos.setVisible(false);
        advertenciaLblClientes.setVisible(false);
        labelSuccess.setVisible(false);
    }

    public void GuardarCliente(){
        if (txtNombre.getText().length() > 3){
            try {
                // Tu código que puede lanzar la excepción
                OperacionesCRUD.insertarCliente(txtNombre.getText(), txtNumTel.getText(), txtDireccion.getText());
                txtNombre.clear();
                txtNumTel.clear();
                txtDireccion.clear();
                clientesLista.clear();
                OperacionesCRUD.mostrarNombresClientes(clientesLista);
                trbCliente.getItems().addAll(clientesLista);
                labelSuccess.setVisible(true);
                labelSuccess.setText("Cliente guardado exitosamente");
                labelTiempo(labelSuccess);
            } catch (SQLException e) {
                System.out.println(e);
                advertenciaLblClientes.setVisible(true);
                advertenciaLblClientes.setText("Este cliente ya existe");
                labelTiempo(advertenciaLblClientes);
            }

        } else {
            advertenciaLblClientes.setVisible(true);
            advertenciaLblClientes.setText("El campo Nombre no puede estar vacio.");
            labelTiempo(advertenciaLblClientes);
        }
    }

    //Funcion que agrega tiempo a la label de advertencia
    public void labelTiempo(Label label){
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), event -> {
            label.setVisible(false);
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.play();
    }

    public void guardarTrabajo(){
        if (!(OperacionesCRUD.obtenerIdCliente(trbCliente.getValue()) == 0)){
            if (trbNombre_trabajo.getText().length() > 0){
                try {
                    OperacionesCRUD.insertarTrabajo(trbNombre_trabajo.getText(),
                            OperacionesCRUD.obtenerIdCliente(trbCliente.getValue()),
                            trbFecha_instalacion.getValue(), trbFecha_mantenimiento.getValue(),
                            Double.parseDouble(trbCosto.getText()),
                            trbComentarios.getText());

                    trbCliente.setValue(null);
                    trbNombre_trabajo.clear();
                    trbFecha_instalacion.setValue(LocalDate.now());
                    trbFecha_mantenimiento.setValue(LocalDate.now());
                    trbCosto.setText("");
                    trbComentarios.setText("");

                    labelSuccess.setVisible(true);
                    labelSuccess.setText("Trabajo guardado exitosamente");
                    labelTiempo(labelSuccess);
                } catch (NumberFormatException e) {
                    advertenciaLblTrabajos.setVisible(true);
                    advertenciaLblTrabajos.setText("Precio no asignado");
                    labelTiempo(advertenciaLblTrabajos);
                } catch (NullPointerException e){
                    advertenciaLblTrabajos.setVisible(true);
                    advertenciaLblTrabajos.setText("Campo fecha no puede ir vacio");
                    labelTiempo(advertenciaLblTrabajos);
                }
            } else {
                advertenciaLblTrabajos.setVisible(true);
                advertenciaLblTrabajos.setText("Campo Nombre Trabajo no puede ir vacio");
                labelTiempo(advertenciaLblTrabajos);
            }

        } else{
            advertenciaLblTrabajos.setVisible(true);
            advertenciaLblTrabajos.setText("No a elegido un cliente");
            labelTiempo(advertenciaLblTrabajos);
        }

    }

    @FXML
    private void ventanaBrrCliente() {
        try {
            cargarVentanaExterna("ventanaBrrCliente.fxml", "Borrar Cliente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ventanaMdfCliente() {
        try {
            cargarVentanaExterna("ventanaMdfCliente.fxml", "Modificar Cliente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void ventanaMdfTrabajo() {
        try {
            cargarVentanaExterna("VentanaMdfTrabajo.fxml", "Modificar Trabajo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void ventanaBrrTrabajo() {
        try {
            cargarVentanaExterna("ventanaBrrTrabajo.fxml", "Borrar Trabajo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void cargarVentanaExterna(String fxml, String title) throws IOException{
        // Cargar el archivo FXML de la ventana externa
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();

        // Crear una nueva escena y asignarla a la ventana externa
        Scene scene = new Scene(root);
        Stage ventanaExterna = new Stage();
        ventanaExterna.initModality(Modality.APPLICATION_MODAL);

        Image icon = new Image(getClass().getResourceAsStream("icono.png"));
        ventanaExterna.getIcons().add(icon);
        ventanaExterna.setTitle(title);
        ventanaExterna.setScene(scene);

        // Mostrar la ventana externa
        ventanaExterna.showAndWait();
    }
}
