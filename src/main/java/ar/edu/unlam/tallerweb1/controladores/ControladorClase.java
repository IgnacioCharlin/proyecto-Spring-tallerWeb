package ar.edu.unlam.tallerweb1.controladores;

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
import ar.edu.unlam.tallerweb1.servicios.ServicioClase;
import ar.edu.unlam.tallerweb1.servicios.ServicioClaseImpl;

@Controller
public class ControladorClase {
	@Autowired
	private ServicioClase servicioClase = new ServicioClaseImpl();

	@RequestMapping(path = "/agregar-clase", method = RequestMethod.GET)
	public ModelAndView irAgregarClase() {
		ModelMap model = new ModelMap();
		model.put("registrarClase", new DatosClase());
		return new ModelAndView("agregar-clase", model);
	}

	@RequestMapping(path = "/agregar-clase", method = RequestMethod.POST)
	public ModelAndView registrarClase(@ModelAttribute DatosClase clase) {
		ModelMap model = new ModelMap();
		
		try {
				servicioClase.agregarClase(clase);
		} catch (FaltaCupo e) {
			return registrarClaseError(model, "Falto cargar el cupo");
		}catch (NoSeCargoProfesor e) {
			return registrarClaseError(model, "Falto cargar el profesor");
		}catch (NoSeCargoUnaFecha e) {
			return registrarClaseError(model, "Falto cargar la hora y fecha");
		}
		
		
		return claseCargadaOk(model);
	}
	
	private ModelAndView claseCargadaOk(ModelMap model) {
		model.put("cargadaOk", true);
		
		return new ModelAndView("redirect:/home", model);
	}

	private ModelAndView registrarClaseError(ModelMap model, String error) {
		model.put("error", error);
		model.put("registrarClase", new DatosClase());
		
		return new ModelAndView("redirect:/agregar-clase");
	}

	public ModelAndView registroConClaseExistente() {
		ModelMap model = new ModelMap("error","La clase ya existe");
		return new ModelAndView("agregar-clase",model);
	}

}
