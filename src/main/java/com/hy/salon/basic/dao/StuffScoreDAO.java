package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.entity.StuffScore;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("stuffScoreDao")
public class StuffScoreDAO extends BaseDAOWithEntity<StuffScore> {
    public StuffScore getStuffScore(Long stuffId){
        String where="stuff_id=#{stuffId}";
        Map parameters = new HashMap();
        parameters.put("stuffId", stuffId);
        return this.getOne(where,parameters);
    }
}
