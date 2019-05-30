package com.hy.salon.basic.controller;

import com.github.pagehelper.PageHelper;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.service.NurseLogService;
import com.hy.salon.basic.vo.NurseLogVo;
import com.hy.salon.basic.vo.Result;
import com.hy.salon.basic.vo.TimeSheetVo;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hy/basic/nurseLog")
@Api(value = "NurseLogController| 护理控制器")
public class NurseLogController {

    @Resource(name = "nurseLogService")
    private NurseLogService nurseLogService;

    @Resource(name = "nurseLogDao")
    private NurseLogDao nurseLogDao;

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @Resource(name = "nurseLogModelDao")
    private NurseLogModelDAO nurseLogModelDao;

    @Resource(name = "salonDao")
    private SalonDao salonDao;

    @Resource(name = "picturesDao")
    private PicturesDAO picturesDao;

    @Resource(name = "stuffJobDao")
    private StuffJobDao stuffJobDao;

    @Resource(name = "jobDAO")
    private JobDAO jobDao;



    /**
     * 按门店查询护理日志
     * @param logType 日志类型 0 回访日志 1 护理日志
     * @param timeStart
     * @param timeEnd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getNurseLogBySalon",method = RequestMethod.GET)
    @ApiOperation(value="按门店查询护理日志", notes="按门店查询护理日志")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "logType", value = "logType 日志类型 0 回访日志 1 护理日志", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "timeStart", value = "开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "timeEnd", value = "结束时间", required = true, dataType = "String")
    })
    public Result getNurseLogBySalon(Integer logType,String timeStart, String timeEnd){
        Result result=new Result();
        try {
            List<NurseLogVo> list = nurseLogService.getNurseLogBySalon(logType, timeStart, timeEnd);
            result.setData(list);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
        }
        return result;
    }
    /**
     * 查询日志模板
     */
    @ResponseBody
    @RequestMapping(value = "getLogModel",method = RequestMethod.GET)
    @ApiOperation(value="查询日志模板", notes="查询日志模板")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "pageNo", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "页面大小", required = true, dataType = "Integer")

    })
    public Result getLogModel(Integer pageNo,Integer pageSize){
        if(pageNo==null){
            pageNo=1;
        }
        Result result=new Result();
        try {
            List<NurseLogModel> list = nurseLogService.getLogModel(pageNo, pageSize);
            result.setData(list);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
        }
        return result;
    }




    /**
     * 填写日志
     */
    @ResponseBody
    @RequestMapping(value = "addLog")
    @ApiOperation(value="填写回访日志", notes="填写回访日志")
    public Result addLog(NurseLog condition){
        Result result=new Result();
        try {
            nurseLogDao.insert(condition);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
        }
        return result;
    }


    /**
     * 获取日志
     */
    @ResponseBody
    @RequestMapping(value = "queryLog")
    @ApiOperation(value="获取日志", notes="获取日志")
    public Result queryLog(Long storeId,String logType,int page,int limit,String memberName,String stuffName,Long memberId){
        Result result=new Result();
        try {
            if(null==memberId){
                if(storeId == null ){
                    SystemUser user = authenticateService.getCurrentLogin();
                    Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
                    storeId=stuff.getStoreId();
                }
            }


            result.setTotal(nurseLogDao.queryLog(storeId,logType,memberName,stuffName,memberId).size());
            PageHelper.startPage(page, limit);
            List<Map<String,Object>> nurseLogList=nurseLogDao.queryLog(storeId,logType,memberName,stuffName,memberId);
            for(Map<String,Object> n:nurseLogList){
                Pictures pic=picturesDao.getOnePicturesForCondition((Long)n.get("stuffId"),new Byte("1"),new Byte("0"));
                if(null!=pic){
                    n.put("picUrl",pic.getPicUrl());
                }else{
                    n.put("picUrl","");
                }



                List<StuffJob>  stuffJobList =stuffJobDao.getStuffJobListForStuff((Long)n.get("stuffId"));
                if(stuffJobList.size() != 0 ){
                    String str1="";
                    for(StuffJob sj:stuffJobList){
                        Job job=jobDao.getJobForId(sj.getJobId());

                        if(null!=job){
                            str1=str1+" "+job.getJobName();
//                            if(n.getJobName()==null) {
//                                n.setJobName("");
//                                String str=n.getJobName()+job.getJobName();
//                                n.setJobName(str);
//                            }else{
//                                String str=n.getJobName()+","+job.getJobName();
//                                n.setJobName(str);
//                            }
                        }

                    }
                    n.put("jobName",str1);
                }


            }
            result.setData(nurseLogList);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
        }
        return result;
    }


    /**
     * 获取日志模板
     */
    @ResponseBody
    @RequestMapping(value = "queryLogModel")
    @ApiOperation(value="获取日志模板", notes="获取日志模板")
    public Result queryLogModel(Long storeId){
        Result result=new Result();
        try {
            List<NurseLogModel> nurseLogList=nurseLogModelDao.getLogModel(storeId);
            result.setData(nurseLogList);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
        }
        return result;
    }

    /**
     * 新镇日志模板
     */
    @ResponseBody
    @RequestMapping(value = "addLogModel")
    @ApiOperation(value="新镇日志模板", notes="新镇日志模板")
    public Result addLogModel(NurseLogModel condition){
        Result result=new Result();
        try {
            nurseLogModelDao.insert(condition);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
        }
        return result;
    }

    /**
     * 院长角色查看各门店日志情况
     */
    @ResponseBody
    @RequestMapping(value = "queryLogForSalon")
    @ApiOperation(value="院长角色查看各门店日志情况", notes="院长角色查看各门店日志情况")
    public Result queryLogForSalon(Long salonId,String logType){
        Result result=new Result();
        try {
            List<Salon> storeList=salonDao.getSalonForStoreId2(salonId);

            for(Salon s:storeList){
                List<Map<String,Object>> nurseLogList=nurseLogDao.queryLog(s.getRecordId(),logType,null,null,null);
                s.setLogSize(nurseLogList.size());
            }

          result.setData(storeList);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
        }
        return result;
    }




}
