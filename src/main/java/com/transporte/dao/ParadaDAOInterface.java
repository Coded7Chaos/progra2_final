package com.transporte.dao;
 
import java.util.List;

import com.transporte.models.Parada;

public interface ParadaDAOInterface
{
    // Método para guardar una nueva parada en la base de datos
    void guardarParada(Parada parada);

    // Método para obtener todas las paradas
    List<Parada> obtenerParadas();

    // Método para obtener las paradas pertenecientes a una ruta
    List<Parada> obtenerParadaPorId(int rutaId);

    // Método para actualizar una parada existente
    void actualizarParada(Parada parada);

    // Método para eliminar una parada por su ID
    void eliminarParada(int idParada);
}