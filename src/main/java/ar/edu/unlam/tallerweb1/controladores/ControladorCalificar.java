package ar.edu.unlam.tallerweb1.controladores;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.excepciones.UsuarioNoEstaPresenteEnLaClase;
import ar.edu.unlam.tallerweb1.modelo.AsistenciaClase;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAsistencia;
import ar.edu.unlam.tallerweb1.servicios.ServicioCalificar;
import ar.edu.unlam.tallerweb1.servicios.ServicioClase;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;


@Controller
public class ControladorCalificar {
	private ServicioCalificar servicioCalificar;
	private ServicioAsistencia servicioAsistencia;
	private ServicioClase servicioClase;
	private ServicioUsuario servicioUsuario;
 

	@Autowired
	public ControladorCalificar(ServicioUsuario servicioUsuario,ServicioClase servicioClase,ServicioCalificar servicioCalificar,ServicioAsistencia servicioAsistencia) {
		this.servicioCalificar = servicioCalificar;
		this.servicioAsistencia = servicioAsistencia;
		this.servicioUsuario = servicioUsuario;
		this.servicioClase = servicioClase;
	} 
	
	
    @RequestMapping(path = "estrellas" , method = RequestMethod.GET)
	public ModelAndView estrellas() {
        ModelMap model = new ModelMap();
        return new ModelAndView("estrellas",model); 
	 
    }
    
    
	
    @RequestMapping(path = "/agregarCalificacion/{idClase}/{idUsuario}/{calificacion}" , method = RequestMethod.GET)
	public ModelAndView agregarCalificacion(@PathVariable Integer idClase,@PathVariable Integer idUsuario,@PathVariable Integer calificacion) {
		ModelMap model = new ModelMap(); 
		 
		try {		
		   	 	if (idUsuario!=0) { 
		   	 	
				Clase clase = servicioClase.consultarClasePorId((long)idClase);
				Usuario usuario = servicioUsuario.consultarUsuarioPorId((long)idUsuario); 
				Integer alumnoPresente = 1;
		   	 	AsistenciaClase tieneAsistencia= servicioAsistencia.consultarAsistenciaPorClaseYusuario(clase,usuario,alumnoPresente);
		   
		   	 	servicioCalificar.agregarCalificacion((long)idUsuario,(long)idClase,calificacion); 
		   	  
		   	 	return new ModelAndView("redirect:/clases-inscriptas/"+idUsuario+"");
		   		}else {
		   		 return new ModelAndView("redirect:/login");
		   		}
		}catch(UsuarioNoEstaPresenteEnLaClase e) {
			model.addAttribute("msj","El usuario no participo de la clase, por tal motivo no podra calificarla.");
			return new ModelAndView("notificacion",model); 
		}

 }
    
    
    
}