package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.service.SalonService;
import com.hy.salon.basic.service.StuffService;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.dao.RoleUserDAO;
import com.zhxh.admin.dao.SystemUserDAO;
import com.zhxh.admin.entity.RoleUser;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.utils.StringUtilsExt;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/hy/basic/stuff")
@Api(value = "StuffController| 员工控制器")
public class StuffController extends SimpleCRUDController<Stuff> {

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    @Resource(name = "stuffService")
    private StuffService stuffService;


    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @Resource(name = "salonService")
    private SalonService salonService;

    @Resource(name = "salonDao")
    private SalonDao salonDao;

    @Resource(name = "picturesDao")
    private PicturesDAO picturesDao;

    @Resource(name = "stuffJobDao")
    private StuffJobDao stuffJobDao;

    @Resource(name = "jobDAO")
    private JobDAO jobDao;

    @Resource(name = "systemUserDAO")
    private SystemUserDAO systemUserDAO;

    @Resource(name = "roleActionDao")
    private RoleActionDAO roleActionDao;

    @Resource(name = "roleUserDAO")
    private RoleUserDAO roleUserDao;






    @Override
    protected BaseDAOWithEntity<Stuff> getCrudDao() {
        return stuffDao;
    }

    /**
     * 后台管理系统获取员工按美容院分类
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getStuffAdmin")
    @ApiOperation(value="获取员工按美容院分类", notes="获取员工按美容院分类")
    public Result getStuffAdmin(String jobLevel){
        SystemUser user = authenticateService.getCurrentLogin();
        Stuff stuff2=stuffDao.getStuffForUser(user.getRecordId());
        if(jobLevel==null){
            List<Map> listMap = stuffDao.getJobLevelByStuffId(stuff2.getRecordId());
            for(Map map:listMap){
                jobLevel =  (String) map.get("jobLevel");
            }
        }
        List<Map> listMap  = new ArrayList<>();
        Result r= new Result();
        if(jobLevel.equals("0")){
            List<Salon> stuffList=salonDao.getAdminSalonForStore(stuff2.getStoreId());
            JSONArray jsonArr=new JSONArray();
            JSONObject jsonObj=new JSONObject();
            if(!stuffList.isEmpty()){
                for(Salon s :stuffList){
                    Map map = new HashMap();
                    map.put("recordId",s.getRecordId());
                    map.put("salonName",s.getSalonName());
                    listMap.add(map);
                }

                Salon s = stuffList.get(0);
                long recordId = s.getRecordId();
                List<Stuff> stuff= stuffService.getStuffForStoreId(recordId);
                for(Stuff ss:stuff){
                    Pictures pic=picturesDao.getOnePicturesForCondition(ss.getRecordId(),new Byte("1"),new Byte("0"));
                    if(null!=pic){
                        ss.setPicUrl(pic.getPicUrl());
                    }
                    List<StuffJob>  stuffJobList =stuffJobDao.getStuffJobListForStuff(ss.getRecordId());
                    if(stuffJobList.size() != 1 ){
                        for(StuffJob sj:stuffJobList){
                            Job job=jobDao.getJobForId(sj.getJobId());
                            String str=ss.getJobName()+","+job.getJobName();
                            ss.setJobName(str);
                        }

                    }

                }

                jsonObj.put("stuff",stuff);
                jsonObj.put("salonName",s.getSalonName());
                //jsonArr.add(jsonObj);
                jsonObj.put("listSalon",listMap);
            }
            r.setData(jsonObj);
        }else{
            JSONArray jsonArr=new JSONArray();
            Salon salon = salonDao.getSalonForId(stuff2.getStoreId());
            List<Stuff> stuff= stuffService.getStuffForStoreId(stuff2.getStoreId());
            for(Stuff ss:stuff){
                Pictures pic=picturesDao.getOnePicturesForCondition(ss.getRecordId(),new Byte("1"),new Byte("0"));
                if(null!=pic){
                    ss.setPicUrl(pic.getPicUrl());
                }
                List<StuffJob>  stuffJobList =stuffJobDao.getStuffJobListForStuff(ss.getRecordId());
                if(stuffJobList.size() != 1 ){
                    for(StuffJob sj:stuffJobList){
                        Job job=jobDao.getJobForId(sj.getJobId());
                        String str=ss.getJobName()+","+job.getJobName();
                        ss.setJobName(str);
                    }

                }

            }
            Map map = new HashMap();
            map.put("recordId",salon.getRecordId());
            map.put("salonName",salon.getSalonName());
            listMap.add(map);
            JSONObject jsonObj=new JSONObject();
            jsonObj.put("stuff",stuff);
            jsonObj.put("listSalon",listMap);
            r.setData(jsonObj);
        }



        r.setMsg("获取成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return r;
    }



    /**
     * 获取员工按美容院分类
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getStuff")
    @ApiOperation(value="获取员工按美容院分类", notes="获取员工按美容院分类")
    public Result getStuff(String jobLevel){
        SystemUser user = authenticateService.getCurrentLogin();
        Stuff stuff2=stuffDao.getStuffForUser(user.getRecordId());
        if(jobLevel==null){
            List<Map> listMap = stuffDao.getJobLevelByStuffId(stuff2.getRecordId());
            for(Map map:listMap){
               jobLevel =  (String) map.get("jobLevel");
            }
        }




        Result r= new Result();
        if(jobLevel.equals("0")){
            List<Salon> stuffList=salonDao.getSalonForStore(stuff2.getStoreId());

//        List<Salon> stuffList=salonService.getSalonForCreateId(user.getRecordId());

            JSONArray jsonArr=new JSONArray();
            if(!stuffList.isEmpty()){
                for(Salon s :stuffList){
                    List<Stuff> stuff= stuffService.getStuffForStoreId(s.getRecordId());
                    for(Stuff ss:stuff){
                        Pictures pic=picturesDao.getOnePicturesForCondition(ss.getRecordId(),new Byte("1"),new Byte("0"));
                        if(null!=pic){
                            ss.setPicUrl(pic.getPicUrl());
                        }
                        List<StuffJob>  stuffJobList =stuffJobDao.getStuffJobListForStuff(ss.getRecordId());
                        if(stuffJobList.size() != 1 ){
                            for(StuffJob sj:stuffJobList){
                                Job job=jobDao.getJobForId(sj.getJobId());
                                String str=ss.getJobName()+","+job.getJobName();
                                ss.setJobName(str);
                            }

                        }

                    }
                    JSONObject jsonObj=new JSONObject();
                    jsonObj.put("stuff",stuff);
                    jsonObj.put("salonName",s.getSalonName());
                    jsonArr.add(jsonObj);
                }


            }
            r.setData(jsonArr);
        }else{
            JSONArray jsonArr=new JSONArray();
            List<Stuff> stuff= stuffService.getStuffForStoreId(stuff2.getStoreId());
            for(Stuff ss:stuff){
                Pictures pic=picturesDao.getOnePicturesForCondition(ss.getRecordId(),new Byte("1"),new Byte("0"));
                if(null!=pic){
                    ss.setPicUrl(pic.getPicUrl());
                }
                List<StuffJob>  stuffJobList =stuffJobDao.getStuffJobListForStuff(ss.getRecordId());
                if(stuffJobList.size() != 1 ){
                    for(StuffJob sj:stuffJobList){
                        Job job=jobDao.getJobForId(sj.getJobId());
                        String str=ss.getJobName()+","+job.getJobName();
                        ss.setJobName(str);
                    }

                }

            }
            JSONObject jsonObj=new JSONObject();
            jsonObj.put("stuff",stuff);
            jsonArr.add(jsonObj);

        }



        r.setMsg("获取成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return r;
    }


    /**
     * 获取店长个人资料
     */
    @ResponseBody
    @RequestMapping(value="getStuffData",method = RequestMethod.GET)
    @ApiOperation(value="获取店长个人资料", notes="获取店长个人资料")
    public Result getStoreDetails() {
        Result r= new Result();
        try {
            JSONObject jsonObj=new JSONObject();
        SystemUser user = authenticateService.getCurrentLogin();
        Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
        Pictures pic=picturesDao.getOnePicturesForCondition(stuff.getRecordId(),new Byte("1"),new Byte("0"));
        Salon salon=salonDao.getSalonForStoreId(stuff.getStoreId());

        if(salon.getParentId() != -1){
            salon=salonDao.getSalonForStoreId(salon.getParentId());
        }
            StuffJob stuffJob=stuffJobDao.getStuffJobForStuff(stuff.getRecordId());
            Job job=jobDao.getJobForId(stuffJob.getJobId());

            jsonObj.put("jobLevel",job.getJobLevel());
            jsonObj.put("salon",salon.getSalonName());
            jsonObj.put("pic",pic);
            jsonObj.put("stuff",stuff);
        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        r.setData(jsonObj);
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
        }
        return r;
    }

