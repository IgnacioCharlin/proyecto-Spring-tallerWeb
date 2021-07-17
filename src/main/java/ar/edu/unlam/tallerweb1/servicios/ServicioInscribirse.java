package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ar.edu.unlam.tallerweb1.modelo.AsistenciaClase;
import ar.edu.unlam.tallerweb1.modelo.CalificacionClase;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.ClasesInscriptas;
import ar.edu.unlam.tallerweb1.modelo.DatosClase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
public interface ServicioInscribirse{

	ClasesInscriptas buscarInscripcion(Clase buscadaAInscribirse, Usuario usuarioAinscribirse);

	void guardarInscripcion(Clase buscadaAInscribirse, Usuario usuarioAinscribirse);

	List<ClasesInscriptas> consultarUsuarioPorId(long idUsuario);

	List<Clase> notificar(Long idUsuario);

	void leerNotificacion(Clase clase, Usuario usuario);

 }
