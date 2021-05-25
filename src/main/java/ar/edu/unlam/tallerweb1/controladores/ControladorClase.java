package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Clase;

@Controller
public class ControladorClase {

	@RequestMapping(path = "/agregar-clase", method = RequestMethod.GET)
	public ModelAndView irAgregarClase() {
		ModelMap model = new ModelMap("agregar-clase", new Clase());
		return new ModelAndView("agregar-clase", model);
	}

	@RequestMapping(path = "/agregar-clase", method = RequestMethod.POST)
	public ModelAndView registrarClase(Clase clase) {
		return new ModelAndView("redirect:/home");
	}
	
	public ModelAndView registroConClaseExistente() {
		ModelMap model = new ModelMap("error","La clase ya existe");
		return new ModelAndView("agregar-clase",model);
	}

}
