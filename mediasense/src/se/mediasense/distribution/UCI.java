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
 */
package se.mediasense.distribution;

import java.io.Serializable;
import se.mediasense.disseminationlayer.communication.MediaSenseHost;

/**
 * This creates a UCI object according to the MediaSense protocol. A modified
 * to be implementable on PGrid, so a UCI - Peer is used instead of a UCI - IP
 * making this more usable on PGrid since a Peer has more information than just
 * an IP address.
 * @author @author <a href="mailto:Jamie Walters <jamiew@dsv.su.se>">Jamie Walters</a>
 */
public class UCI implements Serializable
{

    /**
    * The string representation of this data item type.
    */
    public static final String TYPE_STRING = "UCI";
    private String uci = null;
    private MediaSenseHost peer = null;
    

    /**
     * Default contstructor for UCI, requires both a UCI and the Peer that is
     * responsible for this UCI.
     * @param _uci the URI or the global indentifier of the object/entity
     * being registered
     * @param  _host The peer that is registering this UCI
     */
    public UCI(String _uci, MediaSenseHost _owner)
    {

        this.uci = _uci;
        this.peer = _owner;


    }


    /**
     * Returns the URI (UCI) that uniquely identifies this entity within the
     * lookup service implementation.
     * @return String uci The UCI that identifies this entity
     */
    public String getUciAsString()
    {
        
        return this.uci;

    }

    /**
     * Returns the peer that owns this UCI according to the PGrid implementation.
     * 
     * @return p2p.basic.Peer peer The peer that owns this UCI
     */
    public MediaSenseHost getOwner()
    {
        
        return this.peer;

    }

	/**
	 * Returns a string representation of the type.
	 *
	 * @return the string.
	 */
    @Override
	public String toString()
        {

           return this.getUciAsString();
           
        }


}
