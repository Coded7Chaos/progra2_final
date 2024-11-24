package com.transporte;

import com.transporte.gui.CrearParada;
import com.transporte.gui.TransporteApp;
import javax.swing.SwingUtilities;

/**
 * Hello world!
 */
public class App
{
    public static void main(String[] args)
    {

        CrearParada crearParada = new CrearParada();
        crearParada.setVisible(true);
        //SwingUtilities.invokeLater(TransporteApp::new);
    }
}
