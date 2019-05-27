package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.RoleAction;
import com.hy.salon.basic.entity.Salon;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("roleActionDao")
public class RoleActionDAO extends BaseDAOWithEntity<RoleAction> {

    public RoleAction getRoleActionByRecordId(Long recordId){
        String where = "system_user_id=#{recordId}";
        Map parameters = new HashMap();
        parameters.put("recordId",recordId);
        return this.getOne(where,parameters);
    }

}
