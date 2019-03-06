package com.hy.salon.basic.controller;

import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.service.SalonService;
import com.hy.salon.basic.service.StuffService;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Controller
@RequestMapping("/hy/basic/stuff")
@Api(value = "StuffController| 员工控制器")
public class StuffController extends SimpleCRUDController<Stuff> {

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    @Resource(name = "stuffService")
    private StuffService stuffService;


    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @Resource(name = "salonService")
    private SalonService salonService;


    @Override
    protected BaseDAOWithEntity<Stuff> getCrudDao() {
        return stuffDao;
    }

    /**
     * 获取员工按美容院分类
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getStuff")
    @ApiOperation(value="获取员工按美容院分类", notes="获取员工按美容院分类")
    public Result getStuff(){
        SystemUser user = authenticateService.getCurrentLogin();

        List<Salon> stuffList=salonService.getSalonForCreateId(user.getRecordId());
        Result r= new Result();
        Map dataMap =new HashMap<String, Object>();
        if(!stuffList.isEmpty()){
            for(Salon s :stuffList){
               List<Stuff> stuff= stuffService.getStuffForStoreId(s.getRecordId());
                dataMap.put(s.getSalonName(),stuff);

            }


        }
        r.setData(dataMap);
        r.setMsg("获取成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        return r;
    }


}
