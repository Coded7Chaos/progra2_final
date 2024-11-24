package com.transporte.models;

import java.util.List;

public class Teleferico extends MedioTransporte
{
    private List<String> estaciones;

    public Teleferico(int id, String nombre, List<String> estaciones)
    {
        super(id, nombre, "Teleferico");
        this.estaciones = estaciones;
    }

    @Override
    public String getInfoRutas()
    {
        StringBuilder info = new StringBuilder("[Teleferico]: " + getNombre() + " - Estaciones: ");
        for (String estacion : estaciones)
        {
            info.append("\n  • ").append(estacion);
        }
        return info.toString();
    }
}