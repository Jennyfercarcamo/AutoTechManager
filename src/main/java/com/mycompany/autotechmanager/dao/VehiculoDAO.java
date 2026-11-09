/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.dao;

import com.mycompany.autotechmanager.conexion.ConexionDB;
import com.mycompany.autotechmanager.modelo.Vehiculo;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Dell
 */
public class VehiculoDAO {
     public boolean registrar(Vehiculo vehiculo) {

        String sql = "INSERT INTO Vehiculos "
                + "(IdCliente, Placa, VIN, Marca, Modelo, Anio, "
                + "Color, Kilometraje, Combustible) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, vehiculo.getIdCliente());
            ps.setString(2, vehiculo.getPlaca());
            ps.setString(3, vehiculo.getVin());
            ps.setString(4, vehiculo.getMarca());
            ps.setString(5, vehiculo.getModelo());
            ps.setInt(6, vehiculo.getAnio());
            ps.setString(7, vehiculo.getColor());
            ps.setInt(8, vehiculo.getKilometraje());
            ps.setString(9, vehiculo.getCombustible());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar vehículo: "
                    + e.getMessage());
            return false;
        }
    }

    public ArrayList<Vehiculo> listar() {

        ArrayList<Vehiculo> lista = new ArrayList<>();

        String sql = "SELECT * FROM Vehiculos ";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Vehiculo vehiculo = new Vehiculo();

                vehiculo.setIdVehiculo(rs.getInt("IdVehiculo"));
                vehiculo.setIdCliente(rs.getInt("IdCliente"));
                vehiculo.setPlaca(rs.getString("Placa"));
                vehiculo.setVin(rs.getString("VIN"));
                vehiculo.setMarca(rs.getString("Marca"));
                vehiculo.setModelo(rs.getString("Modelo"));
                vehiculo.setAnio(rs.getInt("Anio"));
                vehiculo.setColor(rs.getString("Color"));
                vehiculo.setKilometraje(rs.getInt("Kilometraje"));
                vehiculo.setCombustible(rs.getString("Combustible"));

                lista.add(vehiculo);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar vehículos: "
                    + e.getMessage());
        }

        return lista;
    }
    
    public ArrayList<Vehiculo> listarPorCliente(int idCliente) {

    ArrayList<Vehiculo> lista = new ArrayList<>();

    String sql = "SELECT * FROM Vehiculos WHERE IdCliente = ?";

    try (Connection cn = ConexionDB.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setInt(1, idCliente);

        try (ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Vehiculo vehiculo = new Vehiculo();

                vehiculo.setIdVehiculo(rs.getInt("IdVehiculo"));
                vehiculo.setIdCliente(rs.getInt("IdCliente"));
                vehiculo.setPlaca(rs.getString("Placa"));
                vehiculo.setVin(rs.getString("VIN"));
                vehiculo.setMarca(rs.getString("Marca"));
                vehiculo.setModelo(rs.getString("Modelo"));
                vehiculo.setAnio(rs.getInt("Anio"));
                vehiculo.setColor(rs.getString("Color"));
                vehiculo.setKilometraje(rs.getInt("Kilometraje"));
                vehiculo.setCombustible(rs.getString("Combustible"));

                lista.add(vehiculo);
            }
        }

    } catch (SQLException e) {
        System.out.println("Error al listar vehículos del cliente: "
                + e.getMessage());
    }

    return lista;
}
    
    public boolean actualizar(Vehiculo vehiculo) {

    String sql = "UPDATE Vehiculos SET IdCliente=?, Placa=?, VIN=?, "
            + "Marca=?, Modelo=?, Anio=?, Color=?, Kilometraje=?, "
            + "Combustible=? WHERE IdVehiculo=?";

    try (Connection con = ConexionDB.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, vehiculo.getIdCliente());
        ps.setString(2, vehiculo.getPlaca());
        ps.setString(3, vehiculo.getVin());
        ps.setString(4, vehiculo.getMarca());
        ps.setString(5, vehiculo.getModelo());
        ps.setInt(6, vehiculo.getAnio());
        ps.setString(7, vehiculo.getColor());
        ps.setInt(8, vehiculo.getKilometraje());
        ps.setString(9, vehiculo.getCombustible());
        ps.setInt(10, vehiculo.getIdVehiculo());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error al actualizar vehículo: " + e.getMessage());
        return false;
    }
}
    
    public boolean eliminar(int idVehiculo) {

    String sql = "DELETE FROM Vehiculos WHERE IdVehiculo = ?";

    try (Connection con = ConexionDB.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idVehiculo);

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error al eliminar vehículo: " + e.getMessage());
        return false;
    }
}
    
}
