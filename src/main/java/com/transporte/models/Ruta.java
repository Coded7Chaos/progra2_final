package com.transporte.models;

import java.util.List;

public class Ruta
{
    private int id;
    private String nombreInicio;
    private String nombreFin;
    private String horarioInicio;
    private String horarioFin;
    private int estado;
    private MedioTransporte transporte;
    private List<Parada> paradas;

    public Ruta(int id, String nombreInicio, String nombreFin, String horarioInicio, String horarioFin, int estado, MedioTransporte transporte, List<Parada> paradas)
    {
        this.id = id;
        this.nombreInicio = nombreInicio;
        this.nombreFin = nombreFin;
        this.horarioInicio = horarioInicio;
        this.horarioFin = horarioFin;
        this.estado = estado;
        this.transporte = transporte;
        this.paradas = paradas;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreInicio() { return nombreInicio; }
    public void setNombreInicio(String nombreInicio) { this.nombreInicio = nombreInicio; }

    public String getNombreFin() { return nombreFin; }
    public void setNombreFin(String nombreFin) { this.nombreFin = nombreFin; }

    public String getHorarioInicio() { return horarioInicio; }
    public void setHorarioInicio(String horarioInicio) { this.horarioInicio = horarioInicio; }

    public String getHorarioFin() { return horarioFin; }
    public void setHorarioFin(String horarioFin) { this.horarioFin = horarioFin; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }

    public MedioTransporte getTransporte() { return transporte; }
    public void setTransporte(MedioTransporte transporte) { this.transporte = transporte; }

    public List<Parada> getParadas() { return paradas; }
    public void setParadas(List<Parada> paradas) { this.paradas = paradas; }
}
