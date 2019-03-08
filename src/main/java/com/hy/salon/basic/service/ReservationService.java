package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.ReservationDao;
import com.hy.salon.basic.dao.SalonDao;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.Reservation;
import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.vo.ReservationVo;
import com.hy.salon.basic.vo.StuffVo;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("reservationService")
public class ReservationService {
    @Resource(name = "reservationDao")
    private ReservationDao reservationDao;
    @Resource(name = "salonDao")
    private SalonDao salonDao;
    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    public List<ReservationVo> getReservationByTime(String timeStart, String timeEnd) {
        List<ReservationVo> listVo=new ArrayList<>();
        List<Salon> salonList = salonDao.getSalon();
        for (Salon salon : salonList) {
            ReservationVo vo=new ReservationVo();
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
                parameter.put("timeStart",timeStart);
                parameter.put("timeEnd", timeEnd);
                Map rMap = new HashMap();
                String rwhere="stuff_id=#{stuffId} and time_start between #{timeStart} and #{timeEnd}";
                rMap.put("where", rwhere);
                List<Reservation> reservationlist = reservationDao.getList(rMap, parameter);
                if(reservationlist!=null){
                    num+=reservationlist.size();
                }
            }
            vo.setReservation(num);
            listVo.add(vo);
        }
        return listVo;
    }

    public List<StuffVo> getStuff(Long recordId, String timeStart, String timeEnd) {
        List<StuffVo> voList=new ArrayList<>();
        //查询门店下所有的员工
        Map parameters = new HashMap();
        parameters.put("storeId", recordId);
        Map listMap = new HashMap();
        String where="store_id=#{storeId}";
        listMap.put("where", where);
        List<Stuff> stufflist = stuffDao.getList(listMap, parameters);
        for (Stuff stuff : stufflist) {
            StuffVo vo=new StuffVo();
            vo.setRecordId(stuff.getRecordId());
            vo.setStuffName(stuff.getStuffName());
            //查询员工当天所有预约
            Map parameter = new HashMap();
            parameter.put("stuffId", stuff.getRecordId());
            parameter.put("timeStart",timeStart);
            parameter.put("timeEnd", timeEnd);
            Map rMap = new HashMap();
            String rwhere="stuff_id=#{stuffId} and time_start between #{timeStart} and #{timeEnd}";
            rMap.put("where", rwhere);
            List<Reservation> reservationlist = reservationDao.getList(rMap, parameter);
            if(reservationlist!=null){
                vo.setReservation(reservationlist.size());
            }
            Map map = reservationDao.getStuffName(stuff.getRecordId());
            String role = (String) map.get("job_name");
            vo.setRole(role);
            voList.add(vo);
        }
        return voList;
    }
}
