package com.transporte;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;
import com.transporte.gui.MainWindow;

public class App
{
    public static void main(String[] args)
    {
        try 
        {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //CrearParada2 crearParada2 = new CrearParada2();
        //crearParada2.setVisible(true);
        //CrearRuta cr = new CrearRuta(null, null);
        //cr.setVisible(true);
        MainWindow np = new MainWindow();
        np.setVisible(true);
    }
}
