package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Reservation;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.vo.StuffVo;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequest;
import com.zhxh.core.web.ListRequestBaseHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("reservationDao")
public class ReservationDao extends BaseDAOWithEntity<Reservation> {
    @Resource(name = "stuffDao")
    private StuffDao stuffDao;
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

    public List<Map> getServiceByReservationId(Long recordId) {
        Map parameters = new HashMap();
        parameters.put("reservationId", recordId);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_SERVICE_BYRESERVATIONID, parameters);
    }
    protected final static String SQL_GET_SERVICE_BYRESERVATIONID = "com.hy.salon.basic.dao.GET_SERVICE_BYRESERVATIONID";

    public  List<Map> getReservationVoList(HttpServletRequest request, Long storeId, String timeStart, String timeEnd, ListRequestBaseHandler listRequestBaseHandler) {
        Map parameters = new HashMap();
        parameters.put("store_Id", storeId);
        parameters.put("time_start", timeStart);
        parameters.put("time_end", timeEnd);
        String  stuffId = request.getParameter("stuffId");
        parameters.put("stuff_id", stuffId);
        String  roomId = request.getParameter("roomId");
        parameters.put("room_id", roomId);
        String  serviceId = request.getParameter("serviceId");
        parameters.put("service_id", serviceId);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_STUFF_RESERVATION, parameters);
    }

    protected final static String SQL_GET_STUFF_RESERVATION= "com.hy.salon.basic.dao.GET_STUFF_RESERVATION";
}
