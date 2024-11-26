package com.transporte.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class Fonts
{
	  public Font getRoboto()
	  {
		Font robotoFont = null;
		try
		{
			robotoFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/java/com/transporte/resource/fonts/Roboto/Roboto-Regular.ttf")).deriveFont(18f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    ge.registerFont(robotoFont);
		} catch (FontFormatException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return robotoFont;
	}
}