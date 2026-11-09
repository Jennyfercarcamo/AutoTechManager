/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.dao;

import com.mycompany.autotechmanager.conexion.ConexionDB;
import com.mycompany.autotechmanager.modelo.AsignacionMecanico;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class AsignacionMecanicoDAO {
    
      public boolean registrar(AsignacionMecanico asignacion) {

        String sql = "INSERT INTO AsignacionMecanicos "
                + "(IdOrden, IdMecanico, EsResponsable) "
                + "VALUES (?, ?, ?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, asignacion.getIdOrden());
            ps.setInt(2, asignacion.getIdMecanico());
            ps.setBoolean(3, asignacion.isEsResponsable());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al asignar mecánico: " + e.getMessage());
            return false;
        }
    }

    
    public ArrayList<AsignacionMecanico> listarPorOrden(int idOrden) {

        ArrayList<AsignacionMecanico> lista = new ArrayList<>();

        String sql = "SELECT * FROM AsignacionMecanicos WHERE IdOrden = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idOrden);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    AsignacionMecanico asignacion =
                            new AsignacionMecanico();

                    asignacion.setIdAsignacion(
                            rs.getInt("IdAsignacion"));

                    asignacion.setIdOrden(
                            rs.getInt("IdOrden"));

                    asignacion.setIdMecanico(
                            rs.getInt("IdMecanico"));

                    asignacion.setEsResponsable(
                            rs.getBoolean("EsResponsable"));

                    lista.add(asignacion);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar mecánicos: " + e.getMessage());
        }

        return lista;
    }

    public boolean eliminar(int idAsignacion) {

        String sql = "DELETE FROM AsignacionMecanicos "
                + "WHERE IdAsignacion = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAsignacion);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar asignación: " + e.getMessage());
            return false;
        }
    }
}
    

