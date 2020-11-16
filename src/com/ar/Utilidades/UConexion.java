package com.ar.Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ar.Properties.ConfiguradorPropiedades;

public class UConexion {
	
	private static UConexion instancia;
	private String driver;
	private String pathConection;
	private String user;
	private String pass;
	
	private Connection con=null;
	
	private UConexion() {

		//"C:/JavaConfig/config/framework.properties"
		this.driver = ConfiguradorPropiedades.getPropiedad("driver");
		this.pathConection = ConfiguradorPropiedades.getPropiedad("pathConection");
		this.user= ConfiguradorPropiedades.getPropiedad("user");
		this.pass=ConfiguradorPropiedades.getPropiedad("pass");
		
		if( this.con == null ) {
			try {
				this.crearConexion();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public Connection getCon() {
		return con;
	}


	private void crearConexion() throws SQLException, ClassNotFoundException {
		Class.forName(this.driver);
		this.con = DriverManager.getConnection(this.pathConection,this.user,this.pass);
	}
	

	public static UConexion getInstance() {
		if(instancia == null) {
			 instancia = new UConexion();
			 return instancia;
		}else {
			return instancia;
		}
		
	}

	
	
	
	
}
