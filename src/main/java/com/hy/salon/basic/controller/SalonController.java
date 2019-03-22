package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.PicturesDAO;
import com.hy.salon.basic.dao.SalonDao;
import com.hy.salon.basic.entity.Pictures;
import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.service.SalonService;
import com.hy.salon.basic.vo.Result;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
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
@Api(value = "SalonController|美容院控制器")
@RequestMapping("/hy/basic/salon")
public class SalonController extends SimpleCRUDController<Salon> {

    @Resource(name = "salonDao")
    private SalonDao salonDao;

    @Override
    protected BaseDAOWithEntity<Salon> getCrudDao() {
        return salonDao;
    }
    @Resource(name = "salonService")
    private SalonService salonService;

    @Resource(name = "picturesDao")
    private PicturesDAO picturesDao;





    @RequestMapping(value="getSalonData",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value="获取美容院信息", notes="获取美容院信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "美容院Id", required = true, dataType = "Long"),
    })
    public Result getSalonData(Long recordId)  {
        Result r= new Result();

        Salon salon=salonService.getSalonForId(recordId);
        if(null == salon ){
            r.setMsg("没有该美容院");
            r.setMsgcode("1");
            r.setSuccess(false);
            return r;
        }
        JSONObject jsonObj=new JSONObject();
        Pictures idPic1=picturesDao.getOnePicturesForCondition(salon.getRecordId(),new Byte("0"),new Byte("2"));

        Pictures idPic2=picturesDao.getOnePicturesForCondition(salon.getRecordId(),new Byte("0"),new Byte("3"));

        Pictures businessPic=picturesDao.getOnePicturesForCondition(salon.getRecordId(),new Byte("0"),new Byte("1"));

        Pictures permitPic=picturesDao.getOnePicturesForCondition(salon.getRecordId(),new Byte("0"),new Byte("4"));

        jsonObj.put("salon",salon);
        jsonObj.put("idPic1",idPic1);
        jsonObj.put("idPic2",idPic2);
        jsonObj.put("businessPic",businessPic);
        jsonObj.put("permitPic",permitPic);



        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        r.setData(jsonObj);
        return r;

    }




    @ResponseBody
    @RequestMapping(value="getStoreList",method = RequestMethod.GET)
    @ApiOperation(value="获取门店列表", notes="获取门店列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "美容院Id", required = true, dataType = "Long"),
    })
    public Result getStoreList(Long recordId) {
        Result r= new Result();
        if( null == recordId || "".equals(recordId)){
            r.setMsg("美容院号不能为空");
            r.setMsgcode("1");
            r.setSuccess(false);
            return r;
        }
        List<Salon> StoreList=salonService.getSalonForStoreId2(recordId);

        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        r.setData(StoreList);

       return r;
    }
    /**
     * 获取门店信息
     */
    @ResponseBody
    @RequestMapping("getStoreDetails")
    @ApiOperation(value="获取门店信息", notes="获取门店信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "门店Id", required = true, dataType = "Long"),
    })
    public Result getStoreDetails(Long recordId) {
        Result r= new Result();
        if( null == recordId || "".equals(recordId)){
            r.setMsg("门店号不能为空");
            r.setMsgcode("1");
            r.setSuccess(false);
            return r;

        }

        Salon store=salonService.getSalonForId(recordId);

        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        r.setData(store);
        return r;


    }

    /**
     * 修改美容院/门店信息
     */
    @ResponseBody
    @RequestMapping("updateSalon")
    @ApiOperation(value="修改美容院/门店信息", notes="修改美容院/门店信息")
    public Result updateSalon(Salon condition,String idPic1Code,String idPic2Code,String businessPicCode,String permitPicCode){
        Result r= new Result();

        try {
        int i= salonDao.update(condition);


        Pictures newIdPic1=picturesDao.getOnePicturesForIdCondition(Long.parseLong(idPic1Code),new Byte("0"),new Byte("2"));
        Pictures idPic1=picturesDao.getOnePicturesForCondition(condition.getRecordId(),new Byte("0"),new Byte("2"));
        idPic1.setPicUrl(newIdPic1.getPicUrl());
        picturesDao.update(idPic1);

        Pictures newIdPic2=picturesDao.getOnePicturesForIdCondition(Long.parseLong(idPic2Code),new Byte("0"),new Byte("3"));
        Pictures idPic2=picturesDao.getOnePicturesForCondition(condition.getRecordId(),new Byte("0"),new Byte("3"));
        idPic2.setPicUrl(newIdPic2.getPicUrl());
        picturesDao.update(idPic2);

        Pictures newBusinessPic=picturesDao.getOnePicturesForIdCondition(Long.parseLong(businessPicCode),new Byte("0"),new Byte("1"));
        Pictures businessPic=picturesDao.getOnePicturesForCondition(condition.getRecordId(),new Byte("0"),new Byte("1"));
        businessPic.setPicUrl(newBusinessPic.getPicUrl());
        picturesDao.update(businessPic);

        Pictures newPermitPic=picturesDao.getOnePicturesForIdCondition(Long.parseLong(permitPicCode),new Byte("0"),new Byte("4"));
        Pictures permitPic=picturesDao.getOnePicturesForCondition(condition.getRecordId(),new Byte("0"),new Byte("4"));
        permitPic.setPicUrl(newPermitPic.getPicUrl());
        picturesDao.update(permitPic);

        if(i != 1){
            r.setMsg("修改失败");
            r.setMsgcode(StatusUtil.ERROR);
            r.setSuccess(false);

            return r;
        }

        r.setMsg("修改成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
        }

        return r;
    }


    /**
     * 修改美容院/门店信息
     */
    @ResponseBody
    @RequestMapping("updateStore")
    @ApiOperation(value="门店信息", notes="门店信息")
    public Result updateStore(Salon condition,String picIdList,String deletePicList){
        Result r= new Result();

        try {
            int i= salonDao.update(condition);

            if(i != 1){
                r.setMsg("修改失败");
                r.setMsgcode(StatusUtil.ERROR);
                r.setSuccess(false);

                return r;
            }


            if(null != picIdList && !"".equals(picIdList)){
                //插入照片关联
                String[] str = picIdList.split(",");
                for(String s:str){
                    Pictures pic= picturesDao.getPicForRecordId(Long.parseLong(s));
                    if(null != pic){
                        pic.setMasterDataId(condition.getRecordId());
                        picturesDao.update(pic);
                    }
                }
            }

            if(null != picIdList && !"".equals(deletePicList)){
                //删除照片关联
                String[] str2=deletePicList.split(",");
                for(String s:str2){
                    Pictures pic= picturesDao.getPicForRecordId(Long.parseLong(s));
                    if(null != pic){
                        picturesDao.delete(pic);
                    }
                }
            }



            r.setMsg("修改成功");
            r.setMsgcode(StatusUtil.OK);
            r.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
        }

        return r;
    }


    /**
     * 删除门店
     */
    @ResponseBody
    @RequestMapping("deleteStore")
    @ApiOperation(value="删除门店", notes="删除门店")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "门店Id", required = true, dataType = "Long"),
    })
    public Result deleteStore(Long recordId) {
        Result r= new Result();
        if( null == recordId || "".equals(recordId)){
            r.setMsg("门店号不能为空");
            r.setMsgcode("1");
            r.setSuccess(false);
            return r;

        }

        salonDao.deleteById(recordId);

        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return r;


    }




}
