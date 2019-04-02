package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Job;
import com.hy.salon.basic.entity.Pictures;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.entity.StuffJob;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequest;
import com.zhxh.core.web.ListRequestBaseHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("stuffDao")
public class StuffDao extends BaseDAOWithEntity<Stuff>{
    @Resource(name = "jobDAO")
    private JobDAO jobDao;

    @Resource(name = "picturesDao")
    private PicturesDAO picturesDao;
    @Resource(name = "stuffJobDao")
    private StuffJobDao stuffJobDao;
//    public List<Stuff> getStuffForStoreId(Long storeId){
//        Map parameters = new HashMap();
//        parameters.put("storeId", storeId);
//
//        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_STUFF_FOR_STOREID, parameters);
//    }

    public List<Stuff> getStuffForStoreId(Long storeId){
        String where="store_id=#{storeId}";
        Map parameters = new HashMap();
        parameters.put("storeId", storeId);

        return this.getByWhere(where,parameters);
    }


    public Stuff getStuffForUser(Long userId) {
        Map parameters = new HashMap();
        parameters.put("userId", userId);
        return this.getSqlHelper().getSqlSession().selectOne(SQL_GET_STUFF_BY_USER, parameters);
    }


    public Map fuzzyQueryStuff(String  stuffName,Long salonId) {
        Map parameters = new HashMap();
        parameters.put("stuffName", stuffName);
        parameters.put("salonId", salonId);
        return this.getSqlHelper().getSqlSession().selectOne(SQL_FUZZY_QUERY_STUFF, parameters);
    }

    public List<Map> getJobLevelByStuffId(Long recordId) {
        Map parameters = new HashMap();
        parameters.put("stuffId", recordId);
        List<Map> mapList = this.getSqlHelper().getSqlSession().selectList(GET_STUFF_JOB_BY_STUFF_ID, parameters);
        return mapList;
    }

    protected final static String SQL_GET_STUFF_BY_USER = "com.hy.salon.basic.dao.GET_STUFF_BY_USER";

    protected final static String SQL_FUZZY_QUERY_STUFF="com.hy.salon.basic.dao.FUZZY_QUERY_STUFF";



    protected final static String GET_STUFF_JOB_BY_STUFF_ID="com.hy.salon.basic.dao.GET_STUFF_JOB_BY_STUFF_ID";

    public ExtJsResult getStuffListStoreIdSystem(HttpServletRequest request, long storeId, ListRequestBaseHandler listRequestBaseHandler) {
        ListRequest listRequest = getListRequest(request);
        listRequest.setWhere(listRequest.getWhere()==""?" store_id="+storeId:listRequest.getWhere()+" store_id="+storeId);
        ExtJsResult ejr  = new ExtJsResult();
        List list2 = listRequestBaseHandler.getByRequest(listRequest);

        for(Stuff ss:(List<Stuff>)list2){
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

        int listCount = listRequestBaseHandler.getRequestListCount(listRequest);
        ejr.setData(list2);
        ejr.setTotal(listCount);
        return ejr;
    }
}
