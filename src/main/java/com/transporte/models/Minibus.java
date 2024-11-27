package com.transporte.models;

import java.util.List;

public class Minibus extends MedioTransporte
{
    private String numero;
    private List<String> carteles;

    public Minibus(int id_transporte, String numero, List<String> carteles, List<Tarifa> tarifas)
    {
        super(id_transporte, "Minibus", tarifas);
        this.numero = numero;
        this.carteles = carteles;
    }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public List<String> getCarteles() { return carteles; }
    public void setCarteles(List<String> carteles) { this.carteles = carteles; }

    @Override
    public String getInfoRutas()
    {
        return "[Minibus]";
    }
}