/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.servicio;

import com.mycompany.autotechmanager.dao.OrdenTrabajoDAO;
import com.mycompany.autotechmanager.modelo.OrdenTrabajo;


/**
 *
 * @author Dell
 */
public class OrdenTrabajosService {
    
     private OrdenTrabajoDAO dao = new OrdenTrabajoDAO();

    public boolean cambiarEstado(int idOrden, String nuevoEstado) {

        OrdenTrabajo orden = dao.buscarPorId(idOrden);

        if (orden == null) {
            throw new IllegalArgumentException(
                    "La orden de trabajo no existe.");
        }

        String estadoActual = orden.getEstado();

        if (!estadoPermitido(estadoActual, nuevoEstado)) {
            throw new IllegalArgumentException(
                    "No se permite cambiar de " + estadoActual
                    + " a " + nuevoEstado);
        }

        return dao.actualizarEstado(idOrden, nuevoEstado);
    }

    private boolean estadoPermitido(String actual, String nuevo) {

        if (actual == null || nuevo == null) {
            return false;
        }

        actual = actual.trim().toLowerCase();
        nuevo = nuevo.trim().toLowerCase();

        switch (actual) {

            case "recibido":
                return nuevo.equals("diagnóstico")
                        || nuevo.equals("diagnostico")
                        || nuevo.equals("cancelado");

            case "diagnóstico":
            case "diagnostico":
                return nuevo.equals("pendiente de aprobación")
                        || nuevo.equals("pendiente de aprobacion")
                        || nuevo.equals("cancelado");

            case "pendiente de aprobación":
            case "pendiente de aprobacion":
                return nuevo.equals("reparación")
                        || nuevo.equals("reparacion")
                        || nuevo.equals("rechazado")
                        || nuevo.equals("cancelado");

            case "reparación":
            case "reparacion":
                return nuevo.equals("control de calidad")
                        || nuevo.equals("cancelado");

            case "control de calidad":
                return nuevo.equals("completado");

            case "completado":
                return nuevo.equals("entregado");

            default:
                return false;
        }
    }
}
    

