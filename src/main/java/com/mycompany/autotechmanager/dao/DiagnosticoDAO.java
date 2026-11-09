/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.dao;

import com.mycompany.autotechmanager.conexion.ConexionDB;
import com.mycompany.autotechmanager.modelo.Diagnostico;

import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author Dell
 */
public class DiagnosticoDAO {
    
      // REGISTRAR DIAGNOSTICO
    public boolean registrar(Diagnostico diagnostico) {

        String sql = "INSERT INTO Diagnosticos "
                + "(IdOrden, EvaluacionTecnica, Evidencias, FechaDiagnostico) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, diagnostico.getIdOrden());
            ps.setString(2, diagnostico.getEvaluacionTecnica());
            ps.setString(3, diagnostico.getEvidencias());

            if (diagnostico.getFechaDiagnostico() == null) {
                ps.setTimestamp(4,
                        new Timestamp(System.currentTimeMillis()));
            } else {
                ps.setTimestamp(4, diagnostico.getFechaDiagnostico());
            }

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar diagnóstico: " + e.getMessage());
            return false;
        }
    }

    // LISTAR DIAGNOSTICOS POR ORDEN
    public ArrayList<Diagnostico> listarPorOrden(int idOrden) {

        ArrayList<Diagnostico> lista = new ArrayList<>();

        String sql = "SELECT * FROM Diagnosticos "
                + "WHERE IdOrden = ? ORDER BY FechaDiagnostico DESC";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idOrden);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Diagnostico diagnostico = new Diagnostico();

                    diagnostico.setIdDiagnostico(
                            rs.getInt("IdDiagnostico"));

                    diagnostico.setIdOrden(
                            rs.getInt("IdOrden"));

                    diagnostico.setEvaluacionTecnica(
                            rs.getString("EvaluacionTecnica"));

                    diagnostico.setEvidencias(
                            rs.getString("Evidencias"));

                    diagnostico.setFechaDiagnostico(
                            rs.getTimestamp("FechaDiagnostico"));

                    lista.add(diagnostico);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar diagnósticos: " + e.getMessage());
        }

        return lista;
    }
}
    

