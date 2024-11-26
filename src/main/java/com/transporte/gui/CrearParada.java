package com.transporte.gui;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.util.List;
import com.transporte.dao.ParadaDAO;
import com.transporte.dao.ZonaDAO;
import com.transporte.utils.Fonts;
import com.transporte.models.Parada;
import com.transporte.models.Zona;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class CrearParada extends JFrame
{
	private JXMapViewer mapViewer;

	ParadaDAO pDAO = new ParadaDAO();
	ZonaDAO zDAO = new ZonaDAO();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Fonts fonts = new Fonts();
	private JTextField longitud;
	private JTextField latitud;
	double longitude;
	double latitude;

	public CrearParada()
    {
		
		mapViewer = new JXMapViewer();
        mapViewer.setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo()));
		mapViewer.setZoom(4);
        mapViewer.setAddressLocation(new GeoPosition(-16.51, -68.11));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
	    panel_1.setLayout(new GridLayout(5, 1, 0, 0));
	    
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
				latitud.setText(String.valueOf(latitude)); 
				longitud.setText(String.valueOf(longitude)); 
			}
		});

		List<Zona> zonas = zDAO.listarZonas();		

	    JComboBox<Zona> comboBox = new JComboBox<>();
		for(Zona z: zonas){
			comboBox.addItem(z);
		}
	    panel_3.add(comboBox);

		//obtener el id de la zona seleccionada 

		comboBox.addActionListener(e -> {
			Zona zonaSeleccionada = (Zona) comboBox.getSelectedItem();
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
                if(latitud.getText()==null || longitud.getText()==null || nombreParada.getText()==null)
				{
					JOptionPane.showMessageDialog(panel,"Error: debe llenar todas las opciones de manera correcta");
				} 
				else
				{
					Parada nuevaParada = new Parada(nombreParada.getText(), latitude, longitude, direccionParada.getText(),(Zona) comboBox.getSelectedItem());
					pDAO.guardarParada(nuevaParada);

					JOptionPane.showMessageDialog(panel,"Error: debe llenar todas las opciones de manera correcta");
				}
			}
		});
	}

}