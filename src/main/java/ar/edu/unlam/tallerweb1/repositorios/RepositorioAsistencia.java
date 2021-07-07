package ar.edu.unlam.tallerweb1.repositorios;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ar.edu.unlam.tallerweb1.modelo.AsistenciaClase;
import ar.edu.unlam.tallerweb1.modelo.CalificacionClase;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioAsistencia {
	
	void guardarAsistencia(Clase clase,Usuario usuario);  
	void modificarAsistencia(AsistenciaClase buscoAsistencia, Integer presente);
	AsistenciaClase buscarPorUsuarioYClase(Usuario usuario, Clase clase);
	List <AsistenciaClase> dameAsistenciaPorClase(Clase idClase);
	AsistenciaClase buscarPorUsuarioYClase(Usuario usuario, Clase clase, Integer presente);
	List<AsistenciaClase> dameAsistenciaPorUsuario(Usuario usuairo);
}


