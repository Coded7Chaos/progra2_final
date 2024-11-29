package com.transporte.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.transporte.config.DatabaseConnection;
import com.transporte.models.Ruta;
import com.transporte.models.Teleferico;
import com.transporte.models.Minibus;
import com.transporte.models.Parada;
import com.transporte.models.Pumakatari;
import com.transporte.models.Tarifa;

public class RutaDAO
{
    public static List<Ruta> obtenerRutas() throws SQLException
    {
        List<Ruta> rutas = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT id_ruta, nombre_inicio, nombre_fin, horario_inicio, horario_fin, tipo_transporte, estado FROM Rutas"))
        {
            while (rs.next())
            {
                int id_ruta             = rs.getInt("id_ruta");
                String nombre_inicio    = rs.getString("nombre_inicio");
                String nombre_fin       = rs.getString("nombre_fin");
                String horario_inicio   = rs.getString("horario_inicio");
                String horario_fin      = rs.getString("horario_fin");
                int tipo_transporte     = rs.getInt("tipo_transporte");
                int estado              = rs.getInt("estado");

                ParadaDAO pdao = new ParadaDAO();
                List<Parada> paradas = pdao.obtenerParadaPorId(id_ruta);

                List<Tarifa> tarifas = TarifaDAO.obtenerTarifasPorRuta(id_ruta);

                switch (tipo_transporte)
                {
                    case 1:
                        rutas.add(new Minibus(id_ruta, nombre_inicio, nombre_fin, horario_inicio, horario_fin, estado, tarifas, paradas, RutaNumeroDAO.obtenerNumeroPorRuta(id_ruta), CartelDAO.obtenerCartelesPorRuta(id_ruta)));
                        break;
                
                    case 2:
                        rutas.add(new Pumakatari(id_ruta, nombre_inicio, nombre_fin, horario_inicio, horario_fin, estado, tarifas, paradas));
                        break;

                    case 3:
                        rutas.add(new Teleferico(id_ruta, nombre_inicio, nombre_fin, horario_inicio, horario_fin, estado, tarifas, paradas, LineaDAO.obtenerLineaPorRuta(id_ruta)));
                        break;

                    default:
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return rutas;
    }
    public static Ruta obtenerRutaPorId(int id) throws SQLException
    {
        Ruta route = new Pumakatari();
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT id_ruta, nombre_inicio, nombre_fin, horario_inicio, horario_fin, tipo_transporte, estado FROM Rutas WHERE id_ruta = ?"))
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
                int tipo_transporte     = rs.getInt("tipo_transporte");
                int estado              = rs.getInt("estado");

                ParadaDAO pdao = new ParadaDAO();
                List<Parada> paradas = pdao.obtenerParadaPorId(id_ruta);

                List<Tarifa> tarifas = TarifaDAO.obtenerTarifasPorRuta(id_ruta);

                switch (tipo_transporte)
                {
                    case 1:
                        route = new Minibus(id_ruta, nombre_inicio, nombre_fin, horario_inicio, horario_fin, estado, tarifas, paradas, RutaNumeroDAO.obtenerNumeroPorRuta(id_ruta), CartelDAO.obtenerCartelesPorRuta(id_ruta));
                        break;
                
                    case 2:
                        route = new Pumakatari(id_ruta, nombre_inicio, nombre_fin, horario_inicio, horario_fin, estado, tarifas, paradas);
                        break;

                    case 3:
                        route = new Teleferico(id_ruta, nombre_inicio, nombre_fin, horario_inicio, horario_fin, estado, tarifas, paradas, LineaDAO.obtenerLineaPorRuta(id_ruta));
                        break;

                    default:
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return route;
    }
}
