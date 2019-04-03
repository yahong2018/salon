package com.hy.salon.basic.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.entity.Retroactive;
import com.hy.salon.basic.entity.Schedule;
import com.hy.salon.basic.entity.Stuff;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequest;
import com.zhxh.core.web.ListRequestBaseHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component("retroactiveDao")
public class RetroactiveDao extends BaseDAOWithEntity<Retroactive> {
    @Resource(name = "stuffDao")
    private StuffDao stuffDao;
    public ExtJsResult getPatchCardListByStoreId(HttpServletRequest request, Long recordId, ListRequestBaseHandler listRequestBaseHandler) {
        ListRequest listRequest = getListRequest(request);
        listRequest.setWhere(listRequest.getWhere()==""?" salon_id="+recordId:listRequest.getWhere()+" salon_id="+recordId);
        ExtJsResult ejr  = new ExtJsResult();
        List list2 = listRequestBaseHandler.getByRequest(listRequest);
        JSONArray jsonArray  = new JSONArray();
        for(Retroactive retroactive:(List<Retroactive>)list2){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("recordId",retroactive.getRecordId());
            Stuff stuff = stuffDao.getById(retroactive.getAuditPerson());
            jsonObject.put("auditPerson",stuff.getStuffName());
            jsonObject.put("reson",retroactive.getReson());
            jsonObject.put("retroactiveType",retroactive.getRetroactiveType()==1?"上班":"下班");
            jsonObject.put("auditStatu",retroactive.getAuditStatu()==0?"未通过":"通过");
            jsonObject.put("auditOpinion",retroactive.getAuditOpinion());
            jsonArray.add(jsonObject);
        }
        int count =  listRequestBaseHandler.getRequestListCount(listRequest);
        ejr.setData(jsonArray);
        ejr.setTotal(count);
        return  ejr;
    }
}
