package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Clase;

@Repository
public class RepositorioClaseImpl implements RepositorioClase{
	

	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioClaseImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Clase guardarClase(Clase clase) {
		if(buscarClase(clase.getNombre()) == null) {
			sessionFactory.getCurrentSession().save(clase);
			return clase;
		}
		return null;
	}


	@Override
	public Clase buscarClase(String nombre) {
		return (Clase) sessionFactory.getCurrentSession().createCriteria(Clase.class)
					.add(Restrictions.eq("nombre", nombre))
					.uniqueResult();
	}
	
	@Override
	public void modificarClase(Clase clase) {
		sessionFactory.getCurrentSession().update(clase);
	}
	
	@Override
	public void eliminarClase(Clase clase) {
		sessionFactory.getCurrentSession().delete(clase);
	}	
	
}




