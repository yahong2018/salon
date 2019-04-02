package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.SalonInviteCode;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("salonInviteCodeDAO")
public class SalonInviteCodeDAO extends BaseDAOWithEntity<SalonInviteCode> {

    public SalonInviteCode getSalonForCode(String inviteCode){
        String where="invite_code=#{inviteCode}";
        Map parameters = new HashMap();
        parameters.put("inviteCode", inviteCode);
        return this.getOne(where,parameters);
    }

    public SalonInviteCode getSalonForSalonId(Long salonId){
        String where="salon_id=#{salonId}";
        Map parameters = new HashMap();
        parameters.put("salonId", salonId);
        return this.getOne(where,parameters);
    }

}
