package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Profesor;

@Repository("repositorioProfesor")
public class RepositorioProfesorImp implements RepositorioProfesor {

	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioProfesorImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Profesor agregarProfesor(Profesor profesor) {
		Profesor profesorBuscado = buscarProfesorPorMail(profesor.getEmail());
		if(profesorBuscado == null && profesor.getRol().equals("profesor")) {
			sessionFactory.getCurrentSession().save(profesor);
			return profesor;
		}
		return null;
	}

	@Override
	public Profesor buscarProfesorPorMail(String email) {
		return (Profesor) sessionFactory.getCurrentSession().createCriteria(Profesor.class)
				.add(Restrictions.eq("email", email))
				.uniqueResult();
	}

	@Override
	public Profesor buscarProfesorPorId(long id) {
		return (Profesor) sessionFactory.getCurrentSession().createCriteria(Profesor.class)
		.add(Restrictions.eq("id", id))
		.uniqueResult();
	}

}
