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
		 Persona person = (Persona) resp;
		 assertTrue(person.id!= null);
		
	}
	

	@Test
	void test7_validaServicios_eliminar() {
		Persona p = new Persona();
		p.nombre = "alejandro";
		p.apellido = "laborde";
		p.dni=38834224;
		p.edad = 25;
		p.id =1;
		
		p= (Persona) Consultas.guardar(p);
		Consultas.eliminar(p);
		//Comparo contra null, porque no deberia traer un objeto que elimino ya
		assertTrue(Consultas.obtenerPorId(Persona.class,p.getId())==null);
	}
	
	@Test
	void test8_validaServicios_Update() {
		Persona p = new Persona();
		p.nombre = "alejandro";
		p.apellido = "laborde";
		p.dni=38834224;
		p.edad = 25;
		p.id =5;
		//cambio apellido
		p.apellido = "LABORDE";
		Consultas.modificar(p);
		
		Persona pModificada = (Persona) Consultas.obtenerPorId(Persona.class,p.getId());
		assertEquals("LABORDE", pModificada.getApellido());
		
	}
	
	@Test
	void test9_validaServicios_recuperarPorId() {
		Persona p = new Persona();
		p.nombre = "alejandro";
		p.apellido = "laborde";
		p.dni=38834224;
		p.edad = 25;
		
		p= (Persona) Consultas.guardar(p);
		Object instanca = Consultas.obtenerPorId(Persona.class,p.getId());
		assertEquals(p, (Persona)instanca);
			
	}
	
	@Test
	void test10_validaServicios_obtenerUlmimoRegistroAgregado() {
		Persona p = new Persona();
		p.nombre = "alejandro";
		p.apellido = "laborde";
		p.dni=38834224;
		p.edad = 25;
		p.id =1;
		p= (Persona) Consultas.guardar(p);
		Persona instanca = (Persona) Consultas.ObtenerUltimoRegistroAgregado(Persona.class);
		assertEquals(p, (Persona)instanca);
			
	}
		
	@Test
	void test11_validaServicios_obtenerTodos() {
		Persona p = new Persona();
		p.nombre = "aLejandro Fabian";
		p.apellido = "laborde";
		p.dni=38834224;
		p.edad = 25;
		p.id =2777;
		
	    ArrayList<Object> asd = Consultas.obtenerTodos(Persona.class);
	    assertTrue(asd.size()>0);
		
			
	}

}
