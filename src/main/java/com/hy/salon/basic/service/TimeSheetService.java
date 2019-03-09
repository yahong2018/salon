package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.SalonDao;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.dao.TimeSheetDao;
import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.entity.TimeSheet;
import com.hy.salon.basic.vo.TimeSheetVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("timeSheetService")
public class TimeSheetService {
    @Resource(name = "salonDao")
    private SalonDao salonDao;
    @Resource(name = "stuffDao")
    private StuffDao stuffDao;
    @Resource(name = "timeSheetDao")
    private TimeSheetDao timeSheetDao;
    public List<TimeSheetVo> getTimeSheet(String cTime, String eTime) {
        List<TimeSheetVo> voList = new ArrayList();
        List<Salon> list = salonDao.getSalon();
        for (Salon salon : list) {
            TimeSheetVo vo=new TimeSheetVo();
            vo.setSalonName(salon.getSalonName());
            List<TimeSheet> timeSheetList = new ArrayList();
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("storeId", salon.getRecordId());
            String where = "store_id=#{storeId}";
            Map listMap = new HashMap();
            listMap.put("where", where);
            //门店下所有员工
            List<Stuff> list1 = stuffDao.getList(listMap, parameters);
            vo.setAttendance((long) list1.size());
            vo.setAttendanceNum((long) timeSheetList.size());
            vo.setAttendanceE((long) (list1.size()-timeSheetList.size()));
            for (Stuff stuff : list1) {
                //查询所有员工的出勤
                TimeSheet timeSheet=timeSheetDao.getTSheet(stuff.getRecordId(),cTime,eTime);
                if(timeSheet!=null){
                    timeSheetList.add(timeSheet);
                }
            }
            voList.add(vo);
        }
        return voList;
    }
}
