/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.dao;

import com.mycompany.autotechmanager.conexion.ConexionDB;
import com.mycompany.autotechmanager.modelo.Mecanico;
import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author Dell
 */
public class MecanicoDAO {
    
    public boolean registrar(Mecanico mecanico) {

        String sql = "INSERT INTO Mecanicos "
                + "(Nombre, DNI, Telefono, Especialidad, Estado) "
                + "VALUES (?, ?, ?, ?, 1)";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, mecanico.getNombre());
            ps.setString(2, mecanico.getDni());
            ps.setString(3, mecanico.getTelefono());
            ps.setString(4, mecanico.getEspecialidad());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar mecánico: "
                    + e.getMessage());
            return false;
        }
    }

    public ArrayList<Mecanico> listar() {

        ArrayList<Mecanico> lista = new ArrayList<>();

        String sql = "SELECT * FROM Mecanicos WHERE Estado = 1";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Mecanico mecanico = new Mecanico();

                mecanico.setIdMecanico(rs.getInt("IdMecanico"));
                mecanico.setNombre(rs.getString("Nombre"));
                mecanico.setDni(rs.getString("DNI"));
                mecanico.setTelefono(rs.getString("Telefono"));
                mecanico.setEspecialidad(
                        rs.getString("Especialidad"));
                mecanico.setEstado(rs.getBoolean("Estado"));

                lista.add(mecanico);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar mecánicos: "
                    + e.getMessage());
        }

        return lista;
    }
    
    public boolean desactivar(int idMecanico) {

    String sql = "UPDATE Mecanicos SET Estado = 0 "
            + "WHERE IdMecanico = ?";

    try (Connection cn = ConexionDB.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setInt(1, idMecanico);

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error al desactivar mecánico: "
                + e.getMessage());
        return false;
    }
}
    
    
    public boolean actualizar(Mecanico mecanico) {

    String sql = "UPDATE Mecanicos SET "
            + "Nombre = ?, DNI = ?, Telefono = ?, "
            + "Especialidad = ?, Estado = ? "
            + "WHERE IdMecanico = ?";

    try (Connection cn = ConexionDB.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setString(1, mecanico.getNombre());
        ps.setString(2, mecanico.getDni());
        ps.setString(3, mecanico.getTelefono());
        ps.setString(4, mecanico.getEspecialidad());
        ps.setBoolean(5, mecanico.isEstado());
        ps.setInt(6, mecanico.getIdMecanico());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error al actualizar mecánico: "
                + e.getMessage());
        return false;
    }
    
    } 
    
}
