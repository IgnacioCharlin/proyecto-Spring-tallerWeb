package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.servicios.ServicioProfesor;

@Controller
public class ControladorProfesor {
	
	private ServicioProfesor servicioProfesor;
	
	@Autowired
	public ControladorProfesor(ServicioProfesor servicioProfesor) {
		this.servicioProfesor = servicioProfesor;
	}
	
	@RequestMapping (path = "/agregar-profesor", method = RequestMethod.GET)
	public ModelAndView irAgregarProfesor() {
		ModelMap model = new ModelMap();
		model.put("registrarProfesor", new Profesor());
		return new ModelAndView("agregar-profesor" , model);
	}
	
	@RequestMapping (path = "/agregarProfesor" , method = RequestMethod.POST)
	public ModelAndView agregarProfesor(@ModelAttribute("registrarProfesor") Profesor profesor) {
		try {
			ModelMap model = new ModelMap();
			servicioProfesor.agregarProfesor(profesor);
			return new ModelAndView("redirect:/home",model);
		} catch (Exception e) {
			return new ModelAndView("redirect:/home");
		}
	}
}
