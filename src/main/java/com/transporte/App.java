package com.transporte;

import com.transporte.gui.TransporteApp;
import javax.swing.SwingUtilities;

public class App
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(TransporteApp::new);
    }
}
