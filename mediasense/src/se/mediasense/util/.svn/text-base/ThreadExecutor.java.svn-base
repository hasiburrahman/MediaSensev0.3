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
package se.mediasense.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadExecutor 
{
    
private ExecutorService executorservice = new ThreadPoolExecutor
                                      (
                                       10, // core thread pool size
                                       100, // maximum thread pool size
                                       1, // time to wait before resizing pool
                                       TimeUnit.MINUTES,
                                       new ArrayBlockingQueue<Runnable>(10, true),
                                       new ThreadPoolExecutor.CallerRunsPolicy()
                                      );    
private static ThreadExecutor sharedinstance = new ThreadExecutor();

private ThreadExecutor()
{
    
}        

    /**
     *
     * @return
     */
    public static ThreadExecutor SharedInstance()
{
    
    return sharedinstance;
    
}


    /**
     *
     * @param r
     */
    public void submit(Runnable r)
{
    
    executorservice.submit(r);
    
}

    /**
     *
     * @param c
     * @return
     */
    public Future submit(Callable c)
{
    
    return executorservice.submit(c);
    
}

    /**
     *
     */
    public void shutdown()
{
        
            executorservice.shutdownNow();
     
    
}

}
