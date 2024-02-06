package com.registro.registro.BaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectBD {

    private static final String URL = "jdbc:sqlite:base_de_datos.db";

    //Conectar a base de datos
    public static Connection obtenerConexion() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
    }

    //cerrar conexion
    public static void cerrarConexion(Connection conexion) {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Hubo un error");
        }
    }

}
