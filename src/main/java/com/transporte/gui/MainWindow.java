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
    private JComboBox<String> comboBoxTransportes;
    private JComboBox<String> comboBoxRutas;
    private JXMapViewer mapViewer;
    private JButton btnAgregar, btnCrear, btnMasInformacion;
    private List<Ruta> rutas;
    private int tipo_transporte;

    public MainWindow()
    {
        setTitle("Visualizar Rutas y Paradas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBackground(new Color(240, 240, 240));
        add(topPanel, BorderLayout.NORTH);

        JLabel lblSeleccionarTransporte = new JLabel("Seleccionar transporte:");
        lblSeleccionarTransporte.setFont(new Font("Arial", Font.PLAIN, 14));
        topPanel.add(lblSeleccionarTransporte);

        String[] transportes = {"Minibus", "Pumakatari", "Teleférico"};
        comboBoxTransportes = new JComboBox<>(transportes);
        comboBoxTransportes.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBoxTransportes.setPreferredSize(new Dimension(150, 30));
        topPanel.add(comboBoxTransportes);

        JLabel lblSeleccionarRuta = new JLabel("Seleccionar ruta:");
        lblSeleccionarRuta.setFont(new Font("Arial", Font.PLAIN, 14));
        topPanel.add(lblSeleccionarRuta);

        comboBoxRutas = new JComboBox<>();
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

        btnCrear = new JButton("Crear Ruta");
        btnCrear.setFont(new Font("Arial", Font.PLAIN, 14));
        btnCrear.setBackground(new Color(255, 153, 51));
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setFocusPainted(false);
        bottomPanel.add(btnCrear);

        btnAgregar = new JButton("Agregar Parada");
        btnAgregar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnAgregar.setBackground(new Color(0, 153, 76));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false);
        bottomPanel.add(btnAgregar);

        comboBoxTransportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipo_transporte = comboBoxTransportes.getSelectedIndex() + 1;
                actualizarRutas(tipo_transporte);
            }
        });

        comboBoxRutas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboBoxRutas.getSelectedIndex();
                if (selectedIndex >= 0) {
                    updateWaypoints(selectedIndex);
                }
            }
        });

        btnMasInformacion.addActionListener(e -> {
            int selectedIndex = comboBoxRutas.getSelectedIndex();
            if (selectedIndex >= 0)
            {
                InformationWindow np = new InformationWindow(rutas.get(selectedIndex));
                np.setVisible(true);
            }
        });

        btnCrear.addActionListener(e -> {
            CrearRuta np = new CrearRuta(rutas, comboBoxRutas);
            np.setVisible(true);
        });

        btnAgregar.addActionListener(e -> {
            CrearParada2 np = new CrearParada2();
            np.setVisible(true);
        });

        actualizarRutas(1);
    }

    private void actualizarRutas(int tipo_transporte) {
        try
        {
            this.rutas = RutaDAO.obtenerRutasPorMedioTransporte(tipo_transporte);
            comboBoxRutas.removeAllItems();
            for (Ruta ruta : rutas)
            {
                comboBoxRutas.addItem(String.format("%s - %s", ruta.getNombreInicio(), ruta.getNombreFin()));
            }
            if (!rutas.isEmpty())
            {
                updateWaypoints(0);
            }
            

        } catch (Exception e) {
            System.out.println("Error al obtener las rutas: " + e.getMessage());
        }
    }

    private void updateWaypoints(int index) {
        Set<Waypoint> waypoints = new HashSet<>();
        for (Parada parada : rutas.get(index).getParadas()) {
            waypoints.add(new DefaultWaypoint(new GeoPosition(parada.getLatitud(), parada.getLongitud())));
        }

        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(waypoints);

        mapViewer.setOverlayPainter(waypointPainter);
    }

    public static void main(String[] args)
    {
        MainWindow np = new MainWindow();
        np.setVisible(true);
    }

}
