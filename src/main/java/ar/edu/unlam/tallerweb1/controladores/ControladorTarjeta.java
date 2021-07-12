package ar.edu.unlam.tallerweb1.controladores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.excepciones.PagoPendiente;
import ar.edu.unlam.tallerweb1.excepciones.PagoRechazadoVuelvaAintentar;
import ar.edu.unlam.tallerweb1.excepciones.noCuentoConTarjetasDisponibles;
import ar.edu.unlam.tallerweb1.modelo.Tarjeta;
import ar.edu.unlam.tallerweb1.modelo.TarjetasCompradas;
import ar.edu.unlam.tallerweb1.modelo.UsuariosFichas;
import ar.edu.unlam.tallerweb1.servicios.ServicioAsistencia;
import ar.edu.unlam.tallerweb1.servicios.ServicioClase;
import ar.edu.unlam.tallerweb1.servicios.ServicioTarjeta;
import ar.edu.unlam.tallerweb1.servicios.ServicioTarjetasCompradas;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarioFichas;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;



@Controller
public class ControladorTarjeta{
	private String setAccessToken="TEST-8319622126923125-063015-33cbd568c93efad3b4e5dbdfa6bab81c-783586377";
	
	private ServicioAsistencia servicioAsistencia;
	private ServicioUsuario servicioUsuario;
	private ServicioClase servicioClase;
	private ServicioTarjeta servicioTarjeta;
	private ServicioTarjetasCompradas servicioTarjetasCompradas;
	private ServicioUsuarioFichas servicioUsuarioFichas;
	
    private ModelAndView mav;
	private List<Preference> listapreference; 

