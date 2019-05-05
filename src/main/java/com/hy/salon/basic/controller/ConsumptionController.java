package com.hy.salon.basic.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hy/basic/consumption")
@Api(value = "ConsumptionController| 消费")
public class ConsumptionController {
    @Resource(name = "reservationDao")
    private ReservationDao reservationDao;
    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;
    @Resource(name = "stuffDao")
    private StuffDao stuffDao;
    @Resource(name = "productSeriesDao")
    private ProductSeriesDAO productSeriesDao;
    @Resource(name = "productDao")
    private ProductDao productDao;
    @Resource(name = "productPropertyDAO")
    private ProductPropertyDAO productPropertyDAO;

    @Resource(name = "picturesDao")
    private PicturesDAO picturesDao;

    @Resource(name="stampProgramDao")
    private  StampProgramDao stampProgramDao;

    @Resource(name = "cardBalanceDao")
    private CardBalanceDao cardBalanceDao;
    @Resource(name = "serviceDao")
    private ServiceDAO serviceDao;
    @Resource(name="paymentDao")
    private PaymentDao paymentDao;
    @Resource(name = "memberDao")
    private MemberDao memberDao;
    @Resource(name = "serviceSeriesDao")
    private ServiceSeriesDAO serviceSeriesDao;
    @Resource(name = "storeRoomDao")
    private StoreRoomDao storeRoomDao;
    @Resource(name = "ServiceSuiteDao")
    private ServiceSuiteDAO serviceSuiteDao;

    @Resource(name = "vipSuiteDao")
    private VipSuiteDAO vipSuiteDao;

    @Resource(name = "vipSuiteItemDao")
    private VipSuiteItemDAO vipSuiteItemDao;
    @Resource(name="arrearagesRecordDao")
    private ArrearagesRecordDao arrearagesRecordDao;

    @Resource(name="memberProductKeepDao")
    private MemberProductKeepDao memberProductKeepDao;

    @Resource(name="memberProductKeepItemDao")
    private MemberProductKeepItemDao memberProductKeepItemDao;


