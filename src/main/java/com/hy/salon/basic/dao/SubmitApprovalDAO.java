package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.MemberTag;
import com.hy.salon.basic.entity.SubmitApproval;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("submitApprovalDAO")
public class SubmitApprovalDAO extends BaseDAOWithEntity<SubmitApproval> {




    public List<Map<String,Object>> getSubmitApprovalForStuff(Long stuffId) {
        Map parameters = new HashMap();
        parameters.put("stuffId", stuffId);
        List<Map<String,Object>> mapList = this.getSqlHelper().getSqlSession().selectList(SQL_QUERY_SUBMIT_APPROVAL, parameters);
        return mapList;
    }



    protected final static String SQL_QUERY_SUBMIT_APPROVAL = "com.hy.salon.basic.dao.QUERY_SUBMIT_APPROVAL";

}
