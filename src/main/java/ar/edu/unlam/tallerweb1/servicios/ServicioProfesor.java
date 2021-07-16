package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Profesor;

public interface ServicioProfesor {
	boolean existeProfesor(Profesor profesor);
	Profesor agregarProfesor(Profesor profesor);
	Profesor consultarProfesor(long id);
	void cambiarClave(String email,String claveNueva,String repiteClaveNueva);
	Profesor buscarProfesorPorEmail(String email);
	List<Profesor> listarProfesores();
}
