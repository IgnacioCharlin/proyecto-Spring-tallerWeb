package ar.edu.unlam.tallerweb1.controladores;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.DatosClase;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.excepciones.FaltaCupo;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoUnaFecha;
import ar.edu.unlam.tallerweb1.servicios.ServicioClase;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;


public class ContoladorClaseTest {

	private static final String FECHAYHORA = "2021,11,03";
	private static final Long ID = 2l;
	private final Long CUPO = 50L;
	private final String NOMBRE = "Funcional";
	private final String REDIRECT_CLASE = "agregar-clase";
	private final String REDIRECT_HOME = "redirect:/home";
	ControladorClase controladorClase;
	private ControladorInscibirseClases controladorInscribirse;
	private ModelAndView mav;
	private ServicioClase servicioClase;
	private ServicioUsuario servicioUsuario;

	@Before
	public void init() {
		servicioClase = mock(ServicioClase.class);
		servicioUsuario = mock(ServicioUsuario.class);
		controladorClase = new ControladorClase(servicioClase);
		controladorInscribirse = new ControladorInscibirseClases(servicioClase, servicioUsuario);
	}
	
	@Test
	public void queSePuedaRegistrarLaClase() {
		DatosClase clase = givenSeCreaLaClase();
		whenRegistroLaClase(clase);
		thenLaClaseSeCreoConExito();
	}
	
	/*
	@Test
	public void siLaClaseExisteNoSePuedeRegistrar() {
		DatosClase clase = givenSeCreaLaClase();
		whenLaClaseCreadaYaExiste(clase.getNombre());
		thenLaClaseNoSePudoRegistrar("La clase ya existe");
	}
	*/
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
	
	@Test
	public void queUnUsuarioPuedaInscribirseAUnaClase() {
		Usuario usuario = gvenUnUsuario("jose@gmail.com");
		Clase claseAInscribirse = givenClaseDisponible();
		
		whenElUsiarioQuiereAnotarseAlaClase(usuario, claseAInscribirse);
		
		thenLaInscripcionEsExitosa();
	}
	
	private void thenLaInscripcionEsExitosa() {
		Mockito.verify(servicioUsuario, times(1)).actualizarUsuario(any());
		
	}

	private void whenElUsiarioQuiereAnotarseAlaClase(Usuario usuario, Clase claseAInscribirse) {
		ModelMap model = new ModelMap();
				
		when(servicioUsuario.consultarUsuarioPorId(usuario.getId())).thenReturn(usuario);
		when(servicioClase.consultarClasePorId(claseAInscribirse.getId())).thenReturn(claseAInscribirse);
		
		mav = controladorInscribirse.confirmaInscripcion(claseAInscribirse.getId(),usuario.getId());
		
	}

	private Clase givenClaseDisponible() {
		Clase clase = new Clase();
		clase.setCapacidad(CUPO);
		clase.setHorarioYFecha("abril 2, 2021 14:30:00 GMT");
		clase.setNombre(NOMBRE);
		//clase.setProfesor(ID);
		return clase;
	}

	private Usuario gvenUnUsuario(String Nombre) {
		Usuario usuario = new Usuario();
		usuario.setEmail(Nombre);
		return usuario;
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