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
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import se.mediasense.disseminationlayer.disseminationcore.DisseminationCore;
import se.mediasense.messages.GetMessage;
import se.mediasense.messages.MediaSenseListener;
import se.mediasense.messages.MediaSenseMessage;
import se.mediasense.messages.NotifyMessage;
import se.mediasense.util.MediaSenseConstants;

public final class GetterImplementation implements MediaSenseListener, Callable<Map<String, Serializable>>
{

    private final P2PCommunication communication = (P2PCommunication) MediaSenseConstants.COMMUNICATION;
    private final DisseminationCore disseminationcore = MediaSenseConstants.DISSEMINATIONCORE;
    private final HashMap<String, UCI> getqueue = new HashMap<String, UCI>();
    private final HashMap<String, java.io.Serializable> getresults = new HashMap<String, java.io.Serializable>();
    private Getter getter = null;

    public GetterImplementation(Getter _getter)
    {
    	getter = _getter;
    }

    public Map<String, Serializable> call()
    {

        startGet();

        synchronized (getqueue)
        {
            while (!getqueue.isEmpty())
            {
                try
                {
                    getqueue.wait();
                } catch (InterruptedException ex)
                {
                }
            }
            
            if(getter!=null)
            {
            	getter.completeAsynchronousGet(getresults);
            	
            }

        }

        disseminationcore.removeListener(NotifyMessage.TYPE, this);

        return getresults;
    }

    public void addUCI(List<UCI> _uci)
    {

        for (UCI u : _uci)
        {

            getqueue.put(u.getUciAsString(), u);

        }


    }

    public void addUCI(UCI _uci)
    {

        getqueue.put(_uci.getUciAsString(), _uci);


    }

    

    private void startGet()
    {

        if(getter==null)
        {
            disseminationcore.registerListener(NotifyMessage.TYPE, this);
        }
        else
        {
           

        }    
        for (UCI u : getqueue.values())
        {
            MediaSenseMessage msg = new GetMessage(u, u.getOwner(), MediaSenseConstants.LOCALHOST);
         
                communication.sendMessage(msg);
   
        }
    }

    private void completeGet(NotifyMessage _msg)
    {

        if (getqueue.containsKey(_msg.getUCI().getUciAsString()))
        {

            getresults.put(_msg.getUCI().getUciAsString(), _msg.getResource());

            getqueue.remove(_msg.getUCI().getUciAsString());


            synchronized (getqueue)
            {

                getqueue.notify();

            }


        }

    }

    public void handleMessage(MediaSenseMessage _msg)
    {
        if (_msg instanceof NotifyMessage)
        {

            completeGet((NotifyMessage) _msg);

        }

    }
}
