package ar.edu.unlam.tallerweb1.servicios;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.ClasesInscriptas;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioInscribirse;

@Service("servicioInscribirse")
@Transactional
public class ServicioInscribirseImpl implements ServicioInscribirse{
	private RepositorioInscribirse repositorioInscribirse;
 
	
	@Autowired
	public ServicioInscribirseImpl(RepositorioInscribirse repositorioInscribirse) {
		this.repositorioInscribirse = repositorioInscribirse;
 
	}

	@Override
	public ClasesInscriptas buscarInscripcion(Clase buscadaAInscribirse, Usuario usuarioAinscribirse) {
		return repositorioInscribirse.buscarPorUsuarioYClase(usuarioAinscribirse, buscadaAInscribirse);
	}

	@Override
	public void guardarInscripcion(Clase buscadaAInscribirse, Usuario usuarioAinscribirse) {
		 repositorioInscribirse.guardarInscripcion(buscadaAInscribirse,usuarioAinscribirse);		
		 return ;
	}

	@Override
	public List<ClasesInscriptas> consultarUsuarioPorId(long idUsuario) {
		return repositorioInscribirse.buscarPorUsuario(idUsuario);

	}
	
	@Override
	public List<Clase> notificar(Long idUsuario) {
		List<Clase> clases = repositorioInscribirse.clasesConNotificacion(idUsuario);
		return clases;
	}
	@Override
	public void leerNotificacion(Clase clase, Usuario usuario) {
		ClasesInscriptas leer = repositorioInscribirse.buscarPorUsuarioYClase(usuario,clase);
		leer.setNotificado(true);
		repositorioInscribirse.guardarClase(leer);
		
	}

	
}