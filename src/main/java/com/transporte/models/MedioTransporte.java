package com.transporte.models;

import java.util.List;

public abstract class MedioTransporte
{
    private int id_transporte;
    private String nombreMedio;
    private List<Tarifa> tarifas;

    public MedioTransporte(int id_transporte, String nombreMedio, List<Tarifa> tarifas)
    {
        this.id_transporte = id_transporte;
        this.nombreMedio = nombreMedio;
        this.tarifas = tarifas;
    }

    public int getIdTransporte() { return id_transporte; }
    public void setIdTransporte(int id_transporte) { this.id_transporte = id_transporte; }

    public String getNombreMedio() { return nombreMedio; }
    public void setNombreMedio(String nombreMedio) { this.nombreMedio = nombreMedio; }

    public List<Tarifa> getTarifas() { return tarifas; }
    public void setTarifas(List<Tarifa> tarifas) { this.tarifas = tarifas; }

    public abstract String getInfoRutas();
}
