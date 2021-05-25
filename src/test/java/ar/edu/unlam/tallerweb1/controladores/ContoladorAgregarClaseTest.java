package ar.edu.unlam.tallerweb1.controladores;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.servicios.ServicioClase;

public class ContoladorAgregarClaseTest {


	private final String REDIRECT_LOGIN = "redirect:/login";
	private final String REDIRECT_HOME = "redirect:/home";
	ControladorClase controladorClase;
	private ModelAndView mav;
	private ServicioClase servicioAgregarClase;

	@Before
	public void init() {
		controladorClase = new ControladorClase();
		servicioAgregarClase = mock(ServicioClase.class);
	}


	@Test
	public void queSePuedaRegistrarLaClase() {
		Clase clase = givenSeCreaLaClase();
		whenRegistroLaClase(clase);
		thenLaClaseSeCreoConExito();
	}

	@Test
	public void siLaClaseExisteNoSePuedeRegistrar() {
		Clase clase = givenSeCreaLaClase();
		whenLaClaseCreadaYaExiste(clase.getNombre());
		thenLaClaseNoSePudoRegistrar("La clase ya existe");
	}
	
	private void whenLaClaseCreadaYaExiste(String nombreClase) {
		Clase clase = new Clase();
		clase.setNombre(nombreClase);
		mav = controladorClase.registroConClaseExistente();
	}

	private void thenLaClaseNoSePudoRegistrar(String motivo) {
		assertEquals(motivo, mav.getModel().get("error"));

	}

	private Clase givenSeCreaLaClase() {
		return new Clase();
	}

	private void whenRegistroLaClase(Clase clase) {
		clase.setNombre("Funcional");
		clase.setCapacidad((long) 50);
		mav = controladorClase.registrarClase(clase);
	}

	private void thenLaClaseSeCreoConExito() {
		assertEquals(mav.getViewName(), REDIRECT_HOME);
	}
}
