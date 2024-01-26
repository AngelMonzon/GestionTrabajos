package com.registro.registro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.registro.registro.Main.cerrarVentana;
import static com.registro.registro.Main.reiniciarVentana;


public class LayoutController implements Initializable {

    //Variables de menu
    @FXML
    private MenuItem reiniciar;

    @FXML
    private MenuItem salir;

    //Layouts
    @FXML
    private HBox menu;

    @FXML
    private StackPane content;

    @FXML
    private StackPane mostrarTrabajos;

    @FXML
    private StackPane agregarTrabajosClientes;

    //ToggleButtons para mostrar layout seleccionada
    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private ToggleButton mostrarBtn;

    @FXML
    private ToggleButton agregarBtn;

    private Toggle selectedToggle;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        agregarTrabajosClientes.setVisible(false);

        //Seleccionar un toggleButton por defecto
        toggleGroup.selectToggle(mostrarBtn);

        //Anadir un identificador a los toggleButtons
        mostrarBtn.setUserData("mostrar");
        agregarBtn.setUserData("agregar");

        //Extraer toggle sellecionado
        selectedToggle = toggleGroup.getSelectedToggle();


    }

    //Cambiar Layout
    public void cambiarLayout(ActionEvent event){
        // Obtener la opción seleccionada y acceder a sus propiedades
        selectedToggle = toggleGroup.getSelectedToggle();
        ToggleButton selectedButton = (ToggleButton) selectedToggle;

        try {
            // Acceder a las propiedades específicas del ToggleButton
            Object userData = selectedButton.getUserData();

            mostrarTrabajos.setVisible((userData == "mostrar"));
            System.out.println((userData == "mostrar"));
            agregarTrabajosClientes.setVisible((userData == "agregar"));


            System.out.println("Valor seleccionado: " + userData);
        } catch (RuntimeException e){
            System.out.println("");
        }
    }



    public void reiniciarVnt() throws IOException {
        reiniciarVentana();
    }

    public void cerrarVnt(){
        cerrarVentana();
    }




}