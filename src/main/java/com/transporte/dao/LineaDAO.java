package com.transporte.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.transporte.config.DatabaseConnection;

public class LineaDAO
{
    public static String obtenerLineaPorRuta(int idRuta) throws SQLException
    {
        String nombreLinea = "";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT nombre FROM Lineas WHERE id_ruta = ?"))
        {
            ps.setInt(1, idRuta);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                String nombre = rs.getString("nombre");

                nombreLinea = nombre;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return nombreLinea;
    }
}
