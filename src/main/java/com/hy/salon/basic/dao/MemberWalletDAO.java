package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.MemberWallet;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("memberWalletDAO")
public class MemberWalletDAO extends BaseDAOWithEntity<MemberWallet> {

    public MemberWallet getMemberWalletForMemberId(Long memberId){
        String where="member_id = #{memberId}";
        Map parameters = new HashMap();
        parameters.put("memberId", memberId);

        return this.getOne(where,parameters);
    }

}
