package com.ey.za.pdf_create;

public class PdfStream extends PdfObject {
	PdfPage parentPage;

//    byte Pg1Strm[] = readFileToBytes((parentDocument.isPageStreamsDeflated ? "C:\\1Hannes\\workspace\\StatementPDF\\DEF\\PL0000511584.STM.DEF" : "C:\\1Hannes\\workspace\\StatementPDF\\STM\\PL0000511584.STM"));
//    byte Pg2Strm[] = readFileToBytes((parentDocument.isPageStreamsDeflated ? "C:\\1Hannes\\workspace\\StatementPDF\\DEF\\PL0000511584.ST2.DEF" : "C:\\1Hannes\\workspace\\StatementPDF\\STM\\PL0000511584.ST2"));
	// MediaBox
	// int contentStreamID = 0;
	// Group
	// Tabs

	public void renderPdfCharacters() {
		if (parentDocument.ADD_COMMENTS) {
			sComment = "  % Stream object";
		}
		text = Integer.toString(id) + " 0 obj<<" + sComment + parentDocument.SPLIT
				+ (parentDocument.isPageStreamsDeflated
						? "/Filter /FlateDecode " + sComment + parentDocument.SPLIT + parentDocument.ISOLATE
						: "")
				+ "/Length " + Integer.toString(parentPage.streamText.length()) + ">>stream" + sComment
				+ parentDocument.SPLIT;
		text = text + parentPage.streamText;
		text = text + "endstream" + sComment + parentDocument.SPLIT // + parentDocument.ISOLATE
				+ "endobj" + sComment + parentDocument.SPLIT + parentDocument.ISOLATE;

		// byte[] b = parentPage.streamText.getBytes(StandardCharsets.UTF_8);
		// System.out.println("b.length = "+b.length);
	}
}
