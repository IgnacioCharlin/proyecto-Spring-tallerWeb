package ar.edu.unlam.tallerweb1.repositorios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;

@Repository("repositorioClase")
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
		
		System.out.println("nombre de la clase antes de ser guardada" + clase.getNombre());
		sessionFactory.getCurrentSession().update(clase);
	}
	
	@Override
	public void eliminarClase(Clase clase) {
		sessionFactory.getCurrentSession().delete(clase);
	}

	@Override
	public List<Clase> buscarTodasLasClase() {
		String fechaHoy = LocalDate.now().toString();
		return sessionFactory.getCurrentSession().createCriteria(Clase.class)
				.add(Restrictions.ge("HorarioYFecha",fechaHoy))
				.list();
	}

	@Override
	public Clase buscarClasePorId(Long id) {
		return (Clase) sessionFactory.getCurrentSession().createCriteria(Clase.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
	}
	
	@Override
	public List<Clase> buscarClasePorProfesor(Profesor profesor) {
		return sessionFactory.getCurrentSession().createCriteria(Clase.class)
				.add(Restrictions.eq("profesor", profesor))
				.list();
	}
	
	@Override
	public List<Clase> filtrarClasesPorFecha(String fechaDesde, String fechaHasta) {
		return sessionFactory.getCurrentSession().createCriteria(Clase.class)
		.add(Restrictions.between("HorarioYFecha", fechaDesde, fechaHasta))
		.list();
	}
}