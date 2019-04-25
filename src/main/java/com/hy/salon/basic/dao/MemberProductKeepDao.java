package com.hy.salon.basic.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hy.salon.basic.entity.MemberProductKeep;
import com.hy.salon.basic.entity.MemberSalonTag;
import com.hy.salon.basic.vo.Result;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component("memberProductKeepDao")
public class MemberProductKeepDao  extends BaseDAOWithEntity<MemberProductKeep> {
    protected final static String SQL_GET_MEMBERPRODUCTKEEP = "com.hy.salon.basic.dao.GET_MEMBERPRODUCTKEEP";

    public Result getMemberProductKeepList(Long memberId) {
        Result result = new Result();
        PageHelper.startPage(Integer.parseInt("1"),2);
        Map parameters = new HashMap();
        if(StringUtils.isNotEmpty(memberId+"")){
            parameters.put("memberId",memberId);
        }
        List<Map> listMap = this.getSqlHelper().getSqlSession().selectList(SQL_GET_MEMBERPRODUCTKEEP, parameters);
        PageInfo<Map> pageInfo = new PageInfo<>(listMap);

        result.setMsg("获取成功");
        result.setMsgcode("0");
        result.setSuccess(true);
        result.setData(listMap);
        result.setTotal(Integer.parseInt(pageInfo.getTotal()+""));
        return  result;
    }
}
