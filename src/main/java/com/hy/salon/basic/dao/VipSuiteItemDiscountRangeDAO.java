package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.VipSuiteItemDiscountRange;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("vipSuiteItemDiscountRangeDAO")
public class VipSuiteItemDiscountRangeDAO extends BaseDAOWithEntity<VipSuiteItemDiscountRange> {

    public VipSuiteItemDiscountRange queryOneRange(Long recordId){
        String where="vip_suite_item_id=#{recordId}";
        Map parameters = new HashMap();
        parameters.put("recordId", recordId);
        return this.getOne(where,parameters);

    }


}
