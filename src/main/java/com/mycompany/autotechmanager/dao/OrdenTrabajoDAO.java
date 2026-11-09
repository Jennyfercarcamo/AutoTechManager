/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.dao;

import com.mycompany.autotechmanager.conexion.ConexionDB;
import com.mycompany.autotechmanager.modelo.OrdenTrabajo;

import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author Dell
 */
public class OrdenTrabajoDAO {
    
      
    public boolean registrar(OrdenTrabajo orden) {

        String sql = "INSERT INTO OrdenesTrabajo "
                + "(IdCliente, IdVehiculo, FechaIngreso, Kilometraje, "
                + "Sintomas, DiagnosticoPreliminar, Responsable, Estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orden.getIdCliente());
            ps.setInt(2, orden.getIdVehiculo());
            ps.setTimestamp(3, orden.getFechaIngreso());
            ps.setInt(4, orden.getKilometraje());
            ps.setString(5, orden.getSintomas());
            ps.setString(6, orden.getDiagnosticoPreliminar());

            if (orden.getResponsable() == null) {
                ps.setNull(7, Types.INTEGER);
            } else {
                ps.setInt(7, orden.getResponsable());
            }

            ps.setString(8, orden.getEstado());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar orden: " + e.getMessage());
            return false;
        }
    }

  
    public ArrayList<OrdenTrabajo> listar() {

        ArrayList<OrdenTrabajo> lista = new ArrayList<>();

        String sql = "SELECT * FROM OrdenesTrabajo ORDER BY IdOrden DESC";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                OrdenTrabajo orden = new OrdenTrabajo();

                orden.setIdOrden(rs.getInt("IdOrden"));
                orden.setIdCliente(rs.getInt("IdCliente"));
                orden.setIdVehiculo(rs.getInt("IdVehiculo"));
                orden.setFechaIngreso(rs.getTimestamp("FechaIngreso"));
                orden.setKilometraje(rs.getInt("Kilometraje"));
                orden.setSintomas(rs.getString("Sintomas"));
                orden.setDiagnosticoPreliminar(
                        rs.getString("DiagnosticoPreliminar"));

                int responsable = rs.getInt("Responsable");

                if (rs.wasNull()) {
                    orden.setResponsable(null);
                } else {
                    orden.setResponsable(responsable);
                }

                orden.setEstado(rs.getString("Estado"));

                lista.add(orden);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar órdenes: " + e.getMessage());
        }

        return lista;
    }

   
    public boolean actualizarEstado(int idOrden, String estado) {

        String sql = "UPDATE OrdenesTrabajo SET Estado = ? "
                + "WHERE IdOrden = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estado);
            ps.setInt(2, idOrden);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar estado: " + e.getMessage());
            return false;
        }
    }
    
    public ArrayList<OrdenTrabajo> listarPorVehiculo(int idVehiculo) {

    ArrayList<OrdenTrabajo> lista = new ArrayList<>();

    String sql = "SELECT * FROM OrdenesTrabajo "
            + "WHERE IdVehiculo = ? ORDER BY FechaIngreso DESC";

    try (Connection con = ConexionDB.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idVehiculo);

        try (ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                OrdenTrabajo orden = new OrdenTrabajo();

                orden.setIdOrden(rs.getInt("IdOrden"));
                orden.setIdCliente(rs.getInt("IdCliente"));
                orden.setIdVehiculo(rs.getInt("IdVehiculo"));
                orden.setFechaIngreso(rs.getTimestamp("FechaIngreso"));
                orden.setKilometraje(rs.getInt("Kilometraje"));
                orden.setSintomas(rs.getString("Sintomas"));
                orden.setDiagnosticoPreliminar(
                        rs.getString("DiagnosticoPreliminar"));

                int responsable = rs.getInt("Responsable");

                if (rs.wasNull()) {
                    orden.setResponsable(null);
                } else {
                    orden.setResponsable(responsable);
                }

                orden.setEstado(rs.getString("Estado"));

                lista.add(orden);
            }

        }

    } catch (SQLException e) {
        System.out.println("Error al consultar historial: " + e.getMessage());
    }

    return lista;
  }
    
    public OrdenTrabajo buscarPorId(int idOrden) {

    OrdenTrabajo orden = null;

    String sql = "SELECT * FROM OrdenesTrabajo WHERE IdOrden = ?";

    try (Connection con = ConexionDB.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idOrden);

        try (ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {

                orden = new OrdenTrabajo();

                orden.setIdOrden(rs.getInt("IdOrden"));
                orden.setIdCliente(rs.getInt("IdCliente"));
                orden.setIdVehiculo(rs.getInt("IdVehiculo"));
                orden.setFechaIngreso(rs.getTimestamp("FechaIngreso"));
                orden.setKilometraje(rs.getInt("Kilometraje"));
                orden.setSintomas(rs.getString("Sintomas"));
                orden.setDiagnosticoPreliminar(
                        rs.getString("DiagnosticoPreliminar"));

                int responsable = rs.getInt("Responsable");

                if (rs.wasNull()) {
                    orden.setResponsable(null);
                } else {
                    orden.setResponsable(responsable);
                }

                orden.setEstado(rs.getString("Estado"));
            }

        }

    } catch (SQLException e) {
        System.out.println("Error al buscar orden: " + e.getMessage());
    }

    return orden;
 }

public boolean actualizar(OrdenTrabajo orden) {

    String sql = "UPDATE OrdenesTrabajo SET "
            + "IdCliente = ?, IdVehiculo = ?, FechaIngreso = ?, "
            + "Kilometraje = ?, Sintomas = ?, DiagnosticoPreliminar = ?, "
            + "Responsable = ?, Estado = ? "
            + "WHERE IdOrden = ?";

    try (Connection con = ConexionDB.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, orden.getIdCliente());
        ps.setInt(2, orden.getIdVehiculo());
        ps.setTimestamp(3, orden.getFechaIngreso());
        ps.setInt(4, orden.getKilometraje());
        ps.setString(5, orden.getSintomas());
        ps.setString(6, orden.getDiagnosticoPreliminar());

        if (orden.getResponsable() == null) {
            ps.setNull(7, Types.INTEGER);
        } else {
            ps.setInt(7, orden.getResponsable());
        }

        ps.setString(8, orden.getEstado());
        ps.setInt(9, orden.getIdOrden());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error al actualizar orden: " + e.getMessage());
        return false;
    }
}
}

    

