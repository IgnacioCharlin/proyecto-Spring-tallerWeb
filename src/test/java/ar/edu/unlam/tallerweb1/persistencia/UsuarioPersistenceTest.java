package ar.edu.unlam.tallerweb1.persistencia;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import static org.assertj.core.api.Assertions.*;

import javax.transaction.Transactional;

public class UsuarioPersistenceTest extends SpringTest{
	
	//Cuando se corra un test que va contra la bdd Se coloca la 
	//anotacion transactional y Rollback para que los datos de mis pruebas no se guarden en la bdd
	@Test @Transactional @Rollback(true)
	public void poderGuardarUnUsuario() {
		Usuario usuario = givenExisteUnUsuario();
		
		Long idUsuario = whenGuardoElUsuario(usuario);
		
		thenLoPuedoBuscarPorSuId(idUsuario);
	}

	@Test @Transactional @Rollback(true)
	public void poderModificarUnUsuario() {
		Usuario usuario = givenExisteUnUsuario();
		Long idUsuario = givenPersistoUnUsuario(usuario);
		String emailNuevo = "abc@abc.com";
		usuario.setEmail(emailNuevo);
		
		whenModificoElUsuario(usuario);;
		
		thenUsuarioCambioElEmail(idUsuario,emailNuevo);
	}
	
	
	private void thenUsuarioCambioElEmail(Long idUsuario, String nuevoNombre) {
		Usuario usuarioBuscado =session().get(Usuario.class,idUsuario);
		assertThat(usuarioBuscado.getEmail()).isEqualTo(nuevoNombre);
	}

	private void whenModificoElUsuario(Usuario usuario) {
		session().update(usuario);
		
	}

	private Long givenPersistoUnUsuario(Usuario usuario) {
		session().save(usuario);
		return usuario.getId();	
	}

	private Usuario givenExisteUnUsuario() {
		return new Usuario();
		
	}
	private Long whenGuardoElUsuario(Usuario usuario) {
		session().save(usuario);
		return usuario.getId();
	}

	private void thenLoPuedoBuscarPorSuId(Long idUsuario) {
		Usuario usuarioBuscado =session().get(Usuario.class,idUsuario);
		assertThat(usuarioBuscado).isNotNull();
	}

}
