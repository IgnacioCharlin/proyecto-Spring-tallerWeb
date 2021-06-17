package ar.edu.unlam.tallerweb1.servicios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.excepciones.ProfesorYaExiste;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProfesor;

public class ServicioProfesorTest {
	private final long ID = 1l;
	private final String EMAIL="asd@asd.com";
	private final String PASSWORD ="123";
	private final String ROL ="profesor";
	private Profesor profesorRegistrado;
	
	ServicioProfesorImpl servicioProfesor;
	RepositorioProfesor repositorioProfesor;

	@Before
	public void init() {
		repositorioProfesor = mock(RepositorioProfesor.class);
		servicioProfesor = new ServicioProfesorImpl(repositorioProfesor);
	}
	
	
	@Test
	public void queSePuedaAgregarUnProfesorQueNoExiste() {
		givenProfesorNoExiste(ID);
		whenRegistroProfesor(ID,EMAIL,PASSWORD,ROL);
		thenElProfesorSeGuardaConExito();
	}
	
	@Test(expected = ProfesorYaExiste.class)
	public void queNoSePuedaAgregarProfesorQueYaExiste() {
		givenProfesorExiste(EMAIL);
		whenRegistroProfesor(ID,EMAIL,PASSWORD,ROL);
		thenElProfesorNoSeGuardo();
	}
	
	
	private void thenElProfesorNoSeGuardo() {
		assertThat(profesorRegistrado).isNull();
	}


	private void givenProfesorExiste(String email) {
		when(repositorioProfesor.buscarProfesorPorMail(email)).thenReturn(new Profesor());
	}


	private void thenElProfesorSeGuardaConExito() {
		assertThat(profesorRegistrado).isNotNull();
	}


	private void whenRegistroProfesor(long id, String email, String password, String rol) {
		Profesor profesor = new Profesor();
		profesor.setId(id);
		profesor.setEmail(email);
		profesor.setPassword(password);
		profesor.setRol(rol);
		profesorRegistrado = servicioProfesor.agregarProfesor(profesor);
	}


	private void givenProfesorNoExiste(long id) {
		when(repositorioProfesor.buscarProfesorPorId(id)).thenReturn(null);
	}


	



}
