package com.transporte.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import com.transporte.dao.ParadaDAO;
import com.transporte.dao.ZonaDAO;
import com.transporte.exceptions.EmptyFieldException;
import com.transporte.exceptions.FieldValidator;
import com.transporte.models.Parada;
import com.transporte.models.Ruta;
import com.transporte.models.Zona;
import com.transporte.utils.Fonts;


public class CrearParada extends JFrame
{
	private JXMapViewer mapViewer;
	Zona zonaSeleccionada=null;
	ParadaDAO pDAO = new ParadaDAO();
	ZonaDAO zDAO = new ZonaDAO();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Fonts fonts = new Fonts();
	private JTextField longitud;
	private JTextField latitud;
	double longitude;
	double latitude;

	public CrearParada(List<Ruta> rutas)
    {
		
		mapViewer = new JXMapViewer();
        mapViewer.setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo()));
		mapViewer.setZoom(4);
        mapViewer.setAddressLocation(new GeoPosition(-16.51, -68.11));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panel = new JPanel();
        panel.setBounds(50, 50, 300, 150);
        panel.setBackground(Color.white);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(panel);
        panel.setLayout(new GridLayout(0, 2, 0, 0));
	        
	    JPanel panel_1 = new JPanel();
	    panel.add(panel_1);
	    panel_1.setLayout(new GridLayout(6, 1, 0, 0));
	    
	    JLabel lblNewLabel = new JLabel("Agregar nueva parada ");
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel.setFont(fonts.getRoboto());
	    panel_1.add(lblNewLabel);
	    
	    JLabel lblNewLabel_1 = new JLabel("Haga click en el mapa en el lugar que desee colocar su parada");
	    panel_1.add(lblNewLabel_1);
	   
		JLabel lblNewLabel_7 = new JLabel("Nombre de la parada");
		panel_1.add(lblNewLabel_7);

		JTextField nombreParada  = new JTextField();
		panel_1.add(nombreParada);

		JLabel lblNewLabel_8 = new JLabel("Direccion de la parada");
		panel_1.add(lblNewLabel_8);

		JTextField direccionParada  = new JTextField();
		panel_1.add(direccionParada);

		JLabel lblNewLabel_f1 = new JLabel("Ruta:");
		panel_1.add(lblNewLabel_f1);

		String[] nombresRutas = new String[rutas.size()];
        for (int i = 0; i < rutas.size(); i++)
            nombresRutas[i] = String.format("%s - %s", rutas.get(i).getNombreInicio(), rutas.get(i).getNombreFin());

		JComboBox<String> comboBoxRutas = new JComboBox<>(nombresRutas);
	    panel_1.add(comboBoxRutas);


	    JPanel panel_3 = new JPanel();
	    panel_1.add(panel_3);
	    panel_3.setLayout(new GridLayout(2, 4, 10, 0));
	    
	    JButton guardarParada = new JButton();
		guardarParada.setText("Guardar parada");
		panel_1.add(guardarParada);
		
	    longitud = new JTextField();
	    longitud.setEditable(false);
	    panel_3.add(longitud);
	    longitud.setColumns(10);
	    longitud.setText(null);

	    latitud = new JTextField();
	    latitud.setEditable(false);
		latitud.setText(null);
	    panel_3.add(latitud);
	    latitud.setColumns(10);
		
		mapViewer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				GeoPosition geoPosition = mapViewer.convertPointToGeoPosition(e.getPoint());
				latitude = geoPosition.getLatitude();
				longitude = geoPosition.getLongitude();
				latitud.setText(String.format("%.3f", latitude)); 
				longitud.setText(String.format("%.3f", longitude));
				
				Set<Waypoint> waypoints = new HashSet<>();
				waypoints.add(new DefaultWaypoint(new GeoPosition(latitude, longitude)));

				WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
				waypointPainter.setWaypoints(waypoints);

				mapViewer.setOverlayPainter(waypointPainter);
			}
		});

		List<Zona> zonas = zDAO.listarZonas();		

	    JComboBox<Zona> comboBox = new JComboBox<>();
		Zona texto = new Zona(0, "Seleccionar zona");
		comboBox.addItem(texto);
		for(Zona z: zonas){
			comboBox.addItem(z);
		}
	    panel_3.add(comboBox);

		//obtener el id de la zona seleccionada 

		comboBox.addActionListener(e -> {
			zonaSeleccionada = (Zona) comboBox.getSelectedItem();
		});

	    JLabel lblNewLabel_2 = new JLabel("Longitud");
	    lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
	    panel_3.add(lblNewLabel_2);
	    
	    JLabel lblNewLabel_3 = new JLabel("Latitud");
	    lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
	    panel_3.add(lblNewLabel_3);
	    
	    JLabel lblNewLabel_4 = new JLabel("Zona");
	    lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
	    panel_3.add(lblNewLabel_4);
	    
	    JPanel panel_2 = new JPanel();
	    panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_2.add(mapViewer, BorderLayout.CENTER);


		
		guardarParada.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e)
			{
                try
				{
					FieldValidator.validateTextField("Nombre de la parada", nombreParada.getText());
					FieldValidator.validateTextField("Direccion de la parada", direccionParada.getText());
					FieldValidator.validateTextField("latitud", latitud.getText());
					FieldValidator.validateTextField("longitud", longitud.getText());
					FieldValidator.validateField(zonaSeleccionada.getNombre());

					Parada nuevaParada = new Parada(rutas.get(comboBoxRutas.getSelectedIndex()).getId(), nombreParada.getText(), latitude, longitude, direccionParada.getText(), zonaSeleccionada);
					try
					{
						nuevaParada.guardarParada();
						JOptionPane.showMessageDialog(contentPane, "Parada guardada exitosamente");

					} catch(SQLException es){
						JOptionPane.showMessageDialog(contentPane, es.getMessage());
					}
				
				} catch(EmptyFieldException emp){
					JOptionPane.showMessageDialog(contentPane, emp.getMessage());
				}
		}});
	}

}