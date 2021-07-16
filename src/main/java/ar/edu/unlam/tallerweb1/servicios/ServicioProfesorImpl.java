package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.excepciones.NoEsProfesor;
import ar.edu.unlam.tallerweb1.excepciones.ProfesorYaExiste;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClase;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProfesor;

@Service("servicioProfesor")
@Transactional
public class ServicioProfesorImpl implements ServicioProfesor {
	
	private RepositorioProfesor repositorioProfesor;
	
	@Autowired
	public ServicioProfesorImpl(RepositorioProfesor repositorioProfesor) {
		this.repositorioProfesor = repositorioProfesor;
	}
	
	
	@Override
	public Profesor agregarProfesor(Profesor profesor) {
		if(!profesor.getRol().equals("profesor")) {
			throw new NoEsProfesor();
		}
		if(repositorioProfesor.buscarProfesorPorMail(profesor.getEmail())!=null) {
			throw new ProfesorYaExiste();
		}
		repositorioProfesor.agregarProfesor(profesor);
		return profesor;
	}

	@Override
	public boolean existeProfesor(Profesor profesor) {
		if(repositorioProfesor.buscarProfesorPorMail(profesor.getEmail())!=null) {
			return true;
		}
		return false;
	}


	@Override
	public Profesor consultarProfesor(long id) {
		Profesor profesorConsultado = repositorioProfesor.buscarProfesorPorId(id);
		return profesorConsultado;
	}


	@Override
	public void cambiarClave(String email, String claveNueva, String repiteClaveNueva) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Profesor buscarProfesorPorEmail(String email) {
		if(repositorioProfesor.buscarProfesorPorMail(email) == null) {
			throw new NoEsProfesor();
		}
		return repositorioProfesor.buscarProfesorPorMail(email);
		/*try {
			return repositorioProfesor.buscarProfesorPorMail(email);
		} catch (Exception e) {
			throw new NoEsProfesor();
		}*/
	}


	@Override
	public List<Profesor> listarProfesores() {
		return repositorioProfesor.listarProfesores();
	}




}
