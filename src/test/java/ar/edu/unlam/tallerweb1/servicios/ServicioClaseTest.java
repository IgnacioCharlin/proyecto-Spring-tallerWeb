package ar.edu.unlam.tallerweb1.servicios;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.excepciones.FaltaCupo;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoUnaFecha;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.DatosClase;
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

	private ServicioClaseImpl servicio;

	private Profesor profesor;
	private RepositorioClase repositorioClase;
	
	@Before
	public void init() {
		repositorioClase = mock(RepositorioClase.class);

		servicio = new ServicioClaseImpl(repositorioClase);
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
		nueva.setIdProfesor(profesor.getId());
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
