package com.registro.registro;

import com.registro.registro.BaseDeDatos.OperacionesCRUD;
import com.registro.registro.Notificaciones.EnviarCorreo;
import com.registro.registro.Notificaciones.NotificarMantenimiento;
import com.registro.registro.Notificaciones.ProgramarNotificaciones;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static com.registro.registro.RecuperarCorreo.hora;
import static com.registro.registro.RecuperarCorreo.minutos;

public class Main extends Application {

    public static Stage primaryStage;
    public static Stage loginStage;

    //La aplicacion al iniciarse por primera vez abrira la ventana de login
    //si ya hay datos guardados entrara directamente a la ventana de inicio
    public void start(Stage stage) throws IOException {
        OperacionesCRUD.crearTablaTrabajos();
        OperacionesCRUD.crearTablaClientes();
        primaryStage = stage;
        loginStage = stage;

        RecuperarCorreo.cargar();

        if (Objects.equals(EnviarCorreo.destinatario, "")){
            cargarLogin(loginStage);
        } else {
            cargarInicio(stage);
        }


    }

    //Método para reiniciar la ventana
    public static void reiniciarVentana() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("layout-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void cargarInicio(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("layout-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 580);

        Image icon = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("image/icono.png")));
        primaryStage.getIcons().add(icon);


        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        Platform.runLater(() -> {
            NotificarMantenimiento.notificar(primaryStage, EnviarCorreo.destinatario);
            ProgramarNotificaciones programarNotificaciones = new ProgramarNotificaciones();
            programarNotificaciones.programar(hora, minutos);
        });
    }

    public static void cargarLogin(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 300);

        Image icon = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("image/icono.png")));
        stage.getIcons().add(icon);


        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void cerrarVentana(){
        primaryStage.close();
        loginStage.close();
    }


    public static void main(String[] args) {
        launch();
    }


}