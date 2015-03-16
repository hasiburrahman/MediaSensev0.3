package se.mediasense.standalone;

import se.mediasense.interfacelayer.MediaSenseApplication;
import se.mediasense.interfacelayer.MediaSensePlatform;
import se.mediasense.messages.MediaSenseMessage;
import se.mediasense.distribution.P2PCommunication;

public class BootStrap extends MediaSenseApplication {

	private static int bootport = 2000;

	public void run() 
    {
            MediaSensePlatform m = new MediaSensePlatform(this);
            ((P2PCommunication) P2PCommunication.getSharedInstance()).initBootStrap(bootport);

    }
    
    public static void main(String[] args) 
    {
    	if(args.length==0)
    	{
    		System.err.println("Bootstrap node require a port to be specified for execution, defaulting to port 2000");
        	
        	BootStrap bstrap = new BootStrap();

            bstrap.run();
    		
    	}
    	else
    	{
    	bootport = Integer.valueOf(args[0]);

    	BootStrap bstrap = new BootStrap();

        bstrap.run();
    	}

    }
	@Override
	public void handleMessage(MediaSenseMessage _msg) {
		// TODO Auto-generated method stub
		
	}

}
