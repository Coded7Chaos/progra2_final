package com.transporte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RutaDAO
{

		TestConexion db = new TestConexion();
		
		Connection conn = db.getConnection();
		
		public void crearRuta(Ruta ruta) throws SQLException{
		
			String query = "INSERT INTO rutas (nombre, horarios, estado) VALUES (?, ?, ?)";
			PreparedStatement insertarRuta = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			insertarRuta.setString(1, ruta.getNombre());
			insertarRuta.setString(2, ruta.getHorarios());
			insertarRuta.setBoolean(3, ruta.isEstado());
			insertarRuta.executeUpdate();
			
			ResultSet keys = insertarRuta.getGeneratedKeys();
			if(keys.next()) {
				int idRuta = keys.getInt(1);
				ruta.setIdRuta(idRuta);
			}
			
		}
}
	