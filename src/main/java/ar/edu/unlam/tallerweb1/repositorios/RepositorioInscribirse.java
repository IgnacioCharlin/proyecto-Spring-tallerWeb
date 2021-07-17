package ar.edu.unlam.tallerweb1.repositorios;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ar.edu.unlam.tallerweb1.modelo.AsistenciaClase;
import ar.edu.unlam.tallerweb1.modelo.CalificacionClase;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.ClasesInscriptas;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioInscribirse{
	ClasesInscriptas buscarPorUsuarioYClase(Usuario usuario, Clase clase);
	void guardarInscripcion(Clase clase,Usuario usuario);
	List<ClasesInscriptas> buscarPorUsuario(long idUsuario);
	List<Clase> clasesConNotificacion(Long idUsuario);
	ClasesInscriptas buscarClasePorId(Long idClase);
	void guardarClase(ClasesInscriptas leer);
	}


