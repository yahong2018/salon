package com.hy.salon.basic.controller;

import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.ProductSeriesDAO;
import com.hy.salon.basic.entity.Product;
import com.hy.salon.basic.entity.ProductSeries;
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
import java.util.Map;

@Controller
@RequestMapping("/hy/basic/product")
@Api(value = "ProductController| 产品控制器")
public class ProductController {
    @Resource(name = "productService")
    private ProductService productService;


    @Resource(name = "productSeriesDao")
    private ProductSeriesDAO productSeriesDao;
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



    /**
     * 添加品牌
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addProductSeries",method = RequestMethod.GET)
    @ApiOperation(value="添加品牌", notes="添加品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "seriesName", value = "品牌名字", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "parentId", value = "上级品牌ID", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "recordStatus", value = "记录状态:0.启用  1.停用", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "salonId", value = "美容院号", required = true, dataType = "Long")
    })
    public Result addProductSeries(ProductSeries condition,Long salonId){
        Result result=new Result();
        try {
            //先查看该品牌是否重名
            List<Map> l=productSeriesDao.getSeriesForName(condition.getSeriesName(),salonId);
            if(l.size()!=0){
                result.setSuccess(false);
                result.setMsgcode(StatusUtil.ERROR);
                result.setMsg("该品牌名字已经使用");
                return result;
            }
            productSeriesDao.insert(condition);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
            result.setMsg("插入成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
            result.setMsg("插入失败");
        }
        return result;
    }



}
