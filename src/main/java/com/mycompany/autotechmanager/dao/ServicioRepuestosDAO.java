/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.dao;

import com.mycompany.autotechmanager.conexion.ConexionDB;
import com.mycompany.autotechmanager.modelo.ServicioRepuestos;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class ServicioRepuestosDAO {
    
     // Registrar repuesto asociado a un servicio
    public boolean registrar(ServicioRepuestos sr) {

        String sql = "INSERT INTO ServiciosRepuestos "
                   + "(IdServicio, IdRepuesto, Cantidad) "
                   + "VALUES (?, ?, ?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, sr.getIdServicio());
            ps.setInt(2, sr.getIdRepuesto());
            ps.setInt(3, sr.getCantidad());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar servicio-repuesto: "
                    + e.getMessage());
            return false;
        }
    }

    // Listar repuestos asociados a un servicio
    public ArrayList<ServicioRepuestos> listarPorServicio(int idServicio) {

        ArrayList<ServicioRepuestos> lista = new ArrayList<>();

        String sql = "SELECT IdServicio, IdRepuesto, Cantidad "
                   + "FROM ServiciosRepuestos WHERE IdServicio = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idServicio);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ServicioRepuestos sr = new ServicioRepuestos();

                sr.setIdServicio(rs.getInt("IdServicio"));
                sr.setIdRepuesto(rs.getInt("IdRepuesto"));
                sr.setCantidad(rs.getInt("Cantidad"));

                lista.add(sr);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }

        return lista;
    }

    // Eliminar relación entre servicio y repuesto
    public boolean eliminar(int idServicio, int idRepuesto) {

        String sql = "DELETE FROM ServiciosRepuestos "
                   + "WHERE IdServicio = ? AND IdRepuesto = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idServicio);
            ps.setInt(2, idRepuesto);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }
}
    
