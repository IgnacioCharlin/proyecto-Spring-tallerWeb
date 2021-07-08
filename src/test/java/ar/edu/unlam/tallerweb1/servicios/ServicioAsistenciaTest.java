package ar.edu.unlam.tallerweb1.servicios;

import static org.assertj.core.api.Assertions.assertThat; 
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

 
import ar.edu.unlam.tallerweb1.excepciones.NoTengoClase;
import ar.edu.unlam.tallerweb1.excepciones.NoTengoUsuario;
import ar.edu.unlam.tallerweb1.excepciones.UsuarioNoEstaPresenteEnLaClase;
import ar.edu.unlam.tallerweb1.modelo.AsistenciaClase;
import ar.edu.unlam.tallerweb1.modelo.Clase;
 import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAsistencia;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClase;
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

	  
	  
	 
	  @Test
	  public void siTengoClaseYusuarioPuedoActualizarAsistencia() {
		  Usuario usuario= givenTengoUnUsuario();
		  Clase clase= givenObtenClase();
 		  Integer presente = 1;
  		  AsistenciaClase buscoAsistencia =   whenActualizoAsistencia(clase,usuario,presente);
  		  Integer presenteActualizado = 0;
		  
		  thenPuedeActualizarAsistencia(buscoAsistencia,presenteActualizado);

	  }
	  
	  
		
	  @Test(expected = UsuarioNoEstaPresenteEnLaClase.class)
	  public void siNoTengoUsuarioPresenteNoPuedoCalificarLaClase() {
		  Usuario usuario= givenTengoUnUsuario();
		  Clase clase= givenObtenClase();
		  Integer alumnoPresente = 1;
		  
		  whenVerificoAsistencia(clase,usuario,alumnoPresente);
		  
	  }
	  
	  
	  
		private AsistenciaClase whenVerificoAsistencia(Clase clase, Usuario usuario,Integer alumnoPresente) {
			AsistenciaClase	alumnoAusente =  null;
			
			when(repositorioAsistencia.buscarPorUsuarioYClase(usuario, clase,alumnoPresente)).thenReturn(alumnoAusente);
			AsistenciaClase  consultoAsistencia= servicioAsistencia.consultarAsistenciaPorClaseYusuario(clase,usuario,alumnoPresente);
			return consultoAsistencia;
 	}



		private void thenPuedeActualizarAsistencia(AsistenciaClase buscoAsistencia, Integer presente) { 
			verify(repositorioAsistencia,times(1)).modificarAsistencia(buscoAsistencia,presente);
		}
		  
		
	  
	  

		private void thenPuedoCargarAsistencia(Clase clase, Usuario usuario) { 
			verify(repositorioAsistencia,times(1)).guardarAsistencia(clase,usuario);
		}
		  
		
		

		private void thenPuedoBuscar(Clase clase, Usuario usuario) { 
			verify(repositorioAsistencia,times(1)).buscarPorUsuarioYClase(usuario,clase);
		}
		  
 
	  

	  
		private AsistenciaClase whenActualizoAsistencia(Clase clase, Usuario usuario,Integer presente) {
 		 	AsistenciaClase buscoAsistencia=new AsistenciaClase(usuario,clase,presente);
			when(repositorioAsistencia.buscarPorUsuarioYClase(usuario, clase)).thenReturn(buscoAsistencia);
			return servicioAsistencia.actualizarAsistencia(clase,usuario); 
			  

		}
		
		
		private AsistenciaClase whenCargoAsistencia(Clase clase, Usuario usuario) {
			return servicioAsistencia.cargarAsistencia(clase,usuario);
			
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
