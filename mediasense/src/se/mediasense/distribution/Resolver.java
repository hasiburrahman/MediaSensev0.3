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

import org.json.simple.JSONObject;

import net.tomp2p.futures.BaseFutureListener;
import net.tomp2p.futures.FutureDHT;
import net.tomp2p.p2p.Peer;
import net.tomp2p.peers.Number160;
import net.tomp2p.peers.PeerAddress;
import net.tomp2p.storage.Data;



public class Resolver implements BaseFutureListener<FutureDHT>, FuturePrimitive
{
	private UCI uci = null;
	private String suci=null;
	private PrimitiveListener lookuplistener = null;
	private boolean complete = false;
	private boolean resolved = false;
	public enum REASON 
		{
		   UCI_DOES_NOT_EXIST, PEER_DOES_NOT_EXIST, INTERNAL_ERROR
		 };
    private enum STATE {
				   RESOLVE_UCI, RESOLVE_PEER, COMPLETED
				 };
	private String ID = null;
	private Peer  peer = null;
	private FutureDHT futureDHT = null;
	private REASON reason = null;
	private String DHTReason = "";
	private STATE state = null;
	private Data rpeer = null;
	private Data ruci = null;
	
	
	
	public Resolver(String _uci)
	{
		suci = _uci;
		
	}

	public void startAsynchronousResolve(PrimitiveListener l)
	{
			resolveUCI();
		    lookuplistener = l;
	}
	
	
	private void resolveUCI()
	{
		    state = STATE.RESOLVE_UCI;		
			futureDHT = P2PCommunication.peer.get(Number160.createHash(suci)).start();
		    futureDHT.addListener(this);		
	}
	
	

	private void resolvePeer(FutureDHT future)
	{
		 try 
		 {
		    state = STATE.RESOLVE_PEER;
		    if(future.isFailed())
		    {
		    	reason = REASON.UCI_DOES_NOT_EXIST;
				complete=(futureDHT.isCompleted());
				resolved =false;
				DHTReason = futureDHT.getFailedReason();		    	
				lookuplistener.handleCompleted(this);

		    }
		    else
		    {
			    ruci = future.getData();
			    JSONObject obj  = (JSONObject)ruci.getObject();
				FutureDHT futureDHT = P2PCommunication.peer.get(Number160.createHash((String)obj.get("host"))).start();
			    futureDHT.addListener(this);
		    }
		 } catch (Exception e) 
		 {
			    reason = REASON.INTERNAL_ERROR;
				complete=(futureDHT.isCompleted());
				resolved =false;
				DHTReason = futureDHT.getFailedReason();
			    uci = new UCI(suci, null);
			    lookuplistener.handleCompleted(this);	
		 }
		    
				
	}
	
	
	
	
	private void completeAsynchronousResolve(FutureDHT future)
	{
		 try 
		 {
		    state = STATE.COMPLETED;
		    if(future.isFailed())
		    {
		    	reason = REASON.PEER_DOES_NOT_EXIST;
				complete=(futureDHT.isCompleted());
				resolved =false;
				DHTReason = futureDHT.getFailedReason();
			    uci = new UCI(ruci.getObject().toString(), null);
			    lookuplistener.handleCompleted(this);
	
		    }
		    else
		    {
				complete=(futureDHT.isCompleted());
				rpeer = future.getData();
				resolved =true;
				DHTReason = futureDHT.getFailedReason();
			    uci = new UCI(ruci.getObject().toString(), new P2PMediaSenseHost(((PeerAddress)futureDHT.getData().getObject()).getID().toString(), (PeerAddress)futureDHT.getData().getObject()));
				lookuplistener.handleCompleted(this);
		    }		
		
		 } catch (Exception e) 
		 {
		    	reason = REASON.INTERNAL_ERROR;
				complete=(futureDHT.isCompleted());
				resolved =false;
				DHTReason = futureDHT.getFailedReason();
			    uci = new UCI(suci, null);
			    lookuplistener.handleCompleted(this);	
		 }
	}
	
	
	public Resolver startSynchronousResolve()
	{
		PeerAddress peeraddress = null;
		Data data = null;
	
		try {
	    FutureDHT futureDHT = P2PCommunication.peer.get(Number160.createHash(suci)).start();
	    futureDHT.awaitUninterruptibly();
	    data = futureDHT.getData(); 
	    JSONObject obj = (JSONObject)data.getObject();
	    futureDHT = P2PCommunication.peer.get(Number160.createHash((String)obj.get("host"))).start();
	    futureDHT.awaitUninterruptibly();	       
	    peeraddress = (PeerAddress)futureDHT.getData().getObject();
	    uci = new UCI(data.getObject().toString(), new P2PMediaSenseHost(peeraddress.getID().toString(), peeraddress));
	    
	    
		} catch (Exception e) 
		{		    	

	    	reason = REASON.INTERNAL_ERROR;
			complete=false;
			resolved =false;
		    uci = new UCI(suci, null);
			return this;

		}
	
		return this;
	}


	@Override
	public void exceptionCaught(Throwable t) throws Exception 
	{
    	reason = REASON.INTERNAL_ERROR;
		complete=(futureDHT.isCompleted());
		resolved =false;
		DHTReason = futureDHT.getFailedReason();
	    uci = new UCI(suci, null);
	    lookuplistener.handleCompleted(this);
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
    		completeAsynchronousResolve(future);
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
		return resolved;
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
       this.lookuplistener = lookuplistener;		
	}

	public UCI getUci() {
		return uci;
	}

	



	
}
