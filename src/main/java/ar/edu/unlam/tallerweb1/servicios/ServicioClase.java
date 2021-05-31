package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.DatosClase;
public interface ServicioClase {


	Clase agregarClase (DatosClase clase);
	Clase consultarClase(String nombre);
	List<Clase> consultarTodasLasClases();
	void eliminarClase(Clase clase);
	Clase consultarClasePorId(Long id);
	void modificarClase(DatosClase clase);
}
