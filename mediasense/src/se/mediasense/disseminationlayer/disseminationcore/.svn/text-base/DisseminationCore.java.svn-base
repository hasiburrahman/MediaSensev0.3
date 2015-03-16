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
package se.mediasense.disseminationlayer.disseminationcore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import se.mediasense.disseminationlayer.communication.Communication;
import se.mediasense.disseminationlayer.communication.DestinationNotReachableException;
import se.mediasense.distribution.P2PCommunication;
import se.mediasense.messages.MediaSenseListener;
import se.mediasense.messages.MediaSenseMessage;
import se.mediasense.util.ThreadExecutor;


public class DisseminationCore implements Runnable
{
    private Communication communication = P2PCommunication.getSharedInstance();
    public static final String DEFAULT_LISTENER = "default";   
    private static final DisseminationCore SHARED_INSTANCE = new DisseminationCore();
    private HashMap<String, ArrayList<MediaSenseListener>> listeners = new HashMap<String, ArrayList<MediaSenseListener>>();
    
    protected DisseminationCore()
    {
        
    }
    
    public static DisseminationCore getSharedInstance()
    {
        return SHARED_INSTANCE;
    }

    public synchronized void registerListener(String _msgtype, MediaSenseListener _listener)
    {
        if (listeners.containsKey(_msgtype))
        {
            listeners.get(_msgtype).add(_listener);
        } else
        {
            listeners.put(_msgtype, new ArrayList<MediaSenseListener>());
            listeners.get(_msgtype).add(_listener);
        }
    }

    public synchronized void removeListener(String _msgtype, MediaSenseListener _listener)
    {
        if (listeners.containsKey(_msgtype))
        {
            listeners.get(_msgtype).remove(_listener);
        }
    }

   public synchronized void Dispatch(final MediaSenseMessage _msg) throws DestinationNotReachableException
    {
        communication.sendMessage(_msg);
    }    
                

    public synchronized void handleMessage(final MediaSenseMessage _msg)
    {System.out.println("trying to handle message");
        boolean handled = false;
        if (listeners.containsKey(_msg.getMsgType()))
        {
            final Iterator it = listeners.get(_msg.getMsgType()).iterator();
            while (it.hasNext())
            {                
                Runnable r = new Runnable()
                {                    
                    public void run()
                    {
                        ((MediaSenseListener) it.next()).handleMessage(_msg);
                    }                    
                };
                ThreadExecutor.SharedInstance().submit(r);
                handled = true;
            }
        }

        if ((!handled) && (listeners.containsKey(DisseminationCore.DEFAULT_LISTENER)))
        {
            final Iterator it = listeners.get(DisseminationCore.DEFAULT_LISTENER).iterator();
            while (it.hasNext())
            {
                Runnable r = new Runnable()
                    {
                        public void run()
                        {
                            ((MediaSenseListener) it.next()).handleMessage(_msg);
                        }
                    };                
                ThreadExecutor.SharedInstance().submit(r);
                handled = true;
            }

        } else if (!handled)
        {

        }

    }

    public void run()
    {
        while(true)
        {
            try
            {
                Thread.sleep(10);
            } catch (InterruptedException e)
            {            
            }
        }
        
    }
}
