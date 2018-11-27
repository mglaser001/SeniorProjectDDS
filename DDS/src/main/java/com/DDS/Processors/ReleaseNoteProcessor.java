package com.DDS.Processors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.DDS.DAO.GitlabDAO;
import com.DDS.TO.BaseTO;
import com.DDS.TO.Group;
import com.DDS.TO.Issue;
import com.DDS.TO.Notes;
import com.DDS.utils.ReleaseNotePDFUtil;
import com.itextpdf.text.DocumentException;

public class ReleaseNoteProcessor extends BaseProcessor {

	List<Issue> issues = new ArrayList<Issue>();
	List<BaseTO> dataList = new ArrayList<BaseTO>();
	private String reportTitle;

	public void initialize() {
		
		List<String> issueIid = new ArrayList<String>();
		String projectid = System.getProperty("ProjectId");
		reportTitle = System.getProperty("ReportTitle");
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
					sheetData.addBaseMap("description", "Description: "+ i.getDescription() +"\n" +n.getBody().replaceAll("#releasenotes", ""));
				}

			}
			if (counter > 1) {
				sheetData.setTitle(i.getTitle());
				dataList.add(sheetData);
			}
		}
	}

	public void process() throws DocumentException, IOException {
		/*for (BaseTO to : dataList) {
			System.out.println(to.getTitle());
		}*/
		ReleaseNotePDFUtil pdfUtil = new ReleaseNotePDFUtil(reportTitle, dataList);
	}
}
