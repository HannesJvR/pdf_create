package com.ey.za.pdf_create;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App 
{
	static String postfix = "R7";
	static String startOfPreviousMonth = "01-Sep-2018"; //Format DD-MMM-YYYY
	static String startOfThisMonth = "01-Oct-2018"; //Format DD-MMM-YYYY
	static String letterDate = "25 October 2018";
	static String targetFolder = "C:\\Temp\\FST\\";
    public static void main( String[] args )
    {
    	//processAllFstLetters();
    	processAllSubsidyLetters();
    }

    private static void processAllSubsidyLetters() {
    	System.out.print("Open email bridge...");
    	BridgeToOutlook outlook = new BridgeToOutlook();
    	System.out.println("Email ready.");
    	
		String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Staging;user=test;password=test";
		SubsidyLetter thisLetter = new SubsidyLetter();
		
    	String sql = new StringBuilder()
    			.append("USE Phoenix; \n")
     			.append("SELECT TOP 10 \n")
    			.append("     [Loan Application Number] AS LoanApplicationNumber \n")
    			.append("     ,[PayrollCode] \n")
    			.append("     ,[RecID] \n")
    			.append("     ,CAST(CAST([Oct Payment] AS money) AS varchar(8)) AS OctPayment \n")
    			.append("     ,CAST(CAST([Correct_Amount] AS money) AS varchar(8)) AS CorrectAmount \n")
    			.append("     ,[Sep-Apps] AS SepApps \n")
    			.append("     ,CAST(ABS(CAST([Variance] AS money)) AS varchar(8)) AS Variance \n")
    			.append("     ,[Nov_Recon] AS NovRecon \n")
    			.append("     ,[Recon] \n")
    			.append("     ,[employeeno] \n")
    			.append("     ,[party_id] \n")
    			.append("     ,[FirstNames] \n")
    			.append("     ,[Surname] \n")
    			.append("     ,[email] \n")
    			.append("FROM \n")
    			.append("     [Phoenix].[dbo].[October2018Subsidy] \n").toString();
//    			.append("WHERE SUBSTRING([Variance],1,1) = '-';").toString();
    	
    	/*      ,SUBSTRING([Variance],2,LEN([Variance])-1)
   WHERE SUBSTRING([Variance],1,1) = '-';*/
    	
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server... ");
			try (Connection connection = DriverManager.getConnection(connectionUrl)) {
				System.out.println("Database ready.");

				// READ demo
				System.out.print("Press ENTER to retrieve data...");
				System.in.read();
				System.out.println("Reading data from table...");
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {

		    			thisLetter.LoanApplicationNumber = resultSet.getString("LoanApplicationNumber").trim();
						thisLetter.PayrollCode = resultSet.getString("PayrollCode").trim();
						thisLetter.RecID = resultSet.getString("RecID").trim();
						thisLetter.OctPayment = resultSet.getString("OctPayment").trim();
						thisLetter.CorrectAmount = resultSet.getString("CorrectAmount").trim();
						thisLetter.SepApps = resultSet.getString("SepApps").trim();
						thisLetter.Variance = resultSet.getString("Variance").trim();
						thisLetter.NovRecon = resultSet.getString("NovRecon").trim();
						thisLetter.Recon = resultSet.getString("Recon").trim();
						thisLetter.employeeno = resultSet.getString("employeeno").trim();
						thisLetter.party_id = resultSet.getString("party_id").trim();
						thisLetter.FirstNames = resultSet.getString("FirstNames").trim();
						thisLetter.Surname = resultSet.getString("Surname").trim();
						thisLetter.email = resultSet.getString("email").trim();

						System.out.println(
								thisLetter.RecID + "|");
						
						
						thisLetter.filepath = "C:\\temp\\FST\\" + thisLetter.employeeno + ".PDF";
						
						createSubsidyLetter(thisLetter);
				    	//outlook.sendEmail("hannesj@mail.com", "Test1", "This is the body of the message", thisLetter.filepath);
				    	//outlook.sendEmail(thisLetter.email_addr_1, "Test1", "This is the body of the message", thisLetter.filepath);
					}
				}
				connection.close();
				System.out.println("All done.");
			}
		} catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
    }
    
    private static void createSubsidyLetter(SubsidyLetter letterData) {
		boolean isPageStreamsDeflated = false;
		PdfDocument myPdf = new PdfDocument();

		myPdf.isPageStreamsDeflated = isPageStreamsDeflated;

		myPdf.addFont("H1","Helvetica");
		//myPdf.addFont("H2","Helvetica-Bold"); //Arial-BoldMT
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
    	int lnPos = 733; 
    	int leftMargin = 86; 
    	int tabPos = 400; 
		//pageArr[0].placeText(leftMargin, lnPos, "CERTIFIED MAIL", "H1", 10, "L", 0);
		lnPos = 703;
		pageArr[0].placeText(leftMargin, lnPos, "Att: " + letterData.FirstNames + " " + letterData.Surname, "H1", 10, "L", 0);
		pageArr[0].placeText(tabPos, lnPos, "Date: 01 November 2018", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(tabPos, lnPos, "Enquiries: Local EFC Office", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
			lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "Dear Mr/Ms " + letterData.Surname, "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "Please note that you were erroneously overpaid on your subsidy during the October 2018 payroll ", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "run. ", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "Your subsidy amount should be R " + letterData.CorrectAmount + " and you received R " + letterData.OctPayment, "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "The overpaid amount of R " + letterData.Variance + " will be deducted from your salary with the November 2018 ", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "payroll run. ", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "Should you have any queries please contact your local EFC office. ", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "Kind regards ", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		pageArr[0].placeText(leftMargin, lnPos, "EFC Management", "H1", 10, "L", 0);
			
		
		try {
			myPdf.saveAs(letterData.filepath);
		} catch (IOException e) {
			System.out.println( "pdf_create:-->Cannot save the PDF file!" );
			e.printStackTrace();
		}

        //System.out.println( "Built the PDF!" );

    }
    
    private static void processAllFstLetters() {
    	System.out.print("Open email bridge...");
    	BridgeToOutlook outlook = new BridgeToOutlook();
    	System.out.println("Email ready.");
    	
		String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Staging;user=test;password=test";
		FstLetter thisLetter = new FstLetter();

    	String sql = new StringBuilder()
    			.append("USE Phoenix; \n")
    			.append("IF OBJECT_ID('tempdb..#TC_300_SUMMARY') IS NOT NULL \n")
    			.append("DROP TABLE #TC_300_SUMMARY; \n")
    			.append("SELECT acct_type, acct_no, SUM(amt) amt, SUM(pass_thru_amt) pass_thru_amt \n")
    			.append("INTO #TC_300_SUMMARY \n")
    			.append("FROM Phoenix.dbo.ln_history \n")
    			.append("WHERE acct_type = 'ML' \n")
    			.append("AND effective_dt >= '" + startOfPreviousMonth + "' \n")
    			.append("AND effective_dt < '" + startOfThisMonth + "' \n")
    			.append("AND tran_code = 300 \n")
    			.append("GROUP BY acct_type, acct_no; \n")
    			.append("SELECT \n")
    			.append("    TT.acct_type, \n")
    			.append("    TT.acct_no, \n")
    			.append("    rm_acct.tin, \n")
    			.append("    ln_acct.rim_no, \n")
    			.append("    COALESCE(rm_acct.first_name,'') AS first_name, \n")
    			.append("    COALESCE(rm_acct.last_name,'') AS last_name, \n")
    			.append("    COALESCE(rm_acct.preferred_name,'') AS preferred_name, \n")
    			.append("    COALESCE(rm_acct.id_value,'') AS id_value, \n")
    			.append("    COALESCE(rm_acct.sex,'') AS sex, \n")
    			.append("    COALESCE(rm_address.name_1,'') AS name_1, \n")
    			.append("    COALESCE(rm_address.address_line_1,'') AS address_line_1, \n")
    			.append("    COALESCE(rm_address.address_line_2,'') AS address_line_2, \n")
    			.append("    COALESCE(rm_address.address_line_3,'') AS address_line_3, \n")
    			.append("    COALESCE(rm_address.city,'') AS city, \n")
    			.append("    COALESCE(rm_address.zip,'') AS zip, \n")
    			.append("    COALESCE(rm_address.email_addr_1,'') AS email_addr_1, \n")
    			.append("    COALESCE(rm_address.email_addr_2,'') AS email_addr_2, \n")
    			//.append("    COALESCE(rm_address.addr_type_id,'') AS addr_type_id, \n")
    			.append("    COALESCE(ad_rm_addr_type.addr_type,'') AS addr_type, \n")
    			.append("    COALESCE(rm_fin_stmt.legal_desc,'') AS legal_desc, \n")
    			.append("    COALESCE(ad_rm_stmt_cat.category,'') AS category, \n")
    			.append("    COALESCE(ln_acct.class_code,'') AS class_code \n")
    			.append("FROM \n")
    			.append("    (SELECT \n")
    			.append("        acct_type, acct_no,  \n")
    			.append("        CASE pass_thru_amt \n")
    			.append("            WHEN 0.000000 THEN 'N' \n")
    			.append("            ELSE 'Y' \n")
    			.append("        END pass_thru_amt \n")
    			.append("    FROM #TC_300_SUMMARY) TT JOIN \n")
    			.append("    Phoenix.dbo.ln_acct ON TT.acct_no = ln_acct.acct_no AND TT.acct_type = ln_acct.acct_type JOIN \n")
    			.append("    Phoenix.dbo.rm_acct ON ln_acct.rim_no = rm_acct.rim_no JOIN \n")
    			.append("    Phoenix.dbo.rm_address ON rm_acct.rim_no = rm_address.rim_no JOIN \n")
    			.append("    Phoenix.dbo.ad_rm_addr_type ON rm_address.addr_type_id = ad_rm_addr_type.addr_type_id LEFT JOIN \n")
    			.append("    Phoenix.dbo.ln_collateral ON TT.acct_no = ln_collateral.acct_no AND TT.acct_type \n= ln_collateral.acct_type LEFT JOIN \n")
    			.append("    Phoenix.dbo.rm_fin_stmt ON ln_collateral.fin_stmt_item_id = rm_fin_stmt.fin_stmt_item_id LEFT JOIN \n")
    			.append("    Phoenix.dbo.ad_rm_stmt_cat ON ad_rm_stmt_cat.CATEGORY_ID = rm_fin_stmt.category_id \n")
    			.append("WHERE TT.pass_thru_amt = 'N' \n")
    			.append("AND ad_rm_addr_type.addr_type = 'Primary' \n")
    			.append("AND ln_acct.class_code NOT IN (105,120,920) \n")
    			//.append("AND ad_rm_stmt_cat.category = 'Sectional title' \n")//Sectional title//Dwelling Unit
    			.append("ORDER BY \n")
    			.append("    TT.acct_type, \n")
    			.append("    TT.acct_no; ").toString();
    	
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (Connection connection = DriverManager.getConnection(connectionUrl)) {
				System.out.println("Done.");

				// READ demo
				System.out.print("Press ENTER to retrieve data...");
				System.in.read();
				System.out.print("Reading data from table...");
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						thisLetter.acct_type = resultSet.getString("acct_type").trim();
						thisLetter.acct_no = resultSet.getString("acct_no").trim();
						thisLetter.tin = resultSet.getString("tin").trim();
						thisLetter.rim_no = resultSet.getString("rim_no").trim();
						thisLetter.first_name = resultSet.getString("first_name").trim();
						thisLetter.last_name = resultSet.getString("last_name").trim();
						thisLetter.preferred_name = resultSet.getString("preferred_name").trim();
						thisLetter.id_value = resultSet.getString("id_value").trim();
						thisLetter.sex = resultSet.getString("sex").trim();
						thisLetter.name_1 = resultSet.getString("name_1").trim();
						thisLetter.address_line_1 = resultSet.getString("address_line_1").trim();
						thisLetter.address_line_2 = resultSet.getString("address_line_2").trim();
						thisLetter.address_line_3 = resultSet.getString("address_line_3").trim();
						thisLetter.city = resultSet.getString("city").trim();
						thisLetter.zip = resultSet.getString("zip").trim();
						thisLetter.email_addr_1 = resultSet.getString("email_addr_1").trim();
						thisLetter.email_addr_2 = resultSet.getString("email_addr_2").trim();
						//thisLetter.addr_type_id = resultSet.getString("addr_type_id").trim();
						thisLetter.addr_type = resultSet.getString("addr_type").trim();
						thisLetter.legal_desc = resultSet.getString("legal_desc").trim();
						thisLetter.category = resultSet.getString("category").trim();
						thisLetter.class_code = resultSet.getString("class_code").trim();

						System.out.println(
								thisLetter.category + "|" + 
								thisLetter.class_code + "|" + 
								thisLetter.acct_type+thisLetter.acct_no + "|" + 
								thisLetter.tin + "|" + 
								thisLetter.rim_no + "|" + 
								thisLetter.first_name + "|" + 
								thisLetter.last_name + "|" + 
								thisLetter.preferred_name + "|" + 
								thisLetter.id_value + "|" + 
								thisLetter.sex + "|" + 
								thisLetter.name_1 + "|" + 
								thisLetter.address_line_1 + "|" + 
								thisLetter.address_line_2 + "|" + 
								thisLetter.address_line_3 + "|" + 
								thisLetter.city + "|" + 
								thisLetter.zip + "|" + 
								thisLetter.email_addr_1 + "|" + 
								thisLetter.email_addr_2 + "|" + 
								//thisLetter.addr_type_id + "|" + 
								thisLetter.addr_type + "|" + 
								thisLetter.legal_desc + "|");
						
						createFstLetter(thisLetter);
					}
				}
				connection.close();
				System.out.println("All done.");
			}
		} catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
    }
    
    private static void createFstLetter(FstLetter letterData) {
		boolean isPageStreamsDeflated = false;
		PdfDocument myPdf = new PdfDocument();
		String subFolder = letterData.acct_no.substring(letterData.acct_no.length() - 1); //Last char on the right

		myPdf.isPageStreamsDeflated = isPageStreamsDeflated;

		myPdf.addFont("H1","Helvetica");
		myPdf.addFont("H2","Helvetica-Bold"); //Arial-BoldMT
		//myPdf.addFont("H2","Arial-BoldMT"); //Arial-BoldMT
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
		placeGenericHeading(pageArr[0],letterData);
		switch (letterData.category) {
        case "Sectional title":  placeSectionalTitleBody(pageArr[0]);
        	break;
        default: placeOutsideInsuranceBody(pageArr[0]);
        	break;
		}
		
//		if (letterData.category != null)) {
//			if (letterData.category.equals("Sectional title")) {
//				placeSectionalTitleBody(pageArr[0]);
//			}
//			else {
//				placeOutsideInsuranceBody(pageArr[0]);
//			}
//		    // Do something here.
//		}
		
		try {
			myPdf.saveAs(targetFolder + subFolder + "\\" + letterData.acct_type+letterData.acct_no + postfix + ".PDF");
		} catch (IOException e) {
			System.out.println( "pdf_create:-->Cannot save the PDF file!" );
			e.printStackTrace();
		}

        //System.out.println( "Built the PDF!" );

    }
    
    private static void placeGenericHeading(PdfPage targetPage, FstLetter letterData) {
    	int lnPos = 733; 
    	int leftMargin = 86; 
    	int tabPos = 400; 
		//pageArr[0].placeText(leftMargin, lnPos, "CERTIFIED MAIL", "H1", 10, "L", 0);
		lnPos = 703;
		targetPage.placeText(leftMargin, lnPos, letterData.name_1, "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, letterData.address_line_1, "H1", 10, "L", 0);
		targetPage.placeText(tabPos, lnPos, letterDate, "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, letterData.address_line_2, "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, letterData.address_line_3, "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, letterData.city, "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, letterData.zip, "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "Dear Sir/Madam", "H1", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "CLIENT: " + letterData.name_1, "H2", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "ACCOUNT NUMBER: "+letterData.acct_type + letterData.acct_no, "H2", 10, "L", 0);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "PROPERTY DESCRIPTION: " + letterData.legal_desc, "H2", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
		targetPage.placeText(leftMargin, lnPos, "Building insurance cover over EFC loan - " + letterData.acct_type + " " + letterData.acct_no, "H2", 10, "L", 0);
		lnPos = newLine(lnPos);
		lnPos = newLine(lnPos);
    }

    private static void placeSectionalTitleBody(PdfPage targetPage) {
    	int lnPos = 487; 
    	int leftMargin = 86; 
		lnPos = newLine(lnPos);
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
    	lnPos = newLine(lnPos);
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
