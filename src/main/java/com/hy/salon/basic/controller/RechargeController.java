package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.service.*;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.dao.RoleUserDAO;
import com.zhxh.admin.entity.RoleUser;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.web.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/hy/basic/recharge")
@Api(value = "RechargeController| 充值")
public class RechargeController {
    @Resource(name = "vipSuiteDao")
    private VipSuiteDAO vipSuiteDao;

    @Resource(name = "vipSuiteService")
    private VipSuiteService vipSuiteService;

    @Resource(name = "vipSuiteItemDao")
    private VipSuiteItemDAO vipSuiteItemDao;

    @Resource(name = "serviceSeriesDao")
    private ServiceSeriesDAO serviceSeriesDao;

    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    @Resource(name = "vipSuiteItemDiscountRangeDAO")
    private VipSuiteItemDiscountRangeDAO vipSuiteItemDiscountRangeDAO;

    @Resource(name = "picturesDao")
    private PicturesDAO picturesDao;

    @Resource(name = "serviceDao")
    private ServiceDAO serviceDao;
    @Resource(name = "productService")
    private ProductService productService;
    @Resource(name = "productDao")
    private ProductDao productDao;

    @Resource(name = "cardBalanceDao")
    private CardBalanceDao cardBalanceDao;

    @Resource(name="memberGiftDao")
    private MemberGiftDao memberGiftDao;
    @Resource(name="cardPurchaseDao")
    private CardPurchaseDao cardPurchaseDao;

    @Resource(name="cardPurchaseService")
    private CardPurchaseService cardPurchaseService;

    @Resource(name="businessStuffDao")
    private  BusinessStuffDao businessStuffDao;
    @Resource(name="memberDao")
    private MemberDao memberDao;

    @Resource(name="memberGiftService")
    private MemberGiftService memberGiftService;
    @Resource(name="arrearagesRecordDao")
    private ArrearagesRecordDao arrearagesRecordDao;

    @Resource(name="stampProgramDao")
    private  StampProgramDao stampProgramDao;

    @Resource(name="memberProductKeepDao")
    private MemberProductKeepDao memberProductKeepDao;

    @Resource(name="memberProductKeepItemDao")
    private MemberProductKeepItemDao memberProductKeepItemDao;

    @Resource(name = "memberWalletDAO")
    private MemberWalletDAO MemberWalletDao;

    @Resource(name = "roleUserDAO")
    private RoleUserDAO roleUserDAO;

    @Resource(name = "reservationDao")
    private ReservationDao reservationDao;
    private final ListRequestProcessHandler listRequestProcessHandler = new ListRequestProcessHandler();
    @ResponseBody
    @RequestMapping("/getMemberInfo")
    @ApiOperation(value="获取会员", notes="获取会员")
    public JSONObject getMemberInfo(HttpServletRequest request,Long recordId){
        if(recordId==null){
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff2 = stuffDao.getStuffForUser(user.getRecordId());
            recordId = stuff2.getStoreId();
        }
        JSONObject jsonObject = new JSONObject();
        SystemUser user = authenticateService.getCurrentLogin();
        Stuff stuff2 = stuffDao.getStuffForUser(user.getRecordId());
        String where="initial_store_id=#{storeId}";
        Map parameters = new HashMap();
        parameters.put("storeId", stuff2.getStoreId());
        List<Member> list =  memberDao.getByWhere(where,parameters);
        jsonObject.put("listMember",list);
        return  jsonObject;
    }

