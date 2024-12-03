package com.transporte.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.transporte.config.DatabaseConnection;

public class EstacionTeleferico extends Parada{
    private String nombre;
    private String direccion;
    private Boolean parqueo;
    private List<Integer> negocios;
    private String color;





        
    public EstacionTeleferico(double longitud, double latitud, Zona zona, int idRuta, String nombre, String direccion,
            Boolean parqueo, List<Integer> negocios, String color) {
        super(longitud, latitud, zona, idRuta);
        this.nombre = nombre;
        this.direccion = direccion;
        this.parqueo = parqueo;
        this.negocios = negocios;
        this.color = color;
    }

    @Override
    public void guardarParada() throws SQLException {
        int generatedId=0;
        int id_teleferico=0;
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Paradas(id_zona, latitud, longitud, color) VALUES (?,?,?,?,?)",
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
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Estaciones_teleferico(nombre, direccion, parqueo, idParada) VALUES (?,?,?,?)",
            PreparedStatement.RETURN_GENERATED_KEYS)){
                ps.setString(1, this.nombre);
                ps.setString(2, this.direccion);
                ps.setBoolean(3, this.parqueo);
                ps.setInt(4, generatedId);
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        id_teleferico = rs.getInt(1);
                        }
                    }
            } catch(SQLException sqle){
                sqle.printStackTrace();
            }
            for(int i=0; i<this.negocios.size(); i++){
          
            try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Negocios_teleferico(id_negocio, id_teleferico) VALUES (?,?)")){
                ps.setInt(1, i);
                ps.setInt(2, id_teleferico);
                ps.executeUpdate();
            } catch(SQLException sqle){
                sqle.printStackTrace();
            }
   
        }
    
    
        }

    @Override
    public void eliminarParada() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarParada'");
    }

    /* 
    public EstacionTeleferico(double longitud, double latitud, Zona zona, String nombre, String direccion,
            Boolean parqueo, List<Integer> negocios) {
        super(longitud, latitud, zona, idRuta);
        this.nombre = nombre;
        this.direccion = direccion;
        this.parqueo = parqueo;
        this.negocios = negocios;
    }*/
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Boolean getParqueo() {
        return parqueo;
    }
    public void setParqueo(Boolean parqueo) {
        this.parqueo = parqueo;
    }
    public List<Integer> getNegocios() {
        return negocios;
    }
    public void setNegocios(List<Integer> negocios) {
        this.negocios = negocios;
    }
    

}
