package com.registro.registro.BaseDeDatos;

public class Cliente {
    private int id_cliente;
    private String nombre_cliente;
    private String numero_telefono;
    private String direccion;

    public Cliente(int id_cliente, String nombre_cliente, String numero_telefono, String direccion){
        this.id_cliente = id_cliente;
        this.nombre_cliente = nombre_cliente;
        this.numero_telefono = numero_telefono;
        this.direccion = direccion;
    }




    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getNumero_telefono() {
        return numero_telefono;
    }

    public void setNumero_telefono(String numero_telefono) {
        this.numero_telefono = numero_telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
