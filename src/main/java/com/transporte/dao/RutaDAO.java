package com.transporte.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.transporte.config.DatabaseConnection;
import com.transporte.models.MedioTransporte;
import com.transporte.models.Minibus;
import com.transporte.models.Pumakatari;
import com.transporte.models.Teleferico;
import com.transporte.models.Parada;
import com.transporte.models.Ruta;

public class RutaDAO
{
    public Ruta obtenerRuta(int id)
    {
        Ruta route = new Ruta(id, null, null, null, null, 0, null, null);
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT id,  FROM Rutas"))
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return route;
    }
}
