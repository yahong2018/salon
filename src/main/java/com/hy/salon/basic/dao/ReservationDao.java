package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Reservation;
import com.hy.salon.basic.vo.StuffVo;
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


    protected final static String GET_RESERVATION_BYID = "com.hy.salon.basic.dao.GET_RESERVATION_BYID";
    public List<StuffVo> getStuffName(Long recordId) {
        Map parameters = new HashMap();
        parameters.put("recordId", recordId);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_STUFF_NAME, parameters);
    }
    protected final static String SQL_GET_STUFF_NAME = "com.hy.salon.basic.dao.GET_STUFF_NAME";
}
