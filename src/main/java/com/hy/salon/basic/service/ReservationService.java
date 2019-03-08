package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.ReservationDao;
import com.hy.salon.basic.entity.Job;
import com.hy.salon.basic.entity.Reservation;
import com.hy.salon.basic.vo.StoreReservation;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("reservationService")
public class ReservationService {
    @Resource(name = "reservationDao")
    private ReservationDao reservationDao;

    public List<Reservation> getReservationById(){
        return reservationDao.getReservationById();
    }

    public List<StoreReservation> getStoreReservationByTimeStart(String reservationDate) {
        String where="time_start=#{timeStart}";
        Map parameters = new HashMap();
        parameters.put("timeStart", reservationDate);
        return  reservationDao.getSqlHelper().getSqlSession().selectList(GET_RESERVATION_BY_TIME,parameters);
    }
    protected final static String GET_RESERVATION_BY_TIME = "com.hy.salon.basic.dao.GET_RESERVATION_BY_TIME";
}
