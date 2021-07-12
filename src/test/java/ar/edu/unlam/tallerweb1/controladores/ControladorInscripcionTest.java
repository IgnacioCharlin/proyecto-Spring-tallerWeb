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
import ar.edu.unlam.tallerweb1.servicios.ServicioAsistencia;
import ar.edu.unlam.tallerweb1.servicios.ServicioClase;
import ar.edu.unlam.tallerweb1.servicios.ServicioInscribirse;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

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
	
	@Before
	public void init() {
		servicioUsuario = mock(ServicioUsuario.class);
		servicioClase = mock(ServicioClase.class);
		servicioAsistencia = mock(ServicioAsistencia.class);
		servicioInscribirse = mock(ServicioInscribirse.class);
		controladorInscribirseClases = new ControladorInscibirseClases(servicioInscribirse, servicioClase, servicioUsuario, servicioAsistencia);
		
	}
	
	@Test
	public void  queUnUsuarioSePuedaInscribirAUnaClase() {
		Usuario usuario = givenUnUsuario();
		Clase clase = gicenUnaClase();
		
		mav = whenInscriboAlUsuarioEnLaClase(usuario, clase);
		
		thenMeRedireccionaAlHome(mav);
				
	}
	@Test
	public void  siElRolNoEsUsuarioNoSePuedeInscribir() {
		Usuario usuario = givenUnUsuarioRolProfesor();
		Clase clase = gicenUnaClase();
		
		mav = whenVouALaVistaInscribirme(usuario, clase);
		
		thenMeRedireccionaAlHomeConMensajeDeError(mav);
				
	}

	private ModelAndView whenVouALaVistaInscribirme(Usuario usuario, Clase clase) {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(servicioUsuario.consultarUsuarioPorId(IDUSUARIO)).thenReturn(usuario);
		doReturn(IDUSUARIO).when(request).getAttribute("idUsuario");
//		when(request.getSession().getAttribute("idUsuario")).thenReturn(IDUSUARIO);
		return controladorInscribirseClases.inscribirseAunaClase(usuario, clase.getId(), request);
	}

	private void thenMeRedireccionaAlHomeConMensajeDeError(ModelAndView mav2) {
		assertThat(mav.getViewName()).isEqualTo("home");
		assertThat(mav.getModel().get("error")).isEqualTo("Solo los Usuarios se puden inscribir a las clases");
	}

	private Usuario givenUnUsuarioRolProfesor() {
		Usuario usuario = new Usuario();
		usuario.setId(IDUSUARIO);
		usuario.setRol("profesor");
		return usuario;
	}

	private void thenMeRedireccionaAlHome(ModelAndView mav) {
		assertThat(mav.getViewName()).isEqualTo(REDIRECT_HOME);
	}

	private ModelAndView whenInscriboAlUsuarioEnLaClase(Usuario usuario, Clase clase) {
		when(servicioInscribirse.buscarInscripcion(clase,usuario)).thenReturn(null);
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
