package com.hy.salon.basic.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hy.salon.basic.entity.CardBalance;
import com.hy.salon.basic.entity.CardPurchase;
import com.hy.salon.basic.entity.Service;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequest;
import com.zhxh.core.web.ListRequestBaseHandler;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("cardPurchaseDao")
public class CardPurchaseDao extends BaseDAOWithEntity<CardPurchase> {
    protected final static String SQL_GET_RECHARGE = "com.hy.salon.basic.dao.GET_RECHARGE";
    public ExtJsResult getSystemRechargeList(String memberName, long storeId, HttpServletRequest request) {
        ExtJsResult extJsResult = new ExtJsResult();
            Map parameters = new HashMap();
            parameters.put("storeId",storeId);
            if(StringUtils.isNotEmpty(memberName)){
                parameters.put("memberName",memberName);
            }

            PageHelper.startPage(Integer.parseInt(request.getParameter("page")),2);
            List<Map> listMap = this.getSqlHelper().getSqlSession().selectList(SQL_GET_RECHARGE, parameters);
            PageInfo<Map> pageInfo = new PageInfo<>(listMap);
            extJsResult.setSuccess(true);
            extJsResult.setMsg("获取成功");
            extJsResult.setTotal(Integer.parseInt(pageInfo.getTotal()+""));
            extJsResult.setData(pageInfo.getList());
            return extJsResult;
        }
}
