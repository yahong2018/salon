package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.dao.RoleUserDAO;
import com.zhxh.admin.dao.SystemUserDAO;
import com.zhxh.admin.entity.RoleUser;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import io.swagger.annotations.Api;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Resource(name = "paymentItemDao")
    private PaymentItemDao paymentItemDao;

    @Resource(name = "memberProductRejectDao")
    private MemberProductRejectDao memberProductRejectDao;


    @Resource(name = "memberWalletDAO")
    private MemberWalletDAO MemberWalletDao;

    @Resource(name = "systemUserDAO")
    private SystemUserDAO systemUserDAO;

    @Resource(name = "roleActionDao")
    private RoleActionDAO roleActionDao;

    @Resource(name = "stuffJobDao")
    private StuffJobDao stuffJobDao;


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

        //今天0点0分
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        String startTime = sDateFormat.format(zero);
        //今天23点59分
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date());
        calendar2.set(Calendar.HOUR_OF_DAY,23);
        calendar2.set(Calendar.MINUTE, 59);
        calendar2.set(Calendar.SECOND, 59);
        Date zero2 = calendar2.getTime();
        String endTime = sDateFormat.format(zero2);

        //当前时间的30天前
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -30);
        String DaysAgo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now.getTime());

        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        String first = sDateFormat.format(c.getTime());

        //现在时间
        String date = sDateFormat.format(new Date());
        if(null !=roleUser){
            if(roleUser.getRoleId()==1){



                List<Salon> salonList=salonDao.getAllStore(startTime,endTime);

                List<Member> memberList=memberDao.getMemberForTime(startTime,endTime,null);

                Double sumAmount=new Double(0);
                Double sumAmount2=new Double(0);
                Double sumAmount3=new Double(0);
                Map<String,Object>  sumAmountMap=CardPurchaseDao.queryAmount(startTime,endTime,"0","0",null,null);
                if(null!=sumAmountMap){
                    sumAmount=(Double)sumAmountMap.get("amount");
                }
                Map<String,Object>  sumAmountMap2=CardPurchaseDao.queryAmount(startTime,endTime,"1","0",null,null);
                if(null!=sumAmountMap2){
                    sumAmount2=(Double)sumAmountMap2.get("amount");
                }

                Map<String,Object>  sumAmountMap3=CardPurchaseDao.queryAmount(startTime,endTime,null,null,null,null);
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
                Map<String,Object>  sumAmountMap=CardPurchaseDao.queryAmount(first,date,"0","0",stuff.getStoreId(),null);
                if(null!=sumAmountMap){
                    sumAmount=(Double)sumAmountMap.get("amount");
                }
                Map<String,Object>  sumAmountMap2=CardPurchaseDao.queryAmount(first,date,"1","0",stuff.getStoreId(),null);
                if(null!=sumAmountMap2){
                    sumAmount2=(Double)sumAmountMap2.get("amount");
                }
                //消费
                Map<String,Object>  sumAmountMap3=CardPurchaseDao.queryAmount(first,date,null,null,stuff.getStoreId(),null);
                if(null!=sumAmountMap3){
                    sumAmount3=(Double)sumAmountMap3.get("amount");
                }
                //还欠款
                Double ArreagesAmount=new Double(0);
               Map<String,Object> arrearagesAmount=arrearagesRecordDao.getArreagesAmount(stuff.getStoreId(),first,date);
                if(null!=arrearagesAmount){
                    ArreagesAmount=(Double)arrearagesAmount.get("reimbursementAmount");
                }
                //退款金额
                Double rejectAmount=new Double(0);
                Map<String,Object> rejectAmountMap=memberProductRejectItemDao.getRejectAmount(stuff.getStoreId(),first,date);
                if(null!=rejectAmountMap){
                    rejectAmount=(Double)rejectAmountMap.get("rejectAmount");
                }


                Double refundAmount=new Double(0);
                Double cashAmount=new Double(0);
                Double bankCardAmount=new Double(0);
                Double weixinAmount=new Double(0);
                Double alipayAmount=new Double(0);

                //总收入

                //总支出
                Map<String,Object>  refundAmountMap=memberProductRejectDao.queryRefundAmountForStuff(stuff.getStoreId());
                if(null!=refundAmountMap){
                    refundAmount=(Double)refundAmountMap.get("amount");
                }

                //现金
                Map<String,Object>  cashAmountMap=CardPurchaseDao.queryAmount(first,date,null,null,stuff.getStoreId(),"3");
                if(null!=cashAmountMap){
                    cashAmount=(Double)cashAmountMap.get("amount");
                }
                //银行卡
                Map<String,Object>  bankCardAmountMap=CardPurchaseDao.queryAmount(first,date,null,null,stuff.getStoreId(),"2");
                if(null!=bankCardAmountMap){
                    bankCardAmount=(Double)bankCardAmountMap.get("amount");
                }
                //微信
                Map<String,Object>  weixinAmountMap=CardPurchaseDao.queryAmount(first,date,null,null,stuff.getStoreId(),"0");
                if(null!=weixinAmountMap){
                    weixinAmount=(Double)weixinAmountMap.get("amount");
                }
                //支付宝
                Map<String,Object>  alipayAmountMap=CardPurchaseDao.queryAmount(first,date,null,null,stuff.getStoreId(),"1");
                if(null!=alipayAmountMap){
                    alipayAmount=(Double)alipayAmountMap.get("amount");
                }



                //顾客到店相关
                //顾客数
                List<Member> memberList=memberDao.getMemberForTime(null,null,stuff.getStoreId());

                //30天内增加的会员数
                List<Member> daysAgoMemberList=memberDao.getMemberForTime(DaysAgo,sDateFormat.format(new Date()),stuff.getStoreId());







                jsonObj.put("sumAmount",sumAmount);
                jsonObj.put("sumAmount2",sumAmount2);
                jsonObj.put("sumAmount3",sumAmount3);
                jsonObj.put("ArreagesAmount",ArreagesAmount);
                jsonObj.put("rejectAmount",rejectAmount);


                jsonObj.put("refundAmount",refundAmount);
                jsonObj.put("cashAmount",cashAmount);
                jsonObj.put("bankCardAmount",bankCardAmount);
                jsonObj.put("weixinAmount",weixinAmount);
                jsonObj.put("alipayAmount",alipayAmount);

                jsonObj.put("memberSize",memberList.size());
                jsonObj.put("daysAgoMemberSize",daysAgoMemberList.size());

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

        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        String first = sDateFormat.format(c.getTime());

        //现在时间
        String date = sDateFormat.format(new Date());

        SystemUser user = authenticateService.getCurrentLogin();
        Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());

        Double sumAmount=new Double(0);
        Double sumAmount2=new Double(0);
        Double sumAmount3=new Double(0);
        //充值
        Map<String,Object>  sumAmountMap=CardPurchaseDao.queryAmount(first,date,"0","0",stuff.getStoreId(),null);
        if(null!=sumAmountMap){
            sumAmount=(Double)sumAmountMap.get("amount");
        }
        Map<String,Object>  sumAmountMap2=CardPurchaseDao.queryAmount(first,date,"1","0",stuff.getStoreId(),null);
        if(null!=sumAmountMap2){
            sumAmount2=(Double)sumAmountMap2.get("amount");
        }
        //消费
        Map<String,Object>  sumAmountMap3=CardPurchaseDao.queryAmount(first,date,null,null,stuff.getStoreId(),null);
        if(null!=sumAmountMap3){
            sumAmount3=(Double)sumAmountMap3.get("amount");
        }
        //还欠款
        Double ArreagesAmount=new Double(0);
        Map<String,Object> arrearagesAmount=arrearagesRecordDao.getArreagesAmount(stuff.getStoreId(),first,date);
        if(null!=arrearagesAmount){
            ArreagesAmount=(Double)arrearagesAmount.get("reimbursementAmount");
        }
        //退款金额
        Double rejectAmount=new Double(0);
        Map<String,Object> rejectAmountMap=memberProductRejectItemDao.getRejectAmount(stuff.getStoreId(),first,date);
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



    /**
     * 获取收支分析
     */
    @RequestMapping("queryBudgetAnalysis")
    @ResponseBody
    public Result queryBudgetAnalysis(){
        Result r= new Result();
        JSONObject jsonObj=new JSONObject();

        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        String first = sDateFormat.format(c.getTime());

        //现在时间
        String date = sDateFormat.format(new Date());

        SystemUser user = authenticateService.getCurrentLogin();
        Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());

        Double cashAmount=new Double(0);
        Double bankCardAmount=new Double(0);
        Double weixinAmount=new Double(0);
        Double alipayAmount=new Double(0);

        //总收入

        //现金
        Map<String,Object>  cashAmountMap=CardPurchaseDao.queryAmount(first,date,null,null,stuff.getStoreId(),"3");
        if(null!=cashAmountMap){
            cashAmount=(Double)cashAmountMap.get("amount");
        }
        //银行卡
        Map<String,Object>  bankCardAmountMap=CardPurchaseDao.queryAmount(first,date,null,null,stuff.getStoreId(),"2");
        if(null!=bankCardAmountMap){
            bankCardAmount=(Double)bankCardAmountMap.get("amount");
        }
        //微信
        Map<String,Object>  weixinAmountMap=CardPurchaseDao.queryAmount(first,date,null,null,stuff.getStoreId(),"0");
        if(null!=weixinAmountMap){
            weixinAmount=(Double)weixinAmountMap.get("amount");
        }
        //支付宝
        Map<String,Object>  alipayAmountMap=CardPurchaseDao.queryAmount(first,date,null,null,stuff.getStoreId(),"1");
        if(null!=alipayAmountMap){
            alipayAmount=(Double)alipayAmountMap.get("amount");
        }

        jsonObj.put("cashAmount",cashAmount);
        jsonObj.put("bankCardAmount",bankCardAmount);
        jsonObj.put("weixinAmount",weixinAmount);
        jsonObj.put("alipayAmount",alipayAmount);

        r.setData(jsonObj);
        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return r;
    }


    /**
     * 获取员工分析
     */
    @RequestMapping("queryStuffAnalysis")
    @ResponseBody
    public Result queryStuffAnalysis(){
        Result r= new Result();
        JSONObject jsonObj=new JSONObject();

        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        String first = sDateFormat.format(c.getTime());

        //现在时间
        String date = sDateFormat.format(new Date());

        SystemUser user = authenticateService.getCurrentLogin();
        Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());

        //员工分析

        List<Map<String,Object>> stuffAmountList=new ArrayList<>();
        //获取所有员工

