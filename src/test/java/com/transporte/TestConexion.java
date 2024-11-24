package com.transporte;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class TestConexion
{
	
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://dababase-de-prueba.cj4ewia8uj02.us-east-2.rds.amazonaws.com:3306/prueba";
    private static String usuario = "admin";
    private static String password = "OsI16fC6vLAj";
    
    Connection conn = null;
    
    public Connection getConnection() {
    try {
        Class.forName(driver); 
        conn = DriverManager.getConnection(url, usuario, password);
        if (conn != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        }
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        System.out.println("Ocurrio un error al conectar a la db.");
    }
    return conn;
}
    

    public void closeConnection()
    {
        try {
             conn.close();
             System.out.println("se cerro la conexion exitosamente" );
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Error al intentar cerrar la conexion");
        }
    }
}
