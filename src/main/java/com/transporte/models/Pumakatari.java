package com.transporte.models;

import java.util.List;

public class Pumakatari extends Ruta
{
    public Pumakatari() {}
    public Pumakatari(int id_ruta, String nombreInicio, String nombreFin, String horarioInicio, String horarioFin, int estado, List<Tarifa> tarifas, List<Parada> paradas)
    {
        super(id_ruta, nombreInicio, nombreFin, horarioInicio, horarioFin, estado, 2, tarifas, paradas);
    }

    @Override
    public String getInfo()
    {
        return "";
    }
}