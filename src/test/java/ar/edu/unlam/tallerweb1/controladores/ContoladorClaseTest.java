package ar.edu.unlam.tallerweb1.controladores;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.assertThat;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.servicios.ServicioClase;


public class ContoladorClaseTest {

	private static final String FECHAYHORA = "miercoles 10hs";
	private static final Long ID = 2l;
	private final Long CUPO = 50L;
	private final String NOMBRE = "Funcional";
	private final String REDIRECT_LOGIN = "redirect:/login";
	private final String REDIRECT_HOME = "redirect:/home";
	ControladorClase controladorClase;
	private ModelAndView mav;
	private ServicioClase servicioClase;

	@Before
	public void init() {
		controladorClase = new ControladorClase();
		servicioClase = mock(ServicioClase.class);
	}

	@Test
	public void queSePuedaRegistrarLaClase() {
		DatosClase clase = givenSeCreaLaClase();
		whenRegistroLaClase(clase);
		thenLaClaseSeCreoConExito();
	}

	@Test
	public void siLaClaseExisteNoSePuedeRegistrar() {
		DatosClase clase = givenSeCreaLaClase();
		whenLaClaseCreadaYaExiste(clase.getNombre());
		thenLaClaseNoSePudoRegistrar("La clase ya existe");
	}
	
	private void whenLaClaseCreadaYaExiste(String nombreClase) {
		DatosClase clase = new DatosClase();
		clase.setNombre(nombreClase);
		mav = controladorClase.registroConClaseExistente();
	}

	private void thenLaClaseNoSePudoRegistrar(String motivo) {
		assertEquals(motivo, mav.getModel().get("error"));

	}

	private DatosClase givenSeCreaLaClase() {
		return new DatosClase();
	}

	private void whenRegistroLaClase(DatosClase clase) {
		clase.setNombre(NOMBRE);
		clase.setCupo((CUPO));
		clase.setFechaYHora(FECHAYHORA);
		clase.setIdProfesor(ID);
		mav = controladorClase.registrarClase(clase);
	}

	private void thenLaClaseSeCreoConExito() {
		assertEquals(mav.getViewName(), REDIRECT_HOME);
		assertThat(mav.getModel().get("cargadaOk")).isEqualTo(Boolean.TRUE);
	}
}
