package ar.edu.unlam.tallerweb1.servicios;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.tallerweb1.excepciones.FaltaCupo;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoUnaFecha;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.DatosClase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClase;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProfesor;

public class ServicioClaseTest {
/*	una clase no se tiene que guardar
 * 	sin una feha y hora falla -------------------->Listo
 * 	sin un profesor asignado  -------------------->Listo
 * 	sin un cupo o mal cargado -------------------->Listo 
 * 	
 * 
 *  */

	private ServicioClaseImpl servicio;

	private Profesor profesor;
	private RepositorioClase repositorioClase;
	private RepositorioProfesor repositorioProfesor;
	
	@Before
	public void init() {
		repositorioClase = mock(RepositorioClase.class);
		servicio = new ServicioClaseImpl(repositorioClase,repositorioProfesor);
	}

	
	@Test(expected = NoSeCargoUnaFecha.class)
	public void siUnaClaseNoTieneFechaLanzaUnaExcepcion() {
		DatosClase clase = givenClaseNueva();
		
		 whenRegistroLaClase(clase);
		
		thenLaClaseNoSeCarga();
	}
	@Test(expected = FaltaCupo.class)
	public void siUnaClaseNoTieneCupoLanzaUnaExcepcion() {
		DatosClase clase = givenClaseNuevaSinCupo();
		
		 whenRegistroLaClase(clase);
		
		thenLaClaseNoSeCarga();
	}
	
	@Test(expected = NoSeCargoProfesor.class)
	public void siUnaClaseNoTieneProfesorLanzaUnaExcepcion() {
		DatosClase clase = givenClaseNuevaSinProfesor();
		
		 whenRegistroLaClase(clase);
		
		thenLaClaseNoSeCarga();
	}
	
	@Test
	public void queElServicioConsulteTodasLasClases() {
		thenTraeTodasLasClases();
	}
	

	private void thenTraeTodasLasClases() {
		List<Clase> clase = new ArrayList<>();
		clase.add(new Clase());
		clase.add(new Clase());
		when(repositorioClase.buscarTodasLasClase()).thenReturn(clase);
		List<Clase> clases = servicio.consultarTodasLasClases();
		assertEquals(2, clases.size());
	}


	private DatosClase givenClaseNuevaSinProfesor() {
		DatosClase nueva = new DatosClase();
		nueva.setNombre("Funcional");
		nueva.setFechaYHora("Miercoles 10hs");
		nueva.setCupo(10l);
//		seteandoIdProfesor();
//		nueva.setProfesor(profesor.getId());
		return nueva;
	}

	private DatosClase givenClaseNuevaSinCupo() {
		DatosClase nueva = new DatosClase();
		nueva.setNombre("Funcional");
		nueva.setFechaYHora("Miercoles 10hs");
		seteandoIdProfesor();
		nueva.setIdProfesor(2l);
//		nueva.setCapacidad(-1l);
		return nueva;
	}

	private void thenLaClaseNoSeCarga() {
		// TODO Auto-generated method stub
		
	}

	private void whenRegistroLaClase(DatosClase clase) {
		servicio.agregarClase(clase);
		
	}

	private DatosClase givenClaseNueva() {
		Long i = (long) 0;
		DatosClase nueva = new DatosClase();
		nueva.setNombre("Funcional");
		nueva.setCupo(20l);
		return nueva;
	}
	private void seteandoIdProfesor() {
		profesor = new Profesor();
		profesor.setId(10L);
	}
	
}
