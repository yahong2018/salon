package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.MemberSalonTag;
import com.hy.salon.basic.entity.VerificationCodeTemporary;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("verificationCodeTemporaryDao")
public class VerificationCodeTemporaryDAO extends BaseDAOWithEntity<VerificationCodeTemporary> {



    public List<VerificationCodeTemporary> getCode(String tel){
        String where="phone_no=#{tel}";
        Map parameters = new HashMap();
        parameters.put("tel", tel);

        return this.getByWhere(where,parameters);
    }
}
