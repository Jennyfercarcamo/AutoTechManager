/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.dao;

import com.mycompany.autotechmanager.conexion.ConexionDB;
import com.mycompany.autotechmanager.modelo.CambioMotor;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class CambioMotorDAO {
    
      // REGISTRAR CAMBIO DE MOTOR
    public boolean registrar(CambioMotor cambio) {

        String sql = "INSERT INTO CambiosMotor "
                + "(IdOrden, IdMotorAnterior, IdMotorNuevo, FechaCambio, "
                + "DestinoMotorAnterior, ResultadoPruebas, Garantia) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cambio.getIdOrden());
            ps.setInt(2, cambio.getIdMotorAnterior());
            ps.setInt(3, cambio.getIdMotorNuevo());

            if (cambio.getFechaCambio() == null) {
                ps.setTimestamp(4,
                        new Timestamp(System.currentTimeMillis()));
            } else {
                ps.setTimestamp(4, cambio.getFechaCambio());
            }

            ps.setString(5, cambio.getDestinoMotorAnterior());
            ps.setString(6, cambio.getResultadoPruebas());
            ps.setString(7, cambio.getGarantia());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar cambio de motor: "
                    + e.getMessage());
            return false;
        }
    }

    // LISTAR CAMBIOS DE MOTOR POR ORDEN
    public ArrayList<CambioMotor> listarPorOrden(int idOrden) {

        ArrayList<CambioMotor> lista = new ArrayList<>();

        String sql = "SELECT * FROM CambiosMotor WHERE IdOrden = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idOrden);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    CambioMotor cambio = new CambioMotor();

                    cambio.setIdCambio(rs.getInt("IdCambio"));
                    cambio.setIdOrden(rs.getInt("IdOrden"));
                    cambio.setIdMotorAnterior(
                            rs.getInt("IdMotorAnterior"));
                    cambio.setIdMotorNuevo(
                            rs.getInt("IdMotorNuevo"));
                    cambio.setFechaCambio(
                            rs.getTimestamp("FechaCambio"));
                    cambio.setDestinoMotorAnterior(
                            rs.getString("DestinoMotorAnterior"));
                    cambio.setResultadoPruebas(
                            rs.getString("ResultadoPruebas"));
                    cambio.setGarantia(rs.getString("Garantia"));

                    lista.add(cambio);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar cambios de motor: "
                    + e.getMessage());
        }

        return lista;
    }
}
    

