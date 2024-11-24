package com.transporte.models;

import java.util.List;

public class Pumakatari extends MedioTransporte
{
    private List<String> paradas;

    public Pumakatari(int id, String nombre, List<String> paradas)
    {
        super(id, nombre, "Pumakatari");
        this.paradas = paradas;
    }

    @Override
    public String getInfoRutas()
    {
        StringBuilder info = new StringBuilder("[Pumakatari]: " + getNombre() + " - Paradas: ");
        for (String parada : paradas)
        {
            info.append("\n  • ").append(parada);
        }
        return info.toString();
    }
}