package com.transporte.models;

import java.util.List;

public class Ruta
{
    private int id;
    private String nombre;
    private MedioTransporte transporte;
    private List<Parada> paradas;

    public Ruta(int id, String nombre, MedioTransporte transporte, List<Parada> paradas)
    {
        this.id = id;
        this.nombre = nombre;
        this.transporte = transporte;
        this.paradas = paradas;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public MedioTransporte getTransporte() { return transporte; }
    public void setTransporte(MedioTransporte transporte) { this.transporte = transporte; }

    public List<Parada> getParadas() { return paradas; }
    public void setParadas(List<Parada> paradas) { this.paradas = paradas; }
}
