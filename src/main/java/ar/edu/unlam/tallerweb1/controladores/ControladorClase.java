package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.excepciones.FaltaCupo;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoUnaFecha;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.DatosClase;
import ar.edu.unlam.tallerweb1.servicios.ServicioClase;


@Controller
public class ControladorClase {
	private ServicioClase servicioClase;
	
	@Autowired
	public ControladorClase(ServicioClase servicioClase) {
		this.servicioClase = servicioClase;
	}

	@RequestMapping(path = "/agregar-clase", method = RequestMethod.GET)
	public ModelAndView irAgregarClase() {
		ModelMap model = new ModelMap();
		model.put("registrarClase", new DatosClase());
		return new ModelAndView("agregar-clase", model);
	}

	@RequestMapping(path = "/agregarClase", method = RequestMethod.POST)
	public ModelAndView registrarClase(@ModelAttribute("registrarClase") DatosClase clase) {
		ModelMap model = new ModelMap();
		String error;
		
		try {
				servicioClase.agregarClase(clase);
				return claseCargadaOk(model);
		} catch (FaltaCupo e) {
			error ="Falto cargar el cupo";
			
		}catch (NoSeCargoProfesor e) {
			error = "Falto cargar el profesor";
		}catch (NoSeCargoUnaFecha e) {
			error = "Falto cargar la hora y fecha";
		}
		
		return registrarClaseError(model, error);
		
		
	}
	
	private ModelAndView claseCargadaOk(ModelMap model) {
		model.put("cargadaOk", true);
		return new ModelAndView("redirect:/home", model);
	}

	private ModelAndView registrarClaseError(ModelMap model, String error) {
		model.put("error", error);
		model.put("registrarClase", new DatosClase());
		
		return new ModelAndView("redirect:/agregar-clase", model);
	}

	public ModelAndView registroConClaseExistente() {
		ModelMap model = new ModelMap("error","La clase ya existe");
		return new ModelAndView("agregar-clase",model);
	}

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		ModelMap model = new ModelMap();
		ArrayList<Clase> clases = servicioClase.consultarTodasLasClases();
		for (Clase clase : clases) {			
			model.put("nombre", clase.getNombre());
		}
		return new ModelAndView("home",model);
	}
}