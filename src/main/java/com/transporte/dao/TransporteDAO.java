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
    public MedioTransporte obtenerTransportePorRuta(int id)
    {
        MedioTransporte transporte = new Minibus(0, "000", new ArrayList<>(), new ArrayList<>());

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT id_transporte, nombre, tipo FROM Transportes WHERE id_ruta = ?"))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int id_transporte   = rs.getInt("id_transporte");
                String nombre       = rs.getString("nombre");
                String tipo         = rs.getString("tipo");

                if ("Minibus".equals(tipo))
                {
                    transporte = new Minibus(id_transporte, nombre, new ArrayList<>(), new ArrayList<>());
                }
                else if ("Pumakatari".equals(tipo))
                {
                    transporte = new Pumakatari(id_transporte, new ArrayList<>());
                }
                else if ("Teleferico".equals(tipo))
                {
                    transporte = new Teleferico(id_transporte, nombre);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transporte;
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
