package ar.edu.unlam.tallerweb1.servicios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProfesor;

public class ServicioProfesorTest {
	private final String EMAIL="asd@asd.com";
	private final String PASSWORD ="123";
	private final String ROL ="profesor";
	
	ServicioProfesorImpl servicioProfesor;
	RepositorioProfesor repositorioProfesor;

	@Before
	public void init() {
		repositorioProfesor = mock(RepositorioProfesor.class);
		servicioProfesor = new ServicioProfesorImpl(repositorioProfesor);
	}
	
	
	@Test
	public void queSePuedaAgregarUnProfesorQueNoExiste() {
		Profesor profesor = givenTengoUnProfesor();
		whenElProfesorNoExiste(profesor);
		thenElProfesorSeGuardaConExito(profesor);
	}

	private Profesor givenTengoUnProfesor() {
		Profesor profesor = new Profesor();
		profesor.setEmail(EMAIL);
		profesor.setPassword(PASSWORD);
		profesor.setRol(ROL);
		return profesor;
	}

	private void whenElProfesorNoExiste(Profesor profesor) {
		if(servicioProfesor.existeProfesor(profesor)== false) {
			servicioProfesor.agregarProfesor(profesor);
		}
	}

	private void thenElProfesorSeGuardaConExito(Profesor profesor) {
		assertThat(servicioProfesor.existeProfesor(profesor)).isNotNull();
	}

}
