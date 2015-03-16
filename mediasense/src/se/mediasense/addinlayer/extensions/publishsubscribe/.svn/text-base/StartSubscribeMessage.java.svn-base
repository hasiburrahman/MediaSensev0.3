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

package se.mediasense.addinlayer.extensions.publishsubscribe;

import se.mediasense.disseminationlayer.communication.MediaSenseHost;
import se.mediasense.messages.MediaSenseMessage;


public class StartSubscribeMessage extends MediaSenseMessage
{
	
	private String uci;
        private String duration;
        private String end_time;
        private String start_time;
        private int frequency;
        private String stale_level;
	

        public StartSubscribeMessage(String _uci, MediaSenseHost _subscriber, MediaSenseHost _resourceowner)
        {

               super(_resourceowner, _subscriber, StartSubscribeMessage.class.getName());
               uci = _uci;
		
	}


	public StartSubscribeMessage(String _uci, MediaSenseHost _subscriber, MediaSenseHost _resourceowner, String _msgid)
        {

               super(_resourceowner, _subscriber, _msgid, StartSubscribeMessage.class.getName());
               uci = _uci;
               

	}

    /**
     * @return the uci
     */
    public String getUci() {
        return uci;
    }

    /**
     * @param uci the uci to set
     */
    public void setUci(String _uci) {
        uci = _uci;
    }

    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(String _duration) {
        duration = _duration;
    }

    /**
     * @return the end_time
     */
    public String getEnd_time() {
        return end_time;
    }

    /**
     * @param end_time the end_time to set
     */
    public void setEnd_time(String _end_time) {
        end_time = _end_time;
    }

    /**
     * @return the start_time
     */
    public String getStart_time() {
        return start_time;
    }

    /**
     * @param start_time the start_time to set
     */
    public void setStart_time(String _start_time) {
        start_time = _start_time;
    }

    /**
     * @return the frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * @param frequency the frequency to set
     */
    public void setFrequency(int _frequency) {
        frequency = _frequency;
    }

    /**
     * @return the stale_level
     */
    public String getStale_level() {
        return stale_level;
    }

    /**
     * @param stale_level the stale_level to set
     */
    public void setStale_level(String _stalelevel) {
        stale_level = _stalelevel;
    }

}

