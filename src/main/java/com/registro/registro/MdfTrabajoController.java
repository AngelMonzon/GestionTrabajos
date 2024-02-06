package com.registro.registro;

import com.registro.registro.BaseDeDatos.OperacionesCRUD;
import com.registro.registro.BaseDeDatos.Trabajo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.controlsfx.control.table.TableFilter;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static com.registro.registro.BrrClienteController.mostrarAlerta;

public class MdfTrabajoController implements Initializable {

    @FXML
    private VBox mostrarTrabajos;

    @FXML
    private VBox moficarTrabajo;

    //Variables para tabla de mostrar trabajos
    ObservableList<Trabajo> data = FXCollections.observableArrayList();

    ObservableList<Trabajo> dataAModificar = FXCollections.observableArrayList();

    @FXML
    private TableView<Trabajo> jobsTable;


    //Variables para formulario de modificar Trabajo
    @FXML
    private Label cliente;

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

    int id = 0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moficarTrabajo.setVisible(false);

        OperacionesCRUD.mostrarTrabajos(this.data);
        jobsTable.setItems(data);
        TableFilter<Trabajo> tableFilter = TableFilter.forTableView(jobsTable).apply();
    }

    @FXML
    private void seleccionarTrabajo(){
        try {
            this.id = jobsTable.getSelectionModel().getSelectedItems().get(0).getId_venta();
            System.out.println(this.id);
        } catch (IndexOutOfBoundsException e){

        }
        if (this.id > 0){
            mostrarTrabajos.setVisible(false);
            moficarTrabajo.setVisible(true);

            dataAModificar.clear();
            OperacionesCRUD.obtenerDatosTrabajo(this.id, dataAModificar);
            System.out.println(dataAModificar.get(0).toString());

            cliente.setText(dataAModificar.get(0).getCliente());
            trbNombre_trabajo.setText(dataAModificar.get(0).getNombre_trabajo());
            trbFecha_instalacion.setValue(dataAModificar.get(0).getFecha_instalacionSF());
            trbFecha_mantenimiento.setValue(dataAModificar.get(0).getFecha_mantenimientoSF());
            trbCosto.setText(String.valueOf(dataAModificar.get(0).getCosto()));
            trbComentarios.setText(dataAModificar.get(0).getComentarios());

        } else {
            mostrarAlerta("Ningun trabajo seleccionado", Alert.AlertType.INFORMATION);
        }

    }

    @FXML
    private void volver(){
        mostrarTrabajos.setVisible(true);
        moficarTrabajo.setVisible(false);
    }

    @FXML
    private void actualizarTrabajo(){
        if (cliente.getText().length() > 0){
            try {
                OperacionesCRUD.actualizarTrabajo(this.id, trbNombre_trabajo.getText(), Date.valueOf(trbFecha_instalacion.getValue()),
                        Date.valueOf(trbFecha_mantenimiento.getValue()), Double.parseDouble(trbCosto.getText()), trbComentarios.getText());

                cliente.setText("");
                trbFecha_instalacion.setValue(LocalDate.now());
                trbFecha_mantenimiento.setValue(LocalDate.now().plusMonths(6));
                trbCosto.clear();
                trbComentarios.clear();

                mostrarAlerta("Cliente modificado", Alert.AlertType.INFORMATION);

                data.clear();
                OperacionesCRUD.mostrarTrabajos(data);
                jobsTable.setItems(data);
                dataAModificar.clear();

                mostrarTrabajos.setVisible(true);
                moficarTrabajo.setVisible(false);
            } catch (NullPointerException e){
                mostrarAlerta("El campo fecha do puede ir vacio", Alert.AlertType.INFORMATION);
            } catch (NumberFormatException e){
                mostrarAlerta("Precio no asignado", Alert.AlertType.INFORMATION);
            }
        } else {
            mostrarAlerta("El campo Nombre trabajo no debe estar vacio", Alert.AlertType.INFORMATION);
        }
    }
}
