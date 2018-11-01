package com.ey.za.pdf_create;

public class FstLetter {
	/* This class is used to save all the data fields required to build the FST Letter
        [acct_type] [char](3) NOT NULL,
        [acct_no] [char](12) NOT NULL,
        [tin] [char](15) NULL,
        [rim_no] [int] NOT NULL,
        [first_name] [varchar](40) NULL,
        [last_name] [varchar](40) NOT NULL,
        [preferred_name] [varchar](40) NULL,
        [id_value] [varchar](25) NULL,
        [sex] [char](1) NOT NULL,
        [name_1] [varchar](125) NOT NULL,
        [address_line_1] [varchar](40) NULL,
        [address_line_2] [varchar](40) NULL,
        [address_line_3] [varchar](40) NULL,
        [city] [varchar](40) NULL,
        [zip] [varchar](10) NULL,
        [email_addr_1] [varchar](40) NULL,
        [email_addr_2] [varchar](40) NULL,
        [addr_type_id] [smallint] NOT NULL,
        [addr_type] [varchar](15) NOT NULL,
        [legal_desc] [varchar](50) NULL,
        [description] [varchar](30) NULL,
        [category] [varchar](15) NULL,
        [class_code] [smallint] NOT NULL
*/
	
	String acct_type = "";
	String acct_no = "";
	String tin = "";
	String rim_no = "";
	String first_name = "";
	String last_name = "";
	String preferred_name = "";
	String id_value = "";
	String sex = "";
	String name_1 = "";
	String address_line_1 = "";
	String address_line_2 = "";
	String address_line_3 = "";
	String city = "";
	String zip = "";
	String email_addr_1 = "";
	String email_addr_2 = "";
	//String addr_type_id = "";
	String addr_type = "";
	String legal_desc = "";
	String description = "";
	String category = "";
	String class_code = "";
	String filepath = "";

	FstLetter() {
		
	}

}
