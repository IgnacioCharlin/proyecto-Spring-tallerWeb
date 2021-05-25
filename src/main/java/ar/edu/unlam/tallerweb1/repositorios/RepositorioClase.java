package ar.edu.unlam.tallerweb1.repositorios;

import java.util.Map;

import java.util.HashMap;


import ar.edu.unlam.tallerweb1.modelo.Clase;

public interface RepositorioClase {
	
	Clase agregarClase (Clase clase);
	void guardarClase(Clase clase);
	Clase buscarClase(String nombre);

}


