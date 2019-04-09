package com.zhxh.admin.dao;

import com.zhxh.admin.entity.SystemRole;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("systemRoleDAO")
public class SystemRoleDAO extends BaseDAOWithEntity<SystemRole> {

    public SystemRole getRole(String  roleCode) {
        Map parameters = new HashMap();
        parameters.put("roleCode", roleCode);

        String where = "role_code=#{roleCode}";

        return this.getOne(where, parameters);
    }

    public SystemRole getRoleForId(String  recordCode) {
        Map parameters = new HashMap();
        parameters.put("recordCode", recordCode);

        String where = "record_id=#{recordCode}";

        return this.getOne(where, parameters);
    }


}
