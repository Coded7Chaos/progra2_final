package com.transporte.models;

public abstract class MedioTransporte
{
    private int id;
    private String nombre;
    private String nombreMedio;

    public MedioTransporte(int id, String nombre, String nombreMedio)
    {
        this.id = id;
        this.nombre = nombre;
        this.nombreMedio = nombreMedio;
    }

    public int getId() { return id; }

    public String getNombre() { return nombre; }

    public String getNombreMedio() { return nombreMedio; }

    public abstract String getInfoRutas();
}
