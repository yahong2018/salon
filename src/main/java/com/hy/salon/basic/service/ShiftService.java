package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.ShiftDao;
import com.hy.salon.basic.entity.Shift;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("shiftService")
public class ShiftService {
    @Resource(name = "shiftDao")
    private ShiftDao shiftDao;

    public void saveShift(List<Shift> list) {
        //查询该门店是否有排班记录,有就删除
        if (list!=null && list.size()>0){
            Map parameters = new HashMap();
            parameters.put("storeId", list.get(0).getStoreId());
            Map listMap = new HashMap();
            String where="store_id=#{storeId}";
            listMap.put("where", where);
            List<Shift> shiftList = shiftDao.getList(listMap, parameters);
            if(shiftList!=null && shiftList.size()>0){
                //删除旧的排班记录
                shiftDao.deleteByWhere(where, parameters);
            }
            //保存新的排班记录
            for (Shift shift : list) {
                shiftDao.insert(shift);
            }
        }
    }
}
