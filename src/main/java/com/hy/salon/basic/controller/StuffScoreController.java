package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hy/basic/stuffScore")
@Api(value = "StuffScoreController | 积分控制器")
public class StuffScoreController {
    @Resource(name = "stuffScoreRecordDao")
    private StuffScoreRecordDAO stuffScoreRecordDao;

    @Resource(name = "stuffScoreDao")
    private StuffScoreDAO stuffScoreDao;

    @Resource(name = "stuffJobDao")
    private StuffJobDao stuffJobDao;

    @Resource(name = "jobDAO")
    private JobDAO jobDao;



    @Resource(name = "salonDao")
    private SalonDao salonDao;

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    @Resource(name = "picturesDao")
    private PicturesDAO picturesDao;


    @ResponseBody
    @RequestMapping("addScoreRecord")
    @ApiOperation(value="院长给员工添加积分", notes="院长给员工添加积分")
    public Result addScoreRecord(StuffScoreRecord condition){
        Result r= new Result();
        try {
            StuffScore stuffScore=stuffScoreDao.getStuffScore(condition.getStuffId());
            if(stuffScore==null){
                StuffScore sf = new StuffScore();
                condition.setTotalScore(condition.getScore());
                sf.setStuffId(condition.getStuffId());
                sf.setExisting(condition.getScore());
                stuffScoreDao.insert(sf);
            }else{
                condition.setTotalScore(condition.getScore()+stuffScore.getExisting());
                stuffScore.setExisting(stuffScore.getExisting()+condition.getScore());
                stuffScoreDao.update(stuffScore);
            }
            stuffScoreRecordDao.insert(condition);


            r.setMsg("添加成功");
            r.setMsgcode("0");
            r.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
            r.setMsg("添加失败");
        }
        return r;


    }



    @ResponseBody
    @RequestMapping("queryScoreRecord")
    @ApiOperation(value="积分列表页", notes="积分列表页")
    public Result queryScoreRecord(Long salonId){
        Result r= new Result();
        try {

            List<Salon> salonList= salonDao.getSalonForStoreId2(salonId);
            for(Salon s:salonList){
                Long existing=new Long(0);
                Long existingCount=new Long(0);
                List<Map<String,Object>> existingList=stuffScoreRecordDao.queryExisting(salonId);
                if(!existingList.isEmpty()){
                    for(Map<String,Object> m:existingList){
                        if(null!=m){
                            existing=existing+(Long)m.get("Existing");
                        }

                    }
                }

                s.setExisting(existing);
                List<Map<String,Object>> existingCountList=stuffScoreRecordDao.queryExistingCount(salonId);
                if(existingCountList.size()!=0){
                    if(null!=existingCountList.get(0)){
                        existingCount=(Long)existingCountList.get(0).get("Existing");
                    }

                }
                s.setAwardsNumber(existingCount);
            }
            r.setData(salonList);
            r.setMsg("获取成功");
            r.setMsgcode("0");
            r.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
            r.setMsg("获取失败");
        }
        return r;
    }


    @ResponseBody
    @RequestMapping("queryStuffScoreFroStuff")
    @ApiOperation(value="获取总积分", notes="获取总积分")
    public Result queryStuffScoreFroStuff(Long stuffId){
        Result r= new Result();
        try {
            JSONObject jsonObj=new JSONObject();
            Stuff stuff=stuffDao.getStuffForRecordId(stuffId);

            StuffScore stuffScore=stuffScoreDao.getStuffScore(stuffId);
            if(null==stuffScore){
                jsonObj.put("existing","0");
            }else{
                jsonObj.put("existing",stuffScore.getExisting());
            }
            Salon salon=salonDao.getSalonForId(stuff.getStoreId());

            jsonObj.put("storeId",salon.getSalonName());
            r.setData(jsonObj);
            r.setMsg("添加成功");
            r.setMsgcode("0");
            r.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
            r.setMsg("添加失败");
        }
        return r;
    }



    @ResponseBody
    @RequestMapping("queryStuffScoreRecord")
    @ApiOperation(value="积分列表页", notes="积分列表页")
    public Result queryStuffScoreRecord(Long storeId){
        Result r= new Result();
        try {
            List<StuffScoreRecord> stuffScoreRecordList=stuffScoreRecordDao.queryScoreRecord(storeId);

            for(StuffScoreRecord s  :stuffScoreRecordList){
                List<StuffJob>  stuffJobList =stuffJobDao.getStuffJobListForStuff(s.getStuffId());
                if(stuffJobList.size() != 0 ){
                    for(StuffJob sj:stuffJobList){
                        Job job=jobDao.getJobForId(sj.getJobId());
                        if(s.getJobName()==null) {
                            s.setJobName("");
                            String str=s.getJobName()+job.getJobName();
                            s.setJobName(str);
                        }else{
                            String str=s.getJobName()+","+job.getJobName();
                            s.setJobName(str);
                        }
                    }

                }

                Pictures pic=picturesDao.getOnePicturesForCondition(s.getStuffId(),new Byte("1"),new Byte("0"));
                if(null!=pic){
                    s.setPicUrl(pic.getPicUrl());
                }
            }
            r.setData(stuffScoreRecordList);
            r.setMsg("获取成功");
            r.setMsgcode("0");
            r.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
            r.setMsg("获取失败");
        }
        return r;
    }


    @ResponseBody
    @RequestMapping("queryStuffScoreRecordData")
    @ApiOperation(value="积分列表页", notes="积分列表页")
    public Result queryStuffScoreRecordData(Long stuffId){
        Result r= new Result();
        try {
            JSONObject jsonObj=new JSONObject();
            StuffScore stuffScore=stuffScoreDao.getStuffScore(stuffId);

            List<StuffScoreRecord> stuffScoreRecordList=stuffScoreRecordDao.queryScoreRecord(stuffId);
            jsonObj.put("stuffScore",stuffScore);

            jsonObj.put("stuffScoreRecordList",stuffScoreRecordList);
            r.setData(jsonObj);
            r.setMsg("获取成功");
            r.setMsgcode("0");
            r.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
            r.setMsg("获取失败");
        }
        return r;
    }








}
