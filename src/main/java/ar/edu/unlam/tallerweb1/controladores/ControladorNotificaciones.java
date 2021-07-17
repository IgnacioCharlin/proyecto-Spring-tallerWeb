package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioClase;
import ar.edu.unlam.tallerweb1.servicios.ServicioInscribirse;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

import java.util.List;

import javax.servlet.http.HttpServletRequest;



@Controller
public class ControladorNotificaciones {

	
	private ServicioInscribirse servicioInscribirse;
	private ServicioUsuario servicioUsuario;
	private ServicioClase servicioClase;

	
	@Autowired
	public ControladorNotificaciones(ServicioInscribirse servicioInscribirse,ServicioUsuario servicioUsuario,ServicioClase servicioClase) {
		this.servicioInscribirse = servicioInscribirse;
		this.servicioUsuario = servicioUsuario;
		this.servicioClase = servicioClase;
	}
	
	
	@RequestMapping(path = "/notificaciones", method = RequestMethod.GET)
	public ModelAndView verNotificaciones(HttpServletRequest request) {
		ModelMap model = new ModelMap();
		Long idUsuario = (Long) request.getSession().getAttribute("idUsuario");
		List<Clase> clases = servicioInscribirse.notificar(idUsuario);
		model.put("clases", clases);
		return new ModelAndView("notificaciones", model);
		
	}
	
	
	@RequestMapping(path = "/notificaciones/leerNotificacion/{idClase}", method = RequestMethod.GET)
	public ModelAndView verNotificaciones(@PathVariable Long idClase,HttpServletRequest request) {
		Long idUsuario = (Long) request.getSession().getAttribute("idUsuario");
		Usuario buscado = servicioUsuario.consultarUsuarioPorId(idUsuario);
		Clase buscada = servicioClase.buscarClaseId(idClase);
		servicioInscribirse.leerNotificacion(buscada, buscado);
		
		return new ModelAndView("redirect:/notificaciones");
		
	}
}
