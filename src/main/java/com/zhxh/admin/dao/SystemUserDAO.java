package com.zhxh.admin.dao;

import com.zhxh.admin.entity.SystemUser;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("systemUserDAO")
public class SystemUserDAO extends BaseDAOWithEntity<SystemUser> {
    public SystemUser getUserByCode(String userCode){
        String where = "user_code=#{userCode}";
        Map<String,String> parameters = new HashMap<>();
        parameters.put("userCode",userCode);

        return this.getOne(where,parameters);
    }

    public SystemUser getUserByRecordId(Long recordId){
        String where = "record_id=#{recordId}";
        Map parameters = new HashMap();
        parameters.put("recordId",recordId);
        return this.getOne(where,parameters);
    }

    protected final static String SQL_GET_SYSTEM_USER_BY_TEL = "com.zhxh.admin.dao.GET_SYSTEM_USER_BY_TEL";
    public SystemUser getUserByTel(String tel) {
        Map parameters = new HashMap();
        parameters.put("tel", tel);
        List<SystemUser> list = this.getSqlHelper().getSqlSession().selectList(SQL_GET_SYSTEM_USER_BY_TEL, parameters);
       return list.get(0);
    }
}
