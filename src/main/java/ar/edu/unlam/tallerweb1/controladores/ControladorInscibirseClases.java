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
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorInscibirseClases {
	
	
	private ServicioUsuario servicioUsuario;
	private ServicioClase servicioClase;
	
	@Autowired
	public ControladorInscibirseClases(ServicioClase servicioClase, ServicioUsuario servicioUsuario) {
		this.servicioClase = servicioClase;
		this.servicioUsuario = servicioUsuario;
	}
	
	
	@RequestMapping(path = "/inscribirseclase/{id}", method = RequestMethod.GET)
	public ModelAndView inscribirseAunaClase(Usuario usuario, @PathVariable("id") Long id) {
		
		Clase buscadaAInscribirse = servicioClase.consultarClasePorId(id);
		ModelMap model = new ModelMap();
		model.put("clase", buscadaAInscribirse);
		
		return new ModelAndView("inscribirseClase", model);
		
	}
	@RequestMapping(path = "/inscribirseclase/{id}/{idUsuario}", method = RequestMethod.GET)
	public ModelAndView confirmaInscripcion(@PathVariable("idUsuario")Long idUsuario, @PathVariable("id") Long id) {
		
		Clase buscadaAInscribirse = servicioClase.consultarClasePorId(id);
				
		Usuario usuarioAinscribirse = servicioUsuario.consultarUsuarioPorId(idUsuario);
		ModelMap model = new ModelMap();
		
			usuarioAinscribirse.setClase(buscadaAInscribirse);
			servicioUsuario.actualizarUsuario(usuarioAinscribirse);
		
		return new ModelAndView("redirect:/home", model);
		
	}
}
