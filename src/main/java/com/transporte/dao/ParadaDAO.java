package com.transporte.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import com.transporte.models.Zona;
import com.transporte.config.DatabaseConnection;
import com.transporte.models.Parada;
import com.transporte.utils.Zona;

public class ParadaDAO implements ParadaDAOInterface
{
    @Override
    public void guardarParada(Parada parada)
    {
        try (Connection conn = DatabaseConnection.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("INSERT INTO parada (nombre, longitud, latitud, direccion, color, idZona) VALUES (?, ?, ?, ?, ?, ?)"))
        {
            stmt.setString(1, parada.getNombre());
            stmt.setDouble(2, parada.getLongitud());
            stmt.setDouble(3, parada.getLatitud());
		    stmt.setString(4, parada.getDireccion());
            stmt.setString(5, parada.getColor() == null ? "255,255,0":	parada.getColor());
		    stmt.setInt(6, parada.getZonaId(parada.getZona()));

		    int filasAfectadas = stmt.executeUpdate();
		    if (filasAfectadas > 0)
                System.out.println("Parada guardada exitosamente.");

        } catch (SQLException e) {
		    e.printStackTrace();
            System.err.println("Error al guardar la parada: " + e.getMessage());
	    }
    }

    @Override
    public List<Parada> obtenerParadas()
    {

	    List<Parada> paradas = new ArrayList<>();

	    String query = """
            SELECT p.id AS id, p.nombre AS nombre, p.longitud AS longitud, 
                   p.latitud AS latitud, p.direccion AS direccion, p.color AS color, z.nombre AS zona, 
                   p.estado AS estado
            FROM parada p
            JOIN zona z ON p.idZona = z.id
            """;

	    try (Connection conn = DatabaseConnection.getConnection();
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(query))
        {
	    	while (rs.next())
            {
	    		int id              = rs.getInt("id");
	    		String nombre       = rs.getString("nombre");
	    		double longitud     = rs.getDouble("longitud");
	    		double latitud      = rs.getDouble("latitud");
	    		String direccion    = rs.getString("direccion");
	    		String color        = rs.getString("color");
	    		String zonaNombre   = rs.getString("zona");
	    		boolean estado      = rs.getBoolean("estado");

	    		Zona zona = null;
                try
                {
                    zona = Zona.valueOf(zonaNombre.toUpperCase()); 
	    		} catch (IllegalArgumentException e) {
                    System.err.println("Nombre de zona inválido: " + zonaNombre);
                }

	    		// Crear el objeto Parada
	    		Parada parada = new Parada(id, nombre, longitud, latitud, direccion, color,zona, estado);
                
	    		// Agregar a la lista
	    		paradas.add(parada);
	    	}

	    } catch(SQLException e) {
	    	e.printStackTrace();
	    }
	    return paradas;
    }

    @Override
    public List<Parada> obtenerParadasPorRuta(int rutaId)
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerParadasPorRuta'");
    }

    @Override
    public void actualizarParada(Parada parada)
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarParada'");
    }

    @Override
    public void eliminarParada(int idParada)
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarParada'");
    }
}