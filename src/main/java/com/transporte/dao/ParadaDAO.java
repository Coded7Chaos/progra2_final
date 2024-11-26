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
	            PreparedStatement stmt = conn.prepareStatement("INSERT INTO parada (nombre, longitud, latitud, direccion, color, idZona) VALUES (?, ?, ?, ?, ?, ?)"))
        {
            stmt.setString(1, parada.getNombre());
            stmt.setDouble(2, parada.getLongitud());
            stmt.setDouble(3, parada.getLatitud());
		    stmt.setString(4, parada.getDireccion());
            stmt.setString(5, parada.getColor() == null ? "255,255,0":	parada.getColor());
		    stmt.setInt(6, parada.getZona().getIdZona());

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
            SELECT id, nombre, longitud, latitud, direccion, color, idZona, estado
            FROM parada 
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
                int idZona          = rs.getInt("idZona");
	    		boolean estado      = rs.getBoolean("estado");
                
                ZonaDAO zDAO = new ZonaDAO();
	    		Zona zona = zDAO.getZonaDeParada(idZona);


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
    public List<Parada> obtenerParadaPorId(int idRuta)
    {
        List<Parada> paradas = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT id_parada, id_zona, nombre, latitud, longitud, direccion, color, estado FROM Paradas WHERE id_ruta = ?"))
        {
            ps.setInt(1, idRuta);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                int id_parada       = rs.getInt("id_parada");
                int id_zona         = rs.getInt("id_zona");
	    		String nombre       = rs.getString("nombre");
                double latitud      = rs.getDouble("latitud");
	    		double longitud     = rs.getDouble("longitud");
	    		String direccion    = rs.getString("direccion");
                String color        = rs.getString("color");
	    		boolean estado      = rs.getBoolean("estado");

                ZonaDAO zDAO = new ZonaDAO();
	    		Zona zona = zDAO.getZonaDeParada(id_zona);

                Parada parada = new Parada(id_parada, nombre, longitud, latitud, direccion, color, zona, estado);
                paradas.add(parada);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paradas;
    }

    @Override
    public List<Parada> obtenerParadasPorRuta(int rutaId)
    {
        List<Parada> paradas = new ArrayList<>();

	    String query = """
            SELECT p.id, p.nombre, p.longitud, p.latitud, p.direccion, p.color, p.idZona, p.estado
            FROM parada p
            JOIN parada_ruta pr ON p.id = pr.idParada
            WHERE pr.idRuta = ?
            """;

	    try (Connection conn = DatabaseConnection.getConnection();
	            PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setInt(1, rutaId);
            try(ResultSet rs = stmt.executeQuery())
            {
                while (rs.next())
                {
                    int id              = rs.getInt("id");
                    String nombre       = rs.getString("nombre");
                    double longitud     = rs.getDouble("longitud");
                    double latitud      = rs.getDouble("latitud");
                    String direccion    = rs.getString("direccion");
                    String color        = rs.getString("color");
                    int idZona          = rs.getInt("idZona");
                    boolean estado      = rs.getBoolean("estado");
                    
                    ZonaDAO zDAO = new ZonaDAO();
                    Zona zona = zDAO.getZonaDeParada(idZona);
    
    
                    // Crear el objeto Parada
                    Parada parada = new Parada(id, nombre, longitud, latitud, direccion, color,zona, estado);
                    
                    // Agregar a la lista
                    paradas.add(parada);
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }         
        } catch (SQLException e){
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