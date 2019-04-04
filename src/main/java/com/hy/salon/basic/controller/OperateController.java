package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.dao.JobDAO;
import com.hy.salon.basic.dao.OperateLogDao;
import com.hy.salon.basic.entity.Job;
import com.hy.salon.basic.entity.OperateLog;
import com.hy.salon.basic.service.OperateLogService;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequest;
import com.zhxh.core.web.ListRequestBaseHandler;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hy/basic/operateLog")
@Api(value = "OperateController|操作日志")
public class OperateController extends SimpleCRUDController<OperateLog> {

    @Resource(name="operateLogDao")
    private OperateLogDao operateLogDao;

    @Resource(name="operateLogService")
    private OperateLogService operateLogService;


    @Override
    protected BaseDAOWithEntity<OperateLog> getCrudDao() {
        return operateLogDao;
    }


    /**
     * 获取日志
     * recordId ,门店id
     */
    @RequestMapping(value = "getOperateLog",method = RequestMethod.GET)
    @ApiOperation(value="获取日志", notes="获取日志")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "用户名", value = "用户名", required = true, dataType = "String"),
    })
    @ResponseBody
    public ExtJsResult getAdminScheduleByTime(HttpServletRequest request) {
        ExtJsResult StoreList=operateLogService.getScheduleForStoreIdSystem(request,new ListRequestBaseHandler() {
            @Override
            public List getByRequest(ListRequest listRequest) {
                return operateLogDao.getPageList(listRequest.toMap(), null);
            }

            @Override
            public int getRequestListCount(ListRequest listRequest) {
                return operateLogDao.getPageListCount(listRequest.toMap(), null);
            }
        });

        return  StoreList;
    }



}
