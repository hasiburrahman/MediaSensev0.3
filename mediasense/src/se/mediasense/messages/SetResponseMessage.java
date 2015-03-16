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


public class SetResponseMessage extends MediaSenseMessage
{
    private final String uci;
    private final Serializable currentvalue;
    private final Serializable previousvalue;
    /**
     * Constructor for the SetMessage which does not accept a
     * specified Message ID
     * @param _uci - The java.lang.String represenation of the uci whose value was set
     * @param _currentvalue  - The new value that was set
     * @param _previousvalue  - The old value before being set
     * @param _destination - The {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * destination for this message
     * @param _origin - The {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * source of this message, usually the local {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     */
    public SetResponseMessage
                            (
                                String _uci,
                                java.io.Serializable _currentvalue,
                                java.io.Serializable _previousvalue,
                                MediaSenseHost _destination,
                                MediaSenseHost _origin
                             )
    {
            super(_destination, _origin, ResolveResponseMessage.class.getName());
            uci = _uci;
            currentvalue = _currentvalue;
            previousvalue = _previousvalue;
    }


    /**
     * Constructor for the SetMessage which does not accept a
     * specified Message ID
     * @param _uci - The java.lang.String represenation of the uci whose value was set
     * @param _currentvalue  - The new value that was set
     * @param _previousvalue  - The old value before being set
     * @param _destination - The {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * destination for this message
     * @param _origin - The {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * source of this message, usually the local {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * @param _MsgID - The ID of this message
     */
    public SetResponseMessage
                            (
                                String _uci,
                                java.io.Serializable _currentvalue,
                                java.io.Serializable _previousvalue,
                                MediaSenseHost _destination,
                                MediaSenseHost _origin,
                                String _MsgID
                             )
    {
            super(_destination, _origin, _MsgID, SetMessage.class.getName());
            uci = _uci;
            currentvalue = _currentvalue;
            previousvalue = _previousvalue;
    }

    
     /**
     * Returns the UCI in java.lang.String format
     * @return the uci
     */
    public String getUci() {
        return uci;
    }

    /**
     * Returns the value being set to the associated UCI
     * @return the value
     */
    public Serializable getCurrentValue() {
        return currentvalue;
    }

    /**
     * Returns the previous value being set to the associated UCI
     * @return the value
     */
    public Serializable getPreviousValue() {
        return previousvalue;
    }

}
