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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "calificaciones_clases")
public class CalificacionClase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_calificacion;
 
    @ManyToOne
    @JoinColumn(name = "id_usuario")
	private Usuario usuario; 
   
    @ManyToOne
    @JoinColumn(name = "id_clase")
 	private Clase clase; 
	
	private Integer calificacion;

	public Long getId_calificacion() {
		return id_calificacion;
	}

	public void setId_calificacion(Long id_calificacion) {
		this.id_calificacion = id_calificacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	} 
	
	public CalificacionClase(){}
 
    public CalificacionClase(Usuario idUsuario,Clase idClase,Integer calificacion){
		this.usuario = idUsuario;
		this.clase = idClase;
		this.calificacion = calificacion;

	}	 


	
}