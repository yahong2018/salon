package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.MemberProductReject;
import com.hy.salon.basic.entity.MemberProductRejectItem;
import com.hy.salon.basic.entity.Stuff;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("memberProductRejectItemDao")
public class MemberProductRejectItemDao  extends BaseDAOWithEntity<MemberProductRejectItem> {

    public Map<String,Object> getRejectAmount( Long storeId) {
        Map parameters = new HashMap();
        parameters.put("storeId", storeId);
        return this.getSqlHelper().getSqlSession().selectOne(SQL_GET_REJECT_AMOUNT, parameters);
    }


    protected final static String SQL_GET_REJECT_AMOUNT = "com.hy.salon.basic.dao.GET_REJECT_AMOUNT";


}
