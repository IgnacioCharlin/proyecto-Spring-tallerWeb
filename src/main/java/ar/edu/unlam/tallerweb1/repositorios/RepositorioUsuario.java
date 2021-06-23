package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuario {

    Usuario consultarUsuario (Usuario usuario);
    void guardar(Usuario usuario);
    Usuario buscar(String email);
    void modificar(Usuario usuario);
	void eliminar(Usuario usuario);
	Usuario buscarPorId(Long idUsuario);
	List<Clase>ClasesInscriptas(Usuario usuario);
}