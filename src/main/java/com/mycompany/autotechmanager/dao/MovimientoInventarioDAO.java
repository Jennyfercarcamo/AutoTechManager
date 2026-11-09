/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.dao;

import com.mycompany.autotechmanager.conexion.ConexionDB;
import com.mycompany.autotechmanager.modelo.MovimientoInventario;
import java.sql.*;


/**
 *
 * @author Dell
 */
public class MovimientoInventarioDAO {
    
     public boolean registrar(MovimientoInventario movimiento) {

        if (movimiento.getCantidad() <= 0) {
            System.out.println("La cantidad debe ser mayor que cero.");
            return false;
        }

        String tipo = movimiento.getTipoMovimiento();

        if (!"Entrada".equalsIgnoreCase(tipo)
                && !"Salida".equalsIgnoreCase(tipo)) {
            System.out.println("Tipo inválido. Use Entrada o Salida.");
            return false;
        }

        String sqlEntrada = "UPDATE Repuestos "
                + "SET Stock = Stock + ? "
                + "WHERE IdRepuesto = ? AND Estado = 1";

        String sqlSalida = "UPDATE Repuestos "
                + "SET Stock = Stock - ? "
                + "WHERE IdRepuesto = ? "
                + "AND Estado = 1 AND Stock >= ?";

        String sqlMovimiento = "INSERT INTO MovimientosInventario "
                + "(IdRepuesto, TipoMovimiento, Cantidad, "
                + "FechaMovimiento, IdOrden, Observacion) "
                + "VALUES (?, ?, ?, GETDATE(), ?, ?)";

        Connection cn = null;

        try {
            cn = ConexionDB.getConexion();
            cn.setAutoCommit(false);

            int filas;

            if ("Entrada".equalsIgnoreCase(tipo)) {

                try (PreparedStatement ps =
                        cn.prepareStatement(sqlEntrada)) {

                    ps.setInt(1, movimiento.getCantidad());
                    ps.setInt(2, movimiento.getIdRepuesto());

                    filas = ps.executeUpdate();
                }

            } else {

                try (PreparedStatement ps =
                        cn.prepareStatement(sqlSalida)) {

                    ps.setInt(1, movimiento.getCantidad());
                    ps.setInt(2, movimiento.getIdRepuesto());
                    ps.setInt(3, movimiento.getCantidad());

                    filas = ps.executeUpdate();
                }
            }

            if (filas == 0) {
                cn.rollback();
                System.out.println(
                        "Repuesto inexistente, inactivo o stock insuficiente.");
                return false;
            }

            try (PreparedStatement ps =
                    cn.prepareStatement(sqlMovimiento)) {

                ps.setInt(1, movimiento.getIdRepuesto());
                ps.setString(2, tipo);
                ps.setInt(3, movimiento.getCantidad());

                if (movimiento.getIdOrden() > 0) {
                    ps.setInt(4, movimiento.getIdOrden());
                } else {
                    ps.setNull(4, Types.INTEGER);
                }

                ps.setString(5, movimiento.getObservacion());

                ps.executeUpdate();
            }

            cn.commit();
            return true;

        } catch (SQLException e) {

            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                System.out.println("Error al revertir: "
                        + ex.getMessage());
            }

            System.out.println("Error en movimiento de inventario: "
                    + e.getMessage());

            return false;

        } finally {

            try {
                if (cn != null) {
                    cn.setAutoCommit(true);
                    cn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión: "
                        + e.getMessage());
            }
        }
    }
}
    

