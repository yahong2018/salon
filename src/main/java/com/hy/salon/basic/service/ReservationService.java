package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.MemberDao;
import com.hy.salon.basic.dao.ReservationDao;
import com.hy.salon.basic.dao.SalonDao;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.Member;
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

    @Resource(name = "memberDao")
    private MemberDao memberDao;

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
            List<StuffVo> list = reservationDao.getStuffName(stuff.getRecordId());
            if(list!=null){
                for (StuffVo stuffVo : list) {
                    vo.setRole(stuffVo.getRole());
                }
            }
            voList.add(vo);
        }
        return voList;
    }

    public List<ReservationVo> getReservationList(Long stuffId, String timeStart, String timeEnd) {
        List<ReservationVo> voList=new ArrayList<>();
        Map parameter = new HashMap();
        parameter.put("stuffId", stuffId);
        parameter.put("timeStart",timeStart);
        parameter.put("timeEnd", timeEnd);
        Map rMap = new HashMap();
        String rwhere="stuff_id=#{stuffId} and time_start between #{timeStart} and #{timeEnd}";
        rMap.put("where", rwhere);
        List<Reservation> reservationlist = reservationDao.getList(rMap, parameter);
        if(reservationlist!=null){
            for (Reservation reservation : reservationlist) {
                ReservationVo vo=new ReservationVo();
                vo.setMemberId(reservation.getMemberId());
                vo.setStuffId(reservation.getStuffId());
                vo.setRoomId(reservation.getRoomId());
                vo.setTimeStart(reservation.getTimeStart());
                vo.setTimeEnd(reservation.getTimeEnd());
                vo.setRecordStatus(reservation.getRecordStatus());
                String where = "record_id=#{recordId}";
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("recordId", reservation.getMemberId());
                Member member = memberDao.getOne(where, parameters);
                vo.setMemberName(member.getMemberName());
                voList.add(vo);
            }
        }
        return voList;
    }
}
