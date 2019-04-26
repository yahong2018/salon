package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.CardPurchaseDao;
import com.hy.salon.basic.dao.MemberDao;
import com.hy.salon.basic.dao.MemberGiftDao;
import com.hy.salon.basic.dao.SalonDao;
import com.hy.salon.basic.entity.Member;
import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.vo.Result;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@Api(value = "StatisticsController|美容院控制器")
@RequestMapping("/hy/basic/statistics")
public class StatisticsController {

    @Resource(name = "salonDao")
    private SalonDao salonDao;

    @Resource(name = "memberDao")
    private MemberDao memberDao;

    @Resource(name = "cardPurchaseDao")
    private CardPurchaseDao CardPurchaseDao;

    @Resource(name = "memberGiftDao")
    private MemberGiftDao MemberGiftDao;
    /**
     * 数据统计
     */
    @RequestMapping("total")
    @ResponseBody
    public Result total(){
        Result r= new Result();
        JSONObject jsonObj=new JSONObject();
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        String startTime = sDateFormat.format(zero);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date());
        calendar2.set(Calendar.HOUR_OF_DAY,23);
        calendar2.set(Calendar.MINUTE, 59);
        calendar2.set(Calendar.SECOND, 59);
        Date zero2 = calendar2.getTime();
        String endTime = sDateFormat.format(zero2);

        List<Salon> salonList=salonDao.getAllStore(startTime,endTime);

        List<Member> memberList=memberDao.getMemberForTime(startTime,endTime);

        Double sumAmount=new Double(0);
        Double sumAmount2=new Double(0);
        Double sumAmount3=new Double(0);
        Map<String,Object>  sumAmountMap=CardPurchaseDao.queryAmount(startTime,endTime,"0","0");
        if(null!=sumAmountMap){
            sumAmount=(Double)sumAmountMap.get("amount");
        }
        Map<String,Object>  sumAmountMap2=CardPurchaseDao.queryAmount(startTime,endTime,"1","0");
        if(null!=sumAmountMap2){
            sumAmount2=(Double)sumAmountMap2.get("amount");
        }

        Map<String,Object>  sumAmountMap3=CardPurchaseDao.queryAmount(startTime,endTime,null,null);
        if(null!=sumAmountMap3){
            sumAmount3=(Double)sumAmountMap3.get("amount");
        }

        Double qty=new Double(0);
        Double qty2=new Double(0);
        Map<String,Object>  gift= MemberGiftDao.getQty(startTime,endTime,"0");
        if(null!=gift){
            qty=(Double)gift.get("qty");
        }
        Map<String,Object>  gift2= MemberGiftDao.getQty(startTime,endTime,"1");
        if(null!=gift2){
            qty2=(Double)gift2.get("qty");
        }
        jsonObj.put("salonSize",salonList.size());
        jsonObj.put("memberSize",memberList.size());
        jsonObj.put("sumAmount",sumAmount);
        jsonObj.put("sumAmount2",sumAmount2);
        jsonObj.put("sumAmount3",sumAmount3);
        jsonObj.put("qty",qty);
        jsonObj.put("qty2",qty2);

        r.setData(jsonObj);
        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return r;
    }
}
