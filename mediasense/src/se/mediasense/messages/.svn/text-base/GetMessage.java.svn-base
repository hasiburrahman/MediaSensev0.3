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

package se.mediasense.messages;

import se.mediasense.disseminationlayer.communication.MediaSenseHost;
import se.mediasense.distribution.UCI;

/**
 *This is a GetMessage, used to fetch the current value associated with a known resource
 * <p>The message is dispatched to a specified remote {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}.
 * <p>This message can be received by either registering a {@link se.mediasense.messages.MediaSenseListener} setting the (String MsgType) as <i>se.mediasense.messages.GetMessage</i>
 * or by using the defaultlistener.
 */

public final class GetMessage extends MediaSenseMessage
{
	
	private final UCI uci;
        public static final String TYPE = GetMessage.class.getName();
        
    /**
     * Constructor for the super type GetMessage which does not accept a specified Message ID
     * @param _destination - The {@link se.mediasense.disseminationlayer.communication.MediaSenseHost} destination for this message
     * @param _requestor - The {@link se.mediasense.disseminationlayer.communication.MediaSenseHost} source of this message
     * @param _uci - The uci being requested
     */
        public GetMessage(UCI _uci, MediaSenseHost _destination, MediaSenseHost _requestor)
        {
               super(_destination, _requestor, GetMessage.TYPE);

               uci = _uci;
               
	}

    /**
     * Constructor for the super type GetMessage which does accepts a specified Message ID
     * @param _destination - The {@link se.mediasense.disseminationlayer.communication.MediaSenseHost} destination for this message
     * @param _requestor - The {@link se.mediasense.disseminationlayer.communication.MediaSenseHost} source of this message
     * @param _uci - The uci being requested
     * @param _MsgID - The java.lang.String represenation of this message ID
     */
        public GetMessage(UCI _uci, MediaSenseHost _destination, MediaSenseHost _requestor,  String _MsgID)
        {
               super(_destination, _requestor, _MsgID, GetMessage.TYPE);
               
               uci = _uci;

	}

        /**
         * Returns the UCI
         * @return the uci
         */
        public UCI getUCI()
        {
            return uci;
        }

        /**
         * Returns the {@link se.mediasense.disseminationlayer.communication.MediaSenseHost} that issued this message.
         * @return the requestor
         */
        public MediaSenseHost getRequestor()
        {
            return super.getSource();
            
        }

  
	
}
