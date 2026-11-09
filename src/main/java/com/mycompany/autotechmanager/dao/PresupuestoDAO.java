/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.dao;

import com.mycompany.autotechmanager.conexion.ConexionDB;
import com.mycompany.autotechmanager.modelo.Presupuesto;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class PresupuestoDAO {
    
    
    // REGISTRAR PRESUPUESTO
    public boolean registrar(Presupuesto p) {

        String sql = "INSERT INTO Presupuestos "
                + "(IdOrden, ManoObra, Repuestos, Descuento, Impuesto, "
                + "Total, Aprobado, FechaPresupuesto) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, p.getIdOrden());
            ps.setBigDecimal(2, p.getManoObra());
            ps.setBigDecimal(3, p.getRepuestos());
            ps.setBigDecimal(4, p.getDescuento());
            ps.setBigDecimal(5, p.getImpuesto());
            ps.setBigDecimal(6, p.getTotal());
            ps.setBoolean(7, p.isAprobado());

            if (p.getFechaPresupuesto() == null) {
                ps.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            } else {
                ps.setTimestamp(8, p.getFechaPresupuesto());
            }

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar presupuesto: " + e.getMessage());
            return false;
        }
    }

    // LISTAR PRESUPUESTOS POR ORDEN
    public ArrayList<Presupuesto> listarPorOrden(int idOrden) {

        ArrayList<Presupuesto> lista = new ArrayList<>();

        String sql = "SELECT * FROM Presupuestos WHERE IdOrden = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idOrden);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Presupuesto p = new Presupuesto();

                    p.setIdPresupuesto(rs.getInt("IdPresupuesto"));
                    p.setIdOrden(rs.getInt("IdOrden"));
                    p.setManoObra(rs.getBigDecimal("ManoObra"));
                    p.setRepuestos(rs.getBigDecimal("Repuestos"));
                    p.setDescuento(rs.getBigDecimal("Descuento"));
                    p.setImpuesto(rs.getBigDecimal("Impuesto"));
                    p.setTotal(rs.getBigDecimal("Total"));
                    p.setAprobado(rs.getBoolean("Aprobado"));
                    p.setFechaPresupuesto(
                            rs.getTimestamp("FechaPresupuesto"));

                    lista.add(p);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar presupuestos: " + e.getMessage());
        }

        return lista;
    }

    // APROBAR O RECHAZAR PRESUPUESTO
    public boolean cambiarEstado(int idPresupuesto, boolean aprobado) {

        String sql = "UPDATE Presupuestos SET Aprobado = ? "
                + "WHERE IdPresupuesto = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBoolean(1, aprobado);
            ps.setInt(2, idPresupuesto);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar presupuesto: " + e.getMessage());
            return false;
        }
    }
}

