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
        jsonObject.put("productPictures",pictures.getPicUrl());

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
                String serviceId =  (String) map.get("recordId");
                String serviceName =  (String) map.get("serviceName");
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
            jsonObject.put("roomId",reservation.getRoomId());
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
     * 获取系列产品列表
     */
    @ResponseBody
    @RequestMapping("queryProductSeries")
    @ApiOperation(value = "获取系列产品列表", notes = "获取系列产品列表")
    public Result queryProductSeries(int page, HttpServletRequest request){
        Result result=new Result();
        try {
            String filterExpr   = request.getParameter("filterExpr");
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
            result.setTotal(productSeriesDao.getSeriesForUserPc(stuff.getStoreId(),filterExpr).size());
            PageHelper.startPage(page, 10);
            List<ProductSeries> seriesList= productSeriesDao.getSeriesForUserPc(stuff.getStoreId(),filterExpr);
            JSONArray jsonArray  = new JSONArray();

            for(ProductSeries ps:seriesList){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("seriesName",ps.getSeriesName());
                jsonObject.put("recordId",ps.getRecordId());
                List<Product> list=productDao.getProdectForCondition(stuff.getStoreId(),null,ps.getRecordId());
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


}
