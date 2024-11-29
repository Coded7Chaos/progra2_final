package com.transporte.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.transporte.config.DatabaseConnection;

public class CartelDAO
{
    public static List<String> obtenerCartelesPorRuta(int idRuta) throws SQLException
    {
        List<String> carteles = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT nombre FROM Carteles WHERE id_ruta = ?"))
        {
            ps.setInt(1, idRuta);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                String nombre = rs.getString("nombre");

                carteles.add(nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return carteles;
    }
}
