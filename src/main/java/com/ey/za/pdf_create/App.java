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
			// TODO Auto-generated catch block
			System.out.println( "pdf_create:-->Cannot addImage ImgJPEG1 to the PDF file!" );
			e1.printStackTrace();
		}
		//myPdf.addImage("ImgJPEG2", "C:\\1Hannes\\workspacePhoton\\pdf_create\\src\\main\\resources\\EFCLogo-30Gray.jpg");

		myPdf.addPage();
		myPdf.addPage();
		myPdf.addPage();
		myPdf.addPage();

		PdfPage[] pageArr = new PdfPage[myPdf.pages.size()];
		pageArr = myPdf.pages.toArray(pageArr);

		//verifyObjectPositions();

		String Pg1 = readAllBytesJava7("C:\\1Hannes\\workspacePhoton\\pdf_create\\src\\main\\resources\\ML0000480137.STM");
		String Pg2 = readAllBytesJava7("C:\\1Hannes\\workspacePhoton\\pdf_create\\src\\main\\resources\\ML0000480137.ST2");

		// Test content streams
		pageArr[0].streamText = Pg1; // We need to fix the image before this will work
		pageArr[1].streamText = Pg2;
		//pageArr[0].placeText(86, 703, "This is page 1", "H1", 10, "L", 0);
		//pageArr[1].placeText(86, 703, "This is page 2", "H1", 10, "L", 0);
		pageArr[2].placeText(86, 703, "This is page 3", "H1", 10, "L", 0);
		pageArr[3].placeText(86, 703, "This is page 4", "H1", 10, "L", 0);
		//pageArr[2].placeImage(42, 768, "ImgJPEG2", 125.150, 38.595, 0);

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
