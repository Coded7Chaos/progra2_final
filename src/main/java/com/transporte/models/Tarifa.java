package com.transporte.models;

public class Tarifa
{
    private int id_tarifa;
    private int id_ruta;
    private String nombre;
    private double costo;

    public Tarifa(int id_tarifa, int id_ruta, String nombre, double costo)
    {
        this.id_tarifa = id_tarifa;
        this.id_ruta = id_ruta;
        this.nombre = nombre;
        this.costo = costo;
    }

    public int getIdTarifa() { return id_tarifa; }
    public void setIdTarifa(int id_tarifa) { this.id_tarifa = id_tarifa; }

    public int getIdRuta() { return id_ruta; }
    public void setIdRuta(int id_ruta) { this.id_ruta = id_ruta; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getCosto() { return costo; }
    public void setCost(double costo) { this.costo = costo; }
}
