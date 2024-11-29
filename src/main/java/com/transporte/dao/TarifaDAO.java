package com.transporte.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.transporte.config.DatabaseConnection;
import com.transporte.models.Tarifa;

public class TarifaDAO
{
    public static List<Tarifa> obtenerTarifasPorRuta(int idRuta) throws SQLException
    {
        List<Tarifa> tarifas = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT id_tarifa, id_ruta, nombre, costo FROM Tarifas WHERE id_ruta = ?"))
        {
            ps.setInt(1, idRuta);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int id_tarifa   = rs.getInt("id_tarifa");
                int id_ruta     = rs.getInt("id_ruta");
                String nombre   = rs.getString("nombre");
                double costo    = rs.getDouble("costo");

                tarifas.add(new Tarifa(id_tarifa, id_ruta, nombre, costo));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return tarifas;
    }
}
