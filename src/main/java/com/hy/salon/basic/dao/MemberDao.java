package com.hy.salon.basic.dao;

import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.entity.Member;
import com.hy.salon.basic.entity.MemberSalonTag;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("memberDao")
public class MemberDao extends BaseDAOWithEntity<Member> {


    public List<Member> getMember(Long salonId,String filterExpr){
        String startTime="";
        String endTime="";
//        String ="";
        if(filterExpr!=null){
        JSONObject json=JSONObject.parseObject(filterExpr);
            String birthday=json.getString("birthday");
            if(!"".equals(birthday) && !"1".equals(birthday)){

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                //获取当前月第一天：
                Calendar c = Calendar.getInstance();
                c.add(Calendar.MONTH, 0);
                c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
                String first = format.format(c.getTime());
                startTime=first;

                //获取当前月最后一天
                Calendar ca = Calendar.getInstance();
                ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
                String last = format.format(ca.getTime());
                endTime=last;
            }
            String earlyWarning=json.getString("earlyWarning");
            if(!"".equals(earlyWarning)){


            }
            String tag=json.getString("tag");
            String memberGradeId=json.getString("memberGradeId");
        }

        JSONObject jsonObj=JSONObject.parseObject(filterExpr);

        String where="initial_store_id=#{salonId}";
        Map parameters = new HashMap();
        parameters.put("salonId", salonId);

        return this.getByWhere(where,parameters);
    }

}
