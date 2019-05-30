package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.ApprovalProcess;
import com.hy.salon.basic.entity.ApprovalRecord;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("approvalRecordDAO")
public class ApprovalRecordDAO extends BaseDAOWithEntity<ApprovalRecord> {





    public List<Map<String,Object>> getSubmitApprovalForStuff(Long stuffId,String approvalStatus) {
        Map parameters = new HashMap();
        parameters.put("stuffId", stuffId);
        parameters.put("approvalStatus", approvalStatus);

        List<Map<String,Object>> mapList = this.getSqlHelper().getSqlSession().selectList(SQL_QUERY_APPROVAL_RECORD, parameters);
        return mapList;
    }


    public ApprovalRecord getApprovalRecordForId(Long submitApprovalId,Integer severalApprovals){
        String where = "submit_approval_id=#{submitApprovalId} and several_approvals=#{severalApprovals}";
        Map parameters = new HashMap();
        parameters.put("submitApprovalId", submitApprovalId);
        parameters.put("severalApprovals", severalApprovals);

        return this.getOne(where, parameters);
    }

    public List<ApprovalRecord> getApprovalRecordForId2(Long submitApprovalId ,Integer isLast){
        String where = "submit_approval_id=#{submitApprovalId} ";
        if(null!=isLast){
            where=where+" and  is_last = 0";
        }
        Map parameters = new HashMap();
        parameters.put("submitApprovalId", submitApprovalId);

        return this.getByWhere(where, parameters);
    }

    public ApprovalRecord getOneApprovalRecordForId(Long submitApprovalId){
        String where = "submit_approval_id=#{submitApprovalId} and  is_last = 0";
        Map parameters = new HashMap();
        parameters.put("submitApprovalId", submitApprovalId);

        return this.getOne(where, parameters);
    }






    protected final static String SQL_QUERY_APPROVAL_RECORD = "com.hy.salon.basic.dao.QUERY_APPROVAL_RECORD";
}
