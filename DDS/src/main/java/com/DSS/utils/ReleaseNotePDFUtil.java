package com.DDS.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.DDS.TO.BaseTO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class ReleaseNotePDFUtil {

	List<BaseTO> dataList = new ArrayList<BaseTO>();
	String reportTitle = "";
	String description = "";
	static String RESULT = "_ReleaseNotes.pdf";
	static String imageUrl = "https://www.mercuryinsurance.com/static/images/logo.png";

	public ReleaseNotePDFUtil(String title, List<BaseTO> baseto, String description)
			throws DocumentException, IOException {
		this.description = description;

		dataList = baseto;
		reportTitle = title;
		System.out.println("Initializing PDFUtil...");
	}

	class MyFooter extends PdfPageEventHelper {
		Font ffont = new Font(Font.FontFamily.UNDEFINED, 12);

		public void onEndPage(PdfWriter writer, Document document) {
			PdfContentByte cb = writer.getDirectContent();
			Phrase footer = new Phrase("Mercury Insurance - Confidential", ffont);

			ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer,
					(document.right() - document.left()) / 2 + document.leftMargin(), document.bottom(), 0);
		}
	}

	public void createPdf(OutputStream outputStream) throws DocumentException, IOException {
		System.out.println("Creating PDF...");
		Document document = new Document(PageSize.A4_LANDSCAPE.rotate(), 5, 5, 5, 5);
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);
		MyFooter event = new MyFooter();

		writer.setPageEvent(event);

		document.open();
		document.setMargins(0.5f, .5f, .5f, .5f);
		document.add(createHeaderTable());
		document.add(createTableBody());
		document.close();
		System.out.println("=================PDF Successfully Created=================");
	}

	private PdfPTable createHeaderTable() {
		PdfPTable table = new PdfPTable(1);
		Font headerFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 25);
		Font dateFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14);
		try {
			Image logo = Image.getInstance(new URL(imageUrl));

			headerFont.setColor(new BaseColor(255, 255, 255));
			dateFont.setColor(new BaseColor(255, 255, 255));
			table.addCell(createHeaderImageCell(81, 10, 10, logo, Element.ALIGN_LEFT, "top"));
			table.addCell(createHeaderCell(81, 10, 10, "Release Date: 12/31/2018", dateFont, Element.ALIGN_RIGHT,
					"right left"));
			table.addCell(createHeaderCell(81, 10, 10, reportTitle, headerFont, Element.ALIGN_CENTER, "right left"));
			table.addCell(
					createHeaderCell(81, 10, 10, MonthUtil.MonthYear(), dateFont, Element.ALIGN_CENTER, "bottom"));
			table.addCell(createHeaderCell(240, 240, 240, description, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14),
					Element.ALIGN_LEFT, "bottom"));
		} catch (Exception e) {
			System.out.println("Exception Thrown on Image Creation");
		}
		return table;
	}

	private PdfPTable createTableBody() {
		float[] columnWidths = { 5, 2, 5 };
		PdfPTable table = new PdfPTable(columnWidths);
		Font headerFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD);
		Font bodyFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);
		table.addCell(createTableCellHead(180, 180, 180, "Change Request/Defect", headerFont, Element.ALIGN_CENTER));
		table.addCell(createTableCellHead(180, 180, 180, "Requestor", headerFont, Element.ALIGN_CENTER));
		table.addCell(createTableCellHead(180, 180, 180, "Description", headerFont, Element.ALIGN_CENTER));

		for (BaseTO to : dataList) {
			table.addCell(createTableCellHead(240, 240, 240, to.getTitle(), bodyFont, Element.ALIGN_CENTER));
			table.addCell(
					createTableCellHead(240, 240, 240, to.getBaseMapVal("requestor"), bodyFont, Element.ALIGN_CENTER));
			table.addCell(
					createTableCellHead(240, 240, 240, to.getBaseMapVal("description"), bodyFont, Element.ALIGN_LEFT));
		}
		return table;
	}

	private PdfPCell createTableCellHead(int red, int green, int blue, String text, Font font, int align) {

		PdfPCell cell = new PdfPCell(new Phrase(text, font));

		cell.setBackgroundColor(new BaseColor(red, green, blue));
		cell.setBorderColor(new BaseColor(red, green, blue));
		cell.setHorizontalAlignment(align);

		cell.setUseVariableBorders(true);
		cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
		cell.setBorderColorRight(BaseColor.BLACK);
		cell.setBorderColorLeft(BaseColor.BLACK);
		cell.setBorderColorBottom(BaseColor.BLACK);

		return cell;
	}

	private PdfPCell createHeaderCell(int red, int green, int blue, String text, Font font, int alignment,
			String border) {
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setBackgroundColor(new BaseColor(red, green, blue));
		cell.setBorderColor(new BaseColor(red, green, blue));
		cell.setHorizontalAlignment(alignment);
		cell.setUseVariableBorders(true);
		if (border.contains("top")) {
			cell.setBorderColorTop(BaseColor.BLACK);
			cell.setBorderColorRight(BaseColor.BLACK);
			cell.setBorderColorLeft(BaseColor.BLACK);
		} else if (border.contains("right")) {
			cell.setBorderColorRight(BaseColor.BLACK);
		} else if (border.contains("left")) {
			cell.setBorderColorLeft(BaseColor.BLACK);
		} else if (border.contains("bottom")) {
			cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
			cell.setBorderColorRight(BaseColor.BLACK);
			cell.setBorderColorLeft(BaseColor.BLACK);
			cell.setBorderColorBottom(BaseColor.BLACK);
		}
		return cell;
	}

	private PdfPCell createHeaderImageCell(int red, int green, int blue, Image image, int alignment, String border) {
		PdfPCell cell = new PdfPCell(image);
		cell.setBackgroundColor(new BaseColor(red, green, blue));
		cell.setBorderColor(new BaseColor(red, green, blue));
		cell.setHorizontalAlignment(alignment);
		cell.setUseVariableBorders(true);
		if (border.contains("top")) {
			cell.setBorderColorTop(BaseColor.BLACK);
			cell.setBorderColorRight(BaseColor.BLACK);
			cell.setBorderColorLeft(BaseColor.BLACK);
		} else if (border.contains("right")) {
			cell.setBorderColorRight(BaseColor.BLACK);
		} else if (border.contains("left")) {
			cell.setBorderColorLeft(BaseColor.BLACK);
		} else if (border.contains("bottom")) {
			cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
			cell.setBorderColorRight(BaseColor.BLACK);
			cell.setBorderColorLeft(BaseColor.BLACK);
			cell.setBorderColorBottom(BaseColor.BLACK);
		}
		return cell;
	}

}
