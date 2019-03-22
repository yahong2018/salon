package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Pictures;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component("picturesDao")
public class PicturesDAO extends BaseDAOWithEntity<Pictures> {

    public Pictures getOnePicturesForId(String filename){
        String where = "pic_url like #{picName}";
        Map parameters = new HashMap();

        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                filename= filename.substring(0, dot);
            }
        }
        filename="%"+filename+"%";
        parameters.put("picName", filename);

        return this.getOne(where, parameters);
    }


    public List<Pictures> getPicturesForId(Long id){
        String where = "master_data_id=#{id} and record_type = 5";
        Map parameters = new HashMap();
        parameters.put("id", id);

        return this.getByWhere(where, parameters);
    }


    public Pictures getPicForRecordId(Long id){
        String where = "record_id=#{id} ";
        Map parameters = new HashMap();
        parameters.put("id", id);

        return this.getOne(where, parameters);
    }

    public List<Pictures> getPicturesForCondition(Long id,Byte recordType,Byte picType){
        String where = "master_data_id=#{id} and record_type = #{recordType} and pic_type= #{picType}";
        Map parameters = new HashMap();
        parameters.put("id", id);
        parameters.put("recordType", recordType);
        parameters.put("picType", picType);
        return this.getByWhere(where, parameters);
    }


    public Pictures getOnePicturesForCondition(Long id,Byte recordType,Byte picType){
        String where = "master_data_id=#{id} and record_type = #{recordType} and pic_type= #{picType}";
        Map parameters = new HashMap();
        parameters.put("id", id);
        parameters.put("recordType", recordType);
        parameters.put("picType", picType);
        return this.getOne(where, parameters);
    }

    public Pictures getOnePicturesForIdCondition(Long id,Byte recordType,Byte picType){
        String where = "record_id=#{id} and record_type = #{recordType} and pic_type= #{picType}";
        Map parameters = new HashMap();
        parameters.put("id", id);
        parameters.put("recordType", recordType);
        parameters.put("picType", picType);
        return this.getOne(where, parameters);
    }



}
