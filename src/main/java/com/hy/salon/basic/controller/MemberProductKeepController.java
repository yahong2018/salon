package com.hy.salon.basic.controller;

import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/hy/basic/memberProductKeep")
@Api(value = "MemberProductKeepController|寄存库控制层")
public class MemberProductKeepController {


    @Resource(name="memberProductKeepDao")
    private MemberProductKeepDao memberProductKeepDao;

    @Resource(name="memberProductKeepItemDao")
    private MemberProductKeepItemDao memberProductKeepItemDao;


    @Resource(name="memberProductRejectDao")
    private MemberProductRejectDao memberProductRejectDao;

    @Resource(name="memberProductRejectItemDao")
    private MemberProductRejectItemDao memberProductRejectItemDao;

    @Resource(name="memberProductGetRecordDao")
    private MemberProductGetRecordDao memberProductGetRecordDao;
    @Resource(name = "memberDao")
    private MemberDao memberDao;
    /**
     * 获取会员寄存库产品列表
     */
    @ResponseBody
    @RequestMapping("getMemberProductKeepList")
    @ApiOperation(value="获取会员寄存库产品列表", notes="获取会员寄存库产品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "memberId", value = "会员id", required = true, dataType = "Long")
    })
    public Result getMemberProductKeepList(Long memberId) {
        Result result =    memberProductKeepDao.getMemberProductKeepList(memberId);
        return  result;
    }

    /**
     * 领取产品
     */
    @ResponseBody
    @RequestMapping("receiveMemberProductKeep")
    @ApiOperation(value="获取会员寄存库产品列表", notes="获取会员寄存库产品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "memberProductKeepItem", value = "产品寄存明细id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "qty", value = "当前领取数量", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "remark", value = "备注", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "memberSignature", value = "客户签名(系统照片ID)", required = true, dataType = "Long"),
    })
    public Result receiveMemberProductKeep(MemberProductGetRecord memberProductGetRecord) {
        Result result = new Result();
       long  memberProductKeepItemId = memberProductGetRecord.getMemberProductKeepItem();
        MemberProductKeepItem memberProductKeepItem = memberProductKeepItemDao.getById(memberProductKeepItemId);//获取寄存产品明细

       double QtyPurchased =  memberProductKeepItem.getQtyPurchased();
       double QtyReceived = memberProductKeepItem.getQtyReceived();
        double temp = QtyPurchased - QtyReceived;

        double qty  =  memberProductGetRecord.getQty();
        if(temp>=qty){//说明可以领取
            memberProductKeepItem.setQtyReceived(memberProductKeepItem.getQtyReceived()+qty);
        }else{//不能领取
            result.setSuccess(false);
            result.setMsgcode("200");
            result.setMsg("不能领取");
        }
        memberProductGetRecordDao.insert(memberProductGetRecord);

        memberProductKeepItemDao.update(memberProductKeepItem);

        result.setSuccess(true);
        result.setMsgcode("0");
        result.setMsg("领取成功");

        return  result;

    }

/*    member_id                    bigint                                          not null,

    remark                       varchar(500)                                    null,       -- 备注
    member_signature             bigint                                          not null,   -- 客户签名(系统照片ID)*/

/*    member_product_reject_id     bigint                                          not null,  -- 产品退款Id
    product_keep_item_id         bigint                                          not null,  -- c

    reject_type                  tinyint                                         not null,  -- 0.未领取退款  1.已领取退款
    qty_reject                   double(8,2)                                     not null,  -- 退还数量
    amount_reject                double(10,2)                                    not null,  -- 退还金额
    type_amount_return           tinyint                                         not null,
            -- 资金退还方式: 0.现金  1.余额
        --     如果是以余额的方式，则要修改member的balance_cash字段*/

    /**
     * 退款产品
     */
    @ResponseBody
    @RequestMapping("refundMemberProductKeep")
    @ApiOperation(value="退款产品", notes="退款产品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "memberId", value = "会员id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "remark", value = "备注", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "memberSignature", value = "客户签名", required = true, dataType = "Long"),

            @ApiImplicitParam(paramType="query", name = "rejectType", value = "0.未领取退款  1.已领取退款", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "qtyReject", value = "退还数量", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "productKeepItemId", value = "原购买记录Id(产品寄存明细表id)", required = true, dataType = "long"),
            @ApiImplicitParam(paramType="query", name = "amountReject", value = "退还金额", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "typeAmountReturn", value = "资金退还方式: 0.现金  1.余额\n" +
                    "        --     如果是以余额的方式，则要修改member的balance_cash字段*/", required = true, dataType = "byte"),

    })
    public Result refundMemberProductKeep(MemberProductReject memberProductReject, MemberProductRejectItem memberProductRejectItem){

        memberProductRejectDao.insert(memberProductReject);
        memberProductRejectItem.setMemberProductRejectId(memberProductReject.getRecordId());

        MemberProductKeepItem memberProductKeepItem = memberProductKeepItemDao.getById(memberProductRejectItem.getProductKeepItemId());
        byte type = memberProductKeepItem.getProductGetType();


        if(type==1){//赠送

        }
        long memberId =  memberProductReject.getMemberId();
        memberProductRejectItemDao.insert(memberProductRejectItem);
        Member member = memberDao.getById(memberId);
        if(memberProductRejectItem.getTypeAmountReturn()==1){
            member.setBalanceCash(member.getBalanceCash()+memberProductRejectItem.getAmountReject());//如果是以余额的方式，则要修改member的balance_cash字段
        }


        return  null;
    }

}
