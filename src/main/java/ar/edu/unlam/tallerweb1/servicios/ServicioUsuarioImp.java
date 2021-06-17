package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.excepciones.ClaveNuevaIgualActual;
import ar.edu.unlam.tallerweb1.excepciones.ClavesNoCoinciden;
import ar.edu.unlam.tallerweb1.excepciones.UsuarioExistente;
import ar.edu.unlam.tallerweb1.excepciones.UsuarioNoExiste;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioUsuarios")
@Transactional
public class ServicioUsuarioImp implements ServicioUsuario {

    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioUsuarioImp(RepositorioUsuario repositorioUsuario){
        this.repositorioUsuario = repositorioUsuario;
    }

    public Usuario registrarUsuario(DatosRegistro datos) {
        if(!datos.getPassword().equals(datos.getRepitePassword())){
            throw new ClavesNoCoinciden();
        }
        if(repositorioUsuario.buscar(datos.getEmail()) != null){
            throw new UsuarioExistente();
        }
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEmail(datos.getEmail());
        nuevoUsuario.setPassword(datos.getPassword());
        repositorioUsuario.guardar(nuevoUsuario);
        return nuevoUsuario;
    }

    @Override
    public void cambiarClave(String email, String claveNueva, String repiteClaveNueva, String claveActual) {
        if(!claveNueva.equals(repiteClaveNueva))
            throw new ClavesNoCoinciden();
        if(claveNueva.equals(claveActual))
            throw new ClaveNuevaIgualActual();

        final Usuario usuario = repositorioUsuario.buscar(email);
        usuario.setPassword(claveNueva);
        repositorioUsuario.modificar(usuario);
    }

	@Override
	public Usuario consultarUsuario(DatosRegistro datos) {
		Usuario usuario = repositorioUsuario.buscar(datos.getEmail());
		if (usuario != null) {
			if (usuario.getPassword().equals(datos.getPassword())) {
				return usuario;
			}else {
				throw new ClavesNoCoinciden();
			}
		}else {
			throw new UsuarioNoExiste();
		}
	}

	@Override
	public Usuario consultarUsuarioPorId(Long idUsuario) {
		
		return repositorioUsuario.buscarPorId(idUsuario);
	}

	@Override
	public void actualizarUsuario(Usuario usuario) {
		repositorioUsuario.modificar(usuario);
		
	}
}
