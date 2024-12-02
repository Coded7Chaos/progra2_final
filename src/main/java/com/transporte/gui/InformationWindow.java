package com.transporte.gui;

import javax.swing.*;
import java.awt.*;

import com.transporte.models.Parada;
import com.transporte.models.Ruta;
import com.transporte.models.Tarifa;

public class InformationWindow extends JFrame
{
    public InformationWindow(Ruta ruta)
    {
        setTitle("Información de la Ruta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 600);

        JLabel titulo = new JLabel(ruta.getNombreTransporte(), SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        add(titulo, BorderLayout.NORTH);

        JPanel panelPrincipal = new JPanel(new GridLayout(3, 1));

        // Sección de Información
        JPanel panelInformacion = new JPanel(new BorderLayout());
        panelInformacion.setBorder(BorderFactory.createTitledBorder("Información"));

        StringBuilder sbI = new StringBuilder();
        sbI.append(String.format("Ruta: %s - %s\nHorario: %s - %s\n", ruta.getNombreInicio(), ruta.getNombreFin(), ruta.getHorarioInicio(), ruta.getHorarioFin()));
        sbI.append(ruta.getInfo());

        JTextArea textoInformacion = new JTextArea(sbI.toString());
        textoInformacion.setEditable(false);
        textoInformacion.setLineWrap(true);
        textoInformacion.setWrapStyleWord(true);
        panelInformacion.add(new JScrollPane(textoInformacion), BorderLayout.CENTER);

        // Sección de Tarifas
        JPanel panelTarifas = new JPanel(new BorderLayout());
        panelTarifas.setBorder(BorderFactory.createTitledBorder("Tarifas"));

        StringBuilder sbT = new StringBuilder();
        for (Tarifa tarifa : ruta.getTarifas())
            sbT.append(String.format("• %s: %.2f Bs.\n", tarifa.getNombre(), tarifa.getCosto()));

        JTextArea textoTarifas = new JTextArea(sbT.toString());
        textoTarifas.setEditable(false);
        textoTarifas.setLineWrap(true);
        textoTarifas.setWrapStyleWord(true);
        panelTarifas.add(new JScrollPane(textoTarifas), BorderLayout.CENTER);

        // Sección de Paradas
        JPanel panelParadas = new JPanel(new BorderLayout());
        panelParadas.setBorder(BorderFactory.createTitledBorder("Paradas"));

        StringBuilder sbP = new StringBuilder();
        for (Parada parada : ruta.getParadas())
            sbP.append(String.format("• %s, %s\n", "Arreglar", parada.getZona().getNombre()));

        JTextArea textoParadas = new JTextArea(sbP.toString());
        textoParadas.setEditable(false);
        textoParadas.setLineWrap(true);
        textoParadas.setWrapStyleWord(true);
        panelParadas.add(new JScrollPane(textoParadas), BorderLayout.CENTER);

        panelPrincipal.add(panelInformacion);
        panelPrincipal.add(panelTarifas);
        panelPrincipal.add(panelParadas);

        add(panelPrincipal, BorderLayout.CENTER);
    }
}