package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(path = "/eliminar/{id}", method = RequestMethod.GET)
	public ModelAndView eliminarClase(@PathVariable("id") Long id) {
		Clase buscada = servicioClase.consultarClasePorId(id);
		servicioClase.eliminarClase(buscada);
		return new ModelAndView("redirect:/home");
	}
	@RequestMapping(path = "/modificar/{id}", method = RequestMethod.GET)
	public ModelAndView irAModificarClase(@PathVariable("id") Long id , @ModelAttribute("modificar") DatosClase clase) {
		ModelMap model = new ModelMap();
		Clase claseBuscada = servicioClase.consultarClasePorId(id);
		System.out.println(claseBuscada.getHorarioYFecha() + " " +claseBuscada.getId());
		model.put("clase", claseBuscada);
		return new ModelAndView("modificar",model);
	}
	
	
	@RequestMapping(path = "/modificar/{id}", method = RequestMethod.POST)
	public ModelAndView modificarClase(@PathVariable("id") Long id, @ModelAttribute("modificar") DatosClase datos) {
		ModelMap model = new ModelMap();
		String error;
		
		try {
				servicioClase.modificarClase(id, datos);
				return claseCargadaOk(model);
		} catch (FaltaCupo e) {
			error ="Falto cargar el cupo";
		} catch (NoSeCargoProfesor e) {
			error = "Falto cargar el profesor";
		} catch (NoSeCargoUnaFecha e) {
			error = "Falto cargar la hora y fecha";
		}
		model.put("error", error);
		return new ModelAndView("modificar",model);
		
	}
	
	private ModelAndView claseCargadaOk(ModelMap model) {
		model.put("cargadaOk", true);
		return new ModelAndView("redirect:/home", model);
	}

	private ModelAndView registrarClaseError(ModelMap model, String error) {
		model.put("error", error);
		model.put("registrarClase", new DatosClase());
		
		return new ModelAndView("agregar-clase", model);
	}

	public ModelAndView registroConClaseExistente() {
		ModelMap model = new ModelMap("error","La clase ya existe");
		return new ModelAndView("agregar-clase",model);
	}

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		Map<Clase, Clase> clasesMap = new HashMap<Clase, Clase>();
		ModelMap model = new ModelMap();
		List<Clase> clases = servicioClase.consultarTodasLasClases();
		
		model.addAttribute("clasesMap",clases);
		return new ModelAndView("home",model);
	}
}