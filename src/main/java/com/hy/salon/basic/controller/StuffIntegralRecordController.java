package com.hy.salon.basic.controller;

import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.StuffIntegralRecordDao;
import com.hy.salon.basic.entity.StuffIntegralRecord;
import com.hy.salon.basic.service.StuffIntegralRecordService;
import com.hy.salon.basic.vo.Result;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/hy/basic/stuffIntegralRecord")
@Api(value = "StuffIntegralRecordController| 积分控制器")
public class StuffIntegralRecordController extends SimpleCRUDController<StuffIntegralRecord> {
    @Resource(name = "stuffIntegralRecordDao")
    private StuffIntegralRecordDao stuffIntegralRecordDao;
    @Resource(name = "stuffIntegralRecordService")
    private StuffIntegralRecordService stuffIntegralRecordService;
    @Override
    protected BaseDAOWithEntity<StuffIntegralRecord> getCrudDao() {
        return stuffIntegralRecordDao;
    }
    /**
     * 新增奖励积分
     */
    @ResponseBody
    @RequestMapping(value = "addIntegral",method = RequestMethod.POST)
    @ApiOperation(value="新增奖励积分", notes="新增奖励积分")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "stuffId", value = "哪个员工", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "metter", value = "在哪里，做了些什么事", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "getPoint", value = "得到的积分总数", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "getById", value = "谁给的 ,这个id来源于员工表", required = true, dataType = "Long")
    })
    public Result addIntegral(@RequestBody StuffIntegralRecord stuffIntegralRecord){
        Result result=new Result();
        try {
            stuffIntegralRecordService.addIntegralc(stuffIntegralRecord);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
        }catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
        }
        return result;
    }
}
