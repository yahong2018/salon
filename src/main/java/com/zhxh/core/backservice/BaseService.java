package com.zhxh.core.backservice;

/**
 * Created by yahong on 14-6-18.
 */
public interface BaseService {
    public final static int SERVICE_NOT_RUN=0;
    public final static int SERVICE_RUNNING=2;

    public boolean init();
    public boolean start();
    public boolean stop();
    public void clean();
    public int getStatus();
}
