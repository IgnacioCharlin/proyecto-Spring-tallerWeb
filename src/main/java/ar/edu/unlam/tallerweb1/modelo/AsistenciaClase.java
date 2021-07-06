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
@Table(name = "asistencia_clases")
public class AsistenciaClase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_asistencia_clases;
 
    @ManyToOne
    @JoinColumn(name = "id_usuario")
	private Usuario usuario; 
   
    @ManyToOne
    @JoinColumn(name = "id_clase")
 	private Clase clase; 
	
	private Integer presente;

 
	public Integer getPresente() {
		return presente;
	}

	public void setPresente(Integer presente) {
		this.presente = presente;
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

 
	public AsistenciaClase(){}
 
    public AsistenciaClase(Usuario idUsuario,Clase idClase,Integer presente){
		this.usuario = idUsuario;
		this.clase = idClase;
		this.presente = presente;

	}	 


	
}