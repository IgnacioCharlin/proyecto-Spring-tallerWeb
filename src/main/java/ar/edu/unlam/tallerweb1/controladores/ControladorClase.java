package ar.edu.unlam.tallerweb1.controladores;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.excepciones.FaltaCupo;
import ar.edu.unlam.tallerweb1.excepciones.FechaYaPaso;
import ar.edu.unlam.tallerweb1.excepciones.NoEsProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoUnaFecha;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.DatosClase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.servicios.ServicioClase;
import ar.edu.unlam.tallerweb1.servicios.ServicioProfesor;


@Controller
public class ControladorClase {
	private ServicioClase servicioClase;
	private ServicioProfesor servicioProfesor;
	
	@Autowired
	public ControladorClase(ServicioClase servicioClase,ServicioProfesor servicioProfesor) {
		this.servicioClase = servicioClase;
		this.servicioProfesor = servicioProfesor;
	}

	@RequestMapping(path = "/agregar-clase", method = RequestMethod.GET)
	public ModelAndView irAgregarClase() {
		ModelMap model = new ModelMap();
		model.put("registrarClase", new DatosClase());
		List<Profesor> listaProfesores=servicioProfesor.listarProfesores();
		model.put("listaProfesores", listaProfesores);
		
		return new ModelAndView("agregar-clase", model);
	}

	@RequestMapping(path = "/agregarClase", method = RequestMethod.POST)
	public ModelAndView registrarClase(@ModelAttribute("registrarClase") DatosClase clase) {
		ModelMap model = new ModelMap();
		List<Profesor> listaProfesores=servicioProfesor.listarProfesores();
		model.put("listaProfesores", listaProfesores);
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
		}catch (NoEsProfesor e) {
			error = "No existe ese profesor";
		}catch (FechaYaPaso e) {
			error = "Fecha pasada";
		}
		
		return registrarClaseError(model, error);
		
		
	}
	
	@RequestMapping(path = "/eliminar/{id}", method = RequestMethod.GET)
	public ModelAndView eliminarClase(@PathVariable("id") Long id) {
		ModelMap model = new ModelMap();
		Clase buscada = servicioClase.consultarClasePorId(id);
		servicioClase.eliminarClase(buscada);
		model.put("msj","La clase se elimino correctamente.");     
        return new ModelAndView("notificacion",model); 
	}
	@RequestMapping(path = "/modificar/{id}", method = RequestMethod.GET)
	public ModelAndView irAModificarClase(@PathVariable("id") Long id , @ModelAttribute("modificar") DatosClase clase) {
		ModelMap model = new ModelMap();
		Clase claseBuscada = servicioClase.consultarClasePorId(id);
		System.out.println(claseBuscada.getHorarioYFecha() + " " +claseBuscada.getId());
		model.put("clase", claseBuscada);
		List<Profesor> listaProfesores=servicioProfesor.listarProfesores();
		model.put("listaProfesores", listaProfesores);
		
		return new ModelAndView("modificar",model);
	}
	
	
	@RequestMapping(path = "/modificar/{id}", method = RequestMethod.POST)
	public ModelAndView modificarClase(@PathVariable("id") Long id, @ModelAttribute("modificar") DatosClase datos) {
		ModelMap model = new ModelMap();
		String error;
		
		try {
				servicioClase.modificarClase(id, datos);
				model.put("msj","La clase se modifico correctamente.");     
		        return new ModelAndView("notificacion",model); 
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
	
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome(HttpServletRequest request) {
		String rolUsuario=  (String) request.getSession().getAttribute("rolUsuario");
		
		if(rolUsuario != null) {
		Map<Clase, Clase> clasesMap = new HashMap<Clase, Clase>();
		ModelMap model = new ModelMap();
		List<Clase> clases = servicioClase.consultarTodasLasClases();
		
		model.addAttribute("clasesMap",clases);
		return new ModelAndView("home",model);
		}
		else {
			return new ModelAndView("redirect:/login");
		}
	}
	
	@RequestMapping("clases-disponibles")
	public ModelAndView irAClasesDisponibles(HttpServletRequest request){
		String rolUsuario=  (String) request.getSession().getAttribute("rolUsuario");
		if(rolUsuario == null)
			return new ModelAndView("redirect:/login");
			
		ModelMap model = new ModelMap();
		Map<Clase, Clase> clasesMap = new HashMap<Clase, Clase>();
		List<Clase> clases = servicioClase.consultarTodasLasClases();
		model.addAttribute("clasesMap",clases);
		return new ModelAndView("clases-disponibles",model);
	}
	
	@RequestMapping("clases-eliminadas")
	public ModelAndView irAClasesEliminadas(HttpServletRequest request){
		String rolUsuario=  (String) request.getSession().getAttribute("rolUsuario");
		if(rolUsuario == null)
			return new ModelAndView("redirect:/login");
			
		ModelMap model = new ModelMap();
		Map<Clase, Clase> clasesMap = new HashMap<Clase, Clase>();
		List<Clase> clases = servicioClase.consultarClasesEliminadas();
		model.addAttribute("clasesMap",clases);
		return new ModelAndView("clases-eliminadas",model);
	}
	
	
	@RequestMapping(path = "filtar-clase" , method = RequestMethod.GET)
	public ModelAndView buscoClasePorFechas(@RequestParam("desde") String desde,@RequestParam("hasta") String hasta) {
		ModelMap model = new ModelMap();
		Map<Clase, Clase> clasesMap = new HashMap<Clase, Clase>();
		List<Clase> clases = servicioClase.consultarClasePorFiltroFecha(desde, hasta);
		
		model.addAttribute("clasesMap",clases);
		return new ModelAndView("clases-disponibles",model);
    }
	
	@RequestMapping(path = "filtar-profesor" , method = RequestMethod.GET)
	public ModelAndView buscoClase(@RequestParam(required = false ,name = "email") String email) {
		String error;
		ModelMap model = new ModelMap();
		Map<Clase, Clase> clasesMap = new HashMap<Clase, Clase>();
		List<Clase> clases;
		try {
			
			if (email != null) {
				Profesor profesor = servicioProfesor.buscarProfesorPorEmail(email);
				clases = servicioClase.consultarClasesPorIdProfesor(profesor.getId());			
			}else {
				clases = servicioClase.consultarTodasLasClases();
			}
			model.addAttribute("clasesMap",clases);
		} catch (NoEsProfesor e) {
			error = "No Existe profesor con ese email";
			model.put("error", error);
		}
		return new ModelAndView("clases-profesor",model);
    }
	
	private ModelAndView claseCargadaOk(ModelMap model) {
		model.put("cargadaOk", true);
		return new ModelAndView("redirect:/home", model);
	}

	private ModelAndView registrarClaseError(ModelMap model, String error) {
		model.put("error", error);
		return new ModelAndView("agregar-clase", model);
	}

	public ModelAndView registroConClaseExistente() {
		ModelMap model = new ModelMap("error","La clase ya existe");
		return new ModelAndView("agregar-clase",model);
	}
}