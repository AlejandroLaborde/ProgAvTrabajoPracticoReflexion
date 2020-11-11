package com.ar.Servicios;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		 sb.append("( ");
		 ArrayList<Field> fields2 = UBean.obtenerAtributos(obj);
		 for (int i = 0; i < fields2.size(); i++) {
			 try {
				  Columna columna = fields2.get(i).getAnnotation(Columna.class);
				  Annotation[] anotaciones = fields2.get(i).getAnnotations();
				  Boolean soloColumna = true;
				  for (Annotation annotation : anotaciones) {
					  if( annotation.annotationType().equals(Id.class) ) {
						  soloColumna = false;
					  }
				  }
				  if( anotaciones.length>0 && soloColumna ) {
					  
		 			sb.append("`");
		 			sb.append(fields2.get(i).getName());
		 			
		 			if( i!=fields2.size()-1 ) {
						sb.append("`, ");
					}else {
						sb.append("`)");
					}
				  }
				 	
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
	
		 sb.append(" VALUES( ");
		 ArrayList<Field> fields = UBean.obtenerAtributos(obj);
		 for (int i = 0; i < fields.size(); i++) {
			 try {
				  Columna columna = fields.get(i).getAnnotation(Columna.class);
				  Annotation[] anotaciones = fields.get(i).getAnnotations();
				  Boolean soloColumna = true;
				  for (Annotation annotation : anotaciones) {
					  if( annotation.annotationType().equals(Id.class) ) {
						  soloColumna = false;
					  }
				  }
				  if( anotaciones.length>0 && soloColumna ) {
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
		
		try {
			 System.out.println(sb.toString());
			 UConexion uconexion = UConexion.getInstance();
			PreparedStatement ps = uconexion.getCon().prepareStatement(sb.toString());
			boolean resp = ps.execute();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
				 		try {
				 			if( fields.get(i).getType().equals(String.class) ) {
					 			sb.append("'");
					 			sb.append(UBean.ejecutarGet(obj, fields.get(i).getName()));
					 			sb.append("'");
					 		}else {
					 			System.out.println(fields.get(i).getName());
					 			System.out.println(UBean.ejecutarGet(obj, fields.get(i).getName()));
					 			sb.append(UBean.ejecutarGet(obj, fields.get(i).getName())+" ");
					 		}
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				 	}
					
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		 sb.append(";");
		try {
			 System.out.println(sb.toString());
			 UConexion uconexion = UConexion.getInstance();
			PreparedStatement ps = uconexion.getCon().prepareStatement(sb.toString());
			boolean resp = ps.execute();
//			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void modificar( Object obj ) {
		StringBuilder sb = new StringBuilder();
		 sb.append("UPDATE ");
		 sb.append(obj.getClass().getAnnotation(Tabla.class).nombre());
		 sb.append(" SET ");
		 ArrayList<Field> fields = UBean.obtenerAtributos(obj);
		 for (int i = 0; i < fields.size(); i++) {
			 try {
				  Columna columna = fields.get(i).getAnnotation(Columna.class);
				  Annotation[] anotaciones = fields.get(i).getAnnotations();
				  Boolean soloColumna = true;
				  for (Annotation annotation : anotaciones) {
					  if( annotation.annotationType().equals(Id.class) ) {
						  soloColumna = false;
					  }
				  }
				  if( anotaciones.length>0 && soloColumna ) {
					  sb.append(fields.get(i).getName());
				 		sb.append("=");
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
		sb.append(" WHERE ");
		 ArrayList<Field> fields2 = UBean.obtenerAtributos(obj);
		 for (int i = 0; i < fields2.size(); i++) {
			 try {
				 	Id id = fields2.get(i).getAnnotation(Id.class);
				 	if(id!=null) {
				 		sb.append(fields2.get(i).getName());
				 		sb.append("=");
				 		try {
				 			if( fields2.get(i).getType().equals(String.class) ) {
					 			sb.append("'");
					 			sb.append(UBean.ejecutarGet(obj, fields2.get(i).getName()));
					 			sb.append("'");
					 		}else {
					 			System.out.println(fields2.get(i).getName());
					 			System.out.println(UBean.ejecutarGet(obj, fields2.get(i).getName()));
					 			sb.append(UBean.ejecutarGet(obj, fields2.get(i).getName())+" ");
					 		}
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				 	}
					
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		
		try {
			 System.out.println(sb.toString());
			 UConexion uconexion = UConexion.getInstance();
			PreparedStatement ps = uconexion.getCon().prepareStatement(sb.toString());
			boolean resp = ps.execute();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static Object obtenerPorId(Class c, Object id) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		Annotation anotacion = c.getAnnotation(Tabla.class);
		sb.append(((com.ar.Anotaciones.Tabla)anotacion).nombre());
		sb.append(" WHERE ");
		 Field[] fields2 = c.getFields();
	
		 for (int i = 0; i < fields2.length; i++) {
			
			 try {
				 	Id id2 = fields2[i].getAnnotation(Id.class);
				 	if(id2!=null) {
				 		sb.append(fields2[i].getName());
				 		sb.append("=");
				 		try {
				 			if( id.getClass().equals(String.class) ) {
					 			sb.append("'");
					 			sb.append(id);
					 			sb.append("'");
					 		}else {
					 			sb.append(id);
					 		}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				 	}
					
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		
		 Constructor constructor = null;
		Object instancia = null;
		try {
			System.out.println(sb.toString());
			UConexion uconexion = UConexion.getInstance();
			PreparedStatement ps = uconexion.getCon().prepareStatement(sb.toString());
			ResultSet resp = ps.executeQuery();
			
			while(resp.next()) {
				
				try {
					 constructor = c.getConstructor(null);
					 instancia = constructor.newInstance(null);
					 ArrayList<Field> fields = UBean.obtenerAtributos(instancia);
					 for (int i = 0; i < fields.size(); i++) {
						 try {
							  Columna columna = fields.get(i).getAnnotation(Columna.class);
							  
							  if( columna!=null ) {
								  UBean.ejecutarSet(instancia, fields.get(i).getName(), resp.getObject(columna.nombre(), fields.get(i).getType()) );
							  }
							 	
							} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
								e.printStackTrace();
							}
					 }
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return instancia;
	}
}