    /**
     * 修改个人资料
     */
    @ResponseBody
    @RequestMapping(value="updateStuffData",method = RequestMethod.POST)
    @ApiOperation(value="修改个人资料", notes="修改个人资料")
    public Result updateStuffData(@RequestBody Stuff condition) {
        Result r= new Result();
        try {

            stuffDao.update(condition);
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
     * 添加员工
     */
    @ResponseBody
    @RequestMapping(value="addStuff")
    @ApiOperation(value="添加员工", notes="添加员工")
    @Transactional(rollbackFor = Exception.class)
    public Result addStuff( Stuff condition, String jobLevel ) {
        Result r= new Result();
        try {
            //检查该手机号是否被使用
            SystemUser user=systemUserDAO.getUserByCode(condition.getTel());
            if(null != user){
                r.setMsg("该账号已被使用");
                r.setSuccess(false);
                r.setMsgcode(StatusUtil.ERROR);
                return r;
            }

            condition.setGender(new Byte("2"));
            condition.setCreateDate(new Date());
            stuffDao.insert(condition);


            String[] str = jobLevel.split(",");
            for(String s :str){
                StuffJob stuffJobCondition=new StuffJob();
                stuffJobCondition.setStuffId(condition.getRecordId());
                Job j=jobDao.getJobForJobLevel(new Byte(s));
                stuffJobCondition.setJobId(j.getRecordId());
                stuffJobDao.insert(stuffJobCondition);
            }

//            Job j=jobDao.getJobForJobLevel(jobLevel);
//            stuffJobCondition.setJobId(j.getRecordId());
//            stuffJobDao.insert(stuffJobCondition);

            SystemUser userController=new SystemUser();
            userController.setUserCode(condition.getTel());
            userController.setUserName(condition.getStuffName());
            String passwordMd5 =StringUtilsExt.getMd5("123456");
            userController.setPassword(passwordMd5);
            userController.setUserStatus(0);
            systemUserDAO.insert(userController);

            RoleAction roleAction=new RoleAction();
            roleAction.setStuffId(condition.getRecordId());
            roleAction.setSystemUserId(userController.getRecordId());
            roleActionDao.insert(roleAction);

            RoleUser roleUser=new RoleUser();
            roleUser.setRoleId(new Long(2));
            roleUser.setUserId(userController.getRecordId());
            roleUserDao.insert(roleUser);



            r.setMsg("添加成功");
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
     * 搜索家人模糊查询(仅院长角色可调)
     */
    @ResponseBody
    @RequestMapping(value="fuzzyQueryStuff",method = RequestMethod.GET)
    @ApiOperation(value="搜索家人模糊查询(仅院长角色可调)", notes="搜索家人模糊查询(仅院长角色可调)")
    public Result fuzzyQueryStuff(String stuffName) {
        Result r= new Result();
        try {
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuffCondition=stuffDao.getStuffForUser(user.getRecordId());
            Map<String,String> stuff=stuffDao.fuzzyQueryStuff(stuffName,stuffCondition.getStoreId());

            r.setData(stuff);
            r.setMsg("查询成功");
            r.setMsgcode(StatusUtil.OK);
            r.setSuccess(true);

        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
        }

        return r;
    }

}
