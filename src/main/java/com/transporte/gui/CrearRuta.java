package com.transporte.gui;

import javax.swing.*;

import com.transporte.models.Ruta;
import com.transporte.models.Minibus;
import com.transporte.models.Pumakatari;
import com.transporte.models.Teleferico;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrearRuta extends JFrame
{

    private JComboBox<String> comboMedioTransporte;
    private JTextField txtNombreInicio, txtNombreFin, txtHorarioInicio, txtHorarioFin;
    private JCheckBox chkEstado;
    private JPanel panelDatosExclusivos;
    private JTextField txtNumeroRuta, txtNombreLinea;
    private JPanel panelCarteles;
    private DefaultListModel<String> modeloCarteles;
    private JList<String> listaCarteles;
    private JTextField txtNuevoCartel;
    private JButton btnCrearRuta;

    public CrearRuta(List<Ruta> rutas, JComboBox<String> comboBoxRutas)
    {
        setTitle("Formulario de Transporte Público");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLayout(new BorderLayout());

        JPanel panelSeleccion = new JPanel();
        panelSeleccion.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblMedioTransporte = new JLabel("Medio de Transporte:");
        comboMedioTransporte = new JComboBox<>(new String[]{"Minibus", "Pumakatari", "Teleférico"});
        panelSeleccion.add(lblMedioTransporte);
        panelSeleccion.add(comboMedioTransporte);

        JPanel panelInformacionGeneral = new JPanel();
        panelInformacionGeneral.setLayout(new GridLayout(5, 2, 5, 5));
        panelInformacionGeneral.setBorder(BorderFactory.createTitledBorder("Información General"));

        txtNombreInicio = new JTextField();
        txtNombreFin = new JTextField();
        txtHorarioInicio = new JTextField();
        txtHorarioFin = new JTextField();
        chkEstado = new JCheckBox("Estado (Activo)");

        panelInformacionGeneral.add(new JLabel("Nombre Inicio:"));
        panelInformacionGeneral.add(txtNombreInicio);
        panelInformacionGeneral.add(new JLabel("Nombre Fin:"));
        panelInformacionGeneral.add(txtNombreFin);
        panelInformacionGeneral.add(new JLabel("Horario Inicio:"));
        panelInformacionGeneral.add(txtHorarioInicio);
        panelInformacionGeneral.add(new JLabel("Horario Fin:"));
        panelInformacionGeneral.add(txtHorarioFin);
        panelInformacionGeneral.add(new JLabel(""));
        panelInformacionGeneral.add(chkEstado);

        panelDatosExclusivos = new JPanel();
        panelDatosExclusivos.setBorder(BorderFactory.createTitledBorder("Datos Exclusivos"));
        panelDatosExclusivos.setLayout(new CardLayout());

        JPanel panelMinibus = new JPanel();
        panelMinibus.setLayout(new BorderLayout());
        txtNumeroRuta = new JTextField();
        panelMinibus.add(new JLabel("Número de Ruta:"), BorderLayout.NORTH);
        panelMinibus.add(txtNumeroRuta, BorderLayout.CENTER);

        panelCarteles = new JPanel();
        panelCarteles.setLayout(new BorderLayout());
        modeloCarteles = new DefaultListModel<>();
        listaCarteles = new JList<>(modeloCarteles);
        panelCarteles.add(new JScrollPane(listaCarteles), BorderLayout.CENTER);

        JPanel panelAgregarCartel = new JPanel();
        txtNuevoCartel = new JTextField(15);
        JButton btnAgregarCartel = new JButton("Agregar Cartel");
        panelAgregarCartel.add(txtNuevoCartel);
        panelAgregarCartel.add(btnAgregarCartel);
        panelCarteles.add(panelAgregarCartel, BorderLayout.SOUTH);

        panelMinibus.add(panelCarteles, BorderLayout.SOUTH);
        panelDatosExclusivos.add(panelMinibus, "Minibus");

        JPanel panelPumakatari = new JPanel();
        panelPumakatari.add(new JLabel("No hay datos exclusivos para este medio."));
        panelDatosExclusivos.add(panelPumakatari, "Pumakatari");

        JPanel panelTeleferico = new JPanel();
        panelTeleferico.setLayout(new BorderLayout());
        txtNombreLinea = new JTextField();
        panelTeleferico.add(new JLabel("Nombre de la Línea:"), BorderLayout.NORTH);
        panelTeleferico.add(txtNombreLinea, BorderLayout.CENTER);
        panelDatosExclusivos.add(panelTeleferico, "Teleférico");

        comboMedioTransporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) panelDatosExclusivos.getLayout();
                cl.show(panelDatosExclusivos, (String) comboMedioTransporte.getSelectedItem());
            }
        });

        List<String> carteles = new ArrayList<>();
        btnAgregarCartel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cartel = txtNuevoCartel.getText().trim();
                if (!cartel.isEmpty()) {
                    modeloCarteles.addElement(cartel);
                    txtNuevoCartel.setText("");
                    carteles.add(cartel);
                } else {
                    JOptionPane.showMessageDialog(CrearRuta.this, "El cartel no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCrearRuta = new JButton("Crear Ruta");

        JPanel panelSur = new JPanel(new BorderLayout());
        panelSur.add(panelDatosExclusivos, BorderLayout.CENTER);
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnCrearRuta);
        panelSur.add(panelBoton, BorderLayout.SOUTH);

        btnCrearRuta.addActionListener(e -> {
            String nombreInicio = txtNombreInicio.getText();
            String nombreFin = txtNombreFin.getText();
            String horarioInicio = txtHorarioInicio.getText();
            String horarioFin = txtHorarioFin.getText();
            int estado = 0;

            int indexCombo = comboMedioTransporte.getSelectedIndex();
            switch (indexCombo)
            {
                case 0:
                    String numeroRuta = txtNumeroRuta.getText();
                    Ruta mini = new Minibus(nombreInicio, nombreFin, horarioInicio, horarioFin, estado, numeroRuta, carteles);
                    comboBoxRutas.addItem(String.format("%s - %s", nombreInicio, nombreFin));
                    try
                    {
                        mini.guardarRuta();
                        rutas.add(mini);
                    } catch (SQLException error) {
                        error.printStackTrace();
                    }
                    break;
            
                case 1:
                    Ruta puma = new Pumakatari(nombreInicio, nombreFin, horarioInicio, horarioFin, estado);
                    comboBoxRutas.addItem(String.format("%s - %s", nombreInicio, nombreFin));
                    try
                    {
                        puma.guardarRuta();
                        rutas.add(puma);
                    } catch (SQLException error) {
                        error.printStackTrace();
                    }
                    break;

                case 2:
                    String nombreLinea = txtNombreLinea.getText();
                    Ruta tele = new Teleferico(nombreInicio, nombreFin, horarioInicio, horarioFin, estado, nombreLinea);
                    comboBoxRutas.addItem(String.format("%s - %s", nombreInicio, nombreFin));
                    try
                    {
                        tele.guardarRuta();
                        rutas.add(tele);
                    } catch (SQLException error) {
                        error.printStackTrace();
                    }
                    break;

                default:
                    break;
            }
        });

        add(panelSeleccion, BorderLayout.NORTH);
        add(panelInformacionGeneral, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);
    }
}