	@Autowired
	public ControladorTarjeta(ServicioTarjeta servicioTarjeta,ServicioTarjetasCompradas servicioTarjetasCompradas,ServicioUsuarioFichas servicioUsuarioFichas) {
		this.servicioTarjeta = servicioTarjeta; 
		this.servicioTarjetasCompradas = servicioTarjetasCompradas; 
		this.servicioUsuarioFichas = servicioUsuarioFichas; 
	} 
	
	
    @RequestMapping(path = "comprarTarjeta/{idUsuario}" , method = RequestMethod.GET)
	public ModelAndView comprarTarjeta(@PathVariable Integer idUsuario, HttpServletRequest request,@RequestParam(required = false)  String msj) throws MPException{
        ModelMap model = new ModelMap();  
        MercadoPago.SDK.setAccessToken(setAccessToken);

        try {
		if (idUsuario!=0) { 
   		List<Tarjeta> tarjetasDisponible = servicioTarjeta.dameTarjetasDisponibles();
   		model.put("tarjetasDisponible",tarjetasDisponible); 
        model.put("msj","");       
   	 	for (Tarjeta p:tarjetasDisponible) {
	    Preference preference = new Preference();
   		Item item = new Item();
        item.setTitle(p.getNombre())
            .setQuantity(1)
            .setUnitPrice((float) p.getPrecio());
        preference.appendItem(item);
         

       BackUrls backUrls = new BackUrls(
               "http://localhost:8080/EnerGym/respuestaMercadoPago/"+p.getId()+"/"+idUsuario+"/1/",
               "http://localhost:8080/EnerGym/respuestaMercadoPago/"+p.getId()+"/"+idUsuario+"/2/",
               "http://localhost:8080/EnerGym/respuestaMercadoPago/"+p.getId()+"/"+idUsuario+"/3/");

       	preference.setBackUrls(backUrls);
       	preference.save();
      
		request.getSession().setAttribute("preference"+p.getId(), preference.getId());			
   	 	}
   	 	
        if(msj!=null) {
            model.put("msj",msj);     
        }
        
		   		 
		   		}else {
		      		 return new ModelAndView("redirect:/login");
		      	}
        }
        catch(noCuentoConTarjetasDisponibles e){
            model.put("msj","No contamos con tarjetas para la venta.");         
			model.addAttribute("tarjetasDisponible",""); 
        	
        } 
  
		return new ModelAndView("comprar-tarjetas",model); 

	 
    }
    
    
    @RequestMapping(path = "respuestaMercadoPago/{idTarjeta}/{idUsuario}/{estado}/" , method = RequestMethod.GET)
	public ModelAndView respuestaMercadoPago(@PathVariable Integer idTarjeta,@PathVariable Integer idUsuario,@PathVariable Integer estado ,@RequestParam (required = false) String collection_status,@RequestParam (required = false)  String collection_id)  {
    	ModelMap model = new ModelMap();  
        try {
		   	 	if (idUsuario!=0) { 
		   	 	TarjetasCompradas tarjetaComprada= servicioTarjetasCompradas.procesoPago(idTarjeta,idUsuario,estado,collection_id);
		   	 	servicioTarjetasCompradas.actualizoFichas(tarjetaComprada,idUsuario,estado);
		   	 	
 		   		UsuariosFichas fichasActuales = servicioUsuarioFichas.buscarFichasPorUsuario((long)idUsuario); 
   	            model.put("tarjetaComprada",tarjetaComprada);         
		   	    model.put("msj","El pago se efectuo correctamente. La cantidad actual de sus creditos es " + fichasActuales.getCantidad());         
		   		 
		   		}else {
		      		 return new ModelAndView("redirect:/login");
		      	}
        }
        catch(PagoRechazadoVuelvaAintentar e){
        	
            model.put("msj","No se pudo procesar el cobro, vuelva a intentarlo");         
        	
        } catch(PagoPendiente e){
        	
        	model.put("msj","El pago se encuentra pendiente hasta que se abone mediante efectivo o la entidad bancaria habilite el pago.");                 	
        }
  
		return new ModelAndView("redirect:/comprarTarjeta/"+idUsuario, model);
	 
    }
    
    
    @RequestMapping(path = "verMisCompras/{idUsuario}" , method = RequestMethod.GET)
	public ModelAndView verMisCompras(@PathVariable Integer idUsuario) {
        ModelMap model = new ModelMap(); 

        try {
		   	 	if (idUsuario!=0) { 
		   	 	List<TarjetasCompradas> comprasRealizadas= servicioTarjetasCompradas.buscarComprasPorUsuario(idUsuario);         
 		   		UsuariosFichas fichasActuales = servicioUsuarioFichas.buscarFichasPorUsuario((long)idUsuario); 
 		   	    model.put("msj","La cantidad actual de sus creditos es " + fichasActuales.getCantidad());         
		   		    
	            model.put("comprasRealizadas",comprasRealizadas);

		   		}else {
		      		 return new ModelAndView("redirect:/login");
		      	}
        }
        catch(PagoRechazadoVuelvaAintentar e){
            model.put("msj","No se pudo procesar el cobro, vuelva a intentarlo.");         
        	
        } 
  
		return new ModelAndView("ver-mis-compras",model); 

	 
    }
    
 
    
    

    @RequestMapping(path = "informacionDelPago/{idUsuario}/{id}" , method = RequestMethod.GET)
	public ModelAndView informacionDelPago(@PathVariable Integer idUsuario,@PathVariable Integer id, HttpServletRequest request) throws MPException, IOException{
        ModelMap model = new ModelMap();  
        MercadoPago.SDK.setAccessToken(setAccessToken);

        try {
		if (idUsuario!=0) { 
			String urlMP = "https://api.mercadopago.com/v1/payments/search?id="+id+"&access_token="+setAccessToken;
		   
		      URL url = new URL(urlMP);
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
		        conn.setRequestProperty("Accept", "application/json");
		        if (conn.getResponseCode() != 200) 
		        {
		            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		        }
		        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		        StringBuilder sb = new StringBuilder();
		        int cp;
		        while ((cp = br.read()) != -1)
		        {
		          sb.append((char) cp);
		        }
		        String output = sb.toString();
		        JsonObject json = new Gson().fromJson(output,JsonObject.class);
 
		 
		        
	         model.put("json",json);  
 
		    
		   		}else {
		      		 return new ModelAndView("redirect:/login");
		      	}
        }
        catch(Exception e) {
            model.put("msj","");    
        System.out.println(e.getMessage());
    
         	
        } 
  
		return new ModelAndView("notificacion",model); 

	 
    }
    
    
    
}