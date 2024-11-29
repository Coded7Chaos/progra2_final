package com.transporte.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.transporte.config.DatabaseConnection;

public class RutaNumeroDAO
{
    public static String obtenerNumeroPorRuta(int id) throws SQLException
    {
        String numero = "";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT numero FROM Ruta_Numeros WHERE id_ruta = ?"))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                String ruta_numero    = rs.getString("numero");
                
                numero = ruta_numero;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return numero;
    }
}
