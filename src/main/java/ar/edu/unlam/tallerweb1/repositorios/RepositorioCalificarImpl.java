package ar.edu.unlam.tallerweb1.repositorios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.CalificacionClase;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioCalificar")
public class RepositorioCalificarImpl implements RepositorioCalificar{
	

	private SessionFactory sessionFactory;
	@Autowired
	public RepositorioCalificarImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void guardarCalificacion(Clase clase,Usuario usuario,Integer calificacion) {
		
		CalificacionClase compararPlato = new CalificacionClase();
		compararPlato.setUsuario(usuario);
		compararPlato.setClase(clase);
		compararPlato.setCalificacion(calificacion);
		sessionFactory.getCurrentSession().save(compararPlato);
		
	}
 
	@Override
	public List<CalificacionClase> buscarCalificacionesPorUsuario(Usuario usuario) {
		
        final Session session = this.sessionFactory.getCurrentSession();
        return session.createCriteria(CalificacionClase.class)
               .add( Restrictions.eq("usuario", usuario ) )
                .list();
 
	}
	@Override
	public CalificacionClase buscarPorUsuarioYClase(Usuario usuario, Clase clase) {
		
        final Session session = this.sessionFactory.getCurrentSession();
        return (CalificacionClase) session.createCriteria(CalificacionClase.class)
                .add( Restrictions.eq("usuario", usuario ) )
               .add( Restrictions.eq("clase", clase ) )
               .uniqueResult();
	}
	@Override
	public void modificarCalificacion(CalificacionClase buscoClasificacion, Integer calificacion) {
		buscoClasificacion.setCalificacion(calificacion);
		sessionFactory.getCurrentSession().save(buscoClasificacion);

	}
 
	
	
}