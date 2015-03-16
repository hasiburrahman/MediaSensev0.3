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
package se.mediasense.distribution;

import java.io.IOException;

import net.tomp2p.futures.BaseFutureAdapter;
import net.tomp2p.futures.FutureDHT;
import net.tomp2p.peers.Number160;
import net.tomp2p.peers.PeerAddress;
import net.tomp2p.storage.Data;
import se.mediasense.disseminationlayer.disseminationcore.DisseminationCore;
import se.mediasense.disseminationlayer.lookupservice.LookupService;
import se.mediasense.messages.MediaSenseListener;
import se.mediasense.messages.MediaSenseMessage;
import se.mediasense.util.LookupException;
import se.mediasense.util.MediaSenseConstants;

public final class P2PLookupService implements LookupService, MediaSenseListener, Runnable
{
	private final P2PCommunication communication = (P2PCommunication)MediaSenseConstants.COMMUNICATION;
    private final DisseminationCore disseminationcore = MediaSenseConstants.DISSEMINATIONCORE;
    public static final LookupService SHARED_INSTANCE = new P2PLookupService();

    protected P2PLookupService()
    {
    }
    
    public static LookupService getSharedInstance()
    {
        return SHARED_INSTANCE;
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleMessage(MediaSenseMessage _msg) {
		// TODO Auto-generated method stub
		
	}

	public UCI resolveAsync(String uci)
	{
		UCI Uci = null;
		PeerAddress peeraddress = null;
		Data data = null;
	
		try {
	    FutureDHT futureDHT = P2PCommunication.peer.get(Number160.createHash(uci)).start();
	    futureDHT.awaitUninterruptibly();
	    data = futureDHT.getData();
	    
	    futureDHT = P2PCommunication.peer.get(data.getPeerId()).start();
	    futureDHT.awaitUninterruptibly();	       
	    peeraddress = (PeerAddress)futureDHT.getData().getObject();
	    
	    Uci = new UCI(data.getObject().toString(), new P2PMediaSenseHost(peeraddress.getID().toString(), peeraddress));
	    
		} catch (Exception e) 
		{
		    Uci = new UCI(uci, null);

		}
	
	
	return Uci;
}

	public UCI resolveSync(String uci)
	{
		UCI Uci = null;
		PeerAddress peeraddress = null;
		Data data = null;
	
		try {
	    FutureDHT futureDHT = P2PCommunication.peer.get(Number160.createHash(uci)).start();
	    futureDHT.awaitUninterruptibly();
	    data = futureDHT.getData();
	    
	    futureDHT = P2PCommunication.peer.get(data.getPeerId()).start();
	    futureDHT.awaitUninterruptibly();	       
	    peeraddress = (PeerAddress)futureDHT.getData().getObject();
	    
	    Uci = new UCI(data.getObject().toString(), new P2PMediaSenseHost(peeraddress.getID().toString(), peeraddress));
	    
		} catch (Exception e) 
		{
		    e.printStackTrace();
			Uci = new UCI(uci, null);

		}
	
	
	return Uci;
}

	
	public void registerAsync(UCI uci) throws LookupException 
	{
		try {
				Data data;
				data = new Data(uci.toString());
			    Number160 key = Number160.createHash(uci.toString());
			    FutureDHT futureDHT = P2PCommunication.peer.put(key).setPutIfAbsent(true).setData(data).start();
			    futureDHT.addListener(new BaseFutureAdapter<FutureDHT>() 
			    {
		                 public void operationComplete(FutureDHT f) throws Exception 
		                 {
		                  //TODO Return this to the Async listener
		                 }
		        });
		
			} catch (IOException e) {

			}

	}
	
	public void registerSync(UCI uci) throws LookupException 
	{
	try {
			Data data;
			data = new Data(uci.toString());
		    Number160 key = Number160.createHash(uci.toString());
		    FutureDHT futureDHT = P2PCommunication.peer.put(key).setPutIfAbsent(true).setData(data).start();
		    if(!(futureDHT.awaitUninterruptibly(10000)))
		    {
		    	throw new LookupException(uci, futureDHT.getFailedReason().toString());
		    }
		    
		} catch (IOException e) {

		}

		
	}
	
	
	public void deleteAsync(UCI uci) throws LookupException 
	{
		try {
				Data data;
				data = new Data(uci.toString());
			    Number160 key = Number160.createHash(uci.toString());
			    FutureDHT futureDHT = P2PCommunication.peer.remove(key).start();
			    futureDHT.addListener(new BaseFutureAdapter<FutureDHT>() 
			    {
		                 public void operationComplete(FutureDHT f) throws Exception 
		                 {
		                  //TODO Return this to the Async listener
		                 }
		        });
		
			} catch (IOException e) {

			}

	}
	
	public void deleteSync(UCI uci) throws LookupException 
	{
	try {
			Data data;
			data = new Data(uci.toString());
		    Number160 key = Number160.createHash(uci.toString());
		    FutureDHT futureDHT = P2PCommunication.peer.remove(key).start();
		    if(!(futureDHT.awaitUninterruptibly(10000)))
		    {
		    	throw new LookupException(uci, futureDHT.getFailedReason().toString());
		    }
		    
		} catch (IOException e) {

		}

		
	}
	
	
	public void updateAsync(UCI uci) throws LookupException 
	{
		try {
				Data data;
				data = new Data(uci.toString());
			    Number160 key = Number160.createHash(uci.toString());
			    FutureDHT futureDHT = P2PCommunication.peer.put(key).setData(data).start();
			    futureDHT.addListener(new BaseFutureAdapter<FutureDHT>() 
			    {
		                 public void operationComplete(FutureDHT f) throws Exception 
		                 {
		                  //TODO Return this to the Async listener
		                 }
		        });
		
			} catch (IOException e) {

			}

	}
	
	
	public void updateSync(UCI uci) throws LookupException 
	{
	try {
			Data data;
			data = new Data(uci.toString());
		    Number160 key = Number160.createHash(uci.toString());
		    FutureDHT futureDHT = P2PCommunication.peer.put(key).setData(data).start();
		    if(!(futureDHT.awaitUninterruptibly(10000)))
		    {
		    	throw new LookupException(uci, futureDHT.getFailedReason().toString());
		    }
		    
		} catch (IOException e) {

		}

		
	}
	
	

	@Override
	public void remove(UCI uci) throws LookupException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(UCI uci) throws LookupException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void register(UCI uci) throws LookupException {
		// TODO Auto-generated method stub
		
	}
}
