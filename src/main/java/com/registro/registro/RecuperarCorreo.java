package com.registro.registro;

import com.registro.registro.Notificaciones.EnviarCorreo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class RecuperarCorreo {
    public static final String FILE_PATH = "login_data.txt";

    public static int hora = 7;
    public static int minutos = 0;

    public static void cargar(){
        // Cargar datos de inicio de sesión almacenados (si existen)
        List<String> loginData;
        try {
            loginData = Files.readAllLines(Paths.get(FILE_PATH));
            if (loginData.size() == 3) {
                EnviarCorreo.destinatario = loginData.get(0);
                hora = Integer.parseInt(loginData.get(1));
                minutos = Integer.parseInt(loginData.get(2));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardar(String correo, int hora, int minutos) {
        // Guardar datos de inicio de sesión si son válidos
        List<String> lines = Arrays.asList(correo, Integer.toString(hora), Integer.toString(minutos));

        try {
            Files.write(Paths.get(FILE_PATH), lines);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }

}
