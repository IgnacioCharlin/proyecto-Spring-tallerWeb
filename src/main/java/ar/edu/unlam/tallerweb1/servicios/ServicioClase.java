package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.DatosClase;
public interface ServicioClase {

	Clase agregarClase (DatosClase clase);
	Clase consultarClase(String nombre);

}
