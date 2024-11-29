package com.transporte.gui;

import com.transporte.dao.RutaDAO;
import com.transporte.models.Ruta;
import com.transporte.models.Parada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;
import org.jxmapviewer.viewer.DefaultWaypoint;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainWindow extends JFrame
{
    private JComboBox<String> comboBoxRutas;
    private JXMapViewer mapViewer;
    private JButton btnAgregar, btnModificar, btnEliminar, btnMasInformacion;
    private List<Ruta> rutas;

    public MainWindow()
    {
        setTitle("Visualizar Rutas y Paradas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        setLayout(new BorderLayout(10, 10));

        try
        {
            this.rutas = RutaDAO.obtenerRutas();
        } catch (SQLException e) {
            System.out.println("Error al obtener las rutas: " + e.getMessage());
        }

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBackground(new Color(240, 240, 240));
        add(topPanel, BorderLayout.NORTH);

        JLabel lblSeleccionarRuta = new JLabel("Seleccionar ruta:");
        lblSeleccionarRuta.setFont(new Font("Arial", Font.PLAIN, 14));
        topPanel.add(lblSeleccionarRuta);

        String[] nombresRutas = new String[rutas.size()];
        for (int i = 0; i < rutas.size(); i++)
            nombresRutas[i] = String.format("%s - %s", rutas.get(i).getNombreInicio(), rutas.get(i).getNombreFin());

        comboBoxRutas = new JComboBox<>(nombresRutas);
        comboBoxRutas.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBoxRutas.setPreferredSize(new Dimension(200, 30));
        topPanel.add(comboBoxRutas);

        btnMasInformacion = new JButton("Más información");
        btnMasInformacion.setFont(new Font("Arial", Font.PLAIN, 14));
        btnMasInformacion.setBackground(new Color(0, 102, 204));
        btnMasInformacion.setForeground(Color.WHITE);
        btnMasInformacion.setFocusPainted(false);
        topPanel.add(btnMasInformacion);

        mapViewer = new JXMapViewer();
        mapViewer.setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo()));
        mapViewer.setZoom(6);
        mapViewer.setAddressLocation(new GeoPosition(-16.51, -68.11));
        add(mapViewer, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(new Color(240, 240, 240));
        add(bottomPanel, BorderLayout.SOUTH);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnAgregar.setBackground(new Color(0, 153, 76));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false);
        bottomPanel.add(btnAgregar);

        /*btnModificar = new JButton("Modificar");
        btnModificar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnModificar.setBackground(new Color(255, 153, 51));
        btnModificar.setForeground(Color.WHITE);
        btnModificar.setFocusPainted(false);
        bottomPanel.add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnEliminar.setBackground(new Color(204, 51, 51));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        bottomPanel.add(btnEliminar);*/

        comboBoxRutas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboBoxRutas.getSelectedIndex();
                updateWaypoints(selectedIndex);
            }
        });

        btnMasInformacion.addActionListener(e -> {
            InformationWindow np = new InformationWindow(rutas.get(comboBoxRutas.getSelectedIndex()));
            np.setVisible(true);
        });

        btnAgregar.addActionListener(e -> {
			CrearParada np = new CrearParada(rutas);
            np.setVisible(true);
		});

        updateWaypoints(0);
    }

    private void updateWaypoints(int index)
    {
        Set<Waypoint> waypoints = new HashSet<>();

        for (Parada parada : rutas.get(index).getParadas())
            waypoints.add(new DefaultWaypoint(new GeoPosition(parada.getLatitud(), parada.getLongitud())));

        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(waypoints);

        mapViewer.setOverlayPainter(waypointPainter);
    }
}
