package com.ey.za.pdf_create;

public class PdfFont extends PdfObject {
	/*
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
	String baseFont = "Arial";
	String subType = "Type1";
	String logicalName = "";

	public void renderPdfCharacters() {
		if (parentDocument.ADD_COMMENTS) {
			sComment = "  % Font object";
		}
		text = Integer.toString(id) + " 0 obj<</Type/Font" + sComment + parentDocument.SPLIT + "/BaseFont/" + baseFont
				+ sComment + parentDocument.SPLIT + "/Subtype/Type1>>" + sComment + parentDocument.SPLIT + "endobj"
				+ sComment + parentDocument.SPLIT + parentDocument.ISOLATE;
	}
}
