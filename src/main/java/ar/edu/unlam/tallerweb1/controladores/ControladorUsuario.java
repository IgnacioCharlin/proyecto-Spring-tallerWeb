package ar.edu.unlam.tallerweb1.controladores;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorUsuario {

	
private ServicioUsuario servicioUsuario;
	

	@Autowired
	public ControladorUsuario(ServicioUsuario servicioLogin){
		this.servicioUsuario = servicioLogin;
		
	}
	
	
	@RequestMapping(path = "/clases-inscriptas", method = RequestMethod.GET)
	public ModelAndView verClasesInscriptas() {
		Map<Clase, Clase> clasesMap = new HashMap<Clase, Clase>();
		ModelMap model = new ModelMap();
		Set<Clase> clases = servicioUsuario.consultarUsuarioPorId((long)1).getClases();
		
		model.addAttribute("clasesMap",clases);
		return new ModelAndView("historial-usuario",model);
	}

}
