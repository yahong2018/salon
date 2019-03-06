package com.hy.salon.stock.controller;

import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.vo.Result;
import com.hy.salon.stock.dao.ProductSockDAO;
import com.hy.salon.stock.entity.ProductStock;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/hy/salon/sock")
public class ProductStockController extends SimpleCRUDController<ProductStock> {

    @Resource(name = "productSockDAO")
    private ProductSockDAO productSockDAO;

    @Override
    protected BaseDAOWithEntity<ProductStock> getCrudDao() {
        return productSockDAO;
    }
    /**
     * 按门店查询库存的情况
     */
    @ResponseBody
    @RequestMapping(value = "findProductStockByWarehouseId",method = RequestMethod.GET)
    public Result findProductStockByWarehouseId(Long warehouseId){
        Result result=new Result();
        try {
            List<ProductStock>list=productSockDAO.findProductStockByWarehouseId(warehouseId);
            result.setMsgcode(StatusUtil.OK);
            result.setData(list);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }
}
