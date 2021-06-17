package ar.edu.unlam.tallerweb1.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Clase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private	Long capacidad;
	private String HorarioYFecha;
	private ArrayList<Long> profesores;
	
	@ManyToMany(mappedBy = "clases")
	private Set<Usuario> usuarios = new HashSet<Usuario>();
	
	public Clase(String nombre, String horarioYfecha, Long idProfesor, Long capacidad) {
		super();
		this.profesores = new ArrayList<Long>();
		this.nombre = nombre;
		this.HorarioYFecha = horarioYfecha;
		this.capacidad = capacidad;
		this.setProfesor(idProfesor);
		
	}
	
	

	public Clase() {
		super();
		this.profesores = new ArrayList<Long>();
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



	public void setProfesor(Long idProfesor) {
		if(idProfesor != null)
			profesores.add(idProfesor);
		
	}



	public ArrayList<Long> getProfesores() {
		return profesores;
	}



	public Set<Usuario> getUsuarios() {
		return usuarios;
	}



	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}



	
}
