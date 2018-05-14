package varadraj.common.service;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

private JavaMailSender mailSender;
	
	public EmailService(JavaMailSender javaMailSender) {
		this.mailSender = javaMailSender ;
	}


	public void sendMail(String email, String name) throws MailException{
	
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setSubject("Welcome "+name+" :-)");
		mail.setText("Hello "+name+",\n\n"
				+"Thank you for signing up for Paridhan shop."
				+"\nWe hope you have a good shopping experience !"
				+"\n\n In case you have any suggestion or problem, please contact us on:"
				+"\n myparidhan@gmail.com"
				+"\n\nThanks & Regards,"
				+"\nParidhan Shop");
		
		mailSender.send(mail);
	}
}
