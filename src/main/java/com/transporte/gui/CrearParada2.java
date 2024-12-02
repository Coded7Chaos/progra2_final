package com.transporte.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.transporte.config.DatabaseConnection;
import com.transporte.dao.ParadaDAO;
import com.transporte.dao.ZonaDAO;
import com.transporte.models.EstacionTeleferico;
import com.transporte.models.Minibus;
import com.transporte.models.ParadaPuma;
import com.transporte.models.Pumakatari;
import com.transporte.models.Ruta;
import com.transporte.models.Teleferico;
import com.transporte.models.Zona;
import com.transporte.utils.Fonts;
import java.sql.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearParada2<E> extends JFrame {
	private JXMapViewer mapViewer;
	Zona zonaSeleccionada=null;
	ParadaDAO pDAO = new ParadaDAO();
	ZonaDAO zDAO = new ZonaDAO();	
	double longitude;
	double latitude;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField longitud;
	private JTextField latitud;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	String[] colores = {
		"Rojo",
		"Amarillo",
		"Verde",
		"Azul",
		"Naranja",
		"Blanco",
		"Celeste",
		"Morado",
		"Cafe",
		"Plateada"
	};

	List<Ruta> rutasPuma(){
		List<Ruta> rp = new ArrayList<>();
		String query = """
				SELECT (id_ruta, nombre_inicio, nombre_fin) FROM Rutas 
				WHERE estado=true
				and tipo_transporte=1
				""";
		try(Connection conn = DatabaseConnection.getConnection();
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(query)) {
				while(rs.next()){
					int id_ruta = rs.getInt("id_ruta");
					String nombre_inicio = rs.getString("nombre_inicio");
					String nombre_fin = rs.getString("nombre_fin");
					Pumakatari rutaPuma = new Pumakatari(id_ruta, nombre_fin, nombre_fin);
					rp.add(rutaPuma); 
				}
			}catch(SQLException sqle){
				sqle.printStackTrace();
			}
		
		return rp;
	}
	
	List<Teleferico> rutasTeleferico(){
		List<Teleferico> rt = new ArrayList<>();

		String query = """
			SELECT id_ruta, nombre 
			FROM Lineas
			""";
				try(Connection conn = DatabaseConnection.getConnection();
				Statement s = conn.createStatement();
				ResultSet rs = s.executeQuery(query)) {
					while(rs.next()){
						int id_ruta = rs.getInt("id_ruta");
						String linea = rs.getString("nombre");
						Teleferico rutaTeleferico = new Teleferico(id_ruta, linea);
						rt.add(rutaTeleferico); 
					}
				}catch(SQLException sqle){
					sqle.printStackTrace();
				}
		return rt;
	}


	
	List<Minibus> rutasMinibus(){
		List<Minibus> rm = new ArrayList<>();

		String query = """
			SELECT id_ruta, numero FROM Ruta_Numeros 
			""";
			try(Connection conn = DatabaseConnection.getConnection();
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(query)) {
				while(rs.next()){
					int id_ruta = rs.getInt("id_ruta");
					String numero = rs.getString("numero");
					Minibus mini = new Minibus(id_ruta,numero);
					rm.add(mini); 
				}
			}catch(SQLException sqle){
				sqle.printStackTrace();
			}

		return rm;
	}

	Map< Integer, String> negocios(){
		 Map<Integer, String> negocios = new HashMap<>();
				String query = """
				SELECT (id_negocio, nombre) FROM Negocios 
				""";
		try(Connection conn = DatabaseConnection.getConnection();
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(query)) {
				while(rs.next()){
					int id_negocio = rs.getInt("id_negocio");
					String nombre = rs.getString("nombre");
					negocios.put( id_negocio, nombre);
				}
			}catch(SQLException sqle){
				sqle.printStackTrace();
			}
		
		return negocios;
	}
	



	public CrearParada2() {




		mapViewer = new JXMapViewer();
        mapViewer.setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo()));
		mapViewer.setZoom(4);
        mapViewer.setAddressLocation(new GeoPosition(-16.51, -68.11));
		

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 872, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panel = new JPanel();
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(panel);
	        GridBagLayout gbl_panel = new GridBagLayout();
	        gbl_panel.columnWidths = new int[]{308, 308, 0};
	        gbl_panel.rowHeights = new int[]{253, 0};
	        gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
	        gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
	        panel.setLayout(gbl_panel);
	        
	        JPanel panel_1 = new JPanel();
	        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
	        gbc_panel_1.fill = GridBagConstraints.BOTH;
	        gbc_panel_1.insets = new Insets(0, 0, 0, 5);
	        gbc_panel_1.gridx = 0;
	        gbc_panel_1.gridy = 0;
	        panel.add(panel_1, gbc_panel_1);
	        GridBagLayout gbl_panel_1 = new GridBagLayout();
	        gbl_panel_1.columnWidths = new int[]{308, 0};
	        gbl_panel_1.rowHeights = new int[]{47, 37, 56, 0, 0};
	        gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	        gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
	        panel_1.setLayout(gbl_panel_1);
	        
	        JLabel lblNewLabel = new JLabel("Agregar nueva parada ");
	        lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
	        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNewLabel.setFont(new Font("Candara", Font.PLAIN, 25));
	        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
	        gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
	        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
	        gbc_lblNewLabel.gridx = 0;
	        gbc_lblNewLabel.gridy = 0;
	        panel_1.add(lblNewLabel, gbc_lblNewLabel);
	        
	        JLabel lblNewLabel_1 = new JLabel("Haga click en el mapa para establecer la direccion de su parada");
	        lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
	        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
	        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
	        gbc_lblNewLabel_1.gridx = 0;
	        gbc_lblNewLabel_1.gridy = 1;
	        panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
	        
			   
	        JPanel panel_2 = new JPanel();
	        panel.add(panel_2);
	        panel_2.setLayout(new BorderLayout(0, 0));
			panel_2.add(mapViewer, BorderLayout.CENTER);



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

	        JPanel panel_3 = new JPanel();
	        GridBagConstraints gbc_panel_3 = new GridBagConstraints();
	        gbc_panel_3.insets = new Insets(0, 0, 5, 0);
	        gbc_panel_3.fill = GridBagConstraints.BOTH;
	        gbc_panel_3.gridx = 0;
	        gbc_panel_3.gridy = 2;
	        panel_1.add(panel_3, gbc_panel_3);
	        panel_3.setLayout(new GridLayout(2, 4, 10, 0));
	        
	        
	        longitud = new JTextField();
	        panel_3.add(longitud);
	        longitud.setColumns(10);
	        
	        latitud = new JTextField();
	        latitud.setToolTipText("");
	        panel_3.add(latitud);
	        latitud.setColumns(10);
	        

			List<Zona> zonas = zDAO.listarZonas();

			JComboBox<Zona> comboBox = new JComboBox<>();
			Zona texto = new Zona(0, "Seleccionar zona");
			comboBox.addItem(texto);
			for(Zona z: zonas){
				comboBox.addItem(z);
			}
			panel_3.add(comboBox);

	        
	        JLabel lblNewLabel_2 = new JLabel("Longitud");
	        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
	        panel_3.add(lblNewLabel_2);
	        
	        JLabel lblNewLabel_3 = new JLabel("Latitud");
	        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
	        panel_3.add(lblNewLabel_3);
	        
	        JLabel lblNewLabel_4 = new JLabel("Zona");
	        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
	        panel_3.add(lblNewLabel_4);
	        
	        JPanel panel_4 = new JPanel();
	        GridBagConstraints gbc_panel_4 = new GridBagConstraints();
	        gbc_panel_4.fill = GridBagConstraints.BOTH;
	        gbc_panel_4.gridx = 0;
	        gbc_panel_4.gridy = 3;
	        panel_1.add(panel_4, gbc_panel_4);
	        panel_4.setLayout(new BorderLayout(0, 0));
	        
	        JPanel panel_5 = new JPanel();
	        panel_4.add(panel_5, BorderLayout.NORTH);
	        
	        
	        JRadioButton rdbtnNewRadioButton = new JRadioButton("Pumakatari");
	        panel_5.add(rdbtnNewRadioButton);
	        
	        JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Teleferico");
	        panel_5.add(rdbtnNewRadioButton_1);
	        
	        JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Minibus");
	        panel_5.add(rdbtnNewRadioButton_2);
	        
	        rdbtnNewRadioButton.setSelected(true);
	        
	        
	        ButtonGroup group = new ButtonGroup();
	        group.add(rdbtnNewRadioButton);
	        group.add(rdbtnNewRadioButton_1);
	        group.add(rdbtnNewRadioButton_2);
	   
	        
	        JPanel panel_6 = new JPanel(new CardLayout());
	        panel_4.add(panel_6, BorderLayout.CENTER);
	        
	        
	     // Paneles que se mostrarán
	        JPanel panel1 = new JPanel();

	        JPanel panel2 = new JPanel();
	     
	        JPanel panel3 = new JPanel();

	        // Añadir los paneles al CardLayout
	        panel_6.add(panel1, "Panel 1");
	        
	        
	        
	        //ESTRUCTURA DE LOS PANELES
			//panel 1  = pumakatari
	        panel1.setLayout(new GridLayout(5, 2, 0, 30));
	        
	        JLabel lblNewLabel_5 = new JLabel("Nombre");
	        panel1.add(lblNewLabel_5);
	        
	        textField = new JTextField();
	        textField.setPreferredSize(new Dimension(20,30));
	        panel1.add(textField);
	        
	        JLabel lblNewLabel_7 = new JLabel("Direccion");
	        panel1.add(lblNewLabel_7);
	        
	        textField_1 = new JTextField();
	        textField_1.setToolTipText("Calle o avenida en la que se encuentra");
	        panel1.add(textField_1);
	        textField_1.setColumns(10);
	        
	        JLabel lblNewLabel_6 = new JLabel("Tiempo de espera aproximado");
	        panel1.add(lblNewLabel_6);
	        
	        JSpinner spinner = new JSpinner();
	        spinner.setToolTipText("minutos");
	        spinner.setModel(new SpinnerNumberModel(15, 0, 60, 1));
	        panel1.add(spinner);
	        
	        JLabel lblNewLabel_8 = new JLabel("Ruta o rutas relacionadas");
	        panel1.add(lblNewLabel_8);
	        
			List<Ruta> rPuma = rutasPuma(); 
			
			String[] nombresRutasPuma = new String[rPuma.size()];
        for (int i = 0; i < rPuma.size(); i++) {
            nombresRutasPuma[i] = rPuma.get(i).getNombreInicio() + "-" + rPuma.get(i).getNombreFin(); 
        }

			JList<String> listaRutas1 = new JList<>(nombresRutasPuma);
			listaRutas1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			JScrollPane scrollPane = new JScrollPane(listaRutas1);
	        panel1.add(scrollPane);

	        
	        JButton guardarParadaPuma = new JButton("Guardar");
	        guardarParadaPuma.setFont(new Font("Calibri", Font.PLAIN, 15));
	        guardarParadaPuma.setHorizontalAlignment(SwingConstants.CENTER);
	        panel1.add(guardarParadaPuma);
	        
			guardarParadaPuma.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e)
				{
					ParadaPuma pPuma = new ParadaPuma(longitude, latitude, zonaSeleccionada, lblNewLabel_5.getText(), lblNewLabel_7.getText(), true, null, (Integer)spinner.getValue());
				}
			});

	        
	        //panel2 = estacion de teleferico
	        
	        panel2.setLayout(new GridLayout(5, 2, 0, 30));
	        
	        JLabel lblNewLabel5 = new JLabel("Nombre");
	        panel2.add(lblNewLabel5);
	        
	        textField_3 = new JTextField();
	        panel2.add(textField_3);
	        textField_3.setColumns(10);

	        JLabel lblNewLabel7 = new JLabel("Direccion");
	        panel2.add(lblNewLabel7);
	        
	        textField_4 = new JTextField();
	        panel2.add(textField_4);
	        textField_4.setColumns(10);
	        
	        JLabel lblNewLabel6 = new JLabel("Marcar si tiene");
	        panel2.add(lblNewLabel6);
	        
	        JPanel panel_7 = new JPanel();
	        panel2.add(panel_7);
	        
			Map<Integer, String> neg = negocios();

	        JCheckBox chckbxNewCheckBox = new JCheckBox("Parqueo");
	        panel_7.add(chckbxNewCheckBox);
	        
	        JCheckBox chckbxNewCheckBox_1 = new JCheckBox(neg.get(2));
	        panel_7.add(chckbxNewCheckBox_1);
	        
	        JCheckBox chckbxNewCheckBox_2 = new JCheckBox(neg.get(3));
	        panel_7.add(chckbxNewCheckBox_2);
	        
	        JCheckBox chckbxNewCheckBox_3 = new JCheckBox(neg.get(4));
	        panel_7.add(chckbxNewCheckBox_3);
	        
	        JCheckBox chckbxNewCheckBox_4 = new JCheckBox(neg.get(5));
	        panel_7.add(chckbxNewCheckBox_4);

			JCheckBox chckbxNewCheckBox_5 = new JCheckBox(neg.get(6));
	        panel_7.add(chckbxNewCheckBox_5);

			JCheckBox chckbxNewCheckBox_6 = new JCheckBox(neg.get(7));
	        panel_7.add(chckbxNewCheckBox_6);

			JCheckBox chckbxNewCheckBox_7 = new JCheckBox(neg.get(1));
	        panel_7.add(chckbxNewCheckBox_7);
	        
			List<Teleferico> rTeleferico = rutasTeleferico();

			String[] telefericoLineas = new String[rTeleferico.size()];
        for (int i = 0; i < rTeleferico.size(); i++) {
            telefericoLineas[i] = rTeleferico.get(i).getLinea(); 
        }

	        JLabel lblNewLabel_9 = new JLabel("Lineas relacionadas");
	        panel2.add(lblNewLabel_9);



	        JList<String> listColores = new JList(telefericoLineas);
	        panel2.add(listColores);
	        

	        JButton guardarTele = new JButton("Guardar");

	        guardarTele.setFont(new Font("Calibri", Font.PLAIN, 15));
	        guardarTele.setHorizontalAlignment(SwingConstants.CENTER);
	        panel2.add(guardarTele);
	        

			guardarTele.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						List<Integer> negocios=null;
						if(chckbxNewCheckBox_7.isSelected()) negocios.add(1);
						
						if(chckbxNewCheckBox_1.isSelected()) negocios.add(2);
						
						if(chckbxNewCheckBox_2.isSelected()) negocios.add(3);
						
						if(chckbxNewCheckBox_3.isSelected()) negocios.add(4);
						
						if(chckbxNewCheckBox_4.isSelected()) negocios.add(5);
						
						if(chckbxNewCheckBox_5.isSelected()) negocios.add(6);
						
						if(chckbxNewCheckBox_6.isSelected()) negocios.add(7);

						EstacionTeleferico nTele = new EstacionTeleferico(longitude, latitude, (Zona)comboBox.getSelectedItem(), 
						textField_3.getText(), textField_4.getText(), chckbxNewCheckBox.isSelected(), negocios);

						nTele.guardarParada();
					} catch(SQLException sqe){
						sqe.printStackTrace();
					}
				}
			});


	        panel_6.add(panel2, "Panel 2");

	        panel_6.add(panel3, "Panel 3");
	        panel3.setLayout(new GridLayout(4, 2, 0, 0));
	        
	        JLabel lblNewLabel_10 = new JLabel("Ruta o rutas relacionadas");
	        panel3.add(lblNewLabel_10);
	        
			//panel 3 = puntos de minibus 
			List<Minibus> rMini = rutasMinibus(); 
			
			String[] nombresRutasMini = new String[rMini.size()];
        for (int i = 0; i < rMini.size(); i++) {
            nombresRutasMini[i] = rMini.get(i).getNumero(); 
		}
			JList<String> listaRutas = new JList<>(nombresRutasMini);
			listaRutas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			JScrollPane scrollPane1 = new JScrollPane(listaRutas);
	        panel3.add(scrollPane1);
	        
	        JLabel lblNewLabel_11 = new JLabel("Color ");
	        panel3.add(lblNewLabel_11);
	        
	        JComboBox comboBoxColor = new JComboBox(colores);
	        panel3.add(comboBoxColor);
	        
	        JLabel lblNewLabel_12 = new JLabel("trayectoria");
	        panel3.add(lblNewLabel_12);
	        
	        JToggleButton tglbtnNewToggleButton = new JToggleButton("ida");
	        panel3.add(tglbtnNewToggleButton);
	        
	        JButton btnNewButton_1 = new JButton("Guardar");
	        panel3.add(btnNewButton_1);
	        
	        
	        
	        ActionListener radioListener = new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                CardLayout cl = (CardLayout) panel_6.getLayout();
	                if (rdbtnNewRadioButton.isSelected()) {
	                    cl.show(panel_6, "Panel 1");
	                } else if (rdbtnNewRadioButton_1.isSelected()) {
	                    cl.show(panel_6, "Panel 2");
	                } else if (rdbtnNewRadioButton_2.isSelected()) {
	                    cl.show(panel_6, "Panel 3");
	                }
	            }
	        };

	 
	        rdbtnNewRadioButton.addActionListener(radioListener);
	        rdbtnNewRadioButton_1.addActionListener(radioListener);
	        rdbtnNewRadioButton_2.addActionListener(radioListener);
	        
	        
	     
	    
	}}