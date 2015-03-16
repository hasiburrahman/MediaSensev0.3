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

package se.mediasense.disseminationlayer.communication;

import se.mediasense.messages.MediaSenseMessage;

public abstract class Communication {

        /* the different types of methods for exchanging mediasense messages
           between nodes.
        */	
	public final static int TCP = 1;
	public final static int UDP = 2;
	public final static int RUDP = 3;
	public final static int SCTP = 4;	
	public final static int TCP_PROXY = 5;
        public final static int P2P = 6;
        
	
    
       /* the local address is a generic object, allows us to store any type of
          local address matching any implementation of the class
        */        
        

        
	public Communication()
        {
         
        }


        /**
         *Shuts down and disables the communication layer. This does not affect
         * the underlaying overlay
         */
        public abstract void shutdown();


        /**
         * Initializes the communication layer, this is independent of the
         * underlying overlay which may still be running
         */
        public abstract void init(String bootstrap, int bootport, int localport);


        /**
         * Sends a message to another mediasense node, does not send messages
         * for maintaining the underlaying overlay
         * @param _msg The message to send
         * @throws DestinationNotReachableException Throws an exception if the
         * message cannot be delivered.
         */
	public abstract void sendMessage(MediaSenseMessage _msg);

		
        /**
         * Returns the local address identifier. Type Object which is specific
         * to the communication type
         * @return Object localaddress
         */
	public abstract MediaSenseHost getLocalHost();


        /**
         * Checks if the localaddress is behind NAT and returns TRUE or FALSE
         * @return boolean 
         */
	public abstract boolean isBehindNat();
	
	public abstract boolean isInitialized();
	

}
