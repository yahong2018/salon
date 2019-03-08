package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Reservation;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("reservationDao")
public class ReservationDao extends BaseDAOWithEntity<Reservation> {

    public Map getStuffName(Long recordId) {
        Map parameters = new HashMap();
        parameters.put("recordId", recordId);
        return (Map) this.getSqlHelper().getSqlSession().selectList(SQL_GET_STUFF_NAME, parameters);
    }
    protected final static String SQL_GET_STUFF_NAME = "com.hy.salon.basic.dao.GET_STUFF_NAME";
}
