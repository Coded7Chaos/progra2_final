package com.transporte.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.transporte.config.DatabaseConnection;

public class ParadaPuma extends Parada{
    private String nombre;
    private String direccion;
    private Boolean estado;
    private Boolean trayectoria;
    private int tiempoDeEspera;
    
    
    
    public ParadaPuma(double longitud, double latitud, Zona zona, String nombre, String direccion, Boolean estado,
            Boolean trayectoria, int tiempoDeEspera) {
        super(longitud, latitud, zona);
        this.nombre = nombre;
        this.direccion = direccion;
        this.estado = estado;
        this.trayectoria = trayectoria;
        this.tiempoDeEspera = tiempoDeEspera;
    }



    void habilitarParada(){

    }



    @Override
    public void guardarParada() throws SQLException {
         int generatedId=0;
         int idPuma=0;
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Paradas(id_zona, latitud, longitud) VALUES (?,?,?)",
            PreparedStatement.RETURN_GENERATED_KEYS)){
                ps.setInt(1, this.zona.getIdZona());
                ps.setDouble(2, this.latitud);
                ps.setDouble(3, this.longitud);
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
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Paradas_pumakatari(nombre, direccion, estado, trayectoria, tiempo_espera ,idParada) VALUES (?,?,?,?,?,?)",
            PreparedStatement.RETURN_GENERATED_KEYS)){
                ps.setString(1, this.nombre);
                ps.setString(2, this.direccion);
                ps.setBoolean(3, this.estado);
                ps.setBoolean(4, this.trayectoria);
                ps.setInt(5, this.tiempoDeEspera);
                ps.setInt(6, generatedId);
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        idPuma= rs.getInt(1);
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
}
