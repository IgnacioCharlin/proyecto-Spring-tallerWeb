package ar.edu.unlam.tallerweb1.modelo;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClaseTest {

	@Test
	public void queSeHayaCreadoUnaClase() {
		Clase clase = new Clase();
		assertNotNull(clase);
	}
	
	@Test
	public void queSeLeIngreseInformacioALaClase() {
		Clase clase = new Clase();
		Usuario profesor = new Usuario();
		String nombre = "Funcional";
		clase.setNombre(nombre);
		//clase.setProfesor(profesor);
		
		assertEquals(nombre,clase.getNombre());
	}

}