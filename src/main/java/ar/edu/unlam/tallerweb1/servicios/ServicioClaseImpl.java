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
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.DatosClase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClase;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProfesor;

@Service("servicioClase")
@Transactional
public class ServicioClaseImpl implements ServicioClase {
	private String fechaHoy = LocalDate.now().toString();
	private RepositorioClase repositorioClase;
	private RepositorioProfesor repositorioProfesor;
	
	@Autowired
	public ServicioClaseImpl(RepositorioClase repositorioClase, RepositorioProfesor repositorioProfesor) {
		this.repositorioClase = repositorioClase;
		this.repositorioProfesor = repositorioProfesor;
	}
	
	@Override
	public Clase agregarClase(DatosClase clase) {
		if(clase.getFechaYHora() == null)
			throw new NoSeCargoUnaFecha();
		if( clase.getCupo() == null ||clase.getCupo() <= 0 )
			throw new FaltaCupo();
		if(clase.getIdProfesor() == null)
			throw new NoSeCargoProfesor();
		if(repositorioProfesor.buscarProfesorPorId(clase.getIdProfesor()) == null)
			throw new NoEsProfesor();
		
		String fechaYHora = clase.getFechaYHora().replace("T", " ");
		String fechaClase = fechaYHora.substring(0, 10);
		if(fechaClase.compareTo(fechaHoy) == -1) 
			throw new FechaYaPaso();
		
		System.out.println(clase.getFechaYHora());
		
		Profesor profesor = repositorioProfesor.buscarProfesorPorId(clase.getIdProfesor());

		Clase nuevaClase = new Clase(clase.getNombre(), fechaYHora, profesor, clase.getCupo());

		repositorioClase.guardarClase(nuevaClase);
		return nuevaClase;
	}

	@Override
	public Clase consultarClase(String nombre) {
		return repositorioClase.buscarClase(nombre);
	}
	

	@Override
	public List<Clase> consultarTodasLasClases() {
		List<Clase> clases = new ArrayList<Clase>();
		clases.addAll(repositorioClase.dameClasesConDisponibilidad());
		return clases;
	}

	@Override
	public void eliminarClase(Clase clase) {
	//	Clase claseBuscada = repositorioClase.buscarClase(clase.getNombre());
		repositorioClase.eliminarClase(clase);
	}

	@Override
	public Clase consultarClasePorId(Long id) {
		return repositorioClase.buscarClasePorId(id);
	}

	@Override
	public void modificarClase(Long id, DatosClase datos) {
		
		if(datos.getFechaYHora() == null)
			throw new NoSeCargoUnaFecha();
		if( datos.getCupo() == null ||datos.getCupo() <= 0 )
			throw new FaltaCupo();
		if(datos.getIdProfesor() == null)
			throw new NoSeCargoProfesor();
		
		final Clase claseBuscada = repositorioClase.buscarClasePorId(id);
		Profesor profesor = repositorioProfesor.buscarProfesorPorId(datos.getIdProfesor());
		claseBuscada.setCapacidad(datos.getCupo());
		claseBuscada.setHorarioYFecha(datos.getFechaYHora().toString());
		claseBuscada.setNombre(datos.getNombre());
		claseBuscada.setProfesor(profesor);
		
		
		repositorioClase.modificarClase(claseBuscada);
	}

	@Override
	public List<Clase> consultarClasesPorIdProfesor(long id) {
		Profesor profesor = repositorioProfesor.buscarProfesorPorId(id);
		return repositorioClase.buscarClasePorProfesor(profesor);
	}

	@Override
	public List<Clase> consultarClasePorFiltroFecha(String desde, String hasta) {
		return repositorioClase.filtrarClasesPorFecha(desde, hasta);
	}
	
	
	
	@Override
	public Clase buscarClaseId(Long id) {
		if(id==0 || id==null) {
			throw new ClaseInvalida();  
		}
		Clase claseBuscada = repositorioClase.buscarClasePorId(id);
		
		  if (claseBuscada == null) {
			  throw new ClaseInvalida();  
		}
		
		return claseBuscada;
	}

	@Override
	public List<Clase> consultarClasesEliminadas() {
		List<Clase> clases = new ArrayList<Clase>();
		clases.addAll(repositorioClase.dameClasesEliminadas());
		return clases;
	}

	@Override
	public List<Clase> notificar(Long idUsuario) {
		List<Clase> clases = repositorioClase.clasesConNotificacion(idUsuario);
		return clases;
	}

	@Override
	public void leerNotificacion(Long idClase) {
		Clase leer = repositorioClase.buscarClasePorId(idClase);
		leer.setNotificado(true);
		repositorioClase.guardarClase(leer);
		
	}
}