package com.hy.salon.basic.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hy.salon.basic.entity.MemberProductKeep;
import com.hy.salon.basic.entity.MemberSalonTag;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.vo.Result;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component("memberProductKeepDao")
public class MemberProductKeepDao  extends BaseDAOWithEntity<MemberProductKeep> {

    @Resource(name="stuffDao")
    private StuffDao stuffDao;

    @Resource(name="memberDao")
    private MemberDao memberDao;


    protected final static String SQL_GET_MEMBERPRODUCTKEEP = "com.hy.salon.basic.dao.GET_MEMBERPRODUCTKEEP";

    protected final static String SQL_GET_STORWMEMBERPRODUCTKEEP = "com.hy.salon.basic.dao.GET_STORWMEMBERPRODUCTKEEP";


    public Result getStoreMemberProductKeepList(Long storeId,Long memberId,String toDays) {
       /* Map parametersS = new HashMap();
        parametersS.put("storeId", storeId);
        Map listMapS = new HashMap();
        String where="store_id=#{storeId}";
        listMapS.put("where", where);
        List<Stuff> stufflist = stuffDao.getList(listMapS, parametersS);
        for(Stuff stuff:stufflist){

        }*/
        Result result = new Result();
        PageHelper.startPage(Integer.parseInt("1"),2);
        Map parameters = new HashMap();
        if(StringUtils.isNotEmpty(memberId+"")){
            parameters.put("memberId",memberId);
        }
        parameters.put("storeId",storeId);
        if(StringUtils.isNotEmpty(toDays)){
            String days[] =  toDays.split("~");
            String timeStart = days[0];
            String  timeEnd = days[1];
            parameters.put("timeStart", timeStart);
            parameters.put("timeEnd", timeEnd);
        }
        List<Map> listMap = this.getSqlHelper().getSqlSession().selectList(SQL_GET_STORWMEMBERPRODUCTKEEP, parameters);
        PageInfo<Map> pageInfo = new PageInfo<>(listMap);

        result.setMsg("获取成功");
        result.setMsgcode("0");
        result.setSuccess(true);
        result.setData(listMap);
        result.setTotal(Integer.parseInt(pageInfo.getTotal()+""));
        return  result;
    }

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
