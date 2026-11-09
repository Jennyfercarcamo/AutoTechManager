/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.dao;


import com.mycompany.autotechmanager.conexion.ConexionDB;
import com.mycompany.autotechmanager.modelo.DetalleOrden;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class DetalleOrdenDAO {
    
    
    // AGREGAR SERVICIO A UNA ORDEN
    public boolean registrar(DetalleOrden detalle) {

        String sql = "INSERT INTO DetalleOrden "
                + "(IdOrden, IdServicio, Cantidad, CostoManoObra) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, detalle.getIdOrden());
            ps.setInt(2, detalle.getIdServicio());
            ps.setInt(3, detalle.getCantidad());
            ps.setBigDecimal(4, detalle.getCostoManoObra());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar detalle: " + e.getMessage());
            return false;
        }
    }

    // LISTAR SERVICIOS DE UNA ORDEN
    public ArrayList<DetalleOrden> listarPorOrden(int idOrden) {

        ArrayList<DetalleOrden> lista = new ArrayList<>();

        String sql = "SELECT * FROM DetalleOrden WHERE IdOrden = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idOrden);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    DetalleOrden detalle = new DetalleOrden();

                    detalle.setIdDetalle(rs.getInt("IdDetalle"));
                    detalle.setIdOrden(rs.getInt("IdOrden"));
                    detalle.setIdServicio(rs.getInt("IdServicio"));
                    detalle.setCantidad(rs.getInt("Cantidad"));
                    detalle.setCostoManoObra(
                            rs.getBigDecimal("CostoManoObra"));

                    lista.add(detalle);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar detalles: " + e.getMessage());
        }

        return lista;
    }

    // ELIMINAR DETALLE
    public boolean eliminar(int idDetalle) {

        String sql = "DELETE FROM DetalleOrden WHERE IdDetalle = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idDetalle);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar detalle: " + e.getMessage());
            return false;
        }
    }
}
    

