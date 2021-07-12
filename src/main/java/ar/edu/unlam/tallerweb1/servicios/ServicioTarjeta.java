package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AsistenciaClase;
import ar.edu.unlam.tallerweb1.modelo.CalificacionClase;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.DatosClase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.modelo.Tarjeta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
public interface ServicioTarjeta {

 	List<Tarjeta> dameTarjetasDisponibles(); 
}
