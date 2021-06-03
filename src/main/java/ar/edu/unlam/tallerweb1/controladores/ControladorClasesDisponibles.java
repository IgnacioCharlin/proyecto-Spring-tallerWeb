package ar.edu.unlam.tallerweb1.controladores;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.servicios.ServicioClase;

@Controller
public class ControladorClasesDisponibles {

	private ServicioClase servicioClase;
	
	@Autowired
	public ControladorClasesDisponibles(ServicioClase servicioClase) {
		this.servicioClase = servicioClase; 
	}
	
	@RequestMapping("clases-disponibles")
	public ModelAndView irAClasesDisponibles(){
		ModelMap model = new ModelMap();
		Map<Clase, Clase> clasesMap = new HashMap<Clase, Clase>();
		List<Clase> clases = servicioClase.consultarTodasLasClases();
		model.addAttribute("clasesMap",clases);
		return new ModelAndView("clases-disponibles",model);
	}
}
