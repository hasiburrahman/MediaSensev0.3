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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import net.tomp2p.futures.BaseFutureListener;
import net.tomp2p.futures.FutureDHT;
import net.tomp2p.p2p.Peer;
import net.tomp2p.peers.Number160;
import net.tomp2p.peers.PeerAddress;
import net.tomp2p.storage.Data;

import org.json.simple.JSONObject;

import se.mediasense.util.ThreadExecutor;

public class Getter
implements BaseFutureListener<FutureDHT>, FuturePrimitive
{
	private UCI uci = null;
	private String suci=null;
	private PrimitiveListener getListener = null;
	private boolean complete = false;
	private boolean gotten = false;
	public enum REASON 
	{
		   UCI_DOES_NOT_EXIST, PEER_DOES_NOT_EXIST, INTERNAL_ERROR
	};
    private enum STATE 
    {
			RESOLVE_UCI, RESOLVE_PEER, GETTING, COMPLETED
	};
	private String ID = null;
	private Peer  peer = null;
	private FutureDHT futureDHT = null;
	private REASON reason = null;
	private String DHTReason = "";
	private STATE state = null;
	private Data rpeer = null;
	private JSONObject ruci = null;
	private Map<String, Serializable> data = null;
	
	
	
	public Getter(String _uci)
	{
		suci = _uci;
		
	}

	public void startAsynchronousGet(PrimitiveListener l)
	{
			resolveUCI();
		    getListener = l;
	}
	
	
	
	private void resolveUCI()
	{
		    state = STATE.RESOLVE_UCI;		
			futureDHT = P2PCommunication.peer.get(Number160.createHash(suci)).start();
		    futureDHT.addListener(this);		
	}
	
	
	

	private void resolvePeer(FutureDHT future)
	{
		try {
		    state = STATE.RESOLVE_PEER;
		    if(future.isFailed())
		    {
		    	reason = REASON.UCI_DOES_NOT_EXIST;
				complete=(futureDHT.isCompleted());
				gotten =false;
				DHTReason = futureDHT.getFailedReason();
				setData(new HashMap<String, Serializable>());
				getListener.handleCompleted(this);

		    }
		    else
		    {
			    ruci = (JSONObject)future.getData().getObject();		    
			    futureDHT = P2PCommunication.peer.get(Number160.createHash((String)ruci.get("host"))).start();
			    futureDHT.addListener(this);
		    }
		} catch (Exception e) 
		{
	    	reason = REASON.INTERNAL_ERROR;
			complete=(futureDHT.isCompleted());
			gotten =false;
			setData(new HashMap<String, Serializable>());
			DHTReason = futureDHT.getFailedReason();		    	
			getListener.handleCompleted(this);
		} 				
	}
	
	private void getResource(FutureDHT future)
	{
		try {    
			
			state = STATE.GETTING;
		    if(future.isFailed())
		    {
		    	reason = REASON.PEER_DOES_NOT_EXIST;
				complete=(futureDHT.isCompleted());
				gotten =false;
				setData(new HashMap<String, Serializable>());
				DHTReason = futureDHT.getFailedReason();		    	
				getListener.handleCompleted(this);

		    }
		    else
		    {
			    rpeer = future.getData();
			    GetterImplementation g = new GetterImplementation(this);
			    g.addUCI(new UCI((String)ruci.get("host"), new P2PMediaSenseHost(((PeerAddress)rpeer.getObject()).getID().toString(), (PeerAddress)futureDHT.getData().getObject())));
			    ThreadExecutor.SharedInstance().submit(g);
		    }
		} catch (Exception e) 
		{
	    	reason = REASON.INTERNAL_ERROR;
			complete=(futureDHT.isCompleted());
			gotten =false;
			setData(new HashMap<String, Serializable>());
			DHTReason = futureDHT.getFailedReason();		    	
			getListener.handleCompleted(this);
		} 
	}	
	
	
	protected void completeAsynchronousGet(Map<String, Serializable> mp)
	{
		    state = STATE.COMPLETED;
			complete=true;
			gotten =true;
			DHTReason = futureDHT.getFailedReason();
			setData(mp);
		    getListener.handleCompleted(this);		    
		 
	}
	
	
	@SuppressWarnings("unchecked")
	public Getter startSynchronousGet()
	{
		PeerAddress peeraddress = null;
		Data data = null;
	
		try {
	    FutureDHT futureDHT = P2PCommunication.peer.get(Number160.createHash(suci)).start();
	    futureDHT.awaitUninterruptibly();
	    data = futureDHT.getData(); 
	    ruci = (JSONObject)data.getObject();
	    
	    futureDHT = P2PCommunication.peer.get(Number160.createHash((String)ruci.get("host"))).start();
	    futureDHT.awaitUninterruptibly();
	    
	    peeraddress = (PeerAddress)futureDHT.getData().getObject();
	    
	    uci = new UCI(suci, new P2PMediaSenseHost(peeraddress.getID().toString(), peeraddress));
	    
	    GetterImplementation g = new GetterImplementation(this);
	    g.addUCI(uci);
	    @SuppressWarnings("rawtypes")
		Future f = ThreadExecutor.SharedInstance().submit(g);
	    this.setData(((Map<String, Serializable>) f.get()));
	    
		} catch (Exception e) 
		{
	    	reason = REASON.INTERNAL_ERROR;
			complete=(futureDHT.isCompleted());
			gotten =false;
			this.setData(new HashMap<String, Serializable>());
			DHTReason = futureDHT.getFailedReason();
			return this;

		}
	
		return this;
	}


	@Override
	public void exceptionCaught(Throwable t) throws Exception 
	{
    	reason = REASON.INTERNAL_ERROR;
		complete=(futureDHT.isCompleted());
		gotten =false;
		DHTReason = futureDHT.getFailedReason();
	    uci = new UCI(suci, null);
	    getListener.handleCompleted(this);
	}

	@Override
	public void operationComplete(FutureDHT future) throws Exception 
	{
    	if(state == STATE.RESOLVE_UCI)
    	{
    		resolvePeer(future);
    	}
    	else if(state == STATE.RESOLVE_PEER)
    	{
    		getResource(future);
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
		return gotten;
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

	@Override
	public void setLookuplistener(PrimitiveListener lookuplistener) {
       this.getListener = lookuplistener;		
	}

	public Map<String, Serializable> getData() {
		return data;
	}

	private void setData(Map<String, Serializable> data) {
		this.data = data;
	}


	
}
