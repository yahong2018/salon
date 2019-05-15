package com.hy.salon.basic.dao;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.entity.MemberProductGetRecord;
import com.hy.salon.basic.entity.MemberProductKeep;
import com.hy.salon.basic.util.DateString;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.ExtJsResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("memberProductGetRecordDao")
public class MemberProductGetRecordDao extends BaseDAOWithEntity<MemberProductGetRecord> {


    protected final static String SQL_GET_PRODUCT_RECORD = "com.hy.salon.basic.dao.GET_PRODUCT_RECORD";
    public ExtJsResult getProductList(Long storeId, HttpServletRequest request, String toDays, String role) {
        ExtJsResult extJsResult = new ExtJsResult();
        Map parameters = new HashMap();
        parameters.put("storeId",storeId);
        parameters.put("role",role);

        if(StringUtils.isNotEmpty(toDays)){
            String days[] =  toDays.split("~");
            String timeStart = days[0];
            String  timeEnd = days[1];
            parameters.put("timeStart", timeStart);
            parameters.put("timeEnd", timeEnd);
        }
        List<Map<String,Object>> listMap1 = this.getSqlHelper().getSqlSession().selectList(SQL_GET_PRODUCT_RECORD, parameters);
        extJsResult.setTotal(listMap1.size());
        PageHelper.startPage(Integer.parseInt(request.getParameter("page")),10);
        List<Map<String,Object>> listMap = this.getSqlHelper().getSqlSession().selectList(SQL_GET_PRODUCT_RECORD, parameters);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(listMap);
        extJsResult.setSuccess(true);
        extJsResult.setMsg("获取成功");

        JSONArray jsonArray = new JSONArray();


        extJsResult.setData(listMap);
        extJsResult.setMsgcode(StatusUtil.OK);
        return extJsResult;

    }


}
