package com.ey.za.pdf_create;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

public class BridgeToOutlook {
	ActiveXComponent axOutlook = new ActiveXComponent("Outlook.Application");
	
	void sendEmail(String emailAddress, String emailSubject, String emailBody, String emailAttachment) {
		try {
			 Dispatch mailItem = Dispatch.call(axOutlook, "CreateItem", 0).getDispatch();   
			 Dispatch recipients = Dispatch.call(mailItem, "Recipients").getDispatch(); 
			 Dispatch.call(recipients, "Add" , emailAddress);
			//Dispatch.put(mailItem, "CC", "myself@xxx.com");
			 //------------------------------------------------------------------------
			 Dispatch attachments = Dispatch.call(mailItem, "Attachments").getDispatch();
			 
			 Dispatch.call(attachments, "Add" , emailAttachment);
			 //------------------------------------------------------------------------
			 Dispatch.put(mailItem, "Subject", emailSubject);    
			 //Dispatch.put(mailItem, "HTMLBody", emailBody);
			 Dispatch.put(mailItem, "Body", emailBody);
			 
			 //System.out.println("Sending email message: " + emailSubject);
			 Dispatch.call(mailItem, "Send"); 
			 
		} catch (Exception e) {
		      e.printStackTrace();
		      System.out.println("Unable to send email message: " + emailSubject + "to " + emailAddress);
	    } finally {
			//axOutlook.invoke("Quit", new Variant[] {});
		}
	}
}
