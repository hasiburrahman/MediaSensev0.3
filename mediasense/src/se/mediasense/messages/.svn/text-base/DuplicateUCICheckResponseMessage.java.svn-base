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

/**
 * This is a ResolveResponseMessage, sent in response to a resolve request from
 * a remote
 * {@link se.mediasense.disseminationlayer.communication.MediaSenseHost} <p>The
 * message is dispatched to a specified remote
 * {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}.
 * <p>These requests are usually handled handled by the
 * {@link se.mediasense.disseminationlayer.lookupservice.LookupService}
 * implementation however it can also be received by any registering a
 * {@link se.mediasense.messages.MediaSenseListener} and setting the (String
 * MsgType) as <i>se.mediasense.messages.ResolveResponseMessage</i> or by using
 * the defaultlistener.
 */
public final class DuplicateUCICheckResponseMessage extends MediaSenseMessage
{

    private final String uci;
    private final MediaSenseHost host;
    public static final String TYPE = DuplicateUCICheckResponseMessage.class.getName();

    /**
     * Constructor for the ResolveResponseMessage which does not accept a
     * specified Message ID
     *
     * @param _uci - The java.lang.String represenation of this message uci
     * requested
     * @param _ucihost - The
     * {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * that is responsible for for this message
     * @param _destination - The
     * {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * destination for this message
     * @param _origin - The
     * {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * source of this message, usually the local
     * {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     */
    public DuplicateUCICheckResponseMessage(
            String _uci,
            MediaSenseHost _ucihost,
            MediaSenseHost _destination,
            MediaSenseHost _origin)
    {
        super(_destination, _origin, DuplicateUCICheckResponseMessage.TYPE);
        host = _ucihost;
        uci = _uci;
//        super.setScope(SCOPE.APPLICATION);

    }

    /**
     * Constructor for the ResolveResponseMessage which does not accept a
     * specified Message ID
     *
     * @param _uci - The java.lang.String represenation of this message uci
     * requested
     * @param _ucihost - The
     * {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * that is responsible for for this message
     * @param _destination - The
     * {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * destination for this message
     * @param _origin - The
     * {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * source of this message, usually the local
     * {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * @param _MsgID - The ID of this message
     */
    public DuplicateUCICheckResponseMessage(
            String _uci,
            MediaSenseHost _ucihost,
            MediaSenseHost _destination,
            MediaSenseHost _origin,
            String _MsgID)
    {
        super(_destination, _origin, _MsgID, DuplicateUCICheckResponseMessage.TYPE);
        host = _ucihost;
        uci = _uci;

    }

    /**
     * Returns the UCI in java.lang.String format
     *
     * @return the uci
     */
    public String getUci()
    {
        return uci;
    }

    /**
     * Returns the
     * {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * responsible for the associated UCI
     *
     * @return the value
     */
    public MediaSenseHost getUCIOwner()
    {
        return host;
    }
}
