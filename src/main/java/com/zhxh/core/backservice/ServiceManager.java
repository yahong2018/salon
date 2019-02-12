package com.zhxh.core.backservice;


import com.zhxh.core.utils.Logger;

import java.util.List;

/**
 * Created by yahong on 14-6-18.
 */
public class ServiceManager {
    public boolean init() {
        for (BaseService service : this.getServiceList()) {
            if (!service.init()) {
                return false;
            }
        }
        Logger.info("系统服务已全部初始化...");
        return true;
    }

    public void start() {
        for (BaseService service : this.getServiceList()) {
            service.start();
        }
        Logger.info("系统服务已全部启动...");
    }

    public void stop() {
        for (int i = this.getServiceList().size(); i > 0; i--) {
            BaseService service = this.getServiceList().get(i - 1);
            service.stop();
        }
        /*
        for (BaseService service : this.getServiceList()) {
            service.stop();
        }
        */
        Logger.info("系统服务已全部停止...");
    }

    public void clean() {
        for (BaseService service : this.getServiceList()) {
            service.clean();
        }
        Logger.info("系统服务已全部清理...");
    }

    List<BaseService> serviceList;

    public List<BaseService> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<BaseService> serviceList) {
        this.serviceList = serviceList;
    }
}
