package com.registro.registro;

import com.registro.registro.Logica.OperacionesCRUD;
import com.registro.registro.Logica.Trabajo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    ObservableList<Trabajo> data = FXCollections.observableArrayList();
    @FXML
    private TableView<Trabajo> jobsTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OperacionesCRUD.mostrarTrabajos(this.data);
        jobsTable.setItems(data);
    }

    public void imprimirEnTabla(){
        OperacionesCRUD.mostrarTrabajos(this.data);
        jobsTable.setItems(data);
    }
}