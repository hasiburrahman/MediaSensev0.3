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

import se.mediasense.disseminationlayer.communication.MediaSenseHost;
import se.mediasense.messages.MediaSenseMessage;

//obsolete? can be replaced with just a regular notify????


public class NotifySubscribersMessage extends MediaSenseMessage {
	
	private final String uci;
	private final java.io.Serializable value;
        private String subscriptionid;


	public NotifySubscribersMessage(String _uci, java.io.Serializable _value, MediaSenseHost _destination, MediaSenseHost _origin)
        {
		super(_destination, _origin, NotifySubscribersMessage.class.getName());
		value = _value;
		uci = _uci;

	}


        public NotifySubscribersMessage(String _uci, java.io.Serializable _value, MediaSenseHost _destination, MediaSenseHost _origin,  String _MsgID)
        {
		super(_destination, _origin, _MsgID, NotifySubscribersMessage.class.getName());
		value = _value;
		uci = _uci;

	}

    /**
     * @return the uci
     */
    public String getUci() {
        return uci;
    }

    /**
     * @return the value
     */
    public java.io.Serializable getValue() {
        return value;
    }

}

