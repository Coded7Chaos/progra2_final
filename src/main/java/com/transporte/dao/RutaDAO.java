package com.transporte.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.transporte.config.DatabaseConnection;
import com.transporte.models.MedioTransporte;
import com.transporte.models.Parada;
import com.transporte.models.Ruta;

public class RutaDAO
{
    public List<Ruta> obtenerRutas()
    {
        List<Ruta> rutas = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT id_ruta, nombre_inicio, nombre_fin, horario_inicio, horario_fin, estado FROM Rutas"))
        {
            while (rs.next())
            {
                int id_ruta             = rs.getInt("id_ruta");
                String nombre_inicio    = rs.getString("nombre_inicio");
                String nombre_fin       = rs.getString("nombre_fin");
                String horario_inicio   = rs.getString("horario_inicio");
                String horario_fin      = rs.getString("horario_fin");
                int estado              = rs.getInt("estado");

                TransporteDAO tdao = new TransporteDAO();
                MedioTransporte transporte = tdao.obtenerTransportePorRuta(id_ruta);

                ParadaDAO pdao = new ParadaDAO();
                List<Parada> paradas = pdao.obtenerParadaPorId(id_ruta);

                rutas.add(new Ruta(id_ruta, nombre_inicio, nombre_fin, horario_inicio, horario_fin, estado, transporte, paradas));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rutas;
    }
    public Ruta obtenerRutaPorId(int id)
    {
        Ruta route = new Ruta(id, null, null, null, null, 0, null, null);
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT id_ruta, nombre_inicio, nombre_fin, horario_inicio, horario_fin, estado FROM Rutas WHERE id_ruta = ?"))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int id_ruta             = rs.getInt("id_ruta");
                String nombre_inicio    = rs.getString("nombre_inicio");
                String nombre_fin       = rs.getString("nombre_fin");
                String horario_inicio   = rs.getString("horario_inicio");
                String horario_fin      = rs.getString("horario_fin");
                int estado              = rs.getInt("estado");

                TransporteDAO tdao = new TransporteDAO();
                MedioTransporte transporte = tdao.obtenerTransportePorRuta(id_ruta);

                ParadaDAO pdao = new ParadaDAO();
                List<Parada> paradas = pdao.obtenerParadaPorId(id_ruta);

                route = new Ruta(id_ruta, nombre_inicio, nombre_fin, horario_inicio, horario_fin, estado, transporte, paradas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return route;
    }
}
