package com.registro.registro;

import com.registro.registro.BaseDeDatos.Trabajo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import org.controlsfx.control.table.TableFilter;

import java.net.URL;
import java.util.ResourceBundle;

import static com.registro.registro.NotificacionesController.dataVerMas;

public class VerMasController implements Initializable {

    @FXML
    private TableView<Trabajo> jobsTable;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jobsTable.setItems(dataVerMas);
        TableFilter<Trabajo> tableFilter = TableFilter.forTableView(jobsTable).apply();
    }
}
