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
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Resource(name = "reservationDao")
    private ReservationDao reservationDao;

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
            //该总结当天0点0分
            SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.HOUR_OF_DAY,0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date zero = calendar.getTime();
            String startTime = sDateFormat.format(zero);
            //该总结当天23点59分
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(new Date());
            calendar2.set(Calendar.HOUR_OF_DAY,23);
            calendar2.set(Calendar.MINUTE, 59);
            calendar2.set(Calendar.SECOND, 59);
            Date zero2 = calendar2.getTime();
            String endTime = sDateFormat.format(zero2);

            //该总结当月1号
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.MONTH, 0);
            c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
            c.set(Calendar.HOUR_OF_DAY,0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            String first = sDateFormat.format(c.getTime());

            //该总结当月30号
            Calendar c2 = Calendar.getInstance();
            c2.setTime(new Date());
            c2.add(Calendar.MONTH, 0);
            c2.set(Calendar.DAY_OF_MONTH,30);//设置为1号,当前日期既为本月第一天
            c2.set(Calendar.HOUR_OF_DAY,23);
            c2.set(Calendar.MINUTE, 59);
            c2.set(Calendar.SECOND, 59);
            String first2 = sDateFormat.format(c2.getTime());


            String sTime="";
            String eTime="";
            if(condition.getSummaryType()==0){
                sTime=first;
                eTime=first2;

                List<WorkSummary> workSummaryList=workSummaryDao.getSummaryForStuff(condition.getStuffId(),sTime,eTime,condition.getSummaryType());
                if(workSummaryList.size()!=0){
                    result.setMsg("本月已写过总结");
                    result.setMsgcode(StatusUtil.ERROR);
                    result.setSuccess(false);
                    return result;
                }


            }else{
                sTime=startTime;
                eTime=endTime;
                List<WorkSummary> workSummaryList=workSummaryDao.getSummaryForStuff(condition.getStuffId(),sTime,eTime,condition.getSummaryType());
                if(workSummaryList.size()!=0){
                    result.setMsg("本日已写过总结");
                    result.setMsgcode(StatusUtil.ERROR);
                    result.setSuccess(false);
                    return result;
                }
            }





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
    public Result queryWorkSummary(Long recordId,Integer summaryType){
        Result result=new Result();
        try {
            List<WorkSummary> workSummaryList=workSummaryDao.getSummaryForStuff(recordId,null,null,summaryType);
            for(WorkSummary w:workSummaryList){
                //该总结当天0点0分
                SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(w.getCreateDate());
                calendar.set(Calendar.HOUR_OF_DAY,0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                Date zero = calendar.getTime();
                String startTime = sDateFormat.format(zero);
                //该总结当天23点59分
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(w.getCreateDate());
                calendar2.set(Calendar.HOUR_OF_DAY,23);
                calendar2.set(Calendar.MINUTE, 59);
                calendar2.set(Calendar.SECOND, 59);
                Date zero2 = calendar2.getTime();
                String endTime = sDateFormat.format(zero2);

                //该总结当月1号
                Calendar c = Calendar.getInstance();
                c.setTime(w.getCreateDate());
                c.add(Calendar.MONTH, 0);
                c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
                c.set(Calendar.HOUR_OF_DAY,0);
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                String first = sDateFormat.format(c.getTime());

                //该总结当月30号
                Calendar c2 = Calendar.getInstance();
                c2.setTime(w.getCreateDate());
                c2.add(Calendar.MONTH, 0);
                c2.set(Calendar.DAY_OF_MONTH,30);//设置为1号,当前日期既为本月第一天
                c2.set(Calendar.HOUR_OF_DAY,23);
                c2.set(Calendar.MINUTE, 59);
                c2.set(Calendar.SECOND, 59);
                String first2 = sDateFormat.format(c2.getTime());


                String sTime="";
                String eTime="";
                if(w.getSummaryType()==0){
                    sTime=first;
                    eTime=first2;
                }else{
                    sTime=startTime;
                    eTime=endTime;
                }



                Map<String,Object> stuffAmountMap=new HashMap<>();
                Double consumptionAmount=new Double(0);
                Double paymentAmount=new Double(0);
                //通过该员工的总消费
                Map<String,Object>  amountMap1=CardPurchaseDao.queryAmountForStuff(recordId,null,sTime,eTime);
                if(null != amountMap1){
                    consumptionAmount=(Double) amountMap1.get("amount");
                }

                //startTime
                Map<String,Object>  amountMap3=paymentItemDao.queryPaymentAmountForStuff(recordId,sTime,eTime);
                if(null != amountMap3){
                    paymentAmount=(Double) amountMap3.get("amount");
                }
                //获取护理日志和回访日志的数量
                //回访日志
                List<NurseLog> NurseLogList1=nurseLogDao.getNurseLogForStuffId(recordId,"0",sTime,eTime);
                //护理日志
                List<NurseLog> NurseLogList2=nurseLogDao.getNurseLogForStuffId(recordId,"1",sTime,eTime);

                //服务次数
                List<Reservation> reservationList=reservationDao.getReservationForStuffId(recordId,"3",sTime,eTime);

                stuffAmountMap.put("serviceSize",reservationList.size());
                stuffAmountMap.put("paymentAmount",paymentAmount);
                stuffAmountMap.put("consumptionAmount",consumptionAmount);
                stuffAmountMap.put("returnLogSize",NurseLogList1.size());
                stuffAmountMap.put("nursingLogSize",NurseLogList2.size());
                w.setStuffAmountMap(stuffAmountMap);

            }

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
            List<NurseLog> NurseLogList1=nurseLogDao.getNurseLogForStuffId(recordId,"0",null,null);
            //护理日志
            List<NurseLog> NurseLogList2=nurseLogDao.getNurseLogForStuffId(recordId,"1",null,null);

            //服务次数
            List<Reservation> reservationList=reservationDao.getReservationForStuffId(recordId,"3",null,null);

            stuffAmountMap.put("serviceSize",reservationList.size());
            stuffAmountMap.put("returnLogSize",NurseLogList1.size());
            stuffAmountMap.put("nursingLogSize",NurseLogList2.size());
            stuffAmountMap.put("paymentAmount",paymentAmount);
            stuffAmountMap.put("consumptionAmount",consumptionAmount);

            result.setData(stuffAmountMap);
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
