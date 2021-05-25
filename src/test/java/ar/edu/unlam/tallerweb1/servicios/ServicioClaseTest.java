package ar.edu.unlam.tallerweb1.servicios;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.excepciones.FaltaCupo;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoUnaFecha;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClase;

public class ServicioClaseTest {
/*	una clase no se tiene que guardar
 * 	sin una feha y hora falla -------------------->Listo
 * 	sin un profesor asignado  -------------------->Listo
 * 	sin un cupo o mal cargado -------------------->Listo 
 * 	
 * 
 *  */
	private ServicioAgregarClaseImpl servicio;
	private Profesor profesor;
	private RepositorioClase repositorioClase;
	
	@Before
	public void init() {
		repositorioClase = mock(RepositorioClase.class);
		servicio = new ServicioAgregarClaseImpl();
	}
	
	@Test(expected = NoSeCargoUnaFecha.class)
	public void siUnaClaseNoTieneFechaLanzaUnaExcepcion() {
		Clase clase = givenClaseNueva();
		
		 whenRegistroLaClase(clase);
		
		thenLaClaseNoSeCarga();
	}
	@Test(expected = FaltaCupo.class)
	public void siUnaClaseNoTieneCupoLanzaUnaExcepcion() {
		Clase clase = givenClaseNuevaSinCupo();
		
		 whenRegistroLaClase(clase);
		
		thenLaClaseNoSeCarga();
	}
	
	@Test(expected = NoSeCargoProfesor.class)
	public void siUnaClaseNoTieneProfesorLanzaUnaExcepcion() {
		Clase clase = givenClaseNuevaSinProfesor();
		
		 whenRegistroLaClase(clase);
		
		thenLaClaseNoSeCarga();
	}
	

	private Clase givenClaseNuevaSinProfesor() {
		Clase nueva = new Clase();
		nueva.setNombre("Funcional");
		nueva.setHorarioYFecha("Miercoles 10hs");
		nueva.setCapacidad(10l);
//		seteandoIdProfesor();
//		nueva.setProfesor(profesor.getId());
		return nueva;
	}

	private Clase givenClaseNuevaSinCupo() {
		Clase nueva = new Clase();
		nueva.setNombre("Funcional");
		nueva.setHorarioYFecha("Miercoles 10hs");
		seteandoIdProfesor();
		nueva.setProfesor(profesor.getId());
//		nueva.setCapacidad(-1l);
		return nueva;
	}

	private void thenLaClaseNoSeCarga() {
		// TODO Auto-generated method stub
		
	}

	private void whenRegistroLaClase(Clase clase) {
		servicio.agregarClase(clase);
		
	}

	private Clase givenClaseNueva() {
		Clase nueva = new Clase();
		nueva.setNombre("Funcional");
		nueva.setCapacidad(20l);
		return nueva;
	}
	private void seteandoIdProfesor() {
		profesor = new Profesor();
		profesor.setId(10L);
	}
	
}
