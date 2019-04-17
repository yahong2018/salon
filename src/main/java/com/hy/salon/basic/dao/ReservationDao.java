package com.hy.salon.basic.dao;

import com.github.pagehelper.PageHelper;
import com.hy.salon.basic.entity.Job;
import com.hy.salon.basic.entity.Pictures;
import com.hy.salon.basic.entity.Reservation;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.service.JobService;
import com.hy.salon.basic.vo.Result;
import com.hy.salon.basic.vo.StuffVo;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequest;
import com.zhxh.core.web.ListRequestBaseHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("reservationDao")
public class ReservationDao extends BaseDAOWithEntity<Reservation> {
    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    @Resource(name = "picturesDao")
    private PicturesDAO picturesDao;

    @Resource(name = "jobService")
    private JobService jobService;
    public List<Reservation> getReservationById(){
        return this.getSqlHelper().getSqlSession().selectList(GET_RESERVATION_BYID);
    }


    protected final static String GET_RESERVATION_BYID = "com.hy.salon.basic.dao.GET_RESERVATION_BYID";
    public List<StuffVo> getStuffName(Long recordId) {
        Map parameters = new HashMap();
        parameters.put("recordId", recordId);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_STUFF_NAME, parameters);
    }
    protected final static String SQL_GET_STUFF_NAME = "com.hy.salon.basic.dao.GET_STUFF_NAME";

    public List<Map> getServiceByReservationId(Long recordId) {
        Map parameters = new HashMap();
        parameters.put("reservationId", recordId);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_SERVICE_BYRESERVATIONID, parameters);
    }
    protected final static String SQL_GET_SERVICE_BYRESERVATIONID = "com.hy.salon.basic.dao.GET_SERVICE_BYRESERVATIONID";

    public Result getReservationVoList(HttpServletRequest request, Long storeId, String oneDay , String toDays) {
        Map parameters = new HashMap();
        String timeStart = "";
        String timeEnd = "";
        if(StringUtils.isNotEmpty(oneDay)){
            timeStart = oneDay.trim()+" 00:00:00";
            timeEnd = oneDay.trim()+" 23:59:59";
            parameters.put("timeStart", timeStart);
            parameters.put("timeEnd", timeEnd);
        }else{
            if(StringUtils.isNotEmpty(toDays)){
                String days[] =  toDays.split("~");
                timeStart = days[0];
                timeEnd = days[1];
                parameters.put("timeStart", timeStart);
                parameters.put("timeEnd", timeEnd);
            }

        }

        parameters.put("storeId", storeId);

        String  stuffId = request.getParameter("stuffId");
        if(StringUtils.isNotEmpty(stuffId)){
            parameters.put("stuffId", stuffId);
        }
        String  roomId = request.getParameter("roomId");
        if(StringUtils.isNotEmpty(roomId)){
            parameters.put("roomId", roomId);
        }
        String  serviceId = request.getParameter("serviceId");
        if(StringUtils.isNotEmpty(serviceId)){
            parameters.put("serviceId", serviceId);
        }
        int countT = this.getSqlHelper().getSqlSession().selectOne(SQL_GET_STUFF_RESERVATIONCOUNT, parameters);
        PageHelper.startPage(Integer.parseInt(request.getParameter("page")),10);
        List<Map> listMap = this.getSqlHelper().getSqlSession().selectList(SQL_GET_STUFF_RESERVATION, parameters);
        com.hy.salon.basic.vo.Result result=new com.hy.salon.basic.vo.Result();
        result.setData(listMap);
        result.setTotal(countT);
        return result;
    }

    protected final static String SQL_GET_STUFF_RESERVATION= "com.hy.salon.basic.dao.GET_STUFF_RESERVATION";
    protected final static String SQL_GET_STUFF_RESERVATIONCOUNT= "com.hy.salon.basic.dao.GET_STUFF_RESERVATIONCOUNT";

    public ExtJsResult getStuffListStoreIdReservation(HttpServletRequest request, Long recordId, ListRequestBaseHandler listRequestBaseHandler,String timeStart,String timeEnd) {
        ListRequest listRequest = getListRequest(request);
        listRequest.setWhere(listRequest.getWhere()==""?" store_id="+recordId:listRequest.getWhere()+" and store_id="+recordId);
        ExtJsResult ejr  = new ExtJsResult();
        List list2 = listRequestBaseHandler.getByRequest(listRequest);
        List<StuffVo> voList=new ArrayList<>();
        for(Stuff stuff:(List<Stuff>)list2){
            StuffVo vo=new StuffVo();
            vo.setRecordId(stuff.getRecordId());
            vo.setStuffName(stuff.getStuffName());
            //查询员工当天所有预约
            Map parameter = new HashMap();
            parameter.put("stuffId", stuff.getRecordId());
            parameter.put("timeStart",timeStart);
            parameter.put("timeEnd", timeEnd);
            Map rMap = new HashMap();
            String rwhere="stuff_id=#{stuffId} and time_start between #{timeStart} and #{timeEnd}";
            rMap.put("where", rwhere);
            List<Reservation> reservationlist = this.getList(rMap, parameter);
            if(reservationlist!=null){
                vo.setReservation(reservationlist.size());
            }else{
                vo.setReservation(0);
            }
            Map parameterP = new HashMap();
            parameterP.put("masterDataId", stuff.getRecordId());
            parameterP.put("recordType",1);
            parameterP.put("picType",0);
            String rwhereP="master_data_id=#{masterDataId} and record_type = #{recordType} and pic_type=#{picType}";
            Pictures pictures = picturesDao.getOne(rwhereP,parameterP);
            List<Job> listJob = jobService.getJobList(stuff.getRecordId());
            if(listJob!=null){
                String jobName = "";
                for(Job jobs:listJob){
                    jobName =jobName+ jobs.getJobName();
                }
                vo.setJobName(jobName);
            }else{
                vo.setJobName("");
            }
            List<StuffVo> list = this.getStuffName(stuff.getRecordId());
            if(list!=null){
                for (StuffVo stuffVo : list) {
                    vo.setRole(stuffVo.getRole());
                }
            }
            vo.setPicUrl(pictures==null?"":pictures.getPicUrl());
            voList.add(vo);
        }
        int listCount = listRequestBaseHandler.getRequestListCount(listRequest);
        ejr.setData(voList);
        ejr.setTotal(listCount);
        ejr.setMsgcode("0");
        ejr.setSuccess(true);
        ejr.setMsg("获取成功");
        return  ejr;
    }
}
