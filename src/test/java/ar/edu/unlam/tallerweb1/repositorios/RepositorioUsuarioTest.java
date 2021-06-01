package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

public class RepositorioUsuarioTest extends SpringTest {
	private final String EMAIL = "asd@asd.com";
	private final String CONTRASEÑA="123";
	private final String ROL ="Administrador";
	private final long ID = 2L;
	
	
    @Autowired
    private RepositorioUsuario repositorioUsuario;
    
    @Test @Transactional @Rollback
    public void queSePuedaAgregarUnUsuario() {
    	Usuario usuario = givenCreoUnUsuario();
    	whenElUsuarioNoExisteYTodoEstaCorrectoGuarda(usuario);
    	thenElUsuarioSeGuardoExitosamente(usuario.getEmail());
    	
    }
    

	@Test @Transactional @Rollback
    public void modificarDeberiaCambiarLosDatos(){
		String contraseñaNueva = "456";
        Usuario	usuario = givenCreoUnUsuario();
        usuario.setPassword(contraseñaNueva);
        whenModificoElUsuario(usuario);
        thenElUsuarioSeModifica(usuario,contraseñaNueva);
    }

    @Test @Transactional @Rollback
    public void buscarUsuarioQueExisteDevuelveUnUsuario(){
        Usuario guardado = givenCreoUnUsuario();
        repositorioUsuario.guardar(guardado);
        Usuario buscado = whenBuscoElUsuario(guardado.getEmail());
        thenElUsuarioExiste(buscado);
    }

    @Test @Transactional @Rollback
    public void buscarUsuarioQueNoExisteNoDevuelveUnUsuario(){
        Usuario guardado = givenCreoUnUsuario();

        Usuario buscado = whenBuscoElUsuario(guardado.getEmail()+"kdkdkdkdkd");
        thenElUsuarioNoExiste(buscado);
    }

    private void thenElUsuarioNoExiste(Usuario buscado) {
        assertThat(buscado).isNull();
    }

    private void thenElUsuarioExiste(Usuario buscado) {
        assertThat(buscado).isNotNull();
    }

    private Usuario whenBuscoElUsuario(String email) {
        return repositorioUsuario.buscar(email);
    }

    private void thenElUsuarioSeModifica(Usuario usuario,String contraseñaNueva) {
        assertThat(usuario.getPassword()).isEqualTo(contraseñaNueva);
    }

    private void whenModificoElUsuario(Usuario usuario) {
        repositorioUsuario.modificar(usuario);
    }

   
    private Usuario givenCreoUnUsuario() {
    	Usuario usuario = new Usuario();
    	usuario.setId(ID);
    	usuario.setEmail(EMAIL);
    	usuario.setPassword(CONTRASEÑA);
    	usuario.setRol(ROL);
    	return usuario;
    }
    
    private void whenElUsuarioNoExisteYTodoEstaCorrectoGuarda(Usuario usuario) {
    	repositorioUsuario.guardar(usuario);
    }
    
    private void thenElUsuarioSeGuardoExitosamente(String email) {
    	assertThat(repositorioUsuario.buscar(EMAIL)).isNotNull();
    }
}
