package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import ar.edu.unlam.tallerweb1.modelo.AsistenciaClase;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.DatosClase;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAsistencia;
import ar.edu.unlam.tallerweb1.servicios.ServicioCalificar;
import ar.edu.unlam.tallerweb1.servicios.ServicioClase;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;


@Controller
public class ControladorAsistencia{
	private ServicioAsistencia servicioAsistencia;
	private ServicioUsuario servicioUsuario;
	private ServicioClase servicioClase;

    private ModelAndView mav; 

	@Autowired
	public ControladorAsistencia(ServicioAsistencia servicioAsistencia,ServicioUsuario servicioUsuario,ServicioClase servicioClase) {
		this.servicioAsistencia = servicioAsistencia;
		this.servicioUsuario = servicioUsuario;
		this.servicioClase = servicioClase;
	} 
	
	
    @RequestMapping(path = "tomarPresente/{idClase}/{idUsuario}" , method = RequestMethod.GET)
	public ModelAndView tomarPresente(@PathVariable Integer idClase,@PathVariable Integer idUsuario) {
        ModelMap model = new ModelMap(); 
		 
   	 	if (idUsuario!=0) { 
   		Clase clase = servicioClase.consultarClasePorId((long)idClase);
   		List<AsistenciaClase> asistencia = servicioAsistencia.consultarAsistenciaPorClase(clase);
  
		model.addAttribute("asistencia",asistencia);

        return new ModelAndView("tomar-presente",model); 
        
   		}else {
      		 return new ModelAndView("redirect:/login");
      	}
   	 	
	 
    }
    
    
    
    @RequestMapping(path = "guardarAsistencia/{idClase}/{idUsuario}" , method = RequestMethod.GET)
	public ModelAndView guardarAsistencia(@PathVariable Integer idClase,@PathVariable Integer idUsuario) {
        ModelMap model = new ModelMap();
        
		Clase clase = servicioClase.consultarClasePorId((long)idClase);
		Usuario usuario = servicioUsuario.consultarUsuarioPorId((long)idUsuario); 
    	servicioAsistencia.actualizarAsistencia(clase,usuario); 
    	return new ModelAndView("redirect:/tomarPresente/"+idClase+"/"+idUsuario);
      	 
   	 	
	 
    }
    
    
	
   
    

    
    
}