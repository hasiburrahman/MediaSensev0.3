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
package se.mediasense.distribution;

import java.io.IOException;

import org.json.simple.JSONObject;

import net.tomp2p.futures.BaseFutureListener;
import net.tomp2p.futures.FutureDHT;
import net.tomp2p.p2p.Peer;
import net.tomp2p.peers.Number160;
import net.tomp2p.storage.Data;
import se.mediasense.distribution.Resolver.REASON;


public class Updater implements BaseFutureListener<FutureDHT>, FuturePrimitive
{
	private String uci = null;
	private PrimitiveListener lookuplistener = null;
	private boolean complete = false;
	private boolean updated = false;
	public enum REASON 
	{
		DOES_NOT_EXIST, FORMATTING_ERROR, SHORT, LONG, INTERNAL_ERROR
	};
    private enum STATE 
    {
    	EXIST_CONTROL, UPDATE_COMPLETION
	};
	private String ID = null;
	private Peer  peer = null;
	private FutureDHT futureDHT = null;
	private REASON reason = null;
	private String DHTReason = "";
	private STATE state = null;
	private boolean forceupdate = false;
	private String hostid = null;
	
	
	
	public Updater(String _uci)
	{
		uci = _uci;
		
	}

	public void startAsynchronousUpdate(PrimitiveListener l)
	{
		
		    state = STATE.EXIST_CONTROL;		
			futureDHT = P2PCommunication.peer.get(Number160.createHash(uci)).start();
		    futureDHT.addListener(this);
		    lookuplistener = l;

	}

	private void completeAsynchronousUpdate(FutureDHT future)
	{
	    state = STATE.UPDATE_COMPLETION;		
		Data data = null;;
		try {
		JSONObject obj = (JSONObject)future.getData().getObject();
		obj.put("hostid", hostid);
	    Number160 key = Number160.createHash(uci);
	    if(future.isFailed())
	    {
	    
			complete=futureDHT.isCompleted();
			updated =false;
			DHTReason = futureDHT.getFailedReason();
			lookuplistener.handleCompleted(this);
			reason = REASON.DOES_NOT_EXIST;

	    }
	    else
	    {
		    data = new Data(obj);
	    	futureDHT = P2PCommunication.peer.put(key).setData(data).start();			
	    	complete=futureDHT.isCompleted();
			updated =futureDHT.isSuccess();
			peer = P2PCommunication.peer;
			DHTReason = futureDHT.getFailedReason();
			lookuplistener.handleCompleted(this);
	    	
	    }
	    
		} catch (Exception e) 
		{
	    	reason = REASON.INTERNAL_ERROR;
			complete=(futureDHT.isCompleted());
			updated =false;
		    lookuplistener.handleCompleted(this);			
		}	   		
	    
	}
	
	
	public Updater startSynchronousUpdate()
	{
		Data data;
		try {
		data = new Data(uci.toString());
	    Number160 key = Number160.createHash(uci.toString());
	    futureDHT = P2PCommunication.peer.get(Number160.createHash(uci.toString())).start();
	    futureDHT.awaitUninterruptibly();
	    if(futureDHT.isFailed())
	    {
	    
			complete=futureDHT.isCompleted();
			updated =false;
			DHTReason = futureDHT.getFailedReason();
			lookuplistener.handleCompleted(this);
			reason = REASON.DOES_NOT_EXIST;		    
	    	return this;
	    }
	    else
	    {
		    futureDHT = P2PCommunication.peer.put(key).setData(data).start();			
	    	complete=futureDHT.isCompleted();
			updated =futureDHT.isSuccess();
			peer = P2PCommunication.peer;
			DHTReason = futureDHT.getFailedReason();
			return this;	    	
	    }
	    
		} catch (IOException e) {
	    	reason = REASON.INTERNAL_ERROR;
			complete=(futureDHT.isCompleted());
			DHTReason = futureDHT.getFailedReason();
			updated =false;
		    return this;
		    }	    
	}


	@Override
	public void exceptionCaught(Throwable t) throws Exception 
	{
    	reason = REASON.INTERNAL_ERROR;
		complete=(futureDHT.isCompleted());
		updated =false;
	    lookuplistener.handleCompleted(this);
	}

	@Override
	public void operationComplete(FutureDHT future) throws Exception 
	{
    	if(state == STATE.EXIST_CONTROL)
    	{
    		this.completeAsynchronousUpdate(future);
    	}
    	else if(state == STATE.UPDATE_COMPLETION)
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
		return updated;
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


	public void updateHostID(String hostid)
	{
		this.hostid=hostid;
	}
	
}



	
	

	
	

    

