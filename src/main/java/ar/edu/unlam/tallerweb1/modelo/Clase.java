package ar.edu.unlam.tallerweb1.modelo;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Clase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private	Long capacidad;
	private String HorarioYFecha;

	//@ManyToOne(optional = false , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Long profesor;
	
	public Clase(String nombre, String horarioYfecha, Long long1, Long capacidad) {
		super();
		this.profesor = long1;
		this.nombre = nombre;
		this.HorarioYFecha = horarioYfecha;
		this.capacidad = capacidad;
	}
	
	

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



	public Long getProfesor() {
		return profesor;
	}



	public void setProfesor(Long profesor) {
		this.profesor = profesor;
	}



	
}
