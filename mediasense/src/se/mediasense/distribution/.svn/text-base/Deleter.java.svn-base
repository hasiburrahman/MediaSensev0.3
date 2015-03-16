package se.mediasense.distribution;

import java.io.IOException;

import net.tomp2p.futures.BaseFutureListener;
import net.tomp2p.futures.FutureDHT;
import net.tomp2p.p2p.Peer;
import net.tomp2p.peers.Number160;
import net.tomp2p.storage.Data;

public class Deleter implements BaseFutureListener<FutureDHT>, FuturePrimitive
{
	
	private UCI uci = null;
	private PrimitiveListener lookuplistener = null;
	private boolean complete = false;
	private boolean deleted = false;
	public enum REASON {
		   DOES_NOT_EXIST, DELETE_FAILED
		 };
	private String ID = null;
	private Peer  peer = null;
	private FutureDHT futureDHT = null;
	private REASON reason = null;
	private String DHTReason = "";
	
	
	
	public Deleter(UCI _uci)
	{
		uci = _uci;
		
	}

	public void startAsynchronousDeletion(PrimitiveListener l)
	{
		
		try 
		{
			Data data = new Data(uci.toString());
		    Number160 key = Number160.createHash(uci.toString());
		    futureDHT = P2PCommunication.peer.remove(Number160.createHash(uci.toString())).start();
			futureDHT.addListener(this);
		} catch (IOException e) {
			e.printStackTrace();
		}	   		
		
	    
	}
	
	public Deleter startSynchronousDeletion()
	{
		try {
			Data data = new Data(uci.toString());
		    futureDHT = P2PCommunication.peer.remove(Number160.createHash(uci.toString())).start();
		    futureDHT.awaitUninterruptibly();
	    	complete = (futureDHT.isCompleted());
	    	deleted =futureDHT.isSuccess();
	    	peer = P2PCommunication.peer;
	    	DHTReason = futureDHT.getFailedReason();
		    
		} catch (IOException e) {
			e.printStackTrace();
		}


	  return this;  
	}


	@Override
	public void exceptionCaught(Throwable t) throws Exception 
	{
		
	}

	@Override
	public void operationComplete(FutureDHT future) throws Exception 
	{
    	complete = (futureDHT.isCompleted());
    	deleted =futureDHT.isSuccess();
    	peer = P2PCommunication.peer;
    	DHTReason = futureDHT.getFailedReason();
		lookuplistener.handleCompleted(this);

	}

	public boolean isComplete() {
		return complete;
	}


	public boolean isSuccessful() {
		return deleted;
	}


	public String getID() {
		return ID;
	}


	public Peer getPeer() {
		return peer;
	}


	public String getDHTReason() {
		return DHTReason;
	}

	@Override
	public boolean isSuccess() {
		return deleted;
	}

	@Override
	public String getReason() {
		return reason.toString();
	}

	public void setLookuplistener(PrimitiveListener lookuplistener) {
		this.lookuplistener = lookuplistener;
	}


	

}
