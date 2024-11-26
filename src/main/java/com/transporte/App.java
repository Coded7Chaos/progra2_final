package com.transporte;

import com.transporte.gui.CrearParada;
import com.transporte.gui.TransporteApp;
import javax.swing.SwingUtilities;

public class App
{
    public static void main(String[] args)
    {
        //CrearParada np = new CrearParada();
        //np.setVisible(true);
        SwingUtilities.invokeLater(TransporteApp::new);
    }
}
