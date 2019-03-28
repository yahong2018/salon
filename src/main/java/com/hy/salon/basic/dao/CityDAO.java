package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.City;
import com.hy.salon.basic.entity.Pictures;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("cityDAO")
public class CityDAO extends BaseDAOWithEntity<City> {


    public List<City> getCity(String parentId){
        String where = "parent_id=#{parentId} and city_code != 0";
        Map parameters = new HashMap();
        parameters.put("parentId", parentId);

        return this.getByWhere(where, parameters);
    }
}
