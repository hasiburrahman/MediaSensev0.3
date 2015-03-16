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


public class EndSubscribeMessage extends MediaSenseMessage
{
	
	public String uci;
	
        public EndSubscribeMessage(String _uci, MediaSenseHost _subscriber, MediaSenseHost _resourceowner)
        {

               super(_resourceowner, _subscriber, EndSubscribeMessage.class.getName());
               uci = _uci;

	}


	public EndSubscribeMessage(String _uci, MediaSenseHost _subscriber, MediaSenseHost _resourceowner, String _msgid)
        {

               super(_resourceowner, _subscriber, _msgid, EndSubscribeMessage.class.getName());
               uci = _uci;

	}

   
}
