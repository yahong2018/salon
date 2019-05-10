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
@Controller
@RequestMapping("/hy/score/memberProductKeep")
@Api(value = "MemberProductKeepController|寄存库控制层")
public class MemberProductKeepForScoreController {


    @Resource(name="memberProductKeepItemDao")
    private MemberProductKeepItemDao memberProductKeepItemDao;


    @Resource(name="memberProductRejectDao")
    private MemberProductRejectDao memberProductRejectDao;

    @Resource(name="memberProductRejectItemDao")
    private MemberProductRejectItemDao memberProductRejectItemDao;

    @Resource(name = "memberDao")
    private MemberDao memberDao;

    @Resource(name = "memberWalletDAO")
    private MemberWalletDAO MemberWalletDao;

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
        Result result = new Result();
        memberProductRejectDao.insert(memberProductReject);
        memberProductRejectItem.setMemberProductRejectId(memberProductReject.getRecordId());

        MemberProductKeepItem memberProductKeepItem = memberProductKeepItemDao.getById(memberProductRejectItem.getProductKeepItemId());
        byte type = memberProductKeepItem.getProductGetType();


        if(type==1){//赠送

        }
        long memberId =  memberProductReject.getMemberId();
        memberProductRejectItemDao.insert(memberProductRejectItem);
        Member member = memberDao.getById(memberId);

        //获取顾客钱包
        MemberWallet memberWallet=MemberWalletDao.getMemberWalletForMemberId(memberId);

        if(memberProductRejectItem.getTypeAmountReturn()==1){
//            member.setBalanceCash(member.getBalanceCash()+memberProductRejectItem.getAmountReject());//如果是以余额的方式，则要修改member的balance_cash字段

            memberWallet.setBalanceCash(memberWallet.getBalanceCash()+memberProductRejectItem.getAmountReject());//如果是以余额的方式，则要修改member的balance_cash字段
        }
        memberDao.update(member);

        result.setSuccess(true);
        result.setMsgcode("0");
        result.setMsg("领取成功");

        return  result;
    }
}
