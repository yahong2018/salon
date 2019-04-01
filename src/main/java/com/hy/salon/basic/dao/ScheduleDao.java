package com.hy.salon.basic.dao;

import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.entity.Schedule;
import com.hy.salon.basic.vo.ShiftVo;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.util.DateString;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequest;
import com.zhxh.core.web.ListRequestBaseHandler;
import com.zhxh.core.web.PageListRequest;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("scheduleDao")
public class ScheduleDao extends BaseDAOWithEntity<Schedule> {

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;
    public List<ShiftVo> getScheduleByTime(Long recordId, String timeStart, String timeEnd) {
        Map parameters = new HashMap();
        parameters.put("storeId", recordId);
        parameters.put("timeStart", timeStart);
        parameters.put("timeEnd", timeEnd);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_SCHEDULE_BY_TIME, parameters);
    }
    protected final static String SQL_GET_SCHEDULE_BY_TIME = "com.hy.salon.basic.dao.GET_SCHEDULE_BY_TIME";

    public Schedule getPaiByStuffId(Long stuffId, String time) {
        String where = "stuff_id=#{stuffId} and day=#{time}";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("stuffId", stuffId);
        parameters.put("time", time);
        return this.getOne(where,parameters);
    }
    //查询当月一个月的排班
    public List<Schedule> getPaiByStuffIdTime(Long stuffId, String time) {
        Calendar now = Calendar.getInstance();

        System.out.println("年: " + now.get(Calendar.YEAR));
        int nowY = now.get(Calendar.YEAR);
        int nowM = now.get(Calendar.MONTH);
        int nowD = now.get(Calendar.DAY_OF_MONTH);
        System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");

        System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));
        String [] temp = time.split("-");
        String timeEnd="";
        //String newStr = temp[1].replaceAll("^(0+)", "");
        String newStr ="";
        if((nowM+"").length()==1){
            newStr = "0"+(nowM+1);
        }
        if(temp[1].equals(newStr)){
             timeEnd=time+"-"+nowD;//当月就是拿到今天的时间
        }else{
            timeEnd = time+"-31";//其他月份的时候
        }

        String timeStart=time+"-01";

        String where = "stuff_id=#{stuffId} and day between  #{timeStart} and  #{timeEnd}";
        Map map=new HashMap();
        map.put("where",where);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("stuffId", stuffId);
        parameters.put("timeStart", timeStart);
        parameters.put("timeEnd", timeEnd);
        return this.getList(map,parameters);
    }
    public ListRequest getListRequest(HttpServletRequest request) {
        ListRequest listRequest;
        if(StringUtils.isNotEmpty(request.getParameter("page"))){
            listRequest = new PageListRequest();
        }else{
            listRequest = new ListRequest();
        }
        listRequest.fromServletRequest(request);
        return listRequest;
    }
    protected final static String SQL_GET_SCHEDULE_BY_TIME_AND_STUFFID = "com.hy.salon.basic.dao.GET_SCHEDULE_BY_TIME_AND_STUFFID";
    public ExtJsResult getScheduleForStoreIdSystem(Date[] dataList,Date timeStartDate, Date timeEndDate, HttpServletRequest request, Long recordId, ListRequestBaseHandler listRequestBaseHandler) {
        ListRequest listRequest = getListRequest(request);
        listRequest.setWhere(listRequest.getWhere()==""?listRequest.getWhere()+" store_id="+recordId:listRequest.getWhere()+" and "+" store_id="+recordId);
        List<Stuff> listStuff =  listRequestBaseHandler.getByRequest(listRequest);
        int count = listRequestBaseHandler.getRequestListCount(listRequest);
        ExtJsResult ejr = new ExtJsResult();
        JSONArray jsonArray  = new JSONArray();
        List<String> listKey = new ArrayList<>();
        listKey.add("name");
        listKey.add("stuffId");
        int i = 0;
        for(Stuff stuff :listStuff){
            Map parameters = new HashMap();
            parameters.put("stuffId", stuff.getRecordId());
            parameters.put("timeStart", timeStartDate);
            parameters.put("timeEnd", timeEndDate);
            List<Map> mapList = this.getSqlHelper().getSqlSession().selectList(SQL_GET_SCHEDULE_BY_TIME_AND_STUFFID, parameters);
            JSONObject json = new JSONObject();
            json.put("name",stuff.getStuffName());
            json.put("stuffId",stuff.getRecordId());
            for(Date date:dataList){
                String dateString = DateString.DateToString(date);
                if(i==0){
                    listKey.add(dateString);
                }
                json.put(dateString,"休");

                for(Map map:mapList){
                    Date temp =  (Date)map.get("day");
                    String tempDateString = DateString.DateToString(temp);
                    if(dateString.equals(tempDateString)){
                        Integer type = (Integer)map.get("shiftType");
                        String typeString = "";
                        if(type==0){
                            typeString = "全班";
                        }else if(type==1){
                            typeString = "早班";
                        }else if(type==2){
                            typeString = "中班";
                        }else{
                            typeString = "晚班";
                        }
                        json.put(dateString,typeString);
                    }
                }

            }
            i++;
            jsonArray.add(json);
        }
        ejr.setData(jsonArray);
        ejr.setTotal(count);
        ejr.setListKey(listKey);
        return  ejr;
    }

    public ExtJsResult getAdminStuffScheduleByTime(Date[] dataList, Date timeStartDate, Date timeEndDate, Long recordId, String stuffId) {
        ExtJsResult ejr = new ExtJsResult();
       Stuff stuff = stuffDao.getById(stuffId);
        Map parameters = new HashMap();
        parameters.put("stuffId", stuffId);
        parameters.put("timeStart", timeStartDate);
        parameters.put("timeEnd", timeEndDate);
        List<Map> mapList = this.getSqlHelper().getSqlSession().selectList(SQL_GET_SCHEDULE_BY_TIME_AND_STUFFID, parameters);
        JSONObject json = new JSONObject();
        json.put("name",stuff.getStuffName());
        json.put("stuffId",stuffId);
        List<String> listKey = new ArrayList<>();
        listKey.add("name");
        listKey.add("stuffId");
        for(Date date:dataList){
            String dateString = DateString.DateToString(date);
            listKey.add(dateString);
            json.put(dateString,"休");

            for(Map map:mapList){
                Date temp =  (Date)map.get("day");
                String tempDateString = DateString.DateToString(temp);
                if(dateString.equals(tempDateString)){
                    Integer type = (Integer)map.get("shiftType");
                    String typeString = "";
                    if(type==0){
                        typeString = "全班";
                    }else if(type==1){
                        typeString = "早班";
                    }else if(type==2){
                        typeString = "中班";
                    }else{
                        typeString = "晚班";
                    }
                    json.put(dateString,typeString);
                }
            }

        }

        ejr.setData(json);
        ejr.setSuccess(true);
        ejr.setMsgcode(StatusUtil.OK);
        ejr.setMsg("查询成功");
        ejr.setListKey(listKey);
        return  ejr;
    }
}
