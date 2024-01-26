package com.registro.registro;

import com.registro.registro.BaseDeDatos.Cliente;
import com.registro.registro.BaseDeDatos.OperacionesCRUD;
import com.registro.registro.BaseDeDatos.Trabajo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import org.controlsfx.control.table.TableFilter;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public class BrrClienteController implements Initializable {

    //Variables para tabla de mostrar trabajos
    ObservableList<Cliente> data = FXCollections.observableArrayList();

    @FXML
    private TableView<Cliente> jobsTable;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarClientes();
        System.out.println(data);
    }

    @FXML
    private void borrarCliente() {
        try {
            int seleccion = jobsTable.getSelectionModel().getSelectedItems().get(0).getId_cliente();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("¿Estás seguro de borrar el cliente seleccionado?");

            // Configurar el modality para bloquear la ventana principal mientras se muestra la alerta
            alert.initModality(Modality.APPLICATION_MODAL);

            // Mostrar la ventana de confirmación y esperar a que el usuario responda
            alert.showAndWait().ifPresent(response -> {
                if (response == javafx.scene.control.ButtonType.OK) {
                    OperacionesCRUD.eliminarCliente(seleccion);
                }
            });


            cargarClientes();
        } catch (IndexOutOfBoundsException e){
            mostrarAlerta("Ningun cliente seleccionado." , Alert.AlertType.INFORMATION);
        }

    }



    public static void mostrarAlerta(String mensaje, Alert.AlertType tipoAlerta) {
        Alert alert = new Alert(tipoAlerta);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);



        // Mostrar la ventana de alerta y esperar a que el usuario la cierre
        alert.showAndWait();
    }

    private void cargarClientes(){
        this.data.clear();
        OperacionesCRUD.mostrarClientes(this.data);
        jobsTable.setItems(data);
        TableFilter<Cliente> tableFilter = TableFilter.forTableView(jobsTable).apply();
    }
}
