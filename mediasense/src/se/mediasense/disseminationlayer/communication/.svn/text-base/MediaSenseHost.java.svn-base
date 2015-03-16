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

import java.io.Serializable;

/**
 *This is the MediaSenseHost Object.
 *The implementation can vary depending on the underlaying communication protocol
 *
 */
public interface MediaSenseHost extends Serializable
{
    /**
     * Returns the ID for the this MediaSenseHost - Must be implemented.
     * <p>The format of the ID is implementation dependent and should only be used
     * for tracking this MediaSenseHost.
     * <p>For communication purposes, this MediaSenseHost object must be used in its entirety.
     *@return java.lang.String represenation of the Host ID
     */
    public String getHostID();

    
    
}
