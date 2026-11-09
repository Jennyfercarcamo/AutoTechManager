/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.modelo;

/**
 *
 * @author Dell
 */
public class ServicioRepuestos {
    
    
    private int idServicio;
    private int idRepuesto;
    private int cantidad;

    public ServicioRepuestos() {
    }

    public ServicioRepuestos(int idServicio, int idRepuestos, int cantidad) {
        this.idServicio = idServicio;
        this.idRepuesto = idRepuestos;
        this.cantidad = cantidad;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getIdRepuesto() {
        return idRepuesto;
    }

    public void setIdRepuesto(int idRepuesto) {
        this.idRepuesto = idRepuesto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
    

