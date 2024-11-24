package com.transporte.gui;

import com.transporte.models.Parada;
import com.transporte.dao.ParadaDAO;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.*;
import org.jxmapviewer.OSMTileFactoryInfo;

import javax.swing.*;
import java.util.List;

public class TransporteApp
{
    private JFrame frame;
    private JXMapViewer mapViewer;

    public TransporteApp()
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
        ParadaDAO dao = new ParadaDAO();
        List<Parada> paradas = dao.obtenerParadas();

        for (Parada i : paradas)
        {
            System.out.printf("[%d]: %s: (%f, %f)\n", i.getIdParada(), i.getNombre(), i.getLatitud(), i.getLongitud());
        }
    }
}
