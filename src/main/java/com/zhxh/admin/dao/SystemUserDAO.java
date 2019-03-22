package com.zhxh.admin.dao;

import com.zhxh.admin.entity.SystemUser;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("systemUserDAO")
public class SystemUserDAO extends BaseDAOWithEntity<SystemUser> {
    public SystemUser getUserByCode(String userCode){
        String where = "user_code=#{userCode}";
        Map<String,String> parameters = new HashMap<>();
        parameters.put("userCode",userCode);

        return this.getOne(where,parameters);
    }




}
