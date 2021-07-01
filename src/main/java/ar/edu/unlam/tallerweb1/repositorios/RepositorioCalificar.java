package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.CalificacionClase;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioCalificar {
	
	void guardarCalificacion(Clase clase,Usuario usuario,Integer calificacion); 
 	List<CalificacionClase> buscarCalificacionesPorUsuario(Usuario usuario);
	CalificacionClase buscarPorUsuarioYClase(Usuario usuario, Clase clase);
	void modificarCalificacion(CalificacionClase buscoClasificacion, Integer calificacion);
 
}

