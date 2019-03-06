package com.hy.salon.basic.controller;

import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.SalonDao;
import com.hy.salon.basic.entity.StockTransferApplication;
import com.hy.salon.basic.service.StockTransferApplicationService;
import com.hy.salon.basic.vo.Result;
import com.hy.salon.basic.vo.StockTransferApplicationVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/hy/basic/stockTransferApplication")
public class StockTransferApplicationController {
    @Resource(name = "stockTransferApplicationService")
    private StockTransferApplicationService stockTransferApplicationService;
    //调拨申请单

    /**
     * 新增调拨
     * @param stockTransferApplicationVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addStockTransferApplication",method = RequestMethod.POST)
    public Result addStockTransferApplication(@RequestBody StockTransferApplicationVo stockTransferApplicationVo){
        Result result=new Result();
        try {
            stockTransferApplicationService.addStockTransferApplication(stockTransferApplicationVo);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
        }catch (Exception e){
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * 查询调拨记录  根据状态
     */
    @ResponseBody
    @RequestMapping(value = "getStockTransferApplication",method = RequestMethod.GET)
    public Result getStockTransferApplication(Integer recordStatus){
        Result result=new Result();
        try {
            List<StockTransferApplicationVo>list=stockTransferApplicationService.getStockTransferApplication(recordStatus);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
            result.setData(list);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
        }
        return result;
    }

}
