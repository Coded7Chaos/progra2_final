package com.transporte.models;

import java.util.List;

public class Pumakatari extends MedioTransporte
{
    private List<String> letrero;
    
    public Pumakatari(int id_transporte, List<Tarifa> tarifas)
    {
        super(id_transporte, "Pumakatari", tarifas);
    }

    @Override
    public String getInfoRutas()
    {
        StringBuilder info = new StringBuilder("[Pumakatari]: - Paradas: ");
        return info.toString();
    }

    @Override
    public String toString()
    {
        return "Pumakatari";
    }
}