    /**
     * 会员卡充值记录列表
     */
    @ResponseBody
    @RequestMapping("/getSystemRechargeList")
    @ApiOperation(value="会员卡充值记录列表", notes="会员卡充值记录列表")
    public ExtJsResult getSystemRechargeList(HttpServletRequest request,String memberId,Long recordId,String toDays){
        String role="0";
        if(recordId==null){
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff2 = stuffDao.getStuffForUser(user.getRecordId());

            String where="user_id=#{userId}";
            Map parameters = new HashMap();
            parameters.put("userId", user.getRecordId());
            RoleUser roleUser =roleUserDAO.getOne(where,parameters);
            //判断用户角色
            if(roleUser.getRoleId()==1){
                role="1";
            }else if(roleUser.getRoleId()==10){
                role="2";
            }else if(roleUser.getRoleId()==11){
                role="3";
            }
            recordId = stuff2.getStoreId();
        }

        //List<Service> serviceList= serviceDao.queryServiceForId(storeId);
        ExtJsResult VipSuiteList=cardPurchaseService.getSystemRechargeList(memberId,recordId, request,toDays,role);
        return  VipSuiteList;
    }

    /**
     * 会员卡充值记录赠送列表
     */
    @ResponseBody
    @RequestMapping("/getSystemMemberGiftList")
    @ApiOperation(value="会员卡充值记录赠送列表", notes="会员卡充值记录赠送列表")
    public ExtJsResult getSystemMemberGiftList(HttpServletRequest request,String memberName,Long recordId,Long refTransId,String toDays){
        String role="2";
        if(recordId==null){
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff2 = stuffDao.getStuffForUser(user.getRecordId());

            String where="user_id=#{userId}";
            Map parameters = new HashMap();
            parameters.put("userId", user.getRecordId());
            RoleUser roleUser =roleUserDAO.getOne(where,parameters);
            //判断用户角色
            if(roleUser.getRoleId()==1){
                role="1";
            }else if(roleUser.getRoleId()==10){
                role="2";
            }else if(roleUser.getRoleId()==11){
                role="3";
            }

            recordId = stuff2.getStoreId();
        }
        //List<Service> serviceList= serviceDao.queryServiceForId(storeId);
        ExtJsResult VipSuiteList=memberGiftService.getSystemMemberGiftList(memberName,recordId,refTransId, request,role,toDays);
        return  VipSuiteList;
    }



