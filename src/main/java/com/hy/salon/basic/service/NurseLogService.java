package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.NurseLogDao;
import com.hy.salon.basic.dao.SalonDao;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.vo.NurseLogVo;
import com.hy.salon.basic.vo.ReservationVo;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("nurseLogService")
public class NurseLogService {

    @Resource(name = "salonDao")
    private SalonDao salonDao;

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    @Resource(name = "nurseLogDao")
    private NurseLogDao nurseLogDao;

    public List<NurseLogVo> getNurseLogBySalon(Integer logType,String timeStart, String timeEnd) {
        List<NurseLogVo> voList=new ArrayList<>();
        List<Salon> salonList = salonDao.getSalon();
        for (Salon salon : salonList) {
            NurseLogVo vo=new NurseLogVo();
            vo.setRecordId(salon.getRecordId());
            vo.setSalonName(salon.getSalonName());
            //查询门店下所有的员工
            Map parameters = new HashMap();
            parameters.put("storeId", salon.getRecordId());
            Map listMap = new HashMap();
            String where="store_id=#{storeId}";
            listMap.put("where", where);
            List<Stuff> stufflist = stuffDao.getList(listMap, parameters);
            Integer num=0;
            for (Stuff stuff : stufflist) {
                //查询员工当天所有预约
                Map parameter = new HashMap();
                parameter.put("stuffId", stuff.getRecordId());
                parameter.put("logType",logType);
                parameter.put("timeStart",timeStart);
                parameter.put("timeEnd", timeEnd);
                Map rMap = new HashMap();
                String rwhere="stuff_id=#{stuffId} and log_type=#{logType} and create_date between #{timeStart} and #{timeEnd}";
                rMap.put("where", rwhere);
                List<NurseLog> nurseLoglist = nurseLogDao.getList(rMap, parameter);
                if(nurseLoglist!=null){
                    num+=nurseLoglist.size();
                }
            }
            vo.setLogType(logType);
            vo.setNurseLog(num);
            vo.setTask(num);
            voList.add(vo);
        }
        return voList;
    }

    public List<NurseLogModel> getLogModel(Integer pageNo, Integer pageSize) {
        return nurseLogDao.getLogModel(pageNo,pageSize);
    }
}
