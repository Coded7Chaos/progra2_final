package com.transporte.gui;

import com.transporte.dao.RutaDAO;
import com.transporte.models.Ruta;
import com.transporte.models.Parada;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainWindow extends JFrame
{
    private JComboBox<String> comboBoxTransportes;
    private JComboBox<String> comboBoxRutas;
    private JXMapViewer mapViewer;
    private JButton btnAgregar, btnCrear, btnMasInformacion;
    private List< List<Ruta> > rutas;
    private int tipo_transporte;
    private JPanel contentPane;

    public MainWindow()
    {
        setTitle("Visualizar Rutas y Paradas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        setLayout(new BorderLayout(10, 10));
        contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
		
        this.rutas = new ArrayList<>();
        rutas.add(new ArrayList<>());
        try
        {
            rutas.add(RutaDAO.obtenerRutasPorMedioTransporte(1));
            rutas.add(RutaDAO.obtenerRutasPorMedioTransporte(2));
            rutas.add(RutaDAO.obtenerRutasPorMedioTransporte(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBackground(new Color(240, 240, 240));
        contentPane.add(topPanel, BorderLayout.NORTH);

        JLabel lblSeleccionarTransporte = new JLabel("Seleccionar transporte:");
        lblSeleccionarTransporte.setFont(new Font("Arial", Font.PLAIN, 14));
        topPanel.add(lblSeleccionarTransporte);

        String[] transportes = {"Seleccione medio de transporte", "Minibus", "Pumakatari", "Teleférico"};
        comboBoxTransportes = new JComboBox<>(transportes);
        comboBoxTransportes.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBoxTransportes.setPreferredSize(new Dimension(150, 30));
        topPanel.add(comboBoxTransportes);

        comboBoxTransportes.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e){
                tipo_transporte = comboBoxTransportes.getSelectedIndex();
                actualizarRutas(tipo_transporte);
            }

        });


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
        contentPane.add(mapViewer, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(new Color(240, 240, 240));
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

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

        comboBoxRutas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboBoxRutas.getSelectedIndex();
                if (selectedIndex >= 0 && tipo_transporte > 0) {
                    updateWaypoints(selectedIndex);
                }
            }
        });

        btnMasInformacion.addActionListener(e -> {
            int selectedIndex = comboBoxRutas.getSelectedIndex();
            if (selectedIndex >= 0 && tipo_transporte > 0)
            {
                InformationWindow np = new InformationWindow(rutas.get(tipo_transporte).get(selectedIndex));
                np.setVisible(true);
            }
        });

        btnCrear.addActionListener(e -> {
            CrearRuta np = new CrearRuta(rutas.get(tipo_transporte), comboBoxRutas);
            np.setVisible(true);
        });

        btnAgregar.addActionListener(e -> {
            CrearParada2 np = new CrearParada2();
            np.setVisible(true);
        });

        actualizarRutas(0);
    }

    private void updateWaypoints(int index)
    {
        Set<Waypoint> waypoints = new HashSet<>();
        for (Parada parada : rutas.get(tipo_transporte).get(index).getParadas()) {
            waypoints.add(new DefaultWaypoint(new GeoPosition(parada.getLatitud(), parada.getLongitud())));
        }

        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(waypoints);

        mapViewer.setOverlayPainter(waypointPainter);
    }

    private void actualizarRutas(int index_transporte)
    {
        try
        {
            comboBoxRutas.removeAllItems();
            if (index_transporte == 0)
            {
                comboBoxRutas.addItem("Seleccione un medio de transporte");
                return;
            }
            for (Ruta ruta : rutas.get(index_transporte))
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
}
