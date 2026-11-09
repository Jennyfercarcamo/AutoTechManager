/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.modelo;

import java.sql.Timestamp;

/**
 *
 * @author Dell
 */
public class CambioMotor {
    
      private int idCambio;
    private int idOrden;
    private int idMotorAnterior;
    private int idMotorNuevo;
    private Timestamp fechaCambio;
    private String destinoMotorAnterior;
    private String resultadoPruebas;
    private String garantia;

    public int getIdCambio() {
        return idCambio;
    }

    public void setIdCambio(int idCambio) {
        this.idCambio = idCambio;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdMotorAnterior() {
        return idMotorAnterior;
    }

    public void setIdMotorAnterior(int idMotorAnterior) {
        this.idMotorAnterior = idMotorAnterior;
    }

    public int getIdMotorNuevo() {
        return idMotorNuevo;
    }

    public void setIdMotorNuevo(int idMotorNuevo) {
        this.idMotorNuevo = idMotorNuevo;
    }

    public Timestamp getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Timestamp fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public String getDestinoMotorAnterior() {
        return destinoMotorAnterior;
    }

    public void setDestinoMotorAnterior(String destinoMotorAnterior) {
        this.destinoMotorAnterior = destinoMotorAnterior;
    }

    public String getResultadoPruebas() {
        return resultadoPruebas;
    }

    public void setResultadoPruebas(String resultadoPruebas) {
        this.resultadoPruebas = resultadoPruebas;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }
}
    
    
    
    
