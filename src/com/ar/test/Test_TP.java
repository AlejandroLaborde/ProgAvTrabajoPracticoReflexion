package com.ar.test;



import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.ar.Clases.*;
import com.ar.Servicios.Consultas;
import com.ar.Utilidades.UBean;

class Test_TP {

	@Test
	void test1_validaUtilidades_obtenerAtributos() {
		Persona p = new Persona();
		 ArrayList<Field> fields = UBean.obtenerAtributos(p);
		assertEquals("apellido", fields.get(3).getName());
	}
	
	@Test
	void test2_validaUtilidades_ejecutarSet_string() {
		Persona p = new Persona();
		try {
			UBean.ejecutarSet(p, "apellido", "Laborde Parodi");
			assertEquals("Laborde Parodi",p.getApellido());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			assertEquals(true, false);
			e.printStackTrace();
		}
	}
	
	@Test
	void test3_validaUtilidades_ejecutarSet_Number() {
		Persona p = new Persona();
		try {
			UBean.ejecutarSet(p, "edad", 25);
			assertEquals(25, p.getEdad());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			assertEquals(true, false);
			e.printStackTrace();
		}
		
	}
	
	@Test
	void test4_validaUtilidades_ejecutarGet_Number() {
		Persona p = new Persona();
		try {
			UBean.ejecutarSet(p, "edad", 25);
			assertEquals("25", UBean.ejecutarGet(p, "edad").toString());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			assertEquals(true, false);
			e.printStackTrace();
		}
		
	}
	
	@Test
	void test5_validaUtilidades_ejecutarGet_String() {
		Persona p = new Persona();
		try {
			UBean.ejecutarSet(p, "apellido", "Laborde Parodi");
			assertEquals("Laborde Parodi", UBean.ejecutarGet(p, "apellido").toString());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			assertEquals(true, false);
			e.printStackTrace();
		}
		
	}
	
	@Test
	void test6_validaServicios_guardar() {
		Persona p = new Persona();
		p.nombre = "alejandro";
		p.apellido = "laborde";
		p.dni=38834224;
		p.edad = 25;
		
		 Object resp = Consultas.guardar(p);
		 System.out.println(resp.toString());
		
	}
	

	@Test
	void test7_validaServicios_eliminar() {
		Persona p = new Persona();
		p.nombre = "alejandro";
		p.apellido = "laborde";
		p.dni=38834224;
		p.edad = 25;
		p.id =1;
		
		Consultas.guardar(p);
		Consultas.eliminar(p);
			
	}
	
	@Test
	void test8_validaServicios_Update() {
		Persona p = new Persona();
		p.nombre = "alejandro";
		p.apellido = "laborde";
		p.dni=38834224;
		p.edad = 25;
		p.id =5;

		p.apellido = "LABORDE";
		Consultas.modificar(p);
			
	}
	
	@Test
	void test9_validaServicios_recuperarPorId() {
		Persona p = new Persona();
		p.nombre = "alejandro";
		p.apellido = "laborde";
		p.dni=38834224;
		p.edad = 25;
		p.id =1;
		
	
		Object instanca = Consultas.obtenerPorId(Persona.class,2);
		System.out.println(instanca.toString());
			
	}
	
	@Test
	void test10_validaServicios_obtenerUlmimoRegistroAgregado() {
		Persona p = new Persona();
		p.nombre = "alejandro";
		p.apellido = "laborde";
		p.dni=38834224;
		p.edad = 25;
		p.id =1;
		
	
		Object instanca = Consultas.ObtenerUltimoRegistroAgregado(Persona.class);
		System.out.println(instanca.toString());
			
	}
	
	@Test
	void test11_validaServicios_guardarModificar() {
		Persona p = new Persona();
		p.nombre = "aLejandro Fabian";
		p.apellido = "laborde";
		p.dni=38834224;
		p.edad = 25;
		p.id =2777;
		
	
		Consultas.guardarModificar(p);
		
			
	}
	
	@Test
	void test12_validaServicios_obtenerTodos() {
		Persona p = new Persona();
		p.nombre = "aLejandro Fabian";
		p.apellido = "laborde";
		p.dni=38834224;
		p.edad = 25;
		p.id =2777;
		
	
		 ArrayList<Object> asd = Consultas.obtenerTodos(Persona.class);
		 for (Object object : asd) {
			System.out.println(object);
		}
			
	}

}
