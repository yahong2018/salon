package com.hy.salon.basic.controller;

import com.hy.salon.basic.dao.ArrearagesRecordDao;
import com.hy.salon.basic.dao.MemberDao;
import com.hy.salon.basic.dao.RepaymentRecordDao;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.ArrearagesRecord;
import com.hy.salon.basic.entity.Member;
import com.hy.salon.basic.entity.RepaymentRecord;
import com.hy.salon.basic.entity.Stuff;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.SimpleCRUDController;
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
@RequestMapping("/hy/basic/arrearagesRecord")
@Api(value = "ArrearagesRecordController| 还欠款控制器")
public class ArrearagesRecordController  {


    @Resource(name = "arrearagesRecordDao")
    private ArrearagesRecordDao arrearagesRecordDao;

    @Resource(name = "memberDao")
    private MemberDao memberDao;

    @Resource(name="repaymentRecordDao")
    private RepaymentRecordDao repaymentRecordDao;

    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;
    @Resource(name = "stuffDao")
    private StuffDao stuffDao;
    /**
     * 欠款记录列表
     */
    @ResponseBody
    @RequestMapping("/getSystemArrearsList")
    @ApiOperation(value="欠款记录列表", notes="欠款记录列表")
    public ExtJsResult getSystemArrearsList(HttpServletRequest request,String memberName,Long recordId,String toDays){
        if(recordId==null){
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff2 = stuffDao.getStuffForUser(user.getRecordId());
            recordId = stuff2.getStoreId();
        }

        //List<Service> serviceList= serviceDao.queryServiceForId(storeId);
        ExtJsResult VipSuiteList=arrearagesRecordDao.getSystemArrearsList(memberName,recordId, request);


        return  VipSuiteList;
    }


    /**
     * 还欠款记录列表
     */
    @ResponseBody
    @RequestMapping("/getSystemRepaymentRecordList")
    @ApiOperation(value="欠款记录列表", notes="欠款记录列表")
    public ExtJsResult getSystemRepaymentRecordList(HttpServletRequest request,String memberName,Long recordId,String toDays,Long arrearagesRecord){
        if(recordId==null){
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff2 = stuffDao.getStuffForUser(user.getRecordId());
            recordId = stuff2.getStoreId();
        }

        //List<Service> serviceList= serviceDao.queryServiceForId(storeId);
        ExtJsResult VipSuiteList=repaymentRecordDao.getSystemRepaymentRecordList(memberName,arrearagesRecord, request);


        return  VipSuiteList;
    }




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
    public ExtJsResult toArrears(HttpServletRequest request,long arrearagesRecordId,long memberId,double amountDebit,byte methodPayed,String remark,long memberSignature){
        Member member = memberDao.getById(memberId);
        ArrearagesRecord arrearagesRecord = arrearagesRecordDao.getById(arrearagesRecordId);//要还款的记录
        double AmountDept = arrearagesRecord.getAmountDept();
        double temp =AmountDept -  amountDebit -arrearagesRecord.getReimbursement();
        RepaymentRecord  repaymentRecord = new RepaymentRecord();
        repaymentRecord.setArrearagesRecord(arrearagesRecord.getRecordId());
        repaymentRecord.setReimbursementDate(new Date());
        repaymentRecord.setReimbursementAmount(amountDebit);
        repaymentRecord.setStillNeedToPay(temp);
        repaymentRecord.setMethodPayed(methodPayed);
        repaymentRecord.setRemark(remark);
        repaymentRecord.setMemberSignature(memberSignature);
        arrearagesRecord.setReimbursement(arrearagesRecord.getReimbursement()+amountDebit);//已还款
        member.setDebt(member.getDebt() - amountDebit);
        if(temp==0){
            repaymentRecord.setIsPaidOff((byte)0);
            arrearagesRecord.setIsPaidOff((byte)0);
        }else{
            repaymentRecord.setIsPaidOff((byte)1);
            arrearagesRecord.setIsPaidOff((byte)1);
        }
        arrearagesRecordDao.update(arrearagesRecord);
        memberDao.update(member);
        repaymentRecordDao.insert(repaymentRecord);
        ExtJsResult ejr = new ExtJsResult();
        ejr.setMsg("还款成功");
        ejr.setMsgcode("0");
        ejr.setSuccess(true);
        return  ejr;
    }





}
