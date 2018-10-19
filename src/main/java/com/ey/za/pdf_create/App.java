package com.ey.za.pdf_create;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App 
{
    public static void main( String[] args )
    {
    	int lnPos = 733; 
    	int leftMargin = 86; 
    	int tabPos = 400; 
		boolean isPageStreamsDeflated = false;
		PdfDocument myPdf = new PdfDocument();

		myPdf.isPageStreamsDeflated = isPageStreamsDeflated;

		myPdf.addFont("H1");
		try {
			myPdf.addImage("ImgJPEG1", "C:\\1Hannes\\workspacePhoton\\pdf_create\\src\\main\\resources\\EFCLogo-30.jpg");
		} catch (IOException e1) {
			System.out.println( "pdf_create:-->Cannot addImage ImgJPEG1 to the PDF file!" );
			e1.printStackTrace();
		}

		myPdf.addPage();

		PdfPage[] pageArr = new PdfPage[myPdf.pages.size()];
		pageArr = myPdf.pages.toArray(pageArr);

		String Pg1 = readAllBytesJava7("C:\\1Hannes\\workspacePhoton\\pdf_create\\src\\main\\resources\\ML0000480137.STM");

		pageArr[0].streamText = Pg1; // Only the letterhead

		pageArr[0].placeText(leftMargin, lnPos, "CERTIFIED MAIL", "H1", 10, "L", 0);
		lnPos = 703;
		pageArr[0].placeText(leftMargin, lnPos, "WF Zulu", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "P O Box 22", "H1", 10, "L", 0);
		pageArr[0].placeText(tabPos, lnPos, "03 September 2012", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "Dube", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "SOWETO", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "1800", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		//pageArr[0].placeText(tabPos, lnPos, "Francina Mapulane", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		//pageArr[0].placeText(tabPos, lnPos, "Tel 011 800 2966", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		//pageArr[0].placeText(tabPos, lnPos, "Fax 086 538 9488", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "Dear Sir/Madam", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "CLIENT: WILFRED FANI ZULU", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "ACCOUNT NUMBER: ML0000424143", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "PROPERTY DESCRIPTION: 113 PINEHAVEN ESTATES", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "Building insurance cover over EFC loan - ML XXXXXXXXXXX", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "According to our records you reside in a sectional title residence and therefore your building", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "insurance is managed by the Body Corporate.", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "To safeguard our security we require a letter from the Body Corporate stating that policy is", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "active, the sum insured and that the interest of Eskom Finance Company SOC Ltd is noted", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "with the above loan reference number.", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "The confirmation letter must reach EFC within 14 working days from date of this letter.", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "The details can be e mailed to the following address EFC_FST@eskom.co.za.", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "Should you require any additional information please send an email to", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "EFCAdmin@eskom.co.za clearly stating your employee or bond account number.", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "Yours Faithfully", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "EFC Litigation Department", "H1", 10, "L", 0);
		
		try {
			myPdf.saveAs("C:\\Temp\\TestMe.PDF");
		} catch (IOException e) {
			System.out.println( "pdf_create:-->Cannot save the PDF file!" );
			e.printStackTrace();
		}

        System.out.println( "Built the PDF!" );
    }
    
	private static String readAllBytesJava7(String filePath) {
		String content = "";
		try {
			content = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	private static int newLine(int curLine) {
		return curLine - 12;
	}


}
