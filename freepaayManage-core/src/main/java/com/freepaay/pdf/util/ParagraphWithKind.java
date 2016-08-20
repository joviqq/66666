package com.freepaay.pdf.util;

public class ParagraphWithKind {

	private String kind;
	private int size;
	private int align;
	private String content;
	
/*	public static final int ALIGN_UNDEFINED = -1;
	public static final int ALIGN_LEFT = 0;
	public static final int ALIGN_CENTER = 1;
	public static final int ALIGN_RIGHT = 2;
	public static final int ALIGN_JUSTIFIED = 3;
	public static final int ALIGN_TOP = 4;
	public static final int ALIGN_MIDDLE = 5;
	public static final int ALIGN_BOTTOM = 6;
	public static final int ALIGN_BASELINE = 7;
	public static final int ALIGN_JUSTIFIED_ALL = 8;*/
	
	public int getAlign() {
		return align;
	}

	public void setAlign(int align) {
		this.align = align;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setAll(String kind, int size, int align, String content) {
		this.kind = kind;
		this.size = size;
		this.align = align;
		this.content = content;
	}
	
	public ParagraphWithKind(String kind, int size, int align, String content) {
		super();
		this.kind = kind;
		this.size = size;
		this.align = align;
		this.content = content;
	}

	public ParagraphWithKind(String kind, int size, int align) {
		super();
		this.kind = kind;
		this.size = size;
		this.align = align;
	}
	
	public ParagraphWithKind() {
		super();
	}
	
}
