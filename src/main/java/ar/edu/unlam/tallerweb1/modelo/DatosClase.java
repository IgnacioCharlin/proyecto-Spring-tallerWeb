package ar.edu.unlam.tallerweb1.modelo;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

public class DatosClase {
    private String nombre;
    private String fechaYHora;
    //private Profesor profesor;	
    private Long idProfesor;
    private Long cupo;
	
    
    public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFechaYHora() {
		return fechaYHora;
	}
	public void setFechaYHora(String fechaYHora) {
		this.fechaYHora = fechaYHora;
	}
	
	public Long getIdProfesor() {
		return idProfesor;
	}
	public void setIdProfesor(Long idProfesor) {
		this.idProfesor = idProfesor;
	}
	
	
	public Long getCupo() {
		return cupo;
	}
	/*
	public Profesor getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	*/
	public void setCupo(Long cupo) {
		this.cupo = cupo;
	}

}
