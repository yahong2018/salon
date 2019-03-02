package com.hy.salon.basic.controller;

import com.hy.salon.basic.dao.SalonDao;
import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.service.SalonService;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hy/basic/salon")
public class SalonController extends SimpleCRUDController<Salon> {

    @Resource(name = "salonDao")
    private SalonDao salonDao;

    @Override
    protected BaseDAOWithEntity<Salon> getCrudDao() {
        return salonDao;
    }
    @Resource(name = "salonService")
    private SalonService salonService;




    /**
     * 获取美容院信息
     */
    @RequestMapping("/getSalonData")
    @ResponseBody
    public Map getSalonData(HttpServletResponse resp, String recordId)  {
        Map map =new HashMap<String, Object>();
        if( null == recordId || "".equals(recordId)){
            map.put("respCode","0001");
            map.put("respDesc","美容院号不能为空");


            return map;
        }
        Salon salon=salonService.getSalonForId(recordId);
        if(null == salon ){
            map.put("respCode","0001");
            map.put("respDesc","没有该美容院");
            return map;
        }

        map.put("respCode","0000");
        map.put("salon",salon);
        return map;

    }
    /**
     * 获取门店列表
     */
    @ResponseBody
    @RequestMapping("getStoreData")
    public Map getStireData(HttpServletResponse resp,String recordId) {
        Map map =new HashMap<String, Object>();
        if( null == recordId || "".equals(recordId)){
            map.put("respCode","0001");
            map.put("respDesc","美容院号不能为空");
            return map;
        }
        List<Salon> StoreList=salonService.getSalonForStoreId(recordId);

        map.put("respCode","0000");
        map.put("StoreList",StoreList);

       return map;
    }
    /**
     * 获取门店信息
     */
    @ResponseBody
    @RequestMapping("getStoreDetails")
    public Map getStoreDetails(HttpServletResponse resp,String recordId) {
        Map map =new HashMap<String, Object>();
        if( null == recordId || "".equals(recordId)){
            map.put("respCode","0001");
            map.put("respDesc","美容院号不能为空");
            return map;
        }

        Salon store=salonService.getSalonForId(recordId);

        map.put("respCode","0000");
        map.put("store",store);
        return map;


    }



}
