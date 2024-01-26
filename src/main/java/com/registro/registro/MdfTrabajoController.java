package com.registro.registro;

import com.registro.registro.BaseDeDatos.OperacionesCRUD;
import com.registro.registro.BaseDeDatos.Trabajo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import org.controlsfx.control.table.TableFilter;

import java.net.URL;
import java.util.ResourceBundle;

public class MdfTrabajoController implements Initializable {

    @FXML
    private VBox mostrarTrabajos;

    @FXML
    private VBox moficarTrabajo;

    //Variables para tabla de mostrar trabajos
    ObservableList<Trabajo> data = FXCollections.observableArrayList();

    @FXML
    private TableView<Trabajo> jobsTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moficarTrabajo.setVisible(false);

        OperacionesCRUD.mostrarTrabajos(this.data);
        jobsTable.setItems(data);
        TableFilter<Trabajo> tableFilter = TableFilter.forTableView(jobsTable).apply();
    }

    @FXML
    private void seleccionarTrabajo(){
        mostrarTrabajos.setVisible(false);
        moficarTrabajo.setVisible(true);
    }
}
