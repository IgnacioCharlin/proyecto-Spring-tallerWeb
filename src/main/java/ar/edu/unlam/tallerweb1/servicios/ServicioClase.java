package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.DatosClase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
public interface ServicioClase {


	Clase agregarClase (DatosClase clase);
	Clase consultarClase(String nombre);
	List<Clase> consultarTodasLasClases();
	void eliminarClase(Clase clase);
	Clase consultarClasePorId(Long id);
	void modificarClase(Long id,DatosClase datos);
	List<Clase> consultarClasesPorIdProfesor(long id);
	List<Clase> consultarClasePorFiltroFecha(String desde, String hasta);
}
