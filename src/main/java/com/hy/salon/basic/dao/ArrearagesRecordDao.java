package com.hy.salon.basic.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hy.salon.basic.entity.ArrearagesRecord;
import com.hy.salon.basic.entity.BusinessStuff;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.ExtJsResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("arrearagesRecordDao")
public class ArrearagesRecordDao extends BaseDAOWithEntity<ArrearagesRecord> {
    protected final static String SQL_GET_ARREARSLIST = "com.hy.salon.basic.dao.GET_ARREARSLIST";
    public ExtJsResult getSystemArrearsList(String memberName, Long storeId, HttpServletRequest request) {
        ExtJsResult extJsResult = new ExtJsResult();
        Map parameters = new HashMap();
        parameters.put("storeId",storeId);
        if(StringUtils.isNotEmpty(memberName)){
            parameters.put("memberName",memberName);
        }

        PageHelper.startPage(Integer.parseInt(request.getParameter("page")),2);
        List<Map> listMap = this.getSqlHelper().getSqlSession().selectList(SQL_GET_ARREARSLIST, parameters);
        PageInfo<Map> pageInfo = new PageInfo<>(listMap);
        extJsResult.setSuccess(true);
        extJsResult.setMsg("获取成功");
        extJsResult.setTotal(Integer.parseInt(pageInfo.getTotal()+""));
        extJsResult.setData(pageInfo.getList());
        return extJsResult;

    }
}