    /**
     * 划卡记录PC
     */
    @ResponseBody
    @RequestMapping("/getPaymentList")
    @ApiOperation(value="划卡记录PC", notes="划卡记录PC")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "page", value = "页数", required = true, dataType = "int")
    })
    public Result getPaymentList(int page,Long memberId,String days,Long storeId){
        if(storeId==null){
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff2 = stuffDao.getStuffForUser(user.getRecordId());
            storeId = stuff2.getStoreId();
        }

        Result result = paymentDao.getPaymentList(page,memberId,days,storeId);
        return  result;
    }

    /**
     * 消费结算
     */
    @ResponseBody
    @RequestMapping("/addConsumption")
    @ApiOperation(value="消费结算", notes="消费结算")
    @ApiImplicitParams({
            /*card_balance  卡户表*/
            @ApiImplicitParam(paramType="query", name = "recordId", value = "卡户余额表id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "memberId", value = "会员id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "cardId", value = "卡id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "cardType", value = "卡类型", required = true, dataType = "Byte"),
            @ApiImplicitParam(paramType="query", name = "balance", value = "卡户余额/剩余  次数(次卡、套卡次数)", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "balanceTotal", value = "次卡、套卡次数总次数", required = true, dataType = "double"),
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
            @ApiImplicitParam(paramType="query", name = "methodPayed", value = "支付方式:0.微信  1.支付宝  2.银行卡  3.现金  10 账户余额  11 充值卡余额", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "remark", value = "备注", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "memberSignature", value = "客户签名(系统照片ID)", required = true, dataType = "long"),
            /*business_stuff 关联员工表*/
            @ApiImplicitParam(paramType="query", name = "stuffId", value = "员工id", required = true, dataType = "Long"),

            @ApiImplicitParam(paramType="query", name = "cardBalanceId", value = "会员卡户充值卡id", required = true, dataType = "long"),
            @ApiImplicitParam(paramType="query", name = "qty_purchased", value = "购买产品数量", required = true, dataType = "double"),
    })
    public Result addConsumption(CardBalance cardBalance, CardPurchase cardPurchase, List<Long> stuffIdList,Long cardBalanceId,double qty_purchased) {
        Result result= new Result();
        Map parameter = new HashMap();
        parameter.put("memberId",cardBalance.getMemberId());
        parameter.put("cardId",cardBalance.getCardId());
        String rwhere="card_id=#{cardId} and member_id=#{memberId}";
        CardBalance cardBalanceOld = cardBalanceDao.getOne(rwhere,parameter);
        if(cardBalanceOld!=null){//说明是已存在的卡户
            //CardBalance cardBalanceOld= cardBalanceDao.getById(cardBalance.getRecordId());
            cardBalanceOld.setRemark(cardBalance.getRemark());
            cardPurchase.setRechargeType(0);
            cardBalanceOld.setBalance(cardBalance.getBalance()+cardBalanceOld.getBalance());
            cardBalanceOld.setBalanceTotal((byte)(cardBalanceOld.getBalanceTotal()+cardBalance.getBalanceTotal()));
            cardBalanceDao.update(cardBalanceOld);//卡户表
        }else{
            cardPurchase.setRechargeType(1);
            cardBalanceDao.insert(cardBalance);//卡户表
        }

        Member member =  memberDao.getById(cardBalance.getMemberId());

        byte  method_payed  = cardPurchase.getMethodPayed();// 10 账户余额  11 充值卡余额
        byte  cardType  = cardBalance.getCardType();//卡类型: 1.套卡  2.次卡、3产品
        long cardId = cardPurchase.getCardId();
        double balance = 0;
        double consumptionBalance = 0;
        if(cardType==1){
            ServiceSuite serviceSuite = serviceSuiteDao.getById(cardId);
            balance = serviceSuite.getPrice();
        }else if(cardType ==2){
            Service service =   serviceDao.getById(cardId);
            balance  = service.getPrice();
        }else if(cardType == 3){//产品
            Product product =  productDao.getById(cardId);
            balance  = product.getPrice();
            MemberProductKeep memberProductKeep = new MemberProductKeep();
            memberProductKeep.setMemberId(cardBalance.getMemberId());
            memberProductKeep.setMemberSignature(cardPurchase.getMemberSignature());
            memberProductKeep.setRemark(cardPurchase.getRemark());
            memberProductKeepDao.insert(memberProductKeep);
            MemberProductKeepItem memberProductKeepItem = new MemberProductKeepItem();

            memberProductKeepItem.setMemberProductKeepId(memberProductKeep.getRecordId());
            memberProductKeepItem.setProductId(cardId);
            memberProductKeepItem.setPrice(balance);
            memberProductKeepItem.setQtyPurchased(qty_purchased);
            memberProductKeepItem.setAmount(qty_purchased*balance);
            memberProductKeepItem.setQtyReceived((double)0);
            memberProductKeepItem.setProductGetType((byte) 0);
            memberProductKeepItemDao.insert(memberProductKeepItem);
        }
        if(method_payed==10){//减掉menber 表的现金余额
            member.setBalanceCash(member.getBalanceCash() - balance);//现金余额减少
            member.setBalanceTotal(member.getBalanceTotal()+balance);//总余额增加
            consumptionBalance = balance;
        }else if(method_payed==11){//11 充值卡余额
           /* Map parameterV = new HashMap();
            parameter.put("memberId",cardBalance.getMemberId());
            parameter.put("cardId",vipSuiteId);
            String rwhereV="card_id=#{cardId} and member_id=#{memberId}";*/
            CardBalance cardBalanceV = cardBalanceDao.getById(cardBalanceId);//获取充值卡卡户
            // 0.单次折扣  1.套卡折扣  2.产品折扣
            Map parameterVI = new HashMap();
            if(cardType==1){
                parameter.put("record_type",1);
            }else if(cardType ==2){
                parameter.put("record_type",2);
            }else if(cardType == 3){
                parameter.put("record_type",3);
            }
            parameter.put("vip_suite_id",cardBalanceV.getCardId());
            String rwhereVI="vip_suite_id=#{vip_suite_id} and record_type=#{record_type}";
            VipSuiteItem vipSuiteItem = vipSuiteItemDao.getOne(rwhereVI,parameterVI);//获取充值卡折扣

            Byte discount = vipSuiteItem.getDiscount();
            double temp = balance*discount;//折扣后的价格
            consumptionBalance = temp;//消费掉的钱
            cardBalanceV.setBalance(cardBalanceV.getBalance() - temp);
            member.setBalanceTotal(member.getBalanceTotal()+ balance- temp);//总余额增加消费购买的卡的金额减掉用充值卡消费的金额
        }

        member.setDebt(member.getDebt()+cardPurchase.getAmountDebit());//账户欠款
        member.setAmountCharge(member.getAmountCharge()+cardPurchase.getAmount());//总充值
        member.setAmountConsumer(member.getAmountConsumer()+consumptionBalance);//总消费
        if(cardPurchase.getAmountDebit()!=null||cardPurchase.getAmountDebit()!=0){
            ArrearagesRecord arrearagesRecord = new ArrearagesRecord();
            arrearagesRecord.setRefTransId(cardPurchase.getRecordId());
            arrearagesRecord.setMemberId(cardBalance.getMemberId());
            arrearagesRecord.setArrearagesDate(new Date());
            arrearagesRecord.setArrearagesType((byte)0);
            arrearagesRecord.setAmountOfRealPay(cardPurchase.getAmount());
            arrearagesRecord.setAmountPayable(cardPurchase.getAmountPayed());
            arrearagesRecord.setIsPaidOff((byte)1);
            arrearagesRecordDao.insert(arrearagesRecord);//欠款表
        }
        memberDao.update(member);//会员表
        result.setSuccess(true);
        result.setMsg("消费成功");
        result.setMsgcode("0");
        return  result;
    }
    /**
     * 单次消费页面
     */
    @ResponseBody
    @RequestMapping(value="getService")
    @ApiOperation(value="单次消费页面", notes="单次消费页面")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "serviceId", value = "项目id", required = true, dataType = "Long")
    })
    public Result getService(Long serviceId){
        Service service =   serviceDao.getById(serviceId);
        Result result= new Result();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("recordId",service.getRecordId());
        jsonObject.put("serviceName",service.getServiceName());
        jsonObject.put("pricePerTime",service.getPeriodPerTime());//原价
        jsonObject.put("price",service.getPrice());//优惠价
        jsonObject.put("discount",(service.getPeriodPerTime()/service.getPrice())*10);//优惠价
        List<Pictures> piclist= picturesDao.getPicturesForCondition(service.getRecordId(),new Byte("2"),new Byte("0"));
        if(null != piclist && piclist.size()!=0){
            jsonObject.put("picUrl",piclist.get(0).getPicUrl());//优惠价();
        }
        result.setData(jsonObject);
        result.setSuccess(true);
        result.setMsg("获取成功");
        result.setMsgcode("0");


        return  result;
    }
    /**
     * 获取单次项目列表
     */
    @ResponseBody
    @RequestMapping(value="getSeriesAndService")
    @ApiOperation(value="获取单次项目列表", notes="获取单次项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "storeId", value = "店铺Id", required = true, dataType = "Long"),
    })
    public Result getSeriesAndService(Long storeId){
        Result r= new Result();
        if(storeId==null){
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
            storeId = stuff.getStoreId();
        }

        List<ServiceSeries> serList=serviceSeriesDao.getServiceSeriesForstoreId(storeId);
        JSONArray jsonArr2=new JSONArray();
        for(ServiceSeries s:serList){

            JSONObject jsonObj=new JSONObject();
            jsonObj.put("seriesName",s.getSeriesName());
            List<Service> serviceList=serviceDao.queryServiceForServiceId(s.getRecordId(),storeId);
            for(Service ss:serviceList){
                List<Pictures> piclist= picturesDao.getPicturesForCondition(ss.getRecordId(),new Byte("2"),new Byte("0"));
                if(null != piclist && piclist.size()!=0){
                    ss.setPicUrl(piclist.get(0).getPicUrl());
                }

            }
            jsonObj.put("serviceList",serviceList);
            jsonArr2.add(jsonObj);
        }
        r.setMsgcode("0");
        r.setMsg("获取成功");
        r.setSuccess(true);
        r.setData(jsonArr2);
        return  r;
    }

    /*    member_id                    bigint                                          not null,  -- 会员Id
    payment_type                 tinyint                                         not null,  -- 0.一般支付   1.偿还欠款

    remark                       varchar(500)                                    null,       -- 备注
    member_signature             bigint                                          not null,   -- 客户签名(系统照片ID)*/
