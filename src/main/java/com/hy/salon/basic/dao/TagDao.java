package com.hy.salon.basic.dao;


import com.hy.salon.basic.entity.Salon;
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

}
