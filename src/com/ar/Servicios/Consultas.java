package com.ar.Servicios;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ar.Anotaciones.Columna;
import com.ar.Anotaciones.Id;
import com.ar.Anotaciones.Tabla;
import com.ar.Utilidades.UBean;
import com.ar.Utilidades.UConexion;

public class Consultas {
	
	public static void guardar( Object obj ) {
		 StringBuilder sb = new StringBuilder();
		 sb.append("INSERT INTO ");
		 sb.append(obj.getClass().getAnnotation(Tabla.class).nombre());
		 sb.append(" VALUES( ");
		 ArrayList<Field> fields = UBean.obtenerAtributos(obj);
		 for (int i = 0; i < fields.size(); i++) {
			 try {
				 Columna columna = fields.get(i).getAnnotation(Columna.class);
				 	if(columna!=null) {
				 		if( fields.get(i).getType().equals(String.class) ) {
				 			sb.append("'");
				 			sb.append(UBean.ejecutarGet(obj, fields.get(i).getName()));
				 			sb.append("'");
				 		}else {
				 			sb.append(UBean.ejecutarGet(obj, fields.get(i).getName())+" ");
				 		}
						if( i!=fields.size()-1 ) {
							sb.append(", ");
						}
				 	}
					
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
		 }
		 sb.append(");");
		 UConexion uconexion = UConexion.getInstance();
		try {
			 System.out.println(sb.toString());
			PreparedStatement ps = uconexion.getCon().prepareStatement(sb.toString());
			boolean resp = ps.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void modificar( Object obj ) {
		
	
	}
	
	public static void eliminar( Object obj ) {
		StringBuilder sb = new StringBuilder();
		 sb.append("DELETE FROM ");
		 sb.append(obj.getClass().getAnnotation(Tabla.class).nombre());
		 sb.append(" WHERE ");
		 ArrayList<Field> fields = UBean.obtenerAtributos(obj);
		 for (int i = 0; i < fields.size(); i++) {
			 try {
				 Id id = fields.get(i).getAnnotation(Id.class);
				 	if(id!=null) {
				 		sb.append(fields.get(i).getName());
				 		sb.append("=");
				 		sb.append(fields.get(i).get(obj)+"");
				 	}
					
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		 sb.append(";");
		 UConexion uconexion = UConexion.getInstance();
		try {
			 System.out.println(sb.toString());
			PreparedStatement ps = uconexion.getCon().prepareStatement(sb.toString());
			boolean resp = ps.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Object obtenerPorId(Class c, Object id) {
		return null;
	}
}
