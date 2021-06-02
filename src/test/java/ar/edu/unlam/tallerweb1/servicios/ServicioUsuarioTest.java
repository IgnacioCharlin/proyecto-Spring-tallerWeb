package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.excepciones.ClaveNuevaIgualActual;
import ar.edu.unlam.tallerweb1.excepciones.ClavesNoCoinciden;
import ar.edu.unlam.tallerweb1.excepciones.UsuarioExistente;
import ar.edu.unlam.tallerweb1.modelo.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ServicioUsuarioTest {

    private static final String MAIL = "algo@algo.com";
    private static final String CLAVE = "12345678";
    private ServicioUsuarioImp servicioUsuario;
    private RepositorioUsuario repositorioUsuario;
    private Usuario usuarioRegistrado;

    @Before
    public void init(){
        repositorioUsuario = mock(RepositorioUsuario.class);
        servicioUsuario = new ServicioUsuarioImp(repositorioUsuario);
    }

    @Test
    public void siLasClavesCoincidenYUsuarioEsNuevoSeDebeRegistrar(){
        givenUsuarioNoExiste(MAIL);
        whenRegistroUsuario(MAIL, CLAVE, CLAVE);
        thenRegistroExitoso();
    }

    @Test(expected = ClavesNoCoinciden.class)
    public void registroFallaSiLasClavesNoCoinciden(){
        givenUsuarioNoExiste(MAIL);
        whenRegistroUsuario(MAIL, CLAVE, CLAVE+"1");
        thenRegistroError();
    }

    @Test(expected = UsuarioExistente.class)
    public void registroFallaSiUsuarioYaExiste(){
        givenUsuarioExiste(MAIL);
        whenRegistroUsuario(MAIL, CLAVE, CLAVE);
        thenRegistroError();
    }

    @Test(expected = ClavesNoCoinciden.class)
    public void siClavesNuevasNoCoincidenElCambioFalla(){

        final String claveNueva = "nueva";
        final String claveActual = "actual";
        givenCambioClave(claveActual, claveNueva, claveNueva + "rtrtry");
    }

    @Test(expected = ClaveNuevaIgualActual.class)
    public void siClaveNuevaIgualAActualElCambioFalla(){
        final String claveNueva = "nueva";
        final String claveActual = "nueva";
        givenCambioClave(claveActual, claveNueva, claveNueva);
    }

    @Test
    public void siClavesCorrectasElCambioSeRealiza(){
        final String claveNueva = "nueva";
        final String claveActual = "actual";
        Usuario usuario = new Usuario();
        when(repositorioUsuario.buscar(MAIL)).thenReturn(usuario);

        givenCambioClave(claveActual, claveNueva, claveNueva);
        thenSeCambiaLaClave(usuario);
    }

    private void thenSeCambiaLaClave(Usuario usuarioConClaveNueva) {
        assertThat(usuarioConClaveNueva.getPassword()).isEqualTo("nueva");
        verify(repositorioUsuario, times(1)).modificar(usuarioConClaveNueva);
    }

    private void givenCambioClave(String claveActual, String claveNueva, String repiteClaveNueva) {
        servicioUsuario.cambiarClave(MAIL, claveNueva, repiteClaveNueva, claveActual);
    }

    private void givenUsuarioExiste(String email) {
        when(repositorioUsuario.buscar(email)).thenReturn(new Usuario());
    }

    private void givenUsuarioNoExiste(String email) {
        when(repositorioUsuario.buscar(email)).thenReturn(null);
    }

    private void whenRegistroUsuario(String email, String password, String repitePassword) {
        DatosRegistro datos = new DatosRegistro();
        datos.setEmail(email);
        datos.setPassword(password);
        datos.setRepitePassword(repitePassword);
        usuarioRegistrado = servicioUsuario.registrarUsuario(datos);
    }

    private void thenRegistroExitoso() {
        assertThat(usuarioRegistrado).isNotNull();
    }

    private void thenRegistroError() {
        assertThat(usuarioRegistrado).isNull();
    }

}
