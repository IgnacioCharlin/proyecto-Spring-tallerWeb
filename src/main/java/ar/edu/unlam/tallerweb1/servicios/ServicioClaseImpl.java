package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.excepciones.FaltaCupo;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoUnaFecha;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClase;

@Service("servicioClase")
@Transactional
public class ServicioClaseImpl implements ServicioClase {
	//private RepositorioClase tablaDeClases = RepositorioClase.getInstance();
	
	@Override
	public Clase agregarClase(Clase clase) {
		if(clase.getHorarioYFecha() == null)
			throw new NoSeCargoUnaFecha();
		if( clase.getCapacidad() == null ||clase.getCapacidad() <= 0 )
			throw new FaltaCupo();
		if(clase.getProfesores().isEmpty())
			throw new NoSeCargoProfesor();
		
		return clase;
	}
}

