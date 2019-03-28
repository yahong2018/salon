package com.hy.salon.basic.controller;
import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.dao.CityDAO;
import com.hy.salon.basic.entity.City;
import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.entity.StoreRoom;
import com.hy.salon.basic.service.SalonService;
import com.hy.salon.basic.service.StoreRoomService;
import com.hy.salon.basic.service.StuffService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/hy/basic/my")
public class MyController {
    @Resource(name = "stuffService")
    private StuffService stuffService;

    @Resource(name = "salonService")
    private SalonService salonService;

    @Resource(name = "storeRoomService")
    private StoreRoomService storeRoomService;

    @Resource(name = "cityDAO")
    private CityDAO cityDAO;



    @ResponseBody
    @RequestMapping("/getStaff")
    public void getStaff(){
//        List<Salon> salonList=salonService.getSalon();
//        JSONObject jsonObj=new JSONObject();
//        JSONArray jsonArr=new JSONArray();
//
//        if(!salonList.isEmpty()){
//            for(Salon s:salonList){
//                Stuff stuff=stuffService.getStuffForStoreId(s.getRecordId());
//                jsonArr.add(stuff.getStuffName());
//                jsonObj.put(s.getSalonName(),jsonArr);
//            }
//           for(int i=0;i<=salonList.size();i++){
//
//
//
//           }
//
//        }

//        System.out.println("stuffName="+stuffService.getStuffForStoreId("1").get(0).getStuffName());
    }


/**
 * 省市区表添加namePath
 */

@ResponseBody
@RequestMapping("/addNamePath")
public void addNamePath(){



    List<City> provinceList=cityDAO.getCity("0");
    for(City c:provinceList){
        c.setNamePath(c.getCityName());
        cityDAO.update(c);
        System.out.println("省名"+c.getNamePath());
        //获取市
        List<City> cityList=cityDAO.getCity(c.getCityCode());
        //获取区
        for(City c2:cityList){
            c2.setNamePath(c.getNamePath()+c2.getCityName());
            System.out.println("市名"+c2.getNamePath());
            cityDAO.update(c2);
            List<City> areaList=cityDAO.getCity(c2.getCityCode());
            for(City c3:areaList){
                c3.setNamePath(c2.getNamePath()+c3.getCityName());
                cityDAO.update(c3);
                System.out.println("区名"+c3.getNamePath());
            }

        }

    }


}








}