/* payment_id                   bigint                                          not null,  -- 支付Id
  card_balance_id              bigint                                          not null,  -- 卡户Id:充值卡/次卡/套卡项目/优惠券的编号  -1.欠款
  merchandise_id               bigint                                          not null,  -- 商品Id:服务/产品的编号   -1.欠款
  merchandise_type             tinyint                                         not null,  -- 商品类型: 0.服务  1.产品  -1.欠款

  qty                          double(8,2)                                     not null,  -- 数量： 还欠款的话，数量为 1
  price                        double(10,2)                                    not null,  -- 单价： 还欠款的话，单价=偿还金额
  amount                       double(10,2)                                    not null,
                      -- 金额：一般支付 注意扣除card_balance或者服务明细的金额或者次数
                      --      偿还欠款：注意修改账户欠款余额*/
    /**
     * 划卡
     */
    @ResponseBody
    @RequestMapping(value = "strokeCard")
    @ApiOperation(value="划卡", notes="划卡")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "memberId", value = "会员id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "paymentType", value = "0.一般支付   1.偿还欠款", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "remark", value = "备注", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "memberSignature", value = "客户签名(系统照片ID)", required = true, dataType = "Long"),

            @ApiImplicitParam(paramType="query", name = "cardBalanceId", value = "卡户Id:充值卡/次卡/套卡项目/优惠券的编号  -1.欠款", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "merchandiseId", value = "商品类型: 0.服务  1.产品  -1.欠款", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "merchandiseType", value = "商品类型: 0.服务  1.产品  -1.欠款", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "paymentType", value = "商品类型: 0.服务  1.产品  -1.欠款", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "qty", value = "数量： 还欠款的话，数量为 1", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "price", value = "单价： 还欠款的话，单价=偿还金额", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "amount", value = "金额：一般支付 注意扣除card_balance或者服务明细的金额或者次数||偿还欠款：注意修改账户欠款余额", required = true, dataType = "double")
    })
    public Result strokeCard(Payment payment,PaymentItem paymentItem){
        Result result=new Result();
        paymentDao.insert(payment);
        paymentItem.setPaymentId(payment.getRecordId());
        Member member =  memberDao.getById(payment.getMemberId());
        CardBalance cardBalance = cardBalanceDao.getById(paymentItem.getCardBalanceId());

        if(paymentItem.getPaymentType()==0){//次数
            cardBalance.setBalance(cardBalance.getBalance() -1);
            member.setBalanceTotal(member.getBalanceTotal() - paymentItem.getPrice()*paymentItem.getQty());
        }else{//项目券
         long id =    paymentItem.getCardBalanceId();
         StampProgram stampProgram = stampProgramDao.getById(id);
            stampProgram.setIsUsed((byte)0);
            stampProgramDao.update(stampProgram);
        }
        memberDao.update(member);
        cardBalanceDao.update(cardBalance);
        result.setMsgcode("0");
        result.setSuccess(true);
        return  result;
    }

    /**
     * 划卡确认
     */
    @ResponseBody
    @RequestMapping(value = "getCardBalance",method = RequestMethod.GET)
    @ApiOperation(value="划卡确认", notes="划卡确认")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "serviceId", value = "卡id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "memberId", value = "会员id", required = true, dataType = "Long")
    })
    public Result getCardBalance(Long serviceId,Long memberId){
        Result result=new Result();
       Service service =  serviceDao.getById(serviceId);

        Map parameter = new HashMap();
        parameter.put("memberId",memberId);
        parameter.put("cardId",serviceId);
        String rwhere="card_id=#{cardId} and member_id=#{memberId}";
        CardBalance cardBalance = cardBalanceDao.getOne(rwhere,parameter);
        Map parameterP = new HashMap();
        parameterP.put("masterDataId", serviceId);
        parameterP.put("recordType",5);
        parameterP.put("picType",0);
        String rwhereP="master_data_id=#{masterDataId} and record_type = #{recordType} and pic_type=#{picType}";
        Pictures pictures = picturesDao.getOne(rwhereP,parameterP);


        JSONObject jsonObject  = new JSONObject();
        jsonObject.put("productPictures",pictures==null?"":pictures.getPicUrl());

        jsonObject.put("serviceName",service.getServiceName());

        jsonObject.put("pricePerTime",service.getPeriodPerTime());
        jsonObject.put("balance",cardBalance.getBalance());
        jsonObject.put("balanceTotal",cardBalance.getBalanceTotal());

        jsonObject.put("cardBalanceId",cardBalance.getRecordId());

        jsonObject.put("returnVisit",service.getReturnVisit());
        result.setMsgcode("0");
        result.setSuccess(true);
        result.setData(jsonObject);
        return  result;
    }



    /**
     * 预约详情
     */
    @ResponseBody
    @RequestMapping(value = "getOneStuffItem",method = RequestMethod.GET)
    @ApiOperation(value="查询当天个人一个预约具体详情--员工（具体到几点，什么项目）", notes="查询当天个人一个预约具体详情--员工（具体到几点，什么项目）")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "预约id", required = true, dataType = "Long")
    })
    public Result getOneStuffItem(Long recordId){
        Result result=new Result();
        try {
            Map rMap = new HashMap();
            String rwhere="record_id=#{record_id} ";
            rMap.put("record_id", recordId);
            Reservation reservation = reservationDao.getOne(rwhere, rMap);
            List<Map>  mapService =  reservationDao.getServiceByReservationId(reservation.getRecordId());
            JSONObject jsonObject  = new JSONObject();
            String serviceIds = "";
            JSONArray jsonArray = new JSONArray();
            for(Map map :mapService){
                JSONObject jsonObject1 = new JSONObject();
                long serviceId =  (long) map.get("record_id");
                String serviceName =  (String) map.get("service_name");
                //serviceIds=serviceIds+serviceId+",";
                Map parameterP = new HashMap();
                parameterP.put("memberId", reservation.getMemberId());
                parameterP.put("cardId",serviceId);
                String rwhereP="card_id=#{cardId} and member_id=#{memberId}";
                CardBalance cardBalance = cardBalanceDao.getOne(rwhereP,parameterP);
                jsonObject1.put("balance",cardBalance.getBalance());
                jsonObject1.put("balanceTotal",cardBalance.getBalanceTotal());
                jsonObject1.put("serviceName",serviceName);
                jsonObject1.put("serviceId",serviceId);
                jsonArray.add(jsonObject1);
            }



            jsonObject.put("service",jsonArray);//预约项目

            Map parameterP = new HashMap();
            parameterP.put("masterDataId", reservation.getMemberId());
            parameterP.put("recordType",1);
            parameterP.put("picType",0);
            String rwhereP="master_data_id=#{masterDataId} and record_type = #{recordType} and pic_type=#{picType}";
            Pictures pictures = picturesDao.getOne(rwhereP,parameterP);
            jsonObject.put("picturesUrl",pictures==null?"":pictures.getPicUrl());
            jsonObject.put("memberId",reservation.getMemberId());
            jsonObject.put("stuffId",reservation.getStuffId());
            StoreRoom storeRoom = storeRoomDao.getById(reservation.getRoomId());
            jsonObject.put("roomId",reservation.getRoomId());
            jsonObject.put("roomName",storeRoom.getRoomName());
            jsonObject.put("timeStart",reservation.getTimeStart());
            jsonObject.put("timeEnd",reservation.getTimeEnd());
            jsonObject.put("recordStatus",reservation.getRecordStatus());
            jsonObject.put("remark",reservation.getRemark());
            jsonObject.put("date_",reservation.getDate_());
            jsonObject.put("duration",reservation.getDuration());
            jsonObject.put("memberSourc",reservation.getMemberSourc());
            jsonObject.put("serviceId",serviceIds);
            jsonObject.put("serviceNum",mapService.size());
            result.setMsgcode("0");
            result.setSuccess(true);
            result.setData(jsonObject);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode("200");
        }
        return result;
    }

    /**
     * 服务状态改变
     * @param reservationId
     * @param recordStatus
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateReservationById",method = RequestMethod.GET)
    @ApiOperation(value = "服务状态改变", notes = "服务状态改变")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "reservationId", value = "预约id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "recordStatus", value = "预约状态", required = true, dataType = "int")

    })
    public Result getReservationById(Long reservationId,int recordStatus){
       Reservation reservation =  reservationDao.getById(reservationId);
        reservation.setRecordStatus(recordStatus);
        reservationDao.update(reservation);
        Result result  = new Result();
        result.setSuccess(true);
        result.setMsg("更新成功");
        result.setMsgcode("0");
       return  result;
    }
    /**
     * 获取赠送项目券
     */
    @ResponseBody
    @RequestMapping("queryProjectTicket")
    @ApiOperation(value = "获取赠送项目券", notes = "获取赠送项目券")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "memberId", value = "会员id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "page", value = "页数", required = true, dataType = "int")

    })
    public Result queryProjectTicket(int page, HttpServletRequest request,Long memberId){
        Result result=new Result();
        String  where = "member_id = #{memberId}";
        Map mapParament = new HashMap();
        mapParament.put("memberId",memberId);
        List<StampProgram> listStampProgram= stampProgramDao.getByWhere(where,mapParament);
        result.setData(listStampProgram);
        result.setSuccess(true);
        result.setMsgcode(StatusUtil.OK);
        result.setMsg("获取成功");
        return  result;
    }

    /**
     * 产品消费页面
     */
    @ResponseBody
    @RequestMapping("queryProduct")
    @ApiOperation(value = "产品消费页面", notes = "产品消费页面")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "productId", value = "产品id", required = true, dataType = "Long")

    })
    public Result queryProduct(Long productId, HttpServletRequest request){
        Result result=new Result();
       Product product =  productDao.getById(productId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("productName",product.getProductName());
        jsonObject.put("priceMarket",product.getPriceMarket());
        jsonObject.put("price",product.getPrice());
        List<Pictures> pic=picturesDao.getPicturesForCondition(product.getRecordId(),new Byte("5"),new Byte("0"));
        if(null!=pic && pic.size()!=0){
            jsonObject.put("picUrl",pic.get(0).getPicUrl());
        }
        result.setData(jsonObject);
        result.setSuccess(true);
        result.setMsgcode(StatusUtil.OK);
        result.setMsg("获取成功");
        return  result;
    }

    /**
     * 选择产品页面
     * 获取系列产品列表
     */
    @ResponseBody
    @RequestMapping("queryProductSeries")
    @ApiOperation(value = "获取系列产品列表", notes = "获取系列产品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "storeId", value = "店铺id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "productName", value = "产品名称", required = true, dataType = "String"),

    })
    public Result queryProductSeries(Long storeId,int page, HttpServletRequest request){
        Result result=new Result();
        try {

            if(storeId==null){
                SystemUser user = authenticateService.getCurrentLogin();
                Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
                storeId = stuff.getStoreId();
            }

            result.setTotal(productSeriesDao.getSeriesForUserPc(storeId,null).size());
            PageHelper.startPage(page, 10);
            List<ProductSeries> seriesList= productSeriesDao.getSeriesForUserPc(storeId,null);
            JSONArray jsonArray  = new JSONArray();

            for(ProductSeries ps:seriesList){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("seriesName",ps.getSeriesName());
                jsonObject.put("recordId",ps.getRecordId());
                String productName   = request.getParameter("productName");
                List<Product> list=productDao.getProdectForConsumption(storeId,productName,ps.getRecordId());
                if(list.size()!=0){
                    for(Product p:list){
                        List<Map<String,Object>> Property1= productPropertyDAO.getPropertyName(p.getRecordId(),new Byte("0"));
                        List<Map<String,Object>> Property2= productPropertyDAO.getPropertyName(p.getRecordId(),new Byte("1"));
                        if(null!=Property1 && Property1.size()!=0){
                            p.setSpecificationsName(Property1.get(0).get("propertyName").toString());
                        }

                        if(null!=Property2 && Property2.size()!=0){
                            p.setCompanyName(Property2.get(0).get("propertyName").toString());
                        }
                        List<Pictures> pic=picturesDao.getPicturesForCondition(p.getRecordId(),new Byte("5"),new Byte("0"));
                        if(null!=pic && pic.size()!=0){
                            p.setPicUrl(pic.get(0).getPicUrl());
                        }
                    }
                }
                jsonObject.put("listProduct",list);
                jsonArray.add(jsonObject);
            }


            result.setData(jsonArray);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
            result.setMsg("获取成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
            result.setMsg("获取失败");
        }
        return result;
    }
    /**
     * 套卡消费
     */
    @ResponseBody
    @RequestMapping("/queryServiceSuite")
    @ApiOperation(value="套卡消费", notes="套卡消费")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "serviceSuiteId", value = "套卡id", required = true, dataType = "Long")
    })
    public Result queryServiceSuite(HttpServletRequest request,Long serviceSuiteId){
        Result result= new Result();
        ServiceSuite serviceSuite = serviceSuiteDao.getById(serviceSuiteId);
        List<Pictures> pic=picturesDao.getPicturesForCondition(serviceSuite.getRecordId(),new Byte("3"),new Byte("0"));
        if(null!=pic && pic.size()!=0){
            serviceSuite.setPicUrl(pic.get(0).getPicUrl());
        }
        result.setData(serviceSuite);
        result.setMsg("获取成功");
        result.setSuccess(true);
        result.setMsgcode(StatusUtil.OK);
        return  result;
    }
    /**
     * 选择套卡
     */
    @ResponseBody
    @RequestMapping("/queryServiceSuiteList")
    @ApiOperation(value="获取套卡列表", notes="获取套卡")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "storeId", value = "店铺id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "serviceSuiteName", value = "套卡名称", required = true, dataType = "String"),
    })
    public Result queryServiceSuiteList(HttpServletRequest request,Long storeId,int page){
        Result r= new Result();
        String serviceSuiteName = request.getParameter("serviceSuiteName");
        try {
            if(null == storeId ){
                SystemUser user = authenticateService.getCurrentLogin();
                Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
                storeId=stuff.getStoreId();
            }
            r.setTotal(serviceSuiteDao.querySuitItemForCreateIdConsumption(storeId,serviceSuiteName).size());
            PageHelper.startPage(page, 10);
            List<ServiceSuite> suiteList= serviceSuiteDao.querySuitItemForCreateIdConsumption(storeId,serviceSuiteName);
            if(null != suiteList && suiteList.size()!=0){
                for(ServiceSuite s:suiteList){
                    if(new Date().getTime()>s.getTimeExpired().getTime()){
                        s.setIsExpired("1");
                    }else{
                        s.setIsExpired("0");
                    }
                    List<Pictures> pic=picturesDao.getPicturesForCondition(s.getRecordId(),new Byte("3"),new Byte("0"));
                    if(null!=pic && pic.size()!=0){
                        s.setPicUrl(pic.get(0).getPicUrl());
                    }
                }
            }
            r.setData(suiteList);
            r.setMsg("获取成功");
            r.setSuccess(true);
            r.setMsgcode(StatusUtil.OK);
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
        }
        return r;
    }


    /**
     * 获取个人充值卡列表
     */
    @ResponseBody
    @RequestMapping("/getVipSuiteList")
    @ApiOperation(value="获取个人充值卡列表", notes="获取个人充值卡列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "memberId", value = "会员id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "card_type", value = "卡户类型", required = true, dataType = "Long"),
    })
    public Result getVipSuiteList(HttpServletRequest request,Long memberId,int card_type){
        Result r= new Result();
        String  where = "member_id = #{memberId} and card_type =#{card_type}";
        Map mapParament = new HashMap();
        mapParament.put("memberId",memberId);
        mapParament.put("card_type",card_type);
        List<CardBalance> cardBalancesList  = cardBalanceDao.getByWhere(where,mapParament);
        JSONArray jsonArr=new JSONArray();
        for(CardBalance cardBalance:cardBalancesList){
            JSONObject jsonObj=new JSONObject();

            VipSuite vipSuit= vipSuiteDao.getVipSuiteForId(cardBalance.getRecordId());
            jsonObj.put("buyTime",cardBalance.getCreateDate());
            jsonObj.put("cardBalanceId",cardBalance.getRecordId());
            jsonObj.put("vipSuitName",vipSuit.getSuiteName());
            jsonObj.put("balance",cardBalance.getBalance());
            jsonObj.put("singleDiscount",null);// 0.单次折扣
            jsonObj.put("courseDiscount",null);// 1.疗程折扣
            jsonObj.put("productDiscount",null);//2.产品折扣
           // 0.单次折扣  1.疗程折扣  2.产品折扣
            List<Map<String,String>> service1=vipSuiteDao.getServiceSeriesForVip(cardBalance.getCardId(),new Long(0));
            if(service1.size() != 0){
                jsonObj.put("singleDiscount",service1);
            }
            List<Map<String,String>> service2=vipSuiteDao.getServiceSeriesForVip(cardBalance.getCardId(),new Long(1));
            if(service2.size() != 0){
                jsonObj.put("courseDiscount",service2);
            }
            List<Map<String,String>> service3=vipSuiteDao.getServiceSeriesForVip(cardBalance.getCardId(),new Long(2));
            if(service3.size() != 0){
                jsonObj.put("productDiscount",service3);
            }
            jsonArr.add(jsonObj);
        }

        r.setData(jsonArr);
        r.setMsg("获取成功");
        r.setSuccess(true);
        r.setMsgcode(StatusUtil.OK);
        return  r;
    }
}
