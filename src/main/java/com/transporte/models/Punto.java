package com.transporte.models;

public class Punto
{
    private double latitud;
    private double longitud;

    public Punto(double latitud, double longitud)
    {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public double getLatitud() { return latitud; }

    public double getLongitud() { return longitud; }
}
