package com.registro.registro;

import com.registro.registro.BaseDeDatos.OperacionesCRUD;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import org.controlsfx.tools.Borders;
import org.kordamp.bootstrapfx.BootstrapFX;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        OperacionesCRUD.crearTablaTrabajos();
        OperacionesCRUD.crearTablaClientes();


        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("layout-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 510);

        Image icon = new Image(getClass().getResourceAsStream("icono.png"));
        primaryStage.getIcons().add(icon);


        // Carga los estilos CSS de BootstrapFX
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();


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