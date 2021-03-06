package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Profesor;

public interface RepositorioProfesor {
	Profesor agregarProfesor(Profesor profesor);
	Profesor buscarProfesorPorMail(String mail);
	Profesor buscarProfesorPorId(long id);
	void modificarProfesor(Profesor profesor);
	List<Profesor> listarProfesores();
}
