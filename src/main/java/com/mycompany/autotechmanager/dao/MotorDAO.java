/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.dao;

import com.mycompany.autotechmanager.conexion.ConexionDB;
import com.mycompany.autotechmanager.modelo.Motor;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class MotorDAO {
    
     // REGISTRAR MOTOR
    public boolean registrar(Motor motor) {

        String sql = "INSERT INTO Motores "
                + "(NumeroSerie, Marca, Modelo, TipoMotor, Estado, Precio, Ubicacion) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, motor.getNumeroSerie());
            ps.setString(2, motor.getMarca());
            ps.setString(3, motor.getModelo());
            ps.setString(4, motor.getTipoMotor());
            ps.setString(5, motor.getEstado());
            ps.setBigDecimal(6, motor.getPrecio());
            ps.setString(7, motor.getUbicacion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar motor: " + e.getMessage());
            return false;
        }
    }

    // LISTAR MOTORES
    public ArrayList<Motor> listar() {

        ArrayList<Motor> lista = new ArrayList<>();

        String sql = "SELECT * FROM Motores";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Motor motor = new Motor();

                motor.setIdMotor(rs.getInt("IdMotor"));
                motor.setNumeroSerie(rs.getString("NumeroSerie"));
                motor.setMarca(rs.getString("Marca"));
                motor.setModelo(rs.getString("Modelo"));
                motor.setTipoMotor(rs.getString("TipoMotor"));
                motor.setEstado(rs.getString("Estado"));
                motor.setPrecio(rs.getBigDecimal("Precio"));
                motor.setUbicacion(rs.getString("Ubicacion"));

                lista.add(motor);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar motores: " + e.getMessage());
        }

        return lista;
    }
}
    
