package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class ControladorNotificacionesTest {
	
	private ControladorNotificaciones controladorNotificacion;
	private ModelAndView mav;
	private static final String FECHAYHORA = "2021,11,03";
	private static final Long ID = 2l;
	private final Long CUPO = 50L;
	private final String NOMBRE = "Funcional";
	
	@Test
	public void queMeNotifiqueSiLaClaseEsEn24hs() {
		Clase clase = givenUnaClaseconFecha24hs();
		Usuario inscripto = givenUsuarioInscriptoAUnaClase();
		ModelAndView notificacion = whenConsultoLaClaseMeNotifica(clase);
		thenLaNotificacionEstaActiva(notificacion);
		
	}


	private Usuario givenUsuarioInscriptoAUnaClase() {
		// TODO Auto-generated method stub
		return null;
	}


	private ModelAndView whenConsultoLaClaseMeNotifica(Clase clase) {
//		return controladorNotificacion.verNotificaciones(null);
		 return null;
	}


	private void thenLaNotificacionEstaActiva(ModelAndView notificacion) {
		assertThat(notificacion).isEqualTo(Boolean.TRUE);
		
	}


	private Clase givenUnaClaseconFecha24hs() {
		Clase nueva = new Clase(NOMBRE,FECHAYHORA,null,CUPO);
		return nueva;
	}

}
