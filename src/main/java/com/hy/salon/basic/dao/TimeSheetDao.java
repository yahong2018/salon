package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.entity.TimeSheet;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("timeSheetDao")
public class TimeSheetDao extends BaseDAOWithEntity<TimeSheet> {

    public TimeSheet getTSheet(Long recordId,String cTime, String eTime) {
        String where = "stuff_id=#{stuffId} and create_date between #{cTime} and #{eTime}";
        //String where = "stuff_id=#{recordId}";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("stuffId", recordId);
        parameters.put("cTime", cTime);
        parameters.put("eTime", eTime);
        return this.getOne(where,parameters);
    }
    //查询当月员工出勤记录
    public List<TimeSheet> getTSheets(Long recordId, String time) {
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
            timeEnd=time+"-"+nowD;
        }else{
            timeEnd = time+"-31";
        }

        String timeStart=time+"-01";
        String where = "stuff_id=#{stuffId} and day between  #{timeStart} and  #{timeEnd}";
        Map map=new HashMap();
        map.put("where",where);
        //String where = "stuff_id=#{recordId}";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("stuffId", recordId);
        parameters.put("timeStart", timeStart);
        parameters.put("timeEnd", timeEnd);
        return this.getList(map,parameters);
    }
}
