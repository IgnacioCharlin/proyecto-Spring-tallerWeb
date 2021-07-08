package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.AsistenciaClase;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.ClasesInscriptas;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class RepositorioClasesInscriptasTest extends SpringTest {
 
	private final String EMAIL = "asd@asd.com";
	private final String CONTRASEÑA="123";
	private final String ROL ="Administrador";
	private final long ID = 2L;
	
    @Autowired
    private RepositorioInscribirse repositorioInscribirse;
    
    @Autowired
    private RepositorioClase repositorioClase;
    
    @Autowired
    private RepositorioUsuario repositorioUsuario;
    
    @Autowired
    private RepositorioAsistencia repositorioAsistencia;
    
    
 
    @Test @Transactional @Rollback
    public void queTraigaTodasLasClasesQueSeInscribioElAlumno(){
        Usuario usuario = givenCreoUnUsuario();
        Clase clase = givenObtengoClase();
        
        whenElusuarioSeInscribeAUnaClase(clase,usuario);
        thenLasCantidadDeClasesSonLasQueSeInscribio(usuario);
        
    }

     
    @Test @Transactional @Rollback
    public void queTraigaPlanilladeAsistenciaDeLaClasesQueSeInscribioElAlumno(){
        Usuario usuario = givenCreoUnUsuario();
        Clase clase = givenObtengoClase();
        
        whenElusuarioTienePlanilladeAsistenciaDeUnaClase(clase,usuario);
        thenLasCantidadDePlanillasSonLasQueSeInscribio(usuario);
        
    }

     
    
    
	private void thenLasCantidadDePlanillasSonLasQueSeInscribio(Usuario usuario) {

		List<AsistenciaClase>  planillaAsistencia =repositorioAsistencia.dameAsistenciaPorUsuario(usuario);
		 
		assertThat(planillaAsistencia.size()).isEqualTo(1);
		
	}


	private void whenElusuarioTienePlanilladeAsistenciaDeUnaClase(Clase clase, Usuario usuario) {
 		 repositorioAsistencia.guardarAsistencia(clase,usuario);
		
	}


	private void thenLasCantidadDeClasesSonLasQueSeInscribio(Usuario usuario) {
	List<ClasesInscriptas> historialClases =repositorioInscribirse.buscarPorUsuario(usuario.getId());
 
		assertThat(historialClases.size()).isEqualTo(1);
	}

	private void whenElusuarioSeInscribeAUnaClase(Clase clase,Usuario usuario) {
 		 repositorioInscribirse.guardarInscripcion(clase,usuario);
	}

	private Clase givenObtengoClase() {
		Profesor carlos = new Profesor();
 		session().save(carlos); 

		Clase funcional = new Clase("funcional","2021-06-23 20:30",carlos,50l);
 		session().save(funcional); 
		return funcional;
		
	} 
   
    private Usuario givenCreoUnUsuario() {
    	Usuario usuario = new Usuario();
    	usuario.setId(ID);
    	usuario.setEmail(EMAIL);
    	usuario.setPassword(CONTRASEÑA);
    	usuario.setRol(ROL);
 		session().save(usuario); 

    	return usuario;
    }
    
 
 
}










