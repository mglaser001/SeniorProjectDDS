package com.DSS.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.DocumentException;

public class EmailUtil {

	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;

	public static void sendmail(String reportTitle, ByteArrayOutputStream outputStream, String[] emails,
			String password) throws AddressException, MessagingException, DocumentException, IOException {

		EmailUtil javaEmail = new EmailUtil();

		javaEmail.setMailServerProperties();
		javaEmail.createEmailMessage(reportTitle, outputStream, emails);
		javaEmail.sendEmail(password);
	}

	public void setMailServerProperties() {

		String emailPort = "587";// gmail's/outlook smtp port

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");

	}

	public void createEmailMessage(String reportTitle, ByteArrayOutputStream outputStream, String[] emails)
			throws AddressException, MessagingException, DocumentException, IOException {

		MimeBodyPart textBodyPart = new MimeBodyPart();
		textBodyPart.setText("Attached are the Release Notes for " + reportTitle);

		byte[] bytes = outputStream.toByteArray();

		// construct the pdf body part
		DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
		MimeBodyPart pdfBodyPart = new MimeBodyPart();
		pdfBodyPart.setDataHandler(new DataHandler(dataSource));
		pdfBodyPart.setHeader("Content-ID", "pdf_id");
		pdfBodyPart.setFileName(reportTitle.replace(" ", "") + "_ReleaseNotes.pdf");

		// construct the mime multi part
		MimeMultipart mimeMultipart = new MimeMultipart();
		mimeMultipart.addBodyPart(textBodyPart);
		mimeMultipart.addBodyPart(pdfBodyPart);

		mailSession = Session.getDefaultInstance(emailProperties, null);

		emailMessage = new MimeMessage(mailSession);
		emailMessage.setSubject("Release Notes: " + reportTitle);
		emailMessage.setContent(mimeMultipart);

		for (int i = 0; i < emails.length; i++) {
			System.out.println("Adding recipient : " + emails[i].split("@")[0].substring(0, 4) + "####@#####."
					+ emails[i].substring(emails[i].length() - 3));
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emails[i]));
		}

	}

	public void sendEmail(String password) throws AddressException, MessagingException {
		System.out.println("Sending Email...");
		String emailHost = "smtp.gmail.com";
		String fromUser = "mglaser001";// just the id alone without @gmail.com
		String fromUserEmailPassword = password;

		Transport transport = mailSession.getTransport("smtp");

		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		System.out.println("=================Email sent successfully=================");
	}

}