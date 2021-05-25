package ar.edu.unlam.tallerweb1.controladores;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Clase;

public class ContoladorAgregarClaseTest {
	
	
	private final String REDIRECT_LOGIN = "redirect:/login";//crear la vista donde estan las clases dispobibles

	private ControladorClase controladorClase = new ControladorClase();
	
	ModelAndView mav;
	
	@Test
	public void queSePuedaRegistrarLaClase() {
		Clase clase = givenLaClaseNoExite();
		whenRegistroLaClase(clase);
		thenLaClaseSeCreoConExito();
	}


	private Clase givenLaClaseNoExite() {
		return new Clase();
	}
	
	private void whenRegistroLaClase(Clase clase) {
		clase.setNombre("Funcional");
		clase.setCapacidad((long) 50);
		mav = controladorClase.registrar(clase);
	}
	
	private void thenLaClaseSeCreoConExito() {
		assertEquals(mav.getViewName(),REDIRECT_LOGIN);
	}
}
