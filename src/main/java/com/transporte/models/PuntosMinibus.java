package com.transporte.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.transporte.config.DatabaseConnection;

public class PuntosMinibus extends Parada{
    private String color;
    private Boolean trayectoria;

    @Override
    public void guardarParada() throws SQLException {
         int generatedId=0;
         int idMini=0;
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Paradas(id_zona, latitud, longitud, color) VALUES (?,?,?,?)",
            PreparedStatement.RETURN_GENERATED_KEYS)){
                ps.setInt(1, this.zona.getIdZona());
                ps.setDouble(2, this.latitud);
                ps.setDouble(3, this.longitud);
                ps.setString(4, this.color);
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                    }
                }
            } catch(SQLException sqle){
                sqle.printStackTrace();
            }
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Puntos_minibus(trayectoria, idParada) VALUES (?,?)",
            PreparedStatement.RETURN_GENERATED_KEYS)){
                ps.setBoolean(1, this.trayectoria);
                ps.setInt(2, generatedId);
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        idMini= rs.getInt(1);
                        }
                    }
            } catch(SQLException sqle){
                sqle.printStackTrace();
            }
       
    }

    @Override
    public void eliminarParada() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarParada'");
    }

    public PuntosMinibus(double longitud, double latitud, Zona zona, String color, Boolean trayectoria) {
        super(longitud, latitud, zona);
        this.color = color;
        this.trayectoria = trayectoria;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getTrayectoria() {
        return trayectoria;
    }

    public void setTrayectoria(Boolean trayectoria) {
        this.trayectoria = trayectoria;
    }

}
