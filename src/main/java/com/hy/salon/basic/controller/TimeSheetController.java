package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.service.StuffService;
import com.hy.salon.basic.service.TimeSheetService;
import com.hy.salon.basic.util.DateString;
import com.hy.salon.basic.vo.MyResult;
import com.hy.salon.basic.vo.Result;
import com.hy.salon.basic.vo.TimeSheetVo;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.admin.service.SystemUserService;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hy/basic/timeSheet")
@Api(value = "TimeSheetController| 考勤控制器")
public class TimeSheetController extends SimpleCRUDController<TimeSheet> {
    @Resource(name = "timeSheetDao")
    private TimeSheetDao timeSheetDao;
    @Resource(name = "picturesDao")
    private PicturesDAO picturesDao;
    @Resource(name = "scheduleDao")
    private ScheduleDao scheduleDao;
    @Resource(name = "timeSheetService")
    private TimeSheetService timeSheetService;
    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;
    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    @Resource(name = "salonDao")
    private SalonDao salonDao;
    @Resource(name = "shiftDao")
    private ShiftDao shiftDao;

    @Resource(name = "attendanceSheetDao")
    private AttendanceSheetDAO attendanceSheetDao;

    @Override
    protected BaseDAOWithEntity<TimeSheet> getCrudDao() {
        return timeSheetDao;
    }




