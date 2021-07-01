package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;

public interface RepositorioClase {
	
	Clase guardarClase(Clase clase);
	Clase buscarClase(String nombre);
	Clase buscarClasePorId(Long id);
	void modificarClase(Clase clase);
	void eliminarClase(Clase clase);
	List<Clase> buscarTodasLasClase();
	List<Clase> buscarClasePorProfesor(Profesor profesor);
<<<<<<< HEAD
	List<Clase> filtrarClasesPorFecha(String fechaDesde , String fechaHasta);
=======
	List<Clase> dameClasesConDisponibilidad();

>>>>>>> db4922b1a1225ec8476fbf850b2cbb3ed84b9770
}

