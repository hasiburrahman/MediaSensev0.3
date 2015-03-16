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
 * This is a SetMessage, used to set the value associated with a UCI located on a
 * remote {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}.
 * This could for example be used to change the state of an actuator or update
 * any other value.
 * <p>The message is dispatched to a specified remote
 * {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}.
 * <p>This message can be received by either registering a
 * {@link se.mediasense.messages.MediaSenseListener} setting the (String MsgType)
 * as <i>se.mediasense.messages.SetMessage</i> or by using the defaultlistener.
 * The corresponding reply message is a {@link se.mediasense.messages.SetResponseMessage}
 */


public final class SetMessage extends MediaSenseMessage {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UCI uci;
    private java.io.Serializable newvalue;
    private java.io.Serializable oldvalue;


    /**
     * Constructor for the SetMessage which does not accept a
     * specified Message ID
     * @param _uci - The java.lang.String represenation of the uci whose value is being set
     * @param _newvalue  - The new value being set
     * @param _destination - The {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * destination for this message
     * @param _origin - The {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * source of this message, usually the local {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     */
    public SetMessage(
                        UCI _uci,
                        java.io.Serializable _newvalue,
                        MediaSenseHost _destination,
                        MediaSenseHost _origin
                     )
    {
            super(_destination, _origin, SetMessage.class.getName());
            uci = _uci;
            newvalue = _newvalue;
    }


    /**
     * Constructor for the SetMessage which does not accept a
     * specified Message ID
     * @param _uci - The java.lang.String represenation of the uci whose value is being set
     * @param _newvalue  - The new value being set
     * @param _destination - The {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * destination for this message
     * @param _origin - The {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * source of this message, usually the local {@link se.mediasense.disseminationlayer.communication.MediaSenseHost}
     * @param _MsgID - The ID of this message
     */
      public SetMessage(
                            UCI _uci,
                            java.io.Serializable _newvalue,
                            MediaSenseHost _destination,
                            MediaSenseHost _origin,
                            String _MsgID
                         )
        {
		super(_destination, _origin, _MsgID, SetMessage.class.getName());
		uci = _uci;
		newvalue = _newvalue;
	}


     /**
     * Returns the UCI in java.lang.String format
     * @return the uci
     */
        public UCI getUci()
        {
            return uci;
        }


        /**
         * Returns the value being set to the associated UCI
         * @return the value
         */
        public java.io.Serializable getNewValue()
        {
            return newvalue;
        }

}
