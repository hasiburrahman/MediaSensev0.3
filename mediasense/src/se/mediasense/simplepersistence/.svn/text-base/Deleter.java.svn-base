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
 */
package se.mediasense.simplepersistence;

import java.io.IOException;
import java.io.Serializable;

import org.json.simple.JSONObject;

import net.tomp2p.futures.BaseFutureListener;
import net.tomp2p.futures.FutureDHT;
import net.tomp2p.p2p.Peer;
import net.tomp2p.peers.Number160;
import net.tomp2p.storage.Data;
import se.mediasense.distribution.FuturePrimitive;
import se.mediasense.distribution.P2PCommunication;
import se.mediasense.distribution.PrimitiveListener;
import se.mediasense.distribution.Updater.REASON;
import se.mediasense.util.MediaSenseConstants;


public class Deleter implements BaseFutureListener<FutureDHT>, FuturePrimitive
{
	private String datakey = null;
	private Serializable data = null;
	private PrimitiveListener lookuplistener = null;
	private boolean complete = false;
	private boolean registered = false;
	public enum REASON {
		   DUPLICATE, FORMATTING_ERROR, SHORT, LONG, INTERNAL_ERROR
		 };
    private enum STATE {
				   DUPLICATE_CONTROL, REGISTRATION_COMPLETION
				 };
	private String ID = null;
	private Peer  peer = null;
	private FutureDHT futureDHT = null;
	private REASON reason = null;
	private String DHTReason = "";
	private STATE state = null;
	@SuppressWarnings("unused")
	private boolean forceregister = false;
	
	
	
	public Deleter(String _key, Serializable _data)
	{
		datakey = _key;
		data = _data;
		
	}

	public void startAsynchronousStore(PrimitiveListener l)
	{
		
		    state = STATE.DUPLICATE_CONTROL;		
			futureDHT = P2PCommunication.peer.get(Number160.createHash(datakey)).start();
		    futureDHT.addListener(this);
		    lookuplistener = l;

	}

	@SuppressWarnings("unchecked")
	private void completeAsynchronousStore(FutureDHT future)
	{
	    state = STATE.REGISTRATION_COMPLETION;		
		Data _data;
		try {
	    Number160 key = Number160.createHash(datakey);
		JSONObject obj=new JSONObject();
		obj.put("key", key.toString());
		obj.put("uci", datakey);
		obj.put("mediasensehost", MediaSenseConstants.LOCALHOST.getHostID());
		obj.put("data", data);
		_data = new Data(obj);
	    if(future.isFailed())
	    {
	    
		    futureDHT = P2PCommunication.peer.put(key).setPutIfAbsent(true).setData(_data).start();
			complete=(futureDHT.isCompleted());
			registered =futureDHT.isSuccess();
			peer = P2PCommunication.peer;
			DHTReason = futureDHT.getFailedReason();
			lookuplistener.handleCompleted(this);

	    }
	    
	    else
	    {
			complete=(futureDHT.isCompleted());
			registered =false;
			peer = P2PCommunication.peer;
			DHTReason = futureDHT.getFailedReason();
			reason = REASON.DUPLICATE;
			lookuplistener.handleCompleted(this);
	    	
	    }
	    
		} catch (IOException e) {
			
	    	reason = REASON.INTERNAL_ERROR;
			complete=(futureDHT.isCompleted());
			registered =false;
		    lookuplistener.handleCompleted(this);			}	   		
		
	    
	}
	
	
	@SuppressWarnings("unchecked")
	public Deleter startSynchronousRegistration()
	{
		Data _data;
		try {
	    Number160 key = Number160.createHash(datakey.toString());
		JSONObject obj=new JSONObject();
		obj.put("key", key.toString());
		obj.put("uci", datakey);
		obj.put("host", MediaSenseConstants.getProperty("mediasensehostid"));
		_data = new Data(obj);
	    futureDHT = P2PCommunication.peer.get(Number160.createHash(datakey)).start();
	    futureDHT.awaitUninterruptibly();
	    if(futureDHT.isFailed())
	    {
		    futureDHT = P2PCommunication.peer.put(key).setPutIfAbsent(true).setData(_data).start();
		    futureDHT.awaitUninterruptibly();  
			complete=(futureDHT.isCompleted());
			registered =futureDHT.isSuccess();
			peer = P2PCommunication.peer;
			DHTReason = futureDHT.getFailedReason();
	    }
	    else
	    {
			complete=(futureDHT.isCompleted());
			registered =false;
			peer = P2PCommunication.peer;
			DHTReason = futureDHT.getFailedReason();
			reason = REASON.DUPLICATE;
	    	
	    }
	    
		} catch (Exception e) {
			e.printStackTrace();
	    	reason = REASON.INTERNAL_ERROR;
			complete=(futureDHT.isCompleted());
			registered =false;
		    return this;
		}	    
	    return this;
	}


	@Override
	public void exceptionCaught(Throwable t) throws Exception 
	{
    	reason = REASON.INTERNAL_ERROR;
		complete=(futureDHT.isCompleted());
		registered =false;
	    lookuplistener.handleCompleted(this);
	}

	@Override
	public void operationComplete(FutureDHT future) throws Exception 
	{
    	if(state == STATE.DUPLICATE_CONTROL)
    	{
    		completeAsynchronousStore(future);
    	}
    	else if(state == STATE.REGISTRATION_COMPLETION)
    	{
    		
    	}

	}

	/* (non-Javadoc)
	 * @see se.mediasense.overlaymanager.pgrid.FutureTask#isComplete()
	 */
	@Override
	public boolean isComplete() {
		return complete;
	}


	/* (non-Javadoc)
	 * @see se.mediasense.overlaymanager.pgrid.FutureTask#isRegistered()
	 */
	@Override
	public boolean isSuccess() {
		return registered;
	}


	/* (non-Javadoc)
	 * @see se.mediasense.overlaymanager.pgrid.FutureTask#getID()
	 */
	@Override
	public String getID() {
		return ID;
	}


	/* (non-Javadoc)
	 * @see se.mediasense.overlaymanager.pgrid.FutureTask#getPeer()
	 */
	@Override
	public Peer getPeer() {
		return peer;
	}


	/* (non-Javadoc)
	 * @see se.mediasense.overlaymanager.pgrid.FutureTask#getFutureDHT()
	 */
	
	public FutureDHT getFutureDHT() {
		return futureDHT;
	}

	/* (non-Javadoc)
	 * @see se.mediasense.overlaymanager.pgrid.FutureTask#getDHTReason()
	 */
	@Override
	public String getDHTReason() {
		return DHTReason;
	}

	public String getReason() {
		return reason.toString();
	}

	public void setLookuplistener(PrimitiveListener lookuplistener) {
		this.lookuplistener = lookuplistener;
	}
	

	
}



	
	

	
	

    

