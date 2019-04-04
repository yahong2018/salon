package com.hy.salon.basic.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.entity.OperateLog;
import com.hy.salon.basic.entity.Product;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.util.DateString;
import com.zhxh.admin.dao.RoleUserDAO;
import com.zhxh.admin.dao.SystemRoleDAO;
import com.zhxh.admin.entity.SystemRole;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequest;
import com.zhxh.core.web.ListRequestBaseHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("operateLogDao")
public class OperateLogDao extends BaseDAOWithEntity<OperateLog> {
    @Resource(name="stuffDao")
    private StuffDao stuffDao;
    @Resource(name = "systemRoleDAO")
    private SystemRoleDAO systemRoleDAO;
    public ExtJsResult getScheduleForStoreIdSystem(HttpServletRequest request,ListRequestBaseHandler listRequestBaseHandler) {
        ExtJsResult ejr = new ExtJsResult();
        ListRequest listRequest = getListRequest(request);
        String name = request.getParameter("stuffName");
        String where = "stuff_name like #{stuffName}";
        Map pMap = new HashMap<>();
        pMap.put("stuffName",name);
        Stuff stuff = stuffDao.getOne(where,pMap);
        if(stuff!=null){
            listRequest.setWhere("stuff_id="+ stuff.getRecordId());
        }
        // listRequest.setWhere(listRequest.getWhere()==""?listRequest.getWhere()+" store_id="+recordId:listRequest.getWhere()+" and "+" store_id="+recordId);
        List<OperateLog> listOperateLog =  listRequestBaseHandler.getByRequest(listRequest);
        int count = listRequestBaseHandler.getRequestListCount(listRequest);
        JSONArray jsonArray = new JSONArray();

        for(OperateLog ol:listOperateLog){
            JSONObject jsonObject = new JSONObject();
            Stuff stuff1 = stuffDao.getById(ol.getOptUserId());
            jsonObject.put("optUser",stuff1.getStuffName());
            SystemRole role = systemRoleDAO.getById(ol.getOptRoleId());
            jsonObject.put("optRole",role.getRoleName());
            jsonObject.put("optAction",ol.getOptAction());
            jsonObject.put("optDate", DateString.DateToString2(ol.getOptDate()));
            jsonObject.put("optInfo",ol.getOptInfo());
            jsonObject.put("optUrl",ol.getOptUrl());
            jsonObject.put("optResult",ol.getOptResult());
            jsonArray.add(jsonObject);
        }

        ejr.setTotal(count);
        ejr.setData(jsonArray);
        return  ejr;
    }
}
