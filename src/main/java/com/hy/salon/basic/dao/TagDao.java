package com.hy.salon.basic.dao;


import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.entity.Tag;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("tagDao")
public class TagDao extends BaseDAOWithEntity<Tag> {


//    public List<TagDao> getSalonForStoreId2(Long parentId){
//        String where="parent_id=#{parentId}";
//        Map parameters = new HashMap();
//        parameters.put("parentId", parentId);
//        return this.getByWhere(where,parameters);
//    }

    public List<Map<String,Object>> getMemberTag(Long recordId) {
        Map parameters = new HashMap();
        parameters.put("recordId", recordId);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_MEMBER_TAG, parameters);
    }



    protected final static String SQL_GET_MEMBER_TAG = "com.hy.salon.basic.dao.GET_MEMBER_TAG";


}
