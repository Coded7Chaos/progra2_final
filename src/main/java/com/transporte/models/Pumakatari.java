package com.transporte.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.transporte.config.DatabaseConnection;

public class Pumakatari extends Ruta
{
    private String nombreTransporte;

    public Pumakatari() {}
    public Pumakatari(String nombreInicio, String nombreFin, String horarioInicio, String horarioFin, int estado)
    {
        super(nombreInicio, nombreFin, horarioInicio, horarioFin, estado, 2, new ArrayList<>(), new ArrayList<>());
    }
    public Pumakatari(int id_ruta, String nombreInicio, String nombreFin, String horarioInicio, String horarioFin, int estado, List<Tarifa> tarifas, List<Parada> paradas)
    {
        super(id_ruta, nombreInicio, nombreFin, horarioInicio, horarioFin, estado, 2, tarifas, paradas);
        this.nombreTransporte = "PumaKatari";
    }

    @Override
    public String getNombreTransporte()
    {
        return nombreTransporte;
    }
    @Override
    public String getInfo()
    {
        return "";
    }

    @Override
    public void guardarRuta() throws SQLException
    {
        try (Connection conn = DatabaseConnection.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Rutas (nombre_inicio, nombre_fin, horario_inicio, horario_fin, tipo_transporte, estado) VALUES (?, ?, ?, ?, ?, ?)"))
        {
            stmt.setString(1, getNombreInicio());
            stmt.setString(2, getNombreFin());
            stmt.setString(3, getHorarioInicio());
            stmt.setString(4, getHorarioFin());
            stmt.setInt(5, getTipoTransporte());
            stmt.setInt(6, getEstado());

		    int filasAfectadas = stmt.executeUpdate();
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