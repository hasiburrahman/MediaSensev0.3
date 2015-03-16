package se.mediasense.distribution;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;

import net.tomp2p.connection.Bindings;
import net.tomp2p.futures.FutureDHT;
import net.tomp2p.futures.FutureDiscover;
import net.tomp2p.futures.FutureResponse;
import net.tomp2p.p2p.Peer;
import net.tomp2p.p2p.PeerMaker;
import net.tomp2p.peers.Number160;
import net.tomp2p.peers.PeerAddress;
import net.tomp2p.replication.Replication;
import net.tomp2p.rpc.ObjectDataReply;
import net.tomp2p.storage.Data;
import net.tomp2p.storage.ReplicationStorage;
import net.tomp2p.storage.StorageDisk;
import se.mediasense.disseminationlayer.communication.Communication;
import se.mediasense.disseminationlayer.communication.MediaSenseHost;
import se.mediasense.messages.MediaSenseMessage;
import se.mediasense.util.MediaSenseConstants;
import se.mediasense.util.ThreadExecutor;

public class P2PCommunication extends Communication implements ObjectDataReply
{
	
	private static final Communication SHARED_INSTANCE = new P2PCommunication();
	private static String bootstrapaddress;
	private static int bootstrapport;
	private static int localhostport;
	private static boolean initialized = false;
	private static P2PMediaSenseHost localhost;
	public static Peer peer = null;
	

    
	protected P2PCommunication()
    {
        bootstrapaddress = null;
        File theDir = new File(MediaSenseConstants.rootdir);
        if (!theDir.exists())
        {
          theDir.mkdir();           
        }
        theDir = new File("overlay");
        if (!theDir.exists())
        {
          theDir.mkdir();           
        }
        theDir = new File("overlay" + System.getProperty("file.separator") + "persistence");
        if (!theDir.exists())
        {
          theDir.mkdir();          
        }
        theDir = new File("overlay" + System.getProperty("file.separator") + "replication");
        if (!theDir.exists())
        {
          theDir.mkdir();           
        }
    }

    
	public static Communication getSharedInstance()
    {
        return SHARED_INSTANCE;
    }

	
	@Override
	public void shutdown() 
	{
		peer.shutdown();
	}



	@Override
	public void sendMessage(MediaSenseMessage _msg)
	{
		
		PeerAddress paddress = ((P2PMediaSenseHost)_msg.getDestination()).getAddress();
		FutureResponse fr = peer.sendDirect(paddress).setObject(_msg).start();	
		fr.awaitUninterruptibly();

//		PeerAddress p = ((P2PMediaSenseHost)_msg.getDestination()).getAddress();
//		FutureResponse fr = peer.sendDirect(p).setObject("hello").start();
//		fr.awaitUninterruptibly();
//
//		System.out.println("Sending Messages: " + _msg.getMsgType() + " to " + ((P2PMediaSenseHost)_msg.getDestination()).getAddress().getInetAddress());
//		
//	    FutureDHT futureDHT = P2PCommunication.peer.get(new Number160(_msg.getDestination().)).start();
//	    futureDHT.awaitUninterruptibly();
//	    try {
//			PeerAddress paddress = (PeerAddress)futureDHT.getData().getObject();
//			FutureResponse fr = peer.sendDirect(paddress).setObject("hello").start();
//			fr.awaitUninterruptibly();
//			System.out.println("Peer1 is: " + paddress.getID().toString());
//
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

				
	}

	@Override
	public MediaSenseHost getLocalHost() 
	{
		return localhost;
	}

