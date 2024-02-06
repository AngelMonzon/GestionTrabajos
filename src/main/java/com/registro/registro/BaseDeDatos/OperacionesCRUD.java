package com.registro.registro.BaseDeDatos;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.registro.registro.BrrClienteController.mostrarAlerta;


public class OperacionesCRUD {

    public static void crearTablaClientes() {
        String crearTablaSQL = "CREATE TABLE IF NOT EXISTS clientes (" +
                "id_cliente INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre_cliente TEXT UNIQUE," +
                "numero_telefono TEXT," +
                "direccion TEXT)";

        try (Connection conexion = ConnectBD.obtenerConexion();
             Statement statement = conexion.createStatement()) {
            statement.execute(crearTablaSQL);
            System.out.println("Tabla clientes creada exitosamente.");
            ConnectBD.cerrarConexion(conexion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void crearTablaTrabajos() {
        String crearTablaSQL = "CREATE TABLE IF NOT EXISTS trabajos (" +
                "id_venta INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_cliente INTEGER," +
                "nombre_trabajo TEXT," +
                "fecha_instalacion DATE," +
                "fecha_mantenimiento DATE," +
                "costo DECIMAL(10,2)," +
                "comentarios TEXT," +
                "FOREIGN KEY (id_cliente) REFERENCES clientes(id_usuario))";

        try (Connection conexion = ConnectBD.obtenerConexion();
             Statement statement = conexion.createStatement()) {
            statement.execute(crearTablaSQL);
            System.out.println("Tabla trabajos creada exitosamente.");
            ConnectBD.cerrarConexion(conexion);
        } catch (SQLException e) {
            mostrarAlerta("Error en la base de datos." , Alert.AlertType.ERROR);
        }
    }

    //Insertar cliente
    public static void insertarCliente(String cliente, String numero_telefono, String direccion) throws SQLException {
        String insertarUsuarioSQL = "INSERT INTO clientes (nombre_cliente, numero_telefono, direccion) " +
                "VALUES (?, ?, ?)";
        Connection conexion = ConnectBD.obtenerConexion();
        PreparedStatement preparedStatement = conexion.prepareStatement(insertarUsuarioSQL);

        preparedStatement.setString(1, cliente);
        preparedStatement.setString(2, numero_telefono);
        preparedStatement.setString(3, direccion);
        preparedStatement.executeUpdate();
        System.out.println("trabajo insertado exitosamente.");
        ConnectBD.cerrarConexion(conexion);

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
            ConnectBD.cerrarConexion(conexion);
        } catch (SQLException e) {
            mostrarAlerta("Error en la base de datos." , Alert.AlertType.ERROR);
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
            ConnectBD.cerrarConexion(conexion);


        } catch (SQLException e) {
            mostrarAlerta("Error en la base de datos." , Alert.AlertType.ERROR);
        }
    }

    //Mostrar trabajos de un cliente
    public static void mostrarTrabajosCliente(int id, ObservableList<Trabajo> data) {
        String seleccionartrabajosSQL = "SELECT * " +
                "FROM trabajos " +
                "JOIN clientes ON clientes.id_cliente = trabajos.id_cliente " +
                "WHERE trabajos.id_cliente = ?";

        try (Connection conexion = ConnectBD.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(seleccionartrabajosSQL)) {

            // Establecer el valor del parámetro
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()){
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
            }

            ConnectBD.cerrarConexion(conexion);


        } catch (SQLException e) {
            mostrarAlerta("Error en la base de datos." , Alert.AlertType.ERROR);
        }
    }



    //Mostrar clientes
    public static void mostrarClientes(ObservableList<Cliente> data) {
        String seleccionartrabajosSQL = "SELECT * " +
                "FROM clientes";

        try (Connection conexion = ConnectBD.obtenerConexion();
             Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(seleccionartrabajosSQL)) {

            while (resultSet.next()) {
                int id_cliente = resultSet.getInt("id_cliente");
                String nombre_cliente = resultSet.getString("nombre_cliente");
                String numero_telefono = resultSet.getString("numero_telefono");
                String direccion = resultSet.getString("direccion");

                data.add(new Cliente(id_cliente, nombre_cliente, numero_telefono, direccion));
            }
            ConnectBD.cerrarConexion(conexion);



        } catch (SQLException e) {
            mostrarAlerta("Error en la base de datos." , Alert.AlertType.ERROR);
        }
    }

    //Mostrar Nombres de clientes

    public static void mostrarNombresClientes(ArrayList<String> data){
        String seleccionartrabajosSQL = "SELECT nombre_cliente " +
                "FROM clientes";

        try (Connection conexion = ConnectBD.obtenerConexion();
             Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(seleccionartrabajosSQL)) {

            while (resultSet.next()) {

                String nombre_cliente = resultSet.getString("nombre_cliente");

                data.add(nombre_cliente);
            }
            ConnectBD.cerrarConexion(conexion);


        } catch (SQLException e) {
            mostrarAlerta("Error en la base de datos." , Alert.AlertType.ERROR);
        }
    }

    //Obtener datos de un cliente por id
    public static void obtenerDatosCliente(int id, ArrayList<String> data){
        String seleccionarTrabajosSQL = "SELECT * " +
                "FROM clientes " +
                "WHERE id_cliente = ?";

        try (Connection conexion = ConnectBD.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(seleccionarTrabajosSQL)) {

            // Establecer el valor del parámetro
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String nombre_cliente = resultSet.getString("nombre_cliente");
                    String numero_telefono = resultSet.getString("numero_telefono");
                    String domicilio = resultSet.getString("direccion");

                    data.add(nombre_cliente);
                    data.add(numero_telefono);
                    data.add(domicilio);
                }
            }
            ConnectBD.cerrarConexion(conexion);


        } catch (SQLException e) {
            mostrarAlerta("Error en la base de datos." , Alert.AlertType.ERROR);
        }
    }

    //Obtener datos de un trabajo por id
    public static void obtenerDatosTrabajo(int id, ObservableList<Trabajo> data){
        String seleccionarTrabajosSQL = "SELECT * " +
                "FROM trabajos " +
                "JOIN clientes ON clientes.id_cliente = trabajos.id_cliente " +
                "WHERE trabajos.id_venta = ?";

        try (Connection conexion = ConnectBD.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(seleccionarTrabajosSQL)) {

            // Establecer el valor del parámetro
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
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
            }
            ConnectBD.cerrarConexion(conexion);


        } catch (SQLException e) {
            mostrarAlerta("Error en la base de datos." , Alert.AlertType.ERROR);
        }
    }

    //Obtener id de cliente

    public static int obtenerIdCliente(String data) {
        String seleccionarTrabajosSQL = "SELECT id_cliente " +
                "FROM clientes " +
                "WHERE nombre_cliente = ?";

        int id_cliente = 0;

        try (Connection conexion = ConnectBD.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(seleccionarTrabajosSQL)) {

            // Establecer el valor del parámetro
            statement.setString(1, data);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    id_cliente = resultSet.getInt("id_cliente");
                    return id_cliente;
                }
            }
            ConnectBD.cerrarConexion(conexion);

        } catch (SQLException e) {
            mostrarAlerta("Error en la base de datos." , Alert.AlertType.ERROR);
        }

        return id_cliente;
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

            ConnectBD.cerrarConexion(conexion);
        } catch (SQLException e) {
            mostrarAlerta("Error en la base de datos." , Alert.AlertType.ERROR);
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
            ConnectBD.cerrarConexion(conexion);
            System.out.println("Cliente actualizado exitosamente.");

            ConnectBD.cerrarConexion(conexion);

        } catch (SQLException e) {
            mostrarAlerta("Error en la base de datos." , Alert.AlertType.ERROR);
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

            ConnectBD.cerrarConexion(conexion);
        } catch (SQLException e) {
            mostrarAlerta("Error en la base de datos." , Alert.AlertType.ERROR);
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

            ConnectBD.cerrarConexion(conexion);
        } catch (SQLException e) {
            mostrarAlerta("Error en la base de datos." , Alert.AlertType.ERROR);
        }
    }

    //Eliminar todos los trabajos de un cliente

    public static void eliminarTrabajosCliente(int id) {
        String eliminarTrbajosSQL = "DELETE FROM trabajos WHERE id_cliente = ?";

        try (Connection conexion = ConnectBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(eliminarTrbajosSQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("trabajos eliminados exitosamente.");

            ConnectBD.cerrarConexion(conexion);
        } catch (SQLException e) {
            mostrarAlerta("Error en la base de datos." , Alert.AlertType.ERROR);
        }
    }


    public static void main(String[] args) {

        System.out.println(obtenerIdCliente("aklsadndndaf"));

    }

}
