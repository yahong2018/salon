package com.hy.salon.basic.controller;

import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.service.WorkSummaryService;
import com.hy.salon.basic.vo.Result;
import com.hy.salon.basic.vo.SalonVo;
import com.hy.salon.basic.vo.StuffVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hy/basic/workSummary")
@Api(value = "WorkSummaryController| 员工总结控制器")
public class WorkSummaryController {
    @Resource(name="workSummaryService")
    private WorkSummaryService workSummaryService;

    @Resource(name="workSummaryDao")
    private WorkSummaryDao workSummaryDao;

    @Resource(name = "salonDao")
    private SalonDao salonDao;

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    @Resource(name = "stuffJobDao")
    private StuffJobDao stuffJobDao;

    @Resource(name = "jobDAO")
    private JobDAO jobDao;

    @Resource(name = "cardPurchaseDao")
    private CardPurchaseDao CardPurchaseDao;

    @Resource(name = "paymentItemDao")
    private PaymentItemDao paymentItemDao;

    @Resource(name = "nurseLogDao")
    private NurseLogDao nurseLogDao;

    /**
     * 按门店查询当天员工已写总结数
     */
    @ResponseBody
    @RequestMapping(value = "getWorkSummaryBySalonId",method = RequestMethod.GET)
    @ApiOperation(value="按门店查询当天员工已写总结数", notes="按门店查询当天员工已写总结数")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "门店id", required = true, dataType = "Long")
    })
    public Result getWorkSummaryBySalonId(Long recordId){
        Result result=new Result();
        try {
            List<StuffVo> list = workSummaryService.getWorkSummaryBySalonId(recordId);
            result.setData(list);
            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }
    /**
     *查询当月各门店员工已写工作总结数
     */
    @ResponseBody
    @RequestMapping(value = "getWorkSummary",method = RequestMethod.GET)
    @ApiOperation(value="查询当月各门店员工已写工作总结数", notes="查询当月各门店员工已写工作总结数")
    public Result getWorkSummary(Long storeId){
        Result result=new Result();
        try {
            List<SalonVo> list = workSummaryService.getWorkSummary(storeId);
            result.setData(list);
            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }


    /**
     * 填写工作总结
     */
    @ResponseBody
    @RequestMapping(value = "addWorkSummary")
    @ApiOperation(value="填写工作总结", notes="填写工作总结")
    public Result addWorkSummary(WorkSummary condition){
        Result result=new Result();
        try {
            condition.setCurMonth(new Date());
            workSummaryDao.insert(condition);
            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }


    /**
     * 院长或店长工作总结统计页
     */
    @ResponseBody
    @RequestMapping(value = "WorkSummaryTotal")
    @ApiOperation(value="填写工作总结", notes="填写工作总结")
    public Result WorkSummaryTotal(Long salonId,String jobLevel){
        Result result=new Result();
        try {
            if(null!=jobLevel) {
                if ("0".equals(jobLevel)) {
                    List<Salon> salonList= salonDao.getSalonForStoreId2(salonId);
                    for(Salon s:salonList){
                        List<Stuff> stuffList=stuffDao.getStuffForStoreId(s.getRecordId());
                        s.setStuffSize(stuffList.size());

                        Map<String,Object> summarySize=workSummaryDao.querySummarySize(s.getRecordId());
                        if(null != summarySize){
                            s.setSummarySize(((Long) summarySize.get("summarySize")).intValue());
                        }else{
                            s.setSummarySize(0);
                        }
                    }
                    result.setData(salonList);

                } else {
                    List<Salon> salonList= salonDao.getSalonForStoreId3(salonId);
                    for(Salon s:salonList){
                        List<Stuff> stuffList=stuffDao.getStuffForStoreId(s.getRecordId());
                        s.setStuffSize(stuffList.size());

                        Map<String,Object> summarySize=workSummaryDao.querySummarySize(s.getRecordId());
                        if(null != summarySize){
                            s.setSummarySize(((Long) summarySize.get("summarySize")).intValue());
                        }else{
                            s.setSummarySize(0);
                        }
                    }
                    result.setData(salonList);

                }
            }else{

            }
            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }


    /**
     * 获取工作总结按每个员工
     */
    @ResponseBody
    @RequestMapping(value = "queryWorkSummaryForStuff")
    @ApiOperation(value="获取工作总结按每个员工", notes="获取工作总结按每个员工")
    public Result queryWorkSummaryForStuff(Long recordId,String startTime,String endTime){
        Result result=new Result();
        try {

            List<Stuff> stuffList=stuffDao.getStuffForStoreId(recordId);
            for(Stuff s:stuffList){
                List<WorkSummary> workSummaryList=workSummaryDao.getSummaryForStuff(s.getRecordId(),startTime,endTime,null);
                s.setWorkSummarySize(workSummaryList.size());

                List<StuffJob>  stuffJobList =stuffJobDao.getStuffJobListForStuff(s.getRecordId());
                if(stuffJobList.size() != 0 ){
                    for(StuffJob sj:stuffJobList){
                        Job job=jobDao.getJobForId(sj.getJobId());
                        if(s.getJobName()==null) {
                            s.setJobName("");
                            String str=s.getJobName()+job.getJobName();
                            s.setJobName(str);
                        }else{
                            String str=s.getJobName()+","+job.getJobName();
                            s.setJobName(str);
                        }
                    }

                }


            }
            result.setData(stuffList);
            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }


    /**
     * 获取该员工的工作总结
     */
    @ResponseBody
    @RequestMapping(value = "queryWorkSummary")
    @ApiOperation(value="获取该员工的工作总结", notes="获取该员工的工作总结")
    public Result queryWorkSummary(Long recordId,Long summaryType){
        Result result=new Result();
        try {
            List<WorkSummary> workSummaryList=workSummaryDao.getSummaryForStuff(recordId,null,null,summaryType);

            result.setData(workSummaryList);
            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }


    /**
     * 获取该员工的总统计
     */
    @ResponseBody
    @RequestMapping(value = "queryTotalForStuff")
    @ApiOperation(value="获取该员工的总统计", notes="获取该员工的总统计")
    public Result queryTotalForStuff(Long recordId){
        Result result=new Result();
        try {


            Map<String,Object> stuffAmountMap=new HashMap<>();
            Double consumptionAmount=new Double(0);
            Double paymentAmount=new Double(0);
            //通过该员工的总消费
            Map<String,Object>  amountMap1=CardPurchaseDao.queryAmountForStuff(recordId,null,null,null);
            if(null != amountMap1){
                consumptionAmount=(Double) amountMap1.get("amount");
            }

            //通过该员工的总卡耗
            Map<String,Object>  amountMap3=paymentItemDao.queryPaymentAmountForStuff(recordId,null,null);
            if(null != amountMap3){
                paymentAmount=(Double) amountMap3.get("amount");
            }

            //获取护理日志和回访日志的数量
            //回访日志
            List<NurseLog> NurseLogList1=nurseLogDao.getNurseLogForStuffId(recordId,"0");
            //护理日志
            List<NurseLog> NurseLogList2=nurseLogDao.getNurseLogForStuffId(recordId,"1");


            stuffAmountMap.put("returnLogSize",NurseLogList1.size());
            stuffAmountMap.put("nursingLogSize",NurseLogList2.size());
            stuffAmountMap.put("paymentAmount",paymentAmount);
            stuffAmountMap.put("consumptionAmount",consumptionAmount);


            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }



}
