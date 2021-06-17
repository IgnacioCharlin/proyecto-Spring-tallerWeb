package ar.edu.unlam.tallerweb1.repositorios;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;

public class RepositorioClaseTest extends SpringTest{

	private final String NOMBRE_CLASE = "funcional";
	private final long CAPACIDAD=10;
	//private final Profesor PROFESOR = new Profesor();
	private Long PROFESOR =2L;
	private final Clase CLASE = new Clase();
	
	@Autowired
	private RepositorioClase repositorioClase;
	
	
	
	@Test @Transactional @Rollback
	public void queSiLaClaseNoExisteSePuedaGuardar() {
		Clase clase = whenSeGuardanLosDatos(CLASE,NOMBRE_CLASE,CAPACIDAD,PROFESOR);
		thenSeRegistroConExito(clase);
	}
	
	@Test @Transactional @Rollback
	public void queSiLaClaseExisteNoSePuedaGuardar() {
		Clase claseCopia =  new Clase();
		Clase clase = whenSeGuardanLosDatos(CLASE,NOMBRE_CLASE,CAPACIDAD, PROFESOR);
		Clase clase2 = whenSeGuardanLosDatos(claseCopia,NOMBRE_CLASE,CAPACIDAD, PROFESOR);
		thenSeNoSeRegistro(clase2);
	}
	
	@Test @Transactional @Rollback
	public void queBusqueLaClasePorElNombreYLaDevuelva() {
		Clase claseBuscada = whenSeGuardanLosDatos(CLASE,NOMBRE_CLASE,CAPACIDAD, PROFESOR);
		thenDevuelveLaClase(claseBuscada.getNombre(),claseBuscada);
	}
	
	@Test @Transactional @Rollback
	public void queBusqueLaClasePorElNombreYNoExista() {
		Clase clase = whenSeGuardanLosDatos(CLASE,NOMBRE_CLASE,CAPACIDAD, PROFESOR);
		Clase buscada = repositorioClase.buscarClase(clase.getNombre()+"asd");
		thenNoDevuelveLaClase(buscada);
	}
	
	@Test @Transactional @Rollback
	public void queSePuedaModificarUnaClase() {
		long nuevaCapacidad = 25;
		Clase clase = whenSeGuardanLosDatos(CLASE,NOMBRE_CLASE,CAPACIDAD, PROFESOR);
		clase.setCapacidad(nuevaCapacidad);
		repositorioClase.modificarClase(clase);
		thenLaClaseSeModifico(clase,nuevaCapacidad);
	}
	
	@Test @Transactional @Rollback
	public void queSePuedaEliminarUnaClase() {
		Clase clase = whenSeGuardanLosDatos(CLASE,NOMBRE_CLASE,CAPACIDAD, PROFESOR);
		repositorioClase.eliminarClase(clase);
		thenLaClaseSeElimino(clase);
	}
	
	@Test @Transactional @Rollback
	public void queSeTraiganTodasLasClases() {
		Clase otraClase= new Clase();
		Clase clase = whenSeGuardanLosDatos(CLASE,NOMBRE_CLASE,CAPACIDAD, PROFESOR);
		Clase clase2 = whenSeGuardanLosDatos(otraClase,NOMBRE_CLASE+"asd",CAPACIDAD+10, PROFESOR);
		List clasesList=repositorioClase.buscarTodasLasClase();
		thenTraeTodasLasClases(clasesList);
	}
	
	/*
	@Test @Transactional @Rollback
	public void queTraigaLasClasesDadasPorUnProfesor() {
		Profesor profesor = givenCreoElProfesor();
		Profesor profesor2 = givenCreoElProfesor();
		Clase clase1 = new Clase();
		Clase clase2 = new Clase();
		Clase clase3 = new Clase();
		whenSeGuardanLosDatos(clase1, "Funcional", 50L,PROFESOR);
		whenSeGuardanLosDatos(clase2, "Zumba", 20L, PROFESOR);
		whenSeGuardanLosDatos(clase3, "Intensivo", 10L, 10L);
		List<Clase> clases = whenBuscoClasesConElProfesor(profesor);
		thenMeTraeTodasLasClases(clases,2);
		
	}
	*/
	private Profesor givenCreoElProfesor() {
		String emailProfesor= "asd@asd.com";
		Profesor profesor = new Profesor();
		profesor.setEmail(emailProfesor);
		return profesor;
	}
	
	private List<Clase> whenBuscoClasesConElProfesor(Profesor profesor) {
		List<Clase> clases = repositorioClase.buscarClasePorProfesor(profesor);
		return clases;
	}
	
	private void thenMeTraeTodasLasClases(List<Clase>clases, Integer cantidadEsperada) {
		assertThat(clases).hasSize(cantidadEsperada);
		
	}
	
	private void thenTraeTodasLasClases(List clasesList) {
		assertThat(clasesList.size()).isEqualTo(2);
	}

	private void thenLaClaseSeElimino(Clase clase) {
		assertThat(repositorioClase.buscarClase(clase.getNombre())).isNull();
	}

	private void thenLaClaseSeModifico(Clase clase, long nuevaCapacidad) {
		assertThat((long)clase.getCapacidad()).isEqualTo(nuevaCapacidad);
	}

	private void thenNoDevuelveLaClase(Clase buscada) {
		assertThat(buscada).isNull();
	}

	private void thenDevuelveLaClase(String nombre ,Clase claseABuscar) {
		assertThat(claseABuscar).isNotNull();
		assertThat(nombre).isEqualTo(claseABuscar.getNombre());
	}

	private void thenSeNoSeRegistro(Clase clase) {
		assertNull(clase);
	}

	private Clase whenSeGuardanLosDatos(Clase clase,String nombre, long capacidad,Long profesor) {
		if(repositorioClase.buscarClase(nombre)==null) {
			clase.setNombre(nombre);
			clase.setCapacidad(capacidad);
			clase.setProfesor(profesor);
			repositorioClase.guardarClase(clase);
			return repositorioClase.buscarClase(nombre);
		}
		return null;
	}


	private void thenSeRegistroConExito(Clase clase) {
		assertThat(clase.getNombre()).isEqualTo(NOMBRE_CLASE);
	}
	
}
