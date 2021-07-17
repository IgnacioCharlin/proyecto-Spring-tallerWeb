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
	@Override
	public List<Clase> clasesConNotificacion(Long idUsuario) {
		String fechaHoy = LocalDate.now().toString();
		String fechaHoyMas1 = LocalDate.now().plusDays(2).toString();
		
		
		String where =" clase.HorarioYFecha between  '"+fechaHoy+ "' and '"+fechaHoyMas1+"'";
		   
		SQLQuery  query =	sessionFactory.getCurrentSession().createSQLQuery(""
				+ " SELECT clase.*"
				+ " FROM clase  "
				+ " LEFT join clases_inscriptas on clases_inscriptas.id_clase=clase.id "
				+ " where"
				+ where +"AND clases_inscriptas.notificado = false "
				+ "AND id_usuario = " + idUsuario
				
//			    + " GROUP by (clase.id) "
			    + " ORDER BY clase.HorarioYFecha asc"

			    //+ " HAVING clase.capacidad>count(clases_inscriptas.id_usuario)"

 			    )
				;
 			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
 return query.list();
	
	}
	@Override
	public ClasesInscriptas buscarClasePorId(Long id) {
		return (ClasesInscriptas) sessionFactory.getCurrentSession().createCriteria(ClasesInscriptas.class)
				.add(Restrictions.eq("id_clase", id))
				.uniqueResult();
	}
	@Override
	public void guardarClase(ClasesInscriptas claseIncripta) {
		this.sessionFactory.getCurrentSession().update(claseIncripta);
		
	}
	 
	
	
}