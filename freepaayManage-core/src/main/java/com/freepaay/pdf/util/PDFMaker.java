package com.freepaay.pdf.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.freepaay.tfs.util.TFSUtil;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/* Author 福福
 * Date 2016/07/16
*/
public class PDFMaker {

	/*
	 * 创建PDF，并上传到TFS上，返回上传文件的路径。
	 * 
	 * ParagraphWithKind有以下属性:
	 * 
	 * kind:有3种 IMAGE, TEXT, TABLE
	 * 
	 * size:指字体大小。一般定成12.
	 * 
	 * align: ALIGN_UNDEFINED = -1;ALIGN_LEFT = 0;ALIGN_CENTER = 1;ALIGN_RIGHT =
	 * 2; ALIGN_JUSTIFIED = 3;ALIGN_TOP = 4;ALIGN_MIDDLE = 5;ALIGN_BOTTOM = 6;
	 * ALIGN_BASELINE = 7;ALIGN_JUSTIFIED_ALL = 8;
	 * 
	 * content: <sup></sup>代表上标 <sub></sub>代表下标
	 * 
	 * 如果kind是IMAGE, content是IMAGE所在的url
	 * 
	 * 如果kind是TEXT, content是文字
	 * 
	 * 如果kind是TABLE,
	 * 
	 * content是如下格式
	 * 
	 * #ENCN#cn#ENCN##cols#5#cols##cells#cell1Value#cell#cell2value#cell#cell3value#cell##cells#
	 * 
	 * #ENCN#里面代表使用的字体是什么字体。
	 * 
	 * en:使用英文字体：HELVETICA
	 * 
	 * cn:使用中文字体：宋体
	 * 
	 * 其中#cols#5#cols#代表这个表格是5列的表格
	 * 
	 * #cells#...#cells#里面是每个单元格的内容。
	 * 
	 * 然后内容用#cell#隔开。
	 * 
	 * 如果需要设置cell字体。用#ENCN#cn#ENCN#，放在前面即可
	 * 
	 * 如果需要rowspan，colspan，则在cell1Value前面加上#rowspan#3#rowspan#,#colspan#3#
	 * colspan#
	 * 
	 * 每个cell如果有#，则#一定要放在左边
	 * 
	 * 最后一个#cell#留空。
	 * 
	 * 一个完整的例子:
	 * 
	 * 
	 */
	public static String createPDF(String fileName, List<ParagraphWithKind> paraList)
			throws DocumentException, MalformedURLException, IOException {
		String fileNameNew = UUID.randomUUID().toString().replaceAll("-", "")
				+ fileName.substring(fileName.lastIndexOf("."), fileName.length());
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);// 是用于生成
		String fileFullName = System.getProperty("user.dir") + "/" + fileNameNew;
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileFullName));
		document.addAuthor("作者");
		document.addTitle(fileName);
		document.addSubject(fileName);
		document.addKeywords("关键字");
		// document.setMargins(16, 16, 16, 16);
		document.open();
		document.newPage();
		writer.setPageEmpty(true);// 不会显示空内容的页
		for (int i = 0; i < paraList.size(); i++) {
			ParagraphWithKind p = paraList.get(i);
			if (p.getKind().equals("IMAGE")) {
				Paragraph par1 = new PDFParagraph("", p.getSize());// 此类表示段落。
				Image png1 = Image.getInstance(p.getContent());
				par1.setAlignment(p.getAlign());
				par1.add(png1);
				par1.setIndentationLeft(12);// 左缩进
				par1.setIndentationRight(12);// 右缩进
				par1.setFirstLineIndent(24);// 首行缩进
				par1.setLeading(20f);// 行间距
				par1.setSpacingBefore(5f);// 设置上空白
				par1.setSpacingAfter(10f);// 设置段落下空白
				document.add(par1);
			} else if (p.getKind().equals("TEXT")) {
				Paragraph par2 = new PDFParagraph("", p.getSize());// 此类表示段落。
				par2.setAlignment(p.getAlign());
				Phrase p1 = getSubOrSupChunks(p.getContent(), PDFParagraph.getChineseFont(p.getSize()),
						PDFParagraph.getChineseFont(5));
				par2.add(p1);
				par2.setIndentationLeft(12);// 左缩进
				par2.setIndentationRight(12);// 右缩进
				par2.setFirstLineIndent(24);// 首行缩进
				par2.setLeading(20f);// 行间距
				par2.setSpacingBefore(5f);// 设置上空白
				par2.setSpacingAfter(10f);// 设置段落下空白
				document.add(par2);
			} else if (p.getKind().equals("TABLE")) {
				Paragraph par3 = new PDFParagraph("", p.getSize());// 此类表示段落。
				// par.setAlignment(p.getAlign());//设定段落的对齐
				int cols = Integer.valueOf(p.getContent().split("#cols#")[1]);
				PdfPTable table = new PdfPTable(cols);// 创建一个表，包含cols列
				table.setWidthPercentage(80F);// 占据百分百宽度
				PdfPCell cell;
				String[] tableStr = p.getContent().split("#cells#")[1].split("#cell#");
				String ENCN = p.getContent().split("#ENCN#")[1];
				// 最后一个#cell#右边会留空
				for (int j = 0; j < tableStr.length; j++) {
					String cellStr = tableStr[j];
					if (cellStr.indexOf("#") == -1) {
						// 如果有下标或者上标，用英文字体会好看些。
						if (ENCN.equals("en")) {
							cell = new PdfPCell(new Phrase(getSubOrSupChunks(cellStr, PDFParagraph.getEnglishFont(p.getSize()),
									PDFParagraph.getEnglishFont(5))));
						} else {
							cell = new PdfPCell(new Phrase(getSubOrSupChunks(cellStr, PDFParagraph.getChineseFont(p.getSize()),
									PDFParagraph.getChineseFont(5))));
						}
						cell.setPaddingBottom(10);// 设置单元格下空白为10.
						table.addCell(cell);
					} else {
						String[] cellStrArray = cellStr.split("#");
						if (cellStr.indexOf("#ENCN#") > -1) {
							String encnCell = cellStr.split("#ENCN#")[1];
							if (encnCell.equals("en")) {
								cell = new PdfPCell(new Phrase(getSubOrSupChunks(cellStrArray[cellStrArray.length - 1],
										PDFParagraph.getEnglishFont(p.getSize()), PDFParagraph.getEnglishFont(5))));
							} else {
								cell = new PdfPCell(new Phrase(getSubOrSupChunks(cellStrArray[cellStrArray.length - 1],
										PDFParagraph.getChineseFont(p.getSize()), PDFParagraph.getChineseFont(5))));
							}
						} else {
							if (ENCN.equals("en")) {
								cell = new PdfPCell(new Phrase(getSubOrSupChunks(cellStrArray[cellStrArray.length - 1],
										PDFParagraph.getEnglishFont(p.getSize()), PDFParagraph.getEnglishFont(5))));
							} else {
								cell = new PdfPCell(new Phrase(getSubOrSupChunks(cellStrArray[cellStrArray.length - 1],
										PDFParagraph.getChineseFont(p.getSize()), PDFParagraph.getChineseFont(5))));
							}
						}
						cell.setPaddingBottom(10);// 设置单元格下空白为10.
						if (cellStr.indexOf("#colspan#") > -1) {
							cell.setColspan(Integer.valueOf(cellStr.split("#colspan#")[1]));
						}
						if (cellStr.indexOf("#rowspan#") > -1) {
							cell.setColspan(Integer.valueOf(cellStr.split("#rowspan#")[1]));
						}
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 设定垂直居中对齐
						cell.setHorizontalAlignment(p.getAlign());// 设定单个格子的对齐
						table.addCell(cell);
					}
				}
				par3.add(table);
				document.add(par3);
			}
		}
		document.close();
		String tfsString = TFSUtil.saveTfsFile(new FileInputStream(fileFullName), fileName);
		// 上传完了需要删除文件
		new File(fileFullName).delete();
