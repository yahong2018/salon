package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.service.ProductService;
import com.hy.salon.basic.service.ReservationService;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @Resource(name = "picturesDao")
    private PicturesDAO picturesDao;

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    @Resource(name = "productPropertyMapDAO")
    private ProductPropertyMapDAO productPropertyMapDAO;
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
    public Result getProductList(Long salonId,Long productClass,Long productSeriesId){
        Result result=new Result();
        try {
            List<Product> list=productDao.getProdectForCondition(salonId,productClass,productSeriesId);
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
     * 获取产品列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getProductListForPc",method = RequestMethod.GET)
    @ApiOperation(value="获取产品列表", notes="获取产品列表")
    public Result getProductListForPc(String salonId,int page){
        Result result=new Result();
        try {
            if(null == salonId || "".equals(salonId)){
                SystemUser user = authenticateService.getCurrentLogin();
                Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
                salonId=String.valueOf(stuff.getStoreId());
            }

            result.setTotal(productDao.getProdectForCondition(Long.parseLong(salonId)).size());
            PageHelper.startPage(page, 10);
            List<Product> list=productDao.getProdectForCondition(Long.parseLong(salonId));
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
    @RequestMapping("addProductSeries")
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
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
            condition.setStoreId(stuff.getStoreId());
            condition.setRecordStatus(new Byte("0"));
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
     * 获取品牌
     */
    @ResponseBody
    @RequestMapping(value = "queryProductSeries", method = RequestMethod.GET)
    @ApiOperation(value = "获取品牌", notes = "获取品牌")
    public Result queryProductSeries(){
        Result result=new Result();
        try {
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
            List<ProductSeries> seriesList= productSeriesDao.getSeriesForUser(stuff.getStoreId());
            result.setData(seriesList);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
            result.setMsg("获取成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
            result.setMsg("获取失败");
        }
        return result;
    }

    /**
     * 获取品牌
     */
    @ResponseBody
    @RequestMapping(value = "queryProductSeriesPC", method = RequestMethod.GET)
    @ApiOperation(value = "获取品牌", notes = "获取品牌")
    public Result queryProductSeriesPC(int page){
        Result result=new Result();
        try {
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
            result.setTotal(productSeriesDao.getSeriesForUser(stuff.getStoreId()).size());
            PageHelper.startPage(page, 10);
            List<ProductSeries> seriesList= productSeriesDao.getSeriesForUser(stuff.getStoreId());
            result.setData(seriesList);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
            result.setMsg("获取成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
            result.setMsg("获取失败");
        }
        return result;
    }


    /**
     * 获取系列
     */
    @ResponseBody
    @RequestMapping(value = "querySonProductSeries", method = RequestMethod.GET)
    @ApiOperation(value = "获取系列", notes = "通过品牌Id获取该Id系列")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "recordId", value = "品牌Id", required = true, dataType = "Long")
    })
    public Result querySonProductSeries(Long recordId){
        Result result=new Result();
        try {
            List<ProductSeries> seriesList= productSeriesDao.getSonSeriesForId(recordId);

            result.setData(seriesList);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
            result.setMsg("获取成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
            result.setMsg("获取失败");
        }
        return result;
    }


    /**
     * 获取系列列表
     */
    @ResponseBody
    @RequestMapping("querySonProductSeriesPC")
    @ApiOperation(value = "获取系列", notes = "获取系列列表")
    public Result querySonProductSeriesPC(int page,HttpServletRequest request){
        Result result=new Result();
        try {
            String filterExpr   = request.getParameter("filterExpr");
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
            result.setTotal(productSeriesDao.getSeriesForUserPc(stuff.getStoreId(),filterExpr).size());
            PageHelper.startPage(page, 10);
            List<ProductSeries> seriesList= productSeriesDao.getSeriesForUserPc(stuff.getStoreId(),filterExpr);

            result.setData(seriesList);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
            result.setMsg("获取成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
            result.setMsg("获取失败");
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
    public Result addProduct(Product condition,String Specifications,String Company,String applicableParts,String effect){
        Result result=new Result();
        try {

//            SystemUser user = authenticateService.getCurrentLogin();
//            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
            if(condition.getRecordId() == null){
//                condition.setStoreId(stuff.getStoreId());

                productDao.insert(condition);
                if(null != Specifications && !"".equals(Specifications)){
                    ProductPropertyMap productMapCondition=new ProductPropertyMap();
                    productMapCondition.setProductId(condition.getRecordId());
                    productMapCondition.setProductPropertyId(Long.parseLong(Specifications));
                    productPropertyMapDAO.insert(productMapCondition);
                }
                if(null != Company && !"".equals(Company)){
                    ProductPropertyMap productMapCondition=new ProductPropertyMap();
                    productMapCondition.setProductId(condition.getRecordId());
                    productMapCondition.setProductPropertyId(Long.parseLong(Company));
                    productPropertyMapDAO.insert(productMapCondition);
                }
                if(null != applicableParts && !"".equals(applicableParts)){
                    ProductPropertyMap productMapCondition=new ProductPropertyMap();
                    productMapCondition.setProductId(condition.getRecordId());
                    productMapCondition.setProductPropertyId(Long.parseLong(applicableParts));
                    productPropertyMapDAO.insert(productMapCondition);
                }
                if(null != effect && !"".equals(effect)){
                    ProductPropertyMap productMapCondition=new ProductPropertyMap();
                    productMapCondition.setProductId(condition.getRecordId());
                    productMapCondition.setProductPropertyId(Long.parseLong(effect));
                    productPropertyMapDAO.insert(productMapCondition);
                }



            }else{

                productDao.update(condition);
            }



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


/**
 * 删除产品
 */
    @ResponseBody
    @RequestMapping(value = "deleteProduct", method = RequestMethod.GET)
    @ApiOperation(value = "删除产品", notes = "删除产品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "recordId", value = "产品Id", required = true, dataType = "Long")
    })
    public Result deleteProduct(Product condition){
        Result result=new Result();
        try {

            productDao.delete(condition);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
            result.setMsg("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
            result.setMsg("删除失败");
        }
        return result;
    }

    /**
     * 获取产品详情
     */
    @ResponseBody
    @RequestMapping(value = "queryProductData", method = RequestMethod.GET)
    @ApiOperation(value = "获取产品详情", notes = "通过id获取产品详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "recordId", value = "产品Id", required = true, dataType = "Long")
    })
    public Result queryProductData(Long recordId){
        Result result=new Result();
        try {

            Product p=productDao.getOneProdectForId(recordId);
            JSONObject jsonObj=new JSONObject();
            List<Pictures> picList=picturesDao.getPicturesForId(p.getRecordId());

            jsonObj.put("product",p);
            jsonObj.put("picList",picList);
            result.setData(jsonObj);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
            result.setMsg("获取成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
            result.setMsg("获取失败");
        }
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "queryAllProductSeries", method = RequestMethod.GET)
    @ApiOperation(value="获取品牌", notes="以一级类别分组获取二级类别")
    public Result queryAllProductSeries(){
        Result r= new Result();
        SystemUser user = authenticateService.getCurrentLogin();
        Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
        List<ProductSeries> serList=productSeriesDao.getSeriesForUser(stuff.getStoreId());

        JSONArray jsonArr2=new JSONArray();

        for(ProductSeries s:serList){
            List<ProductSeries> sonSerList=productSeriesDao.getSonSeriesForId(s.getRecordId());
            JSONObject jsonObj2=new JSONObject();

            jsonObj2.put("ServiceRecordId",s.getRecordId());
            jsonObj2.put("ServiceName",s.getSeriesName());
            jsonObj2.put("ServiceList",sonSerList);
            jsonArr2.add(jsonObj2);
        }

        r.setMsg("获取成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        r.setData(jsonArr2);
        return r;


    }







}
