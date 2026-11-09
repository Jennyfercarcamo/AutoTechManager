/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.servicio;

import com.mycompany.autotechmanager.modelo.Motor;


/**
 *
 * @author Dell
 */
public class CambioMotorService {
    
    
   
    public boolean validarMotorDisponible(Motor motor) {

        if (motor == null) {
            throw new IllegalArgumentException(
                    "Debe seleccionar un motor.");
        }

        if (motor.getNumeroSerie() == null
                || motor.getNumeroSerie().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El motor debe tener número de serie.");
        }

        if (motor.getEstado() == null
                || !motor.getEstado().equalsIgnoreCase("Disponible")) {
            throw new IllegalArgumentException(
                    "El motor no está disponible para instalación.");
        }

        if (motor.getPrecio() == null
                || motor.getPrecio().doubleValue() < 0) {
            throw new IllegalArgumentException(
                    "El precio del motor no es válido.");
        }

        return true;
    }
}
    

