package com.transporte.models;

import com.transporte.utils.Zona;

public class Parada
{
	private int idParada;
	private String nombre;	
	private double longitud;
	private double latitud;
    private String direccion;
	private String color;
	private com.transporte.models.Zona zona;
	private boolean estado;


	public Parada(String nombre, double longitud, double latitud, String direccion, com.transporte.models.Zona zona)
    {
        this.nombre     = nombre;
        this.longitud   = longitud;
        this.latitud    = latitud;
        this.direccion  = direccion;
        this.zona       = zona;
    }

    public Parada(int id, String nombre, double longitud, double latitud, String direccion, String color ,com.transporte.models.Zona zona, boolean estado)
    {
        this.idParada   = id;
        this.nombre     = nombre;
        this.longitud   = longitud;
        this.latitud    = latitud;
        this.direccion  = direccion;
        this.color      = color;
        this.zona       = zona;
        this.estado     = estado;
		this.color      = color != null ? color : "#FFFF00"; // Color amarillo por defecto
    }

	public int getIdParada() { return idParada; }
    public void setIdParada(int idParada) { this.idParada = idParada; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getLongitud() { return longitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }

    public double getLatitud() { return latitud; }
    public void setLatitud(double latitud) { this.latitud = latitud; }

	public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public com.transporte.models.Zona getZona() { return zona; }
    public void setZona(com.transporte.models.Zona zona) { this.zona = zona; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    public int getZonaId(Zona zona)
    {
        switch (zona)
        {
            case SAN_PEDRO:
                return 1;
        
            case ACHUMANI:
                return 2;
                
            case SAN_JORGE:
                return 3;
            
            case SOPOCACHI:
                return 4;
                
            case CENTRO:
                return 5;
            
            case MAX_PAREDES:
                return 6;

            default:
                return 0;
        }
    }


}
