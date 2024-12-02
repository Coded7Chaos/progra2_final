package com.transporte.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.transporte.config.DatabaseConnection;

public class Teleferico extends Ruta
{
    private String nombreTransporte;
    private String linea;

    public Teleferico(int id_ruta, String linea)
    {
        super(id_ruta);
        this.linea = linea;
    }


    public Teleferico(String nombreInicio, String nombreFin, String horarioInicio, String horarioFin, int estado, String linea)
    {
        super(nombreInicio, nombreFin, horarioInicio, horarioFin, estado, 2, new ArrayList<>(), new ArrayList<>());
        this.nombreTransporte = "Teleferico";
        this.linea = linea;
    }

    public Teleferico(int id_ruta, String nombreInicio, String nombreFin, String horarioInicio, String horarioFin, int estado, List<Tarifa> tarifas, List<Parada> paradas, String linea)
    {
        super(id_ruta, nombreInicio, nombreFin, horarioInicio, horarioFin, estado, 2, tarifas, paradas);
        this.nombreTransporte = "Teleferico";
        this.linea = linea;
    }

    public String getLinea() { return linea; }
    public void setLinea(String linea) { this.linea = linea; }

    @Override
    public String getNombreTransporte()
    {
        return nombreTransporte;
    }
    @Override
    public String getInfo()
    {
        return String.format("Linea: %s\n", linea);
    }

    @Override
    public void guardarRuta() throws SQLException
    {
        try (Connection conn = DatabaseConnection.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Rutas (nombre_inicio, nombre_fin, horario_inicio, horario_fin, tipo_transporte, estado) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS))
        {
            stmt.setString(1, getNombreInicio());
            stmt.setString(2, getNombreFin());
            stmt.setString(3, getHorarioInicio());
            stmt.setString(4, getHorarioFin());
            stmt.setInt(5, getTipoTransporte());
            stmt.setInt(6, getEstado());

		    int filasAfectadas = stmt.executeUpdate();

            int idRutaGenerado = -1;
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next())
                idRutaGenerado = generatedKeys.getInt(1);

            setId(idRutaGenerado);

            PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO Lineas (id_ruta, nombre) VALUES (?, ?)");
            stmt2.setInt(1, idRutaGenerado);
            stmt2.setString(2, linea);
            stmt2.executeUpdate();

		    if (filasAfectadas > 0)
                System.out.println("Ruta guardada exitosamente.");

        } catch (SQLException e) {
		    e.printStackTrace();
            throw e;
	    }
    }

    @Override
    public void actualizarRuta() throws SQLException
    {   
        try (Connection conn = DatabaseConnection.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("UPDATE Rutas SET nombre_inicio = ?, nombre_fin = ?, horario_inicio = ?, horario_fin = ?, tipo_transporte = ?, estado = ? WHERE id_ruta = ?"))
        {
            stmt.setString(1, getNombreInicio());
            stmt.setString(2, getNombreFin());
            stmt.setString(3, getHorarioInicio());
            stmt.setString(4, getHorarioFin());
            stmt.setInt(5, getTipoTransporte());
            stmt.setInt(6, getEstado());
            stmt.setInt(7, getId());

            int filasAfectadas = stmt.executeUpdate();
		    if (filasAfectadas > 0)
                System.out.println("Ruta actualizada exitosamente.");
                
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void eliminarRuta() throws SQLException
    {
        try (Connection conn = DatabaseConnection.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Rutas WHERE id_ruta = ?"))
        {
            stmt.setInt(1, getId());

            int filasAfectadas = stmt.executeUpdate();
		    if (filasAfectadas > 0)
                System.out.println("Ruta eliminada exitosamente.");
                
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}