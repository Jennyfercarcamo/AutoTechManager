/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.modelo;


import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author Dell
 */
public class Presupuesto {
    
     private int idPresupuesto;
    private int idOrden;
    private BigDecimal manoObra;
    private BigDecimal repuestos;
    private BigDecimal descuento;
    private BigDecimal impuesto;
    private BigDecimal total;
    private boolean aprobado;
    private Timestamp fechaPresupuesto;

    public int getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(int idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public BigDecimal getManoObra() {
        return manoObra;
    }

    public void setManoObra(BigDecimal manoObra) {
        this.manoObra = manoObra;
    }

    public BigDecimal getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(BigDecimal repuestos) {
        this.repuestos = repuestos;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(BigDecimal impuesto) {
        this.impuesto = impuesto;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public Timestamp getFechaPresupuesto() {
        return fechaPresupuesto;
    }

    public void setFechaPresupuesto(Timestamp fechaPresupuesto) {
        this.fechaPresupuesto = fechaPresupuesto;
    }
}
    

