package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import ar.edu.unlam.tallerweb1.modelo.Clase;

@Repository("repositorioClase")
public class RepositorioClaseImpl implements RepositorioClase{

	private SessionFactory sessionFactory;
	
	
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

	
	
}




