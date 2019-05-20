package com.hy.salon.basic.controller;

import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.ApprovalProcessDAO;
import com.hy.salon.basic.dao.BillTypeDAO;
import com.hy.salon.basic.dao.ConsumeRecordDAO;
import com.hy.salon.basic.dao.SubmitApprovalDAO;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.util.UuidUtils;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.entity.RoleUser;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.core.web.ExtJsResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hy/basic/submitApproval")
@Api(value = "SubmitApprovalController| 审核控制器")
public class SubmitApprovalController {

    @Resource(name="billTypeDAO")
    private BillTypeDAO billTypeDAO;

    @Resource(name="approvalProcessDAO")
    private ApprovalProcessDAO approvalProcessDAO;

    @Resource(name="submitApprovalDAO")
    private SubmitApprovalDAO  submitApprovalDAO;





    @ResponseBody
    @RequestMapping("/addBillType")
    @ApiOperation(value="添加单据类型", notes="添加单据类型")
    public Result addBillType(BillType condition){
        Result r=new Result();
        billTypeDAO.insert(condition);
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return  r;
    }

    @ResponseBody
    @RequestMapping("/queryBillTypeList")
    @ApiOperation(value="单据类型列表", notes="单据类型列表")
    public Result queryBillTypeList(){
        Result r=new Result();
        List<BillType> billType=billTypeDAO.getAll();
        r.setData(billType);
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return  r;
    }


    //提交审核
//    @ResponseBody
//    @RequestMapping("/addSubmitApproval")
//    @ApiOperation(value="单据类型列表", notes="单据类型列表")
//    public Result addSubmitApproval(SubmitApproval condition,Long billTypeId,Long storeId){
//        Result r=new Result();
//
//        //查找该门店，该类型的审批流程
//
//        ApprovalProcess approvalProcess=approvalProcessDAO.getApprovalProcessForId(storeId,billTypeId);
//        if(null == approvalProcess){
//            r.setMsg("请先创建审批流程");
//            r.setMsgcode(StatusUtil.ERROR);
//            r.setSuccess(true);
//            return r;
//        }
//        condition.setApprovalNumber(UuidUtils.generateShortUuid());
//        submitApprovalDAO.insert(condition);
//
//
//        ApprovalRecord approvalRecord1;
//        ApprovalRecord approvalRecord2 = null;
//        ApprovalRecord approvalRecord3 = null;
//        ApprovalRecord approvalRecord4 = null;
//
//        approvalRecord1=new ApprovalRecord();
//        approvalRecord1.setSubmitApprovalId(condition.getRecordId());
//        approvalRecord1.setApprovalStatus(new Byte("3"));
//        approvalRecord1.setApprovalDate(new Date());
//        approvalRecord1.setApprovalType(1);
//        approvalRecord1.setSeveralApprovals(1);
//        approvalRecord1.setSeveralApprovalsStuffId(approvalProcess.getFirst());
//        approvalRecord1.setIsLast(1);
//
//
//        if(approvalProcess.getSecond()!=null){
//            approvalRecord2=new ApprovalRecord();
//            approvalRecord2.setSubmitApprovalId(condition.getRecordId());
//            approvalRecord2.setApprovalStatus(new Byte("2"));
//            approvalRecord2.setApprovalDate(new Date());
//            approvalRecord2.setApprovalType(1);
//            approvalRecord2.setSeveralApprovals(2);
//            approvalRecord2.setSeveralApprovalsStuffId(approvalProcess.getSecond());
//            approvalRecord2.setIsLast(1);
//
//            if(approvalProcess.getSecond()==condition.getApprover()){
//                approvalRecord1.setApprovalStatus(new Byte("0"));
//            }
//
//        }else{
//            approvalRecord1.setIsLast(0);
//        }
//
//
//
//        if(approvalProcess.getThird()!=null){
//            approvalRecord3=new ApprovalRecord();
//            approvalRecord3.setSubmitApprovalId(condition.getRecordId());
//            approvalRecord3.setApprovalStatus(new Byte("2"));
//            approvalRecord3.setApprovalDate(new Date());
//            approvalRecord3.setApprovalType(1);
//            approvalRecord3.setSeveralApprovals(3);
//            approvalRecord3.setSeveralApprovalsStuffId(approvalProcess.getThird());
//            approvalRecord3.setIsLast(1);
//            if(approvalProcess.getThird()==condition.getApprover()){
//                approvalRecord1.setApprovalStatus(new Byte("0"));
//            }
//        }else{
//            approvalRecord2.setIsLast(0);
//        }
//
//
//        if(approvalProcess.getFour()!=null){
//            approvalRecord4=new ApprovalRecord();
//            approvalRecord4.setSubmitApprovalId(condition.getRecordId());
//            approvalRecord4.setApprovalStatus(new Byte("2"));
//            approvalRecord4.setApprovalDate(new Date());
//            approvalRecord4.setApprovalType(1);
//            approvalRecord4.setSeveralApprovals(4);
//            approvalRecord4.setSeveralApprovalsStuffId(approvalProcess.getFour());
//            approvalRecord4.setIsLast(0);
//            if(approvalProcess.getThird()==condition.getApprover()){
//                approvalRecord1.setApprovalStatus(new Byte("0"));
//        }else{
//            approvalRecord3.setIsLast(0);
//        }
//
////        UuidUtils.
//
//
//
//
//        r.setMsgcode(StatusUtil.OK);
//        r.setSuccess(true);
//        return  r;
//    }


}
