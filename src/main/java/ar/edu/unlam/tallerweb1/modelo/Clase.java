package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Clase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private	Long capacidad;
	private String HorarioYFecha;
	
	public Clase() {
		super();
	}
	
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Long getCapacidad() {
		return capacidad;
	}



	public void setCapacidad(Long capacidad) {
		this.capacidad = capacidad;
	}



	public String getHorarioYFecha() {
		return HorarioYFecha;
	}



	public void setHorarioYFecha(String horarioYFecha) {
		HorarioYFecha = horarioYFecha;
	}



	
}
