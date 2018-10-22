package com.ey.za.pdf_create;
/* 
Example of FontDescriptor object
8 0 obj
<</Type/FontDescriptor
/FontName/Arial-BoldMT
/Flags 32
/ItalicAngle 0
/Ascent 905
/Descent -210
/CapHeight 728
/AvgWidth 479
/MaxWidth 2628
/FontWeight 700
/XHeight 250
/Leading 33
/StemV 47
/FontBBox[ -628 -210 2000 728] >>
endobj 
*/
public class PdfFontDescriptor extends PdfObject  {

	String fontName = "Arial-BoldMT"; //Required
	int flags = 32;	//Required
	int italicAngle = 0; //Required
	int ascent = 905; //Required
	int descent = -210; //Required
	int capHeight = 728; //Required
	int avgWidth = 479;
	int maxWidth = 2628;
	int fontWeight = 700;
	int xHeight = 250;
	int leading = 33;
	int stemV = 47; //Required
	String fontBBox = "[ -628 -210 2000 728]"; //Required

	public void renderPdfCharacters() {
		if (parentDocument.ADD_COMMENTS) {
			sComment = "  % FontDescriptor object";
		}
		text = Integer.toString(id) + " 0 obj<</Type/FontDescriptor" + sComment + parentDocument.SPLIT + 
				"/FontName/" + fontName	+ sComment + parentDocument.SPLIT + 
				"/Flags " + flags	+ sComment + parentDocument.SPLIT + 
				"/ItalicAngle " + italicAngle	+ sComment + parentDocument.SPLIT + 
				"/Ascent " + ascent	+ sComment + parentDocument.SPLIT + 
				"/Descent " + descent	+ sComment + parentDocument.SPLIT + 
				"/CapHeight " + capHeight	+ sComment + parentDocument.SPLIT + 
				"/AvgWidth " + avgWidth	+ sComment + parentDocument.SPLIT + 
				"/MaxWidth " + maxWidth	+ sComment + parentDocument.SPLIT + 
				"/FontWeight " + fontWeight	+ sComment + parentDocument.SPLIT + 
				"/XHeight " + xHeight	+ sComment + parentDocument.SPLIT + 
				"/Leading " + leading	+ sComment + parentDocument.SPLIT + 
				"/StemV " + stemV	+ sComment + parentDocument.SPLIT + 
				"/FontBBox" + fontBBox + ">>" + sComment + parentDocument.SPLIT + "endobj"
				+ sComment + parentDocument.SPLIT + parentDocument.ISOLATE;
	}
}
