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
import ar.edu.unlam.tallerweb1.excepciones.FechaYaPaso;
import ar.edu.unlam.tallerweb1.excepciones.NoEsProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoUnaFecha;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.DatosClase;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCalificar;
import ar.edu.unlam.tallerweb1.servicios.ServicioClase;


@Controller
public class ControladorCalificar {
	private ServicioCalificar servicioCalificar;
    private ModelAndView mav; 

	@Autowired
	public ControladorCalificar(ServicioCalificar servicioCalificar) {
		this.servicioCalificar = servicioCalificar;
	} 
	
	
    @RequestMapping(path = "estrellas" , method = RequestMethod.GET)
	public ModelAndView estrellas() {
        ModelMap model = new ModelMap();
        return new ModelAndView("estrellas",model); 
	 
    }
    
    
	
    @RequestMapping(path = "/agregarCalificacion/{idClase}/{idUsuario}/{calificacion}" , method = RequestMethod.GET)
	public ModelAndView agregarCalificacion(@PathVariable Integer idClase,@PathVariable Integer idUsuario,@PathVariable Integer calificacion) {
		ModelMap model = new ModelMap(); 
		 
		   	 	if (idUsuario!=0) { 
		   	 	servicioCalificar.agregarCalificacion((long)idUsuario,(long)idClase,calificacion); 
		   	 	return new ModelAndView("redirect:/clases-inscriptas");
		   		}else {
		   		 return new ModelAndView("redirect:/login");
		   		}

 }
    
    
    
}