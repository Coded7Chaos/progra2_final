package com.transporte.dao;
 
import java.util.List;

import com.transporte.models.Parada;

public interface ParadaDAOInterface
{
    // Método para obtener todas las paradas
    List<Parada> obtenerParadas();

    // Método para obtener las paradas pertenecientes a una ruta
    List<Parada> obtenerParadaPorId(int rutaId);
}