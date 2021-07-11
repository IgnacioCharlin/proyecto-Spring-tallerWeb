package ar.edu.unlam.tallerweb1.repositorios;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ar.edu.unlam.tallerweb1.modelo.AsistenciaClase;
import ar.edu.unlam.tallerweb1.modelo.CalificacionClase;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.modelo.Tarjeta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioTarjeta{ 
	List <Tarjeta> dameTarjetasDisponibles();

	Tarjeta buscarTarjetaPorId(long idTarjeta); 
}


