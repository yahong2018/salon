package com.hy.salon.basic.controller;


import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.dao.ReservationDao;
import com.hy.salon.basic.entity.Reservation;
import com.hy.salon.basic.vo.Result;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/hy/basic/consumption")
@Api(value = "ConsumptionController| 消费")
public class ConsumptionController {
    @Resource(name = "reservationDao")
    private ReservationDao reservationDao;

    @ResponseBody
    @RequestMapping(value = "updateReservationById",method = RequestMethod.GET)
    public Result getReservationById(Long reservationId,int recordStatus){
       Reservation reservation =  reservationDao.getById(reservationId);
        reservation.setRecordStatus(recordStatus);
        reservationDao.update(reservation);
        Result result  = new Result();
        result.setSuccess(true);
        result.setMsg("更新成功");
        result.setMsgcode("0");
       return  result;
    }

}
