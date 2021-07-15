package ar.edu.unlam.tallerweb1.servicios;


import java.time.LocalDate; 
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.excepciones.AlumnoNoPerteneceAlaClase;
import ar.edu.unlam.tallerweb1.excepciones.ClaseInvalida;
import ar.edu.unlam.tallerweb1.excepciones.FaltaCupo;
import ar.edu.unlam.tallerweb1.excepciones.FechaYaPaso;
import ar.edu.unlam.tallerweb1.excepciones.NoEsProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoProfesor;
import ar.edu.unlam.tallerweb1.excepciones.NoSeCargoUnaFecha;
import ar.edu.unlam.tallerweb1.excepciones.NoTengoClase;
import ar.edu.unlam.tallerweb1.excepciones.NoTengoUsuario;
import ar.edu.unlam.tallerweb1.excepciones.UsuarioNoEstaPresenteEnLaClase;
import ar.edu.unlam.tallerweb1.excepciones.UsuarioNoExiste;
import ar.edu.unlam.tallerweb1.excepciones.UsuarioYaEstabaInscrito;
import ar.edu.unlam.tallerweb1.modelo.AsistenciaClase;
import ar.edu.unlam.tallerweb1.modelo.CalificacionClase;
import ar.edu.unlam.tallerweb1.modelo.Clase;
import ar.edu.unlam.tallerweb1.modelo.DatosClase;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAsistencia;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCalificar;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClase;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProfesor;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service("servicioEmail")
@Transactional
public class ServicioEmailImpl implements ServicioEmail{
	private RepositorioAsistencia repositorioAsistencia;
	private RepositorioClase repositorioClase;
	private RepositorioUsuario repositorioUsuario;
	
	@Autowired
	public ServicioEmailImpl(RepositorioAsistencia repositorioAsistencia, RepositorioUsuario repositorioUsuario,RepositorioClase repositorioClase) {
		this.repositorioAsistencia = repositorioAsistencia;
		this.repositorioClase = repositorioClase;
		this.repositorioUsuario = repositorioUsuario;
	}

	@Override
	public void envierEmail(String asunto, String para, String mensaje) {



    	final String username = "systemenergym@gmail.com";
        final String password = "okjrszxdvlfbaodu";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(""+para+",mauro.julian.ayala@gmail.com")
            );
            message.setSubject(asunto);
            message.setText(mensaje);

            Transport.send(message);

            System.out.println("Done");
         } catch (MessagingException e) {
            e.printStackTrace();
         }
        
        
	}

	 
	
	
}