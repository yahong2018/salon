package com.hy.salon.basic.dao;

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
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("scheduleDao")
public class ScheduleDao extends BaseDAOWithEntity<Schedule> {
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
        String timeStart=time+"00";
        String timeEnd=time+"31";
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
        ejr.setRootProperty(jsonArray);
        ejr.setTotal(count);
        ejr.setListKey(listKey);
        return  ejr;
    }
}
