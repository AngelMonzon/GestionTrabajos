package com.registro.registro;

import com.registro.registro.BaseDeDatos.OperacionesCRUD;
import com.registro.registro.Notificaciones.NotificarMantenimiento;
import com.registro.registro.Notificaciones.ProgramarNotificacion;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        OperacionesCRUD.crearTablaTrabajos();
        OperacionesCRUD.crearTablaClientes();


        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("layout-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 510);

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/icono.png")));
        primaryStage.getIcons().add(icon);


        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        Platform.runLater(() -> {
            System.out.println("Código ejecutado después de cargar");
            // Colocar aquí el bloque de código que quieres ejecutar después de cargar

            NotificarMantenimiento.notificar(primaryStage);
        });

        ProgramarNotificacion.programar();

    }

    //Método para reiniciar la ventana
    public static void reiniciarVentana() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("layout-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void cerrarVentana(){
        primaryStage.close();
    }


    public static void main(String[] args) {
        launch();
    }


}