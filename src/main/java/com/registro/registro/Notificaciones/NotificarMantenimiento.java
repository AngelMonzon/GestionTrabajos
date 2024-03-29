package com.registro.registro.Notificaciones;

import com.registro.registro.BaseDeDatos.Trabajo;
import javafx.application.Platform;
import javafx.stage.Window;
import javafx.util.Duration;

import java.time.LocalDate;

import static com.registro.registro.MostrarController.dataGlobal;

public class NotificarMantenimiento {

    public static void notificar(Window owner, String destinatario){

        String mensaje = "";
        int count = 0;

        for(Trabajo trabajo: dataGlobal){
            if (trabajo.getFecha_mantenimientoSF().equals(LocalDate.now())){
                Platform.runLater(() -> {
                    MostrarNotificacion.mostrar(owner, "Mantenimiento", "Tiene un mantenimiento pendiente para hoy \n Para " +
                            trabajo.getCliente(), Duration.seconds(8));
                });
                mensaje = mensaje.concat("\n" + trabajo.getFecha_mantenimiento() + "\n" + trabajo.getCliente()
                        + "  " + trabajo.getNumero_telefono()+ "  " + trabajo.getDireccion() + "\n\n");
                count++;
            }
        }

        if (count == 1){
            EnviarCorreo.enviar("Tiene " + count + " trabajo/mantenimiento pendiente para hoy", mensaje, destinatario);
        }
        if (count > 1){
            EnviarCorreo.enviar("Tiene " + count + " trabajos/mantenimientos pendientes para hoy", mensaje, destinatario);
        }

        System.out.println(mensaje);
    }
}
