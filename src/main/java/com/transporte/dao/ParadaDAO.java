package com.transporte.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import com.transporte.models.Zona;
import com.transporte.config.DatabaseConnection;
import com.transporte.models.Parada;
//import com.transporte.utils.Zona;

public class ParadaDAO implements ParadaDAOInterface
{
    @Override
    public void guardarParada(Parada parada)
    {
        try (Connection conn = DatabaseConnection.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("INSERT INTO parada (id_ruta, id_zona, nombre, latitud, longitud, direccion, color, estado) VALUES (?, ?, ?, ?, ?, ?, ?)"))
        {
            stmt.setInt(1, parada.getIdRuta());
            stmt.setInt(2, parada.getZona().getIdZona());
            stmt.setString(3, parada.getNombre());
            stmt.setDouble(4, parada.getLongitud());
            stmt.setDouble(5, parada.getLatitud());
            stmt.setString(6, parada.getDireccion());
            stmt.setString(7, parada.getColor() == null ? "#FFFFFF" : parada.getColor());
            stmt.setBoolean(8, parada.isEstado());

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

                Parada parada = new Parada(id_parada, id_ruta, nombre, longitud, latitud, direccion, color, zona, estado);
                
	    		// Agregar a la lista
	    		paradas.add(parada);
	    	}

	    } catch(SQLException e) {
	    	e.printStackTrace();
	    }
	    return paradas;
    }

    @Override
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

                Parada parada = new Parada(id_parada, id_ruta, nombre, longitud, latitud, direccion, color, zona, estado);
                paradas.add(parada);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paradas;
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