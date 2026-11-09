/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.modelo;

/**
 *
 * @author Dell
 */
public class Servicio {
    private int idServicio;
    private String nombre;
    private String codigo;
    private String descripcion;
    private int nivelComplejidad;
    private double costoManoObra;
    private double tiempoEstimado;

    public Servicio() {
    }

    public Servicio(int idServicio, String nombre, String descripcion,
            int nivelComplejidad, double costoManoObra,
            double tiempoEstimado) {

        this.idServicio = idServicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivelComplejidad = nivelComplejidad;
        this.costoManoObra = costoManoObra;
        this.tiempoEstimado = tiempoEstimado;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNivelComplejidad() {
        return nivelComplejidad;
    }

    public void setNivelComplejidad(int nivelComplejidad) {
        this.nivelComplejidad = nivelComplejidad;
    }

    public double getCostoManoObra() {
        return costoManoObra;
    }

    public void setCostoManoObra(double costoManoObra) {
        this.costoManoObra = costoManoObra;
    }

    public double getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(double tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }
    public String getCodigo() {
    return codigo;
    }

    public void setCodigo(String codigo) {
    this.codigo = codigo;
    }
    
}
