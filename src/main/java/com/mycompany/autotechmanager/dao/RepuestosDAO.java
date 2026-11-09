/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.dao;

import com.mycompany.autotechmanager.conexion.ConexionDB;
import com.mycompany.autotechmanager.modelo.Repuestos;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class RepuestosDAO {
    
      public boolean registrar(Repuestos repuesto) {
    String sql = "INSERT INTO Repuestos "
            + "(Codigo, Nombre, Descripcion, IdProveedor, Costo, "
            + "PrecioVenta, Stock, StockMinimo, Estado, Marca, Categoria) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection cn = ConexionDB.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setString(1, repuesto.getCodigo());
        ps.setString(2, repuesto.getNombre());
        ps.setString(3, repuesto.getDescripcion());

        if (repuesto.getIdProveedor() > 0) {
            ps.setInt(4, repuesto.getIdProveedor());
        } else {
            ps.setNull(4, Types.INTEGER);
        }

        ps.setDouble(5, repuesto.getCosto());
        ps.setDouble(6, repuesto.getPrecioVenta());
        ps.setInt(7, repuesto.getStock());
        ps.setInt(8, repuesto.getStockMinimo());
        ps.setBoolean(9, repuesto.isEstado());
        ps.setString(10, repuesto.getMarca());
        ps.setString(11, repuesto.getCategoria());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error al registrar repuesto: " + e.getMessage());
        return false;
    }
}

    public ArrayList<Repuestos> listar() {
    ArrayList<Repuestos> lista = new ArrayList<>();

    String sql = "SELECT * FROM Repuestos WHERE Estado = 1";

    try (Connection cn = ConexionDB.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Repuestos repuesto = new Repuestos();

            repuesto.setIdRepuesto(rs.getInt("IdRepuesto"));
            repuesto.setCodigo(rs.getString("Codigo"));
            repuesto.setNombre(rs.getString("Nombre"));
            repuesto.setDescripcion(rs.getString("Descripcion"));
            repuesto.setIdProveedor(rs.getInt("IdProveedor"));
            repuesto.setCosto(rs.getDouble("Costo"));
            repuesto.setPrecioVenta(rs.getDouble("PrecioVenta"));
            repuesto.setStock(rs.getInt("Stock"));
            repuesto.setStockMinimo(rs.getInt("StockMinimo"));
            repuesto.setEstado(rs.getBoolean("Estado"));
            repuesto.setMarca(rs.getString("Marca"));
            repuesto.setCategoria(rs.getString("Categoria"));

            lista.add(repuesto);
        }

    } catch (SQLException e) {
        System.out.println("Error al listar repuestos: " + e.getMessage());
    }

    return lista;
}
    
    public ArrayList<Repuestos> listarStockBajo() {

        ArrayList<Repuestos> lista = new ArrayList<>();

        String sql = "SELECT * FROM Repuestos "
                + "WHERE Estado = 1 AND Stock <= StockMinimo";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Repuestos repuesto = new Repuestos();

                repuesto.setIdRepuesto(rs.getInt("IdRepuesto"));
                repuesto.setCodigo(rs.getString("Codigo"));
                repuesto.setNombre(rs.getString("Nombre"));
                repuesto.setDescripcion(rs.getString("Descripcion"));
                repuesto.setIdProveedor(rs.getInt("IdProveedor"));
                repuesto.setCosto(rs.getDouble("Costo"));
                repuesto.setPrecioVenta(rs.getDouble("PrecioVenta"));
                repuesto.setStock(rs.getInt("Stock"));
                repuesto.setStockMinimo(rs.getInt("StockMinimo"));
                repuesto.setEstado(rs.getBoolean("Estado"));

                lista.add(repuesto);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar stock bajo: "
                    + e.getMessage());
        }

        return lista;
    }    
    
    public boolean actualizar(Repuestos repuesto) {

    String sql = "UPDATE Repuestos SET "
            + "Codigo=?, Nombre=?, Descripcion=?, IdProveedor=?, "
            + "Costo=?, PrecioVenta=?, Stock=?, StockMinimo=?, "
            + "Estado=?, Marca=?, Categoria=? "
            + "WHERE IdRepuesto=?";

    try (Connection cn = ConexionDB.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setString(1, repuesto.getCodigo());
        ps.setString(2, repuesto.getNombre());
        ps.setString(3, repuesto.getDescripcion());

        if (repuesto.getIdProveedor() > 0) {
            ps.setInt(4, repuesto.getIdProveedor());
        } else {
            ps.setNull(4, Types.INTEGER);
        }

        ps.setDouble(5, repuesto.getCosto());
        ps.setDouble(6, repuesto.getPrecioVenta());
        ps.setInt(7, repuesto.getStock());
        ps.setInt(8, repuesto.getStockMinimo());
        ps.setBoolean(9, repuesto.isEstado());
        ps.setString(10, repuesto.getMarca());
        ps.setString(11, repuesto.getCategoria());
        ps.setInt(12, repuesto.getIdRepuesto());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error al actualizar repuesto: " + e.getMessage());
        return false;
    }
}
    
    public boolean desactivar(int idRepuesto) {

    String sql = "UPDATE Repuestos SET Estado=0 "
            + "WHERE IdRepuesto=?";

    try (Connection cn = ConexionDB.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setInt(1, idRepuesto);

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error al desactivar repuesto: "
                + e.getMessage());
        return false;
    }
}
}
