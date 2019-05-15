package com.hy.salon.basic.service;


import com.hy.salon.basic.dao.MemberGiftDao;
import com.zhxh.core.web.ExtJsResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component("memberGiftService")
public class MemberGiftService {
    @Resource(name="memberGiftDao")
    private MemberGiftDao memberGiftDao;
    public ExtJsResult getSystemMemberGiftList(String memberName, Long recordId,  Long refTransId,HttpServletRequest request,String role,String toDays) {
       return memberGiftDao.getSystemMemberGiftList(memberName,recordId,refTransId,request,role,toDays);
    }
}
