package com.transporte.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import com.transporte.models.Zona;
import com.transporte.config.DatabaseConnection;
import com.transporte.models.*;
//import com.transporte.utils.Zona;

public class ParadaDAO 
{
    public List<Parada> obtenerParadas()
    {

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
	    		int id_parada       = rs.getInt("id_parada");
                int id_ruta         = rs.getInt("id_ruta");
                int id_zona         = rs.getInt("id_zona");
	    		String nombre       = rs.getString("nombre");
                double latitud      = rs.getDouble("latitud");
	    		double longitud     = rs.getDouble("longitud");
	    		String direccion    = rs.getString("direccion");
                String color        = rs.getString("color");
	    		boolean estado      = rs.getBoolean("estado");
                
                ZonaDAO zDAO = new ZonaDAO();
	    		Zona zona = zDAO.getZonaDeParada(id_zona);

                //Parada parada = new Parada(id_parada, id_ruta, nombre, latitud, longitud, direccion, color, zona, estado);
                
	    		// Agregar a la lista
	    		//paradas.add(parada);
	    	}

	    } catch(SQLException e) {
	    	e.printStackTrace();
	    }
	    return paradas;
    }

    public List<Parada> obtenerParadaPorId(int idRuta)
    {
        List<Parada> paradas = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT id_parada, id_ruta, id_zona, nombre, latitud, longitud, direccion, color, estado FROM Paradas WHERE id_ruta = ?"))
        {
            ps.setInt(1, idRuta);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                int id_parada       = rs.getInt("id_parada");
                int id_ruta         = rs.getInt("id_ruta");
                int id_zona         = rs.getInt("id_zona");
	    		String nombre       = rs.getString("nombre");
                double latitud      = rs.getDouble("latitud");
	    		double longitud     = rs.getDouble("longitud");
	    		String direccion    = rs.getString("direccion");
                String color        = rs.getString("color");
	    		boolean estado      = rs.getBoolean("estado");

                ZonaDAO zDAO = new ZonaDAO();
	    		Zona zona = zDAO.getZonaDeParada(id_zona);

                //Parada parada = new ParadaPuma(id_parada, id_ruta, nombre, latitud, longitud, direccion, color, zona, estado);
                //paradas.add(parada);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paradas;
    }
}