package ar.edu.unlam.tallerweb1.servicios;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.excepciones.FaltaCupo;
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
		System.out.println(clase.getFechaYHora());
		//recibir datos y guardar Clase claseclase -->repositorioClase.agregarClase()
		//repositorioClase.buscarClasePorId();
		String fechaYHora = clase.getFechaYHora().replace("T", " ");
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
		clases.addAll(repositorioClase.buscarTodasLasClase());
		return clases;
	}

	@Override
	public void eliminarClase(Clase clase) {
		Clase claseBuscada = repositorioClase.buscarClase(clase.getNombre());
		repositorioClase.eliminarClase(claseBuscada);
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
}

