package com.hy.salon.basic.controller;

import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.entity.Product;
import com.hy.salon.basic.service.ProductService;
import com.hy.salon.basic.service.ReservationService;
import com.hy.salon.basic.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/hy/basic/product")
@Api(value = "ProductController| 产品控制器")
public class ProductController {
    @Resource(name = "productService")
    private ProductService productService;

    /**
     * 获取产品列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getProductList",method = RequestMethod.GET)
    @ApiOperation(value="获取产品列表", notes="获取产品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "salonId", value = "美容院ID", required = true, dataType = "Long")
    })
    public Result getProductList(Long salonId){
        Result result=new Result();
        try {
            List<Product> list=productService.getProductList(salonId);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
            result.setMsg("查询成功");
            result.setData(list);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.ERROR);
            result.setMsg("查询失败");
        }
        return result;
    }
}
