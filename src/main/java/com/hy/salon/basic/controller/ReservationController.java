package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.service.ReservationService;
import com.hy.salon.basic.service.ShiftService;
import com.hy.salon.basic.service.StoreRoomService;
import com.hy.salon.basic.util.DateString;
import com.hy.salon.basic.vo.ReservationVo;
import com.hy.salon.basic.vo.Result;
import com.hy.salon.basic.vo.StoreReservation;
import com.hy.salon.basic.vo.StuffVo;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequest;
import com.zhxh.core.web.ListRequestBaseHandler;
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
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hy/basic/reservation")
@Api(value = "ReservationController| 預約控制器")
public class ReservationController {
    @Resource(name = "reservationService")
    private ReservationService reservationService;
    @Resource(name = "reservationDao")
    private ReservationDao reservationDao;

    @Resource(name = "reservationItemDao")
    private ReservationItemDao reservationItemDao;
    @Resource(name = "scheduleDao")
    private ScheduleDao scheduleDao;

    @Resource(name = "shiftDao")
    private ShiftDao shiftDao;

    @Resource(name="stuffDao")
    private StuffDao stuffDao;

    @Resource(name = "storeRoomService")
    private StoreRoomService storeRoomService;



    @Resource(name = "serviceDao")
    private ServiceDAO serviceDao;
/*    @ResponseBody
    @RequestMapping(value = "getReservationById",method = RequestMethod.POST)
    public Map getReservationById(){
        Map map=new HashMap();
        List<Reservation> reservation = reservationService.getReservationById();
        map.put("msg","查询成功");
        map.put("success",true);
        map.put("msgcode","0");
        map.put("data",reservation);
        return map;
    }*/
/*    *//**
     * 按日期获取每个门店的预约汇总
     *//*
    @ResponseBody
    @RequestMapping(value = "getReservationByTimeStart",method = RequestMethod.GET)
    public List<StoreReservation> getReservationByTimeStart(String reservationDate){
        reservationDate = "20190306";
        List<StoreReservation> list =  reservationService.getStoreReservationByTimeStart(reservationDate);
        for(StoreReservation sr : list){
            long  SalonId   = sr.getSalonId();
            List<Holiday> listH = holidayService.getBySalonIdDayTime(SalonId,reservationDate);
            if(listH.size()>0){
                sr.setIsHoliday("1");//是工作日
            }else{
                sr.setIsHoliday("0");
            }
        }
        return list;
    }*/

    /**
     * 后管获取预约列表查询条件
     * @param request
     * @param recordId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getReservationOtherInfo",method = RequestMethod.GET)
    @ApiOperation(value="后管获取预约列表查询条件", notes="后管获取预约列表查询条件")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "门店id", required = true, dataType = "Long"),
    })
    public JSONObject getReservationOtherInfo(HttpServletRequest request, Long recordId){
        JSONObject jsonObject = new JSONObject();
        try {
            String where="store_id=#{storeId}";
            Map parameters = new HashMap();
            parameters.put("storeId", recordId);

            List<Stuff> list =  stuffDao.getByWhere(where,parameters);
            jsonObject.put("listStuff",list);

            List<StoreRoom> roomList=storeRoomService.getRoomForStoreId(recordId+"");
            jsonObject.put("roomList",roomList);
            List<Service> serviceList= serviceDao.queryServiceForId(recordId);
            jsonObject.put("serviceList",serviceList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "getReservationVoList",method = RequestMethod.GET)
    @ApiOperation(value="查询当天一门店有预约员工列表", notes="查询当天一门店有预约员工列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "门店id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "oneDay", value = "一天", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "toDays", value = "多天", required = true, dataType = "String")
    })
    public Result getReservationVoList(HttpServletRequest request,Long recordId,String oneDay ,String toDays){
        Result result = new Result();
        try {
            //JSONArray list = reservationService.getStuffItem(recordId, timeStart, timeEnd);
            result = reservationService.getReservationVoList(request, recordId, oneDay , toDays);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode("200");
        }
        return result;
    }



    /**
     * 按日期获取每个门店的预约汇总--店长
     */
    @ResponseBody
    @RequestMapping(value = "getReservationByTime",method = RequestMethod.GET)
    @ApiOperation(value="按日期获取每个门店的预约汇总", notes="按日期获取每个门店的预约汇总")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "timeStart", value = "開始時間", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "timeEnd", value = "結束時間", required = true, dataType = "String")
    })
    public Result getReservationByTime(String timeStart ,String timeEnd){
        Result result=new Result();
        try {
            List<ReservationVo> list = reservationService.getReservationByTime(timeStart, timeEnd);
            result.setMsgcode("0");
            result.setSuccess(true);
            result.setData(list);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode("200");
        }
        return result;
    }

    /**
     * 查询当天一门店有预约员工列表--店长（统计员工有几个预约）
     */
    @ResponseBody
    @RequestMapping(value = "getStuff",method = RequestMethod.GET)
    @ApiOperation(value="查询当天一门店有预约员工列表", notes="查询当天一门店有预约员工列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "门店id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "timeStart", value = "開始時間", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "timeEnd", value = "結束時間", required = true, dataType = "String")
    })
    public Result getStuff(Long recordId,String timeStart ,String timeEnd){
        Result result=new Result();
        try {
            List<StuffVo> list = reservationService.getStuff(recordId, timeStart, timeEnd);
            result.setMsgcode("0");
            result.setSuccess(true);
            result.setData(list);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode("200");
        }
        return result;
    }

