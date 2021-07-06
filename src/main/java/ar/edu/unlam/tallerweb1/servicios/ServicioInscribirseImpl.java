package ar.edu.unlam.tallerweb1.servicios;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.excepciones.ClaseInvalida;
import ar.edu.unlam.tallerweb1.excepciones.FaltaCupo;
import ar.edu.unlam.tallerweb1.excepciones.FechaYaPaso;
import ar.edu.unlam.tallerweb1.excepciones.NoEsProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoUnaFecha;
import ar.edu.unlam.tallerweb1.modelo.AsistenciaClase;
import ar.edu.unlam.tallerweb1.modelo.CalificacionClase;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.ClasesInscriptas;
import ar.edu.unlam.tallerweb1.modelo.DatosClase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAsistencia;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCalificar;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClase;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioInscribirse;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProfesor;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

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

	 
}