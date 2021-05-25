package ar.edu.unlam.tallerweb1.repositorios;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Clase;

public class RepositorioClaseTest extends SpringTest{

	private final String NOMBRE_CLASE = "funcional";
	private final long CAPACIDAD=10;
	private final Clase CLASE = new Clase();
	@Autowired
	private RepositorioClase repositorioClase;
	
	
	
	@Test @Transactional @Rollback
	public void queSiLaClaseNoExisteSePuedaGuardar() {
		Clase clase = whenSeGuardanLosDatos(CLASE,NOMBRE_CLASE,CAPACIDAD);
		thenSeRegistroConExito(clase);
	}
	
	@Test @Transactional @Rollback
	public void queSiLaClaseExisteNoSePuedaGuardar() {
		Clase claseCopia =  new Clase();
		Clase clase = whenSeGuardanLosDatos(CLASE,NOMBRE_CLASE,CAPACIDAD);
		Clase clase2 = whenSeGuardanLosDatos(claseCopia,NOMBRE_CLASE,CAPACIDAD);
		thenSeNoSeRegistro(clase2);
	}
	
	@Test @Transactional @Rollback
	public void queBusqueLaClasePorElNombreYLaDevuelva() {
		Clase clase = whenSeGuardanLosDatos(CLASE,NOMBRE_CLASE,CAPACIDAD);
		thenDevuelveLaClase(clase.getNombre());
	}
	
	@Test @Transactional @Rollback
	public void queBusqueLaClasePorElNombreYNoExista() {
		Clase clase = whenSeGuardanLosDatos(CLASE,NOMBRE_CLASE,CAPACIDAD);
		thenNoDevuelveLaClase(clase.getNombre()+"aas");
	}
	
	@Test @Transactional @Rollback
	public void queSePuedaModificarUnaClase() {
		long nuevaCapacidad = 25;
		Clase clase = whenSeGuardanLosDatos(CLASE,NOMBRE_CLASE,CAPACIDAD);
		clase.setCapacidad(nuevaCapacidad);
		thenLaClaseSeModifico(clase,nuevaCapacidad);
	}
	
	@Test @Transactional @Rollback
	public void queSePuedaEliminarUnaClase() {
		Clase clase = whenSeGuardanLosDatos(CLASE,NOMBRE_CLASE,CAPACIDAD);
		thenLaClaseSeElimino(clase);
	}
	
	private void thenLaClaseSeElimino(Clase clase) {
		repositorioClase.eliminarClase(clase);
		assertNull(repositorioClase.buscarClase(clase.getNombre()));
		
	}

	private void thenLaClaseSeModifico(Clase clase, long nuevaCapacidad) {
		repositorioClase.modificarClase(clase);
		assertEquals((long)clase.getCapacidad(), nuevaCapacidad);
	}

	private void thenNoDevuelveLaClase(String nombre) {
		Clase buscada = repositorioClase.buscarClase(nombre);
		assertNull(buscada);
	}

	private void thenDevuelveLaClase(String nombre) {
		Clase buscada = repositorioClase.buscarClase(nombre);
		assertNotNull(buscada);
		assertEquals(nombre, buscada.getNombre());
	}

	private void thenSeNoSeRegistro(Clase clase) {
		assertNull(clase);
	}

	private Clase whenSeGuardanLosDatos(Clase clase,String nombre, long capacidad) {
		if(repositorioClase.buscarClase(nombre)==null) {
			clase.setNombre(nombre);
			clase.setCapacidad(capacidad);
			return repositorioClase.guardarClase(clase);
		}
		return null;
	}


	private void thenSeRegistroConExito(Clase clase) {
		Clase claseBuscada = repositorioClase.buscarClase(clase.getNombre());
		assertEquals(claseBuscada.getNombre(), NOMBRE_CLASE);
	}

}