    /**
     *充值3(结算)
     */
    @ResponseBody
    @RequestMapping("/addRecharge")
    @ApiOperation(value="充值3(结算)", notes="充值3(结算)")
    @ApiImplicitParams({
            /*card_balance  卡户表*/
            @ApiImplicitParam(paramType="query", name = "recordId", value = "卡户余额表id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "memberId", value = "会员id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "cardId", value = "卡id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "cardType", value = "卡类型", required = true, dataType = "Byte"),

            @ApiImplicitParam(paramType="query", name = "balance", value = "卡户余额/剩余  次数(充值面额)", required = true, dataType = "double"),

            @ApiImplicitParam(paramType="query", name = "cardStatus", value = "卡状态：0：正常  1：失效", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "remark", value = "备注", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "parentId", value = "所属套卡:如果是充值卡、次卡、套卡，则为0，只有次卡的项目才有此字段的值", required = true, dataType = "Long"),
            /*card_purchase 购卡、充值表*/
            @ApiImplicitParam(paramType="query", name = "memberId", value = "会员id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "cardId", value = "卡id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "cardType", value = "卡类型", required = true, dataType = "Byte"),

            @ApiImplicitParam(paramType="query", name = "amountMarket", value = "原价（不管）", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "amount", value = "充值金额/总金额 （卡面金额）", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "amountDebit", value = "欠款（不管）", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "amountPayed", value = "实际支付/现金支付 ", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "methodPayed", value = "支付方式: 0.微信  1.支付宝  2.银行卡  10.现金", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "remark", value = "备注", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "memberSignature", value = "客户签名(系统照片ID)", required = true, dataType = "long"),
            /*member_gift 赠送表*/

            @ApiImplicitParam(paramType="query", name = "memberId", value = "会员id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "giftType", value = " -- 赠品类型：0.项目 1.产品 2.优惠券 3.金额\n" +
                    "                --   产品:注意扣除库存数量\n" +
                    "                --   金额:注意修改应支付的金额数量", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "gitId", value = "赠品Id: 金额:-1，优惠券:-2 项目和产品:填写其对应的Id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "qty", value = "数量/金额", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "giftSubType", value = "优惠券类型： 0.项目券   1.代金券", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "giftExpiredDate", value = "有效期", required = true, dataType = "Date"),

            /*business_stuff 关联员工表*/
            @ApiImplicitParam(paramType="query", name = "stuffId", value = "员工id", required = true, dataType = "Long"),
    })
    @Transactional(rollbackFor = Exception.class)
    public ExtJsResult addRecharge(CardBalance cardBalance,CardPurchase cardPurchase,String memberGiftListJson,String stuffIdListJson,String memberRechargeType/*,List<MemberGift> memberGiftList,List<Long> stuffIdList*/){
        ExtJsResult  ejr = new ExtJsResult();
//        try{

//            SystemUser user = authenticateService.getCurrentLogin();
//            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());

        if("1".equals(memberRechargeType)){
            CardBalance checkCard=cardBalanceDao.getCardForMemberId(cardBalance.getMemberId(),cardBalance.getCardId(),new Long(0));
            int transType = 0;
            if(checkCard!=null){//说明是已存在的卡户
//                CardBalance cardBalanceOld= cardBalanceDao.getById(cardBalance.getRecordId());
                checkCard.setRemark(cardBalance.getRemark());
                cardPurchase.setRechargeType(0);
                checkCard.setBalance(cardBalance.getBalance()+checkCard.getBalance());
                checkCard.setBalanceTotal(checkCard.getBalanceTotal()+cardBalance.getBalanceTotal());
                cardBalanceDao.update(checkCard);//卡户表
                transType= 1;
            }else{
                cardPurchase.setRechargeType(1);
                cardBalanceDao.insert(cardBalance);//卡户表
            }

        }else{
            cardPurchase.setRechargeType(3);
        }
        cardPurchaseDao.insert(cardPurchase);//充值、购卡记录表

            double balance = 0;
            double cashCoupon = 0;
            double integral = 0;



        if(!"".equals(memberGiftListJson)){

            net.sf.json.JSONArray memberGiftListArr=net.sf.json.JSONArray.fromObject(memberGiftListJson);
            List<MemberGift> memberGiftlist = net.sf.json.JSONArray.toList(memberGiftListArr, MemberGift.class);// 转换成实体类
//            JSONArray
            for(MemberGift mg:memberGiftlist){
                mg.setRefTransId(cardPurchase.getRecordId());
                //mg.setTransType((byte)transType);
                mg.setTransType((byte)0);
                if(mg.getGiftType()==3){
                  //  balance = balance +mg.getQty();
                    if (mg.getGiftCashType() == 1) {
                        integral = integral+mg.getQty();
                    }else{
                        cashCoupon = cashCoupon +mg.getQty();
                    }

                }else if(mg.getGiftType()==0){
                    Service service =  serviceDao.getById(mg.getGitId());//项目表
                    int qty = mg.getQty().intValue();
                    balance = balance + service.getPrice()*qty;

                    //卡户余额添加次卡次数
                    CardBalance checkCard=cardBalanceDao.getCardForMemberId(cardBalance.getMemberId(),mg.getGitId(),new Long(1));
                    if(null==checkCard){
                        checkCard=new CardBalance();
                        checkCard.setCardId(mg.getGitId());
                        checkCard.setMemberId(mg.getMemberId());
                        checkCard.setCardType(new Byte("1"));
                        checkCard.setBalance(mg.getQty());
                        checkCard.setBalanceTotal(mg.getQty());
                        checkCard.setCardStatus(new Byte("0"));
                        checkCard.setParentId(new Long(0));
                        cardBalanceDao.insert(checkCard);
                    }else{
                        checkCard.setBalance(checkCard.getBalance()+mg.getQty());
                        checkCard.setBalanceTotal(checkCard.getBalanceTotal()+mg.getQty());
                        cardBalanceDao.update(checkCard);

                    }


                }else if(mg.getGiftType()==1){
                    Product product = productDao.getById(mg.getGitId());//产品表
                    int qty = mg.getQty().intValue();
                    product.setStockOfPreWarning(product.getStockOfPreWarning()-qty);//数量减少
                    balance = balance + product.getPrice()*qty;
                    productDao.update(product);//产品表

                    balance  = product.getPrice();
                    MemberProductKeep memberProductKeep = new MemberProductKeep();
                    memberProductKeep.setMemberId(cardBalance.getMemberId());
                    memberProductKeep.setMemberSignature(cardPurchase.getMemberSignature());
                    memberProductKeep.setRemark(cardPurchase.getRemark());
                    memberProductKeepDao.insert(memberProductKeep);
                    MemberProductKeepItem memberProductKeepItem = new MemberProductKeepItem();

                    memberProductKeepItem.setMemberProductKeepId(memberProductKeep.getRecordId());
                    memberProductKeepItem.setProductId(product.getRecordId());
                    memberProductKeepItem.setPrice(balance);
                    memberProductKeepItem.setQtyPurchased(mg.getQty());
                    memberProductKeepItem.setAmount(1*balance);
                    memberProductKeepItem.setQtyReceived((double)0);
                    memberProductKeepItem.setProductGetType((byte) 1);
                    memberProductKeepItemDao.insert(memberProductKeepItem);
                }else  if(mg.getGiftType()==2){
                    StampProgram stampProgram = new StampProgram();
                    stampProgram.setMemberId(cardBalance.getMemberId());
                    stampProgram.setDenomination(mg.getQty());
                    stampProgram.setExpiredTime(mg.getGiftExpiredDate());
                    stampProgram.setIsExpired((byte)1);
                    stampProgram.setIsUsed((byte)1);
                    stampProgramDao.insert(stampProgram);//项目券
                }

                memberGiftDao.insert(mg);//赠送表
            }
        }
        net.sf.json.JSONArray stuffListArr=net.sf.json.JSONArray.fromObject(stuffIdListJson);
        List<Long> stufflist = net.sf.json.JSONArray.toList(stuffListArr, Long.class);// 转换成实体类

            for(Long stuffId:stufflist){
                BusinessStuff businessStuff  = new BusinessStuff();
                businessStuff.setStuffId(stuffId);
                businessStuff.setTransType((byte)0);
                businessStuff.setRefTransId(cardPurchase.getRecordId());
                businessStuffDao.insert(businessStuff);//关联员工
            }


            //获取钱包
            MemberWallet memberWallet=MemberWalletDao.getMemberWalletForMemberId(cardBalance.getMemberId());
            memberWallet.setBalanceTotal(memberWallet.getBalanceTotal()+balance+cardPurchase.getAmount());
            memberWallet.setAmountCharge(memberWallet.getAmountCharge()+cardPurchase.getAmount());
            memberWallet.setCashCoupon(memberWallet.getCashCoupon()+cashCoupon);
            memberWallet.setIntegral(memberWallet.getIntegral()+integral);
            MemberWalletDao.update(memberWallet);

//            Member member =  memberDao.getById(cardBalance.getMemberId());
//            member.setBalanceTotal(member.getBalanceTotal()+balance);
//            member.setDebt(member.getDebt()+cardPurchase.getAmountDebit());
//            member.setAmountCharge(member.getAmountCharge()+cardPurchase.getAmount());
//       /*if(cardBalance.getRecordId()!=null) {//说明是已存在的卡户
//           member.setBalanceCash(member.getBalanceCash()+cardBalance.getBalance());//余额充值
//       }*/
//            member.setCashCoupon(cashCoupon);
//            memberDao.update(member);//会员表
//            if(cardPurchase.getAmountDebit()!=null||cardPurchase.getAmountDebit()!=0){
//                ArrearagesRecord arrearagesRecord = new ArrearagesRecord();
//                arrearagesRecord.setRefTransId(cardPurchase.getRecordId());
//                arrearagesRecord.setMemberId(cardBalance.getMemberId());
//                arrearagesRecord.setArrearagesDate(new Date());
//                arrearagesRecord.setArrearagesType((byte)0);
//                arrearagesRecord.setAmountOfRealPay(cardPurchase.getAmount());
//                arrearagesRecord.setAmountPayable(cardPurchase.getAmountPayed());
//                arrearagesRecord.setIsPaidOff((byte)1);
//                arrearagesRecordDao.insert(arrearagesRecord);//欠款表
//            }
            ejr.setMsg("充值成功");
            ejr.setMsgcode("0");
            ejr.setSuccess(true);
            return ejr;
//        }catch (Exception e){
//            ejr.setMsg("充值失败");
//            ejr.setMsgcode("200");
//            ejr.setSuccess(false);
//            return ejr;
//        }
    }



    /**
     *充值2.3(产品列表)
     */
    @ResponseBody
    @RequestMapping("/getVipSuiteMemberGiftProduct")
    @ApiOperation(value="充值2.3(项目列表)", notes="充值2.3(产品列表)")
    public ExtJsResult getVipSuiteMemberGiftProduct(HttpServletRequest request,long storeId,long memberId){
        //List<Service> serviceList= serviceDao.queryServiceForId(storeId);
        ExtJsResult VipSuiteList=productService.getProductListAppForMenber(memberId,storeId, request,new ListRequestBaseHandler() {
            @Override
            public List getByRequest(ListRequest listRequest) {
                return productDao.getPageList(listRequest.toMap(), null);
            }

            @Override
            public int getRequestListCount(ListRequest listRequest) {
                return productDao.getPageListCount(listRequest.toMap(), null);
            }
        });
        return  VipSuiteList;
    }




    /**
     *充值2.4(项目列表)
     */
    @ResponseBody
    @RequestMapping("/getVipSuiteMemberGiftService")
    @ApiOperation(value="充值2.4(项目列表)", notes="充值2.4(项目列表)")
    public ExtJsResult getVipSuiteMemberGiftService(HttpServletRequest request,long storeId,long memberId){
        //List<Service> serviceList= serviceDao.queryServiceForId(storeId);
        ExtJsResult VipSuiteList=vipSuiteService.getServiceListAppForMenber(memberId,storeId, request,new ListRequestBaseHandler() {
            @Override
            public List getByRequest(ListRequest listRequest) {
                return serviceDao.getPageList(listRequest.toMap(), null);
            }

            @Override
            public int getRequestListCount(ListRequest listRequest) {
                return serviceDao.getPageListCount(listRequest.toMap(), null);
            }
        });
        return  VipSuiteList;
    }




    /**
     * 页面：充值2.2
     * @param request
     * @param record_id
     * @param memberId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getVipSuiteItem")
    @ApiOperation(value="页面：充值2.2", notes="页面：充值2.2")
    public Result getVipSuiteItem(HttpServletRequest request,long record_id,long memberId){
        Result ejr = new Result();
        VipSuite vipSuite = vipSuiteDao.getById(record_id);
        JSONObject jsonObject = vipSuiteDao.getVipSuiteJSONObject(vipSuite,memberId);
        ejr.setData(jsonObject);
        ejr.setMsg("充值详情");
        ejr.setMsgcode("0");
        ejr.setSuccess(true);
        return ejr;
    }

    /**
     * 页面：充值2
     * @param request
     * @param storeId
     * @param memberId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getVipSuiteListAppMember")
    @ApiOperation(value="获取充值卡列表-app 会员的充值时展示的列表", notes="获取充值卡列表-app 会员的充值时展示的列表")
    public ExtJsResult getVipSuiteListAppMember(HttpServletRequest request,long storeId,long memberId){

        if(StringUtils.isEmpty(storeId+"")){
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
            storeId = stuff.getStoreId();
        }
        ExtJsResult VipSuiteList=vipSuiteService.getVipSuiteListIdSystemAppForMenber(memberId,storeId, request,new ListRequestBaseHandler() {
            @Override
            public List getByRequest(ListRequest listRequest) {
                return vipSuiteDao.getPageList(listRequest.toMap(), null);
            }

            @Override
            public int getRequestListCount(ListRequest listRequest) {
                return vipSuiteDao.getPageListCount(listRequest.toMap(), null);
            }
        });

        return VipSuiteList;
    }

}
