package com.ar.Clases;

import com.ar.Anotaciones.Columna;
import com.ar.Anotaciones.Id;
import com.ar.Anotaciones.Tabla;

@Tabla(nombre = "Personas")
public class Persona  {
	
	@Id
	@Columna(nombre = "id")
	public Integer id;

	@Columna(nombre = "dni")
	public Integer dni;
	
	@Columna(nombre = "nombre")
	public String nombre;
	
	@Columna(nombre = "apellido")
	public String apellido;
	
	@Columna(nombre = "edad")
	public Integer edad;
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Number getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Integer getEdad() {
		return edad;
	}
	
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	
	
	@Override
	public String toString() {
	
		return this.id + " " +this.nombre + " " + this.apellido + " " + this.edad;
	}

	
	
}
