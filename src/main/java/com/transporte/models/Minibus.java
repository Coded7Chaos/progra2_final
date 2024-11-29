package com.transporte.models;

import java.util.List;

public class Minibus extends Ruta
{
    private String numero;
    private List<String> carteles;

    public Minibus(int id_ruta, String nombreInicio, String nombreFin, String horarioInicio, String horarioFin, int estado, List<Tarifa> tarifas, List<Parada> paradas, String numero, List<String> carteles)
    {
        super(id_ruta, nombreInicio, nombreFin, horarioInicio, horarioFin, estado, 1, tarifas, paradas);
        this.numero = numero;
        this.carteles = carteles;
    }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public List<String> getCarteles() { return carteles; }
    public void setCarteles(List<String> carteles) { this.carteles = carteles; }

    @Override
    public String getInfo()
    {
        StringBuilder information = new StringBuilder();

        information.append("Número de ruta: ").append(numero).append("\n");
        information.append("Carteles:\n");

        for (String cartel : carteles)
            information.append("• ").append(cartel).append("\n");
        
        return information.toString();
    }
}