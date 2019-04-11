package com.hy.salon.basic.dao;

import com.github.pagehelper.PageHelper;
import com.hy.salon.basic.entity.Reservation;
import com.hy.salon.basic.vo.Result;
import com.hy.salon.basic.vo.StuffVo;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.apache.commons.lang3.StringUtils;
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

    public Result getReservationVoList(HttpServletRequest request, Long storeId, String oneDay , String toDays) {
        Map parameters = new HashMap();
        String timeStart = "";
        String timeEnd = "";
        if(StringUtils.isNotEmpty(oneDay)){
            timeStart = oneDay.trim()+" 00:00:00";
            timeEnd = oneDay.trim()+" 23:59:59";
            parameters.put("timeStart", timeStart);
            parameters.put("timeEnd", timeEnd);
        }else{
            if(StringUtils.isNotEmpty(toDays)){
                String days[] =  toDays.split("~");
                timeStart = days[0];
                timeEnd = days[1];
                parameters.put("timeStart", timeStart);
                parameters.put("timeEnd", timeEnd);
            }

        }

        parameters.put("storeId", storeId);

        String  stuffId = request.getParameter("stuffId");
        if(StringUtils.isNotEmpty(stuffId)){
            parameters.put("stuffId", stuffId);
        }
        String  roomId = request.getParameter("roomId");
        if(StringUtils.isNotEmpty(roomId)){
            parameters.put("roomId", roomId);
        }
        String  serviceId = request.getParameter("serviceId");
        if(StringUtils.isNotEmpty(serviceId)){
            parameters.put("serviceId", serviceId);
        }
        int countT = this.getSqlHelper().getSqlSession().selectOne(SQL_GET_STUFF_RESERVATIONCOUNT, parameters);
        PageHelper.startPage(Integer.parseInt(request.getParameter("page")),10);
        List<Map> listMap = this.getSqlHelper().getSqlSession().selectList(SQL_GET_STUFF_RESERVATION, parameters);
        com.hy.salon.basic.vo.Result result=new com.hy.salon.basic.vo.Result();
        result.setData(listMap);
        result.setTotal(countT);
        return result;
    }

    protected final static String SQL_GET_STUFF_RESERVATION= "com.hy.salon.basic.dao.GET_STUFF_RESERVATION";
    protected final static String SQL_GET_STUFF_RESERVATIONCOUNT= "com.hy.salon.basic.dao.GET_STUFF_RESERVATIONCOUNT";
}
