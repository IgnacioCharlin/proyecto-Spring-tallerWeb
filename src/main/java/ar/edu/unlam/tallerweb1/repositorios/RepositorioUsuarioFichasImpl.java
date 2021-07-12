package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.TarjetasCompradas;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuariosFichas;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

 
@Repository("repositorioUsuariosFichas")
public class RepositorioUsuarioFichasImpl implements RepositorioUsuarioFichas {

 
	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioUsuarioFichasImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	
	@Override
	public UsuariosFichas buscoFichas(Usuario usuario) {
		return (UsuariosFichas) sessionFactory.getCurrentSession().createCriteria(UsuariosFichas.class)
				.add(Restrictions.eq("usuario", usuario))
				.uniqueResult();
	}

	@Override
	public UsuariosFichas guardoFichas(Usuario usuario, TarjetasCompradas tarjetaComprada) {

		UsuariosFichas usuarioFichas = new UsuariosFichas();
		usuarioFichas.setUsuario(usuario);
		usuarioFichas.setCantidad(tarjetaComprada.getCantidad()); 
		sessionFactory.getCurrentSession().save(usuarioFichas);
		return usuarioFichas;
		
		
	}
 
	
	@Override
	public UsuariosFichas actualizoFichas(UsuariosFichas usuariosFichas, TarjetasCompradas tarjetaComprada) {
		Integer nuevasFichas=usuariosFichas.getCantidad()+tarjetaComprada.getCantidad();
		
		usuariosFichas.setCantidad(nuevasFichas);
		sessionFactory.getCurrentSession().save(usuariosFichas);
		return usuariosFichas;
	}



	@Override
	public void descuentoUnaClase(UsuariosFichas fichasActualesUsuario) {
		fichasActualesUsuario.setCantidad(fichasActualesUsuario.getCantidad()-1);
		sessionFactory.getCurrentSession().save(fichasActualesUsuario);		
	}


}

