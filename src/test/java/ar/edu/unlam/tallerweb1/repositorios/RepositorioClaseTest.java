package ar.edu.unlam.tallerweb1.repositorios;

import static org.junit.Assert.*;
import static org.mockito.Matchers.isNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Clase;

public class RepositorioClaseTest extends SpringTest{
	private final String NOMBRE_DE_LA_CLASE ="funcional";
	private final Long CAPACIDAD =(long)10;
	@Autowired
	private RepositorioClase repositorioClase;
	
	@Test @Transactional @Rollback
	public void queSiLaClaseNoExisteSePuedaGuardar() {
		Clase clase = givenCreoLaClase();
		whenLaClaseNoExiste(clase,NOMBRE_DE_LA_CLASE,CAPACIDAD);
		thenGuardadoDeClaseExitoso(clase);
	}
	private Clase givenCreoLaClase() {
		return new Clase();
	}
	private void whenLaClaseNoExiste(Clase clase,String nombre, Long capacidad) {
		if(repositorioClase.buscarClase(nombre)==null) {
			clase.setNombre(nombre);
			clase.setCapacidad(capacidad);
			session().save(clase);
		}
	}
	
	private void thenGuardadoDeClaseExitoso(Clase clase) {
		Clase buscada = session().get(Clase.class, clase.getNombre());
	}

}
