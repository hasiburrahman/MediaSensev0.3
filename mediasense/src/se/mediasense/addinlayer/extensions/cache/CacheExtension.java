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

package se.mediasense.addinlayer.extensions.cache;

import java.util.HashMap;
import se.mediasense.addinlayer.extensions.Extension;
import se.mediasense.disseminationlayer.disseminationcore.DisseminationCore;
import se.mediasense.disseminationlayer.disseminationcore.GetResponseListener;
import se.mediasense.interfacelayer.MediaSensePlatform;
import se.mediasense.messages.MediaSenseMessage;


public class CacheExtension implements Extension, GetResponseListener{

	private MediaSensePlatform platform = null;
	private DisseminationCore core = null;
	
	private HashMap<String, Long> timeCache = new HashMap<String, Long>();
	private HashMap<String, String> valueCache = new HashMap<String, String>(); 
	
	private GetResponseListener savedGetResponseListener = null;
	
	private boolean runAddIn = true;
	
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
		timeCache.clear();
		valueCache.clear();		
	}

	
	public void handleMessage(MediaSenseMessage message) {
		//This add in does not handle messages		
	}

	/**
	 * This performs a get, but checks the cache beforehand.
	 * If the value exists in the cache and was retrieved within the freshness threshold, the cached value is returned instead 
	 * @param uci the UCI to get
	 * @param ip the IP to get it from
	 * @param threshold the freshness threshold in milliseconds
	 */
	public void get(String uci, String ip, int threshold) {
						
		//Get values from cache and sanity check
		Long cachedTime = timeCache.get(uci);
		String cachedValue = valueCache.get(uci);		
		if(cachedTime == null || cachedValue == null){
//			platform.get(uci, ip);
			return;
		}

		//If it is old
		if(System.currentTimeMillis() - cachedTime > threshold){
			//Make a new call
		//	platform.get(uci, ip);
		} else {
			//Otherwise, return the cached value!
//			platform.getDisseminationCore().callGetResponseListener(uci, cachedValue);
		}				
	}
	

	
	public void getResponse(String uci, String value) {
		//Cache the values!
		valueCache.put(uci, value);
		timeCache.put(uci, System.currentTimeMillis());
		
		//Make a normal call
		if(savedGetResponseListener != null){
		//	savedGetResponseListener.getResponse(uci, value);
		}				
	}

    public void getResponse(MediaSenseMessage _msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
	
	//This class will hijack the getResponses and cache them!
	private class HijackThread extends Thread{
		
		@Override
		public void run() {
			while(runAddIn){
				try {
					
					Thread.sleep(500);
				
//					if(core.getGetResponseListener() != null && core.getGetResponseListener() != CacheExtension.this){
//						savedGetResponseListener = core.getGetResponseListener();
//					}
//					core.setGetResponseListener(CacheExtension.this);//Take over the listener!

				} catch (Exception e) {
					//e.printStackTrace();					
				}
			}
		}				
	}
}
