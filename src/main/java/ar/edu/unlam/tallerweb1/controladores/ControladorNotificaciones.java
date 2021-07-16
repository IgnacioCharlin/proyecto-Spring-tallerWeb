package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.servicios.ServicioClase;

import java.util.List;

import javax.servlet.http.HttpServletRequest;



@Controller
public class ControladorNotificaciones {

	
	private ServicioClase servicioClase;
	
	@Autowired
	public ControladorNotificaciones(ServicioClase servicioClase) {
		this.servicioClase = servicioClase;
	}
	
	
	@RequestMapping(path = "/notificaciones", method = RequestMethod.GET)
	public ModelAndView verNotificaciones(HttpServletRequest request) {
		ModelMap model = new ModelMap();
		Long idUsuario = (Long) request.getSession().getAttribute("idUsuario");
		List<Clase> clases = servicioClase.notificar(idUsuario);
		model.put("clases", clases);
		return new ModelAndView("notificaciones", model);
		
	}
	@RequestMapping(path = "/notificaciones/leerNotificacion/{idClase}", method = RequestMethod.GET)
	public ModelAndView verNotificaciones(@PathVariable Long idClase) {
		
		
		servicioClase.leerNotificacion(idClase);
		
		return new ModelAndView("redirect:/notificaciones");
		
	}
}
