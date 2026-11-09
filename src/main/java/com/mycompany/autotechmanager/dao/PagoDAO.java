/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autotechmanager.dao;

import com.mycompany.autotechmanager.conexion.ConexionDB;
import com.mycompany.autotechmanager.modelo.Pago;
import com.mycompany.autotechmanager.modelo.Pago;

import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class PagoDAO {
    
    
    // REGISTRAR PAGO
    public boolean registrar(Pago pago) {

        String sqlTotal = "SELECT ISNULL(SUM(Cantidad * CostoManoObra), 0) "
                + "FROM DetalleOrden WHERE IdOrden = ?";

        String sqlPagos = "SELECT ISNULL(SUM(Monto), 0) "
                + "FROM Pagos WHERE IdOrden = ?";

        String sqlInsert = "INSERT INTO Pagos "
                + "(IdOrden, FechaPago, Monto, MetodoPago) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConexion()) {

            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            con.setAutoCommit(false);

            try {

                BigDecimal totalOrden;
                BigDecimal totalPagado;

                try (PreparedStatement ps = con.prepareStatement(sqlTotal)) {

                    ps.setInt(1, pago.getIdOrden());

                    try (ResultSet rs = ps.executeQuery()) {
                        rs.next();
                        totalOrden = rs.getBigDecimal(1);
                    }
                }

                try (PreparedStatement ps = con.prepareStatement(sqlPagos)) {

                    ps.setInt(1, pago.getIdOrden());

                    try (ResultSet rs = ps.executeQuery()) {
                        rs.next();
                        totalPagado = rs.getBigDecimal(1);
                    }
                }

                BigDecimal saldo = totalOrden.subtract(totalPagado);

                if (pago.getMonto() == null
                        || pago.getMonto().compareTo(BigDecimal.ZERO) <= 0
                        || pago.getMonto().compareTo(saldo) > 0) {

                    con.rollback();
                    return false;
                }

                try (PreparedStatement ps = con.prepareStatement(sqlInsert)) {

                    ps.setInt(1, pago.getIdOrden());

                    if (pago.getFechaPago() == null) {
                        ps.setTimestamp(2,
                                new Timestamp(System.currentTimeMillis()));
                    } else {
                        ps.setTimestamp(2, pago.getFechaPago());
                    }

                    ps.setBigDecimal(3, pago.getMonto());
                    ps.setString(4, pago.getMetodoPago());

                    ps.executeUpdate();
                }

                con.commit();
                return true;

            } catch (SQLException e) {

                con.rollback();
                throw e;

            } finally {

                con.setAutoCommit(true);
            }

        } catch (SQLException e) {

            System.out.println("Error al registrar pago: " + e.getMessage());
            return false;
        }
    }

    // LISTAR PAGOS DE UNA ORDEN
    public ArrayList<Pago> listarPorOrden(int idOrden) {

        ArrayList<Pago> lista = new ArrayList<>();

        String sql = "SELECT * FROM Pagos "
                + "WHERE IdOrden = ? ORDER BY FechaPago DESC";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idOrden);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Pago pago = new Pago();

                    pago.setIdPago(rs.getInt("IdPago"));
                    pago.setIdOrden(rs.getInt("IdOrden"));
                    pago.setFechaPago(rs.getTimestamp("FechaPago"));
                    pago.setMonto(rs.getBigDecimal("Monto"));
                    pago.setMetodoPago(rs.getString("MetodoPago"));

                    lista.add(pago);
                }
            }

        } catch (SQLException e) {

            System.out.println("Error al listar pagos: " + e.getMessage());
        }

        return lista;
    }

    // CALCULAR TOTAL PAGADO
    public BigDecimal totalPagado(int idOrden) {

        String sql = "SELECT ISNULL(SUM(Monto), 0) "
                + "FROM Pagos WHERE IdOrden = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idOrden);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getBigDecimal(1);
                }
            }

        } catch (SQLException e) {

            System.out.println("Error al calcular pagos: " + e.getMessage());
        }

        return BigDecimal.ZERO;
    }
}
    
