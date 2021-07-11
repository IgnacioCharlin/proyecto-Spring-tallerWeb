package ar.edu.unlam.tallerweb1.servicios;


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
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuariosFichas;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAsistencia;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCalificar;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClase;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProfesor;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTarjeta;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuarioFichas;

@Service("servicioUsuarioFichas")
@Transactional
public class ServicioUsuarioFichasImpl implements ServicioUsuarioFichas{
	private RepositorioUsuarioFichas repositorioUsuarioFichas; 
	private RepositorioUsuario repositorioUsuario; 
	
	@Autowired
	public ServicioUsuarioFichasImpl(RepositorioUsuarioFichas repositorioUsuarioFichas,RepositorioUsuario repositorioUsuario) {
		this.repositorioUsuarioFichas = repositorioUsuarioFichas;
		this.repositorioUsuario = repositorioUsuario;
	}

	@Override
	public UsuariosFichas buscarFichasPorUsuario(Long idUsuario) {
		Usuario usuario = repositorioUsuario.buscarPorId(idUsuario);
		UsuariosFichas fichasActualesUsuario=	repositorioUsuarioFichas.buscoFichas(usuario);

		return fichasActualesUsuario;
	}

	@Override
	public void descontarClase(Usuario usuarioAinscribirse) {
		UsuariosFichas fichasActualesUsuario=	repositorioUsuarioFichas.buscoFichas(usuarioAinscribirse);
		repositorioUsuarioFichas.descuentoUnaClase(fichasActualesUsuario);
	}

 

	
}