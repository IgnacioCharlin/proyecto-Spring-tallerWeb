package ar.edu.unlam.tallerweb1.servicios;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.excepciones.ClaseInvalida;
import ar.edu.unlam.tallerweb1.excepciones.FaltaCupo;
import ar.edu.unlam.tallerweb1.excepciones.FechaYaPaso;
import ar.edu.unlam.tallerweb1.excepciones.NoEsProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoUnaFecha;
import ar.edu.unlam.tallerweb1.excepciones.NoTengoClase;
import ar.edu.unlam.tallerweb1.excepciones.NoTengoUsuario;
import ar.edu.unlam.tallerweb1.excepciones.UsuarioNoExiste;
import ar.edu.unlam.tallerweb1.modelo.AsistenciaClase;
import ar.edu.unlam.tallerweb1.modelo.CalificacionClase;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.DatosClase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAsistencia;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCalificar;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClase;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProfesor;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service("servicioAsistencia")
@Transactional
public class ServicioAsistenciaImpl implements ServicioAsistencia{
	private RepositorioAsistencia repositorioAsistencia;
	private RepositorioClase repositorioClase;
	private RepositorioUsuario repositorioUsuario;
	
	@Autowired
	public ServicioAsistenciaImpl(RepositorioAsistencia repositorioAsistencia, RepositorioUsuario repositorioUsuario,RepositorioClase repositorioClase) {
		this.repositorioAsistencia = repositorioAsistencia;
		this.repositorioClase = repositorioClase;
		this.repositorioUsuario = repositorioUsuario;
	}

	@Override
	public AsistenciaClase actualizarAsistencia(Clase clase, Usuario usuario) {
		
		
		if(usuario==null) {
			throw new NoTengoUsuario();
		}  
		
		if(clase==null) {
			throw new NoTengoClase();
		}  
 
	 	AsistenciaClase buscoAsistencia= repositorioAsistencia.buscarPorUsuarioYClase(usuario,clase);
		if(buscoAsistencia==null) {
			repositorioAsistencia.guardarAsistencia(clase,usuario);
		}else {
			Integer presente=buscoAsistencia.getPresente();
			Integer nuevoPresente=1;
			
			if(presente==1) {
				nuevoPresente=0;
			} 
			repositorioAsistencia.modificarAsistencia(buscoAsistencia,nuevoPresente);
		}
		
		return buscoAsistencia;
	}
	
	
 
	
	@Override
	public List<AsistenciaClase> consultarAsistenciaPorClase(Clase clase ) {
		List<AsistenciaClase> clases = new ArrayList<AsistenciaClase>();
		clases.addAll(repositorioAsistencia.dameAsistenciaPorClase(clase)); 
 		return clases; 
		
	}
	
	
	
	@Override
	public AsistenciaClase consultarAsistenciaPorClaseYusuario(Clase clase, Usuario usuario,Integer presente ) {
		return repositorioAsistencia.buscarPorUsuarioYClase(usuario,clase,presente);

		  
		
	}
	
	
}