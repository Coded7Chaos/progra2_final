package com.transporte.models;

import java.util.List;

public class Teleferico extends MedioTransporte
{

    public Teleferico(int id, String nombre)
    {
        super(id, nombre, "Teleferico");
    }

    @Override
    public String getInfoRutas()
    {
        StringBuilder info = new StringBuilder("[Teleferico]: " + getNombre() + " - Estaciones: ");
        return info.toString();
    }
}