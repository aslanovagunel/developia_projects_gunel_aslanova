package spring.library_gunel_aslanova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	 @Autowired
	 private JavaMailSender mailSender;

	 public void sendOverdueEmail(String toEmail, String studentName, String bookTitle) {

			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(toEmail);
			message.setSubject("Kitab Gecikməsi Bildirişi");
			message.setText("Hörmətli " + studentName + ",\n\n" + "Siz \"" + bookTitle
					+ "\" adlı kitabı təyin olunmuş müddətdə qaytarmamısınız. "
					+ "Zəhmət olmasa, ən qısa zamanda kitabı geri qaytarın.\n\nTəşəkkürlər.");
			mailSender.send(message);
		}
}
