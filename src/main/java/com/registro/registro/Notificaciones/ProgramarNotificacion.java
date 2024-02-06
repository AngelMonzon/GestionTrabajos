package com.registro.registro.Notificaciones;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class ProgramarNotificacion{

    public static void programar(){
        // Crear un servicio programado (ScheduledExecutorService)
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Obtener la hora actual
        long currentTime = System.currentTimeMillis();

        // Calcular el tiempo hasta las 7:00 AM del día siguiente
        long timeUntilNextExecution = calcularTiempoHastaSieteAm(currentTime);

        // Programar la tarea para que se ejecute todos los días a las 7:00 AM
        scheduler.scheduleAtFixedRate(() -> {
            // Lógica de la función que deseas ejecutar
            System.out.println("La función se está ejecutando a las 7:00 AM todos los días.");
        }, timeUntilNextExecution, 24 * 60 * 60 * 1000, TimeUnit.MILLISECONDS);
    }

    private static long calcularTiempoHastaSieteAm(long currentTime) {
        // Calcular el tiempo hasta las 7:00 AM del día siguiente
        // Suponemos que la aplicación se inicia antes de las 7:00 AM
        long millisEnUnDia = 24 * 60 * 60 * 1000;
        long tiempoHastaSieteAm = millisEnUnDia - (currentTime % millisEnUnDia) + obtenerTiempoEnMillis(20, 33, 0);
        return tiempoHastaSieteAm;
    }

    private static long obtenerTiempoEnMillis(int horas, int minutos, int segundos) {
        // Obtener el tiempo en milisegundos para la hora proporcionada
        return horas * 60 * 60 * 1000 + minutos * 60 * 1000 + segundos * 1000;
    }


}
