package com.hy.salon.basic.controller;

import com.hy.salon.basic.dao.ServiceDAO;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.Service;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/hy/basic/service")
@Api(value = "ServiceController| 次卡/服务项目控制器")
public class ServiceController extends SimpleCRUDController<Service> {


    @Resource(name = "serviceDao")
    private ServiceDAO serviceDao;

    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;


    @Override
    protected BaseDAOWithEntity<Service> getCrudDao() {
        return serviceDao;
    }


    @ResponseBody
    @RequestMapping("/queryService")
    @ApiOperation(value="获取所有次卡", notes="获取本美容院所有次卡")
   public Result queryService(){
        Result r= new Result();
        SystemUser user = authenticateService.getCurrentLogin();
        Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
        List<Service> serviceList= serviceDao.queryServiceForId(stuff.getStoreId());

        r.setMsg("请求成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        r.setData(serviceList);
        return r;
    }

    @ResponseBody
    @RequestMapping("/queryServiceData")
    @ApiOperation(value="获取次卡信息", notes="通过次卡号获取次卡信息")
    public Result queryServiceData(Long recordId){
        Result r= new Result();
        Service service=serviceDao.queryOneService(recordId);



        r.setMsg("请求成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        r.setData(service);
        return r;
    }
    @ResponseBody
    @RequestMapping("/addService")
    @ApiOperation(value="添加次卡", notes="添加次卡")
    public Result addService(Service condition){
        Result r= new Result();
        SystemUser user = authenticateService.getCurrentLogin();
        Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
        condition.setStoreId(stuff.getStoreId());
        int i=serviceDao.insert(condition);
        if(i!=1){
            r.setMsg("添加失败");
            r.setMsgcode("200");
            r.setSuccess(false);
            return r;
        }

        r.setMsg("请求成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        return r;
    }
    @ResponseBody
    @RequestMapping("/updateService")
    @ApiOperation(value="修改次卡", notes="修改次卡")
    public Result updateService(Service condition){
        Result r= new Result();
        int i=serviceDao.update(condition);
        if(i!=1){
            r.setMsg("修改失败");
            r.setMsgcode("200");
            r.setSuccess(false);
            return r;
        }

        r.setMsg("请求成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        return r;
    }

}
