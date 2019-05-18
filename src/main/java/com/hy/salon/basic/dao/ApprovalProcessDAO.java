package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.ApprovalProcess;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("approvalProcessDAO")
public class ApprovalProcessDAO extends BaseDAOWithEntity<ApprovalProcess> {


    public ApprovalProcess getApprovalProcessForId(Long storeId,Long billTypeId){
        String where = "store_id=#{storeId} and bill_type_id=#{billTypeId} ";
        Map parameters = new HashMap();
        parameters.put("storeId", storeId);
        parameters.put("billTypeId", billTypeId);

        return this.getOne(where, parameters);
    }


}
