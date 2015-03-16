/*
 * Copyright 2012 The MediaSense Team
 * 2012-10-02 Theo Kanter, Ulf Jennehag, Patrik Österberg, Stefan Forsström, Victor Kardeby, Jamie Walters
 * This file is part of The MediaSense Platform.
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

package se.mediasense.disseminationlayer.communication;


@SuppressWarnings("serial")
public class DestinationNotReachableException extends Exception {
	protected String errorMsg = "DestinationNotReachableException";
	
	public DestinationNotReachableException() {
		super();
	}
	
	public DestinationNotReachableException(String _errorMsg) {
		errorMsg = _errorMsg;
	}

	@Override
	public String getMessage() {
		return errorMsg;
	}
}
