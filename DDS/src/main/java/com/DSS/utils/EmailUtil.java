package com.DSS.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
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

import com.DSS.TO.BaseTO;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class EmailUtil {

	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;

	public static void sendmail(String reportTitle, List<BaseTO> to, String descrip, String[] emails)
			throws AddressException, MessagingException, DocumentException, IOException {

		EmailUtil javaEmail = new EmailUtil();

		javaEmail.setMailServerProperties();
		javaEmail.createEmailMessage(reportTitle, to, descrip, emails);
		javaEmail.sendEmail();
	}

	public void setMailServerProperties() {

		String emailPort = "587";// gmail's/outlook smtp port

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");

	}

	public void createEmailMessage(String reportTitle, List<BaseTO> to, String descrip, String[] emails)
			throws AddressException, MessagingException, DocumentException, IOException {

		ReleaseNotePDFUtil pdf = new ReleaseNotePDFUtil(reportTitle, to, descrip);
		ByteArrayOutputStream outputStream = null;
		MimeBodyPart textBodyPart = new MimeBodyPart();
		textBodyPart.setText("Attached are the Release Notes for " + reportTitle);

		// now write the PDF content to the output stream
		outputStream = new ByteArrayOutputStream();

		pdf.createPdf(outputStream);
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
			System.out.println("Adding recipient : " + emails[i].split("@")[0].substring(0, 4) + "####@#####." + emails[i].substring(emails[i].length()-3) );
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emails[i]));
		}

	}

	public void sendEmail() throws AddressException, MessagingException {
		System.out.println("Sending Email...");
		String emailHost = "smtp.gmail.com";
		String fromUser = "mglaser001";// just the id alone without @gmail.com
		String fromUserEmailPassword = "Toadboy2562";

		Transport transport = mailSession.getTransport("smtp");

		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		System.out.println("=================Email sent successfully=================");
	}

	public void writePdf(OutputStream outputStream) throws Exception {
		Document document = new Document();
		PdfWriter.getInstance(document, outputStream);

		document.open();

		document.addTitle("Test PDF");
		document.addSubject("Testing email PDF");
		document.addKeywords("iText, email");
		document.addAuthor("Jee Vang");
		document.addCreator("Jee Vang");

		Paragraph paragraph = new Paragraph();
		paragraph.add(new Chunk("hello!"));
		document.add(paragraph);

		document.close();
	}
}