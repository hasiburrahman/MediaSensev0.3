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

import java.io.Serializable;
import se.mediasense.disseminationlayer.communication.MediaSenseHost;
import se.mediasense.util.MediaSenseID;

/**
 * The MediaSenseMessage abstract class.
 * <p>All messages must extend MediaSenseMessage in order to be usable on the platform.
 * In addition, all messages must be serializable to enable delivery to a remote {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}.
 *
 */

public abstract class MediaSenseMessage implements Serializable
{

    private String MsgID;
    private final MediaSenseHost destination;
    private final String MsgType;
    private final MediaSenseHost source;
//    private SCOPE scope;
//
//    /**
//     * @return the scope
//     */
//    public SCOPE getScope()
//    {
//        return scope;
//    }
//
//    /**
//     * @param scope the scope to set
//     */
//    public void setScope(SCOPE _scope)
//    {
//        scope = _scope;
//    }
//    
//    public static enum SCOPE 
//    {
//        APPLICATION,
//        PEER
//    }

/**
 * Constructor for the super type MediaSenseMessage which accepts a specified Messgae ID
 * @param _destination - The {@link se.mediasense.disseminationlayer.communication.MediaSenseHost} destination for this message
 * @param _source - The {@link se.mediasense.disseminationlayer.communication.MediaSenseHost} source of this message
 * @param _MsgID - The java.lang.String represenation of this message ID
 * @param _MsgType - The java.lang.String represenation of this message type
 */
public MediaSenseMessage(MediaSenseHost _destination, MediaSenseHost _source, String _MsgID, String _MsgType)
{
    MsgID = _MsgID;
    MsgType = _MsgType;
    source = _source;
    destination = _destination;


}

/**
 * Constructor for the super type MediaSenseMessage which does not accept a specified Messgae ID. In this case one is automatically assigned
 * @param _destination - The {@link se.mediasense.disseminationlayer.communication.MediaSenseHost} destination for this message
 * @param _source - The {@link se.mediasense.disseminationlayer.communication.MediaSenseHost} source of this message
 * @param _MsgType - The java.lang.String represenation of this message type
 */
public MediaSenseMessage(MediaSenseHost _destination, MediaSenseHost _source, String _MsgType )
{
    MsgType = _MsgType;
    source = _source;
    destination = _destination;
    MsgID = MediaSenseID.generateID();

}

    /**
     * Returns the ID associated with this message. The ID is unique, sometimes random and in the form of a GUID.
     * @return the MsgID - the ID as a java.lang.String
     */
    public String getMsgID()
    {
        return MsgID;
    }

    /**
     *Returns the {@link se.mediasense.disseminationlayer.communication.MediaSenseHost} destination of this message.
     * @return the destination
     */
    public MediaSenseHost getDestination()
    {
        return destination;
    }

    /**
     * Returns the type of message encapsulated in this object.
     * @return the MsgType  - the message type as a java.lang.String
     */
    public String getMsgType()
    {
        return MsgType;
    }

    /**
     * Returns the {@link se.mediasense.disseminationlayer.communication.MediaSenseHost} that generated this message.
     * @return the source
     */
    public MediaSenseHost getSource()
    {
        return source;
    }

}
