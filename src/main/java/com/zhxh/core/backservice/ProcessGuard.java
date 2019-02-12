package com.zhxh.core.backservice;

import com.zhxh.core.env.SysEnv;
import com.zhxh.core.utils.FileLock;
import com.zhxh.core.utils.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by yahong on 14-6-18.
 */
public  class ProcessGuard extends ThreadService {
    public ProcessGuard() {
        super.setName("系统守护线程");
    }

    @Override
    protected void doInternalRun() {
        if (this.isRunning()) {
            try {
                Logger.debug("目标系统正在运行...");
                Thread.sleep(this.getCheckLoopTime());
            } catch (Exception e) {
                // 屏蔽掉线程阻断的异常信息...
            }
            return;
        }
        String appName = this.getTargetApplication();
        String appFullPath = ProcessGuard.class.getResource(appName).getPath();
        Logger.info(appName + "已退出，启动之...");
        Runtime rt = Runtime.getRuntime();

        try {
            processReader = null;
            targetProcess = rt.exec(SysEnv.getShellExecuteName() + " " + appFullPath);
            processReader = new BufferedReader(new InputStreamReader(targetProcess.getInputStream()));

            //targetProcess.waitFor();
            Thread.sleep(this.getCheckLoopTime());
        } catch (Exception e) {
            if (!(e instanceof InterruptedException)) {
                Logger.error(this.getName() + "监视线程出现异常", e);
            }
        }
    }

    @Override
    protected boolean doInternalInit() {
        return true;
    }

    Process targetProcess = null;
    Thread targetProcessOutputCaptureTimer = null;
    BufferedReader processReader = null;

    @Override
    protected void afterStart() {
        super.afterStart();
        targetProcessOutputCaptureTimer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!getTerminated()) {
                    try {
                        if (processReader != null) {
                            String line = null;
                            while ((line = processReader.readLine()) != null) {
                                Logger.info(line);
                            }
                        }
                        Thread.sleep(5 * 1000);
                    } catch (Exception e) {
                        //logger.error(e);
                        processReader = null;
                    }
                }
            }
        });
        targetProcessOutputCaptureTimer.start();
    }

    @Override
    protected void afterStop() {
        super.afterStop();
    }

    @Override
    protected void beforeStop() {
        this.interrupt();
        if (targetProcess != null) {
            targetProcess.destroy();
            targetProcess = null;
        }
    }

    private int checkLoopTime;

    public int getCheckLoopTime() {
        return checkLoopTime;
    }

    public void setCheckLoopTime(int checkLoopTime) {
        this.checkLoopTime = checkLoopTime;
    }

    private boolean isRunning() {
        try {
            if (this.getTargetLock().lock()) {
                return true;
            }

        } catch (Exception e) {
            Logger.error(this.getName() + "检查进程时候运行的时候出现异常", e);
        }finally {
            this.getTargetLock().release();
        }
        return false;
    }

    private String targetApplication;

    public String getTargetApplication() {
        return targetApplication;
    }

    public void setTargetApplication(String targetApplication) {
        this.targetApplication = targetApplication;
    }

    private FileLock targetLock;

    public FileLock getTargetLock() {
        return targetLock;
    }

    public void setTargetLock(FileLock targetLock) {
        this.targetLock = targetLock;
    }

    @Override
    public void clean() {
        this.targetLock.release();
    }
}
