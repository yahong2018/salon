package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.HolidayDao;
import com.hy.salon.basic.entity.Holiday;
import org.springframework.stereotype.Component;
import java.text.*;
import java.util.Calendar;
import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("holidayService")
public class HolidayService {

    @Resource(name="holidayDao")
    private HolidayDao holidayDao;
    public List<Holiday> getBySalonIdDayTime(long salonId, String reservationDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date data = null;
        try {
            System.out.println(sdf.parse(reservationDate));
            data = sdf.parse(reservationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String where="store_id=#{salonId} and DATE_FORMAT(day,'%Y%m%d')= #{day}";
        Map parameters = new HashMap();
        parameters.put("salonId", salonId);
        parameters.put("day", reservationDate);
        return holidayDao.getByWhere(where,parameters);
    }


}
