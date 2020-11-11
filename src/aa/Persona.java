package aa;

import com.ar.Anotaciones.Columna;
import com.ar.Anotaciones.Id;
import com.ar.Anotaciones.Tabla;

@Tabla(nombre = "Personas")
public class Persona  {
	
	@Id
	@Columna(nombre = "dni")
	public Number dni;
	
	@Columna(nombre = "nombre")
	public String nombre;
	
	@Columna(nombre = "apellido")
	public String apellido;
	
	@Columna(nombre = "edad")
	public Number edad;
	
	
	
	public Number getDni() {
		return dni;
	}

	public void setDni(Number dni) {
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
	public Number getEdad() {
		return edad;
	}
	
	public void setEdad(Number edad) {
		this.edad = edad;
	}
	
	
	@Override
	public String toString() {
	
		return this.nombre + " " + this.apellido + " " + this.edad;
	}

	
	
}
