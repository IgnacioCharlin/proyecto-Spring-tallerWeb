package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Profesor;

public interface ServicioProfesor {
	boolean existeProfesor(Profesor profesor);
	void agregarProfesor(Profesor profesor);
}
