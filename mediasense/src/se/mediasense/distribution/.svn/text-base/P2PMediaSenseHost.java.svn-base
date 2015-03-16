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
import net.tomp2p.peers.PeerAddress;


public class P2PMediaSenseHost implements MediaSenseHost, Serializable
{
	private static final long serialVersionUID = 7518844019250489965L;
	private final String ID;
    private final PeerAddress address;
  

    public P2PMediaSenseHost(String _ID, PeerAddress _address)
    {
        ID = _ID;
        address = _address;

    }

    
    public String getHostID()
    {
        return ID;
    }

    
    public PeerAddress getAddress()
    {
        return address;
    }

    
}
