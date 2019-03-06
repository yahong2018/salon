package com.hy.salon.basic.controller;
import com.alibaba.fastjson.JSONObject;
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













}
