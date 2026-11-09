/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.dao;

import com.mycompany.autotechmanager.conexion.ConexionDB;
import com.mycompany.autotechmanager.modelo.ActividadTrabajo;
import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author Dell
 */
public class ActividadTrabajoDAO {
    
       public boolean registrar(ActividadTrabajo actividad) {

        String sql = "INSERT INTO ActividadesTrabajo "
                + "(IdOrden, IdMecanico, Descripcion, FechaInicio, FechaFin, Estado) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, actividad.getIdOrden());
            ps.setInt(2, actividad.getIdMecanico());
            ps.setString(3, actividad.getDescripcion());

            if (actividad.getFechaInicio() != null) {
                ps.setTimestamp(4, actividad.getFechaInicio());
            } else {
                ps.setNull(4, Types.TIMESTAMP);
            }

            if (actividad.getFechaFin() != null) {
                ps.setTimestamp(5, actividad.getFechaFin());
            } else {
                ps.setNull(5, Types.TIMESTAMP);
            }

            ps.setString(6, actividad.getEstado());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar actividad: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<ActividadTrabajo> listarPorOrden(int idOrden) {

        ArrayList<ActividadTrabajo> lista = new ArrayList<>();

        String sql = "SELECT * FROM ActividadesTrabajo WHERE IdOrden = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idOrden);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ActividadTrabajo actividad = new ActividadTrabajo();

                actividad.setIdActividad(rs.getInt("IdActividad"));
                actividad.setIdOrden(rs.getInt("IdOrden"));
                actividad.setIdMecanico(rs.getInt("IdMecanico"));
                actividad.setDescripcion(rs.getString("Descripcion"));
                actividad.setFechaInicio(rs.getTimestamp("FechaInicio"));
                actividad.setFechaFin(rs.getTimestamp("FechaFin"));
                actividad.setEstado(rs.getString("Estado"));

                lista.add(actividad);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar actividades: " + e.getMessage());
        }

        return lista;
    }

    public boolean actualizarEstado(int idActividad, String estado) {

        String sql = "UPDATE ActividadesTrabajo SET Estado = ? WHERE IdActividad = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estado);
            ps.setInt(2, idActividad);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar actividad: " + e.getMessage());
            return false;
        }
    }
}
    
