package com.hy.salon.basic.service;


import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.dao.ActivityDetailInfoDao;
import com.hy.salon.basic.dao.MemberDao;
import com.hy.salon.basic.entity.ActivityDetailInfo;
import com.hy.salon.basic.entity.Member;
import com.zhxh.core.web.ExtJsResult;
import org.springframework.stereotype.Component;
import springfox.documentation.annotations.Cacheable;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("activityDetailInfoService")
public class ActivityDetailInfoService {

    @Resource(name="activityDetailInfoDao")
    private ActivityDetailInfoDao activityDetailInfoDao;

    @Resource(name="memberDao")
    private MemberDao memberDao;


    public ExtJsResult getRewardListByActivityId(long activityId,String openId) {
        ExtJsResult  extJsResult = new ExtJsResult();
        String where="activity_info_id=#{activityId} and totle_recommender = #{totleRecommender}";
        Map parameters = new HashMap();
        parameters.put("activityId", activityId);
        parameters.put("totleRecommender",1);

        List<Map> listMap = activityDetailInfoDao.getSqlHelper().getSqlSession().selectList(GET_REWARDLISTACTIVITY,parameters);//获取排行榜信息

        String whereM="open_id=#{openId}";
        Map parametersM = new HashMap();
        parameters.put("openId", openId);
        Member member = memberDao.getOne(whereM,parametersM);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("numberNum",0);
        jsonObject.put("reward",0.00);
        if(member!=null){
            where = where +" and member_id = #{member_id}";
            parameters.put("member_id",member.getRecordId());
            ActivityDetailInfo activityDetailInfo = activityDetailInfoDao.getOne(where,parameters);
            jsonObject.put("numberNum",activityDetailInfo==null?"":activityDetailInfo.getTotleRecommender());
            jsonObject.put("reward",activityDetailInfo==null?"":activityDetailInfo.getTotleEarnings());
        }

        extJsResult.setData(jsonObject);
        extJsResult.setListMap(listMap);

        return extJsResult;
    }
    protected final static String GET_REWARDLISTACTIVITY = "com.hy.salon.basic.dao.GET_REWARDLISTACTIVITY";

    public ExtJsResult getParticipantList(long activityId) {
        ExtJsResult  extJsResult = new ExtJsResult();
        String where="activity_info_id=#{activityId}";
        Map parameters = new HashMap();
        parameters.put("activityId", activityId);

        List<Map> listMap = activityDetailInfoDao.getSqlHelper().getSqlSession().selectList(GET_REWARDLISTACTIVITY,parameters);//获取排行榜信息

        extJsResult.setListMap(listMap);
        return  extJsResult;
    }
}
