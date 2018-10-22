package com.ey.za.pdf_create;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PdfDocument {
	String STREAM_SPLIT = "\r\n";
	String SPLIT = "\n";// "\r\n" or "\n";
	String ISOLATE = "\r\n"; // Can use "\n" to make it easier to distinguish objects in the the stream file.
	boolean ADD_COMMENTS = false;
	ArrayList<PdfObject> objects = new ArrayList<>();
	// int pageTreeID = 0;
	// int pages[]={};
	ArrayList<Integer> resources = new ArrayList<>();
	ArrayList<Integer> resourceFonts = new ArrayList<>();
	ArrayList<String> resourceFontNames = new ArrayList<>();
	ArrayList<Integer> resourceImages = new ArrayList<>();
	ArrayList<String> resourceImageNames = new ArrayList<>();
	ArrayList<Integer> pageIds = new ArrayList<>();
	ArrayList<PdfPage> pages = new ArrayList<>();
	static int numOfObjects = 0;
	int rootID = 0; // Id of "catalog" object
	int pagetreeID = 0; // Id of "pagetree" object
	int resourceID = 0; // Id of "resource" object - for simplicity a single resource object is used for
						// the whole document
	int doctitleID = 0;
	boolean isPageStreamsDeflated = false;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("PdfDocument - main()");
	}

	PdfDocument() {
		objects = new ArrayList<>();
		// int pageTreeID = 0;
		// int pages[]={};
		resources = new ArrayList<>();
		resourceFonts = new ArrayList<>();
		resourceFontNames = new ArrayList<>();
		resourceImages = new ArrayList<>();
		resourceImageNames = new ArrayList<>();
		pageIds = new ArrayList<>();
		pages = new ArrayList<>();
		numOfObjects = 0;
		System.out.println("Fix this PdfObject.numOfPdfObjects - maybe use numOfObjects propery of PdfDocument");
		PdfObject.numOfPdfObjects=0;
		rootID = 0; // Id of "catalog" object
		pagetreeID = 0; // Id of "pagetree" object
		resourceID = 0; // Id of "resource" object - for simplicity a single resource object is used for
							// the whole document
		doctitleID = 0;
		isPageStreamsDeflated = false;

		//-------------------------------------------above is new
		int newObjectID = 0;
		// System.out.println("PdfDocument Default Constructor.");
		addObject("empty"); // Add empty object to ArrayList<PdfObject> objects to allow the position of
							// object to be the same as the id of the object
		doctitleID = addObject("info");
		resourceID = addObject("resource");
		rootID = addObject("catalog");
		pagetreeID = addObject("pagetree");
	}

	String redirect(int pageId) {
		return Integer.toString(pageId) + " 0 R";
	}

	public void addPage() {
		int newObjectID = addObject("page");
		// System.out.println("newObjectID="+newObjectID);
		pageIds.add(newObjectID);
		pages.add((PdfPage) objects.get(newObjectID));
		// PdfObject newlyAddedObject = objects.get(newObjectID);
		PdfPage newlyAddedPage = (PdfPage) objects.get(newObjectID);
		// System.out.println("newlyAddedPage.id="+newlyAddedPage.id);
		// System.out.println("newlyAddedPage.getClass()="+newlyAddedPage.getClass());
		// System.out.println("newlyAddedPage.contentStreamID="+newlyAddedPage.contentStreamID);

		newlyAddedPage.contentStreamID = addObject("pagestream");
		PdfStream newlyAddedStream = (PdfStream) objects.get(newlyAddedPage.contentStreamID);
		newlyAddedStream.parentPage = newlyAddedPage;
	}

	public void addImage(String imageName, String filePath) throws IOException {
		int newObjectID = addObject("xobject");
		resources.add(newObjectID);
		resourceImages.add(newObjectID);
		resourceImageNames.add(imageName);
		PdfXObject newImage = (PdfXObject) objects.get(newObjectID);
		newImage.logicalName = imageName;
		newImage.filePath = filePath;
		newImage.checkImageProperties();
	}

	public void addFont(String fontName,String baseFont) {
		int newObjectID = addObject("font");
		System.out.println("addFont(" + fontName + ") : resourceFontNames.size()=" + resourceFontNames.size());
		System.out.println("addFont(" + fontName + ") : newObjectID=" + newObjectID);
		resources.add(newObjectID);
		System.out.println("Works 1");
		resourceFonts.add(newObjectID);
		System.out.println("Works 2");
		resourceFontNames.add(fontName);
		System.out.println("Works 3");
		PdfFont newFont = (PdfFont) objects.get(newObjectID); 
		System.out.println("Works 4");
		newFont.name = fontName;
		newFont.baseFont = baseFont;
		System.out.println("Works 5");
		switch(baseFont) {
			case "ArialMT": 
					newFont.fontDescriptorID = addFontDescriptor(baseFont);
					break;
			case "Arial-BoldMT": 
					newFont.fontDescriptorID = addFontDescriptor(baseFont);
					break;
		}
		
	}

	public int addFontDescriptor(String baseFont) {
		int newObjectID = addObject("fontdescriptor");

		PdfFontDescriptor newFontDescriptor = (PdfFontDescriptor) objects.get(newObjectID);

		switch(baseFont) {
		case "ArialMT": 
			newFontDescriptor.fontName = "Arial-BoldMT";
			newFontDescriptor.flags = 32;
			newFontDescriptor.italicAngle = 0;
			newFontDescriptor.ascent = 905;
			newFontDescriptor.descent = -210;
			newFontDescriptor.capHeight = 728;
			newFontDescriptor.avgWidth = 441;
			newFontDescriptor.maxWidth = 2665;
			newFontDescriptor.fontWeight = 400;
			newFontDescriptor.xHeight = 250;
			newFontDescriptor.leading = 33;
			newFontDescriptor.stemV = 44;
			newFontDescriptor.fontBBox = "[ -665 -210 2000 728]";
			break;
		case "Arial-BoldMT": 
			newFontDescriptor.fontName = "Arial-BoldMT";
			newFontDescriptor.flags = 32;
			newFontDescriptor.italicAngle = 0;
			newFontDescriptor.ascent = 905;
			newFontDescriptor.descent = -210;
			newFontDescriptor.capHeight = 728;
			newFontDescriptor.avgWidth = 479;
			newFontDescriptor.maxWidth = 2628;
			newFontDescriptor.fontWeight = 700;
			newFontDescriptor.xHeight = 250;
			newFontDescriptor.leading = 33;
			newFontDescriptor.stemV = 47;
			newFontDescriptor.fontBBox = "[ -628 -210 2000 728]";
			break;
		}

		newFontDescriptor.fontName = baseFont;
		return newObjectID;
	}

	public int addObject(String objectType) {
		PdfObject newObject;// = new PdfObject();
		PdfObject.numOfPdfObjects++;

		switch (objectType) {
		case "empty":
			numOfObjects--;
			newObject = new PdfObject();
			break;
		case "info":
			newObject = new PdfInfo();
			break;
		case "page":
			newObject = new PdfPage();
			break;
		case "font":
			newObject = new PdfFont();
			break;
		case "fontdescriptor":
			newObject = new PdfFontDescriptor();
			break;
		case "xobject":
			newObject = new PdfXObject();
			break;
		case "catalog":
			newObject = new PdfCatalog();
			break;
		case "pagetree":
			newObject = new PdfPagetree();
			break;
		case "resource":
			newObject = new PdfResource();
			break;
		case "pagestream":
			newObject = new PdfStream();
			break;
		default:
			newObject = new PdfObject();
			break;
		}
		numOfObjects++;
		newObject.id = numOfObjects;
		newObject.type = objectType;
		newObject.parentDocument = this;
		System.out.println("PdfDocument - addObject("+objectType+") - ID:"+newObject.id);
		objects.add(newObject);

		return newObject.id;
	}

	public void saveAs(String filePath) throws IOException {
		String sComment = "";
		String text = "";

		System.out.println("");
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("Saving to file : " + filePath);
		System.out.println("------------------------------------------------------------------------------");
		try (FileOutputStream fos = new FileOutputStream(filePath + "BYT.PDF")) {

			if (ADD_COMMENTS) {
				sComment = "  % File header";
			}

			// text = "%PDF-1.3" + sComment + SPLIT + ISOLATE;
			objects.get(0).text = "%PDF-1.3" + sComment + SPLIT + ISOLATE;

			for (PdfObject forObject : objects) {
				// if (!Objects.equals(forObject.type, "empty")) {
				forObject.renderPdfCharacters();
				text = text + forObject.text;
				fos.write(forObject.getObjectBytes());
				// }
			}

			String xrefCorrection = SPLIT;
			System.out.println("Fix this PdfObject.numOfPdfObjects - maybe use numOfObjects propery of PdfDocument");
			String xref = xrefCorrection + "xref" + SPLIT + "0 " + Integer.toString(PdfObject.numOfPdfObjects)// ---------------
					+ SPLIT + "0000000000 65535 f" + STREAM_SPLIT;

			String offsets;
			int objectCounter = 0;
			int iFilePosition = 0;
			for (PdfObject forObject : objects) {
				objectCounter++;
				iFilePosition = (iFilePosition + forObject.numOfBytes);
				if (objectCounter < PdfObject.numOfPdfObjects) {
					// iFilePosition = (iFilePosition + forObject.numOfBytes);
					// This field should be 20 bytes long.
					offsets = ("0000000000" + Integer.toString(iFilePosition))
							.substring((("0000000000" + Integer.toString(iFilePosition)).length() - 10));
					xref += offsets + " 00000 n" + STREAM_SPLIT;
				}
			}
			iFilePosition = iFilePosition + xrefCorrection.length(); // Correct iFilePosition for SPLIT before xref

			if (ADD_COMMENTS) {
				sComment = "  % Trailer";
			} // ------------------------------------------------
			String trailer = "trailer" + sComment + SPLIT + "<</Size " + Integer.toString(PdfObject.numOfPdfObjects)
					+ "/";
			trailer += "Root " + redirect(rootID) + sComment + SPLIT;
			trailer += "/Info " + redirect(doctitleID) + sComment + SPLIT;
			trailer += ">>";
			trailer += SPLIT + "startxref" + SPLIT + Integer.toString(iFilePosition) + SPLIT + "%%EOF" + SPLIT;

			text = text + xref + trailer;
			fos.write(xref.getBytes(StandardCharsets.UTF_8));
			fos.write(trailer.getBytes(StandardCharsets.UTF_8));
			// System.out.println(text);
		}
		try (FileOutputStream fos2 = new FileOutputStream(filePath + ".TXT")) {
			fos2.write(text.getBytes(StandardCharsets.UTF_8));
		}
	}
}
