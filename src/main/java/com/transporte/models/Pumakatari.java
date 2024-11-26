package com.transporte.models;

import java.util.List;

public class Pumakatari extends MedioTransporte
{

    public Pumakatari(int id, String nombre)
    {
        super(id, nombre, "Pumakatari");
    }

    @Override
    public String getInfoRutas()
    {
        StringBuilder info = new StringBuilder("[Pumakatari]: " + getNombre() + " - Paradas: ");
        return info.toString();
    }

    @Override
    public String toString()
    {
        return "Pumakatari";
    }
}