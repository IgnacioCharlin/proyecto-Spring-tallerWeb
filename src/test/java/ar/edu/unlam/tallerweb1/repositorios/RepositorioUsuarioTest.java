package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class RepositorioUsuarioTest extends SpringTest {
	private final String EMAIL = "asd@asd.com";
	private final String CONTRASEÑA="123";
	private final String ROL ="Administrador";
	private final long ID = 2L;
	
	
    @Autowired
    private RepositorioUsuario repositorioUsuario;
    
    @Autowired
    private RepositorioClase repositorioClase;
    
    @Test @Transactional @Rollback
    public void queSePuedaAgregarUnUsuario() {
    	Usuario usuario = givenCreoUnUsuario();
    	whenElUsuarioNoExisteYTodoEstaCorrectoGuarda(usuario);
    	thenElUsuarioSeGuardoExitosamente(usuario.getEmail());
    	
    }
    
    @Test @Transactional @Rollback
    public void queNoSePuedaAgregarDosUsuariosConElMismoMail() {
    	String rolNuevo = "Profesor";
    	String contraseñaNueva ="789";
    	Usuario usuario1 = givenCreoUnUsuario();
    	Usuario usuario2 = givenCreoUnUsuario();
    	usuario2.setRol(rolNuevo);
    	usuario2.setPassword(contraseñaNueva);
    	whenGuardoLosUsuarioYTienenElMismoMail(usuario1,usuario2);
    	thenNoSeGuardaUsuario(usuario2,rolNuevo,contraseñaNueva);
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
    
    @Test @Transactional @Rollback
    public void queSeElimineUsuarioCreado(){
        Usuario	usuario = givenCreoUnUsuario();
        whenGuardoUsuarioYLuegoLoElimino(usuario);
        Usuario buscado = repositorioUsuario.buscar(EMAIL);
        thenElUsuarioNoExiste(buscado);
    }
    
    @Test @Transactional @Rollback
    public void queSePuedaListarLasClasesQueAsistioUnUsuario(){
        Usuario usuario = givenCreoUnUsuario();
        usuario = givenClasesQueAsisteUnUsuario(usuario);
        whenElusuarioAsisteAUnaClase(usuario);
        thenLasCantidadDeClasesSonLasQueAsistio(usuario);
        
    }
    
<<<<<<< HEAD
    @Test @Transactional @Rollback
    public void queTraigaTodasLasClasesQueSeInscribioElAlumno(){
       Usuario usuario = givenTengoUnUsuarioConClases();
       whenGuardoLasClasesAlUsuario(usuario);
       thenTraeLasClasesInscriptas(usuario.getEmail());
    }


    private void thenTraeLasClasesInscriptas(String email) {
    	assertThat(repositorioUsuario.buscar(email)).isNotNull();
    	assertThat(repositorioUsuario.buscar(email).getClases()).hasSize(2);
    }

    private void whenGuardoLasClasesAlUsuario(Usuario usuario) {
    	List<Clase> clases = repositorioClase.buscarTodasLasClase();
    	for (Clase clase : clases) {
    		usuario.setClase(clase);
    	}
    	repositorioUsuario.guardar(usuario);
    }

    private Usuario givenTengoUnUsuarioConClases() {
    	String nombre = "Funcional";
    	String horarioYFecha ="2022-01-01";
    	long capacidad = 50L;
    	Profesor profesor = new Profesor();
    	Usuario usuario = givenCreoUnUsuario();
    	Clase clase1 = new Clase();
    	Clase clase2 = new Clase();
    	clase1.setId(1L);
    	clase1.setNombre(nombre);
    	clase1.setCapacidad(capacidad);
    	clase1.setHorarioYFecha(horarioYFecha);
    	clase1.setProfesor(profesor);
    	clase2.setId(2L);
    	clase2.setNombre(nombre+"asd");
    	clase2.setHorarioYFecha(horarioYFecha);
    	clase2.setCapacidad(capacidad);
    	clase2.setProfesor(profesor);
    	
    	repositorioClase.guardarClase(clase2);
    	repositorioClase.guardarClase(clase1);
    	return usuario;
    }
=======
    
>>>>>>> 902e334fc01fec5d6f99e51da526c199bd61338d
    

	private void thenLasCantidadDeClasesSonLasQueAsistio(Usuario usuario) {
	Usuario historialClases =repositorioUsuario.buscarPorId(usuario.getId());
	for (Clase asistida : historialClases.getClases()) {
		System.out.println(asistida.getNombre());
		
	}
		assertThat(historialClases.getClases().size()).isEqualTo(usuario.getClases().size());
	}

	private void whenElusuarioAsisteAUnaClase(Usuario usuario) {
		repositorioUsuario.modificar(usuario);
		
	}

	private Usuario givenClasesQueAsisteUnUsuario(Usuario usuario) {
		Profesor carlos = new Profesor();
		Clase funcional = new Clase("funcional","2021-06-23 20:30",carlos,50l);
		Clase crossfit = new Clase("crossfit","2021-06-23 20:30",carlos,50l);
		Clase pilates = new Clase("Pilates","2021-06-23 20:30",carlos,50l);
		Clase musculacion = new Clase("Musculacion","2021-06-23 20:30",carlos,50l);
		session().save(usuario);
		session().save(musculacion);
		session().save(funcional);
		session().save(pilates);
		session().save(crossfit);
	
		usuario.setClase(musculacion);
		usuario.setClase(pilates);
		usuario.setClase(funcional);
		return usuario;
		
	}

	private void whenGuardoUsuarioYLuegoLoElimino(Usuario usuario) {
		repositorioUsuario.guardar(usuario);
		repositorioUsuario.eliminar(usuario);
	}

	private void whenGuardoLosUsuarioYTienenElMismoMail(Usuario usuario1, Usuario usuario2) {
    	repositorioUsuario.guardar(usuario1);
    	repositorioUsuario.guardar(usuario2);
    }
    
    private void thenNoSeGuardaUsuario(Usuario usuario,String rolNuevo , String contraseñaNueva) {
    	assertThat(repositorioUsuario.buscar(EMAIL)).isNotNull();
    	assertThat(repositorioUsuario.buscar(EMAIL).getRol()).isNotEqualTo(rolNuevo);
    	assertThat(repositorioUsuario.buscar(EMAIL).getPassword()).isNotEqualTo(contraseñaNueva);
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











