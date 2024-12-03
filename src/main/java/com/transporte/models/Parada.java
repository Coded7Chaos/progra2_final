package com.transporte.models;

import java.sql.SQLException;
public abstract class Parada
{
	protected int idParada;
	protected double longitud;
	protected double latitud;
	protected Zona zona;
    protected int idRuta;




	public int getIdParada() { return idParada; }
    public void setIdParada(int idParada) { this.idParada = idParada; }


    public double getLongitud() { return longitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }

    public double getLatitud() { return latitud; }
    public void setLatitud(double latitud) { this.latitud = latitud; }

    
    public Zona getZona() { return zona; }
    public void setZona(Zona zona) { this.zona = zona; }
    

    public abstract void guardarParada() throws SQLException;
     

    public abstract void eliminarParada() throws SQLException;

    @Override
    public String toString() {
        return "Parada [idParada=" + idParada + ", longitud=" + longitud + ", latitud=" + latitud
                + ", zona=" + zona + "]";
    }

    public Parada(double longitud, double latitud, Zona zona, int idRuta) {
        this.longitud = longitud;
        this.latitud = latitud;
        this.zona = zona;
        this.idRuta = idRuta;
    }
    public int getIdRuta() {
        return idRuta;
    }
    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

}