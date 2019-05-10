package com.hy.score.controller;

import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.service.CardPurchaseService;
import com.hy.salon.basic.service.MemberGiftService;
import com.hy.salon.basic.service.ProductService;
import com.hy.salon.basic.service.VipSuiteService;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.web.ExtJsResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/hy/score/recharge")
@Api(value = "RechargeController| 充值业务金额相关")
public class RechargeFroScoreController {




    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;


    @Resource(name = "serviceDao")
    private ServiceDAO serviceDao;

    @Resource(name = "productDao")
    private ProductDao productDao;

    @Resource(name = "cardBalanceDao")
    private CardBalanceDao cardBalanceDao;

    @Resource(name="memberGiftDao")
    private MemberGiftDao memberGiftDao;
    @Resource(name="cardPurchaseDao")
    private CardPurchaseDao cardPurchaseDao;


    @Resource(name="businessStuffDao")
    private  BusinessStuffDao businessStuffDao;
    @Resource(name="memberDao")
    private MemberDao memberDao;


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
    public ExtJsResult addRecharge(CardBalance cardBalance, CardPurchase cardPurchase, List<MemberGift> memberGiftList, List<Long> stuffIdList){
        ExtJsResult  ejr = new ExtJsResult();
        try{

            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
            int transType = 0;
            if(cardBalance.getRecordId()!=null){//说明是已存在的卡户
                CardBalance cardBalanceOld= cardBalanceDao.getById(cardBalance.getRecordId());
                cardBalanceOld.setRemark(cardBalance.getRemark());
                cardPurchase.setRechargeType(0);
                cardBalanceOld.setBalance(cardBalance.getBalance()+cardBalanceOld.getBalance());
                cardBalanceOld.setBalanceTotal(cardBalanceOld.getBalanceTotal()+cardBalance.getBalanceTotal());
                cardBalanceDao.update(cardBalanceOld);//卡户表
                transType= 1;
            }else{
                cardPurchase.setRechargeType(1);
                cardBalanceDao.insert(cardBalance);//卡户表
            }
            cardPurchaseDao.insert(cardPurchase);//充值、购卡记录表
            double balance = 0;
            double cashCoupon = 0;
            double integral = 0;
            for(MemberGift mg:memberGiftList){
                mg.setRefTransId(cardPurchase.getRecordId());
                //mg.setTransType((byte)transType);
                mg.setTransType((byte)0);
                if(mg.getGiftType()==3){
                    //  balance = balance +mg.getQty();
                    if (mg.getGiftCashType() == 1) {
                        integral = integral+integral;
                    }else{
                        cashCoupon = cashCoupon +mg.getQty();
                    }

                }else if(mg.getGiftType()==0){
                    Service service =  serviceDao.getById(mg.getGitId());//项目表
                    balance = balance + service.getPrice();
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
                    memberProductKeepItem.setQtyPurchased((double)1);
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

            for(Long stuffId:stuffIdList){
                BusinessStuff businessStuff  = new BusinessStuff();
                businessStuff.setStuffId(stuffId);
                businessStuff.setTransType((byte)0);
                businessStuff.setRefTransId(cardPurchase.getRecordId());
                businessStuffDao.insert(businessStuff);//关联员工
            }

            Member member =  memberDao.getById(cardBalance.getMemberId());
            //获取钱包
            MemberWallet memberWallet=MemberWalletDao.getMemberWalletForMemberId(cardBalance.getMemberId());
            memberWallet.setBalanceTotal(memberWallet.getBalanceTotal()+balance);
            memberWallet.setDebt(memberWallet.getDebt()+cardPurchase.getAmountDebit());
            memberWallet.setAmountCharge(memberWallet.getAmountCharge()+cardPurchase.getAmount());
            memberWallet.setCashCoupon(cashCoupon);
            MemberWalletDao.insert(memberWallet);

//            member.setBalanceTotal(member.getBalanceTotal()+balance);
//            member.setDebt(member.getDebt()+cardPurchase.getAmountDebit());
//            member.setAmountCharge(member.getAmountCharge()+cardPurchase.getAmount());
//       /*if(cardBalance.getRecordId()!=null) {//说明是已存在的卡户
//           member.setBalanceCash(member.getBalanceCash()+cardBalance.getBalance());//余额充值
//       }*/
//            member.setCashCoupon(cashCoupon);
//            memberDao.update(member);//会员表
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
            ejr.setMsg("充值成功");
            ejr.setMsgcode("0");
            ejr.setSuccess(true);
            return ejr;
        }catch (Exception e){
            ejr.setMsg("充值失败");
            ejr.setMsgcode("200");
            ejr.setSuccess(false);
            return ejr;
        }
    }

}
