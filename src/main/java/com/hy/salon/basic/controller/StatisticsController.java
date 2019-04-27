package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.Member;
import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.dao.RoleUserDAO;
import com.zhxh.admin.entity.RoleUser;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@Api(value = "StatisticsController|统计控制器")
@RequestMapping("/hy/basic/statistics")
public class StatisticsController {

    @Resource(name = "salonDao")
    private SalonDao salonDao;

    @Resource(name = "memberDao")
    private MemberDao memberDao;

    @Resource(name = "cardPurchaseDao")
    private CardPurchaseDao CardPurchaseDao;

    @Resource(name = "memberGiftDao")
    private MemberGiftDao MemberGiftDao;

    @Resource(name = "productDao")
    private ProductDao  productDao;

    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @Resource(name = "roleUserDAO")
    private RoleUserDAO roleUserDAO;

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    @Resource(name ="arrearagesRecordDao")
    private ArrearagesRecordDao arrearagesRecordDao;

    @Resource(name = "memberProductRejectItemDao")
    private MemberProductRejectItemDao memberProductRejectItemDao;

    /**
     * 数据统计
     */
    @RequestMapping("total")
    @ResponseBody
    public Result total(){
        Result r= new Result();
        //判断该账号身份
        SystemUser user = authenticateService.getCurrentLogin();
        RoleUser roleUser=roleUserDAO.getByUserIdAndRoleId(user.getRecordId());
        Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
        JSONObject jsonObj=new JSONObject();
        if(null !=roleUser){
            if(roleUser.getRoleId()==1){

                SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.set(Calendar.HOUR_OF_DAY,0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                Date zero = calendar.getTime();
                String startTime = sDateFormat.format(zero);

                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(new Date());
                calendar2.set(Calendar.HOUR_OF_DAY,23);
                calendar2.set(Calendar.MINUTE, 59);
                calendar2.set(Calendar.SECOND, 59);
                Date zero2 = calendar2.getTime();
                String endTime = sDateFormat.format(zero2);

                List<Salon> salonList=salonDao.getAllStore(startTime,endTime);

                List<Member> memberList=memberDao.getMemberForTime(startTime,endTime);

                Double sumAmount=new Double(0);
                Double sumAmount2=new Double(0);
                Double sumAmount3=new Double(0);
                Map<String,Object>  sumAmountMap=CardPurchaseDao.queryAmount(startTime,endTime,"0","0",null);
                if(null!=sumAmountMap){
                    sumAmount=(Double)sumAmountMap.get("amount");
                }
                Map<String,Object>  sumAmountMap2=CardPurchaseDao.queryAmount(startTime,endTime,"1","0",null);
                if(null!=sumAmountMap2){
                    sumAmount2=(Double)sumAmountMap2.get("amount");
                }

                Map<String,Object>  sumAmountMap3=CardPurchaseDao.queryAmount(startTime,endTime,null,null,null);
                if(null!=sumAmountMap3){
                    sumAmount3=(Double)sumAmountMap3.get("amount");
                }

                Double qty=new Double(0);
                Double qty2=new Double(0);
                Map<String,Object>  gift= MemberGiftDao.getQty(startTime,endTime,"0");
                if(null!=gift){
                    qty=(Double)gift.get("qty");
                }
                Map<String,Object>  gift2= MemberGiftDao.getQty(startTime,endTime,"1");
                if(null!=gift2){
                    qty2=(Double)gift2.get("qty");
                }

                jsonObj.put("jobLevel",-1);
                jsonObj.put("salonSize",salonList.size());
                jsonObj.put("memberSize",memberList.size());
                jsonObj.put("sumAmount",sumAmount);
                jsonObj.put("sumAmount2",sumAmount2);
                jsonObj.put("sumAmount3",sumAmount3);
                jsonObj.put("qty",qty);
                jsonObj.put("qty2",qty2);

            }else{
            //门店角色
                Double sumAmount=new Double(0);
                Double sumAmount2=new Double(0);
                Double sumAmount3=new Double(0);
                //充值
                Map<String,Object>  sumAmountMap=CardPurchaseDao.queryAmount(null,null,"0","0",stuff.getStoreId());
                if(null!=sumAmountMap){
                    sumAmount=(Double)sumAmountMap.get("amount");
                }
                Map<String,Object>  sumAmountMap2=CardPurchaseDao.queryAmount(null,null,"1","0",stuff.getStoreId());
                if(null!=sumAmountMap2){
                    sumAmount2=(Double)sumAmountMap2.get("amount");
                }
                //消费
                Map<String,Object>  sumAmountMap3=CardPurchaseDao.queryAmount(null,null,null,null,stuff.getStoreId());
                if(null!=sumAmountMap3){
                    sumAmount3=(Double)sumAmountMap3.get("amount");
                }
                //还欠款
                Double ArreagesAmount=new Double(0);
               Map<String,Object> arrearagesAmount=arrearagesRecordDao.getArreagesAmount(stuff.getStoreId());
                if(null!=arrearagesAmount){
                    ArreagesAmount=(Double)arrearagesAmount.get("reimbursementAmount");
                }
                //退款金额
                Double rejectAmount=new Double(0);
                Map<String,Object> rejectAmountMap=memberProductRejectItemDao.getRejectAmount(stuff.getStoreId());
                if(null!=rejectAmountMap){
                    rejectAmount=(Double)rejectAmountMap.get("rejectAmount");
                }



                jsonObj.put("sumAmount",sumAmount);
                jsonObj.put("sumAmount2",sumAmount2);
                jsonObj.put("sumAmount3",sumAmount3);
                jsonObj.put("ArreagesAmount",ArreagesAmount);
                jsonObj.put("rejectAmount",rejectAmount);
                jsonObj.put("jobLevel",1);
            }


        }


        r.setData(jsonObj);
        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return r;
    }


    /**
     * 获取产品销量
     */
    @RequestMapping("queryProductTotal")
    @ResponseBody
    public Result queryProductTotal(){
        Result r= new Result();
        List<Map<String,Object>> productTotal2=new ArrayList<>();
        List<Map<String,Object>> productTotal=productDao.getProductTotal();
        for(int i=0;i<productTotal.size();i++){
            productTotal2.add(productTotal.get(productTotal.size()-i-1));
        }

        r.setData(productTotal2);
        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return r;
    }



    /**
     * 获取综合分析
     */
    @RequestMapping("queryComprehensiveAnalysis")
    @ResponseBody
    public Result queryComprehensiveAnalysis(){
        Result r= new Result();
        JSONObject jsonObj=new JSONObject();
        SystemUser user = authenticateService.getCurrentLogin();
        Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());

        Double sumAmount=new Double(0);
        Double sumAmount2=new Double(0);
        Double sumAmount3=new Double(0);
        //充值
        Map<String,Object>  sumAmountMap=CardPurchaseDao.queryAmount(null,null,"0","0",stuff.getStoreId());
        if(null!=sumAmountMap){
            sumAmount=(Double)sumAmountMap.get("amount");
        }
        Map<String,Object>  sumAmountMap2=CardPurchaseDao.queryAmount(null,null,"1","0",stuff.getStoreId());
        if(null!=sumAmountMap2){
            sumAmount2=(Double)sumAmountMap2.get("amount");
        }
        //消费
        Map<String,Object>  sumAmountMap3=CardPurchaseDao.queryAmount(null,null,null,null,stuff.getStoreId());
        if(null!=sumAmountMap3){
            sumAmount3=(Double)sumAmountMap3.get("amount");
        }
        //还欠款
        Double ArreagesAmount=new Double(0);
        Map<String,Object> arrearagesAmount=arrearagesRecordDao.getArreagesAmount(stuff.getStoreId());
        if(null!=arrearagesAmount){
            ArreagesAmount=(Double)arrearagesAmount.get("reimbursementAmount");
        }
        //退款金额
        Double rejectAmount=new Double(0);
        Map<String,Object> rejectAmountMap=memberProductRejectItemDao.getRejectAmount(stuff.getStoreId());
        if(null!=rejectAmountMap){
            rejectAmount=(Double)rejectAmountMap.get("rejectAmount");
        }

        jsonObj.put("sumAmount",sumAmount);
        jsonObj.put("sumAmount2",sumAmount2);
        jsonObj.put("sumAmount3",sumAmount3);
        jsonObj.put("ArreagesAmount",ArreagesAmount);
        jsonObj.put("rejectAmount",rejectAmount);

        r.setData(jsonObj);
        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return r;
    }







}
