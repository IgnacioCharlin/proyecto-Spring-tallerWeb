package ar.edu.unlam.tallerweb1.modelo;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Clase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer capacidad;
	private Usuario profesor;
	private ArrayList<Usuario> alumnos;
	private String nombre;
	
	public Clase() {
		super();
	}
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
	public Usuario getProfesor() {
		return profesor;
	}
	public void setProfesor(Usuario profesor) {
		this.profesor = profesor;
	}
	public ArrayList<Usuario> getAlumnos() {
		return alumnos;
	}
	public void setAlumnos(ArrayList<Usuario> alumnos) {
		this.alumnos = alumnos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Clase [id=" + id + ", capacidad=" + capacidad + ", profesor=" + profesor + ", alumnos=" + alumnos
				+ ", nombre=" + nombre + "]";
	}
	
	
}
