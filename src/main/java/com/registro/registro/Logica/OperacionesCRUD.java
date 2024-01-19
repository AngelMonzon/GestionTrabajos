package com.registro.registro.Logica;

import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;


public class OperacionesCRUD {

    public static void crearTablaClientes() {
        String crearTablaSQL = "CREATE TABLE IF NOT EXISTS clientes (" +
                "id_cliente INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre_cliente VARCHAR(255)," +
                "numero_telefono VARCHAR(15)," +
                "direccion VARCHAR(255))";

        try (Connection conexion = ConnectBD.obtenerConexion();
             Statement statement = conexion.createStatement()) {
            statement.execute(crearTablaSQL);
            System.out.println("Tabla clientes creada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void crearTablaTrabajos() {
        String crearTablaSQL = "CREATE TABLE IF NOT EXISTS trabajos (" +
                "id_venta INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_cliente INTEGER," +
                "nombre_trabajo VARCHAR(255)," +
                "fecha_instalacion DATE," +
                "fecha_mantenimiento DATE," +
                "costo DECIMAL(10,2)," +
                "comentarios VARCHAR(255)," +
                "FOREIGN KEY (id_cliente) REFERENCES clientes(id_usuario))";

        try (Connection conexion = ConnectBD.obtenerConexion();
             Statement statement = conexion.createStatement()) {
            statement.execute(crearTablaSQL);
            System.out.println("Tabla trabajos creada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Insertar cliente
    public static void insertarCliente(String cliente, String numero_telefono, String direccion) {
        String insertarUsuarioSQL = "INSERT INTO clientes (nombre_cliente, numero_telefono, direccion) " +
                "VALUES (?, ?, ?)";

        try (Connection conexion = ConnectBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(insertarUsuarioSQL)) {
            preparedStatement.setString(1, cliente);
            preparedStatement.setString(2, numero_telefono);
            preparedStatement.setString(3, direccion);
            preparedStatement.executeUpdate();
            System.out.println("trabajo insertado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Insertar Trabajo
    public static void insertarTrabajo(String nombre_trabajo, int cliente, LocalDate fecha_instalacion, LocalDate fecha_mantenimiento,
                                       double costo, String comentarios) {
        String insertarUsuarioSQL = "INSERT INTO trabajos (nombre_trabajo, id_cliente, fecha_instalacion, fecha_mantenimiento, costo, comentarios) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        System.out.println(1);
        try (Connection conexion = ConnectBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(insertarUsuarioSQL)) {
            preparedStatement.setString(1, nombre_trabajo);
            preparedStatement.setInt(2, cliente);
            preparedStatement.setDate(3, Date.valueOf(fecha_instalacion));
            preparedStatement.setDate(4, Date.valueOf(fecha_mantenimiento));
            preparedStatement.setDouble(5, costo);
            preparedStatement.setString(6, comentarios);
            preparedStatement.executeUpdate();
            System.out.println("cliente insertado exitosamente.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cliente no insertado");
        }
    }

    //Mostrar trabajos
    public static void mostrarTrabajos(ObservableList<Trabajo> data) {
        String seleccionartrabajosSQL = "SELECT *" +
                "FROM trabajos " +
                "JOIN clientes ON clientes.id_cliente = trabajos.id_cliente";

        try (Connection conexion = ConnectBD.obtenerConexion();
             Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(seleccionartrabajosSQL)) {

            System.out.println("Trabajos:");
            while (resultSet.next()) {
                int id_venta = resultSet.getInt("id_venta");
                int id_cliente = resultSet.getInt("id_cliente");
                String nombre_trabajo = resultSet.getString("nombre_trabajo");
                LocalDate fecha_instalacion = resultSet.getDate("fecha_instalacion").toLocalDate();
                LocalDate fecha_mantenimiento = resultSet.getDate("fecha_mantenimiento").toLocalDate();
                double costo = resultSet.getDouble("costo");
                String comentarios = resultSet.getString("comentarios");
                String nombre_cliente = resultSet.getString("nombre_cliente");
                String numero_telefono = resultSet.getString("numero_telefono");
                String direccion = resultSet.getString("direccion");

                data.add(new Trabajo(id_venta, id_cliente, nombre_trabajo, fecha_instalacion, fecha_mantenimiento,
                        costo, comentarios, nombre_cliente, numero_telefono, direccion));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Mostrar clientes
    public static void mostrarClientes() {
        String seleccionartrabajosSQL = "SELECT *" +
                "FROM clientes";

        try (Connection conexion = ConnectBD.obtenerConexion();
             Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(seleccionartrabajosSQL)) {

            System.out.println("Trabajos:");
            while (resultSet.next()) {
                String nombre_trabajo = resultSet.getString("nombre_trabajo");
                Date fecha_instalacion = resultSet.getDate("fecha_instalacion");
                Date fecha_mantenimiento = resultSet.getDate("fecha_mantenimiento");
                double costo = resultSet.getDouble("costo");
                String comentarios = resultSet.getString("comentarios");
                String direccion = resultSet.getString("direccion");
                System.out.println("Nombre de Trabajo: " + nombre_trabajo +
                        " Costo: " + costo +
                        " comentarios: " + comentarios +
                        " Direccion del cliente: " + direccion);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Actualizar Trabajo
    public static void actualizarTrabajo(int id, String nombre_trabajo, Date fecha_instalacion, Date fecha_mantenimiento, double costo, String comentarios) {
        String actualizarTrabajoSQL = "UPDATE trabajos" +
                " SET nombre_trabajo = ?, " +
                "fecha_instalacion = ?, " +
                "fecha_mantenimiento = ?, " +
                "costo = ?, " +
                "comentarios = ? " +
                "WHERE id_venta = ?";

        try (Connection conexion = ConnectBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(actualizarTrabajoSQL)) {
            preparedStatement.setString(1, nombre_trabajo);
            preparedStatement.setDate(2, fecha_instalacion);
            preparedStatement.setDate(3, fecha_mantenimiento);
            preparedStatement.setDouble(4, costo);
            preparedStatement.setString(5, comentarios);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
            System.out.println("Trabajo actualizado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Actualizar cliente
    public static void actualizarCliente(int id, String nombre_cliente, String numero_telefono, String direccion) {
        String actualizarTrabajoSQL = "UPDATE clientes" +
                " SET nombre_cliente = ?, " +
                "numero_telefono = ?, " +
                "direccion = ? " +
                "WHERE id_cliente = ?";

        try (Connection conexion = ConnectBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(actualizarTrabajoSQL)) {
            preparedStatement.setString(1, nombre_cliente);
            preparedStatement.setString(2, numero_telefono);
            preparedStatement.setString(3, direccion);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            System.out.println("Cliente actualizado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Eliminar trabajo
    public static void eliminarTrabajo(int id) {
        String eliminarUsuarioSQL = "DELETE FROM trabajos WHERE id_venta = ?";

        try (Connection conexion = ConnectBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(eliminarUsuarioSQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("trabajo eliminado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Eliminar Cliente
    public static void eliminarCliente(int id) {
        String eliminarUsuarioSQL = "DELETE FROM clientes WHERE id_cliente = ?";

        try (Connection conexion = ConnectBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(eliminarUsuarioSQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("cliente eliminado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        crearTablaClientes();
        crearTablaTrabajos();
        insertarCliente("Adolfo", "8994478921", "Cuauhtli");
        insertarTrabajo("Instalacion Minisplit", 1,
                LocalDate.of(2022, 10, 25), LocalDate.of(2023, 4, 25),
                550, "Nada");
    }

}
