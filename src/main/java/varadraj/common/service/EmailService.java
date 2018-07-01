package varadraj.common.service;

import java.util.List;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import varadraj.common.model.address.Address;
import varadraj.order.model.Orders;


@Service
public class EmailService {

private JavaMailSender mailSender;
	
	public EmailService(JavaMailSender javaMailSender) {
		this.mailSender = javaMailSender ;
	}
	
	private void sendMail(String receiverEmail, String subject, String text) throws MailException{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(receiverEmail);
		mail.setSubject(subject);
		mail.setText(text);
		mailSender.send(mail);
	}


	public void sendWelcomeMail(String receiverEmail, String name) throws MailException{
	
		String subject = "Welcome "+name+" :-)";
		
		String text = "Hi "+name+",\n\n"
				+"Thank you for signing up for G&G shopping app."
				+"\nWe hope you have a good shopping experience !"
				+"\n\n In case you have any suggestion or problem, please contact us on:"
				+"\n myparidhan@gmail.com"
				+"\n\nThanks & Regards,"
				+"\nG&G Shop";
		
		this.sendMail(receiverEmail, subject, text);
	}
	
	public void sendOrderPlacedMail(String receiverEmail, String name, List<Orders> orders) throws MailException{
		
		String subject = "G&G: "+orders.size()+" Order Placed";
		
		String text = "Hi "+name+",\n\n"
				+"Your orders have been placed successfully.\n"
				+"\n"+this.orderDetails(orders)+"\n"
				+"\nTotal: Rs."+this.orderTotal(orders)+"\n"
				+"\nDelivery Address:"+this.getDeliveryAddress(orders.get(0).getDeliveryAddress())+"\n"
				+"\n\nThanks & Regards,"
				+"\n  G&G Shop";
		
		this.sendMail(receiverEmail, subject, text);
		
	}

	private String getDeliveryAddress(Address deliveryAddress) {
		return  "\n\t"+deliveryAddress.getHouseNumber()+
				"\n\t"+deliveryAddress.getArea()+
				"\n\t"+deliveryAddress.getLandmark()+
				"\n\t"+deliveryAddress.getCity().getCityName()+" ,"
				+deliveryAddress.getCity().getState().getStateName()+
				"\n\t"+deliveryAddress.getPincode();
	}

	private String orderTotal(List<Orders> orders) {
		double total = 0.0;
		for(Orders order: orders) {
			total += order.getAmount();
		}
		return ""+total;
	}

	private String orderDetails(List<Orders> orders) {
		String output = "";
		for(Orders order: orders) {
			output = output + order.getItem().getName()
			+" x"+order.getQuantity()
			+" @"+order.getAmount()
			+"\n";
		}
		return output;
	}
}
