/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.modelo;

/**
 *
 * @author Dell
 */
public class AsignacionMecanico {
    
    private int idAsignacion;
    private int idOrden;
    private int idMecanico;
    private boolean esResponsable;

    public AsignacionMecanico() {
    }

    public int getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(int idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdMecanico() {
        return idMecanico;
    }

    public void setIdMecanico(int idMecanico) {
        this.idMecanico = idMecanico;
    }

    public boolean isEsResponsable() {
        return esResponsable;
    }

    public void setEsResponsable(boolean esResponsable) {
        this.esResponsable = esResponsable;
    }
}
    

