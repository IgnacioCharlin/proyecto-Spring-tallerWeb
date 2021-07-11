package ar.edu.unlam.tallerweb1.modelo;

import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "tarjetas_compradas")
public class TarjetasCompradas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
 
    @ManyToOne
    @JoinColumn(name = "id_usuario")
	private Usuario usuario; 
   
    @ManyToOne
    @JoinColumn(name = "id_tarjeta")
 	private Tarjeta tarjeta; 
	
	private Integer cantidad;


	private Date fecha;
	private String estado;
	private String idMercadoPago;

	public String getIdMercadoPago() {
		return idMercadoPago;
	}

	public void setIdMercadoPago(String idMercadoPago) {
		this.idMercadoPago = idMercadoPago;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
   


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}	 

	public TarjetasCompradas(){}
	 
    public TarjetasCompradas(Usuario idUsuario,Tarjeta idTarjeta,Integer cantidad,Date fecha,String estado,String idMercadoPago){
		this.usuario = idUsuario;
		this.tarjeta = idTarjeta;
		this.cantidad = cantidad;
		this.fecha= fecha;
		this.estado = estado;
		this.idMercadoPago = idMercadoPago;

		

	}

 
	
}