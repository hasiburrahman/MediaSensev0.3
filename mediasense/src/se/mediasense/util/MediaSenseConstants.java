package se.mediasense.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import se.mediasense.disseminationlayer.communication.Communication;
import se.mediasense.disseminationlayer.communication.MediaSenseHost;
import se.mediasense.disseminationlayer.disseminationcore.DisseminationCore;
import se.mediasense.disseminationlayer.lookupservice.LookupService;
import se.mediasense.distribution.P2PCommunication;
import se.mediasense.distribution.P2PLookupService;

public class MediaSenseConstants
{
    public static final Communication COMMUNICATION = P2PCommunication.getSharedInstance();    
    public static final DisseminationCore DISSEMINATIONCORE = DisseminationCore.getSharedInstance();  
    public static final LookupService LOOKUPSERVICE = P2PLookupService.getSharedInstance();
    public static MediaSenseHost LOCALHOST = P2PCommunication.getSharedInstance().getLocalHost();
    public static final String rootdir = "system";
	private static Properties MEDIASENSEPROPS = new Properties();
	private static File theFile = new File(MediaSenseConstants.rootdir + System.getProperty("file.separator") + "mediasense.ini");
	
	public static void loadProps()
	{
	        File theDir = new File(MediaSenseConstants.rootdir);
	        if (!theDir.exists())
	        {
	          theDir.mkdir();           
	        }
	        	        
	        if (!theFile.exists())
	        {
	        	MEDIASENSEPROPS = new Properties();
	        	
	        	MEDIASENSEPROPS.setProperty("mediasensehostid", MediaSenseID.generateID().toUpperCase()+MediaSenseID.generateID().toUpperCase());
	        	
	        	MEDIASENSEPROPS.setProperty("overlayreplicationfactor", "3");

	        	save();
           
	        }
	        else
	        {
	        try {
				MEDIASENSEPROPS.load(new FileInputStream(theFile));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        }
		
	}
	
	private static void save()
	{
    	
    	try {
			MEDIASENSEPROPS.store(new FileOutputStream(theFile), null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void setProperty(String property, String value)
	{
		
		MEDIASENSEPROPS.setProperty(property, value);
		
		save();
		
	}
	
	public static String getProperty(String property)
	{
		
		return MEDIASENSEPROPS.getProperty(property);
		
		
	}


}
