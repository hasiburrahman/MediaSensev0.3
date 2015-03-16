/*
 * Copyright 2012 http://www.mediasense.se
 * This file is part of The MediaSense Platform.
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

package se.mediasense.messages;

import java.io.Serializable;
import se.mediasense.disseminationlayer.communication.MediaSenseHost;

/**
 *This is a ResolveMessage, used to retrieve the
 * {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
 * associated with a UCI.
 * <p>The message is dispatched to a specified remote
 * {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}.
 * <p>This message is usually handled by the
 * {@link se.mediasense.disseminationlayer.lookupservice.LookupService}
 * implementation however it can also be received by any registering a
 * {@link se.mediasense.messages.MediaSenseListener} and setting the (String MsgType)
 * as <i>se.mediasense.messages.ResolveMessage</i>
 * or by using the defaultlistener.
 */

public final class ResolveMessage extends MediaSenseMessage implements Serializable
{
	
	private final String uci;

        
    /**
     * Constructor for the NotifyMessage which does not accept a
     * specified Message ID
     * @param _uci - The java.lang.String represenation of this message uci being requested
     * @param _requester - The {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     */
	public ResolveMessage(String _uci, MediaSenseHost _requester)
        {
               super(_requester, _requester, ResolveMessage.class.getName());

               uci = _uci;

	}
    /**
     * Constructor for the NotifyMessage which does not accept a
     * specified Message ID
     * @param _uci - The java.lang.String represenation of this message uci being requested
     * @param _requester - The {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * @param _MsgID - The ID of this message
     */

        public ResolveMessage(String _uci, MediaSenseHost _requester,  String _MsgID)
        {
               super(_requester, _requester, _MsgID, ResolveMessage.class.getName());

               uci = _uci;

	}



        /**
         * Returns the UCI in java.lang.String format
         * @return the uci
         */
        public String getUci()
        {
            return uci;
        }

        /**
         * Returns the {@link se.mediasense.disseminationlayer.communication.MediaSenseHost} origin for this message.
         * @return the órigin
         */
        public MediaSenseHost getRequester()
        {
            return super.getSource();

        }

}
