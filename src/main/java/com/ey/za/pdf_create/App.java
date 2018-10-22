package com.ey.za.pdf_create;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App 
{
    public static void main( String[] args )
    {
    	//createFstLetter("SectionalTitle");
       	createFstLetter("OutsideInsurance");
       	createFstLetter("OutsideInsurance");
    }
    
    private static void createFstLetter(String letterType) {
		boolean isPageStreamsDeflated = false;
		PdfDocument myPdf = new PdfDocument();

		myPdf.isPageStreamsDeflated = isPageStreamsDeflated;

		myPdf.addFont("H1","Helvetica");
		myPdf.addFont("H2","Helvetica-Bold");
		try {
			myPdf.addImage("ImgJPEG1", "C:\\1Hannes\\workspacePhoton\\pdf_create\\src\\main\\resources\\EFCLogo-30.jpg");
		} catch (IOException e1) {
			System.out.println( "pdf_create:-->Cannot addImage ImgJPEG1 to the PDF file!" );
			e1.printStackTrace();
		}

		myPdf.addPage();

		PdfPage[] pageArr = new PdfPage[myPdf.pages.size()];
		pageArr = myPdf.pages.toArray(pageArr);

		String Pg1 = readAllBytesJava7("C:\\1Hannes\\workspacePhoton\\pdf_create\\src\\main\\resources\\LetterHead.STM");

		pageArr[0].streamText = Pg1; // Add the letterhead
		placeGenericHeading(pageArr[0]);
		switch (letterType) {
        case "SectionalTitle":  placeSectionalTitleBody(pageArr[0]);
        	break;
        case "OutsideInsurance":  placeOutsideInsuranceBody(pageArr[0]);
        	break;
        default: System.out.println( "Invalid letterType specified!" );
        	break;
		}
		
		try {
			myPdf.saveAs("C:\\Temp\\" + letterType + ".PDF");
		} catch (IOException e) {
			System.out.println( "pdf_create:-->Cannot save the PDF file!" );
			e.printStackTrace();
		}

        System.out.println( "Built the PDF!" );

    }
    
    private static void placeGenericHeading(PdfPage targetPage) {
    	int lnPos = 733; 
    	int leftMargin = 86; 
    	int tabPos = 400; 
		//pageArr[0].placeText(leftMargin, lnPos, "CERTIFIED MAIL", "H1", 10, "L", 0);
		lnPos = 703;
		targetPage.placeText(leftMargin, lnPos, "WF Zulu", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "P O Box 22", "H1", 10, "L", 0);
		targetPage.placeText(tabPos, lnPos, "03 September 2012", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "Dube", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "SOWETO", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "1800", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "Dear Sir/Madam", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "CLIENT: WILFRED FANI ZULU", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "ACCOUNT NUMBER: ML0000424143", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "PROPERTY DESCRIPTION: 113 PINEHAVEN ESTATES", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "Building insurance cover over EFC loan - ML XXXXXXXXXXX", "H2", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
    }

    private static void placeSectionalTitleBody(PdfPage targetPage) {
    	int lnPos = 487; 
    	int leftMargin = 86; 
		targetPage.placeText(leftMargin, lnPos, "According to our records you reside in a sectional title residence and therefore your building", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "insurance is managed by the Body Corporate.", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "To safeguard our security we require a letter from the Body Corporate stating that policy is", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "active, the sum insured and that the interest of Eskom Finance Company SOC Ltd is noted", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "with the above loan reference number.", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "The confirmation letter must reach EFC within 14 working days from date of this letter.", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "The details can be e mailed to the following address EFC_FST@eskom.co.za.", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "Should you require any additional information please send an email to", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "EFCAdmin@eskom.co.za clearly stating your employee or bond account number.", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "Yours Faithfully", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "EFC Litigation Department", "H1", 10, "L", 0);
    }
    

    private static void placeOutsideInsuranceBody(PdfPage targetPage) {
    	int lnPos = 487; 
    	int leftMargin = 86; 
		targetPage.placeText(leftMargin, lnPos, "Eskom Finance Company has granted you permission to have your building insurance", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "covered by an insurance company of your choice.", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "To safeguard our security we require your latest policy schedule where the Eskom Finance", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "Company's interest has been noted or a letter from the insurance company stating that", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "policy is active, the sum insured and that the interest of Eskom Finance Company SOC Ltd", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "is noted with the above loan reference number.", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "The policy schedule or letter must reach EFC within 14 working days from date of this letter.", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "The details can be e mailed to the following address: efc_fst@eskom.co.za.", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "Failure to do so Eskom Finance Company will be obliged re-activate your policy through", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "Hollard to ensure that your property is insured to protect both your interest and Eskom", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "Finance Company, being the bond holder.", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "Yours Faithfully", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "EFC Litigation Department", "H1", 10, "L", 0);
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
