package com.zhxh.core.utils;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by yahong on 14-6-18.
 */
public class AutoResetEvent {
    private Semaphore event;
    private Integer mutex;

    public AutoResetEvent(){
        init(false);
    }

    public AutoResetEvent(boolean signalled)
    {
        init(signalled);
    }

    private void init(boolean signalled) {
        event = new Semaphore(signalled?1:0);
        mutex = new Integer(-1);
        /*
        if(signalled){
        	try {
				this.waitOne();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        */
    }

    public void set()
    {
        synchronized (mutex)
        {
            if (event.availablePermits() == 0)
            {
                event.release();
            }
        }
    }

    public void waitOne() throws InterruptedException
    {
        event.acquire();
    }

    public boolean waitOne(int timeout, TimeUnit unit) throws InterruptedException
    {
        return event.tryAcquire(timeout, unit);
    }

    public boolean isSignalled()
    {
        return event.availablePermits() > 0;
    }

    public boolean waitOne(int timeout) throws InterruptedException
    {
        return waitOne(timeout, TimeUnit.MILLISECONDS);
    }
}
