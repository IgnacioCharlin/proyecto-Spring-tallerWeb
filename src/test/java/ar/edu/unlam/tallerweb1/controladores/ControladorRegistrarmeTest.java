package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.excepciones.ClaveNuevaIgualActual;
import ar.edu.unlam.tallerweb1.excepciones.ClavesNoCoinciden;
import ar.edu.unlam.tallerweb1.excepciones.UsuarioExistente;
import ar.edu.unlam.tallerweb1.modelo.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class ControladorRegistrarmeTest {
    private final String LOGIN_VIEW = "redirect:/login";
    private final Usuario USUARIO = new Usuario();
    private ModelAndView mav;
    private ControladorRegistro controladorRegistro;
    private ServicioUsuario servicioUsuario;

    @Before
    public void init(){
        servicioUsuario = mock(ServicioUsuario.class);
        controladorRegistro = new ControladorRegistro(servicioUsuario);
    }
}
