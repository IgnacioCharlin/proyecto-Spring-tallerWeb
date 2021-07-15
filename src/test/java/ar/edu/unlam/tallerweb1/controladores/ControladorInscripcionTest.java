package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuariosFichas;
import ar.edu.unlam.tallerweb1.servicios.ServicioAsistencia;
import ar.edu.unlam.tallerweb1.servicios.ServicioClase;
import ar.edu.unlam.tallerweb1.servicios.ServicioInscribirse;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarioFichas;

public class ControladorInscripcionTest {

	private static final long IDCLASE = 1L;
	private static final long IDUSUARIO = 2L;
	private final String REDIRECT_HOME = "redirect:/home";
	private ModelAndView mav;
	private ControladorInscibirseClases controladorInscribirseClases;
	private ServicioUsuario servicioUsuario;
	private ServicioClase servicioClase;
	private ServicioAsistencia servicioAsistencia;
	private ServicioInscribirse servicioInscribirse;
	private ServicioUsuarioFichas servicioUsuarioFichas;
	@Before
	public void init() {
		servicioUsuario = mock(ServicioUsuario.class);
		servicioClase = mock(ServicioClase.class);
		servicioAsistencia = mock(ServicioAsistencia.class);
		servicioInscribirse = mock(ServicioInscribirse.class);

		servicioUsuarioFichas = mock(ServicioUsuarioFichas.class);
		controladorInscribirseClases = new ControladorInscibirseClases(servicioInscribirse, servicioClase, servicioUsuario, servicioAsistencia, servicioUsuarioFichas );

		
	}
	
	@Test
	public void  queUnUsuarioSePuedaInscribirAUnaClase() {
		Usuario usuario = givenUnUsuario();
		Clase clase = gicenUnaClase();
		
		mav = whenInscriboAlUsuarioEnLaClase(usuario, clase);
		
		thenMeRedireccionaAlHome(mav);
				
	}
  

	private void thenMeRedireccionaAlHome(ModelAndView mav) {
		//assertThat(mav.getViewName()).isEqualTo(REDIRECT_HOME);
 		assertThat(mav.getModel().get("msj")).isEqualTo("La inscripción se logró correctamente.");


	}

	private ModelAndView whenInscriboAlUsuarioEnLaClase(Usuario usuario, Clase clase) {
		when(servicioInscribirse.buscarInscripcion(clase,usuario)).thenReturn(null);
		 UsuariosFichas fichasDisponible = new UsuariosFichas();
		 fichasDisponible.setCantidad(2);
		 fichasDisponible.setUsuario(usuario);
		 
			when(servicioUsuarioFichas.buscarFichasPorUsuario(usuario.getId())).thenReturn(fichasDisponible);

		return controladorInscribirseClases.confirmaInscripcion(usuario.getId(), clase.getId());
		
	}

	private Clase gicenUnaClase() {
		Clase clase = new Clase();
		clase.setId(IDCLASE);
		return clase;
	}

	private Usuario givenUnUsuario() {
		Usuario usuario = new Usuario();
		usuario.setId(IDUSUARIO);
		return usuario;
	}
	
}
