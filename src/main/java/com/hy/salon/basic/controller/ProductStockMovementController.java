package com.hy.salon.basic.controller;


import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.entity.ProductStockMovement;
import com.hy.salon.basic.service.ProductStockMovementService;
import com.hy.salon.basic.vo.ProductStockMovementVo;
import com.hy.salon.basic.vo.Result;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hy.salon.basic.dao.ProductStockMovementDao;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hy/basic/productStockMovement")
@Api(value = "ProductStockMovementController| 产品库存异动控制器")
public class ProductStockMovementController extends SimpleCRUDController<ProductStockMovement>{
    @Resource(name = "productStockMovementService")
    private ProductStockMovementService productStockMovementService;
    @Resource(name="productStockMovementDao")
    private ProductStockMovementDao productStockMovementDao;
    @Override
    protected BaseDAOWithEntity<ProductStockMovement> getCrudDao() {
        return productStockMovementDao;
    }
    /**
     * 查询出入库记录
     * recordCreateType记录产生方式：0.手工  1.扫码
     */
    @ResponseBody
    @RequestMapping(value = "getProductBymovementType",method = RequestMethod.GET)
    @ApiOperation(value="查询出入库记录", notes="查询出入库记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordCreateType", value = "记录产生方式：0.手工  1.扫码", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "movementType", value = "异动类型：0~63 为入库    64~127为出库", required = true, dataType = "Long"),
    })
    public Result getProductBymovementType(Integer recordCreateType,Long movementType){
        Result result=new Result();
        try {
            List<ProductStockMovement> list=productStockMovementService.getProductBymovementType(recordCreateType,movementType);
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
    /**
     * 新增入库
     */
    @ResponseBody
    @RequestMapping(value = "addProductBymovement",method = RequestMethod.POST)
    public Result addProductBymovement(@RequestBody ProductStockMovement productStockMovement){
        Result result=new Result();
        try {
            Boolean flag = productStockMovementService.addProductBymovement(productStockMovement);
            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * 新增出库
     */
    @ResponseBody
    @RequestMapping(value = "outProductBymovement",method = RequestMethod.POST)
    public Result outProductBymovement(@RequestBody ProductStockMovement productStockMovement){
        Result result=new Result();
        try {
            productStockMovementService.outProductBymovement(productStockMovement);
            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * 查询出入库记录,查询盘点记录
     */
    @ResponseBody
    @RequestMapping(value = "findProductStock",method = RequestMethod.GET)
    public Result findProductStock(Integer movementType,String createTime,String endTime){
        Result result=new Result();
        try {
            List<ProductStockMovementVo>list=productStockMovementService.findProductStock(movementType,createTime,endTime);
            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
            result.setData(list);
        }catch (Exception e){
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }
    /**
     * 盘点修改产品数量
     */
    @ResponseBody
    @RequestMapping(value = "updateProduct",method = RequestMethod.POST)
    public Result updateProduct(@RequestBody ProductStockMovement productStockMovement){
        Result result=new Result();
        try {
            productStockMovementService.updateProduct(productStockMovement);
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
