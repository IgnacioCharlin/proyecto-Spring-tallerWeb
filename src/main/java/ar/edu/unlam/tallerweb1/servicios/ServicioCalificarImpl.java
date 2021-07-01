package ar.edu.unlam.tallerweb1.servicios;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.excepciones.FaltaCupo;
import ar.edu.unlam.tallerweb1.excepciones.FechaYaPaso;
import ar.edu.unlam.tallerweb1.excepciones.NoEsProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoUnaFecha;
import ar.edu.unlam.tallerweb1.modelo.CalificacionClase;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.DatosClase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCalificar;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClase;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProfesor;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service("servicioCalificar")
@Transactional
public class ServicioCalificarImpl implements ServicioCalificar {
	private RepositorioCalificar repositorioCalificar;
	private RepositorioClase repositorioClase;
	private RepositorioUsuario repositorioUsuario;
	
	@Autowired
	public ServicioCalificarImpl(RepositorioCalificar repositorioCalificar, RepositorioUsuario repositorioUsuario,RepositorioClase repositorioClase) {
		this.repositorioCalificar = repositorioCalificar;
		this.repositorioClase = repositorioClase;
		this.repositorioUsuario = repositorioUsuario;
	}

	@Override
	public void agregarCalificacion(Long idUsuario, Long idClase, Integer calificacion) {
 		if(calificacion>0) { 
 			
			Clase clase = repositorioClase.buscarClasePorId((long)idClase);
			Usuario usuario = repositorioUsuario.buscarPorId((long)idUsuario);
			CalificacionClase buscoClasificacion = repositorioCalificar.buscarPorUsuarioYClase(usuario,clase);
			if(buscoClasificacion==null) {
			repositorioCalificar.guardarCalificacion(clase,usuario,calificacion);
			}else {
				repositorioCalificar.modificarCalificacion(buscoClasificacion,calificacion);
			}
			
 		}//fin de calificacion
		  
 	}

	@Override
	public List<CalificacionClase> consultarPorIdUsuario(long idUsuario) {
		Usuario usuario = repositorioUsuario.buscarPorId((long)idUsuario);
 		return repositorioCalificar.buscarCalificacionesPorUsuario(usuario);
	}
	 
}