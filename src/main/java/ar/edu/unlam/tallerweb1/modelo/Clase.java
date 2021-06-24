package ar.edu.unlam.tallerweb1.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;

import javax.persistence.ManyToOne;


@Entity
public class Clase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private	Long capacidad;
	private String HorarioYFecha;

	@ManyToOne(optional = false , cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, fetch = FetchType.EAGER)
	private Profesor profesor;
	
	@ManyToMany(mappedBy = "clases")
	private Set<Usuario> usuarios = new HashSet<Usuario>();
	
	public Clase(String nombre, String horarioYfecha, Profesor profesor, Long capacidad) {
		super();
		this.profesor = profesor;
		this.nombre = nombre;
		this.HorarioYFecha = horarioYfecha;
		this.capacidad = capacidad;
	}
	
	

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
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



	/*
	public Long getProfesor() {
		return profesor;
	}
	public void setProfesor(Long profesor) {
		this.profesor = profesor;
	}
	*/


	public Set<Usuario> getUsuarios() {
		return usuarios;
	}



	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}



	
}