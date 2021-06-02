package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.DatosClase;
import ar.edu.unlam.tallerweb1.modelo.DatosRegistro;
import ar.edu.unlam.tallerweb1.servicios.ServicioClase;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorRegistro {
	private ServicioUsuario servicioUsuario;
	
	@Autowired
	public ControladorRegistro(ServicioUsuario servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}
	
	@RequestMapping(path="/registro", method = RequestMethod.GET)
    public ModelAndView irRegistro(){
        ModelMap model = new ModelMap();
        model.put("datosRegistro", new DatosRegistro());
        return new ModelAndView("registro", model);
    }
    
    @RequestMapping(path="/registro", method = RequestMethod.POST)
    public ModelAndView registroExitoso(@ModelAttribute("modificar") DatosRegistro datos){
        ModelMap model = new ModelMap();
        servicioUsuario.registrarUsuario(datos);
    	return new ModelAndView("redirect:/login", model);
    }	
}
