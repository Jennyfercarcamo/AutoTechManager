/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.dao;

import com.mycompany.autotechmanager.conexion.ConexionDB;
import com.mycompany.autotechmanager.modelo.Cliente;
import java.sql.*;
import java.util.ArrayList;



/**
 *
 * @author Dell
 */
public class ClienteDAO {
    public boolean registrar(Cliente cliente) {

        String sql = "INSERT INTO Clientes "
                + "(Nombre, DNI, Telefono, Correo, Direccion, Estado) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDni());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getCorreo());
            ps.setString(5, cliente.getDireccion());
            ps.setBoolean(6, cliente.getEstado());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar cliente: "
                    + e.getMessage());
            return false;
        }
    }

    public ArrayList<Cliente> listar() {

        ArrayList<Cliente> lista = new ArrayList<>();

        String sql = "SELECT * FROM Clientes WHERE Estado = 1";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setIdCliente(rs.getInt("IdCliente"));
                cliente.setNombre(rs.getString("Nombre"));
                cliente.setDni(rs.getString("DNI"));
                cliente.setTelefono(rs.getString("Telefono"));
                cliente.setCorreo(rs.getString("Correo"));
                cliente.setDireccion(rs.getString("Direccion"));
                cliente.setEstado(rs.getBoolean("Estado"));

                lista.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clientes: "
                    + e.getMessage());
        }

        return lista;
    }

    public boolean actualizar(Cliente cliente) {

    String sql = "UPDATE Clientes SET "
            + "Nombre = ?, DNI = ?, Telefono = ?, "
            + "Correo = ?, Direccion = ?, Estado = ? "
            + "WHERE IdCliente = ?";

    try (Connection cn = ConexionDB.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setString(1, cliente.getNombre());
        ps.setString(2, cliente.getDni());
        ps.setString(3, cliente.getTelefono());
        ps.setString(4, cliente.getCorreo());
        ps.setString(5, cliente.getDireccion());
        ps.setBoolean(6, cliente.getEstado());
        ps.setInt(7, cliente.getIdCliente());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error al actualizar cliente: "
                + e.getMessage());
        return false;
    }
}

    public boolean desactivar(int idCliente) {

        String sql = "UPDATE Clientes SET Estado = 0 "
                + "WHERE IdCliente = ?";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idCliente);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al desactivar cliente: "
                    + e.getMessage());
            return false;
        }
    }    
}
