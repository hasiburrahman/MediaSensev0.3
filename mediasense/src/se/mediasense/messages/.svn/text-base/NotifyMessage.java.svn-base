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
 * This is a NotifyMessage, used as a response to the
 * {@link se.mediasense.messages.GetMessage} returning the current value
 * associated with a resource <p>The message is dispatched to a specified remote
 * {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}.
 * <p>This message can be received by either registering a
 * {@link se.mediasense.messages.MediaSenseListener} setting the (String
 * MsgType) as <i>se.mediasense.messages.NotifyMessage</i> or by using the
 * defaultlistener.
 */
public final class NotifyMessage extends MediaSenseMessage
{

    private final UCI uci;
    private final java.io.Serializable resource;
    public static final String TYPE = NotifyMessage.class.getName();

    /**
     * Constructor for the NotifyMessage which does not accept a specified
     * Message ID
     *
     * @param _uci - The java.lang.String represenation of the uci being
     * requested
     * @param _resource - The value being sent
     * @param _destination - The
     * {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * destination for this message
     * @param _origin - The
     * {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * source of this message, usually the local
     * {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     */
    public NotifyMessage(
            UCI _uci,
            java.io.Serializable _resource,
            MediaSenseHost _destination,
            MediaSenseHost _origin)
    {
        super(_destination, _origin, NotifyMessage.TYPE);
        resource = _resource;
        uci = _uci;

    }

    /**
     * Constructor for the NotifyMessage which does not accept a specified
     * Message ID. This should be the preferred constructor setting the _MsgID
     * to that of the corresponding {@link se.mediasense.messages.GetMessage}.
     *
     * @param _uci - The java.lang.String represenation of the uci being
     * requested
     * @param _resource - The value being sent
     * @param _destination - The
     * {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * destination for this message
     * @param _origin - The
     * {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * source of this message, usually the local
     * {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * @param _MsgID - The ID of this message
     */
    public NotifyMessage(
            UCI _uci,
            java.io.Serializable _resource,
            MediaSenseHost _destination,
            MediaSenseHost _origin,
            String _MsgID)
    {
        super(_destination, _origin, _MsgID, NotifyMessage.TYPE);
        resource = _resource;
        uci = _uci;

    }

    /**
     * Returns the UCI in java.lang.String format
     *
     * @return the uci
     */
    public UCI getUCI()
    {
        return uci;
    }

    /**
     * Returns the java.io.Serializable value associated with the requested UCI
     *
     * @return the value
     */
    public java.io.Serializable getResource()
    {
        return resource;
    }
}