    /**
     * 获取个人当月的异常考勤
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getRYTimeSheetTypeTimeStuffId",method = RequestMethod.GET)
    @ApiOperation(value="获取个人当月的异常考勤", notes="获取个人当月的异常考勤")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "stuffId", value = "员工id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "time", value = "当月日期", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "type", value = "异常类型", required = true, dataType = "String"),
    })
    public Result getTimeSheetTypeTimeStuffId(Long stuffId,String time,String type){
        MyResult result = new MyResult();
        JSONArray list = timeSheetService.getYCTimeSheetByStuffIdNew(result,stuffId, time,type);
        result.setData(list);
        result.setSuccess(true);
        result.setMsgcode(StatusUtil.OK);
        result.setTotal(list.size());

        return result;
    }

    /**
     * 获取当天每家门店考勤
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getTimeSheets",method = RequestMethod.GET)
    @ApiOperation(value="获取当天每家门店考勤", notes="获取当天每家门店考勤")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "美容院id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "time", value = "当月日期,年月日", required = true, dataType = "String")
    })
    public Result getTimeSheets(Long recordId,String time,Integer page,Integer limit){
        if(StringUtils.isEmpty(recordId)){
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
            recordId=stuff.getStoreId();
        }
        Result result=new Result();
        try {
            List<Map> list = timeSheetService.getTimeSheets(recordId, time,page,limit);
            result.setTotal(salonDao.getPageListCount(new HashMap())-1);
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
     * 获取个人一天的考勤
     */
    @ResponseBody
    @RequestMapping(value = "getOneTimeSheetByStuffId",method = RequestMethod.GET)
    @ApiOperation(value="获取月門店考勤", notes="获取当月門店考勤")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "stuffId", value = "员工id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "time", value = "日期", required = true, dataType = "String")
    })
    public Result getOneTimeSheetByStuffId(HttpServletRequest request){
        MyResult result = new MyResult();
        Pictures pictures = null;
        JSONObject jsonK = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            String storeId = request.getParameter("storeId");
            String stuffId = request.getParameter("stuffId");
            String time = request.getParameter("time");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            time = sdf.format(sdf.parse(time));
            String filterExpr = request.getParameter("filterExpr");
            long stuffIdL = Long.parseLong(stuffId);
            Stuff stuff = null;
            long storeIdL = Long.parseLong(storeId);
            if (StringUtils.isEmpty(stuffIdL) || StringUtils.isEmpty(storeId)) {
                SystemUser user = authenticateService.getCurrentLogin();
                stuff = stuffDao.getStuffForUser(user.getRecordId());
                stuffIdL = stuff.getRecordId();
                storeIdL = stuff.getStoreId();
            }

            try {
                String cTime = time + " 00:00:00";
                String eTime = time + " 23:59:59";

                //TimeSheet timeSheet=timeSheetDao.getTSheet(stuffIdL,cTime,eTime);
                 pictures = picturesDao.getOnePicturesForCondition(stuffIdL, (byte) 1, (byte) 0);
                Schedule schedule = scheduleDao.getPaiByStuffId(stuffIdL, time);
                if (schedule == null) {
                    result.setMsg("当天没有排班信息");
                    result.setMsgcode("0");
                    JSONObject json = new JSONObject();
                    json.put("picturesUrl", pictures.getPicUrl());
                    json.put("type", 3);
                    json.put("sxtype", 3);//没有排班信息
                    result.setData(json);
                    return result;
                }


                List<AttendanceSheet> listA = attendanceSheetDao.getTAttendanceSheet(stuffIdL, cTime, eTime);
                String shiftWhere = "store_id=#{storeId} and record_id=#{recordId}";
                Map<String, Object> shiftParameter = new HashMap<>();
                shiftParameter.put("storeId", storeIdL);
                shiftParameter.put("recordId", schedule.getShiftId());
                Shift shift = shiftDao.getOne(shiftWhere, shiftParameter);
                if (shift == null) {
                    result.setMsg("门店未设置排班");
                    result.setMsgcode("0");
                    JSONObject json = new JSONObject();
                    json.put("picturesUrl", pictures.getPicUrl());
                    json.put("type", 4);
                    result.setData(json);
                    return result;
                }


                Date zhongTime = DateString.getZhongJianTime(shift.getTimeEnd(), shift.getTimeStart());



                SimpleDateFormat dateFormat = new SimpleDateFormat(
                        "HH:mm");
                // String attendanceTime = dateFormat.format(condition.getAttendanceTime());
                // System.out.println(attendanceTime);
                SimpleDateFormat df = new SimpleDateFormat("HH:mm");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
                Date data = new Date();
                int sxtype = 1;
                String newDate = dateFormat.format(data);
                Date newD = df.parse(newDate);
                boolean before = newD.before(zhongTime);
           /* if(!before){
                type = 1;//下班卡
            }*/

                String startTime = shift.getTimeStart();
                String endTime = shift.getTimeEnd();
                Date startTimeD = df.parse(startTime);
                Date endTimeD = df.parse(endTime);


                JSONObject jsonS = new JSONObject();
                jsonS.put("time", "无");
                jsonS.put("address", "无");
                jsonS.put("type", 1);
                jsonS.put("abnormalType", 0);
                jsonS.put("SBStartTime", startTime);

                JSONObject jsonX = new JSONObject();
                jsonX.put("time", "无");
                jsonX.put("address", "无");
                jsonX.put("type", 2);
                jsonX.put("abnormalType", 0);//正常打卡还是异常  1异常  2 补卡
                jsonX.put("XBStartTime", endTime);
                if (listA.size() > 0) {//有打卡
                    if (listA.size() == 1) {
                        //早上的
                        String attendanceTime = dateFormat.format(listA.get(0).getAttendanceTime());
                        Date dt2 = df.parse(attendanceTime);//打卡时间
                        if (dt2.getTime() > startTimeD.getTime()) {//迟到
                            jsonS.put("abnormalType", 1);//
                        }
                        jsonS.put("SBStartTime", startTime);
                        jsonS.put("time", listA.get(0).getAttendanceTime());
                        jsonS.put("address", listA.get(0).getAddress());
                        jsonS.put("type", 1);

                        Date enTime = DateString.StringToDateAddNum(time.trim()+" "+endTime.trim()+":00",3);
                        Date nTime = new Date();
                        if(enTime.getTime()<nTime.getTime()){//当前时间大于需要下班的时间节点
                            jsonX.put("abnormalType",2);
                        }
                    } else {
                        if (listA.get(0).getAttendanceTime().getTime() < listA.get(1).getAttendanceTime().getTime()) {
                            //早上的
                            jsonS.put("time", listA.get(0).getAttendanceTime());
                            jsonS.put("address", listA.get(0).getAddress());
                            jsonS.put("type", 1);

                            String attendanceTime = dateFormat.format(listA.get(0).getAttendanceTime());
                            Date dt2 = df.parse(attendanceTime);//上班打卡时间
                            if (dt2.getTime() > startTimeD.getTime()) {//迟到
                                jsonS.put("abnormalType", 1);
                            }
                            //晚上的
                            jsonX.put("time", listA.get(1).getAttendanceTime());
                            jsonX.put("address", listA.get(1).getAddress());
                            jsonX.put("type", 2);


                            String attendanceTime1 = dateFormat.format(listA.get(1).getAttendanceTime());
                            Date dt3 = df.parse(attendanceTime1);//下班打卡时间
                            if (dt3.getTime() < endTimeD.getTime()) {//早退
                                jsonX.put("abnormalType", 1);
                            }
                        }
                        sxtype = 2;
                    }

                }else{//无打卡
                    Date sTime = DateString.StringToDate(time.trim()+" "+startTime.trim()+":00");//跟需要查询的时间拼接
                    Date nTime = new Date();
                    if(sTime.getTime()<nTime.getTime()){//当前时间大于需要上班的时间节点
                        jsonS.put("abnormalType",2);
                    }


                }

/*            if(listA.size()>0){
                for(AttendanceSheet as:listA){
                    String attendanceTime = dateFormat.format(as.getAttendanceTime());
                    Date dt2 = df.parse(attendanceTime);
                   long time1 =  zhongTime.getTime();
                    long time2 =  dt2.getTime();
                    if(zhongTime.getTime()>=dt2.getTime()){
                        //早上的
                        jsonS.put("time",as.getAttendanceTime());
                        jsonS.put("address",as.getAddress());
                        jsonS.put("type",1);
                        //jsonArray.add(json);
                    }else{
                        //下班的

                        jsonX.put("time",as.getAttendanceTime());
                        jsonX.put("address",as.getAddress());
                        jsonX.put("type",2);
                        //jsonArray.add(json);
                    }
                }
            }else{
                //没有签到
            }*/
                jsonArray.add(jsonS);
                jsonArray.add(jsonX);
                jsonK.put("attendanceSheetList", jsonArray);
                jsonK.put("picturesUrl", pictures==null?"":pictures.getPicUrl());
                jsonK.put("sxtype", sxtype);//

                result.setData(jsonK);

                result.setSuccess(true);
                result.setMsgcode(StatusUtil.OK);
            } catch (Exception e) {
                jsonK.put("attendanceSheetList", jsonArray);
                jsonK.put("picturesUrl", pictures==null?"":pictures.getPicUrl());
                jsonK.put("sxtype", 3);//
                result.setData(jsonK);
                result.setSuccess(true);
                result.setMsgcode(StatusUtil.OK);
                e.printStackTrace();
                result.setSuccess(false);
                result.setMsgcode(StatusUtil.ERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 获取个人当月的考勤
     */
    @ResponseBody
    @RequestMapping(value = "getTimeSheetByStuffId",method = RequestMethod.GET)
    @ApiOperation(value="获取个人当月的考勤", notes="获取个人当月的考勤")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "stuffId", value = "员工id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "time", value = "当月日期，年月", required = true, dataType = "String")
    })
    public Result getTimeSheetByStuffId(HttpServletRequest request){
        String stuffId = request.getParameter("stuffId");
        String time = request.getParameter("time");
        String filterExpr   = request.getParameter("filterExpr");
        long stuffIdL = Long.parseLong(stuffId);
        if(StringUtils.isEmpty(stuffIdL)){
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
            stuffIdL=stuff.getRecordId();
        }
        MyResult result = new MyResult();
        try {
            Map list = timeSheetService.getTimeSheetByStuffIdNew(result,stuffIdL, time);
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
     * 获取当月的一个门店的考勤
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getTimeSheetBySalonId",method = RequestMethod.GET)
    @ApiOperation(value="获取月門店考勤", notes="获取当月門店考勤")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "salonId", value = "門店id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "time", value = "当月日期", required = true, dataType = "String")
    })
    public Result getTimeSheetBySalonId(HttpServletRequest request){

        String salonIdS = request.getParameter("salonId");
        String time = request.getParameter("time");
        String filterExpr   = request.getParameter("filterExpr");
        long salonId = Long.parseLong(salonIdS);
        if(StringUtils.isEmpty(salonId)){
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
            salonId=stuff.getStoreId();
        }
        MyResult result = new MyResult();
        try {
            List<Map> list = timeSheetService.getTimeSheetBySalonId(result,salonId, time);
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
     * 考勤3
     * @param time
     * @return
     */
/*    @ResponseBody
    @RequestMapping(value = "getTimeSheetByStuffId",method = RequestMethod.GET)
    @ApiOperation(value="获取员工考勤情况", notes="获取员工考勤情况")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "stuffId", value = "员工id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "time", value = "当月日期", required = true, dataType = "String")
    })
    public Result getTimeSheetByStuffId(Long stuffId,String time){
        MyResult result=new MyResult();
        try {
            Map map = timeSheetService.getTimeSheetByStuffId(result,stuffId, time);
            result.setData(map);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
        }
        return result;
    }*/
    /**
     * 查询员工未打卡记录
     */
    @ResponseBody
    @RequestMapping(value = "getTimeId",method = RequestMethod.GET)
    @ApiOperation(value="查询员工未打卡记录", notes="查询员工未打卡记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "stuffId", value = "员工id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "time", value = "当月日期", required = true, dataType = "String")
    })
    public Result getTimeId(Long stuffId,String time){
        Result result=new Result();
        try {
            List<Schedule> list = timeSheetService.getTimeId(stuffId, time);
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
     * 查询员工未打卡记录详情
     */
    @ResponseBody
    @RequestMapping(value = "getTimeSheetDetails",method = RequestMethod.GET)
    @ApiOperation(value="查询员工未打卡记录详情", notes="查询员工未打卡记录详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "stuffId", value = "员工id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "time", value = "当天日期", required = true, dataType = "String")
    })
    public Result getTimeSheetDetails(Long stuffId,String time){
        Result result=new Result();
        try {
            Map map = timeSheetService.getTimeSheetDetails(stuffId, time);
            result.setData(map);
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
     * 获取分店
     */
    @ResponseBody
    @RequestMapping(value = "getSalon",method = RequestMethod.GET)
    public Result getSalon(){
        SystemUser user = authenticateService.getCurrentLogin();
        Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
        Result result=new Result();
        try {
            List<Salon> list = timeSheetService.getSalon(stuff.getStoreId());
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
     * 考勤2pc
     * @param time
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getTimeSheetBySalonIds",method = RequestMethod.GET)
    public Result getTimeSheetBySalonIds(Long salonId,String time,String name){
        if(StringUtils.isEmpty(salonId)){
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
            salonId=stuff.getStoreId();
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
        Result result=new Result();
        try {
            List<Map> list=null;
            String s="";
            if(time!=null&&time!=""){
                String[] str = time.split("-");
                StringBuffer sb = new StringBuffer();
                for(int i = 0; i < str.length;i++){
                    sb.append(str[i]);
                }
                s= sb.toString();
            }else{
                s=sdf.format(new Date());
            }
            if(name!=null){
                list = timeSheetService.getTimeSheetBySalonId(0,0,0,salonId, s,name);
            }else{
                list = timeSheetService.getTimeSheetBySalonId(salonId, s);
            }
            result.setTotal(stuffDao.getPageListCount(new HashMap()));
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
     * 初始化
     * @param time
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getTimeSheetInit",method = RequestMethod.GET)
    public Result getTimeSheetInit(Long salonId,String time){
        if(StringUtils.isEmpty(salonId)){
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
            salonId=stuff.getStoreId();
        }
        Result result=new Result();
        try {
            List<Map> list = timeSheetService.getTimeSheetInit(0,0,0,salonId,time);
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
}
