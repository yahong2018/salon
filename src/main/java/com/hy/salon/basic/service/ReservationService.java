package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.ReservationDao;
import com.hy.salon.basic.entity.Reservation;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component("reservationService")
public class ReservationService {
    @Resource(name = "reservationDao")
    private ReservationDao reservationDao;

    public List<Reservation> getReservationById(){
        return reservationDao.getReservationById();
    }

}
