package com.transporte.models;

import java.util.List;

public class Ruta
{
    private int id;
    private String nombre;
    private List<Punto> puntos;

    public Ruta(int id, String nombre, List<Punto> puntos) {
        this.id = id;
        this.nombre = nombre;
        this.puntos = puntos;
    }

    public int getId() { return id; }

    public String getNombre() { return nombre; }

    public List<Punto> getPuntos() { return puntos; }
}
