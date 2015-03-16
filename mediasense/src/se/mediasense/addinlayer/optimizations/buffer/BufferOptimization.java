/*
 * This file is part of The MediaSense Platform - http://www.mediasense.se.
 *
 * The MediaSense Platform is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The MediaSense Platform is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with The MediaSense Platform.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package se.mediasense.addinlayer.optimizations.buffer;

import java.util.HashMap;
import se.mediasense.addinlayer.optimizations.Optimization;
import se.mediasense.disseminationlayer.disseminationcore.DisseminationCore;
import se.mediasense.disseminationlayer.disseminationcore.GetEventListener;
import se.mediasense.interfacelayer.MediaSensePlatform;
import se.mediasense.messages.MediaSenseMessage;

public class BufferOptimization implements Optimization, GetEventListener{

	MediaSensePlatform platform = null;		
	DisseminationCore core = null;
	HashMap<String, String> buffer = new HashMap<String, String>();
	
	boolean runAddIn = true;
		
	private GetEventListener savedGetEventListener = null;
	
	@Override
	public void loadAddIn(MediaSensePlatform _platform) {
		platform = _platform;	
		core = platform.getDisseminationCore();
	}

	@Override
	public void startAddIn() {		
		runAddIn = true;
		Thread t = new HijackThread();
		t.start();
	}

	@Override
	public void stopAddIn() {
		runAddIn = false;		
	}

	@Override
	public void unloadAddIn() {
		buffer.clear();
	}

	
	public void handleMessage(MediaSenseMessage message) {
		//No messages related to this add in	
	}
	
	/**
	 * This function will buffer a value for a sensor.
	 * It will then protect the sensors from hammering, etc.
	 * @param uci the UCI related to the value
	 * @param value the actual value
	 */
	public void bufferContextValue(String uci, String value){		
		buffer.put(uci, value);
	}
		
	@Override
	public void getEvent(String source, String uci) {
		
		//Get stored value
		String value = buffer.get(uci);
		
		if(value != null){
			//If exists, notify, 
//			platform.notify(source, uci, value);
		} else {
			//Run a normal event from the saved listener
			if(savedGetEventListener != null){
				savedGetEventListener.getEvent(source, uci);				
			}
		}
	}
	
	//This thread hijacks the getEvents and takes care of them
	private class HijackThread extends Thread {
		
		@Override
		public void run() {			
			while(runAddIn){
				try {
					
					Thread.sleep(500);
				
//					if(core.getGetEventListener() != null && core.getGetEventListener() != BufferOptimization.this){
//						savedGetEventListener = core.getGetEventListener();
//					}
//					core.setGetEventListener(BufferOptimization.this);//Take over the listener!

				} catch (Exception e) {
					//e.printStackTrace();					
				}
			}
		}
	}
}
