package ar.edu.unlam.tallerweb1.servicios;

import static org.assertj.core.api.Assertions.assertThat; 
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.tallerweb1.excepciones.FaltaCupo;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoUnaFecha;
import ar.edu.unlam.tallerweb1.excepciones.NoTengoClase;
import ar.edu.unlam.tallerweb1.excepciones.NoTengoUsuario;
import ar.edu.unlam.tallerweb1.excepciones.UsuarioNoExiste;
import ar.edu.unlam.tallerweb1.modelo.AsistenciaClase;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.DatosClase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAsistencia;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClase;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProfesor;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

public class ServicioAsistenciaTest {
 

	private ServicioAsistenciaImpl servicioAsistencia;
 	private RepositorioAsistencia repositorioAsistencia;
	private RepositorioClase repositorioClase;
	private RepositorioUsuario repositorioUsuario;
	
	@Before
	public void init() {
		repositorioClase = mock(RepositorioClase.class);
		repositorioUsuario = mock(RepositorioUsuario.class);
		repositorioAsistencia = mock(RepositorioAsistencia.class);
		servicioAsistencia = new ServicioAsistenciaImpl(repositorioAsistencia,repositorioUsuario,repositorioClase);
 	}

 
	
	  @Test(expected = NoTengoUsuario.class)
	  public void siNoTengoUsuarioNoPuedoCargarAsistencia() {
		  Usuario usuario= null;
		  Clase clase= givenObtenClase();
		  whenCargoAsistencia(clase,usuario);
		  
	  }
	  
	  
	  
	  @Test(expected = NoTengoClase.class)
	  public void siNoTengoClaseNoPuedoCargarAsistencia() {
		  Usuario usuario= givenTengoUnUsuario();
		  Clase clase= null;
		  whenCargoAsistencia(clase,usuario);
		  
	  }
	  
	  

	  
	  
	  
	  @Test
	  public void siTengoClaseYusuarioPuedoBuscarAsistencia() {
		  Usuario usuario= givenTengoUnUsuario();
		  Clase clase= givenObtenClase();
		  whenCargoAsistencia(clase,usuario);
		  thenPuedoBuscar(clase,usuario);

	  }

	  
	  
	  @Test
	  public void siTengoClaseYusuarioPuedoCargarAsistencia() {
		  Usuario usuario= givenTengoUnUsuario();
		  Clase clase= givenObtenClase();
		  whenCargoAsistencia(clase,usuario);
		  thenPuedoCargarAsistencia(clase,usuario);

	  }

	  
	  
	/*  
	  @Test
	  public void siTengoClaseYusuarioPuedoActualizarAsistencia() {
		  Usuario usuario= givenTengoUnUsuario();
		  Clase clase= givenObtenClase();
		  whenCargoAsistencia(clase,usuario);
		  whenCargoAsistencia(clase,usuario);
		  thenPuedoActualizarAsistencia(clase,usuario);

	  }
	  
		private void thenPuedoActualizarAsistencia(Clase clase, Usuario usuario) { 
			verify(repositorioAsistencia,times(1)).modificarAsistencia(clase,usuario);
		}
		  
		*/
	  
	  

		private void thenPuedoCargarAsistencia(Clase clase, Usuario usuario) { 
			verify(repositorioAsistencia,times(1)).guardarAsistencia(clase,usuario);
		}
		  
		
		

		private void thenPuedoBuscar(Clase clase, Usuario usuario) { 
			verify(repositorioAsistencia,times(1)).buscarPorUsuarioYClase(usuario,clase);
		}
		  
 
	  

	    
		private void whenCargoAsistencia(Clase clase, Usuario usuario) {
			servicioAsistencia.actualizarAsistencia(clase,usuario);
		}
		
		
		
		
		private Clase givenObtenClase() {
			Profesor carlos = new Profesor();  
			Clase clase = new Clase("funcional","2021-06-23 20:30",carlos,50l);
				return clase;  
	}

	
    private Usuario givenTengoUnUsuario() {
    	Usuario usuario = new Usuario();
    	usuario.setId((long)1);
    	usuario.setEmail("asd@asd.com");
    	usuario.setPassword("asd");
    	usuario.setRol("");

    	return usuario;
    }
    
    
		
}
