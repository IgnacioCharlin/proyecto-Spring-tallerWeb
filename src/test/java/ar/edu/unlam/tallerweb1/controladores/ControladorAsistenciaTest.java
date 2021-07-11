package ar.edu.unlam.tallerweb1.controladores;

 
import ar.edu.unlam.tallerweb1.excepciones.ClaseInvalida;
import ar.edu.unlam.tallerweb1.modelo.AsistenciaClase;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.servicios.ServicioAsistencia;
import ar.edu.unlam.tallerweb1.servicios.ServicioClase;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;


import org.junit.Before;
import org.junit.Test;


import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

//import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

 

public class ControladorAsistenciaTest  {
	
	 
    private ControladorAsistencia controladorAsitencia;  
    private ModelAndView mav; 
 	private ServicioAsistencia servicioAsistencia;
 	private ServicioUsuario servicioUsuario;
 	private ServicioClase servicioClase;
    
      
   @Before
   public void init() {
	   servicioAsistencia = mock(ServicioAsistencia.class);
	   servicioUsuario = mock(ServicioUsuario.class);
	   servicioClase= mock(ServicioClase.class);
	   controladorAsitencia = new ControladorAsistencia(servicioAsistencia,servicioUsuario,servicioClase);
 
   }

     
   @Test
    public void siNoTengoIdClaseNoPuedoDarPresente(){
   	      	
   Integer idUsuario= 1;  
   Integer idClase= 0;  
   
	doThrow(ClaseInvalida.class)
	.when(servicioClase)
	.buscarClaseId((long)idClase);
	
   	whenBuscoClaseParaDarPresente(idClase,idUsuario);
   	thanClaseInvalida();
   }

   
   private void whenBuscoClaseParaDarPresente(Integer idClase,Integer idUsuario) {
		mav=controladorAsitencia.tomarPresente(idClase,idUsuario);		

	}
   
   

   private void thanClaseInvalida() {
		assertThat(mav.getModel().get("msj")).isEqualTo("Debe ingresar una clase valida."); 		

		
}
 

@Test
public void noTengoAlumnosEnLaClase(){
	      	
 	Clase clase= new Clase() ; 
 	clase.setId((long)1);

    Integer idUsuario= 1;  
    List<AsistenciaClase> listaVacia= null;
	when(servicioAsistencia.consultarAsistenciaPorClase(clase)).thenReturn(listaVacia);
   	whenBuscoClaseParaDarPresente(1,idUsuario);
   	thanClaseSinAlumnos();
}


private void thanClaseSinAlumnos() {
		assertThat(mav.getModel().get("msj")).isEqualTo("Esta clase no contiene alumnos."); 		
		
}
 
	


}
