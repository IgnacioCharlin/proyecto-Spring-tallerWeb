package ar.edu.unlam.tallerweb1.controladores;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.assertThat;
<<<<<<< HEAD
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.DatosClase;
=======

import ar.edu.unlam.tallerweb1.excepciones.FaltaCupo;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoUnaFecha;
>>>>>>> 7b282cd68c490fe718da7121a0d5d03f54e9fdab
import ar.edu.unlam.tallerweb1.servicios.ServicioClase;


public class ContoladorClaseTest {

	private static final String FECHAYHORA = "miercoles 10hs";
	private static final Long ID = 2l;
	private final Long CUPO = 50L;
	private final String NOMBRE = "Funcional";
	private final String REDIRECT_CLASE = "redirect:/agregar-clase";
	private final String REDIRECT_HOME = "redirect:/home";
	ControladorClase controladorClase;
	private ModelAndView mav;
	private ServicioClase servicioClase;

	@Before
	public void init() {
		servicioClase = mock(ServicioClase.class);
		controladorClase = new ControladorClase(servicioClase);
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
	@Test
	public void siLaClaseNoTieneProfesorNoSeRegistra() {
		DatosClase clase = givenDatosClaseSinProfesor();
		
		whenRegistroClaseSinProfesor(clase);
		
		thenElRegistroFalla("Falto cargar el profesor");
	}
	
	@Test
	public void siLaClaseNoTieneCupoNoSeRegistra() {
		DatosClase clase = givenDatosClaseSinCupo();
		
		whenRegistroClaseSinCupo(clase);
		
		thenElRegistroFalla("Falto cargar el cupo");
	}
	@Test
	public void siLaClaseNoTieneFechaYHoraNoSeRegistra() {
		DatosClase clase = givenDatosClaseSinFechaYHora();
		
		whenRegistroClaseSinFechaYHora(clase);
		
		thenElRegistroFalla("Falto cargar la hora y fecha");
	}
	
	private DatosClase givenDatosClaseSinFechaYHora() {
		DatosClase clase = new DatosClase();
		clase.setCupo(CUPO);
//		clase.setFechaYHora(FECHAYHORA);
		clase.setNombre(NOMBRE);
		clase.setIdProfesor(ID);
		return clase;
	}
	
	private DatosClase givenDatosClaseSinCupo() {
		DatosClase clase = new DatosClase();
		//clase.setCupo(CUPO);
		clase.setFechaYHora(FECHAYHORA);
		clase.setNombre(NOMBRE);
		clase.setIdProfesor(ID);
		return clase;
	}
	
	private DatosClase givenDatosClaseSinProfesor() {
		DatosClase clase = new DatosClase();
		clase.setCupo(CUPO);
		clase.setFechaYHora(FECHAYHORA);
		clase.setNombre(NOMBRE);
		return clase;
	}

	private void thenElRegistroFalla(String motivo) {
		
		assertEquals(mav.getModel().get("error"), (motivo));
		assertEquals(mav.getViewName(), REDIRECT_CLASE);
		
		
	}

	private void whenRegistroClaseSinProfesor(DatosClase clase) {
	when(servicioClase.agregarClase(clase)).thenThrow(NoSeCargoProfesor.class);
		
		mav = controladorClase.registrarClase(clase);
		
	}
	private void whenRegistroClaseSinCupo(DatosClase clase) {
		when(servicioClase.agregarClase(clase)).thenThrow(FaltaCupo.class);
			
			mav = controladorClase.registrarClase(clase);
			
	}
	
	private void whenRegistroClaseSinFechaYHora(DatosClase clase) {
		when(servicioClase.agregarClase(clase)).thenThrow(NoSeCargoUnaFecha.class);
			
			mav = controladorClase.registrarClase(clase);
			
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
