package com.registro.registro;

import com.registro.registro.Logica.OperacionesCRUD;
import com.registro.registro.Logica.Trabajo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.table.TableFilter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.registro.registro.Main.reiniciarVentana;


public class RegisterController implements Initializable {

    //Layouts
    @FXML
    private HBox menu;

    @FXML
    private StackPane content;

    @FXML
    private StackPane mostrarTrabajos;

    @FXML
    private StackPane agregarTrabajos;

    //ToggleButtons para mostrar layout seleccionada
    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private ToggleButton mostrarBtn;

    @FXML
    private ToggleButton agregarBtn;

    //Variables de formulario Agregar usuario
    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNumTel;

    @FXML
    private TextField txtDireccion;

    //Variables de formulario Agregar Trabajo

    @FXML
    private ComboBox cliente;

    @FXML
    private TextField nombre_trabajo;

    @FXML
    private DatePicker fecha_instalacion;

    @FXML
    private DatePicker fecha_mantenimiento;

    @FXML
    private TextField costo;

    @FXML
    private TextArea comentarios;



    ObservableList<Trabajo> data = FXCollections.observableArrayList();

    @FXML
    private TableView<Trabajo> jobsTable;

    private Toggle selectedToggle;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OperacionesCRUD.mostrarTrabajos(this.data);
        jobsTable.setItems(data);
        TableFilter<Trabajo> tableFilter = TableFilter.forTableView(jobsTable).apply();
        agregarTrabajos.setVisible(false);

        //Seleccionar un toggleButton por defecto
        toggleGroup.selectToggle(mostrarBtn);

        //Anadir un identificador a los toggleButtons
        mostrarBtn.setUserData("mostrar");
        agregarBtn.setUserData("agregar");

        //Extraer toggle sellecionado
        selectedToggle = toggleGroup.getSelectedToggle();
    }

    public void imprimirEnTabla(){
        OperacionesCRUD.mostrarTrabajos(this.data);
        jobsTable.setItems(data);
    }

    public void cambiarLayout(ActionEvent event){
        // Obtener la opción seleccionada y acceder a sus propiedades
        selectedToggle = toggleGroup.getSelectedToggle();
        ToggleButton selectedButton = (ToggleButton) selectedToggle;

       try {
           // Acceder a las propiedades específicas del ToggleButton
           Object userData = selectedButton.getUserData();

           mostrarTrabajos.setVisible((userData == "mostrar"));
           System.out.println((userData == "mostrar"));
           agregarTrabajos.setVisible((userData == "agregar"));


           System.out.println("Valor seleccionado: " + userData);
       } catch (RuntimeException e){
           System.out.println("");
       }
    }

    public void reiniciarVnt() throws IOException {
        reiniciarVentana();
    }




}