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
            SELECT id, nombre
            FROM zona
            """;

	    try (Connection conn = DatabaseConnection.getConnection();
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(query))
        {
	    	while (rs.next())
            {
	    		int id			= rs.getInt("id");
	    		String nombre	= rs.getString("nombre");
	    		
	    		// Crear el objeto Zona
	    		Zona zona = new Zona(id, nombre);
                
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
    	    SELECT id, nombre
    	    FROM zona
			WHERE id = ?
    	    """;
		try (Connection conn = DatabaseConnection.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(query))
		{
				stmt.setInt(1, idZona);
			try (ResultSet rs = stmt.executeQuery())
			{
				if (rs.next())
				{
					int id			= rs.getInt("id");
					String nombre	= rs.getString("nombre");
					
					// Crear el objeto Zona
					zona = new Zona(id, nombre);
				}
				else
				{
					System.out.print("No existe una zona con el id proporcionado");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return zona;
	}
}
