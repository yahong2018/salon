package com.hy.salon.basic.controller;

import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.ProductDao;
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
    @Resource(name = "productDao")
    private ProductDao productDao;

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
            @ApiImplicitParam(paramType="query", name = "seriesName", value = "品牌名字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "parentId", value = "上级品牌ID", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "recordStatus", value = "记录状态:0.启用  1.停用", required = true, dataType = "byte"),
    })
    public Result addProductSeries(ProductSeries condition){
        Result result=new Result();
        try {
//            //先查看该品牌是否重名
//            List<Map> l=productSeriesDao.getSeriesForName(condition.getSeriesName(),salonId);
//            if(l.size()!=0){
//                result.setSuccess(false);
//                result.setMsgcode(StatusUtil.ERROR);
//                result.setMsg("该品牌名字已经使用");
//                return result;
//            }
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

    /**
     * 品牌名设置
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateProductSeries",method = RequestMethod.GET)
    @ApiOperation(value="品牌名设置", notes="品牌名设置")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "品牌id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "seriesName", value = "品牌名字", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "parentId", value = "上级品牌ID", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "recordStatus", value = "记录状态:0.启用  1.停用", required = true, dataType = "byte")
    })
    public Result addSeries(ProductSeries condition){
        Result result=new Result();
        try {
            productSeriesDao.update(condition);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
            result.setMsg("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
            result.setMsg("修改失败");
        }
        return result;
    }


    /**
     * 添加产品
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addProduct",method = RequestMethod.GET)
    @ApiOperation(value="品牌名设置", notes="品牌名设置")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "salonId", value = "美容院Id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "productName", value = "产品名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "productClass", value = "产品类型: 0.客装   1.院装   2.易耗品", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "productSeriesId", value = "产品品牌/系列", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "priceMarket", value = "市场价", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "price", value = "优惠价", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "productCode", value = "产品编号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "specification", value = "规格:就是数量，比如3kg/瓶", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "specificationUnit", value = "规格单位：0. g(克)   1.Kg(千克)  2.ml(毫升)  3.L(升)", required = true, dataType = "byte"),
            @ApiImplicitParam(paramType="query", name = "productUnitId", value = "单位：瓶/袋/包等", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "barCode", value = "二维码/条形码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "shelfCife", value = "保质期(月)", required = true, dataType = "byte"),
            @ApiImplicitParam(paramType="query", name = "dayOfPreWarning", value = "产品有效期预警（天）", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "stockOfPreWarning", value = "库存预警数量", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "recordStatus", value = "记录状态：0.启用   1. 停用", required = true, dataType = "byte"),
            @ApiImplicitParam(paramType="query", name = "description", value = "简介", required = true, dataType = "String")
    })
    public Result addProduct(Product condition){
        Result result=new Result();
        try {

            productDao.insert(condition);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
            result.setMsg("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
            result.setMsg("添加失败");
        }
        return result;
    }

    /**
     * 修改产品
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateProduct",method = RequestMethod.GET)
    @ApiOperation(value="修改产品", notes="修改产品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "salonId", value = "美容院Id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "productName", value = "产品名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "productClass", value = "产品类型: 0.客装   1.院装   2.易耗品", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "productSeriesId", value = "产品品牌/系列", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "priceMarket", value = "市场价", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "price", value = "优惠价", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "productCode", value = "产品编号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "specification", value = "规格:就是数量，比如3kg/瓶", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "specificationUnit", value = "规格单位：0. g(克)   1.Kg(千克)  2.ml(毫升)  3.L(升)", required = true, dataType = "byte"),
            @ApiImplicitParam(paramType="query", name = "productUnitId", value = "单位：瓶/袋/包等", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "barCode", value = "二维码/条形码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "shelfLife", value = "保质期(月)", required = true, dataType = "byte"),
            @ApiImplicitParam(paramType="query", name = "dayOfPreWarning", value = "产品有效期预警（天）", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "stockOfPreWarning", value = "库存预警数量", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "recordStatus", value = "记录状态：0.启用   1. 停用", required = true, dataType = "byte"),
            @ApiImplicitParam(paramType="query", name = "description", value = "简介", required = true, dataType = "String")
    })
    public Result updateProduct(Product condition){
        Result result=new Result();
        try {

            productDao.update(condition);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
            result.setMsg("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
            result.setMsg("修改失败");
        }
        return result;
    }









}
