package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.excepciones.FaltaCupo;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoUnaFecha;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.DatosClase;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClase;

@Service
@Transactional
public class ServicioClaseImpl implements ServicioClase {
	
	private RepositorioClase repositorioClase;
	
	@Autowired
	public ServicioClaseImpl(RepositorioClase repositorioClase) {
		this.repositorioClase = repositorioClase;
	}
	
	@Override
	public Clase agregarClase(DatosClase clase) {
		if(clase.getFechaYHora() == null)
			throw new NoSeCargoUnaFecha();
		if( clase.getCupo() == null ||clase.getCupo() <= 0 )
			throw new FaltaCupo();
		if(clase.getIdProfesor() == null)
			throw new NoSeCargoProfesor();
		
		//recibir datos y guardar Clase claseclase -->repositorioClase.agregarClase()
		
		Clase nuevaClase = new Clase(clase.getNombre(), clase.getFechaYHora(), clase.getIdProfesor(), clase.getCupo());
		repositorioClase.guardarClase(nuevaClase);
		return nuevaClase;
	}

	@Override
	public Clase consultarClase(String nombre) {
		return repositorioClase.buscarClase(nombre);
	}
}

