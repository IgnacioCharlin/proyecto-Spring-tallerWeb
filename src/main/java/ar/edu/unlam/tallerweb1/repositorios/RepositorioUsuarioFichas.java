package ar.edu.unlam.tallerweb1.repositorios;


import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.TarjetasCompradas;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuariosFichas;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuarioFichas {

	UsuariosFichas actualizoFichas(UsuariosFichas fichasActualesUsuario, TarjetasCompradas tarjetaComprada);

	UsuariosFichas buscoFichas(Usuario usuario);

	UsuariosFichas guardoFichas(Usuario usuario, TarjetasCompradas tarjetaComprada);

	void descuentoUnaClase(UsuariosFichas fichasActualesUsuario); 
	
}