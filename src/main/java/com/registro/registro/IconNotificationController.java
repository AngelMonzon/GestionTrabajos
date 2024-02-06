package com.registro.registro;

import com.registro.registro.BaseDeDatos.Trabajo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static com.registro.registro.MostrarController.dataGlobal;

public class IconNotificationController implements Initializable {
    @FXML
    private StackPane notificationPane;
    @FXML
    private Label lblCount;

    @FXML
    private void mostrarPopOver() {
        try {
            // Cargar el contenido del FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("notificaciones.fxml"));
            VBox fxmlContent = fxmlLoader.load();

            // Configurar el PopOver con el contenido FXML cargado
            PopOver popOver = new PopOver(fxmlContent);

            // Configurar opciones del PopOver si es necesario
            // popOver.setArrowLocation(PopOver.ArrowLocation.TOP_LEFT);

            // Mostrar el PopOver cerca del notificationPane
            popOver.show(notificationPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(() -> {
            int count = 0;

            for (int i = 0;i <= 6;i++){
                for (Trabajo trabajo: dataGlobal){
                    if (trabajo.getFecha_mantenimientoSF().equals(LocalDate.now().plusDays(i))){
                        count++;
                    }
                }
            }

            lblCount.setText(String.valueOf(count));
        });
    }
}
