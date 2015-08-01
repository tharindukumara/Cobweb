package com.cobweb.io.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServlet;

import com.cobweb.io.meta.Email;

public class SendMail extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4012826357716915515L;
	
	private static final String SMTP_HOST_NAME = "smtp.sendgrid.net";
	private static final String SMTP_AUTH_USER = "YasithLokuge";
	private static final String SMTP_AUTH_PWD = "Y@51th19910507";
	private static final int SMTP_PORT = 2525;

	public void send(Email email) throws Exception {
		
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");

		Authenticator auth = new SMTPAuthenticator();
		Session mailSession = Session.getDefaultInstance(props, auth);
		Transport transport = mailSession.getTransport();

		MimeMessage message = new MimeMessage(mailSession);

		Multipart multipart = new MimeMultipart("alternative");

		// Sets up the contents of the email message
		BodyPart part1 = new MimeBodyPart();
		part1.setText(email.getBody());

		multipart.addBodyPart(part1);

		message.setContent(multipart);
		message.setFrom(new InternetAddress(email.getFrom(),"Cobweb IO"));
		message.setSubject(email.getSubject());
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getTo()));

		// Sends the email
		transport.connect(SMTP_HOST_NAME, SMTP_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);
		transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		transport.close();
	}

	// Authenticates to SendGrid
	private class SMTPAuthenticator extends javax.mail.Authenticator {
		@Override
		public PasswordAuthentication getPasswordAuthentication() {
			String username = SMTP_AUTH_USER;
			String password = SMTP_AUTH_PWD;
			return new PasswordAuthentication(username, password);
		}
	}
}