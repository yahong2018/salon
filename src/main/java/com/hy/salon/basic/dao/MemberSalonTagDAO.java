package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.MemberSalonTag;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.entity.Tag;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("memberSalonTagDAO")
public class MemberSalonTagDAO  extends BaseDAOWithEntity<MemberSalonTag> {


    public List<Tag> getTag(Long salonId) {
        Map parameters = new HashMap();
        parameters.put("salonId", salonId);
        return this.getSqlHelper().getSqlSession().selectList(SQL_QUERY_TAG_FOR_SALON, parameters);
    }

    public List<MemberSalonTag> getMemberTag(Long tagId){
        String where="tag_id=#{tagId}";
        Map parameters = new HashMap();
        parameters.put("tagId", tagId);

        return this.getByWhere(where,parameters);
    }




    protected final static String SQL_QUERY_TAG_FOR_SALON = "com.hy.salon.basic.dao.QUERY_TAG_FOR_SALON";

}
