package br.com.ebix.monitoramento.repository;

import org.springframework.mail.SimpleMailMessage;

import br.com.ebix.monitoramento.model.Evento;

public interface EmailService {
	
	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationEmail(Evento obj);
	
	
}
