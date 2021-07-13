package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AsistenciaClase;
import ar.edu.unlam.tallerweb1.modelo.CalificacionClase;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.DatosClase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.modelo.Tarjeta;
import ar.edu.unlam.tallerweb1.modelo.TarjetasCompradas;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
public interface ServicioTarjetasCompradas {

	TarjetasCompradas procesoPago(Integer idTarjeta,Integer idUsuario, Integer estado, String collection_id);

	List<TarjetasCompradas> buscarComprasPorUsuario(Integer idUsuario);

	void actualizoFichas(TarjetasCompradas tarjetaComprada, Integer idUsuario, Integer estado);

	List<TarjetasCompradas> pagoPendiente();

	TarjetasCompradas buscoPorId(long idTarjetaComprada);

	void cambioEstado(TarjetasCompradas tarjetaComprada, String estado);

 }
