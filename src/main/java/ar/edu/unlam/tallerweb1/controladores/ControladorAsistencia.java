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

import ar.edu.unlam.tallerweb1.excepciones.AlumnoNoPerteneceAlaClase;
import ar.edu.unlam.tallerweb1.excepciones.ClaseInvalida;
import ar.edu.unlam.tallerweb1.excepciones.FaltaCupo;
import ar.edu.unlam.tallerweb1.excepciones.FechaYaPaso;
import ar.edu.unlam.tallerweb1.excepciones.NoEsProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoUnaFecha;
import ar.edu.unlam.tallerweb1.excepciones.NoTengoClase;
import ar.edu.unlam.tallerweb1.excepciones.NoTengoUsuario;
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
		 
        try {
		   	 	if (idUsuario!=0) { 
		   		Clase clase = servicioClase.buscarClaseId((long)idClase);
		   		List<AsistenciaClase> asistencia = servicioAsistencia.consultarAsistenciaPorClase(clase);
		  	
		   		
		   			if(asistencia.size()==0) { 
		   	            model.put("msj","Esta clase no contiene alumnos.");         
		   			}else {  
		   				model.addAttribute("asistencia",asistencia);
		   				return new ModelAndView("tomar-presente",model); 
		   			}
		   		}else {
		      		 return new ModelAndView("redirect:/login");
		      	}
        }
        catch(ClaseInvalida e){
            model.put("msj","Debe ingresar una clase valida.");         

        	
        } 
  
		return new ModelAndView("tomar-presente",model); 

	 
    }
    
    /*
     * 		if(clases.size()>0) {
		return clases;
		}else {
		throw new ClaseSinAlumnos();  	
		}
		
		*/
    
    @RequestMapping(path = "guardarAsistencia/{idClase}/{idUsuario}" , method = RequestMethod.GET)
	public ModelAndView guardarAsistencia(@PathVariable Integer idClase,@PathVariable Integer idUsuario) {
        ModelMap model = new ModelMap();
        try {
		Clase clase = servicioClase.consultarClasePorId((long)idClase);
		Usuario usuario = servicioUsuario.consultarUsuarioPorId((long)idUsuario); 
    	servicioAsistencia.actualizarAsistencia(clase,usuario); 
    	return new ModelAndView("redirect:/tomarPresente/"+idClase+"/"+idUsuario);
        }
        catch(NoTengoUsuario e){
             model.put("msj","Usuario invalido.");         
        } 
        catch(NoTengoClase e){
            model.put("msj","Clase invalida.");         
       } 
        catch(AlumnoNoPerteneceAlaClase e){
            model.put("msj","El alumno no pertence a esta clase.");         
       } 
 		return new ModelAndView("tomar-presente",model); 

   	 	
	 
    }
    
    
	
   
    

    
    
}