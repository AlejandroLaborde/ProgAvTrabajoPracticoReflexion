package com.ar.Properties;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
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
