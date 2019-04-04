package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.MemberTag;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("memberTagDao")
public class MemberTagDao extends BaseDAOWithEntity<MemberTag> {


    public MemberTag getMemberTag(Long memberId){
        String where="member_id=#{memberId}";
        Map parameters = new HashMap();
        parameters.put("memberId", memberId);

        return this.getOne(where,parameters);
    }

}