//		return "/file/upload/" + fileName + "/" + tfsString + "/open";
		return "/file/upload/" + tfsString + "/" + fileName ;
	}

	/** 处理 数字的上标 和 小标 */
	public static final String SUBSCRIPT = "<sub>";
	public static final String SUBSCRIPT_END = "</sub>";
	public static final String SUPERSCRIPT = "<sup>";
	public static final String SUPERSCRIPT_END = "</sup>";

	public static String nextSubOrSupTag(String input) {
		int supIdx = StringUtils.indexOf(input, SUPERSCRIPT);
		int subIdx = StringUtils.indexOf(input, SUBSCRIPT);

		if (subIdx == StringUtils.INDEX_NOT_FOUND && supIdx == StringUtils.INDEX_NOT_FOUND) {
			return null;
		} else if (subIdx == StringUtils.INDEX_NOT_FOUND) {
			return SUPERSCRIPT;
		} else if (supIdx == StringUtils.INDEX_NOT_FOUND) {
			return SUBSCRIPT;
		} else {
			if (subIdx < supIdx) {
				return SUBSCRIPT;
			} else {
				return SUPERSCRIPT;
			}
		}
	}

	public static String nextEndTag(String tag) {
		if (SUPERSCRIPT.equalsIgnoreCase(tag)) {
			return SUPERSCRIPT_END;
		} else {
			return SUBSCRIPT_END;
		}
	}

	public static Phrase getSubOrSupChunks(String temp, Font textFt, Font textriseFt) {
		Phrase phrase = new Phrase();
		String nextTag = nextSubOrSupTag(temp);
		String endTag = nextEndTag(nextTag);
		int tagCount = StringUtils.countMatches(temp, SUBSCRIPT) + StringUtils.countMatches(temp, SUPERSCRIPT);
		if (tagCount == 0) {
			phrase.add(new Chunk(StringUtils.substringBefore(temp, nextTag), textFt));
			return phrase;
		}
		for (int i = 0; i < tagCount; i++) {
			if (i == 0) {
				phrase.add(new Chunk(StringUtils.substringBefore(temp, nextTag), textFt));
			} else {
				temp = StringUtils.substringAfter(temp, nextTag);
				nextTag = nextSubOrSupTag(temp);
				phrase.add(new Chunk(StringUtils.substringBetween(temp, endTag, nextTag), textFt));
				endTag = nextEndTag(nextTag);
			}
			if (SUBSCRIPT.equalsIgnoreCase(nextTag)) {
				phrase.add(new Chunk(StringUtils.substringBetween(temp, nextTag, endTag), textriseFt).setTextRise(-3f));
			} else {
				phrase.add(new Chunk(StringUtils.substringBetween(temp, nextTag, endTag), textriseFt).setTextRise(5f));
			}
			if (i == tagCount - 1) {
				temp = StringUtils.substringAfter(temp, nextTag);
				phrase.add(new Chunk(StringUtils.substringAfter(temp, endTag), textFt));
			}
		}
		return phrase;
	}

	public static PDFMaker getInstance() {
		return new PDFMaker();
	}
}
