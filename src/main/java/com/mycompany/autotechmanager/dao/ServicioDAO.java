/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.dao;

import com.mycompany.autotechmanager.conexion.ConexionDB;
import com.mycompany.autotechmanager.modelo.Servicio;
import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author Dell
 */
public class ServicioDAO {
    public boolean registrar(Servicio servicio) {

        String sql = "INSERT INTO Servicios "
                + "(Codigo, Nombre, Descripcion, Nivel, "
                + "CostoManoObra, TiempoEstimadoHoras, Estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, 1)";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, servicio.getCodigo());
            ps.setString(2, servicio.getNombre());
            ps.setString(3, servicio.getDescripcion());
            ps.setInt(4, servicio.getNivelComplejidad());
            ps.setDouble(5, servicio.getCostoManoObra());
            ps.setDouble(6, servicio.getTiempoEstimado());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar servicio: "
                    + e.getMessage());
            return false;
        }
    }

    public ArrayList<Servicio> listar() {

        ArrayList<Servicio> lista = new ArrayList<>();

        String sql = "SELECT * FROM Servicios WHERE Estado = 1";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Servicio servicio = new Servicio();

                servicio.setIdServicio(rs.getInt("IdServicio"));
                servicio.setCodigo(rs.getString("Codigo"));
                servicio.setNombre(rs.getString("Nombre"));
                servicio.setDescripcion(rs.getString("Descripcion"));
                servicio.setNivelComplejidad(rs.getInt("Nivel"));
                servicio.setCostoManoObra(
                        rs.getDouble("CostoManoObra"));
                servicio.setTiempoEstimado(
                        rs.getDouble("TiempoEstimadoHoras"));

                lista.add(servicio);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar servicios: "
                    + e.getMessage());
        }

        return lista;
    }
    
    public ArrayList<Servicio> buscar(String texto) {

    ArrayList<Servicio> lista = new ArrayList<>();

    String sql = "SELECT * FROM Servicios "
            + "WHERE Estado = 1 "
            + "AND (Codigo LIKE ? OR Nombre LIKE ?)";

    try (Connection cn = ConexionDB.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setString(1, "%" + texto + "%");
        ps.setString(2, "%" + texto + "%");

        try (ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Servicio servicio = new Servicio();

                servicio.setIdServicio(rs.getInt("IdServicio"));
                servicio.setCodigo(rs.getString("Codigo"));
                servicio.setNombre(rs.getString("Nombre"));
                servicio.setDescripcion(rs.getString("Descripcion"));
                servicio.setNivelComplejidad(rs.getInt("Nivel"));
                servicio.setCostoManoObra(
                        rs.getDouble("CostoManoObra"));
                servicio.setTiempoEstimado(
                        rs.getDouble("TiempoEstimadoHoras"));

                lista.add(servicio);
            }

        }

    } catch (SQLException e) {
        System.out.println("Error al buscar servicios: "
                + e.getMessage());
    }

    return lista;
}
    
    public boolean actualizar(Servicio servicio, boolean activo) {

    String sql = "UPDATE Servicios SET "
            + "Codigo = ?, Nombre = ?, Descripcion = ?, Nivel = ?, "
            + "CostoManoObra = ?, TiempoEstimadoHoras = ?, Estado = ? "
            + "WHERE IdServicio = ?";

    try (Connection cn = ConexionDB.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setString(1, servicio.getCodigo());
        ps.setString(2, servicio.getNombre());
        ps.setString(3, servicio.getDescripcion());
        ps.setInt(4, servicio.getNivelComplejidad());
        ps.setDouble(5, servicio.getCostoManoObra());
        ps.setDouble(6, servicio.getTiempoEstimado());
        ps.setInt(7, activo ? 1 : 0);
        ps.setInt(8, servicio.getIdServicio());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error al actualizar servicio: " + e.getMessage());
        return false;
    }
}
    
    public boolean desactivar(int idServicio) {
    String sql = "UPDATE Servicios SET Estado = 0 WHERE IdServicio = ?";

    try (Connection con = ConexionDB.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idServicio);

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error al desactivar servicio: " + e.getMessage());
        return false;
    }
}
    
    
 
}
