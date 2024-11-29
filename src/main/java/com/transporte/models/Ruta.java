package com.transporte.models;

import java.sql.SQLException;
import java.util.List;

public abstract class Ruta
{
    private int id_ruta;
    private String nombreInicio;
    private String nombreFin;
    private String horarioInicio;
    private String horarioFin;
    private int estado;
    private int tipo_transporte;
    //private MedioTransporte transporte;
    private List<Tarifa> tarifas;
    private List<Parada> paradas;

    public Ruta() {}
    public Ruta(String nombreInicio, String nombreFin, String horarioInicio, String horarioFin, int estado, int tipo_transporte, List<Tarifa> tarifas, List<Parada> paradas)
    {
        this.nombreInicio = nombreInicio;
        this.nombreFin = nombreFin;
        this.horarioInicio = horarioInicio;
        this.horarioFin = horarioFin;
        this.estado = estado;
        this.tipo_transporte = tipo_transporte;
        this.tarifas = tarifas;
        this.paradas = paradas;
    }
    public Ruta(int id_ruta, String nombreInicio, String nombreFin, String horarioInicio, String horarioFin, int estado, int tipo_transporte, List<Tarifa> tarifas, List<Parada> paradas)
    {
        this.id_ruta = id_ruta;
        this.nombreInicio = nombreInicio;
        this.nombreFin = nombreFin;
        this.horarioInicio = horarioInicio;
        this.horarioFin = horarioFin;
        this.estado = estado;
        this.tipo_transporte = tipo_transporte;
        this.tarifas = tarifas;
        this.paradas = paradas;
    }

    public int getId() { return id_ruta; }
    public void setId(int id_ruta) { this.id_ruta = id_ruta; }

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

    public int getTipoTransporte() { return tipo_transporte; }
    public void setTipoTransporte(int tipo_transporte) { this.tipo_transporte = tipo_transporte; }

    public List<Tarifa> getTarifas() { return tarifas; }
    public void setTarifas(List<Tarifa> tarifas) { this.tarifas = tarifas; }

    public List<Parada> getParadas() { return paradas; }
    public void setParadas(List<Parada> paradas) { this.paradas = paradas; }

    public void insertParada(Parada parada) { this.paradas.add(parada); }

    public abstract String getNombreTransporte();
    public abstract String getInfo();

    public abstract void guardarRuta() throws SQLException;
    public abstract void actualizarRuta() throws SQLException;
    public abstract void eliminarRuta() throws SQLException;
}
