package com.ey.za.pdf_create;

public class PdfArray extends PdfObject{
	String arrayContents = "";

	public void renderPdfCharacters() {
		if (parentDocument.ADD_COMMENTS) {
			sComment = "  % Array object";
		}
		text = Integer.toString(id) + " 0 obj " + arrayContents + sComment + parentDocument.ISOLATE;
	}
}
