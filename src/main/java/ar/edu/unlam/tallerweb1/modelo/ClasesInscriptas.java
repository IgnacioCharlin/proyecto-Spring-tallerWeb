package ar.edu.unlam.tallerweb1.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "clases_inscriptas")
public class ClasesInscriptas{
	
	private Boolean notificado;
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
    @JoinColumn(name = "id_usuario")
	private Usuario usuario; 
   
    @ManyToOne
    @JoinColumn(name = "id_clase")
 	private Clase clase; 
	
 
  
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

 
	public ClasesInscriptas(){
		this.notificado = false;
	}
 
    public ClasesInscriptas(Usuario idUsuario,Clase idClase){
		this.usuario = idUsuario;
		this.clase = idClase;
 
	}	 
    
	public Boolean getNotificado() {
		return notificado;
	}

	public void setNotificado(Boolean notificado) {
		this.notificado = notificado;
	}

	
}