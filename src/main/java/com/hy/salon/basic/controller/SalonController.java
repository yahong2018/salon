package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.service.SalonService;
import com.hy.salon.basic.util.MapUtils;
import com.hy.salon.basic.util.UuidUtils;
import com.hy.salon.basic.util.messageUtil;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.dao.RoleUserDAO;
import com.zhxh.admin.dao.SystemRoleDAO;
import com.zhxh.admin.dao.SystemUserDAO;
import com.zhxh.admin.entity.RoleUser;
import com.zhxh.admin.entity.SystemRole;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.misc.SessionManager;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.utils.Logger;
import com.zhxh.core.utils.StringUtilsExt;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
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

    @Resource(name = "systemUserDAO")
    private SystemUserDAO systemUserDAO;

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    @Resource(name = "stuffJobDao")
    private StuffJobDao stuffJobDao;

    @Resource(name = "jobDAO")
    private JobDAO jobDao;

    @Resource(name = "roleActionDao")
    private RoleActionDAO roleActionDao;

    @Resource(name = "roleUserDAO")
    private RoleUserDAO roleUserDao;

    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @Resource(name = "salonInviteCodeDAO")
    private SalonInviteCodeDAO salonInviteCodeDAO;

    @Resource(name = "cityDAO")
    private CityDAO cityDAO;

    @Resource(name = "shiftDao")
    private ShiftDao shiftDao;

    @Resource(name= "verificationCodeTemporaryDao")
    private  VerificationCodeTemporaryDAO verificationCodeTemporaryDAO;

    @Resource(name = "systemRoleDAO")
    private SystemRoleDAO systemRoleDAO;



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
    public Result getStoreList(Long recordId,int page,String jobLevel) {
        Result r= new Result();
        List<Salon> StoreList=new ArrayList<>();
        if(null!=jobLevel){
        if("0".equals(jobLevel)){
            r.setTotal(salonService.getSalonForStoreId2(recordId).size());
            PageHelper.startPage(page, 10);
            StoreList=salonService.getSalonForStoreId2(recordId);

        }else{
            Salon salon=salonService.getSalonForId(recordId);
            StoreList.add(salon);

        }


        }else{
            if(recordId == null ||  recordId == 0){
                r.setTotal(salonDao.getSalonForStoreId2(new Long(-1)).size());
                PageHelper.startPage(page, 10);
                StoreList=salonDao.getSalonForStoreId2(new Long(-1));
            }else{
                r.setTotal(salonService.getSalonForStoreId2(recordId).size());
                PageHelper.startPage(page, 10);
                StoreList=salonService.getSalonForStoreId2(recordId);
            }
            if(StoreList.size()!=0){
                for(Salon s:StoreList){
                    SalonInviteCode salonCode=salonInviteCodeDAO.getSalonForSalonId(s.getRecordId());
                    if(salonCode!=null){
                        s.setInviteCode(salonCode.getInviteCode());
                    }

                }

            }
        }




        r.setData(StoreList);

        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);


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
        JSONObject jsonObj=new JSONObject();
        Salon store=salonService.getSalonForId(recordId);

        Pictures idPic1=picturesDao.getOnePicturesForCondition(recordId,new Byte("0"),new Byte("2"));

        Pictures idPic2=picturesDao.getOnePicturesForCondition(recordId,new Byte("0"),new Byte("3"));

        Pictures businessPic=picturesDao.getOnePicturesForCondition(recordId,new Byte("0"),new Byte("1"));

        Pictures permitPic=picturesDao.getOnePicturesForCondition(recordId,new Byte("0"),new Byte("4"));

        List<Pictures> pic=picturesDao.getPicturesForCondition(recordId,new Byte("0"),new Byte("0"));

        jsonObj.put("idPic1",idPic1);
        jsonObj.put("idPic2",idPic2);
        jsonObj.put("businessPic",businessPic);
        jsonObj.put("permitPic",permitPic);
        jsonObj.put("pic",pic);
        jsonObj.put("store",store);


        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        r.setData(jsonObj);
        return r;


    }

    /**
     * 修改美容院/门店信息
     */
    @ResponseBody
    @RequestMapping("updateSalon")
    @ApiOperation(value="修改美容院/门店信息", notes="修改美容院/门店信息")
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
    public Result updateStore(Salon condition,String picIdList,String deletePicList,String idPic1Code,String idPic2Code,String businessPicCode,String permitPicCode){
        Result r= new Result();

        try {

            double[] aaa = MapUtils.getLatAndLonByAddress(condition.getAddress());
            if(null == aaa){
                r.setMsg("请输入详细地址");
                r.setSuccess(false);
                r.setMsgcode(StatusUtil.ERROR);
                return r;
            }
            condition.setLongitude(aaa[0]);
            condition.setLatitude(aaa[1]);

            int i= salonDao.update(condition);

            if(i != 1){
                r.setMsg("修改失败");
                r.setMsgcode(StatusUtil.ERROR);
                r.setSuccess(false);

                return r;
            }

            if(null!= idPic1Code && !"0".equals(idPic1Code)){
                Pictures newIdPic1=picturesDao.getOnePicturesForIdCondition(Long.parseLong(idPic1Code),new Byte("0"),new Byte("2"));
                Pictures idPic1=picturesDao.getOnePicturesForCondition(condition.getRecordId(),new Byte("0"),new Byte("2"));
                if(null == idPic1){
                    newIdPic1.setMasterDataId(condition.getRecordId());
                    picturesDao.update(newIdPic1);
                }else{
                    idPic1.setPicUrl(newIdPic1.getPicUrl());
                    picturesDao.update(idPic1);
                }

            }


            if(null!= idPic2Code && !"0".equals(idPic2Code)){
                Pictures newIdPic2=picturesDao.getOnePicturesForIdCondition(Long.parseLong(idPic2Code),new Byte("0"),new Byte("3"));
                Pictures idPic2=picturesDao.getOnePicturesForCondition(condition.getRecordId(),new Byte("0"),new Byte("3"));
                if(null == idPic2){
                    newIdPic2.setMasterDataId(condition.getRecordId());
                    picturesDao.update(newIdPic2);
                }else{
                    idPic2.setPicUrl(newIdPic2.getPicUrl());
                    picturesDao.update(idPic2);
                }

            }

            if(null!= businessPicCode && !"0".equals(businessPicCode)){
                Pictures newBusinessPic=picturesDao.getOnePicturesForIdCondition(Long.parseLong(businessPicCode),new Byte("0"),new Byte("1"));
                Pictures businessPic=picturesDao.getOnePicturesForCondition(condition.getRecordId(),new Byte("0"),new Byte("1"));
                if(null == businessPic){
                    newBusinessPic.setMasterDataId(condition.getRecordId());
                    picturesDao.update(newBusinessPic);
                }else{
                    businessPic.setPicUrl(newBusinessPic.getPicUrl());
                    picturesDao.update(businessPic);
                }

            }



//            Pictures newIdPic1=picturesDao.getOnePicturesForIdCondition(Long.parseLong(idPic1Code),new Byte("0"),new Byte("2"));
//            Pictures idPic1=picturesDao.getOnePicturesForCondition(condition.getRecordId(),new Byte("0"),new Byte("2"));
//            idPic1.setPicUrl(newIdPic1.getPicUrl());
//            picturesDao.update(idPic1);

//            Pictures newIdPic2=picturesDao.getOnePicturesForIdCondition(Long.parseLong(idPic2Code),new Byte("0"),new Byte("3"));
//            Pictures idPic2=picturesDao.getOnePicturesForCondition(condition.getRecordId(),new Byte("0"),new Byte("3"));
//            idPic2.setPicUrl(newIdPic2.getPicUrl());
//            picturesDao.update(idPic2);

//            Pictures newBusinessPic=picturesDao.getOnePicturesForIdCondition(Long.parseLong(businessPicCode),new Byte("0"),new Byte("1"));
//            Pictures businessPic=picturesDao.getOnePicturesForCondition(condition.getRecordId(),new Byte("0"),new Byte("1"));
//            businessPic.setPicUrl(newBusinessPic.getPicUrl());
//            picturesDao.update(businessPic);


            if(null!= permitPicCode && !"0".equals(permitPicCode)){
                Pictures newPermitPic=picturesDao.getOnePicturesForIdCondition(Long.parseLong(permitPicCode),new Byte("0"),new Byte("4"));
                Pictures permitPic=picturesDao.getOnePicturesForCondition(condition.getRecordId(),new Byte("0"),new Byte("4"));
                if(null == permitPic){
                    newPermitPic.setMasterDataId(condition.getRecordId());
                    picturesDao.update(newPermitPic);
                }else{
                    permitPic.setPicUrl(newPermitPic.getPicUrl());
                    picturesDao.update(permitPic);
                }

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

            if(null != deletePicList && !"".equals(deletePicList)){
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


    /**
     * 创建门店
     */
    @ResponseBody
    @RequestMapping("createStore")
    @ApiOperation(value="添加门店", notes="添加门店")
    @Transactional(rollbackFor = Exception.class)
    public Result createStore(Salon condition, String picIdList, String inviteCode, String idPic1Code, String idPic2Code, String businessPicCode, String permitPicCode,String verificationCode) {
        Result r= new Result();

        HttpSession session= SessionManager.getCurrentSession();
        synchronized (session) {
            List<VerificationCodeTemporary> verificationCodeTemporaryList = verificationCodeTemporaryDAO.getCode(condition.getTel());
            if (verificationCodeTemporaryList.size() != 0) {
                VerificationCodeTemporary verificationCodeTemporary = verificationCodeTemporaryList.get(verificationCodeTemporaryList.size() - 1);
                if (!verificationCodeTemporary.getVerificationCode().equals(verificationCode)) {
                    r.setMsg("验证码错误");
                    r.setSuccess(false);
                    r.setMsgcode(StatusUtil.ERROR);
                    return r;
                }

            }


            SalonInviteCode salonInviteCode = salonInviteCodeDAO.getSalonForCode(inviteCode);
            if (null == salonInviteCode) {
                r.setMsg("请输入正确的邀请码");
                r.setSuccess(false);
                r.setMsgcode(StatusUtil.ERROR);
                return r;
            }

            //检查该手机号是否被使用
            SystemUser user = systemUserDAO.getUserByCode(condition.getTel());
            if (null != user) {
                r.setMsg("该账号已被使用");
                r.setSuccess(false);
                r.setMsgcode(StatusUtil.ERROR);
                return r;
            }

            condition.setAudit(0);
            condition.setBedNum(0);
            condition.setArea(new Double(0));
            condition.setParentId(salonInviteCode.getSalonId());

            double[] aaa = MapUtils.getLatAndLonByAddress(condition.getAddress());
            if (null == aaa) {
                r.setMsg("请输入详细地址");
                r.setSuccess(false);
                r.setMsgcode(StatusUtil.ERROR);
                return r;
            }
            condition.setLongitude(aaa[0]);
            condition.setLatitude(aaa[1]);
            salonDao.insert(condition);

            Stuff stuffCondition = new Stuff();
            stuffCondition.setStoreId(condition.getRecordId());
            stuffCondition.setStuffName(condition.getTel());
            stuffCondition.setTel(condition.getTel());
            stuffCondition.setGender(new Byte("2"));
            stuffDao.insert(stuffCondition);

            StuffJob stuffJobCondition = new StuffJob();
            stuffJobCondition.setStuffId(stuffCondition.getRecordId());
            Job j = jobDao.getJobForJobLevel(new Byte("1"));
            stuffJobCondition.setJobId(j.getRecordId());
            stuffJobDao.insert(stuffJobCondition);


            SystemUser userController = new SystemUser();
            userController.setUserCode(stuffCondition.getTel());
            userController.setUserName(stuffCondition.getStuffName());
            String passwordMd5 = StringUtilsExt.getMd5("123456");
            userController.setPassword(passwordMd5);
            userController.setUserStatus(0);
            systemUserDAO.insert(userController);

            RoleAction roleAction = new RoleAction();
            roleAction.setStuffId(stuffCondition.getRecordId());
            roleAction.setSystemUserId(userController.getRecordId());
            roleActionDao.insert(roleAction);

            SystemRole systemRole = systemRoleDAO.getRole("dianzhang");

            RoleUser roleUser = new RoleUser();
            roleUser.setRoleId(systemRole.getRecordId());
            roleUser.setUserId(userController.getRecordId());
            roleUserDao.insert(roleUser);

            for (int i = 0; i < 4; i++) {
                Shift shiftCondition = new Shift();
                shiftCondition.setStoreId(condition.getRecordId());
                shiftCondition.setShiftType(i);
                shiftCondition.setTimeStart("9:00");
                shiftCondition.setTimeEnd("19:00");
                shiftDao.insert(shiftCondition);
            }


            if (null != picIdList && !"".equals(picIdList)) {
                //插入照片关联
                String[] str = picIdList.split(",");
                for (String s : str) {
                    Pictures pic = picturesDao.getPicForRecordId(Long.parseLong(s));
                    if (null != pic) {
                        pic.setMasterDataId(condition.getRecordId());
                        picturesDao.update(pic);
                    }
                }
            }


            Pictures idPic1 = picturesDao.getPicForRecordId(new Long(idPic1Code));
            idPic1.setMasterDataId(condition.getRecordId());
            picturesDao.update(idPic1);

            Pictures idPic2 = picturesDao.getPicForRecordId(new Long(idPic2Code));
            idPic2.setMasterDataId(condition.getRecordId());
            picturesDao.update(idPic2);

            Pictures businessPic = picturesDao.getPicForRecordId(new Long(businessPicCode));
            businessPic.setMasterDataId(condition.getRecordId());
            picturesDao.update(businessPic);

            if (null != permitPicCode && !"".equals(permitPicCode)) {
                Pictures permitPic = picturesDao.getPicForRecordId(new Long(permitPicCode));
                permitPic.setMasterDataId(condition.getRecordId());
                picturesDao.update(permitPic);
            }
            r.setMsg("添加成功");
            r.setMsgcode(StatusUtil.OK);
            r.setSuccess(true);



            return r;
        }






    }



    /**
     * 创建美容院
     */
    @ResponseBody
    @RequestMapping(value="createSalon",method = RequestMethod.POST)
    @ApiOperation(value="添加门店", notes="添加门店")
    @Transactional(rollbackFor = Exception.class)
    public Result createSalon(Salon condition) {
        Result r= new Result();

        HttpSession session= SessionManager.getCurrentSession();
        synchronized (session) {

            //检查该手机号是否被使用
            SystemUser user = systemUserDAO.getUserByCode(condition.getTel());
            if (null != user) {
                r.setMsg("该账号已被使用");
                r.setSuccess(false);
                r.setMsgcode(StatusUtil.ERROR);
                return r;
            }
            condition.setAudit(1);
            condition.setParentId(new Long(-1));
            condition.setCityId(Long.parseLong("0"));
            condition.setBedNum(0);
            condition.setArea(new Double(0));
            condition.setParentId(new Long(-1));
            condition.setLatitude(new Double(0));
            condition.setLongitude(new Double(0));
            salonDao.insert(condition);

            Stuff stuffCondition = new Stuff();
            stuffCondition.setStoreId(condition.getRecordId());
            stuffCondition.setStuffName(condition.getTel());
            stuffCondition.setTel(condition.getTel());
            stuffCondition.setGender(new Byte("2"));
            stuffDao.insert(stuffCondition);

            StuffJob stuffJobCondition = new StuffJob();
            stuffJobCondition.setStuffId(stuffCondition.getRecordId());
            Job j = jobDao.getJobForJobLevel(new Byte("0"));
            stuffJobCondition.setJobId(j.getRecordId());
            stuffJobDao.insert(stuffJobCondition);


            SystemUser userController = new SystemUser();
            userController.setUserCode(stuffCondition.getTel());
            userController.setUserName(stuffCondition.getStuffName());
            String passwordMd5 = StringUtilsExt.getMd5("123456");
            userController.setPassword(passwordMd5);
            userController.setUserStatus(1);
            systemUserDAO.insert(userController);

            RoleAction roleAction = new RoleAction();
            roleAction.setStuffId(stuffCondition.getRecordId());
            roleAction.setSystemUserId(userController.getRecordId());
            roleActionDao.insert(roleAction);


            SystemRole systemRole = systemRoleDAO.getRole("yuanzhang");

            RoleUser roleUser = new RoleUser();
            roleUser.setRoleId(systemRole.getRecordId());
            roleUser.setUserId(userController.getRecordId());
            roleUserDao.insert(roleUser);

            //插入八位随机码数
            SalonInviteCode salonCode = new SalonInviteCode();
            salonCode.setSalonId(condition.getRecordId());
            String s = UuidUtils.generateShortUuid();
            salonCode.setInviteCode(s);
            salonInviteCodeDAO.insert(salonCode);


            r.setMsg("添加成功");
            r.setMsgcode(StatusUtil.OK);
            r.setSuccess(true);



        return r;

        }
    }


    /**
     * 获取最新省市区Data
     */
    @ResponseBody
    @RequestMapping("writeIn")
    @ApiOperation(value="获取最新省市区Data", notes="获取最新省市区Data")
    public Result writeIn(HttpServletRequest request) {
        Result r= new Result();
        JSONArray jsonArr=new JSONArray();

        List<City> provinceList=cityDAO.getCity("0");
        for(City c:provinceList){
            JSONObject jsonObj=new JSONObject();
            JSONArray jsonArr2=new JSONArray();
            jsonObj.put("cityCode",c.getCityCode());
            jsonObj.put("cityName",c.getCityName());

            //获取市
            List<City> cityList=cityDAO.getCity(c.getCityCode());
            //获取区
            for(City c2:cityList){
                JSONObject jsonObj2=new JSONObject();
                JSONArray jsonArr3=new JSONArray();
                jsonObj2.put("cityCode",c2.getCityCode());
                jsonObj2.put("cityName",c2.getCityName());

                List<City> areaList=cityDAO.getCity(c2.getCityCode());
                for(City c3:areaList){
                    JSONObject jsonObj3=new JSONObject();
                    jsonObj3.put("cityCode",c3.getCityCode());
                    jsonObj3.put("cityName",c3.getCityName());
                    jsonArr3.add(jsonObj3);
                }
                jsonObj2.put("counties",jsonArr3);
                jsonArr2.add(jsonObj2);


            }

            jsonObj.put("counties",jsonArr2);
            jsonArr.add(jsonObj);

        }


        try {
            String pathname = "C:/city/output.txt";
//            String pathname = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/city/output.txt";
            String pathname2 = "C:/city";
            java.io.File folder = new java.io.File("C:/city");
            if (!folder.exists()) {
                folder.mkdirs();     ///如果不存在，创建目录
            }
            File writeName = new File(pathname);
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write(jsonArr.toJSONString());
                out.flush(); // 把缓存区内容压入文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(jsonArr.toJSONString());

        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        r.setData(jsonArr);
        return r;


    }


    /**
     * 获取省市区
     */
    @ResponseBody
    @RequestMapping("getCity")
    @ApiOperation(value="获取省市区", notes="获取省市区")

    public Result getCity(HttpServletRequest request) {
        Result r= new Result();
        String pathname="C:/city/output.txt";


        String line;
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                JSONArray jsonArr=JSONArray.parseArray(line);
                r.setData(jsonArr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);

        return r;


    }

    /**
     * 门店审核列表
     */
    @ResponseBody
    @RequestMapping("getAuditStoreList")
    @ApiOperation(value="门店审核列表", notes="门店审核列表")
    public Result getAuditStoreList(int page) {
        Result r= new Result();
        SystemUser user = authenticateService.getCurrentLogin();
        Stuff stuff2=stuffDao.getStuffForUser(user.getRecordId());

        r.setTotal(salonDao.getSalonForStoreId2(stuff2.getStoreId()).size());
        PageHelper.startPage(page, 10);
        List<Salon> salonList= salonDao.getSalonForStoreId2(stuff2.getStoreId());

        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        r.setData(salonList);
        return r;


    }

    @RequestMapping("auditPass")
    @ResponseBody
    public Result auditPass(@RequestBody Long[] recordIdList) {
        Result r= new Result();
            for(Long recordId:recordIdList){
                Salon salon=salonDao.getSalonForStoreId(recordId);
                salon.setAudit(1);
                salonDao.update(salon);
            }
        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return r;


    }

    @RequestMapping("auditPassForPc")
    @ResponseBody
    public Result auditPassForPc(Long recordId) {
        Result r= new Result();

            Salon salon=salonDao.getSalonForStoreId(recordId);
            salon.setAudit(1);
            salonDao.update(salon);

        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return r;


    }

    /**
     * 发送短信验证码
     */
    @RequestMapping("sendMessage")
    @ResponseBody
    public Result sendMessage(String tel){
        Result r= new Result();
        int  num=(int)(Math.random()*9000)+1000;

        VerificationCodeTemporary  verificationCodeTemporary= new VerificationCodeTemporary();
        verificationCodeTemporary.setPhoneNo(tel);
        verificationCodeTemporary.setVerificationCode(String.valueOf(num));
        verificationCodeTemporary.setEffectiveness(0);
        verificationCodeTemporary.setValidTime(3);
        verificationCodeTemporaryDAO.insert(verificationCodeTemporary);

        String typeCode=messageUtil.sendMessage(tel,"【合一美容院】验证码为"+num+"，在5分钟内有效。");
        System.out.println("typeCode========================"+typeCode);

        if(!"0".equals(typeCode)){
            r.setMsg("请求失败");
            r.setMsgcode(StatusUtil.ERROR);
            r.setSuccess(false);
            return r;
        }

        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return r;
    }

    /**
     * 发送短信验证码
     */
    @RequestMapping("testSendMessage")
    @ResponseBody
    public Result testSendMessage(String tel){
        Result r= new Result();
        int  num=(int)(Math.random()*9000)+1000;

        VerificationCodeTemporary  verificationCodeTemporary= new VerificationCodeTemporary();
        verificationCodeTemporary.setPhoneNo(tel);
        verificationCodeTemporary.setVerificationCode(String.valueOf(num));
        verificationCodeTemporary.setEffectiveness(0);
        verificationCodeTemporary.setValidTime(3);
        verificationCodeTemporaryDAO.insert(verificationCodeTemporary);

        String typeCode=messageUtil.sendMessage(tel,"【合一美容院】验证码为"+num+"，在5分钟内有效。");
        System.out.println("返回typeCode==="+typeCode);
        Logger.debug("返回typeCode==="+typeCode);

        if(!"0".equals(typeCode)){
            r.setMsg("请求失败");
            r.setMsgcode(StatusUtil.ERROR);
            r.setSuccess(false);
            return r;
        }

        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return r;
    }



    /**
     * 获取门店分布
     */
    @RequestMapping("querySalonIndex")
    @ResponseBody
    public Result querySalonIndex(){
        Result r= new Result();


        List<Salon> salonList=salonDao.getAllStore(null,null);

        r.setData(salonList);
        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return r;
    }




    /**
     * 导出报表
     */
    @SuppressWarnings("deprecation")
    @RequestMapping("/exportSalon")
    @ResponseBody
    public Result ExportAeonOrder(HttpServletRequest req, HttpServletResponse resp,String type) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST,GET");
        Result r = new Result();
        JSONObject jsonObj = new JSONObject();

        List<Salon> salonList = salonDao.getAll();

        if("1".equals(type)){
            salonList = salonDao.getSalonForStoreId2(new Long(-1));
        }else{
            salonList = salonDao.getAll();
        }


        String dir = req.getServletContext().getRealPath("/orderData");
        java.io.File folder = new java.io.File(dir);
        if (!folder.exists()) {
            folder.mkdirs();     ///如果不存在，创建目录
        }

        // 创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //在webbook中添加一个sheet,对应Excel文件中的sheet
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String date1 = sdf1.format(date);

        HSSFSheet sheet = wb.createSheet(" 美容院导出" + date1);
        // 在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 创建单元格，并设置值表头 设置表头居中
        //设置样式
        HSSFFont font = wb.createFont();
        font.setFontName("仿宋_GB2312");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示

        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(font);

        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

        style.setBorderRight(BorderStyle.THIN);
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("美容院/门店名称");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("联系电话");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("地址");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("床位数");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("面积");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("简介");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("创建时间");
        cell.setCellStyle(style);


        for (int i = 0; i < salonList.size(); i++) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = sdf.format(salonList.get(i).getCreateDate());

            row = sheet.createRow((int) i + 1);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(salonList.get(i).getSalonName());
            HSSFCell cell1 = row.createCell((short) 1);
            cell1.setCellValue(salonList.get(i).getTel());
            cell1.setCellStyle(style2);
        	/* HSSFCell cell2=row.createCell((short) 2);
        	 cell2.setCellValue("商户名称:"+merOrders.get(i).getMerName()+"商户编号:"+merOrders.get(i).getMerCode());
        	 cell2.setCellStyle(style2);  */
            row.createCell((short) 2).setCellValue(salonList.get(i).getAddress());
            row.createCell((short) 3).setCellValue(salonList.get(i).getBedNum());
            row.createCell((short) 4).setCellValue(salonList.get(i).getArea());
            row.createCell((short) 5).setCellValue(salonList.get(i).getDescription());
            row.createCell((short) 6).setCellValue(time);

            //设置单元格宽度
            sheet.autoSizeColumn((short) 0);
            sheet.autoSizeColumn((short) 1);
            sheet.autoSizeColumn((short) 2);
            sheet.setColumnWidth((short) 3, 6 * 2 * 256);
            sheet.autoSizeColumn((short) 4);
            sheet.setColumnWidth((short) 5, 6 * 2 * 256);
            sheet.autoSizeColumn((short) 6);


        }
        try {

            /* FileOutputStream fout = new FileOutputStream(req.getServletContext().getRealPath("/orderData")+"\\订单导出"+date1+".csv"); */
            FileOutputStream fout = new FileOutputStream(req.getServletContext().getRealPath("/orderData") + "/美容院信息导出" + date1 + ".csv");
            System.out.println("/美容院信息导出" + date1 + ".csv");
            wb.write(fout);
            fout.close();
            wb.close();
            jsonObj.put("respCode", "0000");
            jsonObj.put("dataUrl", "美容院信息导出" + date1 + ".csv");
            r.setData(jsonObj);
            r.setMsg("请求成功");
            r.setMsgcode(StatusUtil.OK);
            r.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return r;
    }








}
