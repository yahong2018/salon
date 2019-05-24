package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.service.MemberService;
import com.hy.salon.basic.util.UuidUtils;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.dao.RoleUserDAO;
import com.zhxh.admin.dao.SystemRoleDAO;
import com.zhxh.admin.dao.SystemUserDAO;
import com.zhxh.admin.entity.RoleUser;
import com.zhxh.admin.entity.SystemRole;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.utils.StringUtilsExt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller
@RequestMapping("/hy/basic/externalCall")
@Api(value = "ExternalCallController| 消费")
public class ExternalCallController {

    @Resource(name = "memberWalletDAO")
    private MemberWalletDAO MemberWalletDao;

    @Resource(name = "memberDao")
    private MemberDao memberDao;

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;
    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @Resource(name = "memberTagDao")
    private MemberTagDao memberTagDao;

    @Resource(name = "picturesDao")
    private PicturesDAO picturesDao;

    @Resource(name = "systemUserDAO")
    private SystemUserDAO systemUserDAO;

    @Resource(name = "roleUserDAO")
    private RoleUserDAO roleUserDao;

    @Resource(name = "systemRoleDAO")
    private SystemRoleDAO systemRoleDAO;

    @Resource(name = "consumeRecordDao")
    private ConsumeRecordDAO consumeRecordDAO;



    @ResponseBody
    @RequestMapping("/memberWallet")
    @ApiOperation(value="顾客角色钱包", notes="顾客角色钱包")
    public Result memberWallet(String tel){
        Result r= new Result();
        try {
            Member member=memberDao.getMemberForTel(tel);
            if(null==member){
                r.setMsg("该顾客不存在");
                r.setSuccess(false);
                r.setMsgcode(StatusUtil.ERROR);
                return r;
            }

            MemberWallet memberWallet=MemberWalletDao.getMemberWalletForMemberId(member.getRecordId());

            r.setData(memberWallet);
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


    @ResponseBody
    @RequestMapping("/consumptionIntegral")
    @ApiOperation(value="消耗积分/代金券", notes="消耗积分/代金券")
    public Result consumptionIntegral(Double integral,Double cashCoupon,Double amount,String tel,String way){
        Result r= new Result();
        try {
            Member member=memberDao.getMemberForTel(tel);
            if(null==member){
                r.setMsg("该顾客不存在");
                r.setSuccess(false);
                r.setMsgcode(StatusUtil.ERROR);
                return r;
            }
            ConsumeRecord consumeRecord=new ConsumeRecord();
            consumeRecord.setWay(new Byte(way));
            String billNo= UuidUtils.generateShortUuid();
            consumeRecord.setBillNo(billNo);
            if(!"0".equals(way) && !"1".equals(way)){
                consumeRecord.setCash(new Double(0));
            }else{
                consumeRecord.setCash(amount);
            }
            consumeRecord.setMemberId(member.getRecordId());

            MemberWallet memberWallet=MemberWalletDao.getMemberWalletForMemberId(member.getRecordId());
            if(null!=integral){
                if(new BigDecimal(memberWallet.getIntegral()).compareTo(new BigDecimal(integral))==-1){
                    r.setMsg("积分不足");
                    r.setSuccess(false);
                    r.setMsgcode(StatusUtil.ERROR);
                    return r;
                }
                memberWallet.setIntegral(new BigDecimal(memberWallet.getIntegral()).subtract(new BigDecimal(integral)).doubleValue());
            }else{
                consumeRecord.setAmount(integral);
            }

            if(null!=cashCoupon){
                if(new BigDecimal(memberWallet.getCashCoupon()).compareTo(new BigDecimal(cashCoupon))==-1){
                    r.setMsg("代金券不足");
                    r.setSuccess(false);
                    r.setMsgcode(StatusUtil.ERROR);
                    return r;
                }
                memberWallet.setCashCoupon(new BigDecimal(memberWallet.getCashCoupon()).subtract(new BigDecimal(cashCoupon)).doubleValue());
            }else{
                consumeRecord.setAmount(cashCoupon);
            }

            MemberWalletDao.update(memberWallet);
            consumeRecordDAO.insert(consumeRecord);


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
     * 注册会员
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addMember")
    @ApiOperation(value="注册会员", notes="注册会员")
    public Result addMember(Member condition) {

        Result result = new Result();
        try {
            //检查该手机号是否被使用
            SystemUser user2=systemUserDAO.getUserByCode(condition.getTel());
            if(null != user2){
                result.setMsg("该账号已被使用");
                result.setSuccess(false);
                result.setMsgcode(StatusUtil.ERROR);
                return result;
            }

                String invitationCode= UuidUtils.generateShortUuid();
                condition.setInvitationCode(invitationCode);

            condition.setMemberGrade(new Long(3));
            memberDao.insert(condition);

            //创建钱包
            MemberWallet memberWallet=new MemberWallet();
            memberWallet.setMemberId(condition.getRecordId());
            memberWallet.setBalanceCash(new Double(0));
            memberWallet.setBalanceTotal(new Double(0));
            memberWallet.setIntegral(new Double(0));
            memberWallet.setDebt(new Double(0));
            memberWallet.setAmountCharge(new Double(0));
            memberWallet.setAmountConsumer(new Double(0));
            memberWallet.setCashCoupon(new Double(0));
            MemberWalletDao.insert(memberWallet);

            SystemUser userController=new SystemUser();
            userController.setUserCode(condition.getTel());
            userController.setUserName(condition.getMemberName());
            String passwordMd5 = StringUtilsExt.getMd5("123456");
            userController.setPassword(passwordMd5);
            userController.setUserStatus(0);
            systemUserDAO.insert(userController);

            SystemRole systemRole=systemRoleDAO.getRole("member");

            RoleUser roleUser=new RoleUser();
            roleUser.setRoleId(systemRole.getRecordId());
            roleUser.setUserId(userController.getRecordId());
            roleUserDao.insert(roleUser);



            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }




}
