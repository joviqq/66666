package com.freepaay.pdf.util;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;

public class PDFParagraph extends Paragraph {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PDFParagraph(String content, int fontSize) {
		super(content, getChineseFont(fontSize));
	}

	public static final Font getChineseFont(int fontSize) {
		Font font = null;
		try {
			BaseFont baseFont = BaseFont.createFont("MSungStd-Light", "UniCNS-UCS2-H", BaseFont.NOT_EMBEDDED);
//			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			font = new Font(baseFont, fontSize, Font.NORMAL);
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		return font;
	}


	public static final Font getEnglishFont(int fontSize) {
		Font font = null;
		try {
			BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
//			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			font = new Font(baseFont, fontSize, Font.NORMAL);
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		return font;
	}
	
}