package com.hy.salon.basic.dao;

import com.github.pagehelper.PageHelper;
import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.entity.SalonShift;
import com.hy.salon.basic.entity.Shift;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequest;
import com.zhxh.core.web.ListRequestBaseHandler;
import com.zhxh.core.web.PageListRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("salonDao")
public class SalonDao extends BaseDAOWithEntity<Salon> {
    @Resource(name = "shiftDao")
    private ShiftDao shiftDao;

    public List<Salon> getSalon(){
        return this.getAll();
    }

    public Salon getSalonForId(Long id){
        String where = "record_id=#{id}";
        Map parameters = new HashMap();
        parameters.put("id", id);

        return this.getOne(where, parameters);
    }

    public Salon getSalonForStoreId(Long id){
        String where = "record_id=#{id}";
        Map parameters = new HashMap();
        parameters.put("id", id);

        return this.getOne(where, parameters);
    }




    public List<Salon> getSalonForStoreId2(Long parentId){
        String where="parent_id=#{parentId}";
        Map parameters = new HashMap();
        parameters.put("parentId", parentId);
        return this.getByWhere(where,parameters);
    }


    public List<Salon> getSalonForCreateId(Long createId){
        String where="create_by=#{createId} and parent_id != -1";
        Map parameters = new HashMap();
        parameters.put("createId", createId);
        return this.getByWhere(where,parameters);
    }

    public ListRequest getListRequest(HttpServletRequest request) {
        ListRequest listRequest;
        if(StringUtils.isNotEmpty(request.getParameter("page"))){
            listRequest = new PageListRequest();
        }else{
            listRequest = new ListRequest();
        }
        listRequest.fromServletRequest(request);
        return listRequest;
    }
    public ExtJsResult getSalonForCreateIdSystem(HttpServletRequest request,Long storeId, ListRequestBaseHandler listHandler) {
//        String where="parent_id=#{parentId}";
//        Map parameters = new HashMap();
//        parameters.put("parentId", storeId);
//        int count = this.getPageListCount(parameters);
        //PageHelper.startPage(page, 10);
        //List<Salon> list = this.getByWhere(where,parameters);
        ListRequest listRequest = getListRequest(request);
        listRequest.setWhere(listRequest.getWhere()==""?" parent_id="+storeId:listRequest.getWhere()+" and  parent_id="+storeId);
        List list2 = listHandler.getByRequest(listRequest);
        int listCount = listHandler.getRequestListCount(listRequest);
        ExtJsResult ejr  = new ExtJsResult();
        List<SalonShift> listSalonShift = new ArrayList<>();
        for(Object salonObject:list2){
            String where2="store_id=#{storeId} order by shift_type";
            Map salonMap = new HashMap();
            Salon salon = (Salon)salonObject;
            salonMap.put("storeId", salon.getRecordId());
            List<Shift> listShift = shiftDao.getByWhere(where2,salonMap);

            SalonShift ss = new SalonShift();
            ss.setAddress(salon.getAddress());
            ss.setSalonName(salon.getSalonName());
            ss.setTel(salon.getTel());
            ss.setRecordId(salon.getRecordId());
            for(Shift shift:listShift){
                int type = shift.getShiftType();
                if(type==0){
                    ss.setAllShift(shift.getTimeStart()+"~"+shift.getTimeEnd());
                }else if(type==1){
                    ss.setMorningShift(shift.getTimeStart()+"~"+shift.getTimeEnd());

                }else if(type==2){
                    ss.setMiddleShift(shift.getTimeStart()+"~"+shift.getTimeEnd());

                }else if(type==3){
                    ss.setEveningShift(shift.getTimeStart()+"~"+shift.getTimeEnd());

                }
            }
            listSalonShift.add(ss);

        }

        ejr.setTotal(listCount);
        ejr.setRootProperty(listSalonShift);
        return ejr;
    }
}
