package com.zhxh.core.backservice;

import com.zhxh.core.utils.AutoResetEvent;
import com.zhxh.core.utils.Logger;

/**
 * Created by yahong on 14-6-18.
 */
public abstract class ThreadService implements BaseService {
    protected ThreadService() {
        this.setThreadPriority(Thread.NORM_PRIORITY);
        this.setTerminated(true);
    }

    public synchronized boolean start() {
        if (this.getStatus() == SERVICE_RUNNING) {
            return false;
        }
        try {
            this.beforeStart();
        } catch (Exception e3) {
            Logger.error(e3);
            return false;
        }
        this.stopEvent.set();
        this.thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (waitOne() != 0) {
                    return;
                }

                afterStart();
                Logger.debug(getName() + "进入服务循环");
                while (!isTerminated()) {
                    doInternalRun();

                    sleep();
                }

                stopEvent.set();
                Logger.debug(getName() + "退出服务循环");
            }

            private int waitOne() {
                startEvent.set();
                try {
                    stopEvent.waitOne();
                    return 0;
                } catch (InterruptedException e1) {
                    Logger.error(getName() + "stopEvent.waitOne出现异常", e1);
                    stopEvent.set();
                }
                return 1;
            }

            private void sleep() {
                try {
                    Thread.sleep(getThreadSleepTime());
                } catch (InterruptedException e) {
                    Logger.error(getName() + "线程内部循环 Sleep 出现异常", e);
                }
            }
        });
        this.thread.setPriority(this.getThreadPriority());
        this.thread.setName(this.getName());
        this.thread.start();
        this.setTerminated(false);

        try {
            this.startEvent.waitOne();
        } catch (Exception e2) {
            Logger.error(this.getName() + "startEvent.waitOne出现异常", e2);
            return false;
        }
        Logger.debug(getName() + "已启动");
        this.setStatus(SERVICE_RUNNING);
        return true;
    }

    @Override
    public synchronized boolean stop() {
        if (this.getStatus() == SERVICE_NOT_RUN) {
            return false;
        }

        this.setTerminated(true);
        try {
            this.beforeStop();
            this.stopEvent.waitOne();
        } catch (Exception e1) {
            Logger.error(this.getName() + "stop出现异常", e1);
            return false;
        }

        this.afterStop();
        Logger.debug(getName() + "已停止");
        this.setStatus(SERVICE_NOT_RUN);
        return true;
    }


    protected abstract void doInternalRun();

    @Override
    public boolean init() {
        if (this.getStatus() == SERVICE_RUNNING) {
            return false;
        }

        return this.doInternalInit();
    }

    protected abstract boolean doInternalInit();

    protected void beforeStart() throws Exception {
    }

    protected void afterStart() {
    }

    protected void beforeStop() throws Exception {
    }

    protected void afterStop() {
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThreadPriority() {
        return threadPriority;
    }

    public void setThreadPriority(int threadPriority) {
        this.threadPriority = threadPriority;
    }

    public int getThreadSleepTime() {
        return threadSleepTime;
    }

    public void setThreadSleepTime(int threadSleepTime) {
        this.threadSleepTime = threadSleepTime;
    }

    public void interrupt() {
        if (this.thread != null) {
            this.thread.interrupt();
        }
    }

    private int threadPriority;

    private boolean terminated = true;
    private Thread thread = null;
    private String name;
    private AutoResetEvent stopEvent = new AutoResetEvent();
    private AutoResetEvent startEvent = new AutoResetEvent(true);

    private int threadSleepTime = 0;

    protected boolean getTerminated() {
        return this.terminated;
    }

    @Override
    public int getStatus() {
        return this.status;
    }

    private int status;
    public void setStatus(int status) {
        this.status = status;
    }

    private String serviceId;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
