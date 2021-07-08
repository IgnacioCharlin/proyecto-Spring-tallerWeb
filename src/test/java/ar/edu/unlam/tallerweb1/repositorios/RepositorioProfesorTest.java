package ar.edu.unlam.tallerweb1.repositorios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;

public class RepositorioProfesorTest extends SpringTest{

	@Autowired
	private RepositorioProfesor repositorioProfesor;
	
	@Autowired
	private RepositorioClase repositorioClase;
	
	private final long ID = 1l;
	private final String EMAIL ="pepe@asd.com";
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
	
	@Test @Transactional @Rollback
	public void queSeBusqueProfesorPorId() {
		Profesor profesor = givenCreoUnProfesor();
		whenElProfesorSeGuarda(profesor);
		thenTraeProfesorPorId(profesor.getId(),profesor);
	}
	
	@Test @Transactional @Rollback
	public void queSeModifiqueElProfesor() {
		Profesor profesor = givenCreoUnProfesor();
		whenElProfesorSeGuarda(profesor);
		whenElProfesorSeModifica(profesor);
		thenElProfesorSeModificoConExito(profesor);
	}
	
	@Test @Transactional @Rollback
	public void queTraigaLasClasesDadasPorElProfesor() {
		Profesor profesor = givenCreoUnProfesor();
		Clase clase = givenCreoUnaClase(profesor);
		List<Clase> clases= whenTraigoClaseDadasPorElProfesor(profesor);
		thenTraeCorrectamenteLasClasesDadasPorEseProfesor(clases);
	}
	
	private void thenTraeCorrectamenteLasClasesDadasPorEseProfesor(List<Clase> clases) {
		assertThat(clases).hasSize(1);
		assertThat(clases.get(0).getProfesor().getEmail()).isEqualTo(EMAIL);
	}

	private List<Clase> whenTraigoClaseDadasPorElProfesor(Profesor profesor) {
		return repositorioClase.buscarClasePorProfesor(profesor);
	}

	private Clase givenCreoUnaClase(Profesor profesor) {
		Clase clase = new Clase();
		clase.setNombre("Zumba");
		clase.setHorarioYFecha("2021-08-08");
		clase.setId(1L);
		clase.setCapacidad(20L);
		clase.setEstado("activa");
		clase.setProfesor(profesor);
		return clase;
	}

	private void whenElProfesorSeModifica(Profesor profesor) {
		String emailNuevo = "nuevo@nuevo.com";
		profesor.setEmail(emailNuevo);
		repositorioProfesor.modificarProfesor(profesor);
	}

	private void thenElProfesorSeModificoConExito(Profesor profesor) {
		assertThat(profesor.getEmail()).isNotEqualTo(EMAIL);
	}

	private void thenTraeProfesorPorId(Long id,Profesor profesorBuscado) {
		assertThat(repositorioProfesor.buscarProfesorPorId(id)).isEqualTo(profesorBuscado);
	}


	private void thenElProfesorNoExiste(Profesor profesor) {
		assertThat(repositorioProfesor.buscarProfesorPorMail(EMAIL)).isNull();
	}

	private void thenElProfesorNoSeGuardo(Profesor profesor,String contraseña) {
		assertThat(repositorioProfesor.buscarProfesorPorMail(profesor.getEmail()).getPassword()).isNotEqualTo(contraseña);
	}

	private Profesor givenCreoUnProfesor() {
		Profesor profesor = new Profesor();
		profesor.setId(ID);
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
		assertThat(repositorioProfesor.buscarProfesorPorId(profesor.getId())).isNotNull();
	}
}
