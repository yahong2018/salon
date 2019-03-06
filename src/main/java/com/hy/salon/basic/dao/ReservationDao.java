package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Reservation;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("reservationDao")
public class ReservationDao extends BaseDAOWithEntity<Reservation> {

    public List<Reservation> getReservationById(){
        return this.getSqlHelper().getSqlSession().selectList(GET_RESERVATION_BYID);
    }


    protected final static String GET_RESERVATION_BYID = "com.hy.salon.basic.dao.GET_RESERVATION_BYId";
}
