package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.excepciones.NoTengoClase;
import ar.edu.unlam.tallerweb1.excepciones.NoTengoUsuario;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.ClasesInscriptas;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAsistencia;
import ar.edu.unlam.tallerweb1.servicios.ServicioClase;
import ar.edu.unlam.tallerweb1.servicios.ServicioInscribirse;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorInscibirseClases {
	
	
	private ServicioUsuario servicioUsuario;
	private ServicioClase servicioClase;
	private ServicioAsistencia servicioAsistencia;
	private ServicioInscribirse servicioInscribirse;
	
	@Autowired
	public ControladorInscibirseClases(ServicioInscribirse servicioInscribirse,ServicioClase servicioClase, ServicioUsuario servicioUsuario, ServicioAsistencia servicioAsistencia) {
		this.servicioClase = servicioClase;
		this.servicioUsuario = servicioUsuario;
		this.servicioAsistencia = servicioAsistencia;
		this.servicioInscribirse = servicioInscribirse;
	}
	
	
	@RequestMapping(path = "/inscribirseclase/{id}", method = RequestMethod.GET)
	public ModelAndView inscribirseAunaClase(Usuario usuario, @PathVariable("id") Long id) {
		
		Clase buscadaAInscribirse = servicioClase.consultarClasePorId(id);
		ModelMap model = new ModelMap();
		model.put("clase", buscadaAInscribirse);
		
		return new ModelAndView("inscribirseClase", model);
		
	}
	@RequestMapping(path = "/inscribirseclase/{id}/{idUsuario}", method = RequestMethod.GET)
	public ModelAndView confirmaInscripcion(@PathVariable("idUsuario")Long idUsuario, @PathVariable("id") Long id) {
		ModelMap model = new ModelMap();

	 try {
		Clase buscadaAInscribirse = servicioClase.consultarClasePorId(id);
				
		Usuario usuarioAinscribirse = servicioUsuario.consultarUsuarioPorId(idUsuario);
		

		ClasesInscriptas clasesInscripta=servicioInscribirse.buscarInscripcion(buscadaAInscribirse,usuarioAinscribirse);
		if(clasesInscripta == null) {
			servicioInscribirse.guardarInscripcion(buscadaAInscribirse,usuarioAinscribirse);
			servicioAsistencia.cargarAsistencia(buscadaAInscribirse,usuarioAinscribirse);
		}else{
            model.put("msj","El usuario ya estaba inscripto."); 
    		return new ModelAndView("inscribirseClase", model);

		}  
		return new ModelAndView("redirect:/home", model);
	 }catch(NoTengoUsuario e) {
			return new ModelAndView("redirect:/login", model);

	 }
		
		
	}
}
