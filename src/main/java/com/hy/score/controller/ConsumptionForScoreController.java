package com.hy.score.controller;

import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.service.AuthenticateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/hy/score/consumption")
@Api(value = "ConsumptionController| 消费")
public class ConsumptionForScoreController {


    @Resource(name = "productDao")
    private ProductDao productDao;


    @Resource(name = "cardBalanceDao")
    private CardBalanceDao cardBalanceDao;
    @Resource(name = "serviceDao")
    private ServiceDAO serviceDao;
    @Resource(name = "memberDao")
    private MemberDao memberDao;
    @Resource(name = "ServiceSuiteDao")
    private ServiceSuiteDAO serviceSuiteDao;

    @Resource(name = "vipSuiteItemDao")
    private VipSuiteItemDAO vipSuiteItemDao;
    @Resource(name="arrearagesRecordDao")
    private ArrearagesRecordDao arrearagesRecordDao;

    @Resource(name="memberProductKeepDao")
    private MemberProductKeepDao memberProductKeepDao;

    @Resource(name="memberProductKeepItemDao")
    private MemberProductKeepItemDao memberProductKeepItemDao;

    @Resource(name = "memberWalletDAO")
    private MemberWalletDAO MemberWalletDao;

    @Resource(name="stampProgramDao")
    private  StampProgramDao stampProgramDao;

    @Resource(name="paymentDao")
    private PaymentDao paymentDao;


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
    public Result addConsumption(CardBalance cardBalance, CardPurchase cardPurchase, List<Long> stuffIdList, Long cardBalanceId, double qty_purchased) {
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
            cardBalanceOld.setBalanceTotal(cardBalanceOld.getBalanceTotal()+cardBalance.getBalanceTotal());
            cardBalanceDao.update(cardBalanceOld);//卡户表
        }else{
            cardPurchase.setRechargeType(1);
            cardBalanceDao.insert(cardBalance);//卡户表
        }

        Member member =  memberDao.getById(cardBalance.getMemberId());
        //获取钱包
        MemberWallet memberWallet=MemberWalletDao.getMemberWalletForMemberId(cardBalance.getMemberId());

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
//            member.setBalanceCash(member.getBalanceCash() - balance);//现金余额减少
//            member.setBalanceTotal(member.getBalanceTotal()+balance);//总余额增加

            memberWallet.setBalanceCash(memberWallet.getBalanceCash() - balance);//现金余额减少
            memberWallet.setBalanceTotal(memberWallet.getBalanceTotal()+balance);//总余额增加

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

            Double discount = vipSuiteItem.getDiscount();
            double temp = balance*discount;//折扣后的价格
            consumptionBalance = temp;//消费掉的钱
            cardBalanceV.setBalance(cardBalanceV.getBalance() - temp);

//            member.setBalanceTotal(member.getBalanceTotal()+ balance- temp);//总余额增加消费购买的卡的金额减掉用充值卡消费的金额

            memberWallet.setBalanceTotal(memberWallet.getBalanceTotal()+ balance- temp);//总余额增加消费购买的卡的金额减掉用充值卡消费的金额
        }

//        member.setDebt(member.getDebt()+cardPurchase.getAmountDebit());//账户欠款
//        member.setAmountCharge(member.getAmountCharge()+cardPurchase.getAmount());//总充值
//        member.setAmountConsumer(member.getAmountConsumer()+consumptionBalance);//总消费


        memberWallet.setDebt(memberWallet.getDebt()+cardPurchase.getAmountDebit());//账户欠款
        memberWallet.setAmountCharge(memberWallet.getAmountCharge()+cardPurchase.getAmount());//总充值
        memberWallet.setAmountConsumer(memberWallet.getAmountConsumer()+consumptionBalance);//总消费

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
//        memberDao.update(member);//会员表
        result.setSuccess(true);
        result.setMsg("消费成功");
        result.setMsgcode("0");
        return  result;
    }


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
        //获取钱包
        MemberWallet memberWallet=MemberWalletDao.getMemberWalletForMemberId(payment.getMemberId());

        CardBalance cardBalance = cardBalanceDao.getById(paymentItem.getCardBalanceId());

        if(paymentItem.getPaymentType()==0){//次数
            cardBalance.setBalance(cardBalance.getBalance() -1);
//            member.setBalanceTotal(member.getBalanceTotal() - paymentItem.getPrice()*paymentItem.getQty());

            memberWallet.setBalanceTotal(memberWallet.getBalanceTotal() - paymentItem.getPrice()*paymentItem.getQty());
        }else{//项目券
            long id =    paymentItem.getCardBalanceId();
            StampProgram stampProgram = stampProgramDao.getById(id);
            stampProgram.setIsUsed((byte)0);
            stampProgramDao.update(stampProgram);
        }
        MemberWalletDao.update(memberWallet);
//        memberDao.update(member);
        cardBalanceDao.update(cardBalance);
        result.setMsgcode("0");
        result.setSuccess(true);
        return  result;
    }



}
