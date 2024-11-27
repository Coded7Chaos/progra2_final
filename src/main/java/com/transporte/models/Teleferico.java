package com.transporte.models;

import java.util.List;
import java.util.ArrayList;

public class Teleferico extends MedioTransporte
{

    public Teleferico(int id, String nombre)
    {
        super(id, "Teleferico", new ArrayList<>());
    }

    @Override
    public String getInfoRutas()
    {
        StringBuilder info = new StringBuilder("[Teleferico]: - Estaciones: ");
        return info.toString();
    }
}