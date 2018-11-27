package com.DDS.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.DDS.TO.BaseTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ReleaseNotePDFUtil {

	List<BaseTO> dataList = new ArrayList<BaseTO>();
	String reportTitle = "";
	File file;
	public static final String RESULT
    = "_ReleaseNotes.pdf";
	
	public ReleaseNotePDFUtil(String title, List<BaseTO> baseto) throws DocumentException, IOException {
		dataList = baseto;
		reportTitle = title;
		System.out.println(reportTitle);
		createPdf(title.replace(" ", "_")+ RESULT);
	}

	public void createPdf(String filename) throws DocumentException, IOException {

		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(filename));

		document.open();
		document.add(new Paragraph("Hello World!"));

		document.close();
	}

}
