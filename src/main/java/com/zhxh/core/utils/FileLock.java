package com.zhxh.core.utils;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Created by yahong on 14-6-18.
 */
public class FileLock {
    public synchronized boolean lock() {
        String filePath = FileLock.class.getResource( this.getTarget()).getPath();
        try {
            randomAccessFile = new RandomAccessFile(filePath, "rw");
            lockFileChannel = randomAccessFile.getChannel();
            fileLock = lockFileChannel.tryLock();
            if (fileLock != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public synchronized void release() {
        try {
            if (randomAccessFile != null)
                fileLock.release();
            if (lockFileChannel != null)
                lockFileChannel.close();
            if (randomAccessFile != null)
                randomAccessFile.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            randomAccessFile = null;
            fileLock = null;
            lockFileChannel = null;
        }
    }

    private RandomAccessFile randomAccessFile;
    FileChannel lockFileChannel;
    java.nio.channels.FileLock fileLock;

    private String target;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
