package com.hy.salon.basic.controller;


import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.entity.ProductStockMovement;
import com.hy.salon.basic.service.ProductStockMovementService;
import com.hy.salon.basic.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/hy/basic/productStockMovement")
public class ProductStockMovementController {
    @Resource(name = "productStockMovementService")
    private ProductStockMovementService productStockMovementService;
    /**
     * 查询出入库记录
     */
    @ResponseBody
    @RequestMapping(value = "getProductBymovementType",method = RequestMethod.GET)
    public Result getProductBymovementType(Long movementType){
        Result result=new Result();
        try {
            List<ProductStockMovement> list=productStockMovementService.getProductBymovementType(movementType);
            result.setData(list);
            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }
}
