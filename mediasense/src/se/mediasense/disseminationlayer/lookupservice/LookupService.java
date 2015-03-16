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

package se.mediasense.disseminationlayer.lookupservice;

import se.mediasense.distribution.UCI;
import se.mediasense.util.LookupException;


public interface LookupService{
	
	public abstract void run();


	public abstract UCI resolveAsync(String uci);

	public abstract UCI resolveSync(String uci);

	public abstract void registerAsync(UCI uci) throws LookupException;

	public abstract void registerSync(UCI uci) throws LookupException;

	public abstract void deleteAsync(UCI uci) throws LookupException;

	public abstract void deleteSync(UCI uci) throws LookupException;

	public abstract void updateAsync(UCI uci) throws LookupException;

	public abstract void updateSync(UCI uci) throws LookupException;

	public abstract void remove(UCI uci) throws LookupException;

	public abstract void update(UCI uci) throws LookupException;

	public abstract void shutdown();

	public abstract void init();

	public abstract void register(UCI uci) throws LookupException;




}
