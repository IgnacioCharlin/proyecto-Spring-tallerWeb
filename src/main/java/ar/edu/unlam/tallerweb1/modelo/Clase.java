package ar.edu.unlam.tallerweb1.modelo;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class Clase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer capacidad;
	//private Usuario profesor;
	//private ArrayList<Usuario> alumnos;
	private String nombre;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}
	/*
	public Usuario getProfesor() {
		return profesor;
	}
	public void setProfesor(Usuario profesor) {
		this.profesor = profesor;
	}
	*/
	/*
	public ArrayList<Usuario> getAlumnos() {
		return alumnos;
	}
	public void setAlumnos(ArrayList<Usuario> alumnos) {
		this.alumnos = alumnos;
	}*/
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
