package com.registro.registro;

import com.registro.registro.BaseDeDatos.OperacionesCRUD;
import com.registro.registro.BaseDeDatos.Trabajo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import org.controlsfx.control.table.TableFilter;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.registro.registro.BrrClienteController.mostrarAlerta;

public class BrrTrabajoController implements Initializable {

    //Variables para tabla de mostrar trabajos
    ObservableList<Trabajo> data = FXCollections.observableArrayList();

    @FXML
    private TableView<Trabajo> jobsTable;

    int id = 0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OperacionesCRUD.mostrarTrabajos(this.data);
        jobsTable.setItems(data);
        TableFilter<Trabajo> tableFilter = TableFilter.forTableView(jobsTable).apply();
    }

    @FXML
    private void borrarTrabajo(){
        try {
            this.id = jobsTable.getSelectionModel().getSelectedItems().get(0).getId_venta();
            System.out.println(this.id);
        } catch (IndexOutOfBoundsException ignored){

        }
        if (id > 0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Esta seguro?");

            Optional<ButtonType> result = alert.showAndWait();


            if (result.isPresent() && result.get() == ButtonType.OK){
                System.out.println(this.id);
                OperacionesCRUD.eliminarTrabajo(this.id);
                this.id = 0;

                data.clear();
                jobsTable.getItems().clear();

                OperacionesCRUD.mostrarTrabajos(this.data);
                jobsTable.setItems(data);
            }

        } else {
            mostrarAlerta("Ningun Trabajo seleccionado", Alert.AlertType.INFORMATION);
        }


    }

}
