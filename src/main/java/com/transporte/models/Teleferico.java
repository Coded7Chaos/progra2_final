package com.transporte.models;

import java.util.List;

public class Teleferico extends Ruta
{
    private String linea;

    public Teleferico(int id_ruta, String nombreInicio, String nombreFin, String horarioInicio, String horarioFin, int estado, List<Tarifa> tarifas, List<Parada> paradas, String linea)
    {
        super(id_ruta, nombreInicio, nombreFin, horarioInicio, horarioFin, estado, 2, tarifas, paradas);
        this.linea = linea;
    }

    public String getLinea() { return linea; }
    public void setLinea(String linea) { this.linea = linea; }

    @Override
    public String getInfo()
    {
        return String.format("Linea: %s\n", linea);
    }
}