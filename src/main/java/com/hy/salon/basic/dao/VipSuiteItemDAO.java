package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.ServiceSuiteItem;
import com.hy.salon.basic.entity.VipSuiteItem;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("vipSuiteItemDao")
public class VipSuiteItemDAO extends BaseDAOWithEntity<VipSuiteItem> {


    public List<VipSuiteItem> queryVipSuitForId(Long Id){
        String where="vip_suite_id = #{Id}";
        Map parameters = new HashMap();
        parameters.put("Id", Id);
        return this.getByWhere(where,parameters);

    }


}
