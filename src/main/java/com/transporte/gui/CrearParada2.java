package com.transporte.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import com.transporte.config.DatabaseConnection;
import com.transporte.dao.ParadaDAO;
import com.transporte.dao.ZonaDAO;
import com.transporte.models.EstacionTeleferico;
import com.transporte.models.Minibus;
import com.transporte.models.ParadaPuma;
import com.transporte.models.Pumakatari;
import com.transporte.models.PuntosMinibus;
import com.transporte.models.Ruta;
import com.transporte.models.Teleferico;
import com.transporte.models.Zona;

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
	private int idRutaPuma;
	private int idTele;
	private int idMini;
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
				and tipo_transporte=2
				""";
		try(Connection conn = DatabaseConnection.getConnection();
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(query)) {
				while(rs.next()){
					int id_ruta = rs.getInt("id_ruta");
					String nombre_inicio = rs.getString("nombre_inicio");
					String nombre_fin = rs.getString("nombre_fin");
					Pumakatari rutaPuma = new Pumakatari(id_ruta, nombre_inicio, nombre_fin);
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
		
		JPanel panel = new JPanel(new GridLayout(1,2));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(panel);
	    JPanel panel_1 = new JPanel(new GridLayout(2,1));
	    panel.add(panel_1);
	    JPanel panelSuperior = new JPanel(new BorderLayout(0,0));
		
			JPanel header = new JPanel(new BorderLayout(0,0));

	        JLabel lblNewLabel = new JLabel("Agregar nueva parada ");
	        lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
	        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNewLabel.setFont(new Font("Candara", Font.PLAIN, 25));
	        header.add(lblNewLabel, BorderLayout.CENTER);
	        
	        JLabel lblNewLabel_1 = new JLabel("Haga click en el mapa para establecer la direccion de su parada");
	        lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
	        header.add(lblNewLabel_1, BorderLayout.SOUTH);
	    
			panelSuperior.add(header, BorderLayout.NORTH);

		panel_1.add(panelSuperior);	
	        JPanel panel_2 = new JPanel();
	        panel_2.setLayout(new BorderLayout(0, 0));
			panel_2.add(mapViewer, BorderLayout.CENTER);

			panel.add(panel_2);
	        


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
	        panelSuperior.add(panel_3, BorderLayout.CENTER);
	        panel_3.setLayout(new GridLayout(2, 3, 10, 0));
	        
	        
	        longitud = new JTextField();
	        panel_3.add(longitud);
	        longitud.setColumns(10);
	        
	        latitud = new JTextField();
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
			lblNewLabel_2.setVerticalAlignment(SwingConstants.NORTH);
			
	        panel_3.add(lblNewLabel_2);
	        
	        JLabel lblNewLabel_3 = new JLabel("Latitud");
	        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3.setVerticalAlignment(SwingConstants.NORTH);
	        panel_3.add(lblNewLabel_3);
	        
	        JLabel lblNewLabel_4 = new JLabel("Zona");
	        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_4.setVerticalAlignment(SwingConstants.NORTH);
	        panel_3.add(lblNewLabel_4);
	        



	        JPanel panel_4 = new JPanel();
	        panelSuperior.add(panel_4, BorderLayout.SOUTH);
	        panel_4.setLayout(new GridLayout(1, 3));
	    
	        
	        JRadioButton rdbtnNewRadioButton = new JRadioButton("Pumakatari");
	        panel_4.add(rdbtnNewRadioButton);
	        
	        JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Teleferico");
	        panel_4.add(rdbtnNewRadioButton_1);
	        
	        JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Minibus");
	        panel_4.add(rdbtnNewRadioButton_2);
	        
	        rdbtnNewRadioButton.setSelected(true);
	        
	        
	        ButtonGroup group = new ButtonGroup();
	        group.add(rdbtnNewRadioButton);
	        group.add(rdbtnNewRadioButton_1);
	        group.add(rdbtnNewRadioButton_2);
	   
	        
	        JPanel panel_6 = new JPanel(new CardLayout());
	        panel_1.add(panel_6);
	        
	        
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
	        
	        JLabel lblNewLabel_8 = new JLabel("Ruta a la que pertenece");
	        panel1.add(lblNewLabel_8);
	        
			List<Ruta> rPuma = rutasPuma(); 
			
			String[] nombresRutasPuma = new String[rPuma.size()];
        for (int i = 0; i < rPuma.size(); i++) {
            nombresRutasPuma[i] = rPuma.get(i).getNombreInicio() + "-" + rPuma.get(i).getNombreFin(); 
        }

			JComboBox<Ruta> comboBoxRutasPuma = new JComboBox<>(rPuma.toArray(new Ruta[0]));
			panel1.add(comboBoxRutasPuma);

	        
	        JButton guardarParadaPuma = new JButton("Guardar");
	        guardarParadaPuma.setFont(new Font("Calibri", Font.PLAIN, 15));
	        guardarParadaPuma.setHorizontalAlignment(SwingConstants.CENTER);
	        panel1.add(guardarParadaPuma);
	        
			Ruta rutaPumaSeleccionada = new Pumakatari();
			rutaPumaSeleccionada = (Ruta)comboBoxRutasPuma.getSelectedItem();
			this.idRutaPuma = rutaPumaSeleccionada.getId();

			guardarParadaPuma.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e)
				{
					
					ParadaPuma pPuma = new ParadaPuma(longitude, latitude, zonaSeleccionada, idRutaPuma ,lblNewLabel_5.getText(), lblNewLabel_7.getText(), true, null, (Integer)spinner.getValue());
					try {
						pPuma.guardarParada();
						JOptionPane.showMessageDialog(contentPane, "Parada Guardada correctamente");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
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
	        
	        JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Baño");
	        panel_7.add(chckbxNewCheckBox_1);
	        
	        JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Restaurante");
	        panel_7.add(chckbxNewCheckBox_2);
	        
	        JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Area de descanso");
	        panel_7.add(chckbxNewCheckBox_3);
	        
	        JCheckBox chckbxNewCheckBox_4 = new JCheckBox("ATM Banco Union");
	        panel_7.add(chckbxNewCheckBox_4);

			JCheckBox chckbxNewCheckBox_5 = new JCheckBox("ATM Banco Union");
	        panel_7.add(chckbxNewCheckBox_5);

			JCheckBox chckbxNewCheckBox_6 = new JCheckBox("ATM Banco Nacional de Bolivia");
	        panel_7.add(chckbxNewCheckBox_6);

			JCheckBox chckbxNewCheckBox_7 = new JCheckBox("Supermercado");
	        panel_7.add(chckbxNewCheckBox_7);
	        
			List<Teleferico> rTeleferico = rutasTeleferico();
/*
			String[] telefericoLineas = new String[rTeleferico.size()];
        for (int i = 0; i < rTeleferico.size(); i++) {
            telefericoLineas[i] = rTeleferico.get(i).getLinea(); 
        }
 */
	        JLabel lblNewLabel_9 = new JLabel("Linea relacionada");
	        panel2.add(lblNewLabel_9);



	        JComboBox<Teleferico> comboBoxRutasTele = new JComboBox<>(rTeleferico.toArray(new Teleferico[0]));
	        panel2.add(comboBoxRutasTele);
	        

	        JButton guardarTele = new JButton("Guardar");

	        guardarTele.setFont(new Font("Calibri", Font.PLAIN, 15));
	        guardarTele.setHorizontalAlignment(SwingConstants.CENTER);
	        panel2.add(guardarTele);
	        

			Teleferico rutaTeleSeleccionada = (Teleferico) comboBoxRutasTele.getSelectedItem();
			String colortele = rutaTeleSeleccionada.getLinea();
			this.idTele = rutaTeleSeleccionada.getId();


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

						EstacionTeleferico nTele = new EstacionTeleferico(longitude, latitude, (Zona)comboBox.getSelectedItem(), idTele, textField_3.getText(), textField_4.getText(), chckbxNewCheckBox.isSelected(), negocios, colortele);

						nTele.guardarParada();
						JOptionPane.showMessageDialog(contentPane, "Parada Guardada correctamente");
					} catch(SQLException sqe){
						sqe.printStackTrace();
					}
				}
			});


	        panel_6.add(panel2, "Panel 2");

	        panel_6.add(panel3, "Panel 3");

			//panel 3 minibus
			//panel 3 = puntos de minibus 

	        panel3.setLayout(new GridLayout(4, 2, 0, 0));
	        
	        JLabel lblNewLabel_10 = new JLabel("Ruta relacionada");
	        panel3.add(lblNewLabel_10);
	        
			
			List<Minibus> rMini = rutasMinibus(); 
			
			String[] nombresRutasMini = new String[rMini.size()];
        for (int i = 0; i < rMini.size(); i++) {
            nombresRutasMini[i] = rMini.get(i).getNumero(); 
		}
			JComboBox<Minibus> listaRutasmini = new JComboBox<>(rMini.toArray(new Minibus[0])); 
	        panel3.add(listaRutasmini);
	        
	        JLabel lblNewLabel_11 = new JLabel("Color ");
	        panel3.add(lblNewLabel_11);
	        
	        JComboBox comboBoxColor = new JComboBox(colores);
	        panel3.add(comboBoxColor);
	        
	        JLabel lblNewLabel_12 = new JLabel("trayectoria");
	        panel3.add(lblNewLabel_12);
	        JPanel panelAux = new JPanel();
	        

			JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Ida");
	        panelAux.add(rdbtnNewRadioButton_3);
	        
	        JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("Vuelta");
	        panelAux.add(rdbtnNewRadioButton_4);
	        
	        rdbtnNewRadioButton_3.setSelected(true);
	        
	        ButtonGroup idaVUelta = new ButtonGroup();
	        idaVUelta.add(rdbtnNewRadioButton_3);
	        idaVUelta.add(rdbtnNewRadioButton_4);
			panel3.add(panelAux);
	        
			Boolean trayec;
			if(rdbtnNewRadioButton_3.isSelected()) trayec = true;
			else trayec = false;
			Minibus rutaMiniSeleccionada = (Minibus) listaRutasmini.getSelectedItem(); 
			this.idMini = rutaMiniSeleccionada.getId();

	       // JToggleButton tglbtnNewToggleButton = new JToggleButton("ida");
	        
	        JButton guardarMini = new JButton("Guardar");
	        panel3.add(guardarMini);
			
			guardarMini.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						PuntosMinibus nMini = new PuntosMinibus(longitude, latitude, (Zona)comboBox.getSelectedItem(), idMini, (String)comboBoxColor.getSelectedItem(), trayec);

						nMini.guardarParada();
						JOptionPane.showMessageDialog(contentPane, "Parada Guardada correctamente");
					} catch(SQLException sqe){
						sqe.printStackTrace();
					}
				}
			});
	        
	        
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