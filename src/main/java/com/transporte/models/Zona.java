package com.transporte.models;

public class Zona
{
    int idZona;
    String zona;

	public Zona(int idZona, String zona)
	{
		this.idZona = idZona;
		this.zona = zona;
	}
	
	public int getIdZona() { return idZona; }
	public void setIdZona(int idZona) { this.idZona = idZona; }

	public String getZona() { return zona; }
	public void setZona(String zona) { this.zona = zona; }

	@Override
    public String toString()
	{
        return zona;  // Solo el nombre es lo que se mostrará
    }
}