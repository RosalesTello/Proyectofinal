package com.example.inventario.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	 @Autowired
	    private JavaMailSender mailSender;

	    public void enviarCorreo(String destinatario, String asunto, String cuerpo) {
	        try {
	            SimpleMailMessage mensaje = new SimpleMailMessage();
	            mensaje.setTo(destinatario);
	            mensaje.setSubject(asunto);
	            mensaje.setText(cuerpo);
	            mensaje.setFrom("rosalesjhona9@gmail.com"); // Usar el email configurado en properties
	            
	            mailSender.send(mensaje);
	            System.out.println("Email enviado exitosamente");
	        } catch (Exception e) {
	            System.out.println("Error al enviar email: " + e.getMessage());
	            throw e; // Re-lanzar la excepción para manejarla en el controlador
	        }
	    }

}
