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
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioAsistencia")
public class RepositorioAsistenciaImpl implements RepositorioAsistencia{
	

	private SessionFactory sessionFactory;
	@Autowired
	public RepositorioAsistenciaImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void guardarAsistencia(Clase clase,Usuario usuario) {
		
		AsistenciaClase asistencia = new AsistenciaClase();
		asistencia.setUsuario(usuario);
		asistencia.setClase(clase);
		asistencia.setPresente(0);
		sessionFactory.getCurrentSession().save(asistencia);
		
	}
 
 
	@Override
	public void modificarAsistencia(AsistenciaClase buscoAsistencia, Integer presente) {
		buscoAsistencia.setPresente(presente);
		sessionFactory.getCurrentSession().save(buscoAsistencia);

	}
 
	
	@Override
	public AsistenciaClase buscarPorUsuarioYClase(Usuario usuario, Clase clase) {
		
	    final Session session = this.sessionFactory.getCurrentSession();
	    return (AsistenciaClase) session.createCriteria(AsistenciaClase.class)
	            .add( Restrictions.eq("usuario", usuario ) )
	           .add( Restrictions.eq("clase", clase ) )
	           .uniqueResult();
	}
	@Override
	public List<AsistenciaClase> dameAsistenciaPorClase(Clase clase) {
		
	
        final Session session = this.sessionFactory.getCurrentSession();
        return session.createCriteria(AsistenciaClase.class)
               .add(Restrictions.eq("clase", clase) )
               .list();
    
	}
	@Override
	public AsistenciaClase buscarPorUsuarioYClase(Usuario usuario, Clase clase, Integer presente) {
	    final Session session = this.sessionFactory.getCurrentSession();
	    return (AsistenciaClase) session.createCriteria(AsistenciaClase.class)
	           .add( Restrictions.eq("usuario.id", usuario.getId() ) )
		       .add( Restrictions.eq("clase.id", clase.getId() ) )
	           .add( Restrictions.eq("presente", presente ) )
	           .uniqueResult();
	}
	
	
	
}