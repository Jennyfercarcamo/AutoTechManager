/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.dao;

import com.mycompany.autotechmanager.conexion.ConexionDB;
import com.mycompany.autotechmanager.modelo.Proveedor;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class ProveedorDAO {
    
     public boolean registrar(Proveedor proveedor) {

        String sql = "INSERT INTO Proveedores "
                + "(Nombre, DNI_RTN, Telefono, Correo, Direccion, Estado) "
                + "VALUES (?, ?, ?, ?, ?, 1)";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getDniRtn());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getCorreo());
            ps.setString(5, proveedor.getDireccion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar proveedor: "
                    + e.getMessage());
            return false;
        }
    }

    public ArrayList<Proveedor> listar() {

        ArrayList<Proveedor> lista = new ArrayList<>();

        String sql = "SELECT * FROM Proveedores WHERE Estado = 1";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Proveedor proveedor = new Proveedor();

                proveedor.setIdProveedor(rs.getInt("IdProveedor"));
                proveedor.setNombre(rs.getString("Nombre"));
                proveedor.setDniRtn(rs.getString("DNI_RTN"));
                proveedor.setTelefono(rs.getString("Telefono"));
                proveedor.setCorreo(rs.getString("Correo"));
                proveedor.setDireccion(rs.getString("Direccion"));
                proveedor.setEstado(rs.getBoolean("Estado"));

                lista.add(proveedor);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar proveedores: "
                    + e.getMessage());
        }

        return lista;
    }

    public boolean desactivar(int idProveedor) {

        String sql = "UPDATE Proveedores SET Estado = 0 "
                + "WHERE IdProveedor = ?";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idProveedor);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al desactivar proveedor: "
                    + e.getMessage());
            return false;
        }
    }
}
    

