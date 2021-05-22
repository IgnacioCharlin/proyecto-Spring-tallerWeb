package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.DatosRegistro;

@Controller
public class ControladorRegistrarme {
	@RequestMapping(path="/registro", method = RequestMethod.GET)
    public ModelAndView saludar(){
        ModelMap model = new ModelMap();
        model.put("datosRegistro", new DatosRegistro());
        return new ModelAndView("registro", model);
    }
    
    @RequestMapping(path="/registro", method = RequestMethod.POST)
    public ModelAndView mostrarDatos(DatosRegistro datos , ModelMap model){
        model.addAttribute("email",datos.getEmail());
        model.addAttribute("password",datos.getPassword());
        return new ModelAndView("registro", model);
    }

}
