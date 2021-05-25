package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Clase;

@Repository("repositorioClase")
public class RepositorioClaseImpl implements RepositorioClase{

	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioClaseImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Clase agregarClase(Clase clase) {
		final Session session = sessionFactory.getCurrentSession();
		return (Clase) session.createCriteria(Clase.class)
				.add(Restrictions.eq("nombre", clase.getNombre()));
	}
	
	@Override
	public void guardarClase(Clase clase) {
		sessionFactory.getCurrentSession().save(clase);
	}


	@Override
	public Clase buscarClase(String nombre) {
		return (Clase) sessionFactory.getCurrentSession().createCriteria(Clase.class)
					.add(Restrictions.eq("nombre", nombre))
					.uniqueResult();
	}


	

	
	
}