//        PageHelper.startPage(1, 10);
        List<Stuff> stuffList=stuffDao.getStuffForStoreId(stuff.getStoreId());
        for(Stuff s:stuffList){
            Map<String,Object> stuffAmountMap=new HashMap<>();
            stuffAmountMap.put("stuffName",s.getStuffName());
            Double consumptionAmount=new Double(0);
            Double rechargeAmount=new Double(0);
            Double paymentAmount=new Double(0);
            //通过每个员工的消费
            Map<String,Object>  amountMap1=CardPurchaseDao.queryAmountForStuff(s.getRecordId(),null,first,date);
            if(null != amountMap1){
                consumptionAmount=(Double) amountMap1.get("amount");
            }
            //通过每个员工的消费
                Map<String,Object>  amountMap2=CardPurchaseDao.queryAmountForStuff(s.getRecordId(),"1",first,date);
                if(null != amountMap2){
                rechargeAmount=(Double) amountMap2.get("amount");
            }
            Map<String,Object>  amountMap3=paymentItemDao.queryPaymentAmountForStuff(s.getRecordId(),first,date);
            if(null != amountMap3){
                paymentAmount=(Double) amountMap3.get("amount");
            }

            stuffAmountMap.put("paymentAmount",paymentAmount);
            stuffAmountMap.put("consumptionAmount",consumptionAmount);
            stuffAmountMap.put("rechargeAmount",rechargeAmount);
            stuffAmountList.add(stuffAmountMap);
        }

        Collections.sort(stuffAmountList, new Comparator<Map<String,Object>>() {
            @Override
            public int compare(Map<String,Object> o1, Map<String,Object> o2) {

                Double a=(Double)o1.get("consumptionAmount");
                Double b=(Double)o2.get("consumptionAmount");
//                char a = o1.getString("letter").charAt(0);
//                char b = o2.getString("letter").charAt(0);
                if (a > b) {
                    return -1;
                } else if(a == b) {
                    return 0;
                } else
                    return 1;
            }
        });

        //获取前十名
        List<Map<String,Object>> stuffAmountList2=new ArrayList<>();
        for (int i=0;i<10;i++){
            if(stuffAmountList.size()-1>=i){
                if(null!=stuffAmountList.get(i)){
                    stuffAmountList2.add(stuffAmountList.get(i));
                }
            }


        }

        jsonObj.put("stuffAmountList",stuffAmountList);
        jsonObj.put("stuffAmountList2",stuffAmountList2);

        r.setData(jsonObj);
        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return r;
    }


    /**
     * 给每个顾客添加一个钱包
     */

    @RequestMapping("addWallet")
    @ResponseBody
    public Result auditPass() {
        Result r= new Result();

        List<Member> memberList=memberDao.getAll();
        for(Member m:memberList){
            MemberWallet memberWallet=MemberWalletDao.getMemberWalletForMemberId(m.getRecordId());
            if(null==memberWallet){
                memberWallet=new MemberWallet();
                memberWallet.setMemberId(m.getRecordId());
                memberWallet.setBalanceCash(new Double(0));
                memberWallet.setBalanceTotal(new Double(0));
                memberWallet.setIntegral(new Double(0));
                memberWallet.setDebt(new Double(0));
                memberWallet.setAmountCharge(new Double(0));
                memberWallet.setAmountConsumer(new Double(0));
                memberWallet.setCashCoupon(new Double(0));
                MemberWalletDao.insert(memberWallet);
            }

        }
        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return r;


    }



    /**
     * 删除家人
     */

    @RequestMapping("deleteStuff")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Result deleteStuff() {
        Result r= new Result();
        List<RoleUser> roleList=roleUserDAO.getRoleUser(new Long(2));
        for(RoleUser u:roleList){
            roleUserDAO.deleteById(u.getRecordId());
            SystemUser user=systemUserDAO.getUserByRecordId(u.getUserId());
            if(null!=user){
               RoleAction roleAction= roleActionDao.getRoleActionByRecordId(user.getRecordId());
                systemUserDAO.deleteById(user.getRecordId());
               if(null!=roleAction){
                   Stuff stuff=stuffDao.getStuffForRecordId(roleAction.getStuffId());
                   roleActionDao.deleteById(roleAction.getRecordId());
                   if(null!=stuff){
                       stuffDao.deleteById(stuff.getRecordId());
                   }
               }

            }

            List<StuffJob> stuffJobList= stuffJobDao.getStuffJobListForStuff(u.getUserId());
            for(StuffJob s:stuffJobList){
                stuffJobDao.deleteById(s.getRecordId());
            }

        }

        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return r;


    }



    /**
     * 删除顾客
     */

    @RequestMapping("deleteMember")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Result deleteMember() {
        Result r= new Result();
        List<RoleUser> roleList=roleUserDAO.getRoleUser(new Long(3));
        for(RoleUser u:roleList){
            roleUserDAO.deleteById(u.getRecordId());
            SystemUser user=systemUserDAO.getUserByRecordId(u.getUserId());
            if(null!=user){
                systemUserDAO.deleteById(user.getRecordId());
              Member member =  memberDao.getMemberForTel(user.getUserCode());
              if(null!=member){
                  memberDao.deleteById(member.getRecordId());
                  MemberWallet memberWallet=MemberWalletDao.getMemberWalletForMemberId(member.getRecordId());
                  if(null!=memberWallet){
                      MemberWalletDao.deleteById(memberWallet.getRecordId());
                  }

              }
            }


        }

        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return r;


    }



}
