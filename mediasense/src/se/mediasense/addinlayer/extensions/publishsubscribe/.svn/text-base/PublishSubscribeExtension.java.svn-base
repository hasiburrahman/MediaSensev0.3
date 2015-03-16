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

package se.mediasense.addinlayer.extensions.publishsubscribe;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;
import se.mediasense.addinlayer.extensions.Extension;
import se.mediasense.disseminationlayer.communication.DestinationNotReachableException;
import se.mediasense.disseminationlayer.communication.MediaSenseHost;
import se.mediasense.interfacelayer.MediaSensePlatform;
import se.mediasense.messages.MediaSenseListener;
import se.mediasense.messages.MediaSenseMessage;

public final class PublishSubscribeExtension implements Extension, MediaSenseListener
{

    private MediaSensePlatform platform;
    private final MultiMap subscriptions = new MultiMap();
    private final SubscriptionResponseListener srl;

    public PublishSubscribeExtension(SubscriptionResponseListener listener)
    {

           srl = listener;
        
    }
	
    @Override
    public void loadAddIn(MediaSensePlatform _platform)
    {

        platform = _platform;
        platform.registerListener(StartSubscribeMessage.class.getName(), this);
        platform.registerListener(EndSubscribeMessage.class.getName(), this);
        platform.registerListener(NotifySubscribersMessage.class.getName(), this);

    }


    @Override
    public void startAddIn()
    {

        //No extra stuff to start

    }


    @Override
    public void stopAddIn()
    {
		
        platform.removeListener(StartSubscribeMessage.class.getName(), this);
        platform.removeListener(EndSubscribeMessage.class.getName(), this);
        platform.removeListener(NotifySubscribersMessage.class.getName(), this);

    }


    @Override
    public void unloadAddIn()
    {

        subscriptions.clear();

    }

    
    public void handleMessage(MediaSenseMessage _msg)
    {

        if(_msg.getMsgType().equalsIgnoreCase(StartSubscribeMessage.class.getName()))
        {
            subscriptions.put(((StartSubscribeMessage)_msg).getUci(), ((StartSubscribeMessage)_msg).getSource());

        }
        else if(_msg.getMsgType().equalsIgnoreCase(EndSubscribeMessage.class.getName()))
        {

            subscriptions.remove(((StartSubscribeMessage)_msg).getUci(), ((StartSubscribeMessage)_msg).getSource());

        }
        else if(_msg.getMsgType().equalsIgnoreCase(NotifySubscribersMessage.class.getName()))
        {

            srl.subscriptionResponse((NotifySubscribersMessage)_msg);

        }

        
    }
    
    
	
	/**
	 * This will start a subscription for a specific UCI. 
	 * Given that the other end point is also running the publish/subscribe interface.
	 * @param uci the UCI to be subscribed to
	 * @param ip the IP end point which handles the UCI, which has previously been resolved. 
	 */
	public void startSubscription(String uci, MediaSenseHost host)
        {
            try
            {

                platform.sendMessage(new StartSubscribeMessage(uci, platform.getLocalHost(), host));

            }
            catch (DestinationNotReachableException ex)
            {


            }


	}
	
	/**
	 * This will end the subscription for a specific UCI.
	 * All notify messages from that UCI should now be stopped.
	 * @param uci the UCI which is not longer wanted
	 * @param ip the IP end point which handles the UCI, which has previously been resolved. 
	 */
	public void endSubscription(String uci, MediaSenseHost host)
        {

            try
            {

                platform.sendMessage(new EndSubscribeMessage(uci, platform.getLocalHost(), host));

            }
            catch (DestinationNotReachableException ex)
            {


            }


	}
	
	/**
	 * This is called to notify all subscribers of a new value.
	 * Should be called when a value is updated.
	 * @param uci the UCI that was just updated
	 * @param value the new value, which will be sent to all subscribers
	 */
	public void notifySubscribers(String uci, Serializable value){

		//Attend to the subscriptions
		MediaSenseHost[] subsriberIp = subscriptions.get(uci);

		for(int i = 0; i != subsriberIp.length; i++)
                {

			try
                        {
				
                            platform.sendMessage(new NotifySubscribersMessage(uci, value, subsriberIp[i], platform.getLocalHost()));
                            

			}
			catch(DestinationNotReachableException e)
                        {
				//Not reachable, remove that subscriber from the list
				subscriptions.remove(uci, subsriberIp[i]);
			}
		}
	}


	
	//MultiMap for handling the Subscriptions
        //replace vector with a synchronised collection, vector is now considered obsolete
        //some persistence is needed here as well, to compensate for possible high churn rates
	private class MultiMap
        {
		
		HashMap<String, Vector<MediaSenseHost>> map = new HashMap<String, Vector<MediaSenseHost>>();
		
		public void put(String key, MediaSenseHost value){
			Vector<MediaSenseHost> v = map.get(key);
			if(v == null){
				v = new Vector<MediaSenseHost>();
			}
			v.add(value);
			map.put(key, v);
		}
		
		public MediaSenseHost[] get(String key){
			Vector<MediaSenseHost> v = map.get(key);
			if(v == null){
				return new MediaSenseHost[0];
			} else {
				return v.toArray(new MediaSenseHost[v.size()]);
			}
		}
		
		public void remove(String key, MediaSenseHost value){
			Vector<MediaSenseHost> v = map.get(key);
			v.remove(value);
			map.put(key, v);
		}
		
		public void clear(){
			map.clear();
		}
		
	}
}





