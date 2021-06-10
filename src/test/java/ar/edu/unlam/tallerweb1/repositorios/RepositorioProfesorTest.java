package ar.edu.unlam.tallerweb1.repositorios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Profesor;

public class RepositorioProfesorTest extends SpringTest{

	@Autowired
	private RepositorioProfesor repositorioProfesor;
	
	private final String EMAIL ="asd@asd.com";
	private final String PASSWORD ="123";
	private final String ROL="profesor";
	
	
	
	@Test @Transactional @Rollback
	public void queSePuedarGuardarUnProfesor() {
		Profesor profesor = givenCreoUnProfesor();
		whenElProfesorSeGuarda(profesor);
		thenElProfesorSeGuardaConExisto(profesor);
	}
	
	@Test @Transactional @Rollback
	public void queNoSePuedarGuardarUnProfesorQueYaExiste() {
		String contraseñaNueva = "asdqwe";
		Profesor profesor = givenCreoUnProfesor();
		Profesor profesor2 = givenCreoUnProfesor();
		profesor2.setPassword(contraseñaNueva);
		whenElProfesorSeGuarda(profesor);
		whenElProfesorSeGuarda(profesor2);
		thenElProfesorNoSeGuardo(profesor2,contraseñaNueva);
	}
	
	@Test @Transactional @Rollback
	public void queNoSePuedarGuardarUnProfesorQueNoLoEs() {
		Profesor profesor = givenCreoUnProfesor();
		profesor.setRol("alumno");
		whenElProfesorSeGuarda(profesor);
		thenElProfesorNoExiste(profesor);
	}

	private void thenElProfesorNoExiste(Profesor profesor) {
		assertThat(repositorioProfesor.buscarProfesorPorMail(EMAIL)).isNull();
	}

	private void thenElProfesorNoSeGuardo(Profesor profesor,String contraseña) {
		assertThat(repositorioProfesor.buscarProfesorPorMail(profesor.getEmail()).getPassword()).isNotEqualTo(contraseña);
	}

	private Profesor givenCreoUnProfesor() {
		Profesor profesor = new Profesor();
		profesor.setEmail(EMAIL);
		profesor.setPassword(PASSWORD);
		profesor.setRol(ROL);
		return profesor;
	}

	private void whenElProfesorSeGuarda(Profesor profesor) {
		if(repositorioProfesor.buscarProfesorPorMail(profesor.getEmail())==null) {
			repositorioProfesor.agregarProfesor(profesor);
		}
	}

	private void thenElProfesorSeGuardaConExisto(Profesor profesor) {
		assertThat(repositorioProfesor.buscarProfesorPorMail(profesor.getEmail())).isNotNull();
	}
}
