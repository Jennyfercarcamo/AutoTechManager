/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.conexion;

import java.sql.Connection;

/**
 *
 * @author Dell
 */
public class PruebaConexion {
     public static void main(String[] args) {

        Connection cn = ConexionDB.getConexion();

        if (cn != null) {
            System.out.println("AutoTech Manager conectado correctamente");
            ConexionDB.cerrarConexion();
        } else {
            System.out.println("Error al conectar con la base de datos");
        }
    }
    
}
