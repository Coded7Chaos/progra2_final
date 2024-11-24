package com.transporte.models;

public class Minibus extends MedioTransporte
{
    public Minibus(int id, String nombre)
    {
        super(id, nombre, "Minibus");
    }

    /*
    public List<String> cartelitos(Parada ubicacion)
    {
		List<String> cartelitos = new ArrayList<>();
		return cartelitos;
	}
    */
    @Override
    public String getInfoRutas()
    {
        return "[Minibus]: " + getNombre() + " - Sin parada fija.";
    }
}