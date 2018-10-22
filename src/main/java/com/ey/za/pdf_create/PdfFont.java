package com.ey.za.pdf_create;

public class PdfFont extends PdfObject {
	/*
9.6.2.2Standard Type 1 Fonts (Standard 14 Fonts)
The PostScript names of 14 Type 1 fonts, known as the standard 14 fonts, are as follows: 
Times-Roman, 
Helvetica, 
Courier, 
Symbol, 
Times-Bold, 
Helvetica-Bold, 
Courier-Bold, 
ZapfDingbats, 
Times-Italic, 
Helvetica-Oblique, 
Courier-Oblique, 
Times-BoldItalic, 
Helvetica-BoldOblique, 
Courier-BoldOblique

These fonts, or their font metrics and suitable substitution fonts, shall be available to the conforming reader.
NOTE The character sets and encodings for these fonts are listed in Annex D. The font metrics files for the standard 14 fonts are 
available from the ASN Web site (see the Bibliography). For more information on font metrics, see Adobe Technical Note #5004, Adobe 
Font Metrics File Format Specification.
	
	FontWeight number
    (Optional; PDF 1.5; should be used for Type 3 fonts in Tagged PDF documents) The weight (thickness) component of the fully-qualified 
    font name or font specifier. The possible values shall be 100, 200, 300, 400, 500, 600, 700, 800, or 900, where each number indicates 
    a weight that is at least as dark as its predecessor. A value of 400 shall indicate a normal weight; 700 shall indicate bold.
    
    If the FontFamily, FontWeight and FontStretch fields are not present in the font descriptor, these values shall be derived from the 
    font name in a manner of the conforming reader’s choosing.
	
	Below are the objects reverse engineered from a PDF created with MS Word - F2 is the bold font:
	
5 0 obj
<</Type/Font
/Subtype/TrueType
/Name/F1/
BaseFont/ArialMT
/Encoding/WinAnsiEncoding
/FontDescriptor 6 0 R
/FirstChar 32
/LastChar 119
/Widths 10 0 R>>
endobj

6 0 obj
<</Type/FontDescriptor
/FontName/ArialMT
/Flags 32
/ItalicAngle 0
/Ascent 905
/Descent -210
/CapHeight 728
/AvgWidth 441
/MaxWidth 2665
/FontWeight 400
/XHeight 250
/Leading 33
/StemV 44
/FontBBox[ -665 -210 2000 728] >>
endobj

7 0 obj
<</Type/Font
/Subtype/TrueType
/Name/F2
/BaseFont/Arial-BoldMT
/Encoding/WinAnsiEncoding
/FontDescriptor 8 0 R
/FirstChar 32
/LastChar 111
/Widths 11 0 R>>
endobj

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

10 0 obj
[ 278 0 0 0 0 0 0 0 0 0 0 0 0 0 278 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 611 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 556 0 0 556 0 0 500 0 0 0 556 0 0 333 500 278 0 0 722] 
endobj

11 0 obj
[ 278 0 0 0 0 0 0 0 0 0 0 0 0 0 278 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 722 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 611 0 611 556 0 0 0 278 0 0 278 0 611 611] 
endobj
*/
	String subType = "Type1";
	String name = "";
	String baseFont = "Arial";
	String encoding = "WinAnsiEncoding";
	int fontDescriptorID = 0; // Id of "pagetree" object
	int firstChar = 32;
	int lastChar = 111;
	int widthsID = 0;
/*<</Type/Font
/Subtype/TrueType
/Name/F2
/BaseFont/Arial-BoldMT
/Encoding/WinAnsiEncoding
/FontDescriptor 8 0 R
/FirstChar 32
/LastChar 111
/Widths 11 0 R>>
*/
	public void renderPdfCharacters() {
		if (parentDocument.ADD_COMMENTS) {
			sComment = "  % Font object";
		}

		System.out.println("/BaseFont/" + baseFont + "/Subtype/" + subType + " > fontDescriptorID = " + fontDescriptorID);
		
		if (fontDescriptorID == 0) { //This is the old way using Type1 fonts
			text = Integer.toString(id) + " 0 obj<</Type/Font" + sComment + parentDocument.SPLIT + 
					"/BaseFont/" + baseFont + sComment + parentDocument.SPLIT + 
					"/Subtype/" + subType;
			text = text + ">>" + sComment + parentDocument.SPLIT + "endobj"	+ sComment + parentDocument.SPLIT + parentDocument.ISOLATE;
		}
		
		//TODO: if (fontDescriptorID > 0) {Add redirect to the FontDescriptor: /FontDescriptor 8 0 R}
		if (fontDescriptorID == 0) { //This is the new way using TrueType fonts
			/*text = Integer.toString(id) + " 0 obj<</Type/Font" + sComment + parentDocument.SPLIT + 
					"/Subtype/" + subType + sComment + parentDocument.SPLIT + 
					"/Name/" + name + sComment + parentDocument.SPLIT + 
					"/BaseFont/" + baseFont + sComment + parentDocument.SPLIT + 
					"/Encoding/" + encoding + sComment + parentDocument.SPLIT + 
					"/FontDescriptor " + redirect(fontDescriptorID) + sComment + parentDocument.SPLIT + 
					"/FirstChar " + firstChar + sComment + parentDocument.SPLIT + 
					"/LastChar " + lastChar + sComment + parentDocument.SPLIT + 
					"/Widths " + redirect(widthsID) + sComment + parentDocument.SPLIT + 
					">>" + sComment + parentDocument.SPLIT + "endobj" + sComment + parentDocument.SPLIT + parentDocument.ISOLATE;*/
			text = Integer.toString(id) + " 0 obj<</Type/Font" + sComment + parentDocument.SPLIT + 
					"/Subtype/" + subType + sComment + parentDocument.SPLIT + 
					"/BaseFont/" + baseFont + sComment + parentDocument.SPLIT + 
					"/FontDescriptor " + redirect(fontDescriptorID) + sComment + parentDocument.SPLIT + 
					">>" + sComment + parentDocument.SPLIT + "endobj" + sComment + parentDocument.SPLIT + parentDocument.ISOLATE;
		}
	}
}
