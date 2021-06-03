package ar.edu.unlam.tallerweb1.controladores;

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

import ar.edu.unlam.tallerweb1.excepciones.ClavesNoCoinciden;
import ar.edu.unlam.tallerweb1.excepciones.FaltaCupo;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoUnaFecha;
import ar.edu.unlam.tallerweb1.excepciones.UsuarioExistente;
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
	
	@RequestMapping("/registro")
    public ModelAndView irRegistro(){
        ModelMap model = new ModelMap();
        DatosRegistro datos = new DatosRegistro();
        model.put("datosRegistro", datos);
        return new ModelAndView("registro", model);
    }
    
    @RequestMapping(path="/validar-registro", method = RequestMethod.POST)
    public ModelAndView registroExitoso(@ModelAttribute("modificar") DatosRegistro datos, HttpServletRequest request){
        ModelMap model = new ModelMap();
        try {        	
        	servicioUsuario.registrarUsuario(datos);
        	return new ModelAndView("redirect:/login");
        }catch (ClavesNoCoinciden e) {
			model.put("error", "Claves no coinciden");
			model.put("datosRegistro", new DatosRegistro());
        }catch (UsuarioExistente e) {
        	model.put("error","Usuario existente");
        	model.put("datosRegistro", new DatosRegistro());
        }
        return new ModelAndView("registro", model);
    }	
   
}
