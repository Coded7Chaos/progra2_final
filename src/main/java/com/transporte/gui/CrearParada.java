package com.transporte.gui;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.transporte.utils.Fonts;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class CrearParada extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Fonts fonts = new Fonts();
	private JTextField longitud;
	private JTextField latitud;

	public CrearParada()
    {
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
	    panel_1.setLayout(new GridLayout(4, 1, 0, 0));
	    
	    JLabel lblNewLabel = new JLabel("Agregar nueva parada ");
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel.setFont(fonts.getRoboto());
	    panel_1.add(lblNewLabel);
	    
	    JLabel lblNewLabel_1 = new JLabel("Haga click en el mapa en el lugar que desee colocar su parada");
	    panel_1.add(lblNewLabel_1);
	    
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
	    
	    latitud = new JTextField();
	    latitud.setToolTipText("");
	    latitud.setEditable(false);
	    panel_3.add(latitud);
	    latitud.setColumns(10);
	    
	    JComboBox comboBox = new JComboBox();
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
	    
	    
	    JPanel panel_2 = new JPanel();
	    panel.add(panel_2);
	}

}