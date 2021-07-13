package ar.edu.unlam.tallerweb1.repositorios;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ar.edu.unlam.tallerweb1.modelo.AsistenciaClase;
import ar.edu.unlam.tallerweb1.modelo.CalificacionClase;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.modelo.Tarjeta;
import ar.edu.unlam.tallerweb1.modelo.TarjetasCompradas;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioTarjetasCompradas{

	TarjetasCompradas guardarCompra(Tarjeta tarjeta, Usuario usuario, Date fechaCompra,String estado, String collection_id);

	List<TarjetasCompradas> buscarComprasPorUsuario(Usuario usuario);

	List<TarjetasCompradas> buscarPendiente();

	TarjetasCompradas buscarPorId(long idTarjetaComprada);

	void actualizarEstado(TarjetasCompradas tarjetaComprada, String estado); 
 }


