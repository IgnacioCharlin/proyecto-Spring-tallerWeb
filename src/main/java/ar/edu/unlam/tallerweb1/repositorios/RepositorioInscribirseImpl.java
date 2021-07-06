package ar.edu.unlam.tallerweb1.repositorios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import ar.edu.unlam.tallerweb1.modelo.ClasesInscriptas;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioInscribirse")
public class RepositorioInscribirseImpl implements RepositorioInscribirse{
	

	private SessionFactory sessionFactory;
	@Autowired
	public RepositorioInscribirseImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void guardarInscripcion(Clase clase,Usuario usuario) {
		
		ClasesInscriptas clasesInscripta = new ClasesInscriptas();
		clasesInscripta.setUsuario(usuario);
		clasesInscripta.setClase(clase);
 		sessionFactory.getCurrentSession().save(clasesInscripta);
		
	}
 
 

 
	
	@Override
	public ClasesInscriptas buscarPorUsuarioYClase(Usuario usuario, Clase clase) {
		
	    final Session session = this.sessionFactory.getCurrentSession();
	    return (ClasesInscriptas) session.createCriteria(ClasesInscriptas.class)
	            .add( Restrictions.eq("usuario", usuario ) )
	           .add( Restrictions.eq("clase", clase ) )
	           .uniqueResult();
	}
	@Override
	public List<ClasesInscriptas> buscarPorUsuario(long idUsuario) { 
        
        final Session session = this.sessionFactory.getCurrentSession();
        return session.createCriteria(ClasesInscriptas.class)
                .add(Restrictions.eq("usuario.id", idUsuario) )
                .list();
        
	}
	 
	
	
}