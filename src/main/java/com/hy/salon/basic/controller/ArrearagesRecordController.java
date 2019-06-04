package com.hy.salon.basic.controller;

import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.zhxh.admin.dao.RoleUserDAO;
import com.zhxh.admin.entity.RoleUser;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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

    @Resource(name = "memberWalletDAO")
    private MemberWalletDAO MemberWalletDao;

    @Resource(name = "roleUserDAO")
    private RoleUserDAO roleUserDAO;
    /**
     * 欠款记录列表
     */
    @ResponseBody
    @RequestMapping("/getSystemArrearsList")
    @ApiOperation(value="欠款记录列表", notes="欠款记录列表")
    public ExtJsResult getSystemArrearsList(HttpServletRequest request,Long memberId,Long recordId,String toDays){
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
        ExtJsResult VipSuiteList=arrearagesRecordDao.getSystemArrearsList(memberId,recordId, request,toDays,role);


        return  VipSuiteList;
    }


    /**
     * 还欠款记录列表
     */
    @ResponseBody
    @RequestMapping("/getSystemRepaymentRecordList")
    @ApiOperation(value="欠款记录列表", notes="欠款记录列表")
    public ExtJsResult getSystemRepaymentRecordList(HttpServletRequest request,String memberName,Long recordId,String toDays,Long arrearagesRecord){
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
        ExtJsResult VipSuiteList=repaymentRecordDao.getSystemRepaymentRecordList(memberName,arrearagesRecord, request,recordId,role,toDays);


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
    @Transactional(rollbackFor = Exception.class)
    public ExtJsResult toArrears(HttpServletRequest request,long arrearagesRecordId,long memberId,double amountDebit,byte methodPayed,String remark,long memberSignature){
//        Member member = memberDao.getById(memberId);
        //获取顾客钱包
        MemberWallet memberWallet=MemberWalletDao.getMemberWalletForMemberId(memberId);
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



    @RequestMapping("/test")
    @Transactional(rollbackFor = Exception.class)
    public  void test (long arrearagesRecordId){
        ArrearagesRecord arrearagesRecord = arrearagesRecordDao.getById(arrearagesRecordId);//要还款的记录
        arrearagesRecordDao.test(arrearagesRecord);
        String[] string = new String[2];
        string[0] = "test0";
        string[1] = "test1";
        string[2] = "test2";
    }
/*    public static void main(String[] args) {
		String[] string = new String[2];
		string[0] = "test0";
		string[1] = "test1";
		string[2] = "test2";
    	
    	
	}*/



}
