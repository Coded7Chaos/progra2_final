package com.transporte.models;

public class Zona
{
    int idZona;
    String nombre;

	public Zona(int idZona, String nombre)
	{
		this.idZona = idZona;
		this.nombre = nombre;
	}
	
	public int getIdZona() { return idZona; }
	public void setIdZona(int idZona) { this.idZona = idZona; }

	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }

	@Override
    public String toString()
	{
        return nombre;
    }
}