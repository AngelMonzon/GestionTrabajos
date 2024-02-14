package com.registro.registro.Notificaciones;

import com.registro.registro.Main;
import javafx.application.Platform;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ProgramarNotificaciones {

    public void programar(int horas, int minutos) {
        // Obtener la fecha y hora actual
        LocalDateTime ahora = LocalDateTime.now();

        // Obtener la fecha y hora para la hora indicada de hoy
        LocalDateTime fechaHoraProgramada = LocalDateTime.of(ahora.toLocalDate(), LocalTime.of(horas, minutos));

        // Calcular la duración hasta la próxima ejecución
        Duration duracionHastaProximaEjecucion;
        if (ahora.isBefore(fechaHoraProgramada)) {
            duracionHastaProximaEjecucion = Duration.between(ahora, fechaHoraProgramada);
        } else {
            // Si ya pasó la hora programada para hoy, calcular la duración hasta la hora programada de mañana
            duracionHastaProximaEjecucion = Duration.between(ahora, fechaHoraProgramada.plusDays(1));
        }

        // Crear un ScheduledExecutorService con un solo hilo
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        // Programar la tarea para ejecutarse con la duración calculada
        scheduler.scheduleAtFixedRate(() -> {
            // Ejecutar la lógica de notificación en el hilo de la aplicación de JavaFX
            Platform.runLater(() -> {
                try {

                    Main.reiniciarVentana();
                    System.out.println("Notificar");
                } catch (IOException e) {
                    // Manejar la excepción de manera adecuada
                    e.printStackTrace();
                }
            });
        }, duracionHastaProximaEjecucion.getSeconds(), 24 * 60 * 60, TimeUnit.SECONDS);

        // 24 * 60 * 60 representa un día en segundos, asegurándonos de que se repita diariamente.

        System.out.println("Notificaciones programadas para las " + horas + " horas con " + minutos + " minutos" +
                " de todos los días.");
    }

    public static void main(String[] args) {
        new ProgramarNotificaciones().programar(19, 57);
    }
}
