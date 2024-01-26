package com.registro.registro;

import com.registro.registro.BaseDeDatos.OperacionesCRUD;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.controlsfx.control.IndexedCheckModel;
import org.controlsfx.control.SearchableComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.registro.registro.BrrClienteController.mostrarAlerta;

public class MdfClienteController implements Initializable {


    //Variables de formulario Modificar Cliente
    @FXML
    private Label txtNombre;

    @FXML
    private TextField txtNumTel;

    @FXML
    private TextField txtDireccion;

    private ArrayList<String> clientesLista = new ArrayList<String>();

    private ArrayList<String> datosCliente = new ArrayList<String>();

    @FXML
    private SearchableComboBox<String> trbCliente;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OperacionesCRUD.mostrarNombresClientes(clientesLista);
        trbCliente.getItems().addAll(clientesLista);
    }

    @FXML
    private void cargarCliente(){
        txtNombre.setText("");
        txtNumTel.clear();
        txtDireccion.clear();

        datosCliente.clear();

        int id = OperacionesCRUD.obtenerIdCliente(trbCliente.getValue());

        if (id > 0){
            OperacionesCRUD.obtenerDatosCliente(id, datosCliente);
            System.out.println(id);
            System.out.println(datosCliente);
            txtNombre.setText(datosCliente.get(0));
            txtNumTel.setText(datosCliente.get(1));
            txtDireccion.setText(datosCliente.get(2));
        } else {
            mostrarAlerta("Ningun cliente seleccionado", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void actualizarCliente(){

        if (!txtNumTel.getText().matches("^[0-9()+\\-\\s]*$")) {
            mostrarAlerta("Formato de número de teléfono no válido.", Alert.AlertType.INFORMATION);
        } else {
            if (OperacionesCRUD.obtenerIdCliente(txtNombre.getText()) > 0 && !(Objects.equals(txtNombre.getText(), ""))){
                OperacionesCRUD.actualizarCliente(OperacionesCRUD.obtenerIdCliente(txtNombre.getText()),
                        txtNombre.getText(), txtNumTel.getText(), txtDireccion.getText());
                trbCliente.setValue(null);
                txtNombre.setText("");
                txtNumTel.clear();
                txtDireccion.clear();

                mostrarAlerta("Cliente actualizado", Alert.AlertType.INFORMATION);
            } else {
                mostrarAlerta("Cliente no cargado", Alert.AlertType.INFORMATION);
            }


        }



    }
}
