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
        MainWindow np = new MainWindow();
        np.setVisible(true);
    }
}
