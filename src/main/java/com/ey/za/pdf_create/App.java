package com.ey.za.pdf_create;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App 
{
    public static void main( String[] args )
    {
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

		pageArr[0].placeText(86, 703, "RICHARD VUMA MAMBA", "H1", 10, "L", 0);
		pageArr[0].placeText(86, 693, "NO 28 KRISANT ROAD", "H1", 10, "L", 0);
		pageArr[0].placeText(86, 683, "BRONKHORSTSPRUIT", "H1", 10, "L", 0);

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


}
