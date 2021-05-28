package ar.edu.unlam.tallerweb1.repositorios;


import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Clase;

public interface RepositorioClase {
	
	Clase guardarClase(Clase clase);
	Clase buscarClase(String nombre);
	void modificarClase(Clase clase);
	void eliminarClase(Clase clase);
	List<Clase> buscarTodasLasClase();
}


