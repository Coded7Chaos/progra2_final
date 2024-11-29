package com.transporte.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.transporte.config.DatabaseConnection;

public abstract class Ruta
{
    private int id_ruta;
    private String nombreInicio;
    private String nombreFin;
    private String horarioInicio;
    private String horarioFin;
    private int estado;
    private int tipo_transporte;
    //private MedioTransporte transporte;
    private List<Tarifa> tarifas;
    private List<Parada> paradas;

    public Ruta() {}
    public Ruta(int id_ruta, String nombreInicio, String nombreFin, String horarioInicio, String horarioFin, int estado, int tipo_transporte, List<Tarifa> tarifas, List<Parada> paradas)
    {
        this.id_ruta = id_ruta;
        this.nombreInicio = nombreInicio;
        this.nombreFin = nombreFin;
        this.horarioInicio = horarioInicio;
        this.horarioFin = horarioFin;
        this.estado = estado;
        this.tipo_transporte = tipo_transporte;
        this.tarifas = tarifas;
        this.paradas = paradas;
    }

    public int getId() { return id_ruta; }
    public void setId(int id_ruta) { this.id_ruta = id_ruta; }

    public String getNombreInicio() { return nombreInicio; }
    public void setNombreInicio(String nombreInicio) { this.nombreInicio = nombreInicio; }

    public String getNombreFin() { return nombreFin; }
    public void setNombreFin(String nombreFin) { this.nombreFin = nombreFin; }

    public String getHorarioInicio() { return horarioInicio; }
    public void setHorarioInicio(String horarioInicio) { this.horarioInicio = horarioInicio; }

    public String getHorarioFin() { return horarioFin; }
    public void setHorarioFin(String horarioFin) { this.horarioFin = horarioFin; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }

    public int getTipoTransporte() { return tipo_transporte; }
    public void setTipoTransporte(int tipo_transporte) { this.tipo_transporte = tipo_transporte; }

    public List<Tarifa> getTarifas() { return tarifas; }
    public void setTarifas(List<Tarifa> tarifas) { this.tarifas = tarifas; }

    public List<Parada> getParadas() { return paradas; }
    public void setParadas(List<Parada> paradas) { this.paradas = paradas; }

    public String getNombreTransporte()
    {
        switch (tipo_transporte) {
            case 1:
                return "Minibus";

            case 2:
                return "Pumakatari";
            
            case 3:
                return "Teleferico";
                
            default:
                return "Desconocido";
        }
    }
    public void insertParada(Parada parada) { this.paradas.add(parada); }

    public abstract String getInfo();

    public void guardarRuta() throws SQLException
    {
        try (Connection conn = DatabaseConnection.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Rutas (nombre_inicio, nombre_fin, horario_inicio, horario_fin, tipo_transporte, estado) VALUES (?, ?, ?, ?, ?, ?)"))
        {
            stmt.setString(1, nombreInicio);
            stmt.setString(2, nombreFin);
            stmt.setString(3, horarioInicio);
            stmt.setString(4, horarioFin);
            stmt.setInt(5, tipo_transporte);
            stmt.setInt(6, estado);

		    int filasAfectadas = stmt.executeUpdate();
		    if (filasAfectadas > 0)
                System.out.println("Ruta guardada exitosamente.");

        } catch (SQLException e) {
		    e.printStackTrace();
            throw e;
	    }
    }

    public void actualizarRuta(String nombreInicio, String nombreFin, String horarioInicio, String horarioFin, int tipo_transporte, int estado) throws SQLException
    {   
        try (Connection conn = DatabaseConnection.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("UPDATE Rutas SET nombre_inicio = ?, nombre_fin = ?, horario_inicio = ?, horario_fin = ?, tipo_transporte = ?, estado = ? WHERE id_ruta = ?"))
        {
            stmt.setString(1, nombreInicio);
            stmt.setString(2, nombreFin);
            stmt.setString(3, horarioInicio);
            stmt.setString(4, horarioFin);
            stmt.setInt(5, tipo_transporte);
            stmt.setInt(6, estado);
            stmt.setInt(7, id_ruta);

            int filasAfectadas = stmt.executeUpdate();
		    if (filasAfectadas > 0)
                System.out.println("Parada actualizada exitosamente.");
                
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void eliminarRuta() throws SQLException
    {
        try (Connection conn = DatabaseConnection.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Rutas WHERE id_ruta = ?"))
        {
            stmt.setInt(1, id_ruta);

            int filasAfectadas = stmt.executeUpdate();
		    if (filasAfectadas > 0)
                System.out.println("Parada actualizada exitosamente.");
                
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
