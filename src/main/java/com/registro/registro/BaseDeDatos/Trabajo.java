package com.registro.registro.BaseDeDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Trabajo {
    private int id_venta;
    private int id_cliente;
    private String nombre_trabajo;
    private String fecha_instalacion;
    private String fecha_mantenimiento;

    //Fecha sin formato
    private LocalDate fecha_instalacionSF;

    private LocalDate fecha_mantenimientoSF;

    private Double costo;
    private String comentarios;
    private String cliente;
    private String numero_telefono;
    private String direccion;

    public LocalDate getFecha_instalacionSF() {
        return fecha_instalacionSF;
    }

    public void setFecha_instalacionSF(LocalDate fecha_instalacionSF) {
        this.fecha_instalacionSF = fecha_instalacionSF;
    }

    public LocalDate getFecha_mantenimientoSF() {
        return fecha_mantenimientoSF;
    }

    public void setFecha_mantenimientoSF(LocalDate fecha_mantenimientoSF) {
        this.fecha_mantenimientoSF = fecha_mantenimientoSF;
    }

    public Trabajo(int id_venta, int id_cliente, String nombre_trabajo, LocalDate fecha_instalacion,
                   LocalDate fecha_mantenimiento, Double costo, String comentarios, String cliente,
                   String numero_telefono, String direccion){
        this.id_venta =  id_venta;
        this.id_cliente = id_cliente;
        this.nombre_trabajo = nombre_trabajo;
        this.fecha_instalacion = fecha_instalacion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.fecha_mantenimiento = fecha_mantenimiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        this.fecha_instalacionSF = fecha_instalacion;
        this.fecha_mantenimientoSF = fecha_mantenimiento;

        this.costo =  costo;
        this.comentarios =  comentarios;
        this.cliente = cliente;
        this.numero_telefono = numero_telefono;
        this.direccion = direccion;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    @Override
    public String toString() {
        return "Trabajo{" +
                "id_venta=" + id_venta +
                ", id_cliente=" + id_cliente +
                ", nombre_trabajo='" + nombre_trabajo + '\'' +
                ", fecha_instalacion='" + fecha_instalacion + '\'' +
                ", fecha_mantenimiento='" + fecha_mantenimiento + '\'' +
                ", fecha_instalacionSF=" + fecha_instalacionSF +
                ", fecha_mantenimientoSF=" + fecha_mantenimientoSF +
                ", costo=" + costo +
                ", comentarios='" + comentarios + '\'' +
                ", cliente='" + cliente + '\'' +
                ", numero_telefono='" + numero_telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }

    public String getNombre_trabajo() {
        return nombre_trabajo;
    }

    public void setNombre_trabajo(String nombre_trabajo) {
        this.nombre_trabajo = nombre_trabajo;
    }

    public String getFecha_instalacion() {
        return fecha_instalacion;
    }

    public void setFecha_instalacion(String fecha_instalacion) {
        this.fecha_instalacion = fecha_instalacion;
    }

    public String getFecha_mantenimiento() {
        return fecha_mantenimiento;
    }

    public void setFecha_mantenimiento(String fecha_mantenimiento) {
        this.fecha_mantenimiento = fecha_mantenimiento;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
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
