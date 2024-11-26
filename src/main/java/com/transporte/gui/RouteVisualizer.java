package com.transporte.gui;

import com.transporte.models.Parada;
import com.transporte.models.Ruta;
import com.transporte.dao.RutaDAO;
import com.transporte.dao.ParadaDAO;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.*;
import org.jxmapviewer.OSMTileFactoryInfo;

import javax.swing.*;
import java.util.List;

public class RouteVisualizer
{
    private JFrame frame;
    private JXMapViewer mapViewer;

    public RouteVisualizer()
    {
        frame = new JFrame("Mapa de Transporte Público");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        mapViewer = new JXMapViewer();
        mapViewer.setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo()));

        mapViewer.setZoom(4);
        mapViewer.setAddressLocation(new GeoPosition(-16.51, -68.11));

        cargarDatos();

        frame.add(mapViewer);
        frame.setVisible(true);
    }

    private void cargarDatos()
    {
        /*ParadaDAO dao = new ParadaDAO();
        List<Parada> paradas = dao.obtenerParadas();

        for (Parada i : paradas)
        {
            System.out.printf("[%d]: %s: (%f, %f)\n", i.getIdParada(), i.getNombre(), i.getLatitud(), i.getLongitud());
        }*/

        RutaDAO rdao = new RutaDAO();
        Ruta ruta = rdao.obtenerRuta(1);

        System.out.printf("[%d]: %s - %s | %s - %s | %d | %s\n", ruta.getId(), ruta.getNombreInicio(), ruta.getNombreFin(), ruta.getHorarioInicio(), ruta.getHorarioFin(), ruta.getEstado(), ruta.getTransporte());
        for (Parada parada : ruta.getParadas())
        {
            System.out.printf("   - %s\n", parada);
        }
    }
}
