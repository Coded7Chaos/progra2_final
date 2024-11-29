package com.transporte.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.transporte.config.DatabaseConnection;

public class Parada
{
	private int idParada;
    private int idRuta;
	private String nombre;	
	private double longitud;
	private double latitud;
    private String direccion;
	private String color;
	private Zona zona;
	private boolean estado;


	public Parada(int idRuta, String nombre, double latitud, double longitud, String direccion, Zona zona)
    {
        this.idRuta     = idRuta;
        this.nombre     = nombre;
        this.latitud    = latitud;
        this.longitud   = longitud;
        this.direccion  = direccion;
        this.zona       = zona;
    }

    public Parada(int idParada, int idRuta, String nombre, double latitud, double longitud, String direccion, String color, Zona zona, boolean estado)
    {
        this.idParada   = idParada;
        this.idRuta     = idRuta;
        this.nombre     = nombre;
        this.latitud    = latitud;
        this.longitud   = longitud;
        this.direccion  = direccion;
        this.color      = color;
        this.zona       = zona;
        this.estado     = estado;
		this.color      = color != null ? color : "#FFFFFF";
    }

	public int getIdParada() { return idParada; }
    public void setIdParada(int idParada) { this.idParada = idParada; }

    public int getIdRuta() { return idRuta; }
    public void setIdRuta(int idRuta) { this.idRuta = idRuta; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getLongitud() { return longitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }

    public double getLatitud() { return latitud; }
    public void setLatitud(double latitud) { this.latitud = latitud; }

	public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public Zona getZona() { return zona; }
    public void setZona(Zona zona) { this.zona = zona; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    public void guardarParada() throws SQLException
    {
        try (Connection conn = DatabaseConnection.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Paradas (id_ruta, id_zona, nombre, latitud, longitud, direccion, color, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"))
        {
            stmt.setInt(1, idRuta);
            stmt.setInt(2, zona.getIdZona());
            stmt.setString(3, nombre);
            stmt.setDouble(4, latitud);
            stmt.setDouble(5, longitud);
            stmt.setString(6, direccion);
            stmt.setString(7, color == null ? "#FFFFFF" : color);
            stmt.setBoolean(8, false);

		    int filasAfectadas = stmt.executeUpdate();
		    if (filasAfectadas > 0)
                System.out.println("Parada guardada exitosamente.");

        } catch (SQLException e) {
		    e.printStackTrace();
            throw e;
	    }
    }

    public void actualizarParada(int idRuta, String nombre, double latitud, double longitud, String direccion, Zona zona) throws SQLException
    {   
        try (Connection conn = DatabaseConnection.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("UPDATE Paradas SET id_ruta = ?, id_zona = ?, nombre = ?, latitud = ?, longitud = ?, direccion = ?, color = ?, estado = ? WHERE id_parada = ?"))
        {
            stmt.setInt(1, idRuta);
            stmt.setInt(2, zona.getIdZona());
            stmt.setString(3, nombre);
            stmt.setDouble(4, latitud);
            stmt.setDouble(5, longitud);
            stmt.setString(6, direccion);
            stmt.setString(7, color == null ? "#FFFFFF" : color);
            stmt.setBoolean(8, false);
            stmt.setInt(9, idParada);

            int filasAfectadas = stmt.executeUpdate();
		    if (filasAfectadas > 0)
                System.out.println("Parada actualizada exitosamente.");
                
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void eliminarParada() throws SQLException
    {
        try (Connection conn = DatabaseConnection.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Paradas WHERE id_parada = ?"))
        {
            stmt.setInt(1, idParada);

            int filasAfectadas = stmt.executeUpdate();
		    if (filasAfectadas > 0)
                System.out.println("Parada actualizada exitosamente.");
                
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public String toString()
    {
        return String.format("{id=%d, nombre=%s, lat=%f, lon=%f, direccion=%s, color=%s}", idParada, nombre, latitud, longitud, direccion, color);
    }
}