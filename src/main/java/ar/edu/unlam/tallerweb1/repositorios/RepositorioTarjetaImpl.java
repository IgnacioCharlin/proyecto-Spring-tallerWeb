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
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioTarjeta")
public class RepositorioTarjetaImpl implements RepositorioTarjeta{
	

	private SessionFactory sessionFactory;
	@Autowired
	public RepositorioTarjetaImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Tarjeta> dameTarjetasDisponibles() {
		Integer estado = 1;
        final Session session = this.sessionFactory.getCurrentSession();
        return session.createCriteria(Tarjeta.class)
               .add(Restrictions.eq("estado", estado) )
               .list();
        
        
	}

	@Override
	public Tarjeta buscarTarjetaPorId(long idTarjeta) {
		return (Tarjeta) sessionFactory.getCurrentSession().createCriteria(Tarjeta.class)
				.add(Restrictions.eq("id", idTarjeta))
				.uniqueResult();
	} 
	
}