package ar.edu.unlam.tallerweb1.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.CalificacionClase;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.servicios.ServicioCalificar;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorUsuario {

	
private ServicioUsuario servicioUsuario;
private ServicioCalificar servicioCalificar;


	@Autowired
	public ControladorUsuario(ServicioUsuario servicioLogin,ServicioCalificar servicioCalificar){
		this.servicioUsuario = servicioLogin;
		this.servicioCalificar = servicioCalificar;
		
	}
	
	
	@RequestMapping(path = "/clases-inscriptas/{idUsuario}", method = RequestMethod.GET)
	public ModelAndView verClasesInscriptas(@PathVariable Integer idUsuario) {
		
		
	 	if (idUsuario!=0) {  
	 	
		Map<Clase, Clase> clasesMap = new HashMap<Clase, Clase>();
		ModelMap model = new ModelMap();
		Set<Clase> clases = servicioUsuario.consultarUsuarioPorId((long)idUsuario).getClases();
		List<CalificacionClase> calificaciones = servicioCalificar.consultarPorIdUsuario((long)idUsuario);

		model.addAttribute("clasesMap",clases);
		model.addAttribute("calificaciones",calificaciones);
		return new ModelAndView("historial-usuario",model);
		
	 	}else {
	   		 return new ModelAndView("redirect:/login");
	   		}
	 	
	}

}
