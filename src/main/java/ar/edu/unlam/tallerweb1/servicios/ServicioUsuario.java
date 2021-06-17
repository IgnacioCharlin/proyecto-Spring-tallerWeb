package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioUsuario {
	
	
    Usuario registrarUsuario(DatosRegistro datos);

    void cambiarClave(String email, String claveNueva, String repiteClaveNueva, String claveActual);
    
    Usuario consultarUsuario(DatosRegistro datos);

	Usuario consultarUsuarioPorId(Long idUsuario);

	void actualizarUsuario(Usuario usuarioAinscribirse);
}
