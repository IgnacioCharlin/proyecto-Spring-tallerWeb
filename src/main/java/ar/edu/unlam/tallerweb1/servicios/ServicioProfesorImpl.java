package ar.edu.unlam.tallerweb1.servicios;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.excepciones.NoEsProfesor;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClase;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProfesor;

@Service
@Transactional
public class ServicioProfesorImpl implements ServicioProfesor {
	
	private RepositorioProfesor repositorioProfesor;
	
	@Autowired
	public ServicioProfesorImpl(RepositorioProfesor repositorioProfesor) {
		this.repositorioProfesor = repositorioProfesor;
	}
	
	
	@Override
	public void agregarProfesor(Profesor profesor) {
		if(!profesor.getRol().equals("profesor")) {
			throw new NoEsProfesor();
		}
		repositorioProfesor.agregarProfesor(profesor);
	}

	@Override
	public boolean existeProfesor(Profesor profesor) {
		if(repositorioProfesor.buscarProfesorPorMail(profesor.getEmail())!=null) {
			return true;
		}
		return false;
	}


}
