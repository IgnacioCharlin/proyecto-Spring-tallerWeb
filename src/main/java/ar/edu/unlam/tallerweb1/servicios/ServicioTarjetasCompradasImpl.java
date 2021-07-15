package ar.edu.unlam.tallerweb1.servicios;

import java.util.Date; 

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.excepciones.AlumnoNoPerteneceAlaClase;
import ar.edu.unlam.tallerweb1.excepciones.ClaseInvalida;
import ar.edu.unlam.tallerweb1.excepciones.FaltaCupo;
import ar.edu.unlam.tallerweb1.excepciones.FechaYaPaso;
import ar.edu.unlam.tallerweb1.excepciones.NoEsProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoUnaFecha;
import ar.edu.unlam.tallerweb1.excepciones.NoTengoClase;
import ar.edu.unlam.tallerweb1.excepciones.NoTengoUsuario;
import ar.edu.unlam.tallerweb1.excepciones.PagoPendiente;
import ar.edu.unlam.tallerweb1.excepciones.PagoRechazadoVuelvaAintentar;
import ar.edu.unlam.tallerweb1.excepciones.UsuarioNoEstaPresenteEnLaClase;
import ar.edu.unlam.tallerweb1.excepciones.UsuarioNoExiste;
import ar.edu.unlam.tallerweb1.excepciones.UsuarioYaEstabaInscrito;
import ar.edu.unlam.tallerweb1.excepciones.noCuentoConTarjetasDisponibles;
import ar.edu.unlam.tallerweb1.modelo.AsistenciaClase;
import ar.edu.unlam.tallerweb1.modelo.CalificacionClase;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.DatosClase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.modelo.Tarjeta;
import ar.edu.unlam.tallerweb1.modelo.TarjetasCompradas;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuariosFichas;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAsistencia;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCalificar;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClase;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProfesor;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTarjeta;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTarjetasCompradas;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuarioFichas;

@Service("servicioTarjetasCompradas")
@Transactional
public class ServicioTarjetasCompradasImpl implements ServicioTarjetasCompradas{
	private RepositorioTarjetasCompradas repositorioTarjetasCompradas; 
	private RepositorioTarjeta repositorioTarjeta; 
	private RepositorioUsuario repositorioUsuario; 
	private RepositorioUsuarioFichas repositorioUsuarioFichas; 
	
	@Autowired
	public ServicioTarjetasCompradasImpl(RepositorioTarjetasCompradas repositorioTarjetasCompradas,RepositorioTarjeta repositorioTarjeta,RepositorioUsuario repositorioUsuario,RepositorioUsuarioFichas repositorioUsuarioFichas) {
		this.repositorioTarjetasCompradas = repositorioTarjetasCompradas; 
		this.repositorioTarjeta = repositorioTarjeta;
		this.repositorioUsuario = repositorioUsuario;
		this.repositorioUsuarioFichas = repositorioUsuarioFichas;
	}

	@Override
	public TarjetasCompradas procesoPago(Integer idTarjeta,Integer idUsuario, Integer estado,String collection_id) {
		Date fechaCompra = new Date();
		Tarjeta tarjeta = repositorioTarjeta.buscarTarjetaPorId((long)idTarjeta);
		Usuario usuario = repositorioUsuario.buscarPorId((long)idUsuario);
		
		String estadoDesc= ""; 
		if(estado==1) {
			 estadoDesc= "Abonada"; 
		}if(estado==2) {
			 estadoDesc= "Pendiente";  
		}if(estado==3) {
			 estadoDesc= "Rechazado"; 
		}
		
		TarjetasCompradas tarjetaComprada =	repositorioTarjetasCompradas.guardarCompra(tarjeta,usuario,fechaCompra,estadoDesc,collection_id);
 		return tarjetaComprada;
		
	}
	
	
 
	@Override
	public void actualizoFichas(TarjetasCompradas tarjetaComprada,Integer idUsuario, Integer estado) {
 		Usuario usuario = repositorioUsuario.buscarPorId((long)idUsuario);
		
		if(estado==1) {
 			UsuariosFichas fichasActualesUsuario=	repositorioUsuarioFichas.buscoFichas(usuario);
			if(fichasActualesUsuario==null) {
			repositorioUsuarioFichas.guardoFichas(usuario,tarjetaComprada);
			}else {
			repositorioUsuarioFichas.actualizoFichas(fichasActualesUsuario,tarjetaComprada);
			}
 
		}if(estado==2) { 	
			throw new PagoPendiente();

		}
		if(estado==3) { 
			throw new PagoRechazadoVuelvaAintentar();
		}
		
	}

	@Override
	public List<TarjetasCompradas> buscarComprasPorUsuario(Integer idUsuario) {
		Usuario usuario = repositorioUsuario.buscarPorId((long)idUsuario);

		return repositorioTarjetasCompradas.buscarComprasPorUsuario(usuario);
	}

	@Override
	public List<TarjetasCompradas> pagoPendiente() {
		List<TarjetasCompradas> comprasPendientes = repositorioTarjetasCompradas.buscarPendiente();

		return comprasPendientes;
	}

	@Override
	public TarjetasCompradas buscoPorId(long idTarjetaComprada) {
		TarjetasCompradas tarjetasComprada = repositorioTarjetasCompradas.buscarPorId(idTarjetaComprada);
		return tarjetasComprada;
	}

	@Override
	public void cambioEstado(TarjetasCompradas tarjetaComprada, String estado) {
		  repositorioTarjetasCompradas.actualizarEstado(tarjetaComprada,estado);
		
	}

 

	
}