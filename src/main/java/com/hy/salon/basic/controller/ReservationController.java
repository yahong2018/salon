package com.hy.salon.basic.controller;

import com.hy.salon.basic.entity.Reservation;
import com.hy.salon.basic.service.ReservationService;
import com.hy.salon.basic.vo.Result;
import com.hy.salon.basic.vo.StoreReservation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hy/basic/reservation")
public class ReservationController {
    @Resource(name = "reservationService")
    private ReservationService reservationService;

    @ResponseBody
    @RequestMapping(value = "getReservationById",method = RequestMethod.POST)
    public Map getReservationById(){
        Map map=new HashMap();
        List<Reservation> reservation = reservationService.getReservationById();
        map.put("msg","查询成功");
        map.put("success",true);
        map.put("msgcode","0");
        map.put("data",reservation);
        return map;
    }
    /**
     * 按日期获取每个门店的预约汇总
     */
    @ResponseBody
    @RequestMapping(value = "getReservationByTimeStart",method = RequestMethod.GET)
    public List<StoreReservation> getReservationByTimeStart(String reservationDate){
        reservationDate = "20190306";
        List<StoreReservation> list =  reservationService.getStoreReservationByTimeStart(reservationDate);
        return list;
    }


}
