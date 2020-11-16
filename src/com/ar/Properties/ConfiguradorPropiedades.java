package com.ar.Properties;

import java.io.FileInputStream;
import java.util.Properties;



public class ConfiguradorPropiedades {

	
private static String path; 


	public ConfiguradorPropiedades(){
		}

	public static String getPropiedad(String key, String path) {
		ConfiguradorPropiedades.controlPath();
		Properties prop = new Properties();
		String salida = "";
		try {
			prop.load(new FileInputStream(path));
			salida =  prop.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}
	
	public static String getPropiedad(String key) {
		ConfiguradorPropiedades.controlPath();
		Properties prop = new Properties();
		String salida = "";
		try {
			prop.load(new FileInputStream(path));
			salida =  prop.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}


	private static void controlPath(){
		if(System.getProperty("PROPERTIES_PATH")==null){
			ConfiguradorPropiedades.setPath("C:/JavaConfig/config/framework.properties");
		}else{
			ConfiguradorPropiedades.setPath(System.getProperty("PROPERTIES_PATH"));
		}
	}
		public static void setPath(String path) {
			ConfiguradorPropiedades.path = path;
		}
	}
