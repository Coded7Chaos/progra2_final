package com.transporte.dao;

import java.sql.*;
import java.util.*;

import com.transporte.config.DatabaseConnection;
import com.transporte.models.Zona;

public class ZonaDAO
{
    public List<Zona> listarZonas()
    {

	    List<Zona> zonas = new ArrayList<>();

	    String query = """
            SELECT id_zona, nombre
            FROM Zonas
            """;

	    try (Connection conn = DatabaseConnection.getConnection();
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(query))
        {
	    	while (rs.next())
            {
	    		int id_zona		= rs.getInt("id_zona");
	    		String nombre	= rs.getString("nombre");
	    		
	    		// Crear el objeto Zona
	    		Zona zona = new Zona(id_zona, nombre);
                
	    		// Agregar a la lista
	    		zonas.add(zona);
	    	}

	    } catch(SQLException e) {
	    	e.printStackTrace();
	    }
	    return zonas;
    }



	public Zona getZonaDeParada(int idZona)
    {
		Zona zona = null;
		String query = """
    	    SELECT id_zona, nombre
    	    FROM Zonas
			WHERE id_zona = ?
    	    """;
		try (Connection conn = DatabaseConnection.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(query))
		{
				stmt.setInt(1, idZona);
			try (ResultSet rs = stmt.executeQuery())
			{
				if (rs.next())
				{
					int id			= rs.getInt("id_zona");
					String nombre	= rs.getString("nombre");
					
					// Crear el objeto Zona
					zona = new Zona(id, nombre);
				}
				else
				{
					System.out.println("No existe una zona con el id proporcionado");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return zona;
	}
}
