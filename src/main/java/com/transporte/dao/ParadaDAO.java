package com.transporte.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.transporte.config.DatabaseConnection;
import com.transporte.models.*;
//import com.transporte.utils.Zona;

public class ParadaDAO {
    public List<Parada> obtenerParadas() {

        List<Parada> paradas = new ArrayList<>();

        String query = """
                SELECT id_parada, id_ruta, id_zona, nombre, latitud, longitud, direccion, color, estado
                FROM Paradas
                """;

        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query))
        {
            while (rs.next())
            {
                int id_parada = rs.getInt("id_parada");
                int id_ruta = rs.getInt("id_ruta");
                int id_zona = rs.getInt("id_zona");
                String nombre = rs.getString("nombre");
                double latitud = rs.getDouble("latitud");
                double longitud = rs.getDouble("longitud");
                String direccion = rs.getString("direccion");
                String color = rs.getString("color");
                boolean estado = rs.getBoolean("estado");

                ZonaDAO zDAO = new ZonaDAO();
                Zona zona = zDAO.getZonaDeParada(id_zona);

                // Parada parada = new Parada(id_parada, id_ruta, nombre, latitud, longitud,
                // direccion, color, zona, estado);

                // Agregar a la lista
                // paradas.add(parada);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paradas;
    }

    public List<Parada> obtenerParadaPorId(int idRuta)
    {
        List<Parada> paradas = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT id_parada, id_ruta, id_zona, nombre, latitud, longitud, direccion, color, estado FROM Paradas WHERE id_ruta = ?"))
        {
            ps.setInt(1, idRuta);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                int id_parada = rs.getInt("id_parada");
                int id_ruta = rs.getInt("id_ruta");
                int id_zona = rs.getInt("id_zona");
                String nombre = rs.getString("nombre");
                double latitud = rs.getDouble("latitud");
                double longitud = rs.getDouble("longitud");
                String direccion = rs.getString("direccion");
                String color = rs.getString("color");
                boolean estado = rs.getBoolean("estado");

                ZonaDAO zDAO = new ZonaDAO();
                Zona zona = zDAO.getZonaDeParada(id_zona);

                // Parada parada = new ParadaPuma(id_parada, id_ruta, nombre, latitud, longitud,
                // direccion, color, zona, estado);
                // paradas.add(parada);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paradas;
    }

    // VERSION SIMPLIFICADA PARA RENDERIZAR LAS RUTAS UNICAMENTE CON LA INFORMACION
    // DE LATITUD Y LONGITUD
    // RELACIONADA A LAS PARADAS

    public List<Parada> obtenerPuntosPorId(int idRuta, int tipo)
    {
        List<Parada> paradas = new ArrayList<>();
        
        switch (tipo)
        {
            case 1:
                try (Connection conn = DatabaseConnection.getConnection();
                    PreparedStatement ps = conn.prepareStatement("SELECT p.id_parada, p.id_zona, p.latitud, p.longitud, p.color, pm.trayectoria FROM Paradas AS p JOIN Puntos_minibus AS pm ON pm.idParada = p.id_parada WHERE p.id_ruta = ?"))
                {
                    ps.setInt(1, idRuta);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next())
                    {
                        int id_parada       = rs.getInt("id_parada");
                        int id_zona         = rs.getInt("id_zona");
                        double latitud      = rs.getDouble("latitud");
                        double longitud     = rs.getDouble("longitud");
                        String color        = rs.getString("color");
                        Boolean trayectoria = rs.getBoolean("trayectoria");

                        ZonaDAO zDAO = new ZonaDAO();
                        Zona zona = zDAO.getZonaDeParada(id_zona);

                        Parada parada = new PuntosMinibus(longitud, latitud, zona, color, trayectoria);
                        paradas.add(parada);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case 2:

                try (Connection conn = DatabaseConnection.getConnection();
                    PreparedStatement ps = conn.prepareStatement("SELECT p.id_parada, p.id_zona, p.latitud, p.longitud, p.color, pp.nombre, pp.direccion, pp.estado, pp.trayectoria, pp.tiempo_espera FROM Paradas AS p JOIN Paradas_pumakatari AS pp ON pp.idParada = p.id_parada WHERE p.id_ruta = ?"))
                {
                    ps.setInt(1, idRuta);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next())
                    {
                        int id_parada       = rs.getInt("id_parada");
                        int id_zona         = rs.getInt("id_zona");
                        double latitud      = rs.getDouble("latitud");
                        double longitud     = rs.getDouble("longitud");
                        String color        = rs.getString("color");
                        String nombre       = rs.getString("nombre");
                        String direccion    = rs.getString("direccion");
                        Boolean estado      = rs.getBoolean("estado");
                        Boolean trayectoria = rs.getBoolean("trayectoria");
                        int tiempoDeEspera  = rs.getInt("tiempo_espera");
                    
                        ZonaDAO zDAO = new ZonaDAO();
                        Zona zona = zDAO.getZonaDeParada(id_zona);

                        Parada parada = new ParadaPuma(longitud, latitud, zona, nombre, direccion, estado, trayectoria, tiempoDeEspera);
                        paradas.add(parada);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case 3:
                try (Connection conn = DatabaseConnection.getConnection();
                    PreparedStatement ps = conn.prepareStatement("SELECT p.id_parada, p.id_zona, p.longitud, p.latitud, p.color, et.id, et.nombre, et.direccion, et.parqueo FROM Paradas AS p JOIN Estaciones_teleferico AS et ON et.idParada = p.id_parada WHERE p.id_ruta = ?"))
                {
                    ps.setInt(1, idRuta);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next())
                    {
                        int id_parada       = rs.getInt("id_parada");
                        int id_zona         = rs.getInt("id_zona");
                        double latitud      = rs.getDouble("latitud");
                        double longitud     = rs.getDouble("longitud");
                        String color        = rs.getString("color");
                        int id_estacion     = rs.getInt("id");
                        String nombre       = rs.getString("nombre");
                        String direccion    = rs.getString("direccion");
                        Boolean parqueo     = rs.getBoolean("parqueo");
                    
                        ZonaDAO zDAO = new ZonaDAO();
                        Zona zona = zDAO.getZonaDeParada(id_zona);
                    
                        List<Integer> negocios = NegociosDAO.obtenerNegociosPorEstacion(id_estacion);
                        // (double longitud, double latitud, Zona zona, String nombre, String direccion, Boolean parqueo, List<Integer> negocios)
                        Parada parada = new EstacionTeleferico(longitud, latitud, zona, nombre, direccion, parqueo, negocios);
                        paradas.add(parada);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        
            default:
                break;
        }
        return paradas;
    }
}