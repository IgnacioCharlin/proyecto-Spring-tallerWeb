package ar.edu.unlam.tallerweb1.repositorios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.AsistenciaClase;
import ar.edu.unlam.tallerweb1.modelo.CalificacionClase;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.modelo.Tarjeta;
import ar.edu.unlam.tallerweb1.modelo.TarjetasCompradas;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioTarjetasCompradas")
public class RepositorioTarjetasCompradasImpl implements RepositorioTarjetasCompradas{
	

	private SessionFactory sessionFactory;
	@Autowired
	public RepositorioTarjetasCompradasImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public TarjetasCompradas guardarCompra(Tarjeta tarjeta, Usuario usuario, Date fechaCompra,String estado,String collection_id) {
		
		TarjetasCompradas tarjetaComprada = new TarjetasCompradas();
		tarjetaComprada.setUsuario(usuario);
		tarjetaComprada.setTarjeta(tarjeta);
		tarjetaComprada.setFecha(fechaCompra);
		tarjetaComprada.setCantidad(tarjeta.getCantidad());
		tarjetaComprada.setEstado(estado);
		tarjetaComprada.setIdMercadoPago(collection_id);
		sessionFactory.getCurrentSession().save(tarjetaComprada);
		return tarjetaComprada;
		
	}
	@Override
	public List<TarjetasCompradas> buscarComprasPorUsuario(Usuario usuario) {
        final Session session = this.sessionFactory.getCurrentSession();
        return session.createCriteria(TarjetasCompradas.class)
               .add(Restrictions.eq("usuario", usuario) )
               .list();
	}
	
 
	
}