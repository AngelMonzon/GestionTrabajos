package com.registro.registro;

import com.registro.registro.BaseDeDatos.Cliente;
import com.registro.registro.BaseDeDatos.OperacionesCRUD;
import com.registro.registro.BaseDeDatos.Trabajo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import org.controlsfx.control.table.TableFilter;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class BrrClienteController implements Initializable {

    @FXML
    private VBox clientsPane;

    @FXML
    private VBox jobsPane;

    //Variables para tabla de mostrar trabajos
    ObservableList<Cliente> dataClients = FXCollections.observableArrayList();

    //Variables para tabla de mostrar trabajos
    ObservableList<Trabajo> dataJobs = FXCollections.observableArrayList();

    //Variable para cliente seleccionado
    int id = 0;

    @FXML
    private TableView<Cliente> clientsTable;

    @FXML
    private TableView<Trabajo> jobsTable;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarClientes();
        System.out.println(dataClients);
        jobsPane.setVisible(false);
    }

    @FXML
    private void cambiarVentana() {


        if (clientsPane.isVisible()){
            try {
                this.id = clientsTable.getSelectionModel().getSelectedItems().get(0).getId_cliente();
            } catch (IndexOutOfBoundsException ignored){

            }

            if (this.id > 0){
                clientsPane.setVisible(false);
                jobsPane.setVisible(true);
                OperacionesCRUD.mostrarTrabajosCliente(this.id, dataJobs);
                jobsTable.getItems().addAll(dataJobs);
            } else {
                mostrarAlerta("Ningun cliente seleccionado", Alert.AlertType.INFORMATION);
            }
        } else if (jobsPane.isVisible()) {
            dataJobs.clear();
            jobsTable.getItems().clear();
            this.id = 0;

            jobsPane.setVisible(false);
            clientsPane.setVisible(true);
        }

    }

    //Borrar trabajos de cliente

    @FXML
    private void borrarTrabajosCliente(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Esta seguro?");

        Optional<ButtonType> result = alert.showAndWait();


        if (result.isPresent() && result.get() == ButtonType.OK){
            OperacionesCRUD.eliminarTrabajosCliente(this.id);
            OperacionesCRUD.eliminarCliente(this.id);
            this.id = 0;

            dataJobs.clear();
            jobsTable.getItems().clear();
            dataClients.clear();
            clientsTable.getItems().clear();

            OperacionesCRUD.mostrarClientes(dataClients);
            clientsTable.setItems(dataClients);

            jobsPane.setVisible(false);
            clientsPane.setVisible(true);
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
        this.dataClients.clear();
        OperacionesCRUD.mostrarClientes(this.dataClients);
        clientsTable.setItems(dataClients);
        TableFilter<Cliente> tableFilter = TableFilter.forTableView(clientsTable).apply();
    }

    private void cargarTrabajos(){
        this.dataJobs.clear();
        OperacionesCRUD.mostrarTrabajosCliente(id, this.dataJobs);
        jobsTable.setItems(dataJobs);
        TableFilter<Trabajo> tableFilter = TableFilter.forTableView(jobsTable).apply();
    }
}
