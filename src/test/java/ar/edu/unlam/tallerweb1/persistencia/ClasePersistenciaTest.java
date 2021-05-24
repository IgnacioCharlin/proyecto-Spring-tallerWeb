package ar.edu.unlam.tallerweb1.persistencia;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

public class ClasePersistenciaTest extends SpringTest{
	
	@Test @Transactional @Rollback(true)
	public void poderGuardarUnaClase() {
		Clase clase = givenExisteUnaClase();
		
		Long idClase = whenGuardoLaClase(clase);
		
		thenLoPuedoBuscarPorSuId(idClase);
	}

	private Clase givenExisteUnaClase() {
		return new Clase();
		
	}
	private Long whenGuardoLaClase(Clase clase) {
		session().save(clase);
		return clase.getId();
	}

	private void thenLoPuedoBuscarPorSuId(Long idUsuario) {
		Clase usuarioBuscado =session().get(Clase.class,idUsuario);
		assertNotNull(usuarioBuscado);
		assertEquals(idUsuario, usuarioBuscado.getId());
	}

}