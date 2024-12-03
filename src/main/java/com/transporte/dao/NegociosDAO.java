package com.transporte.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.transporte.config.DatabaseConnection;

public class NegociosDAO
{
    public static List<String> obtenerNombresNegociosPorEstacion(int idEstacion) throws SQLException
    {
        List<String> negocios = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT n.nombre FROM Negocios_teleferico AS nt, Negocios AS n WHERE nt.id_teleferico = ? AND nt.id_negocio = n.id_negocio"))
        {
            ps.setInt(1, idEstacion);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                String nombre = rs.getString("nombre");

                negocios.add(nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return negocios;
    }

    public static List<Integer> obtenerNegociosPorEstacion(int idEstacion) throws SQLException
    {
        List<Integer> negocios = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT n.id_negocio FROM Negocios_teleferico AS nt, Negocios AS n WHERE nt.id_teleferico = ? AND nt.id_negocio = n.id_negocio"))
        {
            ps.setInt(1, idEstacion);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Integer id_negocio = rs.getInt("id_negocio");

                negocios.add(id_negocio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return negocios;
    }
}