	@Override
	public boolean isBehindNat() 
	{
		if(peer.getPeerAddress().isFirewalledTCP() || peer.getPeerAddress().isFirewalledUDP())
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	@Override
	public void init(String bootstrap, int bootport, int localport) 
	{
		bootstrapaddress = bootstrap;
		bootstrapport = bootport;
	    localhostport = localport;
	    	    
	    Bindings b = new Bindings();
	    InetAddress address = null;
		try 
		{		
			String node = MediaSenseConstants.getProperty("mediasensehostid");
			
			peer = new PeerMaker(Number160.createHash(node)).setPorts(localhostport).setEnableIndirectReplication(true).setEnableIndirectReplication(true).setBindings(b).makeAndListen();
			peer.setObjectDataReply(this);
			peer.getDirectDataRPC().setReply(this);
			peer.getPeerBean().getReplicationStorage().setReplicationFactor(Integer.parseInt(MediaSenseConstants.getProperty("overlayreplicationfactor")));
		    peer.getPeerBean().setStorage(new StorageDisk("overlay" + System.getProperty("file.separator") + "persistence"));
//		    ReplicationStorage rs = new StorageDisk("overlay" + System.getProperty("file.separator") + "replication");
//		    peer.getPeerBean().setReplicationStorage(new Replication(rs, peer.getPeerAddress(), peer.getPeerBean().getPeerMap(), 1));
			address = Inet4Address.getByName(bootstrapaddress);	    
		    FutureDiscover futureDiscover = peer.discover().setInetAddress( address ).setPorts( bootstrapport ).start();
			futureDiscover.await();
			localhost = new P2PMediaSenseHost(peer.getPeerID().toString(), peer.getPeerAddress());
			MediaSenseConstants.LOCALHOST = localhost;
			initialized=true;		    
		    Data data=null;
			try {
				data = new Data(peer.getPeerAddress());
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		    Number160 nr1 = Number160.createHash(node);
		    FutureDHT futureDHT = P2PCommunication.peer.put(nr1).setData(data).start();
		    futureDHT.awaitUninterruptibly();
		
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	   	   		
	}
	
	
	public void initBootStrap(int bootport) 
	{

	    	    
	    Bindings b = new Bindings();
		try 
		{		
			String node = MediaSenseConstants.getProperty("mediasensehostid");
			
			peer = new PeerMaker(Number160.createHash(node)).setPorts(bootport).setEnableIndirectReplication(true).setBindings(b).makeAndListen();
		    peer.getConfiguration().setBehindFirewall(false);
			localhost = new P2PMediaSenseHost(peer.getPeerID().toString(), peer.getPeerAddress());
			MediaSenseConstants.LOCALHOST = localhost;
			initialized=true;
			peer.setObjectDataReply(this);
			peer.getPeerBean().getReplicationStorage().setReplicationFactor(Integer.parseInt(MediaSenseConstants.getProperty("overlayreplicationfactor")));
		    peer.getPeerBean().setStorage(new StorageDisk("overlay" + System.getProperty("file.separator") + "persistence"));
		  // ReplicationStorage rs = new StorageDisk("overlay" + System.getProperty("file.separator") + "replication");
		  // peer.getPeerBean().setReplicationStorage(new Replication(rs, peer.getPeerAddress(), peer.getPeerBean().getPeerMap(), 1));
		    
		    Data data=null;
			try {
				data = new Data(peer.getPeerAddress());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    Number160 nr1 = Number160.createHash(node);
		    FutureDHT futureDHT = P2PCommunication.peer.put(nr1).setData(data).start();
		    futureDHT.awaitUninterruptibly();
		    
		    
		    System.out.println(peer.getPeerBean().getReplicationStorage().isReplicationEnabled());


		
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	   	   		
	}

	public boolean isInitialized() {
		return initialized;
	}


	@Override
	public Object reply(PeerAddress sender, Object request) throws Exception 
	{
		if (request instanceof MediaSenseMessage)
         {
             final MediaSenseMessage msmsg = (MediaSenseMessage) request;

             Runnable r = new Runnable()
             {
                 @Override
                 public void run()
                 {

                     MediaSenseConstants.DISSEMINATIONCORE.handleMessage(msmsg);
                 }
             };

             ThreadExecutor.SharedInstance().submit(r);

         }		return null;
	}



	

}
