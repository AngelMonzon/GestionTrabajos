package com.registro.registro.Notificaciones;

import javafx.stage.Window;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class MostrarNotificacion {
    public static void mostrar(Window owner,String title, String message,  Duration duration) {
        Notifications.create()
                .owner(owner)
                .title(title)
                .text(message)
                .hideAfter(duration)
                .showInformation();
    }
}
