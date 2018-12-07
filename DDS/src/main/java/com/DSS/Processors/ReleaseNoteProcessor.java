package com.DSS.Processors;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.DSS.DAO.GitlabDAO;
import com.DSS.TO.BaseTO;
import com.DSS.TO.Group;
import com.DSS.TO.Issue;
import com.DSS.TO.Notes;
import com.DSS.utils.EmailUtil;
import com.DSS.utils.ReleaseNotePDFUtil;
import com.itextpdf.text.DocumentException;

public class ReleaseNoteProcessor {

	List<Issue> issues = new ArrayList<Issue>();
	List<BaseTO> dataList = new ArrayList<BaseTO>();
	private String reportTitle = System.getProperty("ReportTitle");
	private String emailList = System.getProperty("email");;
	private String description = System.getProperty("description");;
	private String[] toEmails;
	
	public void initialize() {
		
		toEmails = emailList.split(",");

 		String projectid = System.getProperty("ProjectId");
		issues = GitlabDAO.getIssuesFromProjectid(projectid);
		for (Issue i : issues) {
			BaseTO sheetData = new BaseTO();
			List<Notes> notes = GitlabDAO.getNotes(projectid, i.getIid().toString());
			int counter = 0;
			for (Notes n : notes) {
				if (n.getBody().contains("#requestor")) {
					counter++;
					sheetData.addBaseMap("requestor", n.getBody().replaceAll("#requestor", ""));
				} else if (n.getBody().contains("#releasenotes")) {
					counter++;
					sheetData.addBaseMap("description",
							"Description: " + i.getDescription() + "\n" + n.getBody().replaceAll("#releasenotes", ""));
				}

			}
			if (counter > 1) {
				sheetData.addBaseMap("url", i.getWebUrl());
				sheetData.setTitle(i.getTitle());
				dataList.add(sheetData);
			}
		}
	}

	public void process() throws DocumentException, IOException, MessagingException {
		
//		ReleaseNotePDFUtil pdfUtil = new ReleaseNotePDFUtil(reportTitle, dataList, description, toEmails);

		EmailUtil.sendmail(reportTitle, dataList, description, toEmails);
	}
}
