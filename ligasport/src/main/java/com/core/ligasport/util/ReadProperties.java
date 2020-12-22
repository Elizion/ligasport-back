package com.core.ligasport.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
	
	private static Properties propiedades;

	private static Properties readFile() {
		
		InputStream input = null;
		Properties prop = new Properties();
		
		try {
			
			input = ReadProperties.class.getClassLoader().getResourceAsStream("application.properties");
			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return prop;
		
	}
	
	public static String getProperty(String psPropiedad) {
		
        String valorProp = null;
        
        if (propiedades == null) {
        	propiedades = readFile();
        }
        
        valorProp = propiedades.getProperty(psPropiedad);
        
        return valorProp;
        
    }
	
}
