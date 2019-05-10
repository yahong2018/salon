package com.hy.score.controller;

import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.ArrearagesRecord;
import com.hy.salon.basic.entity.MemberWallet;
import com.hy.salon.basic.entity.RepaymentRecord;
import com.zhxh.core.web.ExtJsResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/hy/score/arrearagesRecord")
@Api(value = "ArrearagesRecordForSeoreController| 还款控制器")
public class ArrearagesRecordForScoreController {


    @Resource(name = "arrearagesRecordDao")
    private ArrearagesRecordDao arrearagesRecordDao;


    @Resource(name="repaymentRecordDao")
    private RepaymentRecordDao repaymentRecordDao;


    @Resource(name = "memberWalletDAO")
    private MemberWalletDAO MemberWalletDao;


    @ResponseBody
    @RequestMapping("/toArrears")
    @ApiOperation(value="还款", notes="还款")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "arrearagesRecordId", value = "欠款id", required = true, dataType = "long"),
            @ApiImplicitParam(paramType = "query", name = "amountDebit", value = "还款金额", required = true, dataType = "double"),
            @ApiImplicitParam(paramType = "query", name = "memberId", value = "会员id", required = true, dataType = "long"),

            @ApiImplicitParam(paramType = "query", name = "methodPayed", value = "还款方式", required = true, dataType = "byte"),
            @ApiImplicitParam(paramType = "query", name = "remark", value = "备注", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "memberSignature", value = "签名id", required = true, dataType = "long"),
    })
    public ExtJsResult toArrears(HttpServletRequest request, long arrearagesRecordId, long memberId, double amountDebit, byte methodPayed, String remark, long memberSignature){
//        Member member = memberDao.getById(memberId);
        //获取顾客钱包
        MemberWallet memberWallet=MemberWalletDao.getMemberWalletForMemberId(memberId);
        ArrearagesRecord arrearagesRecord = arrearagesRecordDao.getById(arrearagesRecordId);//要还款的记录
        double AmountDept = arrearagesRecord.getAmountDept();
        double temp =AmountDept -  amountDebit -arrearagesRecord.getReimbursement();
        RepaymentRecord repaymentRecord = new RepaymentRecord();
        repaymentRecord.setArrearagesRecord(arrearagesRecord.getRecordId());
        repaymentRecord.setReimbursementDate(new Date());
        repaymentRecord.setReimbursementAmount(amountDebit);
        repaymentRecord.setStillNeedToPay(temp);
        repaymentRecord.setMethodPayed(methodPayed);
        repaymentRecord.setRemark(remark);
        repaymentRecord.setMemberSignature(memberSignature);
        arrearagesRecord.setReimbursement(arrearagesRecord.getReimbursement()+amountDebit);//已还款
//        member.setDebt(member.getDebt() - amountDebit);
        memberWallet.setDebt(memberWallet.getDebt()-amountDebit);
        if(temp==0){
            repaymentRecord.setIsPaidOff((byte)0);
            arrearagesRecord.setIsPaidOff((byte)0);
        }else{
            repaymentRecord.setIsPaidOff((byte)1);
            arrearagesRecord.setIsPaidOff((byte)1);
        }
        arrearagesRecordDao.update(arrearagesRecord);
        MemberWalletDao.update(memberWallet);
//        memberDao.update(member);
        repaymentRecordDao.insert(repaymentRecord);
        ExtJsResult ejr = new ExtJsResult();
        ejr.setMsg("还款成功");
        ejr.setMsgcode("0");
        ejr.setSuccess(true);
        return  ejr;
    }

}
