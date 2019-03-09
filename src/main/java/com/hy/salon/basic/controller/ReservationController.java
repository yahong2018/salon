package com.hy.salon.basic.controller;

import com.hy.salon.basic.entity.Reservation;
import com.hy.salon.basic.service.ReservationService;
import com.hy.salon.basic.vo.ReservationVo;
import com.hy.salon.basic.vo.Result;
import com.hy.salon.basic.vo.StuffVo;
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

    /**
     * 按日期获取每个门店的预约汇总
     */
    @ResponseBody
    @RequestMapping(value = "getReservationByTime",method = RequestMethod.GET)
    public Result getReservationByTime(String timeStart ,String timeEnd){
        Result result=new Result();
        try {
            List<ReservationVo> list = reservationService.getReservationByTime(timeStart, timeEnd);
            result.setMsgcode("0");
            result.setSuccess(true);
            result.setData(list);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode("200");
        }
        return result;
    }
    /**
     * 查询当天有预约员工列表
     */
    @ResponseBody
    @RequestMapping(value = "getStuff",method = RequestMethod.GET)
    public Result getStuff(Long recordId,String timeStart ,String timeEnd){
        Result result=new Result();
        try {
            List<StuffVo> list = reservationService.getStuff(recordId, timeStart, timeEnd);
            result.setMsgcode("0");
            result.setSuccess(true);
            result.setData(list);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode("200");
        }
        return result;
    }
    /**
     * 查询员工下所有会员预约情况列表
     */
    @ResponseBody
    @RequestMapping(value = "getReservationList",method = RequestMethod.GET)
    public Result getReservationList(Long stuffId,String timeStart ,String timeEnd){
        Result result=new Result();
        try {
            List<ReservationVo> list = reservationService.getReservationList(stuffId, timeStart, timeEnd);
            result.setMsgcode("0");
            result.setSuccess(true);
            result.setData(list);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode("200");
        }
        return result;
    }
}
