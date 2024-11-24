package com.transporte.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.transporte.config.DatabaseConnection;
import com.transporte.models.MedioTransporte;
import com.transporte.models.Minibus;
import com.transporte.models.Pumakatari;
import com.transporte.models.Teleferico;

public class TransporteDAO
{
    public List<MedioTransporte> obtenerTransportes()
    {
        List<MedioTransporte> transportes = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Transporte"))
        {

            while (rs.next())
            {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo");

                if ("Minibus".equals(tipo))
                {
                    transportes.add(new Minibus(id, nombre));
                }
                else if ("Pumakatari".equals(tipo))
                {
                    List<String> paradas = obtenerParadas(conn, id);
                    transportes.add(new Pumakatari(id, nombre, paradas));
                }
                else if ("Teleferico".equals(tipo))
                {
                    List<String> estaciones = obtenerParadas(conn, id);
                    transportes.add(new Teleferico(id, nombre, estaciones));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transportes;
    }

    private List<String> obtenerParadas(Connection conn, int idTransporte) throws SQLException
    {
        List<String> paradas = new ArrayList<>();
        String query = "SELECT nombre FROM Parada WHERE id_transporte = ?";
        try (PreparedStatement ps = conn.prepareStatement(query))
        {
            ps.setInt(1, idTransporte);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                paradas.add(rs.getString("nombre"));
            }
        }
        return paradas;
    }
}
