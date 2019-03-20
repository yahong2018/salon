package com.hy.salon.basic.service;

import com.github.pagehelper.PageHelper;
import com.hy.salon.basic.dao.MemberDao;
import com.hy.salon.basic.dao.SalonDao;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.Member;
import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.entity.Stuff;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("memberService")
@Transactional(rollbackFor = Exception.class)
public class MemberService {

    @Resource(name = "memberDao")
    private MemberDao memberDao;

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    @Resource(name = "salonDao")
    private SalonDao salonDao;

    public List<Member> getArchives(Long initialStoreId) {
        List<Member> list=new ArrayList<>();
        if(initialStoreId==0){
            list = addAttribute(memberDao.getAll());
        }else{
            Map parameters = new HashMap();
            parameters.put("initialStoreId", initialStoreId);
            Map listMap = new HashMap();
            String where = "initial_store_id=#{initialStoreId}";
            listMap.put("where", where);
            list = addAttribute(memberDao.getList(listMap, parameters));
        }
        return list;
    }
    public List<Member> getArchivespc(Long storeId,String pageNum,String pageSize) {
        List<Salon> list=new ArrayList<>();
        Salon salon = salonDao.getSalonForId(storeId);
        if(salon.getParentId()<0){
            Map Map = new HashMap();
            String where = "parent_id=#{parentId}";
            Map.put("where",where);
            Map parameters = new HashMap();
            parameters.put("parentId", salon.getRecordId());
            List<Salon> salonList = salonDao.getList(Map, parameters);
            salonList.add(salon);
            list.addAll(salonList);
        }
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<Member> vo=new ArrayList<>();
        for (Salon salon1 : list) {
            Map parameters = new HashMap();
            parameters.put("initialStoreId", salon1.getRecordId());
            Map listMap = new HashMap();
            String where = "initial_store_id=#{initialStoreId}";
            listMap.put("where", where);
            vo = addAttribute(memberDao.getList(listMap, parameters));
        }
        return vo;
    }
    private List<Member> addAttribute(List<Member> list){
        for (Member member : list) {
            String where = "record_id=#{recordId}";
            Map parameters = new HashMap();
            parameters.put("recordId", member.getPrimaryBeautician());
            Stuff stuff = stuffDao.getOne(where,parameters);
            member.setPrimaryName(stuff.getStuffName());
        }
        return list;
    }

    public Member getCustomerFiles(Long recordId) {
        String where = "record_id=#{recordId}";
        Map parameters = new HashMap();
        parameters.put("recordId", recordId);
        Member member = memberDao.getOne(where,parameters);
        Map stuffParameters = new HashMap();
        stuffParameters.put("recordId", member.getPrimaryBeautician());
        Stuff stuff = stuffDao.getOne(where,stuffParameters);
        Map salonParameters = new HashMap();
        salonParameters.put("recordId", member.getInitialStoreId());
        Salon salon = salonDao.getOne(where,salonParameters);
        member.setPrimaryName(stuff.getStuffName());
        member.setSalonName(salon.getSalonName());
        return member;
    }

    public void addMember(Member member) {
        //设置会员默认值
        member.setMemberGradeId(3L);
        member.setBalance(0.0);
        member.setIntegral(0.0);
        member.setDebt(0.0);
        member.setAmountConsumer(0.0);
        member.setAmountCharge(0.0);
        memberDao.insert(member);
    }
}
