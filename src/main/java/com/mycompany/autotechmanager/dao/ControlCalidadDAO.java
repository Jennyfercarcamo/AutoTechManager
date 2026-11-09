/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.dao;


import com.mycompany.autotechmanager.conexion.ConexionDB;
import com.mycompany.autotechmanager.modelo.ControlCalidad;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class ControlCalidadDAO {
      
    public boolean registrar(ControlCalidad control) {

        String sql = "INSERT INTO ControlesCalidad "
                + "(IdOrden, Descripcion, Aprobado, Observaciones, FechaControl) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, control.getIdOrden());
            ps.setString(2, control.getDescripcion());
            ps.setBoolean(3, control.isAprobado());
            ps.setString(4, control.getObservaciones());

            if (control.getFechaControl() == null) {
                ps.setTimestamp(5,
                        new Timestamp(System.currentTimeMillis()));
            } else {
                ps.setTimestamp(5, control.getFechaControl());
            }

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar control: " + e.getMessage());
            return false;
        }
    }

  
    public ArrayList<ControlCalidad> listarPorOrden(int idOrden) {

        ArrayList<ControlCalidad> lista = new ArrayList<>();

        String sql = "SELECT * FROM ControlesCalidad WHERE IdOrden = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idOrden);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    ControlCalidad control = new ControlCalidad();

                    control.setIdControl(rs.getInt("IdControl"));
                    control.setIdOrden(rs.getInt("IdOrden"));
                    control.setDescripcion(rs.getString("Descripcion"));
                    control.setAprobado(rs.getBoolean("Aprobado"));
                    control.setObservaciones(rs.getString("Observaciones"));
                    control.setFechaControl(
                            rs.getTimestamp("FechaControl"));

                    lista.add(control);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar controles: " + e.getMessage());
        }

        return lista;
    }
}
    

