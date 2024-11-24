package com.transporte;

import java.util.*;

public class Ruta {
	    private int idRuta;            // Identificador único de la ruta
	    private String nombre;         // Nombre de la ruta
	    private List<Parada> paradas;  // Lista de paradas (relación con la clase Parada)
	    private String horarios;      // Horarios en los cuales la ruta funciona
	    private boolean estado;
	    
	    
	    
	    
	    
		public Ruta(int idRuta, String nombre, List<Parada> paradas, String horarios, boolean estado) {
			super();
			this.idRuta = idRuta;
			this.nombre = nombre;
			this.paradas = paradas;
			this.horarios = horarios;
			this.estado = estado;
		}
		public int getIdRuta() {
			return idRuta;
		}
		public void setIdRuta(int idRuta) {
			this.idRuta = idRuta;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public List<Parada> getParadas() {
			return paradas;
		}
		public void setParadas(List<Parada> paradas) {
			this.paradas = paradas;
		}
		public String getHorarios() {
			return horarios;
		}
		public void setHorarios(String horarios) {
			this.horarios = horarios;
		}
		public boolean isEstado() {
			return estado;
		}
		public void setEstado(boolean estado) {
			this.estado = estado;
		}
		@Override
		public String toString() {
			return "Ruta [idRuta=" + idRuta + ", nombre=" + nombre + ", paradas=" + paradas + ", horarios=" + horarios
					+ ", estado=" + estado + "]";
		}
	    
		
		
	    
}
