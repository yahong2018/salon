package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.dao.StuffIntegralDao;
import com.hy.salon.basic.dao.StuffIntegralRecordDao;
import com.hy.salon.basic.entity.StuffIntegral;
import com.hy.salon.basic.entity.StuffIntegralRecord;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component("stuffIntegralRecordService")
public class StuffIntegralRecordService {
    @Resource(name = "stuffIntegralRecordDao")
    private StuffIntegralRecordDao stuffIntegralRecordDao;
    @Resource(name = "stuffIntegralDao")
    private StuffIntegralDao stuffIntegralDao;

    public void addIntegralc(StuffIntegralRecord stuffIntegralRecord) {
        stuffIntegralRecordDao.insert(stuffIntegralRecord);
        //查询员工是否有积分
        String where = "stuff_id=#{stuffId}";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("stuffId", stuffIntegralRecord.getStuffId());
        StuffIntegral one = stuffIntegralDao.getOne(where, parameters);
        if(one==null){
            StuffIntegral stuffIntegral=new StuffIntegral();
            stuffIntegral.setStuffId(stuffIntegralRecord.getStuffId());
            stuffIntegral.setExisting(stuffIntegralRecord.getGetPoint());
            stuffIntegralDao.insert(stuffIntegral);
        }else{
            one.setExisting(one.getExisting()+stuffIntegralRecord.getGetPoint());
            stuffIntegralDao.update(one);
        }
    }
}