/**
     * 查询员工下所有会员预约情况列表
     *//*

    @ResponseBody
    @RequestMapping(value = "getReservationList",method = RequestMethod.GET)
    @ApiOperation(value="查询员工下所有会员预约情况列表", notes="查询员工下所有会员预约情况列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "stuffId", value = "员工id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "timeStart", value = "開始時間", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "timeEnd", value = "結束時間", required = true, dataType = "String")
    })
    public Result getReservationList(Long stuffId,String timeStart ,String timeEnd){
        Result result=new Result();
        try {
            List<ReservationVo> list = reservationService.getReservationList(stuffId, timeStart, timeEnd);
            result.setMsgcode("0");
            result.setSuccess(true);
            result.setData(list);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode("200");
        }
        return result;
    }
*/


    /**
     * 查询当天一门店有预约员工列表--员工（具体到几点，什么项目）
     */
    @ResponseBody
    @RequestMapping(value = "getStoreStuffItem",method = RequestMethod.GET)
    @ApiOperation(value="查询当天一门店有预约员工列表", notes="查询当天一门店有预约员工列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "门店id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "timeStart", value = "開始時間", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "timeEnd", value = "結束時間", required = true, dataType = "String")
    })
    public Result getStoreStuffItem(Long recordId,String timeStart ,String timeEnd){
        Result result=new Result();
        try {
            JSONArray list = reservationService.getStuffItem(recordId, timeStart, timeEnd);
            result.setMsgcode("0");
            result.setSuccess(true);
            result.setData(list);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode("200");
        }
        return result;
    }


    /**
     * 查询当天个人有预约--员工（具体到几点，什么项目）
     */
    @ResponseBody
    @RequestMapping(value = "getStuffItem",method = RequestMethod.GET)
    @ApiOperation(value="查询当天一门店有预约员工列表", notes="查询当天一门店有预约员工列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "员工id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "timeStart", value = "開始時間", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "timeEnd", value = "結束時間", required = true, dataType = "String")
    })
    public Result getStuffItem(Long recordId,String timeStart ,String timeEnd){
        Result result=new Result();
        try {
            JSONArray list = reservationService.getStuffItem(recordId, timeStart, timeEnd);
            result.setMsgcode("0");
            result.setSuccess(true);
            result.setData(list);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode("200");
        }
        return result;
    }
    /**
     * 查询当天个人一个预约具体详情--员工（具体到几点，什么项目）
     */
    @ResponseBody
    @RequestMapping(value = "getOneStuffItem",method = RequestMethod.GET)
    @ApiOperation(value="查询当天个人一个预约具体详情--员工（具体到几点，什么项目）", notes="查询当天个人一个预约具体详情--员工（具体到几点，什么项目）")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "员工id", required = true, dataType = "Long"),
    })
    public Result getOneStuffItem(Long recordId){
        Result result=new Result();
        try {
            Map rMap = new HashMap();
            String rwhere="record_id=#{record_id} ";
            rMap.put("record_id", recordId);
            Reservation reservation = reservationDao.getOne(rwhere, rMap);
            List<Map>  mapService =  reservationDao.getServiceByReservationId(reservation.getRecordId());
            JSONObject jsonObject  = new JSONObject();
            String serviceIds = "";
            for(Map map :mapService){
                String serviceId =  (String) map.get("recordId");
                serviceIds=serviceIds+serviceId+",";
            }
            jsonObject.put("memberId",reservation.getMemberId());
            jsonObject.put("stuffId",reservation.getStuffId());
            jsonObject.put("roomId",reservation.getRoomId());
            jsonObject.put("timeStart",reservation.getTimeStart());
            jsonObject.put("timeEnd",reservation.getTimeEnd());
            jsonObject.put("recordStatus",reservation.getRecordStatus());
            jsonObject.put("remark",reservation.getRemark());
            jsonObject.put("date_",reservation.getDate_());
            jsonObject.put("duration",reservation.getDuration());
            jsonObject.put("memberSourc",reservation.getMemberSourc());
            jsonObject.put("serviceId",serviceIds);
            jsonObject.put("serviceNum",mapService.size());
            result.setMsgcode("0");
            result.setSuccess(true);
            result.setData(jsonObject);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode("200");
        }
        return result;
    }

    /**
     * 添加、修改预约
     */
    @ResponseBody
    @RequestMapping(value = "addReservation",method = RequestMethod.POST)
    @ApiOperation(value="添加、修改预约", notes="添加、修改预约")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "memberId", value = "会员id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "stuffId", value = "预约美容师Id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "roomId", value = "预约的房间Id", required = true, dataType = "Long"),

            @ApiImplicitParam(paramType="query", name = "timeStart", value = "開始時間", required = true, dataType = "Date"),
            @ApiImplicitParam(paramType="query", name = "timeEnd", value = "結束時間", required = true, dataType = "Date"),
            @ApiImplicitParam(paramType="query", name = "recordStatus", value = "记录状态：0.未开始  1.已确认    2.服务中    3.已完成  ", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "serviceIdS", value = "项目id", required = true, dataType = "String")

    })
    public Result addReservation(Reservation reservation,String serviceIdS){
        Result result=new Result();
        try {
        float duration =   reservation.getDuration();
        Date  endTime =    DateString.getDateAddTime(reservation.getTimeStart(),duration);
        reservation.setTimeEnd(endTime);

        if(reservation.getRecordId()!=null){
            //修改
            reservationDao.update(reservation);
            String where="reservation_id=#{reservation_id}";
            Map parameters = new HashMap();
            parameters.put("reservation_id", reservation.getRecordId());
            List<ReservationItem> reservationItemList  =  reservationItemDao.getByWhere(where,parameters);
            if(reservationItemList.size()>0){
                for(ReservationItem reservationItem1:reservationItemList){
                    reservationItemDao.delete(reservationItem1);
                }
            }
            String[] idList = serviceIdS.split(",");
            for (String string:idList){
                ReservationItem reservationItem  = new ReservationItem();
                reservationItem.setReservationId(reservation.getRecordId());
                reservationItem.setServiceId(Long.parseLong(string));
                reservationItemDao.insert(reservationItem);
            }
        }else{
            //新增
            reservationDao.insert(reservation);
            String[] idList = serviceIdS.split(",");
            for (String string:idList){
                ReservationItem reservationItem  = new ReservationItem();
                reservationItem.setReservationId(reservation.getRecordId());
                reservationItem.setServiceId(Long.parseLong(string));
                reservationItemDao.insert(reservationItem);
            }
        }

            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode("200");
        }
        return result;
    }


    /**
     * 取消预约
     */
    @ResponseBody
    @RequestMapping(value = "getIsTrueReservationByDay",method = RequestMethod.GET)
    @ApiOperation(value="取消预约", notes="取消预约")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "reservationId", value = "预约Id", required = true, dataType = "Long"),

    })
    public Result getIsTrueReservationByDay(Long reservationId){
        Reservation reservation = reservationDao.getById(reservationId);
        String where="reservation_id=#{reservation_id}";
        Map parameters = new HashMap();
        parameters.put("reservation_id", reservation.getRecordId());
        List<ReservationItem> reservationItem  =  reservationItemDao.getByWhere(where,parameters);
        if(reservationItem.size()>0){
            for(ReservationItem reservationItem1:reservationItem){
                reservationItemDao.delete(reservationItem1);
            }
        }
        reservationDao.delete(reservation);
        Result result  = new Result();
        result.setMsg("取消成功");
        result.setSuccess(true);
        return result;

    }


    /**
     * 查询当天是否有预约--根据时间段查询
     */
    @ResponseBody
    @RequestMapping(value = "getIsTrueReservationByTime",method = RequestMethod.GET)
    @ApiOperation(value="查询当天是否有预约", notes="查询当天是否有预约")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "stuffId", value = "员工id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "timeStart", value = "開始時間", required = true, dataType = "Date"),
            @ApiImplicitParam(paramType="query", name = "duration", value = "时长", required = true, dataType = "Float")
    })
    public Result getIsTrueReservationByTime(Long stuffId,Date timeStart ,float duration){
        Result result=new Result();
        try {
            Date  endTime =    DateString.getDateAddTime(timeStart,duration);
            Map rMapS = new HashMap();
            String where = "stuff_id=#{stuffId} and  day = #{day}";
            String day = DateString.DateToString(timeStart);
            rMapS.put("stuffId",stuffId);
            rMapS.put("day",day);
            Schedule schedule =  scheduleDao.getOne(where,rMapS);
            if(schedule==null){//休假
                result.setSuccess(false);
                result.setMsgcode("200");
                result.setMsg("休假");
                return result;
            }else{
                Shift shift =  shiftDao.getById(schedule.getShiftId());
                String STime = day+" "+shift.getTimeStart().trim()+":00";
                Date startTime = DateString.StringToDate(STime);
                String ETime = day+" "+shift.getTimeEnd().trim()+":00";
                Date endDTime = DateString.StringToDate(ETime);
              /*  if((timeStart.after(startTime)&&timeStart.before(endDTime))&&(endTime.after(startTime)&&endTime.before(endDTime))){

                }*/
                //在上班范围内可以预约
                if(timeStart.after(startTime)&&endTime.before(endDTime)){
                    //再查询是否在存在的预约范围
                    Map parameter = new HashMap();
                    parameter.put("stuffId", stuffId);
                    parameter.put("timeStart",timeStart);
                    parameter.put("timeEnd", endTime);
                    Map rMap = new HashMap();
                    //String rwhere="stuff_id=#{stuffId} and (time_start between #{timeStart} and #{timeEnd} or time_end between #{timeStart} and #{timeEnd})";
                    String rwhere="stuff_id=#{stuffId} and (#{timeStart} between  time_start and time_end  or #{timeEnd} between time_start and time_end)";
                    rMap.put("where", rwhere);
                    List<Reservation> reservationlist = reservationDao.getList(rMap, parameter);//这个时间短存在预约
                    if(reservationlist.size()>0){
                        result.setSuccess(false);
                        result.setMsgcode("200");
                        result.setMsg("时间段内存在预约");
                        return result;
                    }else{
                        result.setSuccess(true);
                        result.setMsgcode(StatusUtil.OK);
                        result.setMsg("可以预约");
                        return result;
                    }
                }else {
                    result.setSuccess(false);
                    result.setMsgcode("200");
                    result.setMsg("不在上班范围");
                    return result;
                }

            }


        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsg("请求异常");
            result.setMsgcode("200");
        }
        return result;
    }


}
