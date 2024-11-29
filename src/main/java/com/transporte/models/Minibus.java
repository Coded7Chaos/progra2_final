package com.transporte.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.transporte.config.DatabaseConnection;

public class Minibus extends Ruta
{
    private String nombreTransporte;
    private String numero;
    private List<String> carteles;

    public Minibus(String nombreInicio, String nombreFin, String horarioInicio, String horarioFin, int estado, String numero, List<String> carteles)
    {
        super(nombreInicio, nombreFin, horarioInicio, horarioFin, estado, 1, new ArrayList<>(), new ArrayList<>());
        this.nombreTransporte = "Minibus";
        this.numero = numero;
        this.carteles = carteles;
    }
    public Minibus(int id_ruta, String nombreInicio, String nombreFin, String horarioInicio, String horarioFin, int estado, List<Tarifa> tarifas, List<Parada> paradas, String numero, List<String> carteles)
    {
        super(id_ruta, nombreInicio, nombreFin, horarioInicio, horarioFin, estado, 1, tarifas, paradas);
        this.nombreTransporte = "Minibus";
        this.numero = numero;
        this.carteles = carteles;
    }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public List<String> getCarteles() { return carteles; }
    public void setCarteles(List<String> carteles) { this.carteles = carteles; }

    @Override
    public String getNombreTransporte()
    {
        return nombreTransporte;
    }
    @Override
    public String getInfo()
    {
        StringBuilder information = new StringBuilder();

        information.append("Número de ruta: ").append(numero).append("\n");
        information.append("Carteles:\n");

        for (String cartel : carteles)
            information.append("• ").append(cartel).append("\n");
        
        return information.toString();
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

            PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO Ruta_Numeros (id_ruta, numero) VALUES (?, ?)");
            stmt2.setInt(1, idRutaGenerado);
            stmt2.setString(2, numero);
            stmt2.executeUpdate();

            PreparedStatement stmt3 = conn.prepareStatement("INSERT INTO Carteles (id_ruta, nombre) VALUES (?, ?)");
            for (String cartel : carteles)
            {
                stmt3.setInt(1, idRutaGenerado);
                stmt3.setString(2, cartel);

                stmt3.executeUpdate();
            }

